import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	private List<Node> tree;
	private int rSize;
	private List<Node> list;
	
	
	public Tree  (int height) {
		tree = new ArrayList<Node>();
		rSize = nbrNodes(height);
		for (int i = 0; i < rSize; i++) {
			tree.add(new Node(i+1));
		}
		tree = connectNodes(tree);
		list = new ArrayList<Node>();
		for (int i = 0; i < rSize; i++) {
			list.add(new Node(i+1));
		}
		list = connectNodes(list);
	}
	
	public int nbrNodes (int height) {
		int h = 1;
		for (int i = 0; i < height; i++) {
			h = h*2;
		}
		return h = h - 1;
	}
	
	public List<Node> connectNodes(List<Node> list) {
		for (int i = 0; i < list.size(); i++) {
			Node n = list.get(i);
			n.parent = findParent(n);
			n.left = findLeftChild(n);
			n.right = findRightChild(n);
			n.sibling = findSibling(n);
		}
		return list;
	}

	
	public Node findParent(Node n) {
		if (n.getNbr() == 1) {
			return null;
		}
		return tree.get((n.getNbr()/2) - 1);
	}
	
	public Node findLeftChild(Node n) {
		if (n.getNbr() > rSize/2) {
			return null;
		}
		return tree.get(2*n.getNbr() - 1);
	}
	
	public Node findRightChild(Node n) {
		if (n.getNbr() > rSize/2) {
			return null;
		}
		return tree.get((2*n.getNbr()+1) - 1);
	}
	
	public Node findSibling(Node n) {
		if (n.getNbr() == 1) {
			return null;
		}
		if (n.getNbr() % 2 == 0) {
			return tree.get((n.getNbr()+1) - 1);
		} else {
			return tree.get((n.getNbr()-1) - 1);
		}
	}
	
	public void check(Node n) {
		if (n.parent != null && n.sibling != null) {
			if (n.parent.isMarked() == true && n.sibling.isMarked() == false) {
				n.sibling.marked = true;
				check(n.sibling);
			}
			
			if (n.parent.isMarked() == false && n.sibling.isMarked() == true) {
				n.parent.marked = true;
				check(n.parent);
			}
		}
		
		if (n.left != null && n.right != null) {
			if (n.left.isMarked() == true && n.right.isMarked() == false) {
				n.right.marked = true;
				check(n.right);
			}
			
			if (n.left.isMarked() == false && n.right.isMarked() == true) {
				n.left.marked = true;
				check(n.left);
			}
		}	
	}
	
	public List<Integer> check3(Node n) {
		Randomizer r = new Randomizer();
		List<Integer> markedNodes = new ArrayList<>();
		List<Integer> addedNodes = new ArrayList<>();
		markedNodes.add(n.getNbr());
		if (n.parent != null && n.sibling != null) {
			if (n.parent.isMarked() == true && n.sibling.isMarked() == false) {
				n.sibling.marked = true;
				markedNodes.add(n.sibling.getNbr());
				addedNodes = check3(n.sibling);
			}
			
			if (n.parent.isMarked() == false && n.sibling.isMarked() == true) {
				n.parent.marked = true;
				markedNodes.add(n.parent.getNbr());
				addedNodes = check3(n.parent);

			}
		}
		
		if (n.left != null && n.right != null) {
			if (n.left.isMarked() == true && n.right.isMarked() == false) {
				n.right.marked = true;
				markedNodes.add(n.right.getNbr());
				addedNodes = check3(n.right);

			}
			
			if (n.left.isMarked() == false && n.right.isMarked() == true) {
				n.left.marked = true;
				markedNodes.add(n.left.getNbr());
				addedNodes = check3(n.left);

			}
		}
		for(int i = 0; i < addedNodes.size(); i++) {
			if(!markedNodes.contains(addedNodes.get(i))) {
				markedNodes.add(addedNodes.get(i));
			}
		}
		return markedNodes;
//		Node pickedNode;
//		for (int j = 0; j < markedNodes.size(); j++) {
//			pickedNode = markedNodes.get(j);
//			int ind = tree.indexOf(pickedNode);
//			
//			Node swap = list.get(j);
//			list.set(ind, swap);
//			list.set(j, pickedNode);
//			rSize--;
//		}
	}
	
	public void fill(Node n) {
		n.marked = true;
		check(n);
	}
	
	public List<Integer> fill3(Node n) {
		n.marked = true;
		rSize--;
		return check3(n);
	}
	
//	public void check(Node n) {
//		if (n.marked != true) {
//			
//		}
//		
//		if ((findParent(n).isMarked() && findSibling(n).isMarked()) == true) {
//			n.marked = true;
//			check(n.left);
//			check(n.right);
//		} else if ((findLeftChild(n).isMarked() && findRightChild(n).isMarked()) == true) {
//			n.marked = true;
//			check(n.parent);
//		}
//	}
	
	public int run() {
		Randomizer rnd = new Randomizer();
		List<Integer> nbrList = new ArrayList<>();
		for (int i = 0; i < tree.size(); i++) {
			nbrList.add(i+1);
		}

		int rndNbr; 
		int attempts = 0;
		boolean isFull = false;
		while(!isFull){
			rndNbr = rnd.randomMethod(nbrList);
			attempts++;
			fill(tree.get(rndNbr-1));
			check(tree.get(rndNbr-1));
			isFull = checkAll(tree);
		}
		if(isFull) {
			//System.out.println("Tree is fully marked.");
		} else {
			System.out.println("Tree is not fully marked yet.");
		}
		return attempts;
	}
	
	public boolean checkAll(List<Node> tree) {
		int count = 0;
		for (int i = 0; i < tree.size(); i++) {
			if (tree.get(i).marked == true) {
				count++;
			}
		}
		if (count == tree.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int run2() {
		Randomizer rnd = new Randomizer();
		List<Integer> nbrList = new ArrayList<>();
		List<Integer> markedNodes = new ArrayList<>();
		for (int i = 0; i < tree.size(); i++) {
			nbrList.add(i+1);
		}

		int rndNbr; 
		int attempts = 0;
		boolean isFull = false;
		while(!isFull){
			nbrList = rnd.randomMethod3(nbrList, markedNodes);
			rndNbr = rnd.randomMethod(nbrList);
			attempts++;
			fill3(tree.get(rndNbr-1));
			isFull = checkAll(tree);
		}
		if(isFull) {
			//System.out.println("Tree is fully marked.");
		} else {
			System.out.println("Tree is not fully marked yet.");
		}
		return attempts;
	}
	
	public int run3() {
		Randomizer rnd = new Randomizer();
		List<Integer> nbrList = new ArrayList<>();
		List<Integer> markedNodes = new ArrayList<>();
		for (int i = 0; i < tree.size(); i++) {
			nbrList.add(i+1);
		}

		int rndNbr; 
		int attempts = 0;
		boolean isFull = false;
		rndNbr = rnd.randomMethod(nbrList);
		attempts++;
		while(!isFull){
			markedNodes = fill3(tree.get(rndNbr-1));
			nbrList = rnd.randomMethod3(nbrList, markedNodes);
			if (nbrList.size() > 0) {
				rndNbr = rnd.randomMethod(nbrList);
				attempts++;
			}
			isFull = checkAll(tree);
		}
		if(isFull) {
			//System.out.println("Tree is fully marked.");
		} else {
			System.out.println("Tree is not fully marked yet.");
		}
		return attempts;	
	}
}
