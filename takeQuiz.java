import java.io.IOException;
import java.util.ArrayList;
// * = answer, - = correct answer

public class takeQuiz {
	int numberCorrect = 0;
	int totalAnswers = 0;
  	String restart = "";
  	private Boolean inputValid;
  	private Input in = new Input();
  
  	private void setInputValid(Boolean inputValid) {
    	this.inputValid = inputValid;
  }

public takeQuiz() {
	  // Prompt user for their school and year group
  	StudentStatistics studentStatistics;
    ClientStatistics ClientStatistics;
    double timeStartStudent;
    double timeTakenStudent;
    double timeStartQuestion;
    double timeTakenQuestion; 
    double[] timeTakenPerQuestion = new double[10];
    doube timeTakenClient = 0;
    int numberCorrectClient = 0;
    int timesQuizTakenClient = 0
    int minNumberCorrect = 0;
    int maxNumberCorrect = 0;
	setInputValid(true);
	FileHandler files = new FileHandler();
	do {
		try {
			ArrayList<String> quizList = files.getQuizList("Files/quizzes/");
			int quizSelection = (Integer.parseInt(in.getQuizSelection(inputValid, quizList)));
			String fileName = quizList.get(quizSelection-1);
			System.out.println("\nQuiz selected: " + fileName);
			Quiz currentQuiz = new Quiz();
			currentQuiz = loadQuiz(fileName, files);
			for (int i = 0; i < currentQuiz.getQuestions().size(); i++) {
				restart = "";
				getAnswer(currentQuiz.getQuestions().get(i), i+1);
			  	if (restart.equals("0")) {
					i = -1;
				  	numberCorrect = 0;
				 	totalAnswers = 0;
				  	System.out.println("\nRestarting " + fileName);
			  	}
		  	}
			System.out.println("Quiz Finished!");
   			timeTakenClient += timeTakenStudent;
   			numberCorrectClient += numberCorrect;
			timesQuizTakenClient += 1;

  			minNumberCorrect = numberCorrect < minNumberCorrect ? numberCorrect : minNumberCorrect;
   			maxNumberCorrect = numberCorrect > maxNumberCorrect ? numberCorrect : maxNumberCorrect;

   			studentStatistics = new StudentStatistics(numberCorrect, timeTaken, timeTakenPerQuestion);
		  	System.out.println("\nQuiz Finished!");
		  	displayStudentStatistics(studentStatistics);

	  	} catch(Exception e) {
	  		setInputValid(false);
		}
	  } while(!inputValid);
  }

  public void displayStudentStatistics(StudentStatistics studentStatistics) {
        int numberCorrect = studentStatistics.getNumberCorrect();
        double timeTaken = studentStatistics.getTimeTaken();
        double[] timeTakenPerQuestion = studentStatistics.getTimeTakenPerQuestion();
        double averageTimePerQuestion = studentStatistics.getAverageTimePerQuestion();

        System.out.println("Your score is: " + numberCorrect);
        System.out.println("Your time was: " + timeTaken + "seconds");
        for (int i = 0; i < 10; i++) {
            System.out.println("For question " + Integer.toString(i + 1) + " you took " + timeTakenPerQuestion[i] " seconds");
        }
        System.out.println("Your average time per question was: " + averageTimePerQuestion);
    }

public Quiz loadQuiz(String fileName, FileHandler files) {
	Quiz currentQuiz = new Quiz();
	try {
		currentQuiz = files.readFromFile(fileName);
	} catch (IOException e) {
		System.out.println("Sorry there was a problem");
		e.printStackTrace();
	}
	return currentQuiz;
}

public boolean getAnswer(Question question, int questionNumber) {
    System.out.print("\nQuestion " + questionNumber + ":\n" + question);
    totalAnswers += 1;
    Boolean correct = false;
    setInputValid(true);
		do {
			try {
				int input = (Integer.parseInt(in.getQuestionAnswer(inputValid, question)));
				if(input > 0 && input <= question.getNumberOfAnswers()) {
					setInputValid(true);
				    if (question.isCorrect(input) == true) {
				        System.out.println("\nCorrect!");
				        numberCorrect += 1;
				        correct = true;
				      }
				       else {
				        System.out.println("\nIncorrect!");
				        System.out.print("The correct answer was: "+ question.getCorrectAnswer());
				        System.out.println();
				        correct = false;
				      }
				}
			    else if (input == 0) {
			    	setInputValid(true);
			    	restart = "0";
				    correct = false;
				}
				else {
					setInputValid(false);
				}
			}
			catch(Exception e) {
				setInputValid(false);
			}
		} while(!inputValid); // Loop until we get valid user input
		return correct;
	}
}
