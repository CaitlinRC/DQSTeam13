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
    		// call method for adding new quiz
    		break;
    		// Edit an existing quiz
    	case "2":
    		quiz = admin.loadQuiz(admin.selectExistingQuizFile());
    		setMenu("adminQuiz");
    		// call method for editing quiz
    		break;
    	case "3":
    		admin.addAdmin();
    		// call method for new admin
    		break;
    	case "4":
    		// This function is for calling for a new school to be added
    		AddingSchool addingschool = new AddingSchool();
    		addingschool.Added();
    		break;

    	case "5":
    		// this functions displays all current people inside of the list
    		DisplaySchool displayschool = new DisplaySchool();
    		displayschool.Display();
    		break;
    		// Handle invalid input
    		// Logout
    	case "6":
    		setMenu("main");
    		break;

    	default:
    		setInputValid(false);
    		break;
    	}
    }
    
	// Admin quiz dashboard
	public void adminQuizMenu() {
	    	switch (input.adminQuizMenu(inputValid, quiz.getTitle())) {
			// Add a new question
	    	case "1":
	    		admin.addQuestion(quiz);
	    		break;
	    	// Delete a question	
	    	case "2":
	    		admin.deleteQuestion(quiz);
	    		break;
	    	// Edit question
	    	case "3":
	    		admin.editQuestion(quiz);
	    		break;
	    	// Back to dashboard
	    	case "4":
	    		setMenu("admin");
	    		break;	
	    	// Handle invalid input	
	    	default:
	    		setInputValid(false);
	    		break; 
	    	}
	}
}