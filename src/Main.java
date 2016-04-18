import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by kamontat on 12/4/59.
 *
 * @version 0.3.2
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
					// add score from file text to File
					addScoreTo(findReadFile(fileList));
					// log
					System.out.println("Scan will be stop now.");

					Scores score = new Scores(scores);
					score.addDataTo(createOutputFile());
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
		// log
		System.out.println("\n\nProgram run successful. \nCode by Kamontat SKE 13.");
	}

	public static File findReadFile(String[] list) {
		for (String name : list) {
			if (name.indexOf("output") == -1) {
				return new File("textFile/" + name);
			}
		}
		System.err.println("This print shouldn't show up");
		return null;
	}

	public static void addScoreTo(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		int count = 1;
		// put numStudent, score in scores(Array)
		while (scan.hasNext()) {
			// for 2 decimal double number
			scores.add(new double[]{count++, scan.nextDouble()});
		}
	}

	public static Boolean checkNumFile(String[] list) {
		int length = list.length;
		for (String name : list) {
			if (name.indexOf("output") != -1) length--;
		}

		return length == 1;
	}

	public static void deleteFile(String[] fileNames) {
		for (String fileName : fileNames) {
			File unnecessaryFile = new File("textFile/" + fileName);
			if (unnecessaryFile.delete()) System.out.println("Program has deleted File name: " + fileName);
			else System.out.println("No " + fileName + "file exist, Any more");
		}
	}

	public static void createNewFile() throws IOException {
		File newFile = new File("textFile/readFile.txt");
		if (newFile.createNewFile()) {
			writeFile(newFile);
			System.out.println("\nProgram has create new \'File\' called: readFile.txt, ");
			System.out.println("put scores into there And run program again.");
		}
	}

	public static File createOutputFile() throws IOException {
		int countFile = 1;
		String outputName = "outputFile";
		File newFile = new File("textFile/" + outputName + ".txt");
		while (!newFile.createNewFile()) {
			newFile = new File("textFile/" + outputName + countFile++ + ".txt");

			// limit only 4 output file
			if (countFile == 5) {
				deleteFile(new String[]{outputName + ".txt", outputName + "1.txt", outputName + "2.txt", outputName + "3.txt"});
				newFile = new File("textFile/" + outputName + ".txt");
				countFile = 1;
			}
		}
		return newFile;
	}

	public static void writeFile(File file) {
		try {
			FileWriter write = new FileWriter(file);
			String message1 = "Delete this message after you read it clearly.";
			String message2 = " - you can add the list of score by using new line or spacebar in each of score";
			String message3 = " - if program run finish that I expect it will output the table of score, mean and S.D. ";
			String message4 = "----------------------------------------------------------------------------------------";
			String tempOutput = String.format("%s\n%s\n%s\n%s\n", message1, message2, message3, message4);
			write.write(tempOutput);
			write.close();
		} catch (IOException e) {
			System.out.println("File No found by some reason.");
			System.out.println(e);
		}
	}
}
