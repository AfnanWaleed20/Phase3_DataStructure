package application;
//this Class implemintion to AVL Node Cotians A basic operation(left.right);
public class AvlNode {
	Martyr target;
	AvlNode left,right;
	int height;

	public AvlNode(Martyr key) {
		this(key, null, null);
	}

	public AvlNode(Martyr target, AvlNode left, AvlNode right) {
		this.target = target;
		this.left = left;
		this.right = right;
		this.height = 0;
	}
	 public boolean isLeaf() {
	        return left == null && right == null;
	    }

	@Override
	public String toString() {
		return "AvlNode [target=" + target + "]";
	}
	

}


