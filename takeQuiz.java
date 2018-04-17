import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
// * = answer, - = correct answer

public class takeQuiz {

  public takeQuiz() { // takes the filename "quiz.txt"
    FileHandler files = new FileHandler();
    String fileName = files.getQuizFileName("Which quiz do you want to play "); 
    System.out.println(fileName);
    ArrayList<Question> currentQuiz = new ArrayList<Question>();
    currentQuiz = getQuiz(fileName, files);
    
    boolean correct = false;
    for (int i = 0; i < currentQuiz.size(); i++) {
      correct = getAnswer(currentQuiz.get(i));
      // Do whatever you want with statistics here I think? (like increment correct answers if correct == true) 
    }
  }

  public ArrayList<Question> getQuiz(String fileName, FileHandler files) {
    ArrayList<Question> currentQuiz = new ArrayList<Question>();
    try {
      currentQuiz = files.readFromFile(fileName);
    } catch (IOException e) {
      System.out.println("Sorry there was a problem");
      e.printStackTrace();
    }
    return currentQuiz;
  }

  public boolean getAnswer(Question inQuestion) {
    Scanner userInput = new Scanner(System.in); // new instance of Scanner (for user input)
    System.out.print(inQuestion);
    System.out.println("Enter your answer [1 to " + inQuestion.getNumberOfAnswers() + "]: ");
    String userAnswer = userInput.nextLine(); // asks for user inputs
    if (inQuestion.isCorrect(userAnswer) == true) {
      System.out.println("Correct!");
      System.out.println();
      return true;
    } else {
      System.out.println("Incorrect!");
      System.out.print("The correct answer was: "+ inQuestion.getCorrectAnswer());
      System.out.println();
      return false;
    }
  }

}
