import java.io.File;
import java.util.*;

/**
 * Created by kamontat on 12/4/59.
 */
public class WriteTable {
	public static void main(String[] args) {
		File write = new File("textFile/");
		if (write.isDirectory()) {
			String[] array = write.list();
			if (array.length != 0) {
				for (int i = 0; i < array.length; i++) {
					System.out.println(array[i]);
				}
			} else {
				System.out.println("Don't exist");
			}
		}
	}
}
