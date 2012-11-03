	/*
	 * 	VHS_R.java
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
public class VHS_R extends VHS {

	private String timeStamp, index;
	
	public VHS_R (String title, String director, String year, String contentRated, String review, String description, String runningTime, String timeStamp, String index,  int rating){
		super(title, director, year, contentRated, runningTime, review, description, rating);
		this.timeStamp = timeStamp;
		this.index = index;
		//implement search key
	}
	
	//Getters
	public String getTimeStamp(){
		return timeStamp;}
	
	public String getIndex(){
		return index;}
	
	public String getType(){
		return "VHS_R";}
	
	//Setters
	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;}
	
	public void setIndex(String index){
		this.index = index;}
	
	//toString
	public String toString(){
		String dataDump = " "+title+" "+director+" "+year+" "+contentRated+" "+runningTime+" "+timeStamp+" "+index+" "+rating+" ";
		dataDump=dataDump.toLowerCase();
		return dataDump;
	}
	
	//Comparable interface
	public int compareTo(Object vr) throws ClassCastException{
		if (!(vr instanceof VHS_R))
			throw new ClassCastException("A VHS_R object expected.");
		
		int x;
		VHS_R vhsrToTest = ((VHS_R) vr);
		if ((this.toString()).equals(vhsrToTest.toString())){
			x = 0;}
		else if (this.toString().compareTo(vhsrToTest.toString()) > 0){
			x = 1;}
		else{
			x=-1;}
		return x;
	}
}