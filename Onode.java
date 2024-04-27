package application;
//this class is a node of queue
public class Onode {
AvlNode data;
Onode next;
public Onode() {
	super();
	// TODO Auto-generated constructor stub
}

public Onode(AvlNode data) {
	super();
	this.data = data;
}

@Override
public String toString() {
	return "Onode [data=" + data + "]";
}

}
