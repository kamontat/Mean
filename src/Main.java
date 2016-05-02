import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by kamontat on 12/4/59.
 *
 * @version 0.5.1
 */
public class Main {
	/**
	 * first element is Number of List, <br>
	 * second is Score.
	 */
	static ArrayList<double[]> scores = new ArrayList<>();

	public static void main(String[] args) {
		try {
			if (!createNewFolder("inputFolder")) {
				Boolean answer = true;
				while (answer) {
					File folder = new File("inputFolder/");

					String[] fileList = folder.list();
					fileList = listReadFile(fileList);
					// log
					System.out.println(Arrays.toString(fileList));

					if (fileList.length == 0) {
						createInputFile("readFile");
					} else {
						// if has one file run in normal way
						// add score from file text to File
						File[] files = findReadFile(fileList);

						// if user don't input something
						if (files == null) throw new FileNotFoundException();

						for (File file : files) {
							addScoreBy(file);
							Scores score = new Scores(file, scores);
							score.addDataTo(outputFile());
							resetScores();
						}
					}

					Scanner scanner = new Scanner(System.in);
					System.out.print("\nDo you want in calculate again(Y/N)?  ");
					String input = scanner.nextLine();
					// check answer if user input "no" or "n"
					answer = !(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"));
					if (answer) {
						scores = new ArrayList<>();
					}
				}
			} else {
				createInputFile("readFile");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// log
		System.out.println("\n\nProgram run successful. \nCode by Kamontat SKE 13.");
	}

	public static File[] findReadFile(String[] list) {
		ArrayList<File> files = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		System.out.print("Which File you want to read?? : ");
		String filesName = scanner.nextLine().trim();

		// break if no input
		if (filesName.isEmpty()) return null;

		// split in the whitespace
		String[] nameList = filesName.split("\\s+");

		// add txt in each file name
		for (int i = 0; i < nameList.length; i++) {
			nameList[i] += nameList[i] + ".txt";
		}

		// check name in list
		for (String fileName : nameList) {
			for (String name : list) {
				if (fileName.contains(name)) files.add(new File("inputFolder/" + name));
			}
		}
		return files.toArray(new File[files.size()]);
	}

	public static String[] listReadFile(String[] list) {
		ArrayList<String> newList = new ArrayList<>();
		for (String name : list) {
			if (!name.contains("output")) newList.add(name);
		}

		return newList.toArray(new String[newList.size()]);
	}

	public static void deleteFileIn(String folder, String[] fileNames) {
		for (String fileName : fileNames) {
			File unnecessaryFile = new File(folder + "/" + fileName);
			if (unnecessaryFile.delete()) System.out.println("Program has deleted File name: " + fileName);
			else System.out.println("No " + fileName + "file exist, Any more");
		}
	}

	public static void deleteFileIn(String folder) {
		File file = new File(folder + "/");
		String[] fileNames = file.list();
		for (String fileName : fileNames) {
			File unnecessaryFile = new File(folder + "/" + fileName);
			if (unnecessaryFile.delete()) System.out.println("Program has deleted File name: " + fileName);
			else System.out.println("No " + fileName + "file exist, Any more");
		}
	}

	public static Boolean createNewFolder(String name) {
		File folder = new File(name + "/");
		if (folder.mkdir()) {
			System.out.println(name + " folder has been created");
			return true;
		}
		return false;
	}

	/**
	 * create new file in <b>inputFolder</b>.
	 */
	public static void createInputFile(String name) throws IOException {
		File newFile = new File("inputFolder/" + name + ".txt");
		if (newFile.createNewFile()) {
			writeFile(newFile);
			System.out.println("\nProgram has create new \'File\' called: readFile.txt, ");
			System.out.println("put scores into there And run program again.");
		}
	}

	/**
	 * Max output that this method can create is 9
	 *
	 * @return output file in output folder
	 * @throws IOException
	 */
	public static File outputFile() throws IOException {
		int countFile = 1;
		String outputName = "outputFile";

		createNewFolder("outputFolder");

		File newFile = new File("outputFolder/" + outputName + countFile + ".txt");

		while (!newFile.createNewFile()) {
			newFile = new File("outputFolder/" + outputName + ++countFile + ".txt");

			// limit only 9 output file
			if (countFile == 10) {
				countFile = 1;
				deleteFileIn("outputFolder");
				newFile = new File("outputFolder/" + outputName + countFile + ".txt");
			}
		}
		return newFile;
	}

	/**
	 * write in description in new file. (What kind of text user must put into text file)
	 *
	 * @param file
	 * 		textFile.
	 */
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
			e.printStackTrace();
		}
	}

	public static void addScoreBy(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		int count = 1;
		// put numStudent, score in scores(Array)
		while (scan.hasNext()) {
			// for 2 decimal double number
			scores.add(new double[]{count++, scan.nextDouble()});
		}
	}

	public static void resetScores() {
		scores.removeAll(new ArrayList<Double[]>());
	}
}
