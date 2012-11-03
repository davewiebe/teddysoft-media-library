	/*
	 * 	VHS.java
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

public class VHS implements Serializable, Comparable {

		protected String title, director, year, contentRated, runningTime, review, description;
		protected int rating;
		//implement search key
	
		public VHS (String title, String director, String year, String contentRated, String runningTime, String review, String description, int rating){
			this.title = title;
			this.director = director;
			this.year = year;
			this.contentRated = contentRated;
			this.runningTime = runningTime;
			this.review = review;
			this.description = description;
			this.rating = rating;
			//implement search key
			
		}
		//Getters
		public String getTitle(){
			return title;}
		
		public String getdirector(){
			return director;}
		
		public String getyear(){
			return year;}
		
		public String getContentRated(){
			return contentRated;}
		
		public String getRunningTime(){
			return runningTime;}
		
		public String getReview(){
			return review;}
		
		public String getDescription(){
			return description;}
		
		public String getType(){
			return "VHS";}
		
		public int getRating(){
			return rating;}

		
		//Setters for editing feature
		public void setTitle(String title){
			this.title = title;}
		
		public void setDirector(String director){
			this.director = director;}
		
		public void setYear(String year){
			this.year = year;}
		
		public void setContedRated(String contentRated){
			this.contentRated = contentRated;}
		
		public void setRunningTime(String runningTime){
			this.runningTime = runningTime;}
		
		public void setReview(String review){
			this.review = review;}
		
		public void setDescription(String description){
			this.description = description;}
		
		public void setRating(int rating){
			this.rating = rating;}
		
		//toString
		public String toString(){
			String dataDump = " "+title+" "+director+" "+year+" "+contentRated+" "+runningTime+" "+rating+" ";
			dataDump=dataDump.toLowerCase();
			return dataDump;
		}
		
		//Comparable interface
		public int compareTo(Object v) throws ClassCastException{
			if (!(v instanceof VHS))
				throw new ClassCastException("A VHS object expected.");
			
			int x;
			VHS vhsToTest = ((VHS) v);
			if ((this.toString()).equals(vhsToTest.toString())){
				x = 0;}
			else if (this.toString().compareTo(vhsToTest.toString()) > 0){
				x = 1;}
			else{
				x=-1;}
			return x;
		}
		
}
