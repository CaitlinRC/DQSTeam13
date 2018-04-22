import java.util.*;
import java.io.*;

public class Admin {

	private static int adminID;
	private static String password;
	public static String adminName;
	private static String file_path = "Files/admin/admin_database.txt";
	private static Scanner in = new Scanner(System.in);

	public static boolean detectAdminFile() { //checks if there's admin file
		try {
			File directory = new File("Files/admin/");
			directory.mkdir();
			File file = new File(file_path);


			boolean isFileNew = file.createNewFile();

			return isFileNew;
		} catch (IOException e) {
			System.out.println("Unable to create admin database file this time.");
			return false;
		}
	}
//main function to call when loggin in
	public static boolean adminLogin() {
		

		System.out.print("\n Admin ID: ");
		String _adminID = in.nextLine();
		while (!_adminID.matches("[1-9][0-9]{4}")) {
			System.out.print("\nInvalid Admin ID.\n Admin ID: ");
			_adminID = in.nextLine();
		}

		System.out.print(" Password: ");
		String _password = in.nextLine();
		while (_password.length()<6) {
			System.out.println("\nInvalid password.\n Password: ");
			_password = in.nextLine();
		}

		if (verifyLogin(Integer.parseInt(_adminID), _password)) {
			System.out.println("\nLogin verified.\nLogged in as " + adminName + ".\n");
			return true;
		} else {
			System.out.println("\nIncorrect Admin ID/Password.\n");
			return false;
		}
	}
//verify login. Authenticates parameters if they match what's written in the admin_database
	private static boolean verifyLogin(int _adminID, String _password) {
		adminID = _adminID;
		password = _password;

		try {
			File file = new File(file_path);

			FileReader file_reader = new FileReader(file);
			BufferedReader buff_reader = new BufferedReader(file_reader);

			String row;
			while ((row = buff_reader.readLine()) != null) {
				String content[] = row.split(",");

				if ((Integer.parseInt(content[0]))==adminID && password.equals(content[1])) {
					adminName = content[2];
					return true;
				}
			}
			buff_reader.close();
			return false;
		} catch (IOException e) {
			System.out.println("Unable to read admin database file this time.");
			return false;				
		}
	}

	public static void addAdmin(){
		System.out.println("Enter a valid admin ID (Requirements: 5 digits): ");
		String _adminID = in.nextLine();
		while (!_adminID.matches("[1-9][0-9]{4}")) {
			System.out.print("\nInvalid Admin ID.\nEnter a valid admin ID (Requirements: 5 digits): ");
			_adminID = in.nextLine();
		}

		System.out.print("Enter password (Requirements: minimum of 6 characters): ");
		String _password = in.nextLine();
		while (_password.length()<6) {
			System.out.println("\nInvalid password.\nEnter password (Requirements: minimum of 6 characters): ");
			_password = in.nextLine();
		}

		System.out.print("Enter Admin Name (Requirements: no digits): ");
		String _adminName = in.nextLine();
		while (!_adminName.matches("[A-Za-z ]+")) {
			System.out.print("\nInvalid Admin ID.\nEnter Admin Name (Requirements: no digits): ");
			_adminName = in.nextLine();
		}

		try {
			FileWriter file_write = new FileWriter(file_path, true);

			String new_admin = _adminID + "," + _password + "," + _adminName;
			String s = System.getProperty("line.separator");

			file_write.write(s + new_admin);
			file_write.close();
			System.out.println("\nSuccessfully added " + _adminName + " into the database.\n");
		} catch (IOException e) {
			System.out.println("\nUnable to add " + _adminName + " into the database.\n");
		}
	}
	private static void selectOperation(String file_name) {
		boolean _isDone = false;
		while (_isDone==false) {
			System.out.println("\nYou may do the following with " + file_name + ": ");
			System.out.println("  1. Add a new question");
			System.out.println("  2. Delete a question by no.");
			System.out.println("  3. Edit a question by index");
			System.out.println("  4. Back to dashboard\n");
			System.out.print("Enter Operation: ");

			switch (in.nextInt()) {
				case 1:
					AdminFileHandler.addQuestion("Files/quizzes/" + file_name + ".txt");
					break;

				case 2:
					//to do
					break;

				case 4:
					_isDone = true;
					break;
			}
		}
	}

	public static void addNewQuiz() {
		FileHandler file = new FileHandler();
		System.out.print("\nEnter the topic/theme name: ");
		String _input = in.nextLine();
		try {
			file.createFile(_input);
		} catch (IOException e) {
			System.out.println("Unable to create new file.");
		}

		selectOperation(_input);

	}

	public static void selectExistingQuizFile() {
		FileHandler file = new FileHandler(); 
		ArrayList<String> quizList = file.getQuizList("Files/quizzes/"); //reused a code from FileHandler.java to obtain all exisitng files
		System.out.println("\nExisting quiz file(s) you may edit:");
		for (int i=0;i<=quizList.size()-1;i++) {
			System.out.println("  " + (i+1) + ". " + quizList.get(i));
		}

		System.out.print("\nSelection (choose between: 1 - " + quizList.size() + "): ");
		while (!in.hasNextInt()) { //|| in.nextInt()>quizList.size() || in.nextInt()<1) {
			System.out.print("\nInvalid Selection.\nSelection (choose between: 1 - " + quizList.size() + "): ");
			in.next();
		}		
		int _input = in.nextInt();

		selectOperation(quizList.get(_input - 1));
	}
}