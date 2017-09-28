import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// String filename = "C:/Users/Myky/Documents/EDAN55/FedUPS/toy.txt";
		int tries = 10000;
		String filename;
		filename = "small";
		Main.sovleFile(filename, tries);
		System.out.println("\n");
		filename = "toy";
		Main.sovleFile(filename, tries);
		System.out.println("\n");
		filename = "rnd1";
		Main.sovleFile(filename, tries);
		System.out.println("\n");
		filename = "rnd2";
		Main.sovleFile(filename, tries);
		System.out.println("\n");
		filename = "rnd3";
		Main.sovleFile(filename, tries);
		System.out.println("\n");
		filename = "strange2";
		Main.sovleFile(filename, tries);
		System.out.println("\n");
		filename = "strange1";
		Main.sovleFile(filename, tries);

		// markov
		// parse code
		// create A matrix and T matrix while parsing
		// compute B vector
		// print A and B, maybe A-I instead of A
		// solve in matlab

		// monte carlo
		// use A to jump between rows of the A matrix

	}

	public static void sovleFile(String filename, int tries) {
		StringBuilder sb = new StringBuilder();
		sb.append("C:/Users/Shintai/Desktop/Skola/edan55/FedUPS/");
		sb.append(filename);
		sb.append(".txt");
		int N = tries;
		MatrixStorer ms = parseCode(sb.toString());
		int f = ms.getF();
		int p = ms.getP();
		List<Integer> tryPoints = endReachable(ms.getH(), ms.getF(), ms.getP(), ms.getA());
		System.out.println(filename + ":");

		// Monte-Carlo
		List<Double> FList = new ArrayList<>();
		List<Double> PList = new ArrayList<>();
		double totWeightF = 0;
		double totWeightP = 0;

		for (int i = 0; i < N; i++) {
			if (tryPoints.contains(f)) {
				totWeightF = MonteCarlo(ms.getF(), ms.getH(), ms.getA(), ms.getT());
				FList.add(totWeightF);
			}
			if (tryPoints.contains(p)) {
				totWeightP = MonteCarlo(ms.getP(), ms.getH(), ms.getA(), ms.getT());
				PList.add(totWeightP);
			}
		}

		double avgF = Double.MAX_VALUE;
		double avgP = Double.MAX_VALUE;

		if (!FList.isEmpty()) {
			double sumF = 0;
			for (Double d : FList) {
				sumF = sumF + d;
			}
			avgF = sumF / N;
		}

		if (!PList.isEmpty()) {
			double sumP = 0;
			for (Double d : PList) {
				sumP = sumP + d;
			}
			avgP = sumP / N;
		}

		System.out.println("Average delivery time for F and P are: " + avgF + " & " + avgP + " (Monte Carlo)");

		
		// Markov
		// System.out.println("\n");
		// ms.printA();
		// System.out.println("\n");
		// ms.printB();
		// System.out.println("\n");
		// ms.printT();
		int ftemp = f;
		int ptemp = p;
		boolean fExist = tryPoints.contains(ftemp);
		boolean pExist = tryPoints.contains(ptemp);
		double[] sol;
		try {
			sol = GaussianElimination.lsolve(ms.getAI(), ms.getB());
			if (fExist && pExist) {
				System.out.println("FedUps: " + -sol[f] + ",\t PostNHL: " + -sol[p]);
			} else if (fExist && !pExist) {
				System.out.println(
						"FedUps: " + -sol[f] + ", \t You weren't at home when PostNHL tried to deliver your package.");
			} else if (!fExist && pExist) {
				System.out.println(
						"PostNHL: " + -sol[p] + ", \t You weren't at home when FedUPS tried to deliver your package.");
			} else {
				System.out.println("You weren't at home when either FedUPS or PostNHL tried to deliver your package.");
			}
		} catch (ArithmeticException e) {
			double[][] Amod = subMatrix(ms.getA(), tryPoints);
			double[] Bmod = subVector(ms.getB(), tryPoints);
			ArrayList<Integer> removedPoints = new ArrayList<>();
			for (int i = 0; i < ms.getA().length; i++) {
				if (!tryPoints.contains(i)) {
					removedPoints.add(i);
				}
			}
			for (int k : removedPoints) {
				if (k < ftemp) {
					f--;
				}
				if (k < ptemp) {
					p--;
				}
			}
			sol = GaussianElimination.lsolve(ms.getAI(Amod), Bmod);
			if (fExist && pExist) {
				System.out.println("FedUps: " + -sol[f] + ",\t PostNHL: " + -sol[p]);
			} else if (fExist && !pExist) {
				System.out.println(
						"FedUps: " + -sol[f] + ", \t You weren't at home when PostNHL tried to deliver your package.");
			} else if (!fExist && pExist) {
				System.out.println(
						"PostNHL: " + -sol[p] + ", \t You weren't at home when FedUPS tried to deliver your package.");
			} else {
				System.out.println("You weren't at home when either FedUPS or PostNHL tried to deliver your package.");
			}
		}


	}

	public static double[][] subMatrix(double[][] adjMatrix, List<Integer> connectedList) {
		int sub = connectedList.size();
		int k = adjMatrix.length;
		double[][] subMatrix = new double[sub][sub];
		int a = 0;
		int b = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if (connectedList.contains(i) && connectedList.contains(j)) {
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

	public static double[] subVector(double[] adjMatrix, List<Integer> connectedList) {
		int sub = connectedList.size();
		int k = adjMatrix.length;
		double[] subMatrix = new double[sub];
		int a = 0;
		for (int i = 0; i < k; i++) {
			if (connectedList.contains(i)) {
				if (a < sub) {
					subMatrix[a] = adjMatrix[i];
					a++;
				}
			}
		}
		return subMatrix;
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

			// Fill with -1, problems will appear when calculating
			// for (int i = 0; i < A.length; i++) {
			// b[i] = -1;
			// for (int j = 0; j < A.length; j++) {
			// A[i][j] = -1;
			// T[i][j] = -1;
			// }
			// }

			int u, v, t;
			double p1, p2;
			for (int i = 0; i < M; i++) {
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
					sum += A[i][j] * T[j][i];
				}
				b[i] = sum;
			}
			ms.setA(A);
			ms.setT(T);
			ms.setB(b);
			ms.setH(H);
			ms.setF(F);
			ms.setP(P);
			br.close();
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

	public static double MonteCarlo(int startRow, int homeRow, double A[][], double T[][]) {
		List<Double> weight = new ArrayList<>();
		Random rnd = new Random();
		int attempts = 0;

		while (startRow != homeRow && attempts < 1000000) {
			attempts++;
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

	public static List<Integer> endReachable(int home, int F, int P, double[][] A) {
		List<Integer> connected = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		connected.add(home);
		int currSize = 1;
		int preSize = 0;

		for (int i = 0; i < A.length; i++) {
			if (A[i][home] != 0) {
				connected.add(i);
			}
		}
		preSize = currSize;
		currSize = connected.size();

		while (currSize > preSize) {
			for (Integer i : connected) {
				for (int j = 0; j < A.length; j++) {
					if (A[j][i] != 0 && !connected.contains(j)) {
						temp.add(j);
					}
				}
			}
			connected.addAll(temp);
			temp.clear();
			preSize = currSize;
			currSize = connected.size();
		}

		return connected;
	}

}
