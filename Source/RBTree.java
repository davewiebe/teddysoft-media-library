	/*
	 * 	RBTree.java
	 * 	
	 * 	Written by Jordan McMillan
	 * 	Edited by David Wiebe
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 *  Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */

package teddySoft;
import java.io.Serializable;

public class RBTree implements Serializable{
	
	class RBnode implements Serializable{
		public RBnode left;
		public RBnode right;
		public RBnode parent;
		public Comparable data;
		public boolean isBlack;
		
		RBnode(){
			left = null;
			right = null;
			parent = null;
			data = null;
			isBlack = true;
		}
		RBnode(Comparable data){
			left = null;
			right = null;
			parent = null;
			this.data = data;
			isBlack = true;
		}
	}
	
	private int size;
	private RBnode root;
	private int listCount;
	
	public RBTree(){
		root = null;
		size = 0;	
	}
	
	public int size(){
		return size;
	}
	
	///////////////////////////////////////////////////////////////////
	//POST: returns true it target is found. False otherwise
	//PARAM: target- search the tree for this data. current: current node that is being looked at.
	private boolean searchHelper(Comparable target, RBnode current){
		if (current == null)
			return false;
		if (current.data.compareTo(target) == 0) //if current node contains target data
			return true;
		else if (current.data.compareTo(target) < 0) //if data in current node is less than target data
			return searchHelper(target, current.right);
		else //if data in current node is larger than target data
			return searchHelper(target, current.left);
	}
	
	public boolean search(Comparable target){
		if (size == 0) return false; //if tree is empty return false
		else return searchHelper(target, root);
	}
	
	private Comparable getObjHelper(Comparable target, RBnode current){
		if (current == null)
			return null;
		if (current.data.compareTo(target) == 0) //if current node contains target data
			return current.data;
		else if (current.data.compareTo(target) < 0) //if data in current node is less than target data
			return getObjHelper(target, current.right);
		else //if data in current node is larger than target data
			return getObjHelper(target, current.left);
	}
	public Comparable getObj(Comparable target){
		if (size == 0) return null; //if tree is empty return false
		else return getObjHelper(target, root);
	}
	
	///////////////////////////////////////////////////////////////////
	//POST: prints all elements in tree from lowest to highest
	//PARAM: current: current node being visited
	public void inOrderPrint(RBnode current){
		if (current != null){ //if current node exists
			inOrderPrint(current.left); //recurse with current node's left child
			System.out.println(((Books) current.data).getTitle()); //print data in node
			inOrderPrint(current.right); //recurse with current node's right child
		}
	}
	
	public void printTree(){
		inOrderPrint(root); //start search from root of tree
	}
	
	///////////////////////////////////////////////////////////////////
	//POST: Inserts a data into the correct binary search tree position then returns address of RBnode inserted
	//PARAM: data - data to be stored into tree
	//NOTE: 1)Done iteratively for the sake of good exercise. 2) Sets toInsert's parent pointer
	private RBnode BSTreeInsert(Comparable data){
		RBnode toInsert = new RBnode(data); //create new RBnode with target data
		RBnode temp = root; //temp RBnode
		
		if (root == null){ //if root does not exist
			root = toInsert; //root is now node toInsert
			size++;
			return toInsert;
		}
		
		while ((temp.left != null) || (temp.right != null)){ //while temp's left or right child exist
			if (temp.data.compareTo(toInsert.data) == 0){ //if temp's data equals the data to insert in tree
				return null;
				
				//break; //for adding copies of objects
			}
			else if (temp.data.compareTo(toInsert.data) < 0){ //if temp's data is less than data toInsert
				if (temp.right != null) //if temp has a right child
					temp = temp.right; //temp is now equal to that right child
				else break;
			}
			else { //if temp's data is greater than data toInsert
				if (temp.left != null) //if temp has a left child
					temp=temp.left; //temp is now equal to that left child
				else break;
			}
		}
		
		if (temp.data.compareTo(toInsert.data) == 0){ //if temp's data equals the data to insert in tree
			return null;
			
		/*	RBnode tempL = new RBnode(); //temp RBnode
			RBnode tempR = new RBnode(); //temp RBnode
			temp.left = toInsert;
			temp.right = null;
			toInsert.left = tempL;
			toInsert.right = tempR;
			return toInsert; //for adding coppies of objects */			
		}
		else if (temp.data.compareTo(toInsert.data) < 0){ //if temp's data is less than data toInsert
			temp.right = toInsert; //temp's right child is equal to toInsert
			toInsert.parent = temp; //sets toInsert's parent pointer
			size++;
			return toInsert;
		}
		else{ //if temp's data is greater than data toInsert
			temp.left = toInsert; //temp's left child is equal to toInsert
			toInsert.parent = temp; //sets toInsert's parent pointer
			size++;
			return toInsert;
		}
	}
	
	///////////////////////////////////////////////////////////////////
	//POST: inserts data into a red black tree while maintaining the tree's properties
	//PARAM: data = data to be inserted into Red Black tree
	public void RBTreeInsert(Comparable data){
		//RBnode toInsert = new RBnode(data); 
		RBnode temp = new RBnode();
		RBnode toInsert = BSTreeInsert(data); //create new RBnode that points to the node that was inserted
	
		if(toInsert == null || toInsert.parent==null){
			root.isBlack = true;
			return;
		}
		
		toInsert.isBlack = false;
		while(toInsert != root && toInsert.parent.parent != null && toInsert.parent.isBlack == false){ //edited with "toInsert.parent.parent != null &&"
			if (toInsert.parent == toInsert.parent.parent.left){ //parent is left
				temp = toInsert.parent.parent.right; //uncle of toInsert
				if (temp != null && temp.isBlack == false){ //same as toInsert.parent
					toInsert.parent.isBlack = true;
					temp.isBlack = true;
					toInsert.parent.parent.isBlack = false;
					toInsert = toInsert.parent.parent;
				}
				else { // if (temp.isBlack == true)
					if (toInsert == toInsert.parent.right){
						toInsert = toInsert.parent;
						left_rotate(toInsert);
					}
					toInsert.parent.isBlack = true;
					toInsert.parent.parent.isBlack = false;
					right_rotate(toInsert.parent.parent);
				}
			}
			else{ //parent is right
				temp = toInsert.parent.parent.left; //uncle of toInsert
				if (temp != null && temp.isBlack == false){ //same as toInsert.parent
					toInsert.parent.isBlack = true;
					temp.isBlack = true;
					toInsert.parent.parent.isBlack = false;
					toInsert = toInsert.parent.parent;
				}
				else { //if (temp.isBlack == true)
					if (toInsert == toInsert.parent.left){
						toInsert = toInsert.parent;
						right_rotate(toInsert);
					}
					toInsert.parent.isBlack = true;
					toInsert.parent.parent.isBlack = false;
					left_rotate(toInsert.parent.parent);
				}
			}
		}
		root.isBlack=true;
	}
	
	///////////////////////////////////////////////////////////////////
	private void left_rotate(RBnode pivot){
		RBnode temp = pivot.right;
		pivot.right = temp.left;
		
		//set parent of pivot's right child
		if(temp.left != null)
			temp.left.parent = pivot;
		
		temp.parent = pivot.parent; //move temp up
		
		//set parent reference of pivot
		if(pivot.parent == null) //pivot was the root
			root = temp;
		else if (pivot == pivot.parent.left) //left child
			pivot.parent.left = temp;
		else //right child
			pivot.parent.right = temp;
		
		temp.left = pivot;
		pivot.parent = temp;		
	}
	
	///////////////////////////////////////////////////////////////////
	private void right_rotate(RBnode pivot){
		RBnode temp = pivot.left;
		pivot.left = temp.right;
		
		//set parent of pivot's right child
		if(temp.right != null)
			temp.right.parent = pivot;
		
		temp.parent = pivot.parent; //move temp up
		
		//set parent reference of pivot
		if(pivot.parent == null) //pivot was the root
			root = temp;
		else if (pivot == pivot.parent.right) //right child
			pivot.parent.right = temp;
		else //left child
			pivot.parent.left = temp;
		
		temp.right = pivot;
		pivot.parent = temp;		
	}
	
	///////////////////////////////////////////////////////////////////
	public RBnode findTargetPointer(Comparable target, RBnode current){
		if (current == null)
			return null;
		if (current.data.compareTo(target) == 0) //if current node contains target data
			return current;
		else if (current.data.compareTo(target) < 0) //if data in current node is less than target data
			return findTargetPointer(target, current.right);
		else //if data in current node is larger than target data
			return findTargetPointer(target, current.left);
	}
	
	///////////////////////////////////////////////////////////////////
	//POST: returns the predecesor of target RBnode
	//PARAM: target - the node which predecessor is to be returned.
	public RBnode predecessor(RBnode target){
		RBnode result = target.left; //result initiated to the root of left subtree of target
		while (result.right != null) 
			result = result.right; //result is equal to the rightmost child of target's left subtree
		return result;
	}
	///////////////////////////////////////////////////////////////////
	private void RBRemoveFix(RBnode x){
		RBnode y = new RBnode();
		while (x != root && x.isBlack == true){
			if (x == x.parent.left){ //x is left child
				y = x.parent.right; //x's sibling
				if (y.isBlack == false){
					y.isBlack = true;
					x.parent.isBlack = false; //parent was black
					left_rotate(x.parent);
					y = x.parent.right;
				}
				if(y.left.isBlack == true && y.right.isBlack == true){
					y.isBlack = false;
					x = x.parent;
					continue; //into while loop again...
				}
				else{
					if (y.right.isBlack == true){
						y.left.isBlack = true;
						y.isBlack = false;
						right_rotate(y);
						y = x.parent.right;
					}
					y.isBlack = x.parent.isBlack;
					x.parent.isBlack = true;
					y.right.isBlack = true;
					left_rotate(x.parent);
					x = root;
				}
			}
			else{ //symetric to if
				y = x.parent.left; //x's sibling
				if (y.isBlack == false){
					y.isBlack = true;
					x.parent.isBlack = false; //parent was black
					right_rotate(x.parent);
					y = x.parent.left;
				}
				if(y.right.isBlack == true && y.left.isBlack == true){
					y.isBlack = false;
					x = x.parent;
					continue; //into while loop again...
				}
				else{
					if (y.left.isBlack == true){
						y.right.isBlack = true;
						y.isBlack = false;
						left_rotate(y);
						y = x.parent.left;
					}
					y.isBlack = x.parent.isBlack;
					x.parent.isBlack = true;
					y.left.isBlack = true;
					right_rotate(x.parent);
					x = root;
				}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////
	public boolean RBTreeRemove(Comparable target){
		RBnode toRemove = findTargetPointer(target, root);
		RBnode y = new RBnode();
		RBnode x = new RBnode();
		if (toRemove == null)
			return false;
		
		if (toRemove.left == null || toRemove.right == null)
			y = toRemove;
		else
			y = predecessor(toRemove);
		
		if(y.left != null)
			x = y.left;
		else if(y.right != null)
			x = y.right;
		else x = null;
		
		if (x != null)
			x.parent = y.parent; //detach x from y
		
		if(y.parent == null) //y is the root
			root = x;
		else{ //attatch x to y's parent
			if (y == y.parent.left) //left child
				y.parent.left = x;
			else
				y.parent.right = x;
		}
		if (y != toRemove) //i.e. y has been moved up
			toRemove.data = y.data; //replace toRemove with y
		if (y.isBlack == true && x != null)
			RBRemoveFix(x); //x could be null
		size--;
		return true;
	}

	///////////////////////////////////////////////////////////////////
	public RBnode RB_Get_Root(){ //return private variable root
		return root;
	}
	
	public int getSize(){
		return size;
	}
	///////////////////////////////////////////////////////////////////
	//POST: returns ordered list of tree elements
	public Comparable[] getTreeElements(){
		listCount = 0;
		Comparable List[] = new Comparable[this.size];
		return getTreeElementsHelper(root, List); //start with root of tree
	}	
	///////////////////////////////////////////////////////////////////
	//POST: puts all elements in tree into an ordered list
	//PARAM: current: current node being visited
	//       List: current list of nodes
	public Comparable[] getTreeElementsHelper(RBnode current, Comparable[] List){
		if (current != null){ //if current node exists
			getTreeElementsHelper(current.left, List); //recurse with current node's left child
			List[listCount] = current.data;
			listCount++;
			getTreeElementsHelper(current.right, List); //recurse with current node's right child
		}
		return List;
	}
	
	///////////////////////////////////////////////////////////////////
	//POST: Returns list of tree elements in which their toString function contains the keyword
	//      Length of returning array will always be the size of the entire red black tree
	public Comparable[] searchTreeElements(String textFeild){
		listCount = 0;
		textFeild = textFeild.toLowerCase();
		String[] keywords = textFeild.split(" "); //split the text into individual words
		Comparable Results[] = new Comparable[this.size];
		return searchTreeElementsHelper(root, Results, keywords); //start with root of tree
	}	
	///////////////////////////////////////////////////////////////////
	//POST: Puts all matches in tree into an ordered list. 
	//      If no matches are found it returns an array of length one. The only element is a null element
	//PARAM: current: current node being visited
	//       Results: current list of nodes that contain the keyword
	//       keyword: string that is being searched for in nodes
	public Comparable[] searchTreeElementsHelper(RBnode current, Comparable[] Results, String[] keywords){
		if (current != null){ //if current node exists
			searchTreeElementsHelper(current.left, Results, keywords); //recurse with current node's left child
			
			boolean containsFlag=false;
			for(int i=0;i<keywords.length;i++){ //for all keywords, if current data contains a keyword
				//keywords[i] = " "+keywords[i]+" "; //ensures that we search for the entire keyword and not just a portion of it
				if(current.data.toString().contains(keywords[i])){ //toString is overwritten in each media type class
					//System.out.println("currentdata dot toString "+current.data.toString());
					containsFlag=true;
				}
			}
			if(containsFlag){
				Results[listCount] = current.data;
				listCount++;
			}
			
			searchTreeElementsHelper(current.right, Results, keywords); //recurse with current node's right child
		}
		return Results;
	}
	
}
