import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class takeQuiz {

  public takeQuiz(String fileName) {
    
    try {
      File fileIn = new File(fileName);
      Scanner in = new Scanner(fileIn);;


    while (in.hasNextLine()) {
      String line = in.nextLine();

      System.out.println(line);
    }
  }

    catch (FileNotFoundException e){
      System.out.println("Problem");
    }
  }
}
