import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DisplaySchool {

	public void Display() {
	try {
		File file = new File("Schools.txt");
		FileReader filereader = new FileReader("Schools.txt");
		BufferedReader buff = new BufferedReader(filereader);
		StringBuffer stringbuff = new StringBuffer();
		String line;

		while((line = buff.readLine()) != null) {
			stringbuff.append(line);
			stringbuff.append("\n");
		}
		System.out.println(stringbuff.toString());
		
		}catch (IOException e) {
			e.printStackTrace();
	}
}
}