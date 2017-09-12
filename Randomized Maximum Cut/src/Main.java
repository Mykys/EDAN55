import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Edge> edges = new ArrayList<>();
		ArrayList<Integer> nodeNbr = new ArrayList<>();
		String filename = "C:/Users/Myky/Documents/EDAN55/Randomized Maximum Cut/matching_1000.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		String Line;
		int seg1, seg2, seg3;
		String[] seg;
		for (int i = 0; i < m; i++) {
			Line = br.readLine().trim();
			seg = Line.split(" ");
			seg1 = Integer.parseInt(seg[0]);
			seg2 = Integer.parseInt(seg[1]);
			seg3 = Integer.parseInt(seg[2]);
			Node from, to;
			if (nodeNbr.contains(seg1)) {
				nodeNbr.add(seg1);
				from = new Node(seg1);
			}
			
			if (nodeNbr.contains(seg2)) {
				nodeNbr.add(seg2);
				to = new Node(seg2);
			}
			
			Edge e = new Edge(from,to,seg3);
			edges.add(e);
		}
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
