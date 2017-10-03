import java.util.ArrayList;
import java.util.List;

public class MISSolver {
	
	public List<Integer> algRecursive (int[][] bagMatrix, List<Integer> ignoreList) {
		List<Integer> nodeList = new ArrayList<>();
		
		// Empty
		if (bagMatrix == null) {
			return nodeList;
		}
		
		// Base
		if (ignoreList.size() >= bagMatrix.length) {
			return nodeList;
		}
		
		// Nodes with degree 1
		ArrayList<Integer> neighbours;
		int nodeNbr = findNode(bagMatrix, ignoreList, 1);
		while (nodeNbr != -1) {
			//MIS++;
			nodeList.add(nodeNbr);
			neighbours = findNeighbours(bagMatrix, ignoreList, nodeNbr);
			for (Integer i : neighbours) {
				if (!ignoreList.contains(i)) {
					ignoreList.add(i);
				}
			}
			nodeNbr = findNode(bagMatrix, ignoreList, 1);
		}
		
		// Isolated nodes
		nodeNbr = findNode(bagMatrix, ignoreList, 0);
		while (nodeNbr != -1) {
			//MIS++;
			nodeList.add(nodeNbr);
			ignoreList.add(nodeNbr);
			nodeNbr = findNode(bagMatrix, ignoreList, 0);
		}
		
		// Connected nodes
		int startingPoint = maxDegree(bagMatrix, ignoreList);
		if (startingPoint != -1) {
			neighbours = findNeighbours(bagMatrix, ignoreList, startingPoint);
			ArrayList<Integer> ignoreList_left = new ArrayList<>();
			ignoreList_left.addAll(ignoreList);
			for (Integer i : neighbours) {
				if (!ignoreList_left.contains(i)) {
					ignoreList_left.add(i);
				}
			}

			List<Integer> alt_left = algRecursive(bagMatrix, ignoreList_left);

			ignoreList.add(startingPoint);
			List<Integer> alt_right = algRecursive(bagMatrix, ignoreList);
			
			if ((alt_left.size() + 1) > alt_right.size()) {
				nodeList.addAll(alt_left);
				nodeList.add(startingPoint); //Doublet might appear
			} else {
				nodeList.addAll(alt_right);
			}
		}
		
		return nodeList;
	}
	
	private static int findNode(int[][] bagMatrix, List<Integer> ignoreList, int deg) {
		int k = bagMatrix.length;
		for (int i = 0; i < k; i++) {
			if (!ignoreList.contains(i)) {
				int degree = 0;
				for (int j = 0; j < k; j++) {
					if (!ignoreList.contains(j)) {
						if (bagMatrix[i][j] == 1) {
							degree++;
						}
					}
				}
				if (degree == deg) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private static ArrayList<Integer> findNeighbours(int[][] bagMatrix, List<Integer> ignoreList, int startingPoint) {
		ArrayList<Integer> neighbours = new ArrayList<>();
		neighbours.add(startingPoint);
		for (int i = 0; i < bagMatrix.length; i++) {
			if (!ignoreList.contains(i) && bagMatrix[startingPoint][i] == 1) {
				neighbours.add(i);
			}
		}

		return neighbours;
	}
	
	private static int maxDegree(int[][] bagMatrix, List<Integer> ignoreList) {
		int k = bagMatrix.length;
		int maxDegree = -1;
		int node = -1;
		for (int i = 0; i < k; i++) {
			if (!ignoreList.contains(i)) {
				int currDegree = 0;
				for (int j = 0; j < k; j++) {
					if (!ignoreList.contains(j)) {
						if (bagMatrix[i][j] == 1) {
							currDegree++;
						}
					}
				}
				if (currDegree > maxDegree) {
					maxDegree = currDegree;
					node = i;
				}
			}
		}
		return node;
	}
	

}
