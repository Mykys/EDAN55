
public class MainTW {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
//		String filename = "asdf";
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
		Parser p = new Parser();
		Graph g = p.readGraph(args[0]);
		TreeD TD = p.readTree(args[1], g);
		int root = TD.setRandomRoot();
		
	}
	


}
