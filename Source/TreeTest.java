package teddySoft;
/*
 * 	TreeTest.java
 * 	
 * 	Written by Jordan McMillan
 * 	
 * 
 * 	Team TeddySoft is:
 * 	David Wiebe
 *  Frankie Yan
 * 	Jordan McMillan
 * 	Lisa Chen
 */


public class TreeTest {

	public static void main(String[] args) {
		test2();
		test1();
	}
	
	public static void test1(){
		RBTree tree = new RBTree();
		//insert items
		String[] st = {"1", "2", "3", "4", "5", "6", "7", "8"};
		for(int t=0;t<st.length;t++){
			tree.RBTreeInsert(new Books(st[t] , "Tolken", "1st", "Today", "middle earth", "12345", "SciFi", 5, "", "", "publication place"));
		}
		
		//display inserted items
		tree.printTree(); 
		System.out.println("Size =" + tree.getSize());
		
		//view a single item
		Books temp = new Books();
		temp = (Books)tree.RB_Get_Root().left.data;
		System.out.println("Found " + temp.getTitle() + " " + temp.getAuthor());
		
		//remove an item
		tree.RBTreeRemove(tree.RB_Get_Root().left.data);
		//verify that the item was removed
		tree.printTree();
		System.out.println("Size =" + tree.getSize());
		
		//display items' data
		Comparable List[] = new Comparable[tree.getSize()];
		List = tree.getTreeElements();
		for (int i=0;i<List.length;i++){
			Books temp2 = (Books)List[i];
			System.out.println(temp2.getTitle() + " " + temp2.getAuthor());
		}
	}
	
	public static void test2(){
		RBTree tree = new RBTree();
		//insert items
		String[] st = {"a", "b", "c", "d"};
		for(int t=0;t<st.length;t++){
			tree.RBTreeInsert(new Books(st[t] , "", "", "", "", "", "", 1, "", "", "publication place"));
		}
		
	}
}
