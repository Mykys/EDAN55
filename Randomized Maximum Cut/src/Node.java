import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	private int nbr;
	private List<Edge> edges = new ArrayList<>();
	private int val = 0;

	public Node(int nbr) {
		this.nbr = nbr;
	}

	public void randomize() {
		Random rnd = new Random();
		int rndVal = rnd.nextInt(2);
		val = rndVal;
	}

	public int getNodeVal() {
		return val;
	}

	public void setNodeVal(int nbr) {
		this.nbr = nbr;
	}

	public void add(Edge e) {
		edges.add(e);
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void changeVal() {
		if (val == 1) {
			val = 0;
		} else {
			val = 1;
		}
	}

	public int maxCut(List<Edge> list) {
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
}
