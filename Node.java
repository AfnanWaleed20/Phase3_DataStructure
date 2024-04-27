package application;

//this class is node of Doubly circular linked list
public class Node {
Location element;
Node next,prev;
public Node(Object x) {
element= (Location)x;
prev = null;
next = null;
}

public Node() {
	super();
	
}

/*public AvlTree getAvl1() {
	if (avl1==null) {
		return new AvlTree();
		}
	else
	return avl1;
}

public void setAvl1(AvlTree avl1) {
	this.avl1 = avl1;
}
*/


@Override
public String toString() {
	return "Node [element=" + element ;
}



}
