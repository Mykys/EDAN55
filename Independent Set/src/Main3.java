import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main3 {

	public static void main(String[] args) {
		String filename = "C:/Users/Shintai/Desktop/Skola/edan55/Independent Set/g120.txt";
		//String filename = "C:/Users/Myky/Documents/EDAN55/Independent Set/g4.txt";
		int[][] adjMatrix = readFile(filename);
		Counter counter = new Counter(0);
		int MIS = run(adjMatrix, counter);
		print(MIS);
		int nbrRec = counter.getCount();
		print(nbrRec);
	}

	public static int run(int[][] adjMatrix, Counter counter) {
		int MIS = 0;
		ArrayList<Integer> ignoreList = new ArrayList<>();
		MIS = algRecursive(adjMatrix, ignoreList, counter);
		return MIS;
	}

	private static int algRecursive(int[][] adjMatrix, List<Integer> ignoreList, Counter counter) {
		if (adjMatrix == null) {
			return 0;
		}
		
		// Base
		if (ignoreList.size() >= adjMatrix.length) {
			return 0;
		}

		counter.increment();

		// Isolated nodes
		int MIS = 0;
		
		// Nodes with degree 2
		ArrayList<Integer> neighbours;
		int nodeNbr = findNode(adjMatrix, ignoreList, 2);
		while (nodeNbr != -1) {
			MIS++;
			neighbours = findNeighbours(adjMatrix, ignoreList, nodeNbr);
			int a = neighbours.get(1);
			int b = neighbours.get(2);
			ArrayList<Integer> neighboursA = findNeighboursR2(adjMatrix, ignoreList, a, nodeNbr);
			if (!neighboursA.contains(b)) {
				ArrayList<Integer> neighboursB = findNeighboursR2(adjMatrix, ignoreList, b, nodeNbr);
				for (Integer i : neighboursA) {
					if (!neighboursB.contains(i)) {
						neighboursB.add(i);
					}
				}
				//maybe problem here, adjMatrix != newMatrix?
				adjMatrix = createMatrix(adjMatrix, neighboursB);
			}
				//maybe problem here, check ignoreList with 'if' on every node addition
				ignoreList.add(nodeNbr);
				ignoreList.add(a);
				ignoreList.add(b);
				nodeNbr = findNode(adjMatrix, ignoreList, 2);
		}
		
		// Nodes with degree 1
		nodeNbr = findNode(adjMatrix, ignoreList, 1);
		while (nodeNbr != -1) {
			MIS++;
			neighbours = findNeighbours(adjMatrix, ignoreList, nodeNbr);
			for (Integer i : neighbours) {
				if (!ignoreList.contains(i)) {
					ignoreList.add(i);
				}
			}
			// ignoreList.add(nodeNbr);
			nodeNbr = findNode(adjMatrix, ignoreList, 1);
		}
		
		nodeNbr = findNode(adjMatrix, ignoreList, 0);
		while (nodeNbr != -1) {
			MIS++;
			ignoreList.add(nodeNbr);
			nodeNbr = findNode(adjMatrix, ignoreList, 0);
		}
		

		// Connected nodes
		int startingPoint = maxDegree(adjMatrix, ignoreList);
		if (startingPoint != -1) {
			neighbours = findNeighbours(adjMatrix, ignoreList, startingPoint);

			ArrayList<Integer> ignoreList_left = new ArrayList<>();
			ignoreList_left.addAll(ignoreList);
			for (Integer i : neighbours) {
				if (!ignoreList_left.contains(i)) {
					ignoreList_left.add(i);
				}
			}

			int alt_left = algRecursive(adjMatrix, ignoreList_left, counter) + 1;

			ignoreList.add(startingPoint);
			int alt_right = algRecursive(adjMatrix, ignoreList, counter);
			int altMax = Math.max(alt_left, alt_right);
			MIS += altMax;
		}
		return MIS;
	}

	private static int[][] createMatrix(int[][] adjMatrix, List<Integer> neighbours) {
		int n = adjMatrix.length;
		int[][] newMatrix = new int[n+1][n+1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newMatrix[i][j] = adjMatrix[i][j];
			}
		}
		for (int i = 0; i < n+1; i++) {
			if (neighbours.contains(i)) {
				newMatrix[n][i] = 1;
				newMatrix[i][n] = 1;
			} else {
				newMatrix[n][i] = 0;
				newMatrix[i][n] = 0;
			}
		}
		return newMatrix;
	}
	
	private static ArrayList<Integer> findNeighbours(int[][] adjMatrix, List<Integer> ignoreList, int startingPoint) {
		ArrayList<Integer> neighbours = new ArrayList<>();
		neighbours.add(startingPoint);
		for (int i = 0; i < adjMatrix.length; i++) {
			if (!ignoreList.contains(i) && adjMatrix[startingPoint][i] == 1) {
				neighbours.add(i);
			}
		}

		return neighbours;
	}
	
	private static ArrayList<Integer> findNeighboursR2(int[][] adjMatrix, List<Integer> ignoreList, int startingPoint, int nodeNbr) {
		ArrayList<Integer> neighbours = new ArrayList<>();
		//neighbours.add(startingPoint);
		for (int i = 0; i < adjMatrix.length; i++) {
			if (!ignoreList.contains(i) && adjMatrix[startingPoint][i] == 1 && i != nodeNbr) {
				neighbours.add(i);
			}
		}

		return neighbours;
	}

	private static int maxDegree(int[][] adjMatrix, List<Integer> ignoreList) {
		int k = adjMatrix.length;
		int maxDegree = -1;
		int node = -1;
		for (int i = 0; i < k; i++) {
			if (!ignoreList.contains(i)) {
				int currDegree = 0;
				for (int j = 0; j < k; j++) {
					if (!ignoreList.contains(j)) {
						if (adjMatrix[i][j] == 1) {
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

	private static int findNode(int[][] adjMatrix, List<Integer> ignoreList, int deg) {
		int k = adjMatrix.length;
		for (int i = 0; i < k; i++) {
			if (!ignoreList.contains(i)) {
				int degree = 0;
				for (int j = 0; j < k; j++) {
					if (!ignoreList.contains(j)) {
						if (adjMatrix[i][j] == 1) {
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

	public static void print(int MIS) {
		System.out.println(MIS);
	}

	public static int[][] readFile(String filename) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String size = br.readLine().trim();
			int n = Integer.parseInt(size);
			String[] row;
			int[][] adjMatrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				row = br.readLine().trim().split(" ");
				for (int j = 0; j < row.length; j++) {
					int k = Integer.parseInt(row[j]);
					adjMatrix[i][j] = k;
				}
			}
			br.close();
			return adjMatrix;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
