import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// no clue why my indents got all whack on this file
public class FileHandler {
        public FileHandler () {

        }

        public void createFile (String fileName) throws IOException {
            FileWriter fw = new FileWriter(new File("Files/quizzes", fileName + ".txt"));
            BufferedWriter bw = new BufferedWriter(fw);
               bw.close();
        }

        private void addQuestion (Question inQuestion, BufferedWriter bw) throws IOException {
               bw.append(inQuestion.toFileString());
        }
        
        public void saveQuiz (Quiz quiz) throws IOException {           
               File file = new File("Files/quizzes", quiz.getTitle() + ".txt");
               FileWriter fw = new FileWriter(file);
               BufferedWriter bw = new BufferedWriter(fw);
               for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    if (i!=quiz.getQuestions().size()-1) {
                        addQuestion(quiz.getQuestions().get(i), bw);
                        bw.append("\r\n");
                    } else {
                        addQuestion(quiz.getQuestions().get(i), bw);
                    }
               }
               bw.close();
        }

        public Quiz readFromFile (String fileName) throws IOException {
            Quiz quiz = new Quiz();
            quiz.setTitle(fileName);
            File file = new File("Files/quizzes", fileName + ".txt");
            Scanner scanner = new Scanner(file); 
            Question question = new Question();
            if (file.length() == 0) { // Check if the file is empty
                // Do nothing
                // This is to allow existing empty (blank) files to be loaded
            }
            else {
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
            }
            quiz.addQuestion(question); // to make sure the last question gets added
            scanner.close();
            return quiz;
        }

        public ArrayList<String> getQuizList(String directory) {
            File dir = new File(directory);
            ArrayList<String> quizList = new ArrayList<String>();
            String fileName = "";
            for (int i = 0; i < dir.list().length; i++) {
                fileName = dir.list()[i];
                quizList.add(fileName.substring(0, fileName.length() - 4));
            }
            return quizList;
        }
        
    	public boolean detectAdminFile() { //checks if there's admin file
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
}