import java.util.List;
import java.util.Random;

public class Randomizer {
	
	public Randomizer() {
		
	}
	
	public int randomMethod (List<Integer> nbrs) {
		int nbr = new Random().nextInt(nbrs.size());
		return nbrs.get(nbr);
		
	}
	
	public Node randomMethod3(List<Node> nbrs) {
		int nbr = new Random().nextInt(nbrs.size());
		return nbrs.get(nbr);
	}
	
	public int randomMethod4(int rSize) {
		return new Random().nextInt(rSize);
	}
	
	public int randomMethod2 (List<Integer> nbrs) {
		int pickedNbr = 0;
		for (int i = nbrs.size()-1; i > 0; i--) {
			pickedNbr = randomMethod(nbrs.subList(0, i+1));
			int ind = nbrs.indexOf(pickedNbr);
			nbrs.set(ind, nbrs.get(i));
			nbrs.set(i, pickedNbr);
		}
		return pickedNbr;
	}

}
