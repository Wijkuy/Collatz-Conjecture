package temporary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

	public static File file = new File(System.getProperty("user.home") + File.separatorChar + "My Documents" + File.separatorChar + "Temp");
	public static File files = new File(new File(System.getProperty("user.home") + File.separatorChar + "My Documents" + File.separatorChar + "Tempe"), "conjecture.csv");

	public static boolean createFile(File files) throws IOException {

		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		if (!files.exists()) {
			if (files.createNewFile()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
		return true;
	}

	/**
	 * 
	 * @param s
	 * @throws IOException
	 */
	public static void addEntry(String s) throws IOException {
		if (grabber() != null && grabber().length > 0) {
			for (String a : grabber()) {
				if (a != s.trim()) {
					Files.write(FileManager.files.toPath(), s.getBytes(), StandardOpenOption.APPEND);
				}
			}
		} else {
			Files.write(FileManager.files.toPath(), s.getBytes(), StandardOpenOption.APPEND);
		}
	}

	public static String[] grabber() throws IOException {

		String csvFile = files.toPath().toString();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = " ";
		List<String> temp = new ArrayList<>();

		if (createFile(files)) {
			try {

				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {

					String[] country = line.split(cvsSplitBy);
					for (String s : country) {
						if (!s.trim().equals(""))
							temp.add(s);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return temp.toArray(new String[0]);
	}

	public static void write(int i, int j, File files) throws IOException {
		String s = (i + "," + j + "\n");
		if (createFile(files)) {
			Files.write(files.toPath(), s.toString().getBytes(), StandardOpenOption.APPEND);
		}
	}

	public static void clear(File files) throws IOException {
		if (files != null)
			Files.delete(files.toPath());
	}
}
