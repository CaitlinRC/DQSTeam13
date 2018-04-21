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
	    		break;
	    	// Admin login	
	    	case "2":
	    		// call admin login method when it's written
	    		// if successful, then should call setMenu("admin")
	    		break;
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
    	switch (input.mainMenu(inputValid)) {
    		// Add a new quiz
	    	case "1":
	    		// call method for adding new quiz
	    		break;
	    	// Edit an existing quiz
	    	case "2":
	    		// call method for editing quiz
	    		break;
	    	// Logout
	    	case "3":
	    		setMenu("main");
	    		break;
	    	// Handle invalid input	
	    	default:
	    		setInputValid(false);
	    		break; 
    	}
    }
}