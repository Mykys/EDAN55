import java.util.ArrayList;
import java.util.List;

public class Bag {
	
	List<Integer> nodes;
	List<Integer> umax;
	int MIS;
	
	public  Bag() {
		nodes = new ArrayList<>();
		umax = new ArrayList<>();
	}
	
	public int[][] bagMatrix (List<Integer> nodes, Graph g) {
		int size = nodes.size();
		int[][] bagMatrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			int currNode = nodes.get(i);
			for (int j = 0; i < size; j++) {
				int node = nodes.get(j);
				if (g.getElement(i, j) == 1) {
					bagMatrix[i][j] = 1;
					bagMatrix[j][i] = 1;
				}
			}
		}
		
		return bagMatrix;
	}

}
