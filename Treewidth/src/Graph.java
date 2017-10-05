
public class Graph {
	
	private int[][] adjMatrix;
	
	public Graph(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}
	
	public int getElement(int i, int j) {
		return adjMatrix[i][j];
	}

	
}
