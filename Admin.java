import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
	private Input input = new Input();
	private FileHandler file = new FileHandler();
	private Boolean inputValid=true;

	public Admin() {
	}
	
    private void setInputValid(Boolean inputValid) {
        this.inputValid = inputValid;
    }
	
	public void addAdmin(){
		// Admin ID
		AdminAccount admin = new AdminAccount();
		do {
			try {
				admin.setAdminID(input.getAdminID(inputValid));
				setInputValid(true);
			}
    		catch(IllegalArgumentException e) {
    			setInputValid(false);
    		}
		} while(!inputValid);
		
		// Admin password
		do {
			try {
				admin.setPassword(input.getAdminPassword(inputValid));
				setInputValid(true);
			}
    		catch(IllegalArgumentException e) {
    			setInputValid(false);
    		}
		} while(!inputValid);

		// Admin name
		do {
			try {
				admin.setAdminName(input.getAdminName(inputValid));
				setInputValid(true);
			}
    		catch(IllegalArgumentException e) {
    			setInputValid(false);
    		}
		} while(!inputValid);
	
		// Add new admin to file
		
		
		// Move this to file handler
		try {
			FileWriter file_write = new FileWriter("Files/admin/admin_database.txt", true);
			String new_admin = admin.getAdminID() + "," + admin.getPassword() + "," + admin.getAdminName();
			String s = System.getProperty("line.separator");
			file_write.write(s + new_admin);
			file_write.close();
			System.out.println("\nSuccessfully added " + admin.getAdminName() + " into the database.\n");
		} catch (IOException e) {
			System.out.println("\nUnable to add " + admin.getAdminName() + " into the database.\n");
		}
	}

	public String addNewQuiz() {
		String filename = "";
		try {
			filename = input.getNewQuizName();
			file.createFile(filename);
		} catch (IOException e) {
			System.out.println("Unable to create new file.");
		}
		return filename;
	}
	
	public Quiz addNewQuizCopy() {
		setInputValid(true);
		Quiz quiz = new Quiz();
		ArrayList<String> quizList = file.getQuizList("Files/quizzes/");
		do {
			try {
				String quizSelection = input.getQuizToCopy(inputValid, quizList);
				Quiz quizToCopy = new Quiz();
				quizToCopy = loadQuiz(quizList.get(Integer.parseInt(quizSelection)-1));
				quiz = quizToCopy.copy();
				setInputValid(true);
			} catch (Exception e) {
				setInputValid(false);
			}
		} while (!inputValid);
		String filename = input.getNewQuizName();
		quiz.setTitle(filename);
		  try {
			  file.saveQuiz(quiz);
		  } catch (IOException e) {
			  System.out.println("\nUnable to add quiz.");
		  }
		return quiz;
	}
	
	public String selectExistingQuizFile() {
		FileHandler file = new FileHandler(); 
		ArrayList<String> quizList = file.getQuizList("Files/quizzes/"); //reused a code from FileHandler.java to obtain all exisitng files
		System.out.println("\nExisting quiz file(s) you may edit:");
		for (int i=0;i<=quizList.size()-1;i++) {
			System.out.println("  " + (i+1) + ". " + quizList.get(i));
		}
		return quizList.get(Integer.parseInt(input.getQuizToEdit(quizList))-1);
	}
	
	  public Quiz loadQuiz(String fileName) {
		  Quiz currentQuiz = new Quiz();
		  try {
			  currentQuiz = file.readFromFile(fileName);
		  } catch (IOException e) {
			  System.out.println("Sorry there was a problem");
			  e.printStackTrace();
		  }
		  return currentQuiz;
	  }
	  
	  public void editQuestion(Quiz quiz) {
		  setInputValid(true);
		  do {
			  try {
					int selection = (Integer.parseInt(input.editQuestion(inputValid, quiz)));
					if (selection == 0) {
						return;
					}
				  Question question = quiz.getQuestions().get(selection - 1);
				  question.setQuestionText(input.getNewQuizQuestion());
				  question.getAnswerList().clear();
				  String numberOfAnswers = input.getNewQuizAnswers();
				  boolean _rightAnswerSelected = false;
				  for (int i = 0; i < Integer.parseInt(numberOfAnswers); i++) {
					  Answer answer = new Answer();
					  answer.setTitle(input.getAnswer(i + 1)); 
					  answer.setCorrect(false);
					  if (_rightAnswerSelected == false) {
						  if (input.rightAnswer().equals("y")) {
							  answer.setCorrect(true);
							  _rightAnswerSelected = true;
						  }
					  }
					  question.addAnswer(answer);
				  }
				  try {
					  file.saveQuiz(quiz);
					  System.out.println("\nSuccessfully added a question into the database.");
				  } catch (IOException e) {
					  System.out.println("\nUnable to add question into the database.");
				  }
				  setInputValid(true);}
			  catch (Exception e) {
				  setInputValid(false);
			  }
		  }while(!inputValid);
	  }

	public void addQuestion(Quiz quiz) {
		Question question = new Question();
		question.setQuestionText(input.getNewQuizQuestion());
		String numberOfAnswers = input.getNewQuizAnswers();
		boolean _rightAnswerSelected = false;
		for (int i = 0; i < Integer.parseInt(numberOfAnswers); i++) {
			Answer answer = new Answer();
			answer.setTitle(input.getAnswer(i + 1)); 
			answer.setCorrect(false);
			if (_rightAnswerSelected == false) {
				if (input.rightAnswer().equals("y")) {
					answer.setCorrect(true);
					_rightAnswerSelected = true;
				}
			}
			question.addAnswer(answer);
		}
		quiz.addQuestion(question);
		try {
			file.saveQuiz(quiz);
			System.out.println("\nSuccessfully added a question into the database.");
		} catch (IOException e) {
			System.out.println("\nUnable to add question into the database.");
		}
		setInputValid(true);
	}
	
	public void deleteQuestion(Quiz quiz) {
		setInputValid(true);
		do {
			try {
				int selection = (Integer.parseInt(input.deleteQuestion(inputValid, quiz)));
				if (selection == 0) {
					return;
				}
					quiz.deleteQuestionByIndex(selection - 1);
				setInputValid(true);
			} catch (Exception e) {
				setInputValid(false);
			}
		} while (!inputValid);
		try {
			file.saveQuiz(quiz);
			System.out.println("\nSuccessfully deleted question.");
		} catch (IOException e) {
			System.out.println("\nUnable to delete question.");
		}
		setInputValid(true);
	}

	public void displayClientStatistics() {
		System.out.println("The total number of correct answers on this client is: ");
		System.out.println("The average number of answers correct is: ");
		System.out.println("The average time taken per quiz is: ");
		for (int i = 0; i < 10; i++) {
			System.out.println("The average time taken for question " + Integer.toString(i + 1) + " is: ");
		}
	}
	
	public void addSchool() {
		Scanner input = new Scanner(System.in);
		School school = new School();

		System.out.println("\nEnter the name of the school you wish to enter: ");
		String name = input.nextLine();
		while(!name.matches("[a-zA-Z ]+")){
			System.out.println("\nEnter a valid name:");
			name = input.nextLine();
		}
		school.setName(name);

		System.out.println("\nEnter the school year of the students: ");
		String year = input.nextLine();
		while(!year.matches("[0-9]+")){
			System.out.println("\nEnter a valid year:");
			year = input.nextLine();
		}
		school.setYear(year);
		System.out.println("\n");
		try{
			FileWriter writer = new FileWriter("Files/admin/Schools.txt", true);
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter out = new PrintWriter(writer);

			bw.write(name + "," + "Year:" + year);
			bw.newLine();
			bw.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
