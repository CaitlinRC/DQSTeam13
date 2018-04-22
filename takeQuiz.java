import java.io.IOException;
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
    FileHandler files = new FileHandler();
    String fileName = files.getQuizFileName("\nWhich quiz do you want to play ");
    System.out.println(fileName);
    Quiz currentQuiz = new Quiz();
    currentQuiz = getQuiz(fileName, files);
    for (int i = 0; i < currentQuiz.getQuestions().size(); i++) {
      restart = "";
      getAnswer(currentQuiz.getQuestions().get(i));

      if (restart.equals("0")) {
        i = -1;
        numberCorrect = 0;
        totalAnswers = 0;
        System.out.println();
        System.out.println("Restarting Quiz");
        System.out.println();
      }
      // Do whatever you want with statistics here I think? (like increment correct answers if correct == true)
    }

    System.out.println("\nQuiz Finished!");
    System.out.println("Your Score: " + numberCorrect + "/" + totalAnswers + "\n"); // produces score if someone finished the queue
  }

  public Quiz getQuiz(String fileName, FileHandler files) {
    Quiz currentQuiz = new Quiz();
    try {
      currentQuiz = files.readFromFile(fileName);
    } catch (IOException e) {
      System.out.println("Sorry there was a problem");
      e.printStackTrace();
    }
    return currentQuiz;
  }

  public boolean getAnswer(Question question) {
    System.out.print("\n" + question);
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
				        System.out.println();
				        numberCorrect += 1;
				        correct = true;
				      }
				      else if (input == 0) {
				        restart = "0";
				        correct = false;
				      }
				       else {
				        System.out.println("\nIncorrect!");
				        System.out.print("The correct answer was: "+ question.getCorrectAnswer());
				        System.out.println();
				        correct = false;
				      }
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
