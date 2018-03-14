import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

public class takeQuiz {

  public takeQuiz(String fileName) {

    try {
      File fileIn = new File(fileName);
      Scanner in = new Scanner(fileIn);;

      String question;
      ArrayList correctAnswers = new ArrayList();
      int questionNumber = 0;

    while (in.hasNextLine()) {
      // Maybe do text file with stop words?
      String line = in.nextLine();
      String testEmpty = line.replaceAll("\"", "");

      if (testEmpty.isEmpty() == false) {
        printValues(line, correctAnswers);
    }
      else {
        getAnswer(correctAnswers, questionNumber);
        questionNumber += 1;
      }
  }
  getAnswer(correctAnswers, questionNumber);
}

    catch (FileNotFoundException e){
      System.out.println("Problem");
    }
  }

  public void getAnswer(ArrayList correctAnswers, int questionNumber) {
    Scanner userInput = new Scanner(System.in);
    System.out.println("Enter your answer: ");
    String userAnswer = userInput.nextLine();

    userAnswer = userAnswer.replaceAll("\\pP", "");

    if (correctAnswers.indexOf(userAnswer) == questionNumber) {
      System.out.println("Correct!");
    }

    else {
      System.out.println("Incorrect!");
    }

  }

  public void printValues(String line, ArrayList correctAnswers) {

    if (line.contains("*")) {
      System.out.println(line.replaceAll("\\pP", "")); // strips punctuation

      if (line.contains("-")) {
        correctAnswers.add(line.replaceAll("\\pP", ""));
      }
    }
    else {
      System.out.println(line);
    }
  }
}
