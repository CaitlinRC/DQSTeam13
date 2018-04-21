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
		return in.nextLine();
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
		return in.nextLine();
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
		return in.nextLine();
    }
    */
}