	/*
	 * 	Recipe.java
	 * 	
	 * 	Written by Jordan McMillan
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 * 	Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */

package teddySoft;
import java.io.Serializable;

public class Recipe implements Serializable, Comparable {

	private String title, ingredients, instructions, review, description;
	private int rating;

	public Recipe (String title, String ingredients, String instructions, String description, String review, int rating){
		this.title = title;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.review = review;
		this.description = description;
		this.rating = rating;
		
	}
	//Getters
	public String getTitle(){
		return title;}
	
	public String getIngredients(){
		return ingredients;}
	
	public String getInstructions(){
		return instructions;}
	
	public String getReview(){
		return review;}
	
	public String getDescription(){
		return description;}
	
	public String getType(){
		return "Recipe";}
	
	public int getRating(){
		return rating;}

	
	//Setters for editing feature
	public void setTitle(String title){
		this.title = title;}
	
	public void setIngredients(String ingredients){
		this.ingredients = ingredients;}
	
	public void setInstructions(String instructions){
		this.instructions = instructions;}
	
	public void setReview(String review){
		this.review = review;}
	
	public void setDescription(String description){
		this.description = description;}
	
	public void setRating(int rating){
		this.rating = rating;}
	
	//toString
	public String toString(){
		String dataDump = " "+title+" "+ingredients+" "+instructions+" "+rating+" ";
		dataDump=dataDump.toLowerCase();
		return dataDump;
	}
	
	//Comparable interface
	public int compareTo(Object r) throws ClassCastException{
		if (!(r instanceof Recipe))
			throw new ClassCastException("A Recipe object expected.");
		
		int x;
		Recipe recipeToTest = ((Recipe) r);
		if ((this.toString()).equals(recipeToTest.toString())){
			x = 0;}
		else if (this.toString().compareTo(recipeToTest.toString()) > 0){
			x = 1;}
		else{
			x=-1;}
		return x;
	}	
	
}
