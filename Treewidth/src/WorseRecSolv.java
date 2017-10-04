import java.util.ArrayList;
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
	
}
