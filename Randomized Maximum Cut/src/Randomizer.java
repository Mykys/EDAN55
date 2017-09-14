import java.util.HashMap;

public class Randomizer {

	private HashMap<Integer, Node> map;
	public static int asdf = 0;

	public Randomizer(HashMap<Integer, Node> map) {
		this.map = map;
	}

	public int RandomR() {
		for (int i = 1; i <= map.size(); i++) {
			Node n = map.get(i);
			n.randomize();
		}
		return totalMaxCut();
	}

	public int RandomS() {
		boolean stat = true;
		int maxCut = 0;
		int prevMaxCut = 0;
		while (stat) {
			for (int i = 1; i <= map.size(); i++) {
				Node n = map.get(i);
				n.changeVal();
				int currMaxCut = totalMaxCut();

				if (currMaxCut > prevMaxCut) {
					prevMaxCut = currMaxCut;
					asdf++;
				} else {
					n.changeVal();
				}
			}
			if (prevMaxCut > maxCut) {
				maxCut = prevMaxCut;
			} else {
				stat = false;
			}
		}
		return maxCut;
	}

	public int RandomRS() {
		RandomR();
		return RandomS();
	}

	public int totalMaxCut() {
		int total = 0;
		for (int i = 1; i <= map.size(); i++) {
			Node n = map.get(i);
			int count = n.maxCut(n.getEdges());
			total = total + count;
		}
		return total;
	}

	public void reset() {
		for(int i = 1; i <= map.size(); i++) {
			map.get(i).reset();
			asdf=0;
		}
		
	}
	
	public void printSwap() {
		System.out.println(asdf);
	}
}