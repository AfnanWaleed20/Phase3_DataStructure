package application;

import java.util.Date;
//implemention of AVL Two with basic and uses method
public class AvlTreeTwo {
	private NodeAvl2 root;

	public AvlTreeTwo() {
		root=null;
	}
	public int height() {
		return getHeight(root);
	}
	public int getHeight(NodeAvl2 root2) {
	    if (root2 == null) {
	        return -1; 
	    }
	    
	    int leftHeight = getHeight(root2.left);
	    int rightHeight = getHeight(root2.right);
	    
	    return Math.max(leftHeight, rightHeight) + 1;
	}
	
public void insertNode(Date_Stack key) {
	root=insertNode(root, key);
	System.out.println("ko");
}
    private NodeAvl2 insertNode(NodeAvl2 node, Date_Stack key) {
        if (node == null) {
            return new NodeAvl2(key);
        }

        if (key.getDate().compareTo(node.target.date)<0){
            node.left = insertNode(node.left, key);
        } else if (key.getDate().compareTo(node.target.date)>0) {
            node.right = insertNode(node.right, key);
        } else {
    
            return node;
        }
 node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        
        int balance = getBalance(node);

        if (balance > 1 && key.date.compareTo(node.left.target.date)<0){
            return rotateRight(node);
        }

        if (balance < -1 &&  key.date.compareTo(node.right.target.date)>0) {
            return rotateLeft(node);
           
        }

        if (balance > 1 && key.date.compareTo(node.left.target.date)>0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key.date.compareTo(node.right.target.date)<0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

   

    private int getBalance(NodeAvl2 node) {
        if (node == null) {
            return 0;
        }
        
        return getHeight(node.left) - getHeight(node.right);
    }

    private NodeAvl2 rotateRight(NodeAvl2 k1) {
    	 NodeAvl2 k2 = k1.left;
	        k1.left = k2.right;
	        k2.right = k1;

	        k1.height = 1 + Math.max(getHeight(k1.left), getHeight(k1.right));
	        k2.height = 1 + Math.max(getHeight(k2.left), getHeight(k2.right));

	        return k2;
     }
    

    private NodeAvl2 rotateLeft(NodeAvl2 k2) {
    	NodeAvl2 k1 = k2.right;
	        k2.right = k1.left;
	        k1.left = k2;

	        k2.height = 1 + Math.max(getHeight(k2.left), getHeight(k2.right));
	        k1.height = 1 + Math.max(getHeight(k1.left), getHeight(k1.right));

	        return k1;
         }
    
    public void updateNode(Date date, Martyr oldMartyr, Martyr newMartyr) {
	    NodeAvl2 targetNode = search(new Date_Stack(date));

	    if (targetNode != null) {
	        Stack martyrsStack = targetNode.stack;

	     
	        Stack tempStack = new Stack();

	      
	        while (!martyrsStack.isEmpty()) {
	            Martyr currentMartyr = martyrsStack.pop();

	            if (currentMartyr.equals(oldMartyr)) {
	               
	                tempStack.push(newMartyr);
	            } else {
	                tempStack.push(currentMartyr);
	            }
	        }

	        
	        while (!tempStack.isEmpty()) {
	            martyrsStack.push(tempStack.pop());
	        }
	    }
	}      
    
 
    public NodeAvl2 search(Date_Stack key) {
    	NodeAvl2 node=searchNode(root, key);
    	if (node!=null) {
        return node;
    }
    	return null;
    }
    private NodeAvl2 searchNode(NodeAvl2 node, Date_Stack key) {
        if (node == null || node.target.date.compareTo(key.getDate())==0) {
            return node;
        }

        if (key.date.compareTo(node.target.date)<0) {
            return searchNode(node.left, key);
        } else {
            return searchNode(node.right, key);
        }
    }
    
    public void printTree3() {
		inOrderTraversal2(root);
		System.out.println();
	}

	private void inOrderTraversal2(NodeAvl2  node) {
		if (node != null) {
			inOrderTraversal2(node.left);
			System.out.print(node.target + " ");
			//inOrderTraversal2(node.left);
			inOrderTraversal2(node.right);
		}
	}
    public String printTree() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString();
    }

    private void inOrderTraversal( NodeAvl2  node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.target).append(" ");
            inOrderTraversal(node.right, sb);
        }
    }
    public StringBuilder stackinorder() {
    	StringBuilder string=new StringBuilder();
    	return inOrder(root, string);
    }
    public StringBuilder inOrder(NodeAvl2 node, StringBuilder string) {
        Stack s = new Stack();
        if (node != null) {
            inOrder(node.left, string);
            
            while (!node.stack.isEmpty()) {
                Martyr martyr = node.stack.pop();
                string.append(martyr.toString()).append("\n");
                s.push(martyr);
            }
            
            while (!s.isEmpty()) {
                node.stack.push(s.pop());
            }
            
            inOrder(node.right, string);
        }
        return string;
    }
  






	/*public Date findMaxMartyrsDate() {
	    if (root == null) {
	        return null; // Tree is empty
	    }

	    int maxCount = 0;
	    Date maxDate = null;

	    NodeAvl2 current = root;

	    while (!current.stack().isEmpty()|| current != null) {
	        if (current != null) {
	        	current.stack().push(current.stack.peek());
	            current = current.left;
	        } else {
	            Martyr martyr = current.stack().pop();
	            int count = 1;
	            while (!current.stack().isEmpty() && current.stack().getTop().getDateOfDeath().equals(martyr.getDateOfDeath())) {
	                stack.pop();
	                count++;
	            }

	            if (count > maxCount) {
	                maxCount = count;
	                maxDate = martyr.getDateOfDeath();
	            }

	            current = current.right;
	        }
	    }

	    return maxDate;
	}*/
	
	private Date getDate(NodeAvl2 node, Date maxDate, int maxMartyrs) {
	    if (node == null) {
	        return maxDate;
	    }

	    int numMartyrs = node.stack.getCount();
	    if (numMartyrs > maxMartyrs) {
	        maxMartyrs = numMartyrs;
	        maxDate = node.target.getDate();
	    }

	    Date leftMaxDate = getDate(node.left, maxDate, maxMartyrs);
	    Date rightMaxDate = getDate(node.right, maxDate, maxMartyrs);

	    return getMaxDate(maxDate, getMaxDate(leftMaxDate, rightMaxDate));
	}

	// Method to get the date that had the maximum number of martyrs
	public Date getMaxMartyrsDate() {
	    return getDate(root, null, 0);
	}

	// Helper method to get the maximum date between two dates
	private Date getMaxDate(Date date1, Date date2) {
	    if (date1 == null) {
	        return date2;
	    } else if (date2 == null) {
	        return date1;
	    }

	    return date1.after(date2) ? date1 : date2;
	}

}

