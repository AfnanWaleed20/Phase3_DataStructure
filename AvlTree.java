package application;

import java.io.BufferedWriter;
import java.io.IOException;
//this Class implemintion to AVL One Cotians A basic Method
public class AvlTree {
	private AvlNode root;

	public AvlTree() {
		root = null;
	}

	public AvlNode getRoot() {
		return root;
	}

	public void setRoot(AvlNode root) {
		this.root = root;
	}

	public int height() {
		return getHeight(root);
	}

	private int getHeight(AvlNode node) {
		if (node == null) {
			return -1;
		}
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public void insertNode(Martyr martyr) {
		root = insertNode(root, martyr);
	}

	private AvlNode insertNode(AvlNode node, Martyr key) {
		if (node == null) {
			return new AvlNode(key);
		}

		if (key.getName().compareTo(node.target.getName()) < 0) {
			node.left = insertNode(node.left, key);
		} else if (key.getName().compareTo(node.target.getName()) > 0) {
			node.right = insertNode(node.right, key);
		} else {
			
			return node;
		}

		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

		int balance = getBalance(node);

		if (balance > 1 && key.getName().compareTo(node.left.target.getName()) < 0) {
			return rotateRight(node);
		}

		if (balance < -1 && key.getName().compareTo(node.right.target.getName()) > 0) {
			return rotateLeft(node);
		}

		if (balance > 1 && key.getName().compareTo(node.left.target.getName()) > 0) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}

		if (balance < -1 && key.getName().compareTo(node.right.target.getName()) < 0) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}

		return node;
	}

	private int getBalance(AvlNode node) {
		if (node == null) {
			return 0;
		}

		return getHeight(node.left) - getHeight(node.right);
	}

	private AvlNode rotateRight(AvlNode k1) {
		AvlNode k2 = k1.left;
		k1.left = k2.right;
		k2.right = k1;

		k1.height = 1 + Math.max(getHeight(k1.left), getHeight(k1.right));
		k2.height = 1 + Math.max(getHeight(k2.left), getHeight(k2.right));

		return k2;
	}

	private AvlNode rotateLeft(AvlNode k2) {
		AvlNode k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;

		k2.height = 1 + Math.max(getHeight(k2.left), getHeight(k2.right));
		k1.height = 1 + Math.max(getHeight(k1.left), getHeight(k1.right));

		return k1;
	}

	public AvlNode searchbyName(String key) {
		AvlNode node = searchNode(root, key);
		if (node != null) {
			return searchNode(root, key);
		}
		return null;
	}

	private AvlNode searchNode(AvlNode node, String key) {
		if (node == null || node.target.getName().trim().equalsIgnoreCase(key.trim())) {
			return node;
		}

		if (key.trim().compareToIgnoreCase(node.target.getName().trim()) < 0) {
			return searchNode(node.left, key);
		} else {
			return searchNode(node.right, key);
		}
	}
	

	public String printTree() {
		StringBuilder sb = new StringBuilder();
		inOrderTraversal(root, sb);
		return sb.toString();
	}

	private void inOrderTraversal(AvlNode node, StringBuilder sb) {
		if (node != null) {
			inOrderTraversal(node.left, sb);
			sb.append(node.target).append(" ");
			inOrderTraversal(node.right, sb);
		}
	}

	public void printTree2() {
		inOrderTraversal(root);
		System.out.println("\n");
	}

	private void inOrderTraversal(AvlNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.print(node.target.toString() + "," + node.height + "/");
			inOrderTraversal(node.right);
		}
	}

	
		
		public boolean deleteAvlNode(Martyr mart) {
		    root = remove(root, mart);
		    return true;
		}

		private AvlNode remove(AvlNode node, Martyr mart) {
		    if (node == null) {
		        return null;
		    }

		    if (mart.getName().trim().compareToIgnoreCase(node.target.getName().trim()) < 0) {
		        node.left = remove(node.left, mart);
		    } else if (mart.getName().trim().compareToIgnoreCase(node.target.getName().trim()) > 0) {
		        node.right = remove(node.right, mart);
		    } else {
		        if (node.target.equals(mart)) {
		            if (node.isLeaf()) {
		                return null;
		            } else if (node.left == null) {
		                return node.right;
		            } else if (node.right == null) {
		                return node.left;
		            } else {
		                AvlNode smallest = findSmallest(node.right);
		                node.target = smallest.target;
		                node.right = remove(node.right, smallest.target);
		            }
		        } else {
		            if (mart.getName().trim().compareToIgnoreCase(node.target.getName().trim()) < 0) {
		                node.left = remove(node.left, mart);
		            } else {
		                node.right = remove(node.right, mart);
		            }
		        }
		    }

		    node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

		    int balance = getBalance(node);

		    if (balance > 1 && mart.getName().compareTo(node.left.target.getName()) < 0) {
		        return rotateRight(node);
		    }

		    if (balance < -1 && mart.getName().compareTo(node.right.target.getName()) > 0) {
		        return rotateLeft(node);
		    }

		    if (balance > 1 && mart.getName().compareTo(node.left.target.getName()) > 0) {
		        node.left = rotateLeft(node.left);
		        return rotateRight(node);
		    }

		    if (balance < -1 && mart.getName().compareTo(node.right.target.getName()) < 0) {
		        node.right = rotateRight(node.right);
		        return rotateLeft(node);
		    }

		    return node;
		}

		private AvlNode findSmallest(AvlNode node) {
		    AvlNode current = node;
		    while (current.left != null) {
		        current = current.left;
		    }
		    return current;
		
	
	}

	private AvlNode findsmallest(AvlNode node) {
		AvlNode current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	public boolean update(Martyr oldKey, Martyr newKey) {

		AvlNode node = search(root, oldKey);

		if (node != null) {

			root = remove(root, oldKey);

			root = insertNode(root, newKey);
			return true;
		} else if (node == null)
			return false;
		return false;
	}

	public AvlNode search(Martyr key) {

		return search(root, key);
	}

	private AvlNode search(AvlNode node, Martyr key) {
		if (node == null || node.target.getName().compareToIgnoreCase(key.getName()) == 0
				&& node.target.getAge() == key.getAge() 
				&& node.target.getDateOfDeath().compareTo(key.getDateOfDeath()) == 0 &&node.target.getGender() == key.getGender()) {
			return node;
		}
		if (node.target.getName().compareToIgnoreCase(key.getName()) < 0) {
			return search(node.left, key);
		} else {
			return search(node.right, key);
		}
	}
	  public void printAvlTree(BufferedWriter writer) throws IOException {
	    	 printAvlTree(root, writer);
	    }
	    private void printAvlTree(AvlNode node, BufferedWriter writer) throws IOException {
	        if (node != null) {
	            printAvlTree(node.left, writer);
	            Martyr martyr=node.target;
	            writer.write(martyr.getName()+","+martyr.getAge()+","+martyr.getLocation2().toString()+","
	            		+martyr.getDateOfDeath()+","+martyr.getGender());
	            writer.newLine();
	            printAvlTree(node.right, writer);
	        }
	    
	    }
	
}
