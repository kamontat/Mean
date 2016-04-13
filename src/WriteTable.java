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

		Scores score = new Scores(scores);
		score.printData();
	}
}
