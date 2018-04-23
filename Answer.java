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
	
	//Other methods
	public Answer copy() {
		Answer answerCopy = new Answer();
		answerCopy.setTitle(getTitle());
		answerCopy.setCorrect(getCorrect());
		return answerCopy;
	}
	
	public String writeFormat() {
		StringBuffer temp = new StringBuffer();
		temp.append(getTitle() + "*");
		if (getCorrect()) {
			temp.append("-");
		}
		return temp.toString();
	}
}