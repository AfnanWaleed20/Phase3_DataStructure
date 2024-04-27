package application;
//this class is node to Avl2 cotians a stack
public class NodeAvl2 {
		Date_Stack target;
		NodeAvl2 left;
		NodeAvl2 right;
		int height;
		Stack stack=new Stack();

		public NodeAvl2(Date_Stack key) {
			this.target=key;
		}

		public NodeAvl2(Date_Stack target, NodeAvl2 left, NodeAvl2 right) {
			this.target = target;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
		 public boolean isLeaf() {
		        return left == null && right == null;
		    }
		
		 public Stack getStack() {
			 if (stack==null) {
				 return new Stack();}
			 else
				return stack;
			}
			public void setStack(Stack stack) {
				this.stack = stack;
			}

			@Override
			public String toString() {
				return "NodeAvl2 [target=" + target + ", left=" + left + ", right=" + right + ", height=" + height
						+ ", stack=" + stack + "]";
			}
			
	}


