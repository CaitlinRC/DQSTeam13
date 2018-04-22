import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// no clue why my indents got all whack on this file
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

        public Quiz readFromFile (String fileName) throws IOException {
        	Quiz quiz = new Quiz();
        	quiz.setTitle(fileName);
        	File file = new File("Files/", fileName + ".txt");
            Scanner scanner = new Scanner(file); 
                Question question = new Question();
                while(scanner.hasNextLine()) {
                	String line = scanner.nextLine();
                	if (line.contains("?")){
                		question = new Question();
                		question.setQuestionText(line);
                	}
	                else if(line.contains("-")) {
	                	String title = new String();
	                	title = line.substring(0, line.length() - 2);
	                	question.addAnswer(title, true);
	                }
	                else if (line.contains("*")) {
	                	String title = new String(); 
	                	title = line.substring(0, line.length() - 1);
	                	question.addAnswer(title, false);
	                }
	                else {
	                	quiz.addQuestion(question);
	                }
                }
            quiz.addQuestion(question); // to make sure the last question gets added
            scanner.close();
        	return quiz;
        }

        public void displayQuizList(File directory) {
                String fileName = "";
                for (int i = 0; i < directory.list().length; i++) {
                        fileName = directory.list()[i];
                        System.out.println((i + 1) + ". " + fileName.substring(0, fileName.length() - 4));
                }
        }

        public String getQuizFileName(String prompt) {
                File dir = new File("Files/");
                String fileName = "";
                Scanner userInput = new Scanner(System.in);

                System.out.println(prompt + "[1 to " + dir.list().length + "]: ");
                displayQuizList(dir);
                String userOption = userInput.nextLine();
                fileName = dir.list()[Integer.parseInt(userOption) - 1];
                fileName = fileName.substring(0, fileName.length() - 4);
                return fileName;
        }

}