package application;

//this class is node of stack

public class Onode2 {
		Martyr data;
	    Onode2 next;
	   
	    
	public Onode2() {
			super();

		}


	public Onode2(Martyr x) {
	data=x;
	}


	public Martyr getData() {
		return data;
	}


	public void setData(Martyr element) {
		this.data= data;
	}


	@Override
	public String toString() {
		return "Node [element=" + data + ", next=" + next + "]";
	}


	public Onode2 getNext() {
		return next;
	}


	public void setNext(Onode2 next) {
		this.next = next;
	}
}
