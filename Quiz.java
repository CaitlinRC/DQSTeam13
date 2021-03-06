import java.util.ArrayList;

public class Quiz {
	
	private String title;
	private ArrayList<Question> questions;
	
	// Constructors
	public Quiz() { 
		questions = new ArrayList<Question>();
	}
	
	public Quiz(String title, ArrayList<Question> questions) {
		this.title = title;
		this.questions = questions;
	}

	// Getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public int getSize() {
		return this.questions.size();
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}

	public void deleteQuestionByIndex(int position) {
		questions.remove(position);
	}
	
	public void replaceQuestionByIndex(int position, Question question) {
		questions.set(position, question);
	}

	// Used for creating a deep copy of a quiz object. The .clone method only creates a shallow copy	
    public Quiz copy() {
    	Quiz quizCopy = new Quiz();
    	quizCopy.setTitle(getTitle());
        for(int i = 0; i < questions.size(); ++i) {
        	quizCopy.addQuestion(questions.get(i).copy());
        }
        return quizCopy;
    }
    
    public String displayQuestions() {
    	StringBuffer temp = new StringBuffer();
    	for (int i = 0; i < questions.size(); ++i) {
    		temp.append((i+1) + ". " + getQuestions().get(i).getQuestionText()+ "\n");
    	}
		return temp.toString();
    }
}