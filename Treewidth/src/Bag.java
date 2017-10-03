import java.util.ArrayList;
import java.util.List;

public class Bag {
	
	List<Integer> nodes;
	List<Integer> umax;
	int MIS;
	int bagNbr = 0;
	List<Bag> neighbours;
	
	public Bag() {
		nodes = new ArrayList<>();
		umax = new ArrayList<>();
		neighbours = new ArrayList<>();
	}
	
	public Bag(List<Integer> nodes, int bagNbr) {
		this.nodes = nodes;
		umax = new ArrayList<>();
		this.bagNbr = bagNbr;
		neighbours = new ArrayList<>();
	}
	
	public int getBagNbr() {
		return bagNbr;
	}
	
	public List<Integer> getNodes() {
		return nodes;
	}
	
	public void addNeighbour(Bag b) { //Maybe want to change "int i" to "Bag b"
		neighbours.add(b);
	}
	
	public List<Bag> getNeighbours() {
		return neighbours;
	}
	
	public List<Integer> MISList(int[][] AdjMatrix, List<Integer> nodes) {
		List<Integer> MISList = new ArrayList<>();
		
		return MISList;
	}
	
	public int[][] bagMatrix (List<Integer> nodes, Graph g) {
		int size = nodes.size();
		int[][] bagMatrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			int currNode = nodes.get(i);
			for (int j = 0; i < size; j++) {
				int node = nodes.get(j);
				if (g.getElement(currNode, node) == 1) {
					bagMatrix[i][j] = 1;
					bagMatrix[j][i] = 1;
				}
			}
		}
		
		return bagMatrix;
	}
	
	public int bagSize() {
		return nodes.size();
	}
	
	public List<Integer> getbagList() {
		return nodes;
	}

}
