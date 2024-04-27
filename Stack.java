package application;


//this class implemaintion to Stack 
public class Stack {
	private int count;
	private Stack_list list3= new Stack_list();

	public void push(Martyr martyr)
	{
		list3.insertFirstS(martyr);
		count++;
		
	}
	
	
	public Martyr pop()
	{
		return (list3.deleteFirstS());
	}
	public boolean isEmpty() {
		return (list3.isEmptyS());
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public Object peek() {
		
		return list3.getStackNode();
	}

}

