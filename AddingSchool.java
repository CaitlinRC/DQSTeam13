import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

public class AddingSchool {
	public void Added() {
		Scanner input = new Scanner(System.in);
		School school = new School();

		System.out.println("Enter the name of the school you wish to enter: ");
		String name = input.nextLine();
		while(!name.matches("[a-zA-Z ]+")){
			System.out.println("Enter a valid name!");
			name = input.nextLine();
		}
		school.setName(name);

		System.out.println("Enter the school year of the students: ");
		String year = input.nextLine();
		while(!year.matches("[0-9]+")){
			System.out.println("Enter a valid year!");
			year = input.nextLine();
		}
		school.setYear(year);

		try{
			FileWriter writer = new FileWriter("Files/Schools.txt", true);
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter out = new PrintWriter(writer);

			bw.write(name + "," + "Year:" + year);
			bw.newLine();
			bw.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}