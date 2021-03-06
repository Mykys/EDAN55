import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	
	
	
	public Graph readGraph(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			int n = -1;
			int m = -1;
			String[] line = nextLine(br);
			while (isCommentLine(line)) {
				line = nextLine(br);
			}
			if (line[0].equals("p")) {
				n = toInt(line[2]);
				m = toInt(line[3]);
				line = nextLine(br);
			}
			int tempM = 0;
			int[][] adjMatrix = new int[n][n];
			while (tempM < m) {
				while (isCommentLine(line)) {
					line = nextLine(br);
					tempM--;
				}
				tempM++;
				int i = toInt(line[0]);
				int j = toInt(line[1]);
				adjMatrix[i-1][j-1] = 1;
				adjMatrix[j-1][i-1] = 1;
				line = nextLine(br);
				
				if (line == null) {
					break;
				}
			}
			Graph g = new Graph(adjMatrix);
			br.close();
			return g;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public TreeD readTree(String filename, Graph g) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			int b = -1;
			int w = -1;
			int n = -1;
			String[] line = nextLine(br);
			while (isCommentLine(line)) {
				line = nextLine(br);
			}
			if (line[0].equals("s")) {
				b = toInt(line[2]);
				w = toInt(line[3]);
				n = toInt(line[4]);
			}
			int tempB = 0;
			int[][] adjMatrix = new int[b][b];
			TreeD TD = new TreeD();
			while (tempB < b) {
				while (isCommentLine(line)) {
					line = nextLine(br);
				}
				line = nextLine(br);
				tempB++;
				int bagNbr = toInt(line[1]);
				List<Integer> bagNodes = new ArrayList<>();
				for (int i = 2; i < line.length; i++) {
					bagNodes.add(toInt(line[i]));
				}
				Bag bag = new Bag(bagNodes,bagNbr);
				TD.addBag(bagNbr, bag);
			}
			line = nextLine(br);
			while (line != null) {
				while (isCommentLine(line)) {
					line = nextLine(br);
				}
				int i = toInt(line[0]);
				int j = toInt(line[1]);
				adjMatrix[i-1][j-1] = 1;
				adjMatrix[j-1][i-1] = 1;
				TD.getBag(i).addNeighbour(TD.getBag(j));
				TD.getBag(j).addNeighbour(TD.getBag(i));
				line = nextLine(br);
			}
			TD.setAdjMatrix(adjMatrix);
			br.close();
			return TD;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private int toInt(String string) {
		return Integer.parseInt(string);
	}

//	private String[] nextLine(BufferedReader br) {
//		String[] line = null;
//		try {
//			line = br.readLine().trim().split("\\s");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return line;
//	}
	
	private String[] nextLine(BufferedReader br) {
		String[] line = null;
		String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (s == null) {
			return null;
		}
		line = s.trim().split("\\s");
		return line;
	}
	
	private boolean isCommentLine(String[] s) {
		if(s[0].equals("c")) {
			return true;
		} else {
			return false;
		}
	}
	
}
