public class Menus {
	private Input input = new Input();
	private Boolean inputValid;
	private String menu;
	
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
				if (!Admin.detectAdminFile()) {
					if (Admin.adminLogin()) {
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
	    		Admin.addNewQuiz();
	    		// call method for adding new quiz
	    		break;
	    	// Edit an existing quiz
	    	case "2":
	    		Admin.selectExistingQuizFile();
	    		// call method for editing quiz
	    		break;
	    	case "3":
	    		Admin.addAdmin();
	    		// call method for new admin
	    		break;	    		
	    	case "4":
	    	//This function is for calling for a new school to be added
	    		AddingSchool addingschool = new AddingSchool();
	    		addingschool.Added();
	    		break;

	    	case "5":
	    	//this functions displays all current people inside of the list
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
}