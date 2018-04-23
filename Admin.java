import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Admin {
	private Input input = new Input();
	private FileHandler file = new FileHandler();
	private Boolean inputValid;
	
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
				admin.setAdminID(input.getAdminPassword(inputValid));
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
<<<<<<< HEAD
		  setInputValid(true);
		  do {
			  try {
				  int selection = (Integer.parseInt(input.deleteQuestion(inputValid, quiz)));
				  if(selection == 0) {
						return;
				  }
				  Question question = quiz.getQuestions().get(selection-1);
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
=======
	  	int inputtedIndex = Integer.parseInt(input.editQuestion()) - 1;
	  	if (inputtedIndex >= quiz.getSize() || inputtedIndex < 0) {
	  		System.out.println("Sorry but that is an invalid index");
	  		return;
	  	}
		  Question question = quiz.getQuestions().get(inputtedIndex);
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
>>>>>>> 6b5aad277ff35592191e229b16ef00075bb1f98d
				  }

			  }	 catch (Exception e) {
				  setInputValid(false);
			  }
		  } while (!inputValid);
		  try {
			  file.saveQuiz(quiz);
			  System.out.println("\nSuccessfully added a question into the database.");
		  } catch (IOException e) {
			  System.out.println("\nUnable to add question into the database.");
		  }
		  setInputValid(true);
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
				try {
<<<<<<< HEAD
					quiz.deleteQuestionByIndex(selection - 1);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("\nQuestion does not exist. Therefore, can not be deleted.\n");
=======
					int selection = (Integer
							.parseInt(input.deleteQuestion(inputValid, quiz.getQuestions().size())));
					try {
						quiz.deleteQuestionByIndex(selection - 1);
					} catch (IndexOutOfBoundsException e) {
						System.out.println("\nQuestion does not exist. Therefore, can not be deleted.\n");
						return;
					}
					setInputValid(true);
				} catch (Exception e) {
					setInputValid(false);
>>>>>>> 6b5aad277ff35592191e229b16ef00075bb1f98d
				}

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
}
