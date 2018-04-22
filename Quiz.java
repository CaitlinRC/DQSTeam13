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
	
	public void addQuestion(Question question) {
		questions.add(question);
	}

	// Used for creating a deep copy of a quiz object. The .clone method only creates a shallow copy	
    public Quiz copy() {
    	Quiz quizCopy = new Quiz();
    	quizCopy.setTitle(getTitle());
    	ArrayList<Question> questionsCopy = new ArrayList<Question>();
        for(int i = 0; i < questions.size(); ++i) {
        	questionsCopy.add(questions.get(i).copy());
        }
        return quizCopy;
    }
}