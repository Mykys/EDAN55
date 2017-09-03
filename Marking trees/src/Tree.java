import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	private List<Node> tree;
	private int rSize;
	private List<Node> list;
	
	
	public Tree  (int height) {
		int rSize = nbrNodes(height);
		for (int i = 0; i < rSize; i++) {
			tree.add(new Node(i+1));
		}
		tree = connectNodes(tree);
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
		return tree.get((n.getNbr()/2) - 1);
	}
	
	public Node findLeftChild(Node n) {
		return tree.get(2*n.getNbr() - 1);
	}
	
	public Node findRightChild(Node n) {
		return tree.get((2*n.getNbr()+1) - 1);
	}
	
	public Node findSibling(Node n) {
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
	
	public void check3(Node n) {
		Randomizer r = new Randomizer();
		List<Node> markedNodes = new ArrayList<Node>();
		if (n.parent != null && n.sibling != null) {
			if (n.parent.isMarked() == true && n.sibling.isMarked() == false) {
				n.sibling.marked = true;
				markedNodes.add(n.sibling);
				check(n.sibling);
			}
			
			if (n.parent.isMarked() == false && n.sibling.isMarked() == true) {
				n.parent.marked = true;
				markedNodes.add(n.parent);
				check(n.parent);
			}
		}
		
		if (n.left != null && n.right != null) {
			if (n.left.isMarked() == true && n.right.isMarked() == false) {
				n.right.marked = true;
				markedNodes.add(n.right);
				check(n.right);
			}
			
			if (n.left.isMarked() == false && n.right.isMarked() == true) {
				n.left.marked = true;
				markedNodes.add(n.left);
				check(n.left);
			}
		}
		Node pickedNode;
		for (int j = 0; j < markedNodes.size(); j++) {
			pickedNode = markedNodes.get(j);
			int ind = list.indexOf(pickedNode);
			Node swap = list.get(j);
			list.set(ind, swap);
			list.set(j, pickedNode);
			rSize--;
		}
	}
	
	public void fill(Node n) {
		n.marked = true;
		check(n);
	}
	
	public void fill3(Node n) {
		n.marked = true;
		rSize--;
		check3(n);
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

}
