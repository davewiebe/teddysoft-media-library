	/*
	 * 	DVD.java
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
public class DVD extends VHS {
	
	private boolean isWideScreen;
	private String format;

	public DVD (String title, String director, String year, String contentRated, String runningTime, String review, String description, String format, boolean isWideScreen, int rating){
		super(title, director, year, contentRated, runningTime, review, description, rating);
		this.isWideScreen = isWideScreen;
		this.format = format;
	}
	
	//Getters
	public boolean getIsWideScreen(){
		return isWideScreen;}
	
	public String getFormat(){
		return format;}
	
	public String getType(){
		return "DVD";}
	
	//Setters
	public void setIsWideScreen(boolean isWideScreen){
		this.isWideScreen = isWideScreen;}
	
	public void setFormat(String format){
		this.format = format;}
	
	//toString
	public String toString(){
		String dataDump = " "+title+" "+director+" "+year+" "+contentRated+" "+runningTime+" "+format+" "+isWideScreen+" "+rating+" ";
		dataDump=dataDump.toLowerCase();
		return dataDump;
	}
	
	//Comparable interface
	public int compareTo(Object dvd) throws ClassCastException{
		if (!(dvd instanceof DVD))
			throw new ClassCastException("A DVD object expected.");
		
		int x;
		DVD dvdToTest = ((DVD) dvd);
		if ((this.toString()).equals(dvdToTest.toString())){
			x = 0;}
		else if (this.toString().compareTo(dvdToTest.toString()) > 0){
			x = 1;}
		else{
			x=-1;}
		return x;
	}
	
}
