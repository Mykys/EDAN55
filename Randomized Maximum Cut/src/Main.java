import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
//		ArrayList<Edge> edges = new ArrayList<>();
//		ArrayList<Integer> nodeNbr = new ArrayList<>();
		HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		String filename = "C:/Users/Myky/Documents/EDAN55/Randomized Maximum Cut/pw09_100.9.txt";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		String Line = br.readLine().trim();
		String[] splitLine = Line.split(" ");
		int n = Integer.parseInt(splitLine[0]);
		int m = Integer.parseInt(splitLine[1]);
//		int n = Integer.parseInt(br.readLine());
//		int m = Integer.parseInt(br.readLine());
//		String Line;
		int seg1, seg2, seg3;
		String[] seg;
		for (int i = 0; i < m; i++) {
			Line = br.readLine().trim();
			seg = Line.split(" ");
			seg1 = Integer.parseInt(seg[0]);
			seg2 = Integer.parseInt(seg[1]);
			seg3 = Integer.parseInt(seg[2]);
			
			if(!nodeMap.containsKey(seg1)) {
				nodeMap.put(seg1, new Node(seg1));
			} 
			
			if(!nodeMap.containsKey(seg2)) {
				nodeMap.put(seg2, new Node(seg2));
			}
			
			Edge e = new Edge(nodeMap.get(seg1), nodeMap.get(seg2), seg3);
			nodeMap.get(seg1).add(e);
//			nodeMap.get(seg2).add(e);
		}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Randomize all the nodes in the map
		for (int i = 1; i <= nodeMap.size(); i++) {
			Node n = nodeMap.get(i);
			n.randomize();
		}
		
		int total = 0;
		for (int i = 1; i <= nodeMap.size(); i++) {
			Node n = nodeMap.get(i);
			int count = n.maxCut(n.getEdges());
			total = total + count;
		}
		
		System.out.println(total);
	
	}
}
