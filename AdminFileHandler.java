import java.io.*;
import java.util.*;

public class AdminFileHandler {
	public static void addQuestion(String file_name, boolean _isAdding) {
		Scanner in = new Scanner(System.in);
    	FileHandler file = new FileHandler();
    	Quiz current_quiz = new Quiz();
    	Question question = new Question();
    	int input_position=0;

    	try {
			if (!_isAdding) {//determines whether we're adding a NEW question or REPLACING an existing question
				current_quiz.addQuestion(question);
				System.out.print("Enter which question you want to edit: ");
				input_position= in.nextInt();
				in.nextLine();		
			}	

			System.out.print("Enter question here: ");
			String input_question = in.nextLine();
			question.setQuestionText(input_question);

			System.out.print("Enter the number of answer options here: ");
			int _numberOfAnswers = in.nextInt();
			in.nextLine();
			boolean _rightAnswerSelected = false;
			for (int i=0;i<=_numberOfAnswers-1;i++) {
				System.out.print("Enter answer no. " + (i+1) + " here: ");
				String input_answer = in.nextLine();

				if (_rightAnswerSelected==false) {	
					System.out.print("Is this the right answer? (y/n):");
					if ((in.nextLine()).equals("y")) {	
						question.addAnswer(input_answer, true);
						_rightAnswerSelected = true;
					} else {
						question.addAnswer(input_answer, false);
					}
				} else {
					question.addAnswer(input_answer, false);
				}
			}

			current_quiz = file.readFromFile(file_name);
			if (_isAdding) {
				current_quiz.addQuestion(question);
			} else {
				current_quiz.replaceQuestionByIndex(input_position-1, question);
			}
			file.saveCurrentQuiz(current_quiz.getQuestions(), file_name);
    		System.out.println("\n" + "Successfully added a question." + "\n");

    	} catch (IOException e){
    		System.out.println("\nUnable to read into the class.\n");
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
				return;
			}
    		file.saveCurrentQuiz(current_quiz.getQuestions(), file_name);
    		System.out.println("\n" + "Successfully deleted a question." + "\n");
    	} catch (IOException e) {
    		System.out.println("\nUnable to read into the class.\n");
    	}
    }
}

