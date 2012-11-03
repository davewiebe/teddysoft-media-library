	/*
	 * 	AddEditAlbumGUI.java
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

/* NEEDS:
 * albums.setTitle();
 * albums.setArtist();
 * albums.setReleaseDate();
 * int albums.setNumTracks();
 * albums.setLabel();
 * albums.setFormat();
 * albums.setGenre();
 * albums.setRating();
 * albums.setDescription();
 * albums.setReview();*/


package teddySoft;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.image.*;

import javax.imageio.ImageIO;

public class AddEditAlbumGUI implements ActionListener, ImageObserver {
	private static Albums albums;
	private JTextField title, artist, releaseDate, numberTracks, label;
	private ButtonGroup ratinggroup;
	private JComboBox genreList, formatList; 
	private JButton btnAdd, btnAnother, btnClose, btnAttach;
	private JButton btnSave, btnCancel;
	private static JFrame frame;
	private JTextArea description, review;
	private JRadioButton oneButton, twoButton, threeButton, fourButton, fiveButton;
	private static User currentUser;
	private static int op; //0=add, 1=edit
	private static Image scaleImage;
	private static JLabel attachment;
	private static Component contents;
	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, 
			int width, int height) 
	{
		return true;	  
	} 
	
	
	/** Returns an ImageIcon, or null if the path was invalid. (From Java Swing tutorial)*/
	protected static ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = LoginGUI.class.getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}	
	
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
		String[] genres = { "N/A", "Children's", "Classical", "Country", "Dance", "Folk", "Hip-Hop", "Instrumental", "Jazz", "Misc", "Pop", "R&B", "Rock", "Soundtracks"  };
		genreList = new JComboBox(genres);
		genreList.setSelectedIndex(0);
		genreList.setMaximumSize(new Dimension(160, 22));
		genreList.setAlignmentX(Component.LEFT_ALIGNMENT);		
		genreList.addActionListener(this);
		
		//Media combo box
		JLabel formatlabel = new JLabel("Format:");
		String[] format = { "N/A", "Audio CD", "Cassette", "DVD-Audio", "Vinyl" };
		formatList = new JComboBox(format);
		formatList.setSelectedIndex(0);
		formatList.setMaximumSize(new Dimension(160, 22));
		formatList.setAlignmentX(Component.LEFT_ALIGNMENT);		
		formatList.addActionListener(this);		
		
		//Information Panel: title, artist, releaseDate, numberTracks, Label, genre, format
		JPanel infopanel = new JPanel();
		infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.LINE_AXIS));	
		infopanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infopanel.setAlignmentY(Component.TOP_ALIGNMENT);
		infopanel.setBorder(BorderFactory.createTitledBorder(
        "Album Information"));
		
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
		
		//Image Panel
		JPanel imagepanel = new JPanel();
		imagepanel.setLayout(new BoxLayout(imagepanel, BoxLayout.PAGE_AXIS));	
		imagepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		imagepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		if (scaleImage != null){
			ImageIcon picture = new ImageIcon(scaleImage);
			attachment = new JLabel(picture);
			attachment.setBorder(BorderFactory.createEtchedBorder());
			attachment.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		else{
		ImageIcon picture = createImageIcon("null.gif","null");
		attachment = new JLabel(picture);
		attachment.setBorder(BorderFactory.createEtchedBorder());
		attachment.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		btnAttach = new JButton("Attach picture");
		btnAttach.setMaximumSize(new Dimension(120, 23));
		btnAttach.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAttach.setActionCommand("Attach");
		btnAttach.addActionListener(this);
		
		//if (op == 1){
		//	JLabel attachment = new JLabel(picture);
		//	btnAttach.setText("Edit picture");
		//	btnAttach.setText("Remove picture");
		//}
		
		//Title
		JLabel titlelabel = new JLabel("Title:");
		title = new JTextField(20);
		title.setMinimumSize(new Dimension(160, 20));
		title.setMaximumSize(new Dimension(160, 20));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Author
		JLabel artistlabel = new JLabel("Artist:");
		artist = new JTextField(20);
		artist.setMinimumSize(new Dimension(160, 20));
		artist.setMaximumSize(new Dimension(160, 20));
		artist.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Label
		JLabel labellabel = new JLabel("Label:");
		label = new JTextField(20);
		label.setMinimumSize(new Dimension(160, 20));
		label.setMaximumSize(new Dimension(160, 20));
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//release
		JLabel datelabel = new JLabel("Release Date:");
		releaseDate = new JTextField(20);
		releaseDate.setMinimumSize(new Dimension(160, 20));
		releaseDate.setMaximumSize(new Dimension(160, 20));
		releaseDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//numtracks
		JLabel trackslabel = new JLabel("Number of Tracks:");
		numberTracks = new JTextField(20);
		numberTracks.setMinimumSize(new Dimension(160, 20));
		numberTracks.setMaximumSize(new Dimension(160, 20));
		numberTracks.setAlignmentX(Component.LEFT_ALIGNMENT);
			
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
		
		//title, artist, releaseDate, numberTracks, Label, genre, format
		//Addalbum op=0, Editalbum op=1
		if (op == 1){
//			Genre: "N/A", "Children's", "Classical", "Country", "Dance", "Folk", "Hip-Hop", "Instrumental", "Jazz", "Misc", "Pop", "R&B", "Rock", "Soundtracks"
			if (albums.getGenre().equals("Children's")){
				genreList.setSelectedIndex(1);
			}else if (albums.getGenre().equals("Classical")){
				genreList.setSelectedIndex(2);
			}else if (albums.getGenre().equals("Country")){
				genreList.setSelectedIndex(3);
			}else if (albums.getGenre().equals("Dance")){
				genreList.setSelectedIndex(4);
			}else if (albums.getGenre().equals("Folk")){
				genreList.setSelectedIndex(5);
			}else if (albums.getGenre().equals("Hip-Hop")){
				genreList.setSelectedIndex(6);
			}else if (albums.getGenre().equals("Instrumental")){
				genreList.setSelectedIndex(7);
			}else if (albums.getGenre().equals("Jazz")){
				genreList.setSelectedIndex(8);
			}else if (albums.getGenre().equals("Misc")){
				genreList.setSelectedIndex(9);
			}else if (albums.getGenre().equals("Pop")){
				genreList.setSelectedIndex(10);
			}else if (albums.getGenre().equals("R&B")){
				genreList.setSelectedIndex(11);
			}else if (albums.getGenre().equals("Rock")){
				genreList.setSelectedIndex(12);
			}else if (albums.getGenre().equals("Soundtracks")){
				genreList.setSelectedIndex(13);				
			}else{
				genreList.setSelectedIndex(0);
			}	
			
			//format "N/A", "Audio CD", "Cassette", "DVD-Audio", "Vinyl"
			if (albums.getFormat().equals("Audio CD")){
				formatList.setSelectedIndex(1);
			}else if (albums.getFormat().equals("Cassette")){
				formatList.setSelectedIndex(2);
			}else if (albums.getFormat().equals("DVD-Audio")){
				formatList.setSelectedIndex(3);
			}else if (albums.getFormat().equals("Vinyl")){
				formatList.setSelectedIndex(4);
			}else{
				formatList.setSelectedIndex(0);
			}				
			
			//Rating
		    if (albums.getRating() == 1){
		    	oneButton.setSelected(true);
		    }else if (albums.getRating() == 2){
		    	twoButton.setSelected(true);
		    }else if (albums.getRating() == 3){
		    	threeButton.setSelected(true);
		    }else if (albums.getRating() == 4){
		    	fourButton.setSelected(true);
		    }else if (albums.getRating() == 5){
		    	fiveButton.setSelected(true);
		    }
//		  title, artist, releaseDate, numberTracks, Label, genre, format
		    title.setText(albums.getTitle());
		    artist.setText(albums.getArtist());
		    releaseDate.setText(albums.getReleaseDate());
		    numberTracks.setText(albums.getTracks());
		    label.setText(albums.getLabel());
		    description.setText(albums.getDescription());
		    review.setText(albums.getReview());
		}


		labelpanel.add(Box.createRigidArea(new Dimension(0,5)));
		labelpanel.add(titlelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(artistlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(datelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(trackslabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(labellabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(genrelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(formatlabel);
		
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(artist);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(releaseDate);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(numberTracks);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(label);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(genreList);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(formatList);
		
		imagepanel.add(attachment);
		imagepanel.add(Box.createRigidArea(new Dimension(0,5)));
		imagepanel.add(btnAttach);
		
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));
		infopanel.add(labelpanel);
		infopanel.add(Box.createRigidArea(new Dimension(2,0)));
		infopanel.add(textpanel);
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));
		infopanel.add(imagepanel);		
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
		//Genre: "N/A", "Children's", "Classical", "Country", "Dance", "Folk", "Hip-Hop", "Instrumental", 
		//"Jazz", "Misc", "Pop", "R&B", "Rock", "Soundtracks"
		String genre = "";
		if (genreList.getSelectedIndex() == 1){
			genre = "Children's";
		}
		else if(genreList.getSelectedIndex() == 2){
			genre = "Classical";
		}
		else if(genreList.getSelectedIndex() == 3){
			genre = "Country";
		}
		else if(genreList.getSelectedIndex() == 4){
			genre = "Dance";
		}
		else if(genreList.getSelectedIndex() == 5){
			genre = "Folk";
		}
		else if(genreList.getSelectedIndex() == 6){
			genre = "Hip-Hop";
		}
		else if(genreList.getSelectedIndex() == 7){
			genre = "Instrumental";
		}
		else if(genreList.getSelectedIndex() == 8){
			genre = "Jazz";
		}
		else if(genreList.getSelectedIndex() == 9){
			genre = "Misc";
		}
		else if(genreList.getSelectedIndex() == 10){
			genre = "Pop";
		}
		else if(genreList.getSelectedIndex() == 11){
			genre = "R&B";
		}
		else if(genreList.getSelectedIndex() == 12){
			genre = "Rock";
		}		
		else if(genreList.getSelectedIndex() == 13){
			genre = "Soundtracks";
		}			
		
		//Set Format when action happens
		//format "N/A", "Audio CD", "Cassette", "DVD-Audio", "Vinyl"
		String format = "";
		if(formatList.getSelectedIndex() == 0){
			format = "N/A";
		}
		else if (formatList.getSelectedIndex() == 1){
			format = "Audio CD";
		}
		else if(formatList.getSelectedIndex() == 2){
			format = "Cassette";
		}
		else if(formatList.getSelectedIndex() == 3){
			format = "DVD-Audio";
		}
		else if(formatList.getSelectedIndex() == 4){
			format = "Vinyl";
		}
		
		// On button Close
		if(e.getSource() == btnClose){
			Main.refreshJTable();
			frame.dispose();
		}
		
		int tracks = 0;
		if (!isNumber(numberTracks.getText())){
			tracks = 0;
		}else{
			tracks = Integer.parseInt(numberTracks.getText());
		}
		
		//Saves a album (edit album)
		//title, artist, releaseDate, numberTracks, Label, genre, format

		if(e.getSource() == btnSave){
			albums.setTitle(title.getText());
			albums.setArtist(artist.getText());
			albums.setReleaseDate(releaseDate.getText());
			albums.setTracks(String.valueOf(tracks));
			albums.setLabel(label.getText());
			albums.setFormat(format);
			albums.setGenre(genre);
			albums.setRating(rating);
			albums.setDescription(description.getText());
			albums.setReview(review.getText());
			
	        BufferedImage bi = new BufferedImage(scaleImage.getWidth(this),scaleImage.getHeight(this),BufferedImage.TYPE_INT_BGR);
	        bi.getGraphics().drawImage(this.scaleImage,0,0,null);
	        File outputfile = new File(currentUser.getName()+"-"+"album_"+albums.getAlbumNum()+".jpg"); 
	        try{
	        	ImageIO.write(bi, "jpg", outputfile);
	        }catch (IOException ex){System.out.println("Cannot write image to hard drive.");}
			
			Main.refreshJTable();
			frame.dispose();
		}	
		
		if(e.getSource() == btnAttach){
			frame.getContentPane().setVisible(false);
			frame.getContentPane().removeAll();
	        AddEditAlbumGUI app = new AddEditAlbumGUI();
	        Image i = ImageFilter.getInputImage();
	        scaleImage = i;
	        app.scaleImage = i;
	        contents = app.mainWindowComponents();
			frame.getContentPane().add(contents, BorderLayout.CENTER);
			app.genreList.setSelectedIndex(this.genreList.getSelectedIndex());
			app.formatList.setSelectedIndex(this.formatList.getSelectedIndex());
			app.oneButton.setSelected(this.oneButton.isSelected());	
			app.twoButton.setSelected(this.twoButton.isSelected());	
			app.threeButton.setSelected(this.threeButton.isSelected());	
			app.fourButton.setSelected(this.fourButton.isSelected());	
			app.fiveButton.setSelected(this.fiveButton.isSelected());	
			app.title.setText(this.title.getText());
			app.artist.setText(this.artist.getText());
			app.releaseDate.setText(this.releaseDate.getText());
			app.numberTracks.setText(this.numberTracks.getText());
			app.label.setText(this.label.getText());
			app.description.setText(this.description.getText());
			app.review.setText(this.review.getText());
			
			frame.getContentPane().setVisible(true);
		}
		
		// When Album is Added
		else if(e.getSource() == btnAdd || e.getSource() == btnAnother){
			if (scaleImage != null){
				BufferedImage bi = new BufferedImage(scaleImage.getWidth(this),scaleImage.getHeight(this),BufferedImage.TYPE_INT_BGR);
	        	bi.getGraphics().drawImage(this.scaleImage,0,0,null);
	        	File outputfile = new File(currentUser.getName()+"-"+"album_"+currentUser.getDB().getAlbumInt()+".jpg"); 
	        	try{
	        	ImageIO.write(bi, "jpg", outputfile);
	        	}catch (IOException ex){System.out.println("Cannot write image to hard drive.");}
			}
	        System.out.println("album num is: "+currentUser.getDB().getAlbumInt());
			Albums newAlbum = new Albums(title.getText(),artist.getText(), releaseDate.getText(),
					String.valueOf(tracks), label.getText(), format, genre, 
					rating, description.getText(), review.getText(), currentUser.getDB().getAlbumInt() );
					
			currentUser.getDB().addAlbum(newAlbum);
			Main.writeData(); //serialize the new data
			currentUser.getDB().incAlbNum();
			System.out.println("album num after inc: "+currentUser.getDB().getAlbumInt());
			
			// If "Add" was pressed
			if (e.getSource() == btnAdd){
				Main.refreshJTable();
				frame.dispose();
			}
			
			// If "Add another" was pressed, clear all information
			title.setText("");
			artist.setText("");
			releaseDate.setText("");
			numberTracks.setText("");
			label.setText("");
			oneButton.setSelected(true);
			genreList.setSelectedIndex(0);
			formatList.setSelectedIndex(0);			
			description.setText("");
			review.setText("");
		}
	}	
	
	// PRE: need a user.
	// PARAM: User information parameter, so window knows which user it is.
	// POST: Creates window, will be able to edit users.
	public static void CreateGUI(User user, Albums currentalbum, int operation){
		setWindowsLook(); //Set windows decorations
		currentUser = user;
		//Create and set up the window.
		op = operation;
		if (op == 0){
			frame = new JFrame("Add Album");
			scaleImage = null;
		}else{
			frame = new JFrame("Edit Album");
			albums = currentalbum;
			scaleImage = null;
			BufferedImage img = null;
			try {
				//System.out.println("Album num is "+albums.getAlbumNum());
				img = ImageIO.read(new File(currentUser.getName()+"-"+"album_"+albums.getAlbumNum()+".jpg"));
				scaleImage = (Image) img;
			} catch (IOException e) {}
		}
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {//add Window closing handler
            public void windowClosing(WindowEvent e) {
            	Main.enableButtons();
            	frame.dispose();
            	}
        });
		
        AddEditAlbumGUI app = new AddEditAlbumGUI();
        contents = app.mainWindowComponents();
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
