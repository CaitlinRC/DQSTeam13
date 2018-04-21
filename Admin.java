import java.util.*;
import java.io.*;

public class Admin {

	private static int adminID;
	private static String password;

	public static boolean detectAdminFile() { //checks if there's no admin file
												//otherwise it makes a new file with a default admin in it.
		try {
			File directory = new File("Files/admin/");
			directory.mkdir();
			File file = new File("Files/admin/admin_database.txt");


			boolean isFileNew = file.createNewFile();

			return isFileNew;
		} catch (IOException e) {
			System.out.println("Unable to create admin database file this time.");
			return false;
		}
	}
//main function to call when loggin in
	public static boolean adminLogin() {
		Scanner in = new Scanner(System.in);

		System.out.print("Admin ID: ");
		int id_input = in.nextInt();

		System.out.print("Password: ");
		String password_input = in.next();
		

		if (verifyLogin(id_input, password_input)) {
			System.out.println("\nLogin verified.\n");
			return true;
		} else {
			System.out.println("\nIncorrect Admin ID/Password.\n");
			return false;
		}
	}

	private static boolean verifyLogin(int _adminID, String _password) {
		adminID = _adminID;
		password = _password;

		try {
			File file = new File("Files/admin/admin_database.txt");

			FileReader file_reader = new FileReader(file);
			BufferedReader buff_reader = new BufferedReader(file_reader);

			String row;
			while ((row = buff_reader.readLine()) != null) {
				String content[] = row.split(",");

				if ((Integer.parseInt(content[0]))==adminID && password.equals(content[1])) {
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
}