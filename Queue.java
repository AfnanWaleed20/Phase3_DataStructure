package application;
//this class in implemention to queue with basic operation
public class Queue {
	Stack_list list2 = new Stack_list();

	public Queue() {

	}
	
	

	public boolean isEmpty()
	{
		return (list2.isEmptyQ());
	}
	
	
	public void enqueue (AvlNode node) {
		list2.insertFirstQ(node);
	}
	
	public AvlNode dequeue() {
		return list2.deleteLastQ();
	}



	public boolean isEmpty2()

	{
		return (list2.isEmptyQ());
	}
}


