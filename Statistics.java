import java.lang.Math;
import java.util.stream.DoubleStream;

// Base class to be inherited from.

public class Statistics {
	protected int numberCorrect;
	protected double timeTaken;

	public Statistics(int numberCorrect, double timeTaken) {
		this.numberCorrect = numberCorrect;
		this.timeTaken = timeTaken;
	}

	public int getNumberCorrect() {
		return numberCorrect;
	}

	public void setNumberCorrect() {
		this.numberCorrect = numberCorrect;
	}

	public double getTimeTaken() {
		return timeTaken;
	}

	private void setTimeTaken (double timeTaken) {
		this.timeTaken = timeTaken;
	}

	public double getAverage(double[] n) {
		double average = DoubleStream.of(n).sum() / n.length;
		return average;
	}

	public int getRange(int n, int m) {
		return m - n;
	}
}

