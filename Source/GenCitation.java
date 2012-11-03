	/*
	 * 	GenCitation.java
	 * 	
	 * 	Written by David Wiebe
	 * 	Edited by David Wiebe
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 *  Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 * 
	 */

package teddySoft;

public class GenCitation {

	// PRE: 
	// POST: Returns Properly formated APA style Citation
	// PARAM: Author, Year, Title, Place, Publisher, Edition.  All Strings.
	public static String genAPA(String author, String year, String title, String place, String publisher, String edition){
		String apa = "";
		
		// get author citations
		String citeAuthor = citeAuthor(author).trim();
		
		// if there is no author, citation is formatted differently.
		if (citeAuthor.equalsIgnoreCase("")){
			// if there is a title, 
			if(!citeTitle(title).equalsIgnoreCase("")){
				apa += citeTitle(title);
				// if there is an edition
				if(!citeEdition(edition).equalsIgnoreCase("")){
					apa += " (" + citeEdition(edition) + " ed.)";
				}	
				apa += ". ";
			}
			// if a year was inputed
			if (!citeYear(year).equalsIgnoreCase("") && !citeYear(year).equalsIgnoreCase("0")){
				apa += "(" + citeYear(year) + "). ";
			}
		}
		
		// if there was an author, citation is formatted differently.
		else {
			apa += citeAuthor + " ";
			// year comes after author, if there is a year inputed.
			if (!citeYear(year).equalsIgnoreCase("") && !citeYear(year).equalsIgnoreCase("0")){
				apa += "(" + citeYear(year) + "). ";
			}
			// then title.
			if (!citeTitle(title).equalsIgnoreCase("")){
				apa += citeTitle(title);
				// if there is an edition, it follows the title.
				if(!citeEdition(edition).equalsIgnoreCase("")){
					apa += " (" + citeEdition(edition) + " ed.)";
				}
				apa += ". ";
			}
		}
		
		// if theres a place.
		if (!citePlace(place).equalsIgnoreCase("")){
			apa += citePlace(place);
			// if theres a publisher, it follows after the colon.
			if (!citePublisher(publisher).equalsIgnoreCase("")){
				apa += ": " + citePublisher(publisher);
			}
			apa += ". ";
		}
		// if no place, but there is a publisher, we dont want the colon like above.
		else{
			if (!citePublisher(publisher).equalsIgnoreCase("")){
				apa += citePublisher(publisher) + ". ";
			}
		}// return citation without author
		return apa;
	}

	// PRE: Author Strings, separated by a semicolon ';'
	// POST: Returns String of Cited Authors, from author list input
	// PARAM: String authors, multiple authors supported with ; separation
	private static String citeAuthor(String author){
		// our string we will return after its all done.
		String names = "";
		
		// split multiple authors by the semicolon into a String array.
		String[] multipleAuthors = author.split(";");
		
		// the first author gets automatically entered into names
		names += citeAuthorHelper(multipleAuthors[0]);
		
		// the rest will go through this for loop, 
		for (int i = 1; i < multipleAuthors.length; i++ ){
			// if it is the last author to be added, then it will have an & before it.
			if (i == multipleAuthors.length-1){
				names += "& ";
				
			// if its not the last author, a comma will be before the name.
			}else{
				names = names.substring(0, names.length()-1) + ", ";
			}
			// cite it and add the name to the string.
			names += citeAuthorHelper(multipleAuthors[i]);
		}
		
		// return our string of names.
		return names;
	}
	
	// PRE: 
	// POST: Individual author cited style APA.
	// PARAM: Individual 3 name author.  
	private static String citeAuthorHelper(String author){

		// split the authors name into a String array, around the First, Middle, and Last names.
		String[] splitString = author.split(" ");
		
		// h is which value we are on, just in case there were extra spaces between the values.
		int h = 0;
		
		// initialize our names to return.
		String fname = "";
		String mname = "";
		String lname = "";
		String name = "";
		
		// if there is a jr found, set this to true, and we'll deal with it later.
		boolean jr = false;
		
		// put the authorArrayList through a for loop to find all values.
		for(int i = 0; i < splitString.length; i++){
			// trim unnecessary spaces
			splitString[i] = splitString[i].trim();
			// if theres a jr. remove it.
			if(splitString[i].compareToIgnoreCase("Jr.") == 0 ||
					splitString[i].compareToIgnoreCase("Jr") == 0 ||
					splitString[i].compareToIgnoreCase("Jr,") == 0){
				jr = true;
			}
			
			// if its the first value, and its not empty, set it as first name.
			else if(h==0 && splitString[i].compareTo("") != 0){
				fname = splitString[i];
				h++;
			}
			// if its the second value, and its not empty, set it as middle name.
			else if(h==1 && splitString[i].compareTo("") != 0){
				mname = splitString[i];
				h++;
			}
			// if its the third value, and its not empty, set it as last name
			else if(h==2 && splitString[i].compareTo("") != 0){
				lname = splitString[i];
				h++;
			}
			else if(h==3 && splitString[i].compareTo("") != 0){
				lname = splitString[i];
			}
		}
		
		// if the first name ends with a comma, 
		// set the first name to be the last, 
		// the last to be the middle, and the middle is the new first.
		if(fname.endsWith(",")){
			fname = fname.substring(0, fname.length()-1);
			String temp = lname;
			lname = fname;
			fname = mname;
			mname = temp;
			// if the new first name ends with a comma, 
			// swap the first name with the middle name.
			if(fname.endsWith(",")){
				fname = fname.substring(0, fname.length()-1);
				temp = fname;
				fname = mname;
				mname = temp;
			}
		}
		
		// if the middle name ends with a comma, then its probably the last name, so swap values accordingly.
		if(mname.endsWith(",") && lname.compareTo("") != 0){ // prevent cases where  Dave Wiebe, A. == A., D. W. 
			mname = mname.substring(0, mname.length()-1);
			String temp = mname;
			mname = lname;
			lname = temp;		
		}
		
		// if the last name is empty, then the middle name must be the last name,
		// as the probably didnt enter a middle name.
		if(lname.compareTo("") == 0){
			String temp = lname;
			lname = mname;
			mname = temp;
		}
		
		// if the last name is empty, and the middle name is empty, we'll assume the first name is the last name.
		if(lname.compareTo("") == 0 && mname.compareTo("") == 0){
			lname = fname;
			fname = "";
		}
		
		// if the first names length is greater than 1, 
		//  and the last names length is equal to 1, 
		//  and the middle names length is equal to 1 or 0, 
		//  then we'll assume the last name is the first name,
		//  and the middle name is the first name.
		//  and the last name is the middle name.
		if(fname.length() > 1 && lname.length() == 1 && mname.length() < 2){
			String temp = fname;
			fname = mname;
			mname = lname;
			lname = temp;
		}
		
		// if the first name is empty, we'll assume the middle name was the first name.
		if(fname.length() == 0){
			fname = mname;
			mname = "";
		}
		
		// start the name off with the last name.
		name = name + lname;
		
		// if there is a first name, the first name follows the last.
		if (fname.compareTo("") != 0){
			name = name + ", "+fname.charAt(0)+".";
		} else if (lname.compareTo("") != 0){
			name += ".";
		}
		
		// if theres a middle name, the middle follows next.
		if (mname.compareTo("") != 0){
			name = name + " "+mname.charAt(0)+".";
		}
		
		// if we did find a jr., then it always comes last, and after a comma.
		if(jr == true){
			name = name + ", Jr.";
		}
		
		// finish off with a space, and return it.
		name = name+" ";
		return name;
		
	}
	
	// PRE: voidCiteAuthor helper function.
	// POST: Prints to console, helper.
	// PARAM: String of authors, seperated by a semicolon.
	private static void testCiteAuthorHelper(String author){
		System.out.println(author + " == " + citeAuthor(author));
	}
	

	public static void main(String []args){
		testCiteAuthor();
		//testCiteEdition();
		//testGenAPA();
	}
	
	// PRE: 
	// POST: Outputs edition citation tests to console.
	// PARAM: none.  Just test functions.
	private static void testCiteEdition(){
		testCiteEdtionHelper("3");
		testCiteEdtionHelper("3rd");
		testCiteEdtionHelper("third");
		testCiteEdtionHelper("36th");
		testCiteEdtionHelper("34");
		testCiteEdtionHelper("33");
		testCiteEdtionHelper("35478587854857841");
		testCiteEdtionHelper("1");
		testCiteEdtionHelper("0");
		testCiteEdtionHelper(" ");
		testCiteEdtionHelper("Twenty Fifth");
		testCiteEdtionHelper("dsfdagsgdsffdgshtrgyhtd");
		testCiteEdtionHelper("David Wiebe");
		testCiteEdtionHelper("-3");
		testCiteEdtionHelper("fourteenth");
		testCiteEdtionHelper("Fourteen");
		
	}
	
	// PRE: testCiteEdition helper function
	// POST: Prints tests to console
	// PARAM: Uncited Edition parameter.
	private static void testCiteEdtionHelper(String edition){
		System.out.println(edition + " == " + citeEdition(edition));
	}
	
	// PRE: Tests generating APA citations
	// POST: Prints tests to console.
	// PARAM: None.
	private static void testGenAPA(){
		//genAPA(String author, String year, String title, String place, String publisher, String edition)
		testGenAPAHelper("Dave Wiebe", "1996", "Title of the book here.", "New Jersey", "Apple Books Publishing Ltd", "ThIRD edition");
		testGenAPAHelper("", "1996", "Title of the book here.", "New Jersey", "Apple Books Publishing Ltd", "ThIRD edition");
		testGenAPAHelper("Dave Wiebe", "", "Title of the book here.", "New Jersey", "Apple Books Publishing Ltd", "ThIRD edition");
		testGenAPAHelper("Dave Wiebe", "1996", "", "New Jersey", "Apple Books Publishing Ltd", "ThIRD edition");
		testGenAPAHelper("Dave Wiebe", "1996", "Title of the book here.", "", "Apple Books Publishing Ltd", "ThIRD edition");
		testGenAPAHelper("Dave Wiebe", "1996", "Title of the book here.", "New Jersey", "", "ThIRD edition");
		testGenAPAHelper("Dave Wiebe", "1996", "Title of the book here.", "New Jersey", "Apple Books Publishing Ltd", "");
		testGenAPAHelper("", "", "", "", "", "");
		testGenAPAHelper("Dave Wiebe; Jordan McMillan; Chen, Lisa A.", "1996", "Title of the book here.", "New Jersey", "Apple Books Publishing Ltd", "ThIRD edition");
		
		}
	
	// PRE: testGenAPA helper function
	// POST: prints tests to console.
	// PARAM: Input all genAPA citation information
	private static void testGenAPAHelper(String author, String year, String title, String place, String publisher, String edition){
		System.out.println(author +" - "+ year +" - "+ title +" - "+ place +" - "+ publisher +" - "+ edition);
		System.out.println(genAPA(author, year, title, place, publisher, edition));
		System.out.println();		
	}
	
	// PRE: citeAuthor test function
	// POST: prints tests to console
	// PARAM: None.
	private static void testCiteAuthor(){
		testCiteAuthorHelper("");
		testCiteAuthorHelper("Fanna  Williamson   Jr.");
		testCiteAuthorHelper("Williamson, Fanna  jr  ");
		testCiteAuthorHelper("Fanna JR. Williamson   ");
		testCiteAuthorHelper("Williamson F  Jr.      ");
		testCiteAuthorHelper("Williamson, F  Jr.     ");
		testCiteAuthorHelper("Williamson, jr F       ");
		
		testCiteAuthorHelper("");
		testCiteAuthorHelper("Fds sfds fds fsdf dsf df");
		testCiteAuthorHelper("David Andrew Wiebe");
		testCiteAuthorHelper("04593 9305 39053943");
		
		testCiteAuthorHelper("Fanna A. Williamson   Jr. ");
		testCiteAuthorHelper("Williamson, Fanna A jr    ");
		testCiteAuthorHelper("Fanna A JR. Williamson    ");
		testCiteAuthorHelper("F A Williamson Jr.        ");
		testCiteAuthorHelper("Williamson, F A.  Jr.     ");
		testCiteAuthorHelper("Williamson, A, jr F       ");

		testCiteAuthorHelper("Fanna A. Williamson   Jr. ; David A Wiebe");
		testCiteAuthorHelper("Williamson, Fanna A jr; Dave Wiebe, A.    ");
		testCiteAuthorHelper("Fanna A JR. Williamson ; D Wiebe ; Mcmillan, Jordan");
		testCiteAuthorHelper("F A Williamson Jr.        ");
		testCiteAuthorHelper("Williamson, F A.  Jr.     ");
		testCiteAuthorHelper(" D Wiebe    ");
		testCiteAuthorHelper(" Dave Wiebe    ");
		testCiteAuthorHelper(" Dave Wiebe, A.   ");
		testCiteAuthorHelper(" Wiebe D A   ");
		testCiteAuthorHelper(" Wiebe D   ");
		testCiteAuthorHelper(" Wiebe    ");
	}
	
	// PRE: 
	// POST: trims and returns year input.
	// PARAM: String year.
	private static String citeYear(String year){
		//trim the year of unnecessary spaces.
		year = year.trim();
		
		// trim the year of unnecessary periods or commas.
		if(year.endsWith(",") || year.endsWith(".")){
			year = year.substring(0, year.length()-1);
		}
		return year;
	}
	
	// PRE: 
	// POST: trims and returns title input.
	// PARAM: String Title.
	private static String citeTitle(String title){	
//		trim unnecessary spaces.
		title = title.trim();
		
		// trim unnecessary periods or commas.
		if(title.endsWith(",") || title.endsWith(".")){
			title = title.substring(0, title.length()-1);
		}
		return title;
	}
	
	
	// PRE: 
	// POST: trims and returns Place input.
	// PARAM: String Place.
	private static String citePlace(String place){
//		trim unnecessary spaces.
		place = place.trim();
		
		// trim unnecessary periods or commas.
		if(place.endsWith(",") || place.endsWith(".")){
			place = place.substring(0, place.length()-1);
		}
		return place;
	}

	
	// PRE: 
	// POST: trims and returns Publisher input.
	// PARAM: String Publisher.
	private static String citePublisher(String publisher){
//		trim unnecessary spaces.
		publisher = publisher.trim();

		// trim unnecessary periods or commas.
		if(publisher.endsWith(",") || publisher.endsWith(".")){
			publisher = publisher.substring(0, publisher.length()-1);
		}
		return publisher;
	}


	
	// PRE: 
	// POST: trims and returns Edition input.
	// PARAM: String Edition.
	private static String citeEdition(String edition){
//		trim unnecessary spaces.
		edition = edition.trim();

		// trim unnecessary periods or commas.
		if(edition.endsWith(",") || edition.endsWith(".")){
			edition = edition.substring(0, edition.length()-1);
		}
		
		// if its empty, return empty string.
		if(edition.equalsIgnoreCase(""))
		{
			return "";
		}
		
		// split the string up a bit, and detect obvious edition numbers.
		else{
			String[] editionSplit = edition.split(" ");
			
			if(editionSplit[0].equalsIgnoreCase("first")){
				return "1st";
			}
			else if(editionSplit[0].equalsIgnoreCase("second")){
				return "2nd";
			}
			else if(editionSplit[0].equalsIgnoreCase("third")){
				return "3rd";
			}
			else if(editionSplit[0].equalsIgnoreCase("fourth")){
				return "4th";
			}
			else if(editionSplit[0].equalsIgnoreCase("fifth")){
				return "5th";
			}
			else if(editionSplit[0].equalsIgnoreCase("sixth")){
				return "6th";
			}
			else if(editionSplit[0].equalsIgnoreCase("seventh")){
				return "7th";
			}
			else if(editionSplit[0].equalsIgnoreCase("eighth")){
				return "8th";
			}
			else if(editionSplit[0].equalsIgnoreCase("ninth")){
				return "9th";
			}
			else if(editionSplit[0].equalsIgnoreCase("tenth")){
				return "10th";
			}
			else if(editionSplit[0].equalsIgnoreCase("eleventh")){
				return "11th";
			}
			else if(editionSplit[0].equalsIgnoreCase("twelveth")){
				return "12th";
			}
			else if(editionSplit[0].equalsIgnoreCase("thirteenth")){
				return "13th";
			}
			else if(editionSplit[0].equalsIgnoreCase("fourteenth")){
				return "14th";
			}
			
			// if the string ends with certain suffixes, let it, but format it nicely.
			else if(editionSplit[0].endsWith("st") || editionSplit[0].endsWith("ST") || editionSplit[0].endsWith("St")){
				return editionSplit[0].substring(0, editionSplit[0].length()-2) + "st";
			}
			else if(editionSplit[0].endsWith("ND") || editionSplit[0].endsWith("nd")|| editionSplit[0].endsWith("nD")){
				return editionSplit[0].substring(0, editionSplit[0].length()-2) + "nd";
			}
			else if(editionSplit[0].endsWith("rd") || editionSplit[0].endsWith("RD")|| editionSplit[0].endsWith("Rd")){
				return editionSplit[0].substring(0, editionSplit[0].length()-2) + "rd";
			}
			else if(editionSplit[0].endsWith("th") || editionSplit[0].endsWith("TH") || editionSplit[0].endsWith("Th")){
				return editionSplit[0].substring(0, editionSplit[0].length()-2) + "th";
			}
			
			// if the word is really long, is a number, and does not have a typical suffix, lets give it one.
			else if(editionSplit[0].endsWith("1")){
				return editionSplit[0] + "st";
			}
			else if(editionSplit[0].endsWith("2")){
				return editionSplit[0] + "nd";
			}
			else if(editionSplit[0].endsWith("3")){
				return editionSplit[0] + "rd";
			}
			else if(editionSplit[0].endsWith("4") || editionSplit[0].endsWith("5") ||
					editionSplit[0].endsWith("6") || editionSplit[0].endsWith("7") || 
					editionSplit[0].endsWith("8") || editionSplit[0].endsWith("9") || 
					editionSplit[0].endsWith("0")){
				return editionSplit[0] + "th";
			}			
			else{
				
				// else, it could be a string or something, so lets just return it in hopes that they know what they're doing.
				return edition;
			}
		}
	}
	
}
