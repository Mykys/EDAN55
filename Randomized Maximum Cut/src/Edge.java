
public class Edge {
	private int weight;
	private Node from;
	private Node to;
	
	public  Edge (Node from, Node to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public Node getFrom() {
		return from;
	}
	
	public Node getTo() {
		return to;
	}
	
	public int getWeight() {
		return weight;
	}
}
