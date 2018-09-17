package flightProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchMap {
	static FlightMap map = new FlightMap();
	static char source;

	/**
	 * Main method--all execution done here
	 * 
	 * @param args
	 *            Arguments with filenames
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Not enough filenames provided");
			return;
		}
		String input = args[0];
		String output = args[1];

		readInputFile(input);
		List<List<String>> info = map.getAllPathInfo(source);
		writeFileOutput(output, info);

	}

	/**
	 * Writes information to an output file
	 * 
	 * @param the
	 *            output file name and the information about all paths from source
	 */
	private static void writeFileOutput(String output, List<List<String>> info) {
		if (info == null) {
			System.err.println("Invalid city provided.");
			return;
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(output, "UTF-8");
			for (List<String> line : info) {
				writer.printf("%-15s %-20s %-10s %n", line.get(0), line.get(1), line.get(2));
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Reads info from an input file and sets it in the map
	 * 
	 * @param the
	 *            input file name
	 */
	private static void readInputFile(String input) {
		File file = new File(input);
		BufferedReader br;
		String st;
		boolean sourceSet = false;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
				if (!sourceSet) {
					source = st.trim().charAt(0);
					sourceSet = true;
				} else {
					parseAndCreateEdge(st);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception occured. Please try again.");
		}
	}

	/**
	 * Helper function that parses each string of a file and creates edges for nodes
	 * in the graph
	 * 
	 * @param input
	 *            String as input
	 */
	public static void parseAndCreateEdge(String input) {
		if (input == null || input.length() == 0) {
			return;
		}
		String[] info = input.trim().split("\\s+");
		if (info.length != 3) {
			return;
		}
		char source = info[0].trim().charAt(0);
		char dest = info[1].trim().charAt(0);
		int cost = Integer.parseInt(info[2].trim());
		map.addEdge(source, dest, cost);
	}

}
