	/*
	 * 	Albums.java
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

import java.awt.Image;
import java.io.Serializable;

public class Albums implements Comparable, Serializable {
	
	private String title, artist, releaseDate, tracks, label, format, genre, description, review;
	private int rating;
	private int albumNum;

	public Albums(String title, String artist, String releaseDate, String tracks , String label, String format, String genre, int rating, String description, String review, int albumNum){
		this.title = title;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.tracks = tracks;
		this.label = label;
		this.format = format;
		this.genre = genre;
		this.description = description;
		this.review = review;
		this.rating = rating;
		this.albumNum = albumNum;
	}
	
	//Getters
	public String getTitle(){
		return title;}
	
	public String getArtist(){
		return artist;}
	
	public String getReleaseDate(){
		return releaseDate;}
	
	public String getTracks(){
		return tracks;}
	
	public String getLabel(){
		return label;}
	
	public String getFormat(){
		return format;}
	
	public String getGenre(){
		return genre;}
	
	public String getDescription(){
		return description;}
	
	public String getReview(){
		return review;}
	
	public int getRating(){
		return rating;}
	
	public int getAlbumNum(){
		return albumNum;}
	
	public String getType(){
		return "Album";}
	
	//Setters
	public void setTitle(String title){
		this.title = title;}
	
	public void setArtist(String artist){
		this.artist = artist;}
	
	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;}
	
	public void setTracks(String tracks){
		this.tracks = tracks;}
	
	public void setLabel(String label){
		this.label = label;}
	
	public void setFormat(String format){
		this.format = format;}
	
	public void setGenre(String genre){
		this.genre = genre;}
	
	public void setDescription(String description){
		this.description = description;}
	
	public void setReview(String review){
		this.review = review;}
	
	public void setRating(int rating){
		this.rating = rating;}
	
	//toString
	public String toString(){
		String dataDump = " "+title+" "+artist+" "+releaseDate+" "+tracks+" "+label+" "+format+" "+genre+" "+rating+" ";
		dataDump=dataDump.toLowerCase();
		return dataDump;
	}
	
	//Comparable interface
	public int compareTo(Object album) throws ClassCastException{
		if (!(album instanceof Albums))
			throw new ClassCastException("An Album object expected.");
		
		int x;
		Albums albumToTest = ((Albums) album);
		if ((this.toString()).equals(albumToTest.toString())){
			x = 0;}
		else if (this.toString().compareTo(albumToTest.toString()) > 0){
			x = 1;}
		else{
			x=-1;}
		return x;
	}	
}
