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
        System.out.println("3) Add new admin");
        System.out.println("4) Add visiting School");
        System.out.println("5) Display Schools");
        System.out.println("6) Logout");
		return cleanseInput(in.nextLine());
    }
    
    public String adminQuizMenu(Boolean inputValid, String filename) {
    	if(inputValid) {
			System.out.println("\nYou may do the following with " + filename + ": ");
		}
		else {
			System.out.println("\nPlease select a valid menu option:");
		}
		System.out.println("  1. Add a new question");
		System.out.println("  2. Delete a question by no.");
		System.out.println("  3. Edit a question by index");
		System.out.println("  4. Back to dashboard\n");
		return cleanseInput(in.nextLine());
    }
    
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
    
    public String getQuizSelection(Boolean inputValid, ArrayList<String> quizList) {
    	if(inputValid) {
			System.out.println("\nWhich quiz do you want to play [1 to " + quizList.size() + "]");
		}
		else {
			System.out.println("\nPlease select a valid quiz [1 to " + quizList.size() + "]");
		}
    	// Display an option for each of the available quizzes
    	for (int i = 0; i < quizList.size(); ++i) {
    		System.out.println((i+1) + ". " + quizList.get(i));
    	}
		return cleanseInput(in.nextLine());
    }
    
    /* -------------- Admin -------------- */
    public String getAdminID(Boolean inputValid) {
    	if(inputValid) {
			System.out.println("Enter your admin ID (Requirements: 5 digits):");
		}
		else {
			System.out.println("\nInvalid Admin ID.\nEnter a valid admin ID (Requirements: 5 digits):");
		}
		return cleanseInput(in.nextLine());
    }
    
    public String getAdminPassword(Boolean inputValid) {
    	if(inputValid) {
			System.out.println("Enter password (Requirements: minimum of 6 characters):");
		}
		else {
			System.out.println("\nInvalid Admin password.\nEnter a valid password (Requirements: minimum of 6 characters):");
		}
		return cleanseInput(in.nextLine());
    }
    
    public String getAdminName(Boolean inputValid) {
    	if(inputValid) {
			System.out.println("Enter Admin Name (Requirements: no digits):");
		}
		else {
			System.out.println("\nInvalid Admin name.\nEnter valid Admin Name (Requirements: no digits):");
		}
		return cleanseInput(in.nextLine());
    }
    
    public String getNewQuizName() {
    	System.out.println("\nEnter the topic/theme name:");
		return cleanseInput(in.nextLine());
    }
    
    public String getQuizToEdit(ArrayList<String> quizList) {
    		System.out.println("\nWhich quiz do you want to edit [1 to " + quizList.size() + "]");
    	return cleanseInput(in.nextLine());
    }
    
    public String getNewQuizQuestion() {
    	System.out.println("\nEnter question here:");
		return cleanseInput(in.nextLine()) + "?"; // because we strip punctation we need to add it back
    }
    
    public String getNewQuizAnswers() {
    	System.out.println("\nEnter the number of answer options here:");
		return cleanseInput(in.nextLine());
    }
    
    public String getAnswer(int number) {
    	System.out.println("Enter answer no. " + (number) + " here:");
		return cleanseInput(in.nextLine());
    }
    
    public String rightAnswer() {
    	System.out.println("\nIs this the right answer? (y/n):");
		return cleanseInput(in.nextLine());
    }
    
    public String deleteQuestion(Boolean inputValid, int max) {
    	if(inputValid) {
    		System.out.println("\nEnter the position of the question you want to be removed (choose between: 1 - " + max + "): ");
    	}
    	else {
    		System.out.println("\nInvalid Selection.\nEnter a position of the question you want to be removed (choose between: 1 - " + max + "): ");
    	}
		return cleanseInput(in.nextLine());
    }
    
    public String editQuestion() {
    	System.out.println("Enter which question you want to edit:");
		return cleanseInput(in.nextLine());
    }
    
    public String addQuiz(Boolean inputValid) {
    	if(inputValid) {
    		System.out.println("\nPlease select one of the following options:");
    	}
    	else {
    		System.out.println("\nPlease select a valid option:");
    	}
		System.out.println("1. Create a brand new quiz");
		System.out.println("2. Copy an existing quiz");
		return cleanseInput(in.nextLine());
    }
    
    public String getQuizToCopy(Boolean inputValid, ArrayList<String> quizList) {
    	if(inputValid) {
    		System.out.println("\nPlease select one of the following options:");
    	}
    	else {
    		System.out.println("\nPlease select a valid option:");
    	}
    	// Display an option for each of the available quizzes
    	for (int i = 0; i < quizList.size(); ++i) {
    		System.out.println((i+1) + ". " + quizList.get(i));
    	}
		return cleanseInput(in.nextLine());
    }
    
    /* -------------- Cleanse Input -------------- */
    private String cleanseInput(String input) {
		String cleansed = input.trim();
		cleansed = cleansed.replaceAll("\\pP", ""); // replaces all punctuation
		return cleansed;
    }
}