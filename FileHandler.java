import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
	public FileHandler () {

	}

	private void createFile (String fileName) throws IOException {
	    FileWriter fw = new FileWriter(new File("Files/", fileName + ".txt"));
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.close();
	}

	private void addQuestion (Question inQuestion, BufferedWriter bw) throws IOException {
		bw.append(inQuestion.toFileString());
	}

	public void saveCurrentQuiz (ArrayList<Question> allQuestions, String fileName) throws IOException {
		createFile(fileName);			
		File file = new File("Files/", fileName + ".txt");
		FileWriter fw = new FileWriter(file, true);
	    BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < allQuestions.size(); i++) {
			addQuestion(allQuestions.get(i), bw);
		}
		bw.close();
	}

	public ArrayList<Question> readFromFile (String fileName) throws IOException {
		ArrayList<Question> returnList = new ArrayList<Question>();
		String line;
		File file = new File("Files/", fileName + ".txt");
		FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String questionText = "";
        ArrayList<String> tempAnswerList = new ArrayList<String>();
        int counter = 0;
        int tempCorrectPos = 0;
        boolean isAnswers = false;
        Question tempQuestion;
        while((line = br.readLine()) != null) {
        	if (isAnswers == false) {
        		questionText = line;
        		isAnswers = true;
        		counter = 0;
        	} else if (isAnswers == true) {
        		if (line.contains("*")) {
        			counter += 1;
        			if (line.contains("-")) {
        				tempAnswerList.add(line.substring(0, line.length() - 2));
        				tempCorrectPos = counter;
        			} else {
        				tempAnswerList.add(line.substring(0, line.length() - 1));
        			}
        		} else {
        			isAnswers = false;
        			System.out.println(questionText);
        			System.out.println(tempCorrectPos);
        			tempQuestion = new Question(questionText, tempAnswerList, tempCorrectPos);
        			returnList.add(tempQuestion);
        			questionText = "";
        			tempAnswerList = new ArrayList<String>();
        			tempCorrectPos = 0;
        			tempQuestion = new Question();
        		}
        	}
        	//System.out.println(line);
        }
        System.out.println(questionText);
		System.out.println(tempCorrectPos);
		tempQuestion = new Question(questionText, tempAnswerList, tempCorrectPos);
		returnList.add(tempQuestion);
        br.close(); 
		return returnList;
	}

}