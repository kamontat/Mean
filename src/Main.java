import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by kamontat on 12/4/59.
 *
 * @version 0.2.4
 */
public class Main {
	/**
	 * first element is Number of List, <br>
	 * second is Score.
	 */
	static ArrayList<double[]> scores = new ArrayList<>();

	public static void main(String[] args) {
		File folder = new File("textFile/");
		try {
			if (!folder.mkdir()) {
				String[] fileList = folder.list();

				// if has one file run in normal way
				if (checkNumFile(fileList)) {
					File read = new File("textFile/" + fileList[0]);
					// add score from file text to File
					addTo(read);
					// log
					System.out.println("Scan will be stop now.");

					Scores score = new Scores(scores);
					score.printData();
				} else {
					// delete unused file
					deleteFile(fileList);
					// create the new one
					createNewFile();
				}
			} else {
				System.out.println("Folder \"textFile/\" not found.");

				System.out.println("Program has create new \'Folder\' called: textFile");
				createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addTo(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		int count = 1;
		// put numStudent, score in scores(Array)
		while (scan.hasNext()) {
			// for 2 decimal double number
			scores.add(new double[]{count++, scan.nextDouble()});
		}
	}

	public static Boolean checkNumFile(String[] list) {
		return list.length == 1;
	}

	public static void deleteFile(String[] fileNames) {
		for (String fileName : fileNames) {
			File unnecessaryFile = new File("textFile/" + fileName);
			if (unnecessaryFile.delete()) System.out.println("Program has deleted File name: " + fileName);
			else System.out.println("No file exist, Any more");
		}
	}

	public static void createNewFile() throws IOException {
		File newFile = new File("textFile/readFile.txt");
		if (newFile.createNewFile()) {
			System.out.println("\nProgram has create new \'File\' called: readFile.txt, ");
			System.out.println("put scores into there And run program again.");
		}
	}
}
