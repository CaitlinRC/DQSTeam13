import java.util.ArrayList;

public class Question {
	private String questionText;
	private ArrayList<String> answerList;
	private int correctPos;

	public Question() {

	}

	public Question(String inQuestion, ArrayList<String> inAnswerList, int inCorrectPos) {
		questionText = inQuestion;
		answerList = inAnswerList;
		correctPos = inCorrectPos;
	}

	public String getQuestionText() {
		return questionText;
	}

	public int getCorrectPos() {
		return correctPos;
	}

	public int getNumberOfAnswers() {
		return answerList.size();
	}

	public String getCorrectAnswer() {
		int tempPos = getCorrectPos();
		return answerList.get(tempPos - 1);
	}

	public boolean isCorrect(String userInput) {
		userInput = userInput.trim();
		userInput = userInput.replaceAll("\\pP", ""); // replaces all punctuation
		int userInputValue = Integer.parseInt(userInput);
		if (correctPos == userInputValue) {
			return true;
		}
		return false;
	}


	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append(questionText + "\n");
		for (int i = 0; i < answerList.size(); i++) {
			temp.append((i + 1) + ". " + answerList.get(i) + "\n");
		}
		//temp.append("\n");
		return temp.toString();
	}

	public String toFileString() {
		StringBuffer temp = new StringBuffer();
		temp.append(questionText + "\n");
		for (int i = 0; i < answerList.size(); i++) {
			if (i == correctPos - 1) {
				temp.append(answerList.get(i) + "*-\n");
			} else {
				temp.append(answerList.get(i) + "*\n");
			}
		}
		//temp.append("\n");
		return temp.toString();
	}
}