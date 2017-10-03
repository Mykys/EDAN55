import java.util.List;
import java.util.ArrayList;

public class RecursiveSolver {
	
	Graph g;
	TreeD TD;
	
	public RecursiveSolver(Graph g, TreeD TD) {
		this.g = g;
		this.TD = TD;
	}
	
	public List<Integer> solvePostOrder(int rootNbr) {
		List<Integer> solution = new ArrayList<>();
		Bag root = TD.getBag(rootNbr);
		solution = solveRecursive(root);
		
		
		return solution;
	}
	
	private List<Integer> solveRecursive(Bag root) {
		List<Bag> neighbours = root.getNeighbours();
		for (Bag b : neighbours) {
			if (b != root) {
				solveRecursive(b);
			}
			if (neighbours.size() == 1) {
				MISSolver ms = new MISSolver();
				int[][] bagMatrix = b.bagMatrix(b.getNodes(), g);
				List<Integer> ignoreList = new ArrayList<Integer>();
				List<Integer> solution = ms.algRecursive(bagMatrix, ignoreList);
			}
		}

		return null;
	}
	
}
