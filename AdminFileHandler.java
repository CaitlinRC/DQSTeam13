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
			BufferedReader buff_reader = new BufferedReader(new FileReader(file_path));
			String s = System.getProperty("line.separator");
			if ((buff_reader.readLine()) == null) {
				file_write.write(_question);
			} else {
				file_write.write(s + s + _question);
			}

			for (int i=0;i<=answers.length-1;i++) {
				file_write.write(s + answers[i]);
			}
			file_write.close();
			System.out.println("\nSuccessfully added a question into the database.\n");
		} catch (IOException e) {
			System.out.println("\nUnable to add question into the database.\n");
		}
	}

    public static void deleteQuestion(String file_name) {
    	Scanner in = new Scanner(System.in);
    	FileHandler file = new FileHandler();
    	Quiz current_quiz = new Quiz();

    	try {
    		current_quiz = file.readFromFile(file_name);

    		System.out.print("\nEnter the position of the question you want to be removed (choose between: 1 - " + current_quiz.getQuestions().size() + "): ");
			while (!in.hasNextInt()) { //|| !(in.nextInt()<=current_quiz.getQuestions().size()) || !(in.nextInt()>=1)) {
				System.out.println("\nInvalid Selection.");
				System.out.print("Enter the position of the question you want to be removed (choose between: 1 - " + current_quiz.getQuestions().size() + "): ");
				in.next();
			}		
			int _input = in.nextInt();
			try {
    			current_quiz.deleteQuestionByIndex(_input-1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("\nQuestion does not exist. Therefore, can not be deleted.\n");
			}
    		file.saveCurrentQuiz(current_quiz.getQuestions(), file_name);
    		System.out.println("\n" + "Successfully deleted a question." + "\n");
    	} catch (IOException e) {
    		System.out.println("\nUnable to read into the class.\n");
    	}
    }
}

