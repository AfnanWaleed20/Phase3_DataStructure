package application;
// this is implemiton of  DoubleCircularLinkedList with basic Methods
public class DoubleCircularLinkedList {
	private Node first;

	public DoubleCircularLinkedList() {
		super();

	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public void insertAndSort(Location data) {
		Node newNode = new Node(data);

		if (first == null) {
			first = newNode;
			first.prev = newNode;
			first.next = newNode;
			return;
		}

		Node current = first;
		while (current.next != first && current.next.element.getLocation().trim().compareTo(data.getLocation().trim()) > 0) {
			current = current.next;
		}

		newNode.prev = current;
		newNode.next = current.next;
		current.next.prev = newNode;
		current.next = newNode;

		if (current == first && data.getLocation().compareTo(first.element.getLocation()) < 0) {
			first = newNode;
		}

	}

	public Node search(Location data) {
		if (first == null) {
			return null;
		}

		Node current = first;
		while (true) {
			if (current.element.getLocation().trim().equals(data.getLocation().trim())) {
				return current;
			}

			current = current.next;
			if (current == first) {
				break;
			}
		}

		return null;
	}

	/*public Node search(Location data) {
	    if (first == null) {
	        return null;
	    }

	    Node current = first;
	    do {
	        if (current.element != null && current.element.getLocation() != null &&
	                data.getLocation() != null && current.element.getLocation().trim().equals(data.getLocation().trim())) {
	            return current;
	        }
	        current = current.next;
	    } while (current != first);

	    return null;
	}*/


	

	public void update(String oldPoint, String newPoint) {
		Node current = first;
		while (current != null) {
			if (current.element.getLocation().equalsIgnoreCase(oldPoint)) {
				current.element.setLocation(newPoint);

			}
			current = current.next;
		}
	}

	public void printList() {
		if (first == null) {
			System.out.println("The list is empty");
		}

		Node current = first;
		while (current != null) {
			System.out.print(current.element + " ");
			if (current.next == first) {
				break;
			}
			current = current.next;
		}

		System.out.println();
	}

	public boolean remove(Location value) {
		if (first == null) {
			return false;
		}

		else if (first.element.getLocation().equals(value.getLocation())) {
			first = first.next;
			return true;
		} else {
			Node current = first;
			while (current.next != null) {
				if (current.next.element.getLocation().equals(value.getLocation())) {
					current.next = current.next.next;
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}
	
	    public void sort() {
	        if (first == null) {
	            return; // Empty list, nothing to sort
	        }

	        Node current = first;
	        do {
	            Node minValueNode = findMinValueNode(current);
	            swapData(current, minValueNode);
	            current = current.next;
	        } while (current != first);
	    }

	    private Node findMinValueNode(Node startNode) {
	        Node minNode = startNode;
	        Node current = startNode.next;
	        while (current != startNode) {
	            if ((current.element.getLocation()).compareTo(minNode.element.getLocation()) < 0) {
	                minNode = current;
	            }
	            current = current.next;
	        }
	        return minNode;
	    }

	    private void swapData(Node node1, Node node2) {
	        Location temp = node1.element;
	        node1.element = node2.element;
	        node2.element = temp;
	    }

	}


