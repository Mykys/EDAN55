import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
	//markov
		//parse code
		//create A matrix and T matrix while parsing
		//compute B vector
		//print A and B, maybe A-I instead of A
		//solve in matlab
		
	//monte carlo
		//use A to jump between rows of the A matrix
		

	}
	
	public MatrixStorer parseCode(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			MatrixStorer ms = new MatrixStorer();
			String[] info = br.readLine().trim().split(" ");
			int N = Integer.parseInt(info[0]);
			int M = Integer.parseInt(info[1]);
			int H = Integer.parseInt(info[2]);
			int F = Integer.parseInt(info[3]);
			int P = Integer.parseInt(info[4]);
			int[][] A = new int[N][N];
			int[][] T = new int[N][N];
			int[] b = new int[N];
			int u, v, t, p1, p2;
			for(int i = 0; i < M; i++) {
				info = br.readLine().trim().split(" ");
				u = Integer.parseInt(info[0]);
				v = Integer.parseInt(info[1]);
				t = Integer.parseInt(info[2]);
				p1 = Integer.parseInt(info[3]);
				p2 = Integer.parseInt(info[4]);
				A[u][v] = p1;
				A[v][u] = p2;
				T[u][v] = t;
				T[v][u] = t;
			}
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += A[i][j]*T[j][i];
				}
				b[i] = sum;
			}
			ms.setA(A);
			ms.setT(T);
			ms.setB(b);
			return ms;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in parsing?");
		return null;
	}

}

