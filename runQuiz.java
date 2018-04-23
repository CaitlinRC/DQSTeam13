public class runQuiz {

  public static void main(String args[]) {
	 Menus menus = new Menus();
  	// Main program loop
  	while(true) {
  		switch (menus.getMenu()) {
		    	case "main":
		    		menus.mainMenu();
			    	break;
		    	case "admin":
		    		menus.adminMenu();
		    		break;
		    	case "adminQuiz":
		    		menus.adminQuizMenu();
		    		break;
  		}
  	}
  }
}
