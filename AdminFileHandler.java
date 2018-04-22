import java.io.*;
import java.util.*;

public class AdminFileHandler {
	public static void addQuestion(String file_path) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter question here: ");
		String _question = in.nextLine();

		System.out.print("Enter the number of answer options here: ");
		String _numberOfAnswers = in.nextLine();

		String answers[] = new String[Integer.parseInt(_numberOfAnswers)];

		boolean _rightAnswerSelected = false;
		for (int i=0;i<=answers.length-1;i++) {
			System.out.print("Enter answer no. " + (i+1) + " here: ");
			answers[i] = in.nextLine() + "*";

			if (_rightAnswerSelected==false) {	
				System.out.print("\nIs this the right answer? (y/n):");

				if ((in.nextLine()).equals("y")) {	
					answers[i] = answers[i] + "-";
					_rightAnswerSelected = true;
				}
			}
		}

		try {
			FileWriter file_write = new FileWriter(file_path, true);

			String s = System.getProperty("line.separator");

			file_write.write(s + s + _question);

			for (int i=0;i<=answers.length-1;i++) {
				file_write.write(s + answers[i]);
			}
			file_write.close();
			System.out.println("\nSuccessfully added a question into the database.\n");
		} catch (IOException e) {
			System.out.println("\nUnable to add question into the database.\n");
		}
	}
}