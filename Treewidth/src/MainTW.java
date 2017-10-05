import java.util.ArrayList;
import java.util.List;

public class MainTW {
	
	public static void main(String[] args) {
		//StringBuilder sb = new StringBuilder();
		//String filename = "asdf";
//		sb.append("C:/Users/Shintai/Desktop/Skola/edan55/Randomized Maximum Cut/");
//		sb.append("C:/Users/Myky/Documents/EDAN55/Randomized Maximum Cut/");
//		sb.append(filename);
//		sb.append("GR.txt");
//		String graph = sb.toString();
//		sb.delete(0, graph.length()-1);
//		sb.append("C:/Users/Shintai/Desktop/Skola/edan55/Randomized Maximum Cut/");
//		sb.append("C:/Users/Myky/Documents/EDAN55/Randomized Maximum Cut/");
//		sb.append(filename);
//		sb.append("TD.txt");
		
		
		String filenameG = "C:/Users/Myky/Documents/EDAN55/Treewidth/web1.gr.txt";
		String filenameT = "C:/Users/Myky/Documents/EDAN55/Treewidth/web1.td.txt";
		Parser p = new Parser();
		Graph g = p.readGraph(filenameG);
		TreeD TD = p.readTree(filenameT, g);
		int root = TD.setRandomRoot();
		TD.modifyTree();
		Bag rootBag = TD.getBag(root);
		RecursiveSolver rs = new RecursiveSolver(g, TD);
		List<List<Integer>> MISNodes = new ArrayList<>();
		MISNodes = rs.solveRecursive(rootBag, null, MISNodes);
		
	}
	


}
