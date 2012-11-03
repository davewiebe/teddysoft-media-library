	/*
	 * 	unusedAddBookGUI.java
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class unusedAddBookGUI implements ActionListener {
	
	private JTextField title, author, edition, date, place, isbn;
	private ButtonGroup ratinggroup;
	private JComboBox genreList; 
	private JButton btnAdd, btnAnother, btnClose;
	private static JFrame frame;
	private JTextArea description, review;
	private JRadioButton oneButton, twoButton, threeButton, fourButton, fiveButton;
	private static User currentUser;
	
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
		//Genre Combobox Panel
		JPanel genrepanel = new JPanel();
		genrepanel.setLayout(new BoxLayout(genrepanel, BoxLayout.PAGE_AXIS));	
		genrepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		genrepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//Genre combo box
		String[] genres = { "Select book genre...", "Action", "Adventure", "Children", "Comedy", "Fantasy", "Horror", "Mystery", "Romance", "Science Fiction" };
		genreList = new JComboBox(genres);
		genreList.setSelectedIndex(0);
		genreList.setMaximumSize(new Dimension(240, 22));
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
		
		//Edition
		JLabel editionlabel = new JLabel("Edition:");
		edition = new JTextField(20);
		edition.setMinimumSize(new Dimension(160, 20));
		edition.setMaximumSize(new Dimension(160, 20));
		edition.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Date
		JLabel datelabel = new JLabel("Publishing date:");
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


		genrepanel.add(genreList);
		
		labelpanel.add(Box.createRigidArea(new Dimension(0,5)));
		labelpanel.add(titlelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(authorlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(editionlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(datelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(placelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(isbnlabel);
		
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(author);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(edition);
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
		buttonpanel.add(btnAdd);
		buttonpanel.add(btnAnother);
		buttonpanel.add(btnClose);
		
		mainpanel.add(genrepanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(midpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(descpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(revpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(buttonpanel);
		
		return mainpanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		
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
		
		// When Book is Added
		else if(e.getSource() == btnAdd || e.getSource() == btnAnother){
						
			Books newBook = new Books(title.getText(),author.getText(), edition.getText(),
					date.getText(), place.getText(), isbn.getText(), genre, 
					rating, description.getText(), review.getText(), "publication place");
					
			currentUser.getDB().addBook(newBook);
			
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
		}
	}	
	
	// PRE: need a user.
	// PARAM: User information parameter, so window knows which user it is.
	// POST: Creates window, will be able to edit users.
	public static void CreateGUI(User user){
	//public static void CreateGUI(){
		setWindowsLook(); //Set windows decorations
		currentUser = user;
		//Create and set up the window.
		frame = new JFrame("Add Book");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        unusedAddBookGUI app = new unusedAddBookGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
		frame.pack();
		frame.setSize(460,520); // make frame 640x460
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); //centers window

	}	
	
/*	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CreateGUI();
			}
		});
	}*/
}
