import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Node {
	private int val;
	private List<Edge> edges = new ArrayList<>();
	
	public  Node (int val) {
		this.val = val;
	}
	
	public void randomize() {
		Random rnd = new Random();
		int rndVal = rnd.nextInt(2);
		val = rndVal;
	}
	
	public int getNodeVal() {
		return val;
	}
	
	public void add(Edge e) {
		edges.add(e);
	}
	
	public List<Edge> getEdges() {
		return edges;
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
	
//	public int maxCut(HashMap<Integer, Node> nodeList) {
//		int count = 0;
//		List<Edge> e;
//		for (int i = 0; i < nodeList.size(); i++) {
//			e = nodeList.get(i).edges;
//			for (int j = 0; j < e.size(); j++) {
//				int fromVal = e.get(j).getFrom().getNodeVal();
//				int toVal = e.get(j).getTo().getNodeVal();
//				if (fromVal == 0 && toVal == 1 || fromVal == 1 && toVal == 0) {
//					count = count + e.get(j).getWeight();
//				}
//			}	
//		}
//		return count;
//	}
//	
//	public int run(List<Edge> e) {
//		for (int i = 0; i < e.size(); i++) {
//			nodeList.get(i).randomize();
//		}
//		return maxCut(nodeList);
		
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

