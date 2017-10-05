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
		solution = solveRecursive(root, root);

		return solution;
	}

	private List<List<Integer>> solveRecursive(Bag root, Bag prevRoot) {
		WorseULC UListCreator = new WorseULC(g);
		List<Bag> branches = root.getNeighbours();
		List<List<List<Integer>>> childList = new ArrayList<>();
		for (Bag b : branches) {
			if (root.equals(prevRoot)) {
				List<List<Integer>> locSolution = solveRecursive(b, root);
				childList.add(locSolution);
			}
			if (b != prevRoot) {
				List<List<Integer>> locSolution = solveRecursive(b, root);
				childList.add(locSolution);
			}
			if (branches.size() == 1 && b == prevRoot) {
				// solve for leaf
				return UListCreator.calcUNodes(root, UListCreator.calcUList(root));
			}

		}
		// solve for branch
		List<List<Integer>> parentUList = UListCreator.calcUNodes(root, UListCreator.calcUList(root));
		for (List<Integer> currU : parentUList) {
			for (List<List<Integer>> childCurrUList : childList) {
				// intitialize temp List<Integer>
				List<Integer> temp = new ArrayList<>();
				int max = 0;
				for (List<Integer> childCurrU : childCurrUList) {
					// calc max (get f, compare childCurrU w/ currU) (save max
					// in temp List<Integer>)
					if (childCurrU.size() > max) {
						max = childCurrU.size();
						temp = childCurrU;
					}
				}
				// move the add part over here
				for (int node : temp) { // change childCurrU to temp
										// List<Integer>, also "move out"
					if (!currU.contains(node) && checkIndep(currU, node)) {
						currU.add(node);
					}
				}
			}
		}
		return parentUList; // this gives all nodes in each parentU, not
							// discerning between nodes originally from parentU
							// or childU
	}

	private boolean checkIndep(List<Integer> currU, int node) {
		boolean indep = true;
		for (int i : currU) {
			if(g.getElement(i-1, node-1) == 1) {
				indep = false;
			}
		}
		return indep;
	}

}
