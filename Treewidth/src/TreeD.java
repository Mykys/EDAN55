import java.util.HashMap;

public class TreeD {
	
	HashMap<Integer,Bag> bagList;
	int[][] adjMatrix;
	
	public void addBag(int bagNbr, Bag bag) {
		bagList.put(bagNbr, bag);
	}
	
	public void setAdjMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}
	
}
