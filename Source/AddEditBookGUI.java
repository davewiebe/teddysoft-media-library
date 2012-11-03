	/*
	 * 	AddEditBookGUI.java
	 * 	
	 * 	Written by Frankie Yan
	 * 	Edited by David Wiebe and Jordan McMillan
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 * 	Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */
package teddySoft;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AddEditBookGUI implements ActionListener {
	private static Books books;
	private JTextField title, author, edition, publisher, date, place, isbn;
	private ButtonGroup ratinggroup;
	private JComboBox genreList; 
	private JButton btnAdd, btnAnother, btnClose;
	private JButton btnSave, btnCancel;
	private static JFrame frame;
	private JTextArea description, review;
	private JRadioButton oneButton, twoButton, threeButton, fourButton, fiveButton;
	private static User currentUser;
	private static int op; //0=add, 1=edit
	
	public static void setWindowsLook(){
	    try{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    // Set System L&F
	    }catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }catch (ClassNotFoundException e){
	       // handle exception
	    }catch (InstantiationException e){
	       // handle exception
	    }catch (IllegalAccessException e){
	       // handle exception
	    }		
	}	
	
	private Component mainWindowComponents() {
		//Genre combo box
		JLabel genrelabel = new JLabel("Genre:");
		String[] genres = { "N/A", "Action", "Adventure", "Children", "Comedy", "Fantasy", "Horror", "Mystery", "Romance", "Science Fiction" };
		genreList = new JComboBox(genres);
		genreList.setSelectedIndex(0);
		genreList.setMaximumSize(new Dimension(160, 22));
		genreList.setAlignmentX(Component.LEFT_ALIGNMENT);		
		genreList.addActionListener(this);
		
		//Information Panel: title, author, edition, pubDate, pubLocation, isbn
		JPanel infopanel = new JPanel();
		infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.LINE_AXIS));	
		infopanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infopanel.setAlignmentY(Component.TOP_ALIGNMENT);
		infopanel.setBorder(BorderFactory.createTitledBorder(
        "Book Information"));
		
		//Label Panel
		JPanel labelpanel = new JPanel();
		labelpanel.setLayout(new BoxLayout(labelpanel, BoxLayout.PAGE_AXIS));	
		labelpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//Text Panel
		JPanel textpanel = new JPanel();
		textpanel.setLayout(new BoxLayout(textpanel, BoxLayout.PAGE_AXIS));	
		textpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		textpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//Title
		JLabel titlelabel = new JLabel("Title:");
		title = new JTextField(20);
		title.setMinimumSize(new Dimension(160, 20));
		title.setMaximumSize(new Dimension(160, 20));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Author
		JLabel authorlabel = new JLabel("Author:");
		author = new JTextField(20);
		author.setMinimumSize(new Dimension(160, 20));
		author.setMaximumSize(new Dimension(160, 20));
		author.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel authorhelp = new JLabel("For multiple authors, seperate");
		JLabel authorhelp2 = new JLabel("authors with a semicolon (;).");
		JLabel authorhelp3 = new JLabel("(Eg. Stephen A. Smith; John B. Jones)");
		authorhelp.setFont(new Font("Tahoma", Font.PLAIN, 9));
		authorhelp2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		authorhelp3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		//Edition
		JLabel editionlabel = new JLabel("Edition:");
		edition = new JTextField(20);
		edition.setMinimumSize(new Dimension(160, 20));
		edition.setMaximumSize(new Dimension(160, 20));
		edition.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Publisher
		JLabel publishlabel = new JLabel("Publisher:");
		publisher = new JTextField(20);
		publisher.setMinimumSize(new Dimension(160, 20));
		publisher.setMaximumSize(new Dimension(160, 20));
		publisher.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Date
		JLabel datelabel = new JLabel("Publishing year:");
		date = new JTextField(20);
		date.setMinimumSize(new Dimension(160, 20));
		date.setMaximumSize(new Dimension(160, 20));
		date.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Location
		JLabel placelabel = new JLabel("Publishing place:");
		place = new JTextField(20);
		place.setMinimumSize(new Dimension(160, 20));
		place.setMaximumSize(new Dimension(160, 20));
		place.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//ISBN
		JLabel isbnlabel = new JLabel("ISBN number:");
		isbn = new JTextField(20);
		isbn.setMinimumSize(new Dimension(160, 20));
		isbn.setMaximumSize(new Dimension(160, 20));
		isbn.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Rating Panel
		JPanel ratepanel = new JPanel();
		ratepanel.setLayout(new BoxLayout(ratepanel, BoxLayout.PAGE_AXIS));	
		ratepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		ratepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		ratepanel.setBorder(BorderFactory.createTitledBorder(
        "Rating"));
		
		//Mid Panel
		JPanel midpanel = new JPanel();
		midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.LINE_AXIS));	
		midpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		midpanel.setAlignmentY(Component.TOP_ALIGNMENT);
				
		//Radio Buttons
	    oneButton = new JRadioButton("1 Star");
	    oneButton.setActionCommand("One");
	    oneButton.addActionListener(this);
	    oneButton.setSelected(true);

	    twoButton = new JRadioButton("2 Stars");
	    oneButton.setActionCommand("Two");
	    oneButton.addActionListener(this);
	    
	    threeButton = new JRadioButton("3 Stars");
	    oneButton.setActionCommand("Three");
	    oneButton.addActionListener(this);
	    
	    fourButton = new JRadioButton("4 Stars");
	    oneButton.setActionCommand("Four");
	    oneButton.addActionListener(this);
	    
	    fiveButton = new JRadioButton("5 Stars");
	    oneButton.setActionCommand("Five");
	    oneButton.addActionListener(this);
	    
	    ratinggroup = new ButtonGroup();
	    ratinggroup.add(oneButton);
	    ratinggroup.add(twoButton);
	    ratinggroup.add(threeButton);
	    ratinggroup.add(fourButton);
	    ratinggroup.add(fiveButton);
		
		//Description Panel
		JPanel descpanel = new JPanel();
		descpanel.setLayout(new BoxLayout(descpanel, BoxLayout.PAGE_AXIS));	
		descpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		descpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		descpanel.setBorder(BorderFactory.createTitledBorder(
        "Description"));
		
		description = new JTextArea(6, 20);
		description.setLineWrap(true);
		JScrollPane descscroll = new JScrollPane(description);
		
		//Review Panel
		JPanel revpanel = new JPanel();
		revpanel.setLayout(new BoxLayout(revpanel, BoxLayout.PAGE_AXIS));	
		revpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		revpanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		revpanel.setBorder(BorderFactory.createTitledBorder(
        "Review"));

				
		review = new JTextArea(6, 20);
		review.setLineWrap(true);
		JScrollPane reviewscroll = new JScrollPane(review);
				
		//Button panel
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.LINE_AXIS));	
		buttonpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonpanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		
		btnAdd = new JButton("Add");
		btnAdd.setMaximumSize(new Dimension(120, 23));
		btnAdd.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnAdd.setActionCommand("Add");
		btnAdd.addActionListener(this);
		
		btnAnother = new JButton("Add another");
		btnAnother.setMaximumSize(new Dimension(120, 23));
		btnAnother.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnAnother.setActionCommand("Another");
		btnAnother.addActionListener(this);
		
		btnClose = new JButton("Close");
		btnClose.setMaximumSize(new Dimension(120, 23));
		btnClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnClose.setActionCommand("Close");
		btnClose.addActionListener(this);
		
		btnSave = new JButton("Save");
		btnSave.setMaximumSize(new Dimension(120, 23));
		btnSave.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setMaximumSize(new Dimension(120, 23));
		btnCancel.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnCancel.setActionCommand("Cancel");
		btnCancel.addActionListener(this);		
		
		//Main Panel
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));	
		mainpanel.setAlignmentX(Component.CENTER_ALIGNMENT);	
		mainpanel.setBorder(BorderFactory.createEmptyBorder(
                20, //top
                20, //left
                20, //bottom
                20) //right
                );	
		
		//Addbook op=0, Editbook op=1
		if (op == 1){
			//Genre
			if (books.getGenre().equals("Action")){
				genreList.setSelectedIndex(1);
			}else if (books.getGenre().equals("Adventure")){
				genreList.setSelectedIndex(2);
			}else if (books.getGenre().equals("Children")){
				genreList.setSelectedIndex(3);
			}else if (books.getGenre().equals("Comedy")){
				genreList.setSelectedIndex(4);
			}else if (books.getGenre().equals("Fantasy")){
				genreList.setSelectedIndex(5);
			}else if (books.getGenre().equals("Horror")){
				genreList.setSelectedIndex(6);
			}else if (books.getGenre().equals("Mystery")){
				genreList.setSelectedIndex(7);
			}else if (books.getGenre().equals("Romance")){
				genreList.setSelectedIndex(8);
			}else if (books.getGenre().equals("Science Fiction")){
				genreList.setSelectedIndex(9);
			}else{
				genreList.setSelectedIndex(0);
			}	
			
			//Rating
		    if (books.getRating() == 1){
		    	oneButton.setSelected(true);
		    }else if (books.getRating() == 2){
		    	twoButton.setSelected(true);
		    }else if (books.getRating() == 3){
		    	threeButton.setSelected(true);
		    }else if (books.getRating() == 4){
		    	fourButton.setSelected(true);
		    }else if (books.getRating() == 5){
		    	fiveButton.setSelected(true);
		    }
		    
		    title.setText(books.getTitle());
		    author.setText(books.getAuthor());
		    edition.setText(books.getEdition());
		    publisher.setText(books.getPublication());
		    date.setText(books.getPubDate());
		    place.setText(books.getPubLocation());
		    isbn.setText(books.getIsbn());
		    description.setText(books.getDescription());
		    review.setText(books.getReview());		    
		}


		labelpanel.add(Box.createRigidArea(new Dimension(0,5)));
		labelpanel.add(titlelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(genrelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(authorlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,45)));
		labelpanel.add(editionlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(publishlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(datelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(placelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(isbnlabel);
		
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(genreList);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(author);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(authorhelp);
		textpanel.add(authorhelp2);
		textpanel.add(authorhelp3);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(edition);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(publisher);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(date);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(place);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(isbn);
		
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));
		infopanel.add(labelpanel);
		infopanel.add(Box.createRigidArea(new Dimension(2,0)));
		infopanel.add(textpanel);
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));
				
		ratepanel.add(oneButton);
		ratepanel.add(twoButton);
		ratepanel.add(threeButton);
		ratepanel.add(fourButton);
		ratepanel.add(fiveButton);
		
		midpanel.add(infopanel);
		midpanel.add(ratepanel);
		
		descpanel.add(descscroll);
				
		//revpanel.add(review);
		revpanel.add(reviewscroll);
				
		buttonpanel.add(Box.createHorizontalGlue());
		
		if (op == 0){
			buttonpanel.add(btnAdd);
			buttonpanel.add(btnAnother);
			buttonpanel.add(btnClose);
		}else{
			//buttonpanel.add(btnGenCite);
			buttonpanel.add(btnSave);
			buttonpanel.add(btnCancel);
		}
		
		mainpanel.add(midpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(descpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(revpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(buttonpanel);
		
		return mainpanel;
	}
	
	public boolean isNumber(String string) {
		if (string.equals("")) return false;
		for (int i = 0;i < string.length();i++) {
			if (!Character.isDigit(string.charAt(i))) {
		    	return false;
		    }
		}
		return true;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel){
			frame.dispose();
		}
		//set rating from 1-5 when button is pressed.
		int rating = 1;
		if (oneButton.isSelected() == true){
			rating = 1;
		}
		else if(twoButton.isSelected() == true){
			rating = 2;
		}
		else if(threeButton.isSelected() == true){
			rating = 3;
		}
		else if(fourButton.isSelected() == true){
			rating = 4;
		}
		else if(fiveButton.isSelected() == true){
			rating = 5;
		}
		
		//Set Genre when action happens
		String genre = "";
		if (genreList.getSelectedIndex() == 1){
			genre = "Action";
		}
		else if(genreList.getSelectedIndex() == 2){
			genre = "Adventure";
		}
		else if(genreList.getSelectedIndex() == 3){
			genre = "Children";
		}
		else if(genreList.getSelectedIndex() == 4){
			genre = "Comedy";
		}
		else if(genreList.getSelectedIndex() == 5){
			genre = "Fantasy";
		}
		else if(genreList.getSelectedIndex() == 6){
			genre = "Horror";
		}
		else if(genreList.getSelectedIndex() == 7){
			genre = "Mystery";
		}
		else if(genreList.getSelectedIndex() == 8){
			genre = "Romance";
		}
		else if(genreList.getSelectedIndex() == 9){
			genre = "Science Fiction";
		}
		
		// On button Close
		if(e.getSource() == btnClose){
			Main.refreshJTable();
			frame.dispose();
		}
		
		int year = 0;
		if (!isNumber(date.getText())){
			year = 0;
		}else{
			year = Integer.parseInt(date.getText());
		}
		
		//Saves a book (edit book)
		if(e.getSource() == btnSave){
			books.setTitle(title.getText());
			books.setAuthor(author.getText());
			books.setEdition(edition.getText());
			books.setPubDate(String.valueOf(year));
			books.setPubLocation(place.getText());
			books.setIsbn(isbn.getText());
			books.setGenre(genre);
			books.setRating(rating);
			books.setDescription(description.getText());
			books.setReview(review.getText());
			books.setPublication(publisher.getText());
			
			Main.refreshJTable();
			frame.dispose();
		}		
		
		// When Book is Added
		else if(e.getSource() == btnAdd || e.getSource() == btnAnother){
						
			Books newBook = new Books(title.getText(),author.getText(), edition.getText(),
					String.valueOf(year), place.getText(), isbn.getText(), genre, 
					rating, description.getText(), review.getText(), publisher.getText());
					
			currentUser.getDB().addBook(newBook);
			Main.writeData(); //serialize the new data
			
			// If "Add" was pressed
			if (e.getSource() == btnAdd){
				Main.refreshJTable();
				frame.dispose();
			}
			
			// If "Add another" was pressed, clear all information
			title.setText("");
			author.setText("");
			date.setText("");
			edition.setText("");
			place.setText("");
			isbn.setText("");
			oneButton.setSelected(true);
			genreList.setSelectedIndex(0);
			description.setText("");
			review.setText("");
			publisher.setText("");
		}
	}	
	
	// PRE: need a user.
	// PARAM: User information parameter, so window knows which user it is.
	// POST: Creates window, will be able to edit users.
	public static void CreateGUI(User user, Books currentbook, int operation){
		setWindowsLook(); //Set windows decorations
		currentUser = user;
		//Create and set up the window.
		op = operation;
		if (op == 0){
			frame = new JFrame("Add Book");
		}else{
			frame = new JFrame("Edit Book");
			books = currentbook;
		}
		
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {//add Window closing handler
            public void windowClosing(WindowEvent e) {
            	Main.enableButtons();
            	frame.dispose();
            	}
        });
		
        AddEditBookGUI app = new AddEditBookGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
		frame.setSize(460,580); // make frame 640x460
		frame.setLocationRelativeTo(null); //centers window
		frame.pack();
		frame.setVisible(true);

	}	
	
/*	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CreateGUI(null,null,0);
			}
		});
	}*/
}
