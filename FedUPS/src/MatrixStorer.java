
public class MatrixStorer {
	
	private int[][] A;
	private int[][] T;
	private int[] B;
	
	public void setA(int[][] A) {
		this.A = A;
	}
	
	public void setT(int[][] T) {
		this.T = T;
	}
	
	public void setB(int[] B) {
		this.B = B;
	}
	
	public int[][] getA() {
		return A;
	}
	
	public int[][] getT() {
		return T;
	}
	
	public int[] getB() {
		return B;
	}
	
	public int[][] getAI() {
		int[][] AI = new int[A.length][A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; i < A.length; j++) {
				AI[i][j] = A[i][j];
				if(i==j) {
					AI[i][j]--;
				}
			}
		}
		return AI;
	}
	
	public void printA() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; i < A.length; j++) {
				sb.append(A[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
			sb.append(" ");
			if(i==A.length) {
				sb.append("]");
			}
		}
		String Amatr = sb.toString();
		System.out.println(Amatr);
	}
	
	public void printT() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < T.length; i++) {
			for (int j = 0; i < T.length; j++) {
				sb.append(T[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
			sb.append(" ");
			if(i==T.length) {
				sb.append("]");
			}
		}
		String Tmatr = sb.toString();
		System.out.println(Tmatr);
	}
	
	public void printB() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < B.length; i++) {
			sb.append(B[i]);
			sb.append(" ");
		}
		sb.append("]");
	}
	
}
