import java.util.ArrayList;

public class Question {
	private String questionText;
	private ArrayList<Answer> answerList;

	public Question() {
		answerList = new ArrayList<Answer>();
	}

	public ArrayList<Answer> getAnswerList() {
		return answerList;
	}
	
	public Question(String inQuestion, ArrayList<Answer> inAnswerList) {
		questionText = inQuestion;
		answerList = inAnswerList;
	}

	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public void addAnswer(Answer answer) {
		answerList.add(answer);
	}

	public int getCorrectPos() {
		int correctPos = 0;
		for (int i = 0; i < answerList.size(); i++) {
			if(answerList.get(i).getCorrect()) {
				correctPos = i+1;
			}
		}
		return correctPos;
	}

	public int getNumberOfAnswers() {
		return answerList.size();
	}

	public String getCorrectAnswer() {
		String correctAnswer = "";
		for (int i = 0; i < answerList.size(); i++) {
			if(answerList.get(i).getCorrect()) {
				correctAnswer = answerList.get(i).getTitle();
			}
		}
		return correctAnswer;
	}

	public void addAnswer(String title, Boolean correct) {
		answerList.add(new Answer(title, correct));
	}
	
	public boolean isCorrect(int input) {
		if (getCorrectPos() == input) {
			return true;
		}
		return false;
	}


	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append(questionText + "\n");
		for (int i = 0; i < answerList.size(); i++) {
			temp.append((i + 1) + ". " + answerList.get(i).getTitle() + "\n");
		}
		return temp.toString();
	}

	public String toFileString() {
		StringBuffer temp = new StringBuffer();
		temp.append(questionText + "\r\n");
		for (int i = 0; i < answerList.size(); i++) {
			temp.append(answerList.get(i).writeFormat() + "\n");
		} 
		return temp.toString();
	}
	
    public Question copy() {
    	Question questionCopy = new Question();
    	questionCopy.setQuestionText(getQuestionText());
        for(int i = 0; i < answerList.size(); ++i) {
        	questionCopy.addAnswer(answerList.get(i).copy());
        }
        return questionCopy;
    }
}