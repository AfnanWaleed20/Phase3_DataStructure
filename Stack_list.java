package application;
//this class contians basic method to Stack and queue
public class Stack_list {

	private Onode2 StackNode;
	private Onode qNode;
		public Stack_list() {
			super();

		}

		
		public Object getStackNode() {
			return StackNode.data;
		}

		public void setStackrNode(Onode2 stackrNode) {
			StackNode = stackrNode;
		}

		

		public boolean isEmptyQ() {
			return (qNode == null);
		}
		public void insertFirstQ(AvlNode node) {
			Onode newN = new Onode(node);
			if (qNode == null) {
				qNode = newN;
			} else {
				newN.next = (qNode);
				qNode = newN;
			}
		}

		public AvlNode deleteLastQ() // delete last item
		{
			AvlNode temp;
			Onode current = qNode;
			if (qNode == null)
				return null;

			if (qNode.next == null) {
				temp = qNode.data;
				qNode = null;
				return (temp);
			}

			while (current.next.next != null)
				current = current.next;
			temp = current.next.data;
			current.next=(null);
			
			return temp;
		}

		public void insertFirstS(Martyr data) {
			Onode2 newNode = new Onode2(data);
			if (StackNode == null) {
				StackNode = newNode;
			} else {
				newNode.setNext(StackNode);
				StackNode = newNode;
			}
		}

		public Martyr deleteFirstS() {
			if (StackNode != null) {
				Onode2 temp = StackNode;
				StackNode = StackNode.next;
				return temp.getData();

			} else
				return null;

		}

		public boolean isEmptyS() {
			return (StackNode == null);
		}
}
