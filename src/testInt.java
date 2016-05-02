import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by kamontat on 3/5/59.
 */
public class testInt {
	public static void main(String[] args) throws IOException {
		File my = new File("inputFolder/");
		if (!my.mkdir() || my.mkdir()) {
			int countFile = 1;
			String outputName = "someFile";
			File newFile = new File("inputFolder/" + outputName + countFile + ".txt");
			while (!newFile.createNewFile()) {
				newFile = new File("inputFolder/" + outputName + ++countFile + ".txt");
			}

			FileWriter write = new FileWriter(newFile);
			Scanner console = new Scanner(System.in);
			String output = "";

			System.out.print("random (number): ");
			int num = console.nextInt();
			System.out.print("from (number): ");
			int from = console.nextInt();
			System.out.print("to (number): ");
			int to = console.nextInt();

			for (int i = 0; i < num; i++) {
				output += randomIn(from, to) + "\n";
			}
			write.write(output);

			write.close();
			console.close();
		}
	}

	// some random method
	public static int randomIn(int first, int second) {
		int rand = (int) Math.ceil(Math.random() * second);
		while (rand < first) {
			rand = (int) Math.ceil(Math.random() * second);
		}
		return rand;
	}
}
