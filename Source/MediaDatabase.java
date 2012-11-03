	/*
	 * 	MediaDatabase.java
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

public class MediaDatabase implements Serializable {
	
	private RBTree AlbumTree;
	private RBTree BooksTree;
	private RBTree VHSTree;
	private RBTree VHS_RTree;
	private RBTree DVDTree;
	private RBTree VideoGameTree;
	private RBTree RecipeTree;
	private int albumInt=0;
	
	public MediaDatabase(){
		AlbumTree = new RBTree();
		BooksTree = new RBTree();
		VHSTree = new RBTree();
		VHS_RTree = new RBTree();
		DVDTree = new RBTree();
		VideoGameTree = new RBTree();
		RecipeTree = new RBTree();
	}
	
	public void addAlbum(Albums a){
		AlbumTree.RBTreeInsert(a);}
	
	public void addBook(Books b){
		BooksTree.RBTreeInsert(b);
		/*System.out.println(b.getTitle() + " added!");*/}
	
	public void addVHS(VHS v){
		VHSTree.RBTreeInsert(v);}
	
	public void addVHS_R(VHS_R vhsr){
		VHS_RTree.RBTreeInsert(vhsr);}
	
	public void addDVD(DVD dvd){
		DVDTree.RBTreeInsert(dvd);}
	
	public void addVideoGame(VideoGame vg){
		VideoGameTree.RBTreeInsert(vg);}
	
	public void addRecipe(Recipe r){
		RecipeTree.RBTreeInsert(r);}
	
	// Getters
	public RBTree getAlbumTree(){
		return AlbumTree;}
	
	public RBTree getBooksTree(){
		return BooksTree;}
	
	public RBTree getDVDTree(){
		return DVDTree;}
	
	public RBTree getVHSTree(){
		return VHSTree;}
	
	public RBTree getVHS_RTree(){
		return VHS_RTree;}
	
	public RBTree getVideoGameTree(){
		return VideoGameTree;}
	
	public RBTree getRecipeTree(){
		return RecipeTree;}
	
	public int getAlbumInt(){
		return albumInt;
		}
	public void incAlbNum(){
		albumInt++;
	}
	
}
