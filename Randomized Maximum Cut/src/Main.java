import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		String filename = "C:/Users/Shintai/Desktop/Skola/edan55/Randomized Maximum Cut/matching_1000.txt";
		//String filename = "C:/Users/Shintai/Desktop/Skola/edan55/Randomized Maximum Cut/pw09_100.9.txt";
		//String filename = "C:/Users/Myky/Documents/EDAN55/Randomized Maximum Cut/matching_1000.txt";
		//String filename = "C:/Users/Myky/Documents/EDAN55/Randomized Maximum Cut/pw09_100.9.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String Line = br.readLine().trim();
			String[] splitLine = Line.split(" ");
			// int n = Integer.parseInt(splitLine[0]);
			int m = Integer.parseInt(splitLine[1]);
			int seg1, seg2, seg3;
			String[] seg;
			for (int i = 0; i < m; i++) {
				Line = br.readLine().trim();
				seg = Line.split(" ");
				seg1 = Integer.parseInt(seg[0]);
				seg2 = Integer.parseInt(seg[1]);
				seg3 = Integer.parseInt(seg[2]);

				if (!nodeMap.containsKey(seg1)) {
					nodeMap.put(seg1, new Node(seg1));
				}

				if (!nodeMap.containsKey(seg2)) {
					nodeMap.put(seg2, new Node(seg2));
				}

				Edge e = new Edge(nodeMap.get(seg1), nodeMap.get(seg2), seg3);
				nodeMap.get(seg1).add(e);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//int maxCut;
		int t = 100;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < t; i++) {
			Randomizer rnd = new Randomizer(nodeMap);
			//int maxCut = rnd.RandomR();
			int maxCut = rnd.RandomS();
			//int maxCut = rnd.RandomRS();
			list.add(maxCut);
			//System.out.println(maxCut);
		}
		int max = Collections.max(list);
		int sum = 0;
		for (int mc : list) {
			sum = sum + mc;
		}
		float avg = sum/list.size();
		System.out.println("The maximum cut of this method is " + max + " and the average cut is " + avg);

	}
}
