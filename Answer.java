public class Answer {
	private String title;
	private Boolean correct;
	
	// Constructors
	public Answer() { 
    	
	}
	
	public Answer(String title, Boolean correct) {
		this.title = title;
		this.correct = correct;
	}

	// Getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
}