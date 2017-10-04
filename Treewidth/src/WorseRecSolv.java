import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorseRecSolv {
	
	Graph g;
	TreeD TD;

	public WorseRecSolv(Graph g, TreeD TD) {
		this.g = g;
		this.TD = TD;
	}

	public List<List<Integer>> solvePostOrder(int rootNbr) {
		List<List<Integer>> solution = new ArrayList<>();
		Bag root = TD.getBag(rootNbr);
		solution = solveRecursive(root);

		return solution;
	}

	private List<List<Integer>> solveRecursive(Bag root) {
		WorseULC UListCreator = new WorseULC(g);
		List<Bag> branches = root.getNeighbours();
		List<List<List<Integer>>> childList = new ArrayList<>();
		for (Bag b : branches) {
			if (b != root) {
				List<List<Integer>> locSolution = solveRecursive(b);
				childList.add(locSolution);
			}
			if (branches.size() == 1) {
				//solve for leaf
				return UListCreator.calcUNodes(b, UListCreator.calcUList(b));
			}
			
		}
		//solve for branch
		List<List<Integer>> parentUList = UListCreator.calcUNodes(root, UListCreator.calcUList(root));
		for (List<Integer> currU : parentUList) {
			for (List<List<Integer>> childCurrUList : childList) {
				for (List<Integer> childCurrU : childCurrUList) {
					for (int node : childCurrU) {
						if (!currU.contains(node)) {
							currU.add(node);
						}
					}
				}
			}
		}
		return parentUList; //this gives all nodes in each parentU, not discerning between nodes originally from parentU or childU
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
