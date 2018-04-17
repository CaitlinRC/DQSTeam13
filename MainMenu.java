public class MainMenu {

	public static void main(String[] args) {
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
		String userInput = getInputInRange(1, 9 /* 9 is just a temporary value */);
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
				// edit quiz - prompts user to pick a file they want to edit questions for, they can edit the quiz, save it etc.
			} /* and so on */

	}
}