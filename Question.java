import java.util.ArrayList;

public class Question {
	private String questionText;
	private ArrayList<Answer> answerList;
	private int correctPos;

	public Question() {
		answerList = new ArrayList<Answer>();
	}

	public Question(String inQuestion, ArrayList<Answer> inAnswerList, int inCorrectPos) {
		questionText = inQuestion;
		answerList = inAnswerList;
		correctPos = inCorrectPos;
	}

	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public int getCorrectPos() {
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
		if (correctPos == input) {
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
		//temp.append("\n");
		return temp.toString();
	}

	public String toFileString() {
		StringBuffer temp = new StringBuffer();
		temp.append(questionText + "\n");
		for (int i = 0; i < answerList.size(); i++) {
			if (i == correctPos - 1) {
				temp.append(answerList.get(i).getTitle() + "*-\n");
			} else {
				temp.append(answerList.get(i).getTitle() + "*\n");
			}
		}
		//temp.append("\n");
		return temp.toString();
	}
}