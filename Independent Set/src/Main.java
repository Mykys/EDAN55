import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String filename = "C:/Users/Shintai/Desktop/Skola/edan55/Independent Set/g15.txt";
		int[][] adjMatrix = readFile(filename);
		Counter counter = new Counter(0);
		int MIS = run(adjMatrix);
		print(MIS);

	}

	public static int run(int[][] adjMatrix) {
		int MIS = 0;
		MIS = algRecursive(adjMatrix);
		return MIS;
	}

	private static int algRecursive(int[][] adjMatrix) {
		if (adjMatrix == null) {
			return 0;
		}
		ArrayList<Integer> removeList = new ArrayList<>();
		int MIS = 0;
		// Check for nodes with degree 1
		removeList.clear();
		removeList = findNode(adjMatrix, 1);
		if (removeList.size() > 0) {
			ArrayList<Integer> removeList2 = R1AddOn(adjMatrix, removeList); //Find neighbors to removeList
			adjMatrix = subMatrix(adjMatrix, removeList2);
			MIS += removeList.size();
		}
		// Check for nodes with degree 0
//		int MIS = 0;
//		ArrayList<Integer> 
		removeList = findNode(adjMatrix, 0);
		if (removeList.size() > 0) {
			adjMatrix = subMatrix(adjMatrix, removeList);
			MIS += removeList.size();
		}

		if (adjMatrix.length != 0) {
			int startingPoint = maxDegree(adjMatrix);
			ArrayList<Integer> neighbors = findNeighbors(adjMatrix, startingPoint);
			int[][] a1Matrix = subMatrix(adjMatrix, neighbors);
			int alt1 = algRecursive(a1Matrix) + 1;
			neighbors.clear();
			neighbors.add(startingPoint);
			int[][] a2Matrix = subMatrix(adjMatrix, neighbors);
			int alt2 = algRecursive(a2Matrix);
			int altMax = Math.max(alt1, alt2);
			MIS += altMax;
		}
		return MIS;
	}

	private static ArrayList<Integer> findNode(int[][] adjMatrix, int deg) {
		ArrayList<Integer> removeList = new ArrayList<>();
		for (int i = 0; i < adjMatrix.length; i++) {
			int degree = 0;
			for (int j = 0; j < adjMatrix.length; j++) {
				if (adjMatrix[i][j] == 1) {
					degree++;
				}
			}
			if (degree == deg) {
				if(!removeList.contains(i)) {
					removeList.add(i);
				}
			}
		}
		return removeList;
	}

	private static ArrayList<Integer> R1AddOn(int[][] adjMatrix, ArrayList<Integer> removeList) {
		ArrayList<Integer> neighborList = new ArrayList<>();
		neighborList.addAll(removeList);
		for (int i : removeList) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if (adjMatrix[i][j] == 1) {
					if (!neighborList.contains(j)) {
						neighborList.add(j);
					}
				}
			}
		}
		return neighborList;
	}

	private static ArrayList<Integer> findNeighbors(int[][] adjMatrix, int startingPoint) {
		ArrayList<Integer> neighbors = new ArrayList<>();
		neighbors.add(startingPoint);
		for (int i = 0; i < adjMatrix.length; i++) {
			if (adjMatrix[startingPoint][i] == 1) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}

	private static int maxDegree(int[][] adjMatrix) {
		int start = 0;
		int size = adjMatrix.length;
		int maxDegree = 0;
		for (int i = 0; i < size; i++) {
			int degree = 0;
			for (int j = 0; j < size; j++) {
				if (adjMatrix[i][j] == 1) {
					degree++;
				}
			}
			if (degree > maxDegree) {
				maxDegree = degree;
				start = i;
			}
		}
		return start;
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
