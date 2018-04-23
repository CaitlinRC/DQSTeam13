import java.util.*;
import java.io.*;

public class AdminAccount {

	private String adminID;
	private String password;
	private String adminName;
	private Scanner in = new Scanner(System.in);
	
	public String getAdminID() {
		return adminID;
	}
	
	public void setAdminID(String adminID) {
		if(adminID.matches("[1-9][0-9]{4}")){
			this.adminID = adminID;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password.length()>=6){
			this.password = password;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		if(password.matches("^[a-zA-Z\\s]*$")){
			this.adminName = adminName;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
//main function to call when loggin in
	public boolean adminLogin() {

		System.out.print("\nAdmin ID: ");
		String _adminID = in.nextLine();
		while (!_adminID.matches("[1-9][0-9]{4}")) {
			System.out.print("\nInvalid Admin ID.\n Admin ID: ");
			_adminID = in.nextLine();
		}

		System.out.print("Password: ");
		String _password = in.nextLine();
		while (_password.length()<6) {
			System.out.print("\nInvalid password.\n Password: ");
			_password = in.nextLine();
		}

		if (verifyLogin(_adminID, _password)) {
			System.out.println("\nLogin verified.\nLogged in as " + adminName + ".\n");
			return true;
		} else {
			System.out.println("\nIncorrect Admin ID/Password.\n");
			return false;
		}
	}
//verify login. Authenticates parameters if they match what's written in the admin_database
	private boolean verifyLogin(String _adminID, String _password) {
		try {
			File file = new File("Files/admin/admin_database.txt");

			FileReader file_reader = new FileReader(file);
			BufferedReader buff_reader = new BufferedReader(file_reader);

			String row;
			while ((row = buff_reader.readLine()) != null) {
				String content[] = row.split(",");
				if (content[0].equals(_adminID) && _password.equals(content[1])) {
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
}