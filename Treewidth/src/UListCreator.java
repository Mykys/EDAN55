import java.util.List;
import java.util.ArrayList;

public class UListCreator {
	
	private Graph g;
	private List<String> UList;
	
	public UListCreator(Graph g) {
		this.g = g;
		UList = new ArrayList<>();
	}
	
	public List<String> calcUList(Bag b) {
		int bagSize = b.bagSize();
		for (int i = 0; i < possibleCombinations(bagSize); i++) {
			String nbr = createString(i, b);
			String U = reverseString(nbr);
			if (checkIndep(U, b)) {
				UList.add(U);
			}
		}
		return UList;
	}
	
	private String createString(int i, Bag b) {
		StringBuilder sb = new StringBuilder();
		int remainder = i;
		while (remainder > 0) {
			sb.append(remainder % 2);
		}
		while (sb.toString().length() < b.bagSize()) {
			sb.append(0);
		}
		
		return sb.toString();
	}
	
	private boolean checkIndep(String u, Bag b) {
		List<Integer> nodes = b.getNodeNbr(u);
		for (int i : nodes) {
			for (int j : nodes) {
				if (g.getElement(i, j) == 1) {
					return false;
				}
			}
		}
		return true;
	}

	private int possibleCombinations(int bagSize) {
		if(bagSize == 0) {
			return 1;
		} else {
			return 2*possibleCombinations(bagSize-1);
		}
	}

	private String reverseString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length()-1; i++) {
			sb.append(s.charAt(i));
			sb.append(" ");
		}
		sb.append(s.charAt(s.length()-1));
		return sb.toString();
	}
	
}
