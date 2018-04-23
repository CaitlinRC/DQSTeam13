import java.util.ArrayList;

public class Menus {
	private Input input = new Input();
	private Boolean inputValid;
	private String menu;
	private Quiz quiz = new Quiz();
	private Admin admin = new Admin();
	private AdminAccount adminAccount = new AdminAccount();
	private FileHandler file = new FileHandler();
	
    public Menus() {
        setMenu("main"); // Set the main menu as the entry point
        setInputValid(true);
    }
	
	// Getters and setters
    private void setInputValid(Boolean inputValid) {
        this.inputValid = inputValid;
    }
    
    public String getMenu() {
        return menu;
    }

    private void setMenu(String menu) {
        this.menu = menu;
    }
	
	// Main menu
    public void mainMenu() {
    	switch (input.mainMenu(inputValid)) {
    		// Take quiz
	    	case "1":
	    		new takeQuiz();
	    		setInputValid(true);
	    		break;
	    	// Admin login	
	    	case "2":
				if (!file.detectAdminFile()) {
					if (adminAccount.adminLogin()) {
		    			setMenu("admin");
		    			break;
		    		} else {
		    			break;
		    		}
				} else { 
				  		System.out.println("\nNo data to read."); //means text file is empty
				  		break;
				}
	    	// Exit
	    	case "3":
	    		System.exit(0);
	    		break;
	    	// Handle invalid input	
	    	default:
	    		setInputValid(false);
	    		break; 
    	}
    }
    
    // Admin menu
    public void adminMenu() {
    	switch (input.adminMenu(inputValid)) {
    	// Add a new quiz
    	case "1":
    		quiz = new Quiz();
    		do {
    			switch (input.addQuiz(inputValid)) {
    			// Brand new quiz
    			case "1":
    				quiz = new Quiz();
    				quiz.setTitle(admin.addNewQuiz());
    				setMenu("adminQuiz");
    				setInputValid(true);
    				break;
    				// Copy quiz
    			case "2":
    				quiz = admin.addNewQuizCopy();
    				setMenu("adminQuiz");
    				setInputValid(true);
    				break;
    			default:
    				setInputValid(false);
    				break;
    			}
    		} while (!inputValid);
    		setInputValid(true);
    		break;
    	// Edit an existing quiz
    	case "2":
    		quiz = admin.loadQuiz(admin.selectExistingQuizFile());
    		setMenu("adminQuiz");
    		setInputValid(true);
    		break;
    	// Add new admin account
    	case "3":
    		admin.addAdmin();
    		setInputValid(true);
    		break;
    	// New schoool
    	case "4":
    		admin.addSchool();
    		setInputValid(true);
    		break;
    	// Display schools
    	case "5":
    		file.readSchoolsFile();
    		setInputValid(true);
    		break;
    	// Logout
    	case "6":
    		setMenu("main");
    		setInputValid(true);
    		break;
    	// Handle invalid input
    	default:
    		setInputValid(false);
    		break;
    	}
    }
    
	// Admin quiz dashboard
	public void adminQuizMenu() {
	    	switch (input.adminQuizMenu(inputValid, quiz)) {
			// Add a new question
	    	case "1":
	    		admin.addQuestion(quiz);
	    		setInputValid(true);
	    		break;
	    	// Delete a question	
	    	case "2":
	    		admin.deleteQuestion(quiz);
	    		setInputValid(true);
	    		break;
	    	// Edit question
	    	case "3":
	    		admin.editQuestion(quiz);
	    		setInputValid(true);
	    		break;
	    	// Back to dashboard
	    	case "4":
	    		setMenu("admin");
	    		setInputValid(true);
	    		break;	
	    	// Handle invalid input	
	    	default:
	    		setInputValid(false);
	    		break; 
	    	}
	}
}