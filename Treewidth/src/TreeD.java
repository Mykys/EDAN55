import java.util.HashMap;
import java.util.Random;
import java.util.List;

public class TreeD {
	
	HashMap<Integer, Bag> bagList;
	int[][] adjMatrix;
	
	public void addBag(int bagNbr, Bag bag) {
		bagList.put(bagNbr, bag);
	}
	
	public void setAdjMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	public int setRandomRoot() {
		Random r = new Random();
		return 1+r.nextInt(bagList.size());
	}
	
	public Bag getBag(int bagNbr) {
		return bagList.get(bagNbr);
	}
	
	public HashMap<Integer, Bag> modifyTree() {
		boolean status = true;
		int remove = 0;
		for (int i = 1; i <= bagList.size(); i++) {
			Bag bag1 = bagList.get(i);
			Bag bag2 = bagList.get(i+1);
			List<Integer> large;
			List<Integer> small;
			if (bag1.bagSize() > bag2.bagSize()) {
				large = bag1.getbagList();
				small = bag2.getbagList();
				remove = i+1;
			} else {
				small = bag1.getbagList();
				large = bag2.getbagList();
				remove = i;
			}
			
			for (Integer k : small) {
				if (!large.contains(k)) {
					status = false;
				}
					
			}
		if (status == true) {
			bagList.remove(remove);
					
		}
	}
	return bagList;
			
}	
}
