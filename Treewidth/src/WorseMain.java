import java.util.ArrayList;
import java.util.List;

public class WorseMain {
	
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
		
		
//		String filenameG = "C:/Users/Myky/Documents/EDAN55/Treewidth/web1.gr.txt";
		String filenameG = "C:/Users/Shintai/Desktop/Skola/edan55/Treewidth/eppstein.gr.txt";
//		String filenameT = "C:/Users/Myky/Documents/EDAN55/Treewidth/web1.td.txt";
		String filenameT = "C:/Users/Shintai/Desktop/Skola/edan55/Treewidth/eppstein.td.txt";
		Parser p = new Parser();
		Graph g = p.readGraph(filenameG);
		TreeD TD = p.readTree(filenameT, g);
		TD.modifyTree();
		int root = TD.setRandomRoot();
		Bag rootBag = TD.getBag(root);
//		RecursiveSolver rs = new RecursiveSolver(g, TD);
		WorseRecSolv wrs = new WorseRecSolv(g, TD);
		List<List<Integer>> MISNodes = new ArrayList<>();
		MISNodes = wrs.solvePostOrder(root);
		StringBuilder sb = new StringBuilder();
		int max = 0;
		List<Integer> rootMax = new ArrayList<>();
		for (List<Integer> rootU : MISNodes) {
			if (rootU.size() > max) {
				max = rootU.size();
				rootMax = rootU;
			}
		}
		for (int i : rootMax) {
			sb.append(i);
			sb.append(" ");
		}
		System.out.println(rootMax.size());
		System.out.println(sb.toString());
	}
	


}
