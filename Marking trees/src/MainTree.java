
public class MainTree {

	public static void main(String[] args) {
		for (int i = 2; i < 10; i++) {
			Tree tree = new Tree(i);
			int attempts = tree.run3();
			System.out.println("Height " + i + ": " + attempts);
		}
		
	}

}
