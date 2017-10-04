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
				List<Integer> locSolution = solveRecursive(b);
			}
			if (neighbours.size() == 1) {
				MISSolver ms = new MISSolver();
				int[][] bagMatrix = b.bagMatrix(b.getNodes(), g);
				List<Integer> ignoreList = new ArrayList<Integer>();
				List<Integer> leafSolution = ms.algRecursive(bagMatrix, ignoreList);
				return leafSolution;
			} else {
				MISSolver ms = new MISSolver();
				int[][] bagMatrix = b.bagMatrix(b.getNodes(), g);
				List<Integer> ignoreList = new ArrayList<Integer>();
				List<Integer> nodeSolution = ms.algRecursive(bagMatrix, ignoreList);
			}

		}

		return null;
	}
	
	private List<Integer> localSolution(Bag b) {
		MISSolver ms = new MISSolver();
		int[][] bagMatrix = b.bagMatrix(b.getNodes(), g);
		List<Integer> ignoreList = new ArrayList<Integer>();
		List<Integer> localSolution = ms.algRecursive(bagMatrix, ignoreList);
		return localSolution;
	}
	

	private List<List<Integer>> SolveRecursive(Bag root) {
		List<List<Integer>> returnSet = new ArrayList<>();
		List<Bag> neighbours = root.getNeighbours();
		UListCreator ulc = new UListCreator(g);
//		List<U> parent_sets = root.getUList();
		List<String> parent_sets = ulc.calcUList(root);
		List<List<Integer>> UAll = ulc.calcUNodes(root, parent_sets);

		List<Integer> total_from_children = new ArrayList<>();
		List<Integer> parent_nodes = new ArrayList<>();
		for (List<Integer> u : UAll) {
//			parent_nodes = u.getNodesInU();

			List<List<Integer>> children_sets;
			int weightOfU = 0; // w(u)
			for (Bag b : neighbours) {
//				children_sets = b.getUList();
				children_sets = ulc.calcUNodes(b, ulc.calcUList(b));

				List<Integer> diffVal = new ArrayList<>();
				List<Integer> children_nodes = new ArrayList<>();
				int maxIS = 0;
				for (List<Integer> w : children_sets) {
//					children_nodes = w.getNodesInU();
					maxIS = w.size();

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
				List<Integer> maxSet = children_sets.get(position);
				returnSet.add(maxSet);
				weightOfU = u.size(); // w(u), getSize()?

			}
			int parent_MaxIS = weightOfU;
			for (Integer i : total_from_children) {
				parent_MaxIS += i;
			}
		}
		return returnSet;
	}

}
