import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; // Imports modules to deal with files, exceptions, user input and ArrayLists
// * = answer - = correct answer

public class takeQuizEdits {

  public takeQuizEdits(String fileName) { // takes the filename "quiz.txt"

    try {
      File fileIn = new File(fileName); // opens the file
      Scanner in = new Scanner(fileIn); // creates a new instance of Scanner (for file)

      ArrayList correctAnswers = new ArrayList(); // creates list of correctAnswers
      int questionNumber = 0; // keeps track of current question

    while (in.hasNextLine()) { // while file has another line
      String line = in.nextLine();
      String testEmpty = line.replaceAll("\"", ""); // removes spaces from the line

      if (testEmpty.isEmpty() == false) { // if its a blank line (no spaces)
        printValues(line, correctAnswers); // calls printvalues
    }
      else {
        getAnswer(correctAnswers, questionNumber); // calls getAnswer
        questionNumber += 1; // moves to next question
      }
  }
  // getAnswer(correctAnswers, questionNumber); // CURRENT FIX FOR NOT CALLING GET ANSWER ON LAST LINE OF FILE
}

    catch (FileNotFoundException e){ // if the quiz txt file is not present
      System.out.println("Problem");
    }
  }

  public void getAnswer(ArrayList correctAnswers, int questionNumber) {
    Scanner userInput = new Scanner(System.in); // new instance of Scanner (for user input)
    System.out.println("Enter your answer: ");
    String userAnswer = userInput.nextLine(); // asks for user inputs

    userAnswer = userAnswer.replaceAll("\\pP", ""); // replaces all punctuation

    if (correctAnswers.indexOf(userAnswer) == questionNumber) { // if the question number matches the index (1st index=answer for first question)
      // Potential issue could be caused if the same correct answer is in multiple Qs
      // indexOf will return the FIRST index with the answer
      // so if the same answer is done twice it will only count the first
      System.out.println("Correct!");
      System.out.println();
    }

    else {
      System.out.println("Incorrect!");
      System.out.print("The correct answer was: "+ correctAnswers.get(questionNumber)); // retrieves correct answer
      System.out.println();
      System.out.println();
    }


  }

  public void printValues(String line, ArrayList correctAnswers) {

    if (line.contains("*")) {
      System.out.println(line.replaceAll("\\pP", "")); // strips punctuation

      if (line.contains("-")) {
        correctAnswers.add(line.replaceAll("\\pP", "")); // strips punctuation and appends to correct answers
      }
    }
    else {
      System.out.println(line);
    }
  }
}
