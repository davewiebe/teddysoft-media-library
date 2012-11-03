	/*
	 * 	ViewBookGUI.java
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
import teddySoft.Books;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewBookGUI implements ActionListener {
	
	private static Books books;
	private JButton btnClose;
	private static JFrame frame;
	private static GenCitation citation;
	
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
		citation = new GenCitation();
		
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
		JLabel title = new JLabel();
		if (!books.getTitle().equals("")){
			title.setText(books.getTitle());
		}else{
			title.setText("N/A");
		}
		title.setFont(new Font("Tahoma", Font.BOLD, 11));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Genre
		JLabel genrelabel = new JLabel("Genre:");
		JLabel genre = new JLabel();
		if (!books.getGenre().equals("")){
			genre.setText(books.getGenre());
		}else{
			genre.setText("N/A");
		}
		genre.setFont(new Font("Tahoma", Font.BOLD, 11));
		genre.setAlignmentX(Component.LEFT_ALIGNMENT);	
				
		//Author
		JLabel authorlabel = new JLabel("Author:");
		JLabel author = new JLabel();
		if (!books.getAuthor().equals("")){
			author.setText(books.getAuthor());
		}else{
			author.setText("N/A");
		}
		author.setFont(new Font("Tahoma", Font.BOLD, 11));
		author.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Edition
		JLabel editionlabel = new JLabel("Edition:");
		JLabel edition = new JLabel();
		if (!books.getEdition().equals("")){
			edition.setText(books.getEdition());
		}else{
			edition.setText("N/A");
		}
		edition.setFont(new Font("Tahoma", Font.BOLD, 11));
		edition.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Publisher
		JLabel publishlabel = new JLabel("Publisher:");
		JLabel publish = new JLabel();
		if (!books.getPublication().equals("")){
			publish.setText(books.getPublication());
		}else{
			publish.setText("N/A");
		}
		publish.setFont(new Font("Tahoma", Font.BOLD, 11));
		publish.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Date
		JLabel datelabel = new JLabel("Publishing date:");
		JLabel date = new JLabel();
		if (!books.getPubDate().equals("")){
			date.setText(books.getPubDate());
		}else{
			date.setText("N/A");
		}
		date.setFont(new Font("Tahoma", Font.BOLD, 11));
		date.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Location
		JLabel placelabel = new JLabel("Publishing place:");
		JLabel place = new JLabel();
		if (!books.getPubLocation().equals("")){
			place.setText(books.getPubLocation());
		}else{
			place.setText("N/A");
		}
		place.setFont(new Font("Tahoma", Font.BOLD, 11));
		place.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//ISBN
		JLabel isbnlabel = new JLabel("ISBN number:");
		JLabel isbn = new JLabel();
		if (!books.getIsbn().equals("")){
			isbn.setText(books.getIsbn());
		}else{
			isbn.setText("N/A");
		}
		isbn.setFont(new Font("Tahoma", Font.BOLD, 11));
		isbn.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Rating Panel
		JPanel ratepanel = new JPanel();
		ratepanel.setLayout(new BoxLayout(ratepanel, BoxLayout.PAGE_AXIS));	
		ratepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		ratepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		ratepanel.setBorder(BorderFactory.createTitledBorder("Rating"));
		
		//Mid Panel
		JPanel midpanel = new JPanel();
		midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.LINE_AXIS));	
		midpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		midpanel.setAlignmentY(Component.TOP_ALIGNMENT);
				
		//Radio Buttons
	    JRadioButton oneButton = new JRadioButton("1 Star");
	    oneButton.setActionCommand("One");
	    oneButton.addActionListener(this);

	    JRadioButton twoButton = new JRadioButton("2 Stars");
	    oneButton.setActionCommand("Two");
	    oneButton.addActionListener(this);
	    
	    JRadioButton threeButton = new JRadioButton("3 Stars");
	    oneButton.setActionCommand("Three");
	    oneButton.addActionListener(this);
	    
	    JRadioButton fourButton = new JRadioButton("4 Stars");
	    oneButton.setActionCommand("Four");
	    oneButton.addActionListener(this);
	    
	    JRadioButton fiveButton = new JRadioButton("5 Stars");
	    oneButton.setActionCommand("Five");
	    oneButton.addActionListener(this);
	    
	    
	    //Check for Rating
	    if (books.getRating() != 1){
	    	oneButton.setEnabled(false);
	    }	    
	    if (books.getRating() != 2){
	    	twoButton.setEnabled(false);
	    }	    
	    if (books.getRating() != 3){
	    	threeButton.setEnabled(false);
	    }
	    if (books.getRating() != 4){
	    	fourButton.setEnabled(false);
	    }
	    if (books.getRating() != 5){
	    	fiveButton.setEnabled(false);
	    }
	    
	    if (books.getRating() == 1){
	    	oneButton.setSelected(true);
	    }	    
	    if (books.getRating() == 2){
	    	twoButton.setSelected(true);
	    }	    
	    if (books.getRating() == 3){
	    	threeButton.setSelected(true);
	    }
	    if (books.getRating() == 4){
	    	fourButton.setSelected(true);
	    }
	    if (books.getRating() == 5){
	    	fiveButton.setSelected(true);
	    }
	    ButtonGroup ratinggroup = new ButtonGroup();
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
		
		JTextArea description = new JTextArea(4, 60);
		description.setLineWrap(true);
		description.setText(books.getDescription());
		description.setEditable(false);
		JScrollPane descscroll = new JScrollPane(description);
		
		//Review Panel
		JPanel revpanel = new JPanel();
		revpanel.setLayout(new BoxLayout(revpanel, BoxLayout.PAGE_AXIS));	
		revpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		revpanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		revpanel.setBorder(BorderFactory.createTitledBorder(
        "Review"));
				
		JTextArea review = new JTextArea(4, 60);
		review.setLineWrap(true);
		review.setText(books.getReview());
		review.setEditable(false);
		JScrollPane reviewscroll = new JScrollPane(review);
		
		//Citation Panel
		JPanel citepanel = new JPanel();
		citepanel.setLayout(new BoxLayout(citepanel, BoxLayout.PAGE_AXIS));	
		citepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		citepanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		citepanel.setBorder(BorderFactory.createTitledBorder(
        "Citation"));
				
		JTextArea cite = new JTextArea(1, 60);
		cite.setLineWrap(true);
		cite.setRows(3);
		cite.setText(citation.genAPA(books.getAuthor(), books.getPubDate(),
				books.getTitle(), books.getPubLocation(), books.getPublication(), books.getEdition()));
		JScrollPane citescroll = new JScrollPane(cite);		
				
		//Button panel
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.LINE_AXIS));	
		buttonpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonpanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		
		btnClose = new JButton("Close");
		btnClose.setMaximumSize(new Dimension(120, 23));
		btnClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnClose.setActionCommand("Close");
		btnClose.addActionListener(this);
		
		//Main Panel
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));	
		mainpanel.setAlignmentX(Component.LEFT_ALIGNMENT);	
		mainpanel.setBorder(BorderFactory.createEmptyBorder(
                20, //top
                20, //left
                20, //bottom
                20) //right
                );	


		labelpanel.add(Box.createRigidArea(new Dimension(0,5)));
		labelpanel.add(titlelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(genrelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(authorlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(editionlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(publishlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(datelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(placelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(isbnlabel);
		
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(genre);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(author);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(edition);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(publish);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(date);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(place);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(isbn);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));		
		
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));		
		infopanel.add(labelpanel);
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));
		infopanel.add(textpanel);
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));		
		infopanel.add(Box.createHorizontalGlue());
				
		ratepanel.add(oneButton);
		ratepanel.add(twoButton);
		ratepanel.add(threeButton);
		ratepanel.add(fourButton);
		ratepanel.add(fiveButton);
		
		midpanel.add(infopanel);
		midpanel.add(ratepanel);
		
		descpanel.add(descscroll);
				
		revpanel.add(reviewscroll);
		
		citepanel.add(citescroll);
				
		buttonpanel.add(Box.createHorizontalGlue());
		buttonpanel.add(btnClose);
		
		mainpanel.add(midpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(descpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(revpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(citepanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));		
		mainpanel.add(buttonpanel);
		
		return mainpanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// if close button pressed
		if(e.getSource() == btnClose){
			frame.dispose();
		}
	}	
	
	public static void CreateGUI(Books book){
		setWindowsLook(); //Set windows decorations
		books = book;
		
		//Create and set up the window.
		frame = new JFrame("View Book");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        ViewBookGUI app = new ViewBookGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
        frame.setSize(460,570); // 460 520 // make frame 640x460
		frame.setLocationRelativeTo(null); //centers window
        frame.pack();
		frame.setVisible(true);

	}	
	
//	public static void Test(){
/*	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CreateGUI(new Books
						("", "", "", 
								"1", "", 
								"", "", 
								3, "", "1", "")
				);
			}
		});
	}*/
}

