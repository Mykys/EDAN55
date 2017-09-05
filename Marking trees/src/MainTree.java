
public class MainTree {

	public static void main(String[] args) {
		for (int i = 2; i < 5; i++) {
			Tree tree = new Tree(i);
			int attempts = tree.run2();
			System.out.println("Height " + i + ": " + attempts);
		}
		
	}

}
