import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by kamontat on 12/4/59.
 *
 * @version 0.1.2
 */
public class WriteTable {
	public static void main(String[] args) {
		File folder = new File("textFile/");
		File read = null;
		String readName = "";
		/*
		 * first element is number of list,
		 * second is scores.
		 */
		ArrayList<double[]> scores = new ArrayList<double[]>();

		try {
			if (folder.isDirectory()) {
				String[] array = folder.list();

				// if has one file run in normal way
				if (array.length == 1) {
					readName = array[0];
					read = new File("textFile/" + readName);
					Scanner scan = new Scanner(read);
					int count = 1;
					// put score in scores(Array)
					while (scan.hasNext()) {
						// for 2 decimal double number
						scores.add(new double[]{count++, scan.nextDouble()});
					}
					System.out.println("Scan will be stop now.");
				} else {
					// delete unused file
					for (int i = 0; i < array.length; i++) {
						String tempName = array[i];
						read = new File("textFile/" + tempName);
						if (read.delete()) System.out.println("Program has deleted File name: " + tempName);
						else System.out.println("No file exist, Any more");
					}
					// create the new one
					read = new File("textFile/readFile.txt");
					if (read.createNewFile()) {
						System.out.println("\nCreate new file called: readFile.txt, ");
						System.out.println("put scores into here And run program again.");
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// bubble sort score
		for (int i = 0; i < scores.size(); i++) {
			for (int j = 0; j < scores.size() - (i + 1); j++) {
				// To sort from more to less.
				if (scores.get(j)[1] < scores.get(j + 1)[1]) {
					// to swap number in the array
					double changeNum = scores.get(j)[0];
					double changeScore = scores.get(j)[1];

					scores.get(j)[0] = scores.get(j + 1)[0];
					scores.get(j)[1] = scores.get(j + 1)[1];

					scores.get(j + 1)[0] = changeNum;
					scores.get(j + 1)[1] = changeScore;
				}
			}
		}

		printArray(scores);
	}

	public static void printArray(ArrayList<double[]> array) {
		System.out.printf("|%6s|| %12s|\n", "Number", "Scores");
		System.out.println("-----------------------");
		for (int i = 0; i < array.size(); i++) {
			System.out.printf("|%6d|| %12.2f|\n", (int) array.get(i)[0], array.get(i)[1]);
		}
	}
}
