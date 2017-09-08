import java.util.ArrayList;
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
	
	public List<Integer> randomMethod2 (List<Integer> nbrs) {
		int pickedNbr = 0;
		for (int i = nbrs.size()-1; i > 0; i--) {
			pickedNbr = randomMethod(nbrs.subList(0, i+1));
			int ind = nbrs.indexOf(pickedNbr);
			nbrs.set(ind, nbrs.get(i));
			nbrs.set(i, pickedNbr);
		}
		return nbrs;
	}
	
	public List<Integer> randomMethod3 (List<Integer> nodes, List<Integer> markedNodes) {
		int i = nodes.size()-1;
		for (int j = 0; j < markedNodes.size(); j++) {
			int nbr = markedNodes.get(j);
			int ind = nodes.indexOf(nbr);
			nodes.set(ind, nodes.get(i));
			nodes.set(i, nbr);
			i--;
		}
		ArrayList<Integer> nbrList = new ArrayList<>();
		for(int j = 0; j < i+1; j++) {
			nbrList.add(nodes.get(j));
		}
		return nbrList;
//		return nodes.subList(0, i+1);
	}

}
