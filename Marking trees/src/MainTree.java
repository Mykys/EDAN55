import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class MainTree {

	public static void main(String[] args) {
//		for (int i = 2; i < 20; i++) {
//			Tree tree = new Tree(i);
//			int attempts = tree.run();
//			System.out.println("Height " + i + ": " + attempts);
//		}
//		
		ArrayList<Float> nbr = new ArrayList<>();
		float meanVal = 0;
		float n = 1000;
		for (int i = 0; i < n; i++) {
			Tree tree = new Tree(10);
			float attempts = tree.run3();
			System.out.println(attempts);
			nbr.add(attempts);
			meanVal = meanVal + attempts;
		}
		meanVal = meanVal/n;
		System.out.println(meanVal);
		 
		float devVal = 0;
//		double diffVal = 0;
		float multVal = 0;
		for (int i = 0; i < n; i++) {
			float diffVal = nbr.get(i) - meanVal;
			multVal = diffVal*diffVal;
			devVal = devVal + multVal;
		}
		devVal = (1/(n-1))*devVal;
		System.out.println(Math.sqrt(devVal));
			
	}

}
