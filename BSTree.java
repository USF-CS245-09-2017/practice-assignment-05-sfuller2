/*CS245 Practice Assignment 05
 * 
 * @author Sophie Fuller
 *
 */
public class BSTree {
	
	protected class BSTNode{
		
		public String data;
		public BSTNode left;
		public BSTNode right;
		
		public BSTNode(String newdata) {
			data = newdata;
		}
	}
	
	private BSTNode root;
	
	public BSTree() {
		root = null;
	}
	
	public void insert(String value) {
		//if the tree is empty, the value is inserted at the root
		if(root == null) {
			root = insert(value, root);
		}
		else { //if the tree isn't empty, the value is inserted in its proper place
			insert(value, root);
		}
		
	}
	
	public BSTNode insert(String value, BSTNode node) {
		//base case: once the node is null, create a new node with that value
		if(node == null) {
			return new BSTNode(value);
		}
		//if the value is less than the current node, traverse to the left and put value in its proper place
		if(value.compareTo(node.data) < 0) {
			node.left = insert(value, node.left);
			return node;
		}
		else { //if the value is greater than the current node, traverse to the right
			node.right = insert(value, node.right);
			return node;
		}	
	}
	
	public boolean find(String value) {
		return find(value, root);
	}
	
	public boolean find(String value, BSTNode node) {
		//base case: if the end of the tree is reached, the value is not in the tree
		if(node == null) {
			return false;
		}
		//return true once the value is found
		if(node.data.compareTo(value) == 0) {
			return true;
		}//if the value is less than the date, throw out the right half of the tree and and check the left half
		else if(value.compareTo(node.data) < 0) {
			return find(value, node.left);
		}//if the value is greater than the data, throw out the left half
		else {
			return find(value, node.right);
		}
	}
	
	public void delete(String value) {
		//starting at the root, traverse the tree until the value is found and deleted
		root = delete(value, root);
	}
	
	public BSTNode delete(String value, BSTNode node) {
		//base case: if the value is not in the tree, return null
		if(node == null) {
			return null;
		}
		//if the value is found
		if(node.data.compareTo(value) == 0) {
			//if the node has no left leaf, parent adopts the right leaf
			if(node.left == null) {
				return node.right;
			}
			//if the node has no right leaf, parent adopts left leaf
			else if(node.right == null) {
				return node.left;
			}
			else { //if the node has two children
				if(node.right.left == null) { //if the node's right child doesn't have a left child
					node.data = node.right.data; //parent adopts the child
					node.right = node.right.right;
					return node;
				} else { //otherwise, traverse the left subtree and adopt the rightmost child
					node.data = removeSmallest(node.right);
					return node;
				}	
			}
		} else if (value.compareTo(node.data) < 0) { //if the value is less than the current node
			node.left = delete(value, node.left); //traverse the left subtree to find and delete it
			return node;
		} else {
			node.right = delete(value, node.right); //traverse the right subtree to find and delete it
			return node;
		}
		
	}
	
	private String removeSmallest(BSTNode node) {
		if(node.left.left == null) {
			String smallest = node.left.data;
			node.left = node.left.right;
			return smallest;
		} else {
			return removeSmallest(node.left);
		}
	}
	
	
	public String toStringInOrder() {
		return toStringInOrder(root);
	}
	
	public String toStringInOrder(BSTNode node) {
		if(node == null) { //if the end is reached, return an empty string
			return "";
		}  //traverse the left subtree first, then the current root, then the right subtree
		String toReturn = toStringInOrder(node.left) + " " + node.data + " " + toStringInOrder(node.right);
		return toReturn.trim(); //trim off any additional whitespace
		
	}
	public String toStringPreOrder() {
		return toStringPreOrder(root).trim(); //trim any additional whitespace
	} 
	
	public String toStringPreOrder(BSTNode node) {
		if(node == null) { //if the end is reached, return an empty string
			return "";
		} //print the current node (starting with root) then its left subtree, then its right subtree
		String toReturn = node.data + " " + toStringPreOrder(node.left) + toStringPreOrder(node.right);
		return toReturn;
	}
	
	
}
