	/*
	 * 	VideoGame.java
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

package teddySoft;
import java.io.Serializable;

public class VideoGame implements Serializable, Comparable  {

	private String title, developer, year, contentRated, platform, description, review;
	private int rating, maxPlayers;
	//implement search key

	public VideoGame (String title, String developer, String year, String contentRated, String platform, String description, String review, int rating, int maxPlayers){
		this.title = title;
		this.developer = developer;
		this.year = year;
		this.contentRated = contentRated;
		this.platform = platform;
		this.rating = rating;
		this.maxPlayers = maxPlayers;
		this.description = description;
		this.review = review;
		//implement search key
		
	}
	//Getters
	public String getTitle(){
		return title;}
	
	public String getdeveloper(){
		return developer;}
	
	public String getyear(){
		return year;}
	
	public String getContentRated(){
		return contentRated;}
	
	public String getPlatform(){
		return platform;}
	
	public String getType(){
		return "Video Game";}
	
	public String getDescription(){
		return description;}
	
	public String getReview(){
		return review;}
	
	public int getRating(){
		return rating;}
	
	public int getMaxPlayers(){
		return maxPlayers;}
	
	//Setters for editing feature
	public void setTitle(String title){
		this.title = title;}
	
	public void setDeveloper(String developer){
		this.developer = developer;}
	
	public void setYear(String year){
		this.year = year;}
	
	public void setContedRated(String contentRated){
		this.contentRated = contentRated;}
	
	public void setPlatform(String platform){
		this.platform = platform;}
	
	public void setRating(int rating){
		this.rating = rating;}
	
	public void setMaxPlayers(int maxPlayers){
		this.maxPlayers = maxPlayers;}
	
	public void setDescription(String description){
		this.description = description;}
	
	public void setReview(String review){
		this.review = review;}
	
	//toString
	public String toString(){
		String dataDump = " "+title+" "+developer+" "+year+" "+contentRated+" "+platform+" "+maxPlayers+" "+rating+" ";
		dataDump=dataDump.toLowerCase();
		return dataDump;
	}
	
	//Comparable interface
	public int compareTo(Object vg) throws ClassCastException{
		if (!(vg instanceof VideoGame))
			throw new ClassCastException("A VideoGame object expected.");
		
		int x;
		VideoGame vgToTest = ((VideoGame) vg);
		if ((this.toString()).equals(vgToTest.toString())){
			x = 0;}
		else if (this.toString().compareTo(vgToTest.toString()) > 0){
			x = 1;}
		else{
			x=-1;}
		return x;
	}
}
