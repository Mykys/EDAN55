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
		List<U> parent_sets = ulc.calcUList(root); // List of all U in root
		//List<List<Integer>> parent_nodes = ulc.calcUNodes(root, parent_sets); //List of all nodes in each U in root

		List<Integer> total_from_children = new ArrayList<>(); 
		List<Integer> parent_nodes;
		for (U u : parent_sets) {
			parent_nodes = ulc.calcUNodes(root, u); //Parent nodes for each U
//			parent_nodes = u.getNodesInU();

			List<U> children_sets;
			List<Integer> children_nodes;
			int weightOfU = 0; // w(u)
			for (Bag b : neighbours) {
				children_sets = ulc.calcUList(b); // List of all U in child
				
				List<Integer> diffVal = new ArrayList<>();
				int maxIS = 0;
				//U currUChild;
				for (U uc : children_sets) {
					//currUChild = uc;
					children_nodes = ulc.calcUNodes(b, uc); //List of all nodes in each U in child
					maxIS = uc.getMaxWeight();
					
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
				
				int position = diffVal.indexOf(Collections.max(diffVal)); //Hope this works as we think
				U maxSet = children_sets.get(position);
				maxSet.setMaxWeight(maxFromChild);
				returnSet.add(ulc.calcUNodes(b, maxSet));
			}
			
			weightOfU = parent_nodes.size(); // w(u)
			int parent_MaxIS = weightOfU;
			for (Integer i : total_from_children) {
				parent_MaxIS += i;
			}
		}
		return returnSet;
	}

}
