import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

	private void SolveRecursive(Bag root) {
		List<Bag> neighbours = root.getNeighbours();
		List<U> parent_sets = root.getUList();

		List<Integer> total_from_children;
		List<Integer> parent_nodes;
		for (U u : parent_sets) {
			nodes = u.getNodesInU();

			List<U> children_sets;
			int weightOfU; // w(u)
			for (Bag b : neighbours) {
				children_sets = b.getUList();

				List<Integer> diffVal = new ArrayList<>();
				List<Integer> children_nodes;
				int maxIS = 0;
				for (U u : children_sets) {
					children_nodes = u.getNodesInU();
					maxIS = u.getMaxIS();

					int weight = 0;
					for (Integer i : children_nodes) {
						if (parent_nodes.contains(i)) {
							weight++;
						}
					}
					int diff = maxIS - weight;
					diffVal.add(diff);
				}
				int maxFromChild = Collections.max(diffVal);
				total_from_children.add(maxFromChild);

				int position = diffVal.indexOf(Collections.max(diffVal));
				weightOfU = u.getMaxIS(); // w(u), getSize()?

			}
			int parent_MaxIS = weightOfU;
			for (Integer i : total_from_children) {
				parent_MaxIS += i;
			}
		}

	}

}
