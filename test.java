import java.util.ArrayList;
import java.io.IOException;

public class test {
	public static void main (String[] args) {
		FileHandler f = new FileHandler();
		Question x = new Question();
		Question y = new Question();
		ArrayList<Question> a = new ArrayList<Question>();
		try {
			a = f.readFromFile("quiz");
			f.saveCurrentQuiz(a, "quiz2");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(a.get(2));
		System.out.println("The correct answer here is " + a.get(2).getCorrectAnswer());
	}
}