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
	
	public List<Integer> MISList(int[][] AdjMatrix, List<Integer> nodes) {
		List<Integer> MISList = new ArrayList<>();
		
		return MISList;
	}
	
	public int[][] bagMatrix (List<Integer> nodes, Graph g) {
		int size = nodes.size();
		int row = 0;
		int col = 0;
		int[][] bagMatrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			int node = nodes.get(i);
			for (int j = 0; i < size; j++) {
				if (nodes.contains(i))
			}
		}
		
		return bagMatrix;
	}
	
	
	public static int[][] subMatrix(int[][] adjMatrix, List<Integer> removeList) {
		int remove = removeList.size();
		int k = adjMatrix.length;
		int sub = k - remove;
		int[][] subMatrix = new int[sub][sub];
		int a = 0;
		int b = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if (removeList.contains(i) || removeList.contains(j)) {
					// skip/do nothing if this case is true
				} else {
					if (a < sub) {
						subMatrix[a][b] = adjMatrix[i][j];
						b++;
						if (b >= sub) {
							a++;
							b = 0;
						}
					}
				}
			}
		}
		return subMatrix;
	}

}
