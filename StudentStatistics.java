import java.lang.Math;
import java.util.stream.DoubleStream;
import javax.swing.*;

public class StudentStatistics extends Statistics {
	private double[] timeTakenPerQuestion;

	public StudentStatistics(int numberCorrect, double timeTaken, double[] timeTakenPerQuestion) {
		super(numberCorrect, timeTaken);	
		this.timeTakenPerQuestion = timeTakenPerQuestion;
	}

	public double[] getTimeTakenPerQuestion() {
		return timeTakenPerQuestion;
	}

	public void setTimeTakenPerQuestion(double[] timeTakenPerQuestion) {
		this.timeTakenPerQuestion = timeTakenPerQuestion;
	}

	public double getAverageTimePerQuestion() {
		return getAverage(timeTaken, 10);
	}
} 

