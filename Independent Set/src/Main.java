
public class Main {

	public static void main(String[] args) {
		String filename = "asdf";
		int[][] adjMatrix = readFile(filename); //also number of nodes, given as length
		int MIS = run(adjMatrix);
		print(MIS);

	}

	public static int run(int[][] adjMatrix) {
		int MIS = 0;
		MIS = algR0(adjMatrix);
		return MIS;
	}
	
	private static int algR0(int[][] adjMatrix) {
		if(adjMatrix == null) {
			return 0;
		} else if() {
			//steg 2
		} else {
			//steg 3
			int altMax = 0;
			//things happen here: find node to add, prep.work such as "flip()"?
			//add ignoreList, might add as in.param for algR0
			int alt1 = algR0(adjMatrix);
			//undo prep.work such as "flip()", do new prep.work
			int alt2 = algR0(adjMatrix);
			//undo prep.work
			if(alt1 > alt2) {
				altMax = alt1;
			} else {
				altMax = alt2;
			}
		}
			
	}
	
	public static void print(int MIS) {
		System.out.println(MIS);
	}
	
	public int[][] readFile(String filename) {
		return null;
		
	}
	
}
