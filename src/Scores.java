import java.util.*;

/**
 * Created by kamontat on 13/4/59.
 *
 * @version 1.5
 */
public class Scores {
	private ArrayList<double[]> data = new ArrayList<>();
	private int length;

	public double average, SD, range;

	public Scores(ArrayList<double[]> scores) {
		for (double[] score : scores) {
			this.data.add(score);
		}
		this.length = scores.size();

		// sort data
		sortData();
		// average
		average();
		// Standard Deviation
		standardDeviation();
		// range
		range();
	}

	/**
	 * sort from most --(to)--> least
	 */
	public void sortData() {
		// bubble sort score
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - (i + 1); j++) {
				// To sort from more to less.
				if (data.get(j)[1] < data.get(j + 1)[1]) {
					// to swap number in the array
					double changeNum = data.get(j)[0];
					double changeScore = data.get(j)[1];

					data.get(j)[0] = data.get(j + 1)[0];
					data.get(j)[1] = data.get(j + 1)[1];

					data.get(j + 1)[0] = changeNum;
					data.get(j + 1)[1] = changeScore;
				}
			}
		}
	}

	/**
	 * sum all number of score together
	 *
	 * @return summary of score
	 */
	private double sumScores() {
		double sum = 0;
		for (int i = 0; i < length; i++) {
			sum += data.get(i)[1];
		}
		return sum;
	}

	/**
	 * sum all number square(^2) of score together
	 *
	 * @return summary of score
	 */
	private double sumSquareScores() {
		double sum = 0;
		for (int i = 0; i < length; i++) {
			sum += Math.pow(data.get(i)[1], 2);
		}
		return sum;
	}

	/**
	 * Average score together and put to <code>average</code>
	 */
	private void average() {
		average = (sumScores() / length);
	}

	/**
	 * Standard Deviation score and put to <code>SD</code>
	 */
	private void standardDeviation() {
		SD = Math.sqrt(((length * sumSquareScores()) - Math.pow(sumScores(), 2)) / (length * (length - 1)));
	}

	/**
	 * range of score and put to <code>range</code>
	 */
	private void range() {
		range = data.get(0)[1] - data.get(length - 1)[1];
	}

	/**
	 * print data in form of table
	 */
	public void printData() {
		System.out.println("------------------------");
		System.out.printf("|%7s|| %12s|\n", "Number", "Scores");
		System.out.println("------------------------");
		for (int i = 0; i < length; i++) {
			System.out.printf("|%7.0f|| %12.2f|\n", data.get(i)[0], data.get(i)[1]);
		}
		System.out.println("------------------------");
		System.out.printf("|%7s|| %12.4f|\n", "Range", range);
		System.out.printf("|%7s|| %12.4f|\n", "Average", average);
		System.out.printf("|%7s|| %12.4f|\n", "S.D.", SD);
		System.out.println("------------------------\n");

	}
}
