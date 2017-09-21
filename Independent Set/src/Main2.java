import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2 {

	public static void main(String[] args) {
		//String filename = "C:/Users/Shintai/Desktop/Skola/edan55/Independent Set/g30.txt";
		String filename = "C:/Users/Myky/Documents/EDAN55/Independent Set/g30.txt";
		int[][] adjMatrix = readFile(filename);
		Counter counter = new Counter(0);
		int MIS = run(adjMatrix);
		print(MIS);

	}

	public static int run(int[][] adjMatrix) {
		int MIS = 0;
		ArrayList<Integer> ignoreList = new ArrayList<>();
		HashMap<Integer, Integer> degreeList = new HashMap<>();
		MIS = algRecursive(adjMatrix, ignoreList, degreeList);
		return MIS;
	}

	private static int algRecursive(int[][] adjMatrix, List<Integer> ignoreList, HashMap<Integer, Integer> degreeList) {
		if (adjMatrix == null) {
			return 0;
		}
		if (ignoreList.size() >= adjMatrix.length) {
			return 0;
		}
		int MIS = 0;
		degreeList = findDegrees(adjMatrix, ignoreList);
		int nodeNbr = findNode(degreeList, ignoreList, 0);
		while (nodeNbr != -1) {
			MIS++;
			ignoreList.add(nodeNbr);
			degreeList = findDegrees(adjMatrix, ignoreList);
			nodeNbr = findNode(degreeList, ignoreList, 0);
		}
		if (!degreeList.isEmpty()) {
			int maxSize = adjMatrix.length;
			int startingPoint = maxDegree(degreeList, maxSize);
			ArrayList<Integer> neighbors = findNeighbors(adjMatrix, startingPoint);
			ArrayList<Integer> ignoreList1 = new ArrayList<>();
			ignoreList1.addAll(ignoreList);
			for (Integer i : neighbors) {
				if (!ignoreList1.contains(i)) {
					ignoreList1.add(i);
				}
			}
			HashMap<Integer, Integer> degreeList1 = findDegrees(adjMatrix, ignoreList1);
			int alt1 = algRecursive(adjMatrix, ignoreList1, degreeList1) + 1;
			ignoreList.add(startingPoint);
			degreeList = findDegrees(adjMatrix, ignoreList);
			int alt2 = algRecursive(adjMatrix, ignoreList, degreeList);
			int altMax = Math.max(alt1, alt2);
			MIS += altMax;
		}
		return MIS;
	}

	private static HashMap<Integer, Integer> findDegrees(int[][] adjMatrix, List<Integer> ignoreList) {
		int k = adjMatrix.length;
		// int size = k - ig;
		HashMap<Integer, Integer> degreeList = new HashMap<>();
		for (int i = 0; i < k; i++) {
			int degree = 0;
			for (int j = 0; j < k; j++) {
				if (ignoreList.contains(i) || ignoreList.contains(j)) {
					
				} else {
					if (adjMatrix[i][j] == 1) {
						degree++;
					}
				}
			}
			degreeList.put(i, degree);
		}
		for (Integer i : ignoreList) {
			degreeList.remove(i);
		}
		return degreeList;
	}

	private static int findNode(HashMap<Integer, Integer> degreeList, List<Integer> ignoreList, int deg) {
		int nodeNbr = -1;
		for (int i = 0; i < degreeList.size(); i++) {
			if (!ignoreList.contains(i) && degreeList.get(i) != null && degreeList.get(i) == deg) {
				nodeNbr = i;
				return nodeNbr;
			}
		}
		return nodeNbr;
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

	private static int maxDegree(HashMap<Integer, Integer> degreeList, int maxSize) {
		int start = -1;
		int value = -1;
		for (int i = 0; i < maxSize; i++) {
			if (degreeList.get(i) != null && degreeList.get(i) > value) {
				value = degreeList.get(i);
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
