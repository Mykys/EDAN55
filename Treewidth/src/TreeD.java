import java.util.HashMap;
import java.util.Random;

public class TreeD {
	
	HashMap<Integer,Bag> bagList;
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
	
}
