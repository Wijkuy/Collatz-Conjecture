package temporary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Collatz_Conjecture {

	public static File dir = new File(System.getProperty("user.home") + File.separatorChar + "My Documents" + File.separatorChar + "Temp");
	public static File conjecture = new File(dir.getAbsolutePath() + File.separatorChar + "conjecture.csv");

	public static void main(String[] args) throws IOException {
		List<Integer> results = new ArrayList<>();
		long start = System.currentTimeMillis();
		clear(conjecture);
		for (int i = 1; i < 100001; i++) {
			results.add(path(i, 0));
		}
		System.out.println("Writing results to file...");
		write(results, dir, conjecture);
		long end = System.currentTimeMillis();

		System.out.println("Took : " + ((end - start) / 1000) + " seconds");
	}

	public static int path(long a, int counter) {
		return (a == 1) ? counter : (a % 2 == 0) ? path(a / 2, ++counter) : path((3 * a) + 1, ++counter);
	}

	public static boolean createPath(File dir, File conjecture) throws IOException {
		return (!dir.isDirectory()) ? dir.mkdirs() : (!conjecture.exists()) ? conjecture.createNewFile() : false;
	}

	public static void write(List<Integer> results, File dir, File conjecture) throws IOException {
		if (createPath(dir, conjecture)) {
			String s = "";
			for (int i = 1; i < results.size() + 1; i++) {
				s += i + "," + results.get(i - 1) + "\n";
			}
			Files.write(conjecture.toPath(), s.getBytes(), StandardOpenOption.APPEND);
		}
	}

	public static void clear(File files) throws IOException {
		if (files != null && files.exists())
			System.out.println(files);
		files.delete();
	}

}
