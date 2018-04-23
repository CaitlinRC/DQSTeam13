import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
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
    double timeStartStudent;
    double timeTakenStudent;
    double timeStartQuestion;
    double[] timeTakenPerQuestion = new double[10];
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
			timeStartStudent = System.currentTimeMillis();
			for (int i = 0; i < currentQuiz.getQuestions().size(); i++) {
				timeStartQuestion = System.currentTimeMillis();
				restart = "";
				getAnswer(currentQuiz.getQuestions().get(i), i + 1);
			  	if (restart.equals("0")) {
					i = -1;
				  	numberCorrect = 0;
				 	totalAnswers = 0;
				  	System.out.println("\nRestarting " + fileName);
			  	}
			  	timeTakenPerQuestion[i] = Math.round((System.currentTimeMillis() - timeStartQuestion) / 1000);
		  	}

		  	timeTakenStudent = Math.round((System.currentTimeMillis() - timeStartStudent) / 1000);

   			studentStatistics = new StudentStatistics(numberCorrect, timeTakenStudent, timeTakenPerQuestion);

		  	System.out.println("\nQuiz Finished!\n");
		  	try {TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex) {}
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

        System.out.println("Your score was: " + numberCorrect + "\n");
        try {TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex) {}
        System.out.println("Your time was: " + timeTaken + " seconds\n");
        try {TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex) {}
        for (int i = 0; i < 10; i++) {
            System.out.println("For question " + Integer.toString(i + 1) + " you took " + timeTakenPerQuestion[i] + " seconds");
            try {TimeUnit.MILLISECONDS.sleep(500);} catch(InterruptedException ex) {}
        }
        System.out.println("\nYour average time per question was: " + averageTimePerQuestion);
        try {TimeUnit.SECONDS.sleep(1);} catch(InterruptedException ex) {}
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
