import java.util.HashMap;
import java.util.Random;
import java.util.List;

public class TreeD {

	HashMap<Integer, Bag> bagList = new HashMap<Integer, Bag>();
	int[][] adjMatrix;

	public void addBag(int bagNbr, Bag bag) {
		bagList.put(bagNbr, bag);
	}

	public void setAdjMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	public int setRandomRoot() {
		Random r = new Random();
		return 1 + r.nextInt(bagList.size());
	}

	public Bag getBag(int bagNbr) {
		return bagList.get(bagNbr);
	}

	public void modifyTree() {
		boolean status = true;
		int remove = 0;

		List<Integer> large = null;
		List<Integer> small = null;
		for (int i = 1; i <= bagList.size(); i++) {
			Bag bag1 = bagList.get(i);
			for (int j = i + 1; j <= bagList.size(); j++) {
				status = true;
				Bag bag2 = bagList.get(j);
				if (bag1.bagSize() > bag2.bagSize()) {
					large = bag1.getbagList();
					small = bag2.getbagList();
					remove = j;
				} else {
					small = bag1.getbagList();
					large = bag2.getbagList();
					remove = i;
				}

				if (small.isEmpty()) {
					bagList.remove(remove);
				} else {
					for (Integer n : small) {
						if (!large.contains(n)) {
							status = false;
							break;
						}
					}
				}

				if (status == true) {
					bagList.remove(remove);
				}

			}
		}
	}

	public int treeSize() {
		return bagList.size();
	}
}
