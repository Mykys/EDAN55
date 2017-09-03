import java.util.List;

public class Node {
	
	public Node parent;
	public Node left;
	public Node right;
	private int nbr;
	public Node sibling;
	public boolean marked;
	
	
	public  Node(int nbr) {
		this.nbr = nbr;
		this.marked = false;
	}
	
	public  Node(int nbr, Node parent, Node left, Node right, Node sibling) {
		this.nbr = nbr;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.sibling = sibling;
	}
	
	public int getNbr() {
		return nbr;
	}
	
	public boolean isMarked() {
		return marked;
	}
}
