import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateNumbers {
	int size;
	List<Integer> list;

	public GenerateNumbers(int size) {
		super();
		this.size = size;
		list = new ArrayList<>();
	}

	public GenerateNumbers() {
		super();
	}

	void generate(int n) {
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		list = list.subList(0, size);
	}

	void addToFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		for (Integer i : list) {
			writer.print(i + " ");
		}
		writer.close();
	}

	List<Integer> getNumbersFromFile(String fileName) throws IOException {
		List<Integer> ret = new ArrayList<>();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String st;
		while ((st = br.readLine()) != null) {
			String s[] = st.split(" ");
			for (int i = 0; i < s.length; i++) {
				ret.add(Integer.parseInt(s[i]));
			}
		}
		return ret;
	}
}