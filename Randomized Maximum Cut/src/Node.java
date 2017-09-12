import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	private int val;
	List<Edge> edges = new ArrayList<>();
	//Lägg till t.ex. hashmap för att inte skapa samma objekt flera ggr
	
	public  Node (int val) {
		this.val = val;
	}
	
	public Node randomize() {
		Random rnd = new Random();
		int rndVal = rnd.nextInt(2);
		val = rndVal;
		return this;
	}
	
	public int getNodeVal() {
		return val;
	}
	
	public void add(Edge e) {
		edges.add(e);
	}
	
//	public List<Node> sortA(List<Node> nodeList, List<Node> listA) {
//		for (Node n : nodeList) {
//			if (n.getNodeVal() == 0) {
//				listA.add(n);
//			}
//		}
//		return listA;	
//	}
//	
//	public List<Node> sortB(List<Node> nodeList, List<Node> listB) {
//		for (Node n : nodeList) {
//			if (n.getNodeVal() == 1) {
//				listB.add(n);
//			}
//		}
//		return listB;
//	}
	
	public int maxCut (List<Edge> list) {
		int count = 0;
		for (Edge e : list) {
			int fromVal = e.getFrom().getNodeVal();
			int toVal = e.getTo().getNodeVal();
			if (fromVal == 0 && toVal == 1 || fromVal == 1 && toVal == 0) {
				count = count + e.getWeight();
			}
		}	
		return count;
	}
	
//	public int maxCut (List<Node> listA, List<Node> listB) {
//		int count = 0;
//		for (Node n : listA) {
//			for (Node m : listB) {
//				if(n.getNodeVal() == m.getNodeVal()) {
//					count = count + m.getNodeVal();
//				}
//			}
//		}
//		return count;
//	}
}
