import java.util.ArrayList;
import java.util.Scanner;

public class Input {
	private Scanner in;
	
	public Input() {
		in = new Scanner(System.in);
	}
	
	/* -------------- Menus -------------- */
	
	// Main menu
    public String mainMenu(Boolean inputValid) {
    	if(inputValid) {
			System.out.println("Please select one of the following options:");
		}
		else {
			System.out.println("\nPlease select a valid menu option:");
		}
        System.out.println("1) Take a quiz");
        System.out.println("2) Login as admin");
        System.out.println("3) Exit");
		return cleanseInput(in.nextLine());
    }
    
	// Admin menu
    public String adminMenu(Boolean inputValid) {
    	if(inputValid) {
			System.out.println("Please select one of the following options:");
		}
		else {
			System.out.println("\nPlease select a valid menu option:");
		}
        System.out.println("1) Add a new quiz");
        System.out.println("2) Edit an existing quiz");
        System.out.println("3) Logout");
		return cleanseInput(in.nextLine());
    }
    
    /*
    // Quiz menu
    public String quizMenu(Boolean inputValid, ArrayList<Quiz> quizzes) {
    	System.out.println("Which quiz would you like to play?");
    	if(inputValid) {
			System.out.println("Please select one of the following options:");
		}
		else {
			System.out.println("\nPlease select a valid menu option:");
		}
    	// Display an option for each of the available quizzes
    	for (int i = 0; i < quizzes.size(); ++i) {
    		System.out.println((i+1) + ") " + quizzes.get(i).getTitle() + "\n");
    	}
		return cleanseInput(in.nextLine());
    }
    */
    
    /* -------------- Take Quiz -------------- */
    public String getQuestionAnswer(Boolean inputValid, Question question) {
    	if(inputValid) {
			System.out.println("\nEnter your answer [1 to " + question.getNumberOfAnswers() + "] (Enter 0 To Restart The Quiz)");
		}
		else {
			System.out.println("\nEnter a valid answer [1 to " + question.getNumberOfAnswers() + "] (Enter 0 To Restart The Quiz)");
		}
		return cleanseInput(in.nextLine());
    }
    
    public String getQuizSelection(Boolean inputValid, int maxNumber) {
    	if(inputValid) {
			System.out.println("\nWhich quiz do you want to play [1 to " + maxNumber + "]");
		}
		else {
			System.out.println("\nPlease select a valid quiz [1 to \" + maxNumber + \"]");
		}
		return cleanseInput(in.nextLine());
    }
    
    private String cleanseInput(String input) {
		String cleansed = input.trim();
		cleansed = cleansed.replaceAll("\\pP", ""); // replaces all punctuation
		return cleansed;
    }
}