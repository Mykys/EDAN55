import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
//		String filename = "C:/Users/Myky/Documents/EDAN55/FedUPS/toy.txt";
		String filename = "C:/Users/Shintai/Desktop/Skola/edan55/FedUPS/toy.txt";
		int N = 10000;
		MatrixStorer ms = parseCode(filename);
		
		//Markov
		ms.printA();
		System.out.println("\n");
		ms.printB();
		System.out.println("\n");
		ms.printT();
		double[] sol = GaussianElimination.lsolve(ms.getAI(), ms.getB());
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < sol.length; i++) {
			sb.append(sol[i]);
			sb.append(" ");
		}
		sb.append("]");
		
		String Bmatr = sb.toString();
		System.out.println(Bmatr);
		
		//Monte-Carlo
		List<Double> FList = new ArrayList<>();
		List<Double> PList = new ArrayList<>();
		double totWeightF = 0;
		double totWeightP = 0;
//		if (endReachable(ms.getF(), ms.getH(), ms.getP(), ms.getA())) {
			
			for (int i = 0; i < N; i++) {
				totWeightF = MonteCarlo(ms.getF(), ms.getH(), ms.getA(), ms.getT());
				totWeightP = MonteCarlo(ms.getP(), ms.getH(), ms.getA(), ms.getT());
				FList.add(totWeightF);
				PList.add(totWeightP);
			}
//		}
		
		double sumF = 0;
		for (Double d : FList) {
			sumF = sumF + d;
		}
		
		double sumP = 0;
		for (Double d : PList) {
			sumP = sumP + d;
		}
		
		double avgF = sumF/N;
		double avgP = sumP/N;
		
		System.out.println("Average delivery time for F and P are: " + avgF + " & " + avgP);
		
	//markov
		//parse code
		//create A matrix and T matrix while parsing
		//compute B vector
		//print A and B, maybe A-I instead of A
		//solve in matlab
		
	//monte carlo
		//use A to jump between rows of the A matrix
		

	}
	
	public static MatrixStorer parseCode(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			MatrixStorer ms = new MatrixStorer();
			String[] info = br.readLine().trim().split(" ");
			int N = Integer.parseInt(info[0]);
			int M = Integer.parseInt(info[1]);
			int H = Integer.parseInt(info[2]);
			int F = Integer.parseInt(info[3]);
			int P = Integer.parseInt(info[4]);
			double[][] A = new double[N][N];
			double[][] T = new double[N][N];
			double[] b = new double[N];
			
			//Fill with -1, problems will appear when calculating
//			for (int i = 0; i < A.length; i++) {
//				b[i] = -1;
//				for (int j = 0; j < A.length; j++) {
//					A[i][j] = -1;
//					T[i][j] = -1;
//				}
//			}
			
			int u, v, t;
			double p1, p2;
			for(int i = 0; i < M; i++) {
				info = br.readLine().trim().split(" ");
				u = Integer.parseInt(info[0]);
				v = Integer.parseInt(info[1]);
				t = Integer.parseInt(info[2]);
				p1 = Double.parseDouble(info[3]);
				p2 = Double.parseDouble(info[4]);
				A[u][v] = p1;
				A[v][u] = p2;
				T[u][v] = t;
				T[v][u] = t;
			}
			for (int i = 0; i < N; i++) {
				double sum = 0;
				for (int j = 0; j < N; j++) {
					sum += A[i][j]*T[j][i];
				}
				b[i] = sum;
			}
			ms.setA(A);
			ms.setT(T);
			ms.setB(b);
			ms.setH(H);
			ms.setF(F);
			ms.setP(P);
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
	
	public static double MonteCarlo (int startRow, int homeRow, double A[][], double T[][]) {
		List<Double> weight = new ArrayList<>();
		Random rnd = new Random();
		
		while(startRow != homeRow) {
			double rndNbr = rnd.nextDouble();
			double sum = 0;
			int i = 0;
			if (A[startRow][i] != 0) {
				sum = sum + A[startRow][i];
			}
			while (rndNbr > sum) {
				if (i < (A.length - 1)) {
					i++;
				} else {
					break;
				}	
				if (A[startRow][i] != 0) {
					sum = sum + A[startRow][i];
				}
				
			}
			weight.add(T[startRow][i]);
			startRow = i;
		}
		double totalWeight = 0;
		for (Double d : weight) {
			totalWeight = totalWeight + d;
		}
		
		return totalWeight;
	}
	
	public static boolean endReachable (int home, int F, int P, double A[][]) {
		List<Integer> connected = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		connected.add(home);
		int currSize = 1;
		int preSize = 0;
		
		for (int i = 0; i < A.length; i++) {
			if (A[i][home] != 0) {
				connected.add(i);
			}
			preSize = currSize;
			currSize = connected.size();
		}
		
		while (currSize > preSize) {
			if (connected.contains(F) && connected.contains(P)) {
				return true;
			}
			
			for (Integer i : connected) {
				for (int j = 0; j < A.length; j++) {
					if (A[j][i] != 0 && !connected.contains(j)) {
						temp.add(j);
					}
				}
			}
			connected.addAll(temp);
			preSize = currSize;
			currSize = connected.size();
		}
		
		return false;
	}

}

