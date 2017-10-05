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
//		List<List<Integer>> parentTempList = new ArrayList<>();
//		int UListSize = parentUList.size();
//		int count = 0;
		for (List<List<Integer>> extra : childList) {
			for (List<Integer> extra2 : extra) {
				if (!parentUList.contains(extra2)) {
					parentUList.add(extra2);
				}
			}
		}
		for (List<Integer> currU : parentUList) {
//		while (count < UListSize) {
//			List<Integer> currU = parentUList.get(count);
			for (List<List<Integer>> childCurrUList : childList) {
				// intitialize temp List<Integer>
				List<Integer> temp = new ArrayList<>();
				int max = 0;
				for (List<Integer> childCurrU : childCurrUList) {
					// calc max (get f, compare childCurrU w/ currU) (save max
					// in temp List<Integer>)
					boolean indep = true;
					for (int node : childCurrU) {
						if (!checkIndep(currU, node)) {
							indep = false;
						}
					}
					if (childCurrU.size() > max && indep) {
						max = childCurrU.size();
						temp = childCurrU;
					}
				}
				// move the add part over here
				for (int node : temp) { // change childCurrU to temp
										// List<Integer>, also "move out"
					if (!currU.contains(node) && checkIndep(currU, node) && !currU.isEmpty()) {
						currU.add(node);
//						List<Integer> temp2 = new ArrayList<>();
//						temp2.add(node);
//						parentTempList.add(temp2);
					}
				}
			}
//			for (int j = 0; j < parentTempList.size(); j++) {
//				if (!parentUList.contains(parentTempList.get(j))) {
//					parentUList.add(parentTempList.get(j));
//				}
//			}
//			count++;
//			UListSize = parentUList.size();
		}
		return parentUList; // this gives all nodes in each parentU, not
							// discerning between nodes originally from parentU
							// or childU
	}

	private boolean checkIndep(List<Integer> currU, int node) {
		boolean indep = true;
		for (int i : currU) {
			if (i == node || g.getElement(i - 1, node - 1) == 1) {
				indep = false;
			}
		}
		return indep;
	}

}
