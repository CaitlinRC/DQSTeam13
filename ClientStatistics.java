import java.lang.Math;
import java.util.stream.DoubleStream;
import javax.swing.*;

public class ClientStatistics extends Statistics {
	private int timesQuizTaken;
	private int minNumberCorrect;
	private int maxNumberCorrect;
	private double averageNumberCorrect;
	private double averageTimeTaken;

	public ClientStatistics(int numberCorrect, double timeTaken, int timesQuizTaken, int minNumberCorrect, int maxNumberCorrect) {
		super(numberCorrect, timeTaken);
		this.timesQuizTaken = timesQuizTaken;
		this.minNumberCorrect = minNumberCorrect;
		this.maxNumberCorrect = maxNumberCorrect;
	}

	public int timesQuizTaken() {
		return timesQuizTaken;
	}

	public double getAverageNumberCorrect() {
		return getAverage((double) numberCorrect, timesQuizTaken);
	}

	public double getAverageTimeTaken() {
		return getAverage(timeTaken, timesQuizTaken);	
	}

	public int getRangeNumberCorrect() {
		return getRange(minNumberCorrect, maxNumberCorrect);
	}
}

