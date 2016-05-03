import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by kamontat on 3/5/59.
 */
public class testDouble {
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
			double num = console.nextDouble();
			System.out.print("from (number): ");
			double from = console.nextDouble();
			System.out.print("to (number): ");
			double to = console.nextDouble();

			for (int i = 0; i < num; i++) {
				output += String.format("%.2f \n", randomIn(from, to));
			}
			write.write(output);

			write.close();
			console.close();
		}
	}

	// some random method
	public static double randomIn(double first, double second) {
		double rand = Math.random() * second;
		while (rand < first) {
			rand = Math.random() * second;
		}
		return rand;
	}
}
