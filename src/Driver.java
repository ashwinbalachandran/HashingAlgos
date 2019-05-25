import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import quadraticprobing.QuadraticProbing;

public class Driver {

	public static void main(String[] args) throws Exception, UnsupportedEncodingException {
		/*
		 * GenerateNumbers g = new GenerateNumbers(100); g.generate(100000);
		 * g.addToFile("test_100.txt"); g = new GenerateNumbers(500);
		 * g.generate(100000); g.addToFile("test_500.txt"); g = new
		 * GenerateNumbers(1000); g.generate(100000); g.addToFile("test_1000.txt"); g =
		 * new GenerateNumbers(2500); g.generate(100000); g.addToFile("test_2500.txt");
		 * g = new GenerateNumbers(5000); g.generate(100000);
		 * g.addToFile("test_5000.txt");
		 */

		/*
		 * generateWorstLinear25(100); generateWorstLinear50(100);
		 * generateWorstLinear75(100); generateWorstLinear25(500);
		 * generateWorstLinear50(500); generateWorstLinear75(500);
		 * generateWorstLinear25(1000); generateWorstLinear50(1000);
		 * generateWorstLinear75(1000); generateWorstLinear25(2500);
		 * generateWorstLinear50(2500); generateWorstLinear75(2500);
		 * generateWorstLinear25(5000); generateWorstLinear50(5000);
		 * generateWorstLinear75(5000);
		 */

		int n[] = { 100, 500, 100, 2500, 5000 };
		int lf[] = { 25, 50, 75 };
		testLinearWorst(n, lf);
		testLinearRandom(n, lf);

	}

	private static void testLinearWorst(int[] n, int[] lf) throws IOException {
		long start;
		long end;
		for (int j : lf) {
			for (int i : n) {
				String t = "worst_" + String.valueOf(i) + "_" + String.valueOf(j) + ".txt";
				ArrayList<Integer> nums = getNumbersFromFile(t);
				start = System.nanoTime();
				int size = (int) (i / (j / 100.0f));
				// LinearHashing lh = new LinearHashing(size);
				// ChainHashing ch = new ChainHashing(size);
				//CuckooHashing cuh = new CuckooHashing(size);
				QuadraticProbing qp = new QuadraticProbing(size);
				addAllElements(nums, qp);
				end = System.nanoTime();
				// System.out.println("Linear Hashing "+ (end-start)/1000000.0+" milli seconds
				// "+ lh.getCollisions()+" collisions");
				System.out.println((end - start) / 1000000.0 + " " + qp.getCollisions());
			}
		}
	}

	private static void testLinearRandom(int[] n, int[] lf) throws IOException {
		long start;
		long end;
		for (int j : lf) {
			for (int i : n) {
				String t = "test_" + String.valueOf(i) + ".txt";
				ArrayList<Integer> nums = getNumbersFromFile(t);
				int size = (int) (i / (j / 100.0f));
				start = System.nanoTime();
				// LinearHashing lh = new LinearHashing(size);
				// ChainHashing ch = new ChainHashing(size);
				//CuckooHashing cuh = new CuckooHashing(size);
				QuadraticProbing qp = new QuadraticProbing(size);
				addAllElements(nums, qp);
				end = System.nanoTime();
				// System.out.println("Linear Hashing "+ (end-start)/1000000.0+" milli seconds
				// "+ lh.getCollisions()+" collisions");
				System.out.println((end - start) / 1000000.0 + " " + qp.getCollisions());
			}
		}
	}

	private static void addAllElements(ArrayList<Integer> nums, QuadraticProbing qp) {
		for (int i : nums) {
			qp.set(i, i);
		}
	}

	/*
	 * private static void addAllElements(ArrayList<Integer> num, LinearHashing lh)
	 * { for (int i : num) { lh.set(i, i); } }
	 * 
	 * private static void addAllElements(ArrayList<Integer> nums, ChainHashing ch)
	 * { for (int i : nums) { ch.set(i, i); } }
	 * 
	 * private static void addAllElements(ArrayList<Integer> nums, CuckooHashing
	 * cuh) { for (int i : nums) { cuh.set(i, i); } }
	 * 
	 * private static void generateWorstLinear25(int n) throws
	 * FileNotFoundException, UnsupportedEncodingException { int ctr = 0; String
	 * fname = "worst_" + String.valueOf(n) + "_"; int s = (int) (n / (25 /
	 * 100.0f)); PrintWriter writer = new PrintWriter(fname + "25.txt", "UTF-8");
	 * for (int i = 0; ctr < n; i++) { if (i % s == 0) { writer.print(i + " ");
	 * ++ctr; } } writer.close(); }
	 * 
	 * private static void generateWorstLinear50(int n) throws
	 * FileNotFoundException, UnsupportedEncodingException { int ctr = 0; String
	 * fname = "worst_" + String.valueOf(n) + "_"; int s = (int) (n / (50 /
	 * 100.0f)); PrintWriter writer = new PrintWriter(fname + "50.txt", "UTF-8");
	 * for (int i = 0; ctr < n; i++) { if (i % s == 0) { writer.print(i + " ");
	 * ++ctr; } } writer.close(); }
	 * 
	 * private static void generateWorstLinear75(int n) throws
	 * FileNotFoundException, UnsupportedEncodingException { int ctr = 0; String
	 * fname = "worst_" + String.valueOf(n) + "_"; int s = (int) (n / (75 /
	 * 100.0f)); PrintWriter writer = new PrintWriter(fname + "75.txt", "UTF-8");
	 * for (int i = 0; ctr < n; i++) { if (i % s == 0) { writer.print(i + " ");
	 * ++ctr; } } writer.close(); }
	 */

	private static ArrayList<Integer> getNumbersFromFile(String fileName) throws IOException {
		ArrayList<Integer> ret = new ArrayList<>();
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
