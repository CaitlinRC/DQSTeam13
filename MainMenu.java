public class MainMenu {

	public static void main(String[] args) {
		// Prompt for input of schools and year groups attending event
		while (true) {
			mainMenu();
		}
	}

	private void displayMenu() {
		// displays a manually inputted list of menu options
	}

	private String getInputInRange(int min, int max) {

	}

	private int getMenuOption() {
		String userInput = getInputInRange(1, 3);
		int userOption = Integer.parseInt(userInput);
		return userOption;
	}

	public void mainMenu() {
			displayMenu();
			int userOption;
			userOption = getMenuOption();
			if (userOption == 1) {
				new takeQuiz();
			} else if (userOption == 2) {
				// admin log in
			} else if (userOption == 3) {
				System.exit(0); // closes program - idk whether to put this here or in admin menu so students won't close it, or if to include this at all
			}

	}
}