
public class MatrixStorer {
	
	private double[][] A;
	private double[][] T; //Risk för fel vid parsing
	private double[] B;
	private int H;
	private int F;
	private int P;
	
	public void setA(double[][] A) {
		this.A = A;
	}
	
	public void setT(double[][] T) {
		this.T = T;
	}
	
	public void setB(double[] B) {
		this.B = B;
	}
	
	public void setH(int H) {
		this.H = H;
	}
	
	public void setF(int F) {
		this.F = F;
	}
	
	public void setP(int P) {
		this.P = P;
	}
	
	public double[][] getA() {
		return A;
	}
	
	public double[][] getT() {
		return T;
	}
	
	public double[] getB() {
		return B;
	}
	
	
	public int getH() {
		return H;
	}
	
	public int getF() {
		return F;
	}
	
	public int getP() {
		return P;
	}
	
	public double[][] getAI() {
		double[][] AI = new double[A.length][A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
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
			for (int j = 0; j < A.length; j++) {
				sb.append(A[i][j]);
				sb.append(" ");
			}
			if (i < (A.length - 1)) {
				sb.append("\n");
				sb.append(" ");
			} else {
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
			for (int j = 0; j < T.length; j++) {
				sb.append(T[i][j]);
				sb.append(" ");
			}
			if (i < (T.length - 1)) {
				sb.append("\n");
				sb.append(" ");
			} else {
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
		
		String Bmatr = sb.toString();
		System.out.println(Bmatr);
	}
}
