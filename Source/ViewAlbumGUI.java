	/*
	 * 	ViewAlbumGUI.java
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
 * albums.getTitle();
 * albums.getArtist();
 * albums.getReleaseDate();
 * int albums.getNumTracks();
 * albums.getLabel();
 * albums.getFormat();
 * albums.getGenre();
 * albums.getRating();
 * albums.getDescription();
 * albums.getReview();*/

package teddySoft;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ViewAlbumGUI implements ActionListener {
	
	private static Albums albums;
	private JButton btnClose;
	private static JFrame frame;
	private static Image scaleImage;
	private static JLabel attachment;
	
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
	
	private Component mainWindowComponents() {
		
		//Information Panel: title, author, edition, pubDate, pubLocation, isbn
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
		imagepanel.add(attachment);
		
		//Title
		JLabel titlelabel = new JLabel("Title:");
		JLabel title = new JLabel();
		if (!albums.getTitle().equals("")){
			title.setText(albums.getTitle());
		}else{
			title.setText("N/A");
		}
		title.setFont(new Font("Tahoma", Font.BOLD, 11));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Artist
		JLabel artistlabel = new JLabel("Artist:");
		JLabel artist = new JLabel();
		if (!albums.getArtist().equals("")){
			artist.setText(albums.getArtist());
		}else{
			artist.setText("N/A");
		}
		artist.setFont(new Font("Tahoma", Font.BOLD, 11));
		artist.setAlignmentX(Component.LEFT_ALIGNMENT);	
				
		//ReleaseDate
		JLabel datelabel = new JLabel("Release Date:");
		JLabel ReleaseDate = new JLabel();
		if (!albums.getReleaseDate().equals("")){
			ReleaseDate.setText(albums.getReleaseDate());
		}else{
			ReleaseDate.setText("N/A");
		}
		ReleaseDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		ReleaseDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//NumTracks
		JLabel trackslabel = new JLabel("Number of Tracks:");
		JLabel NumTracks = new JLabel();
		if (!albums.getTracks().equals("")){
			NumTracks.setText(albums.getTracks());
		}else{
			NumTracks.setText("N/A");
		}
		NumTracks.setFont(new Font("Tahoma", Font.BOLD, 11));
		NumTracks.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Label
		JLabel labellabel = new JLabel("Label:");
		JLabel Label = new JLabel();
		if (!albums.getLabel().equals("")){
			Label.setText(albums.getLabel());
		}else{
			Label.setText("N/A");
		}
		Label.setFont(new Font("Tahoma", Font.BOLD, 11));
		Label.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Format
		JLabel formatlabel = new JLabel("Format:");
		JLabel Format = new JLabel();
		if (!albums.getFormat().equals("")){
			Format.setText(albums.getFormat());
		}else{
			Format.setText("N/A");
		}
		Format.setFont(new Font("Tahoma", Font.BOLD, 11));
		Format.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Genre
		JLabel genrelabel = new JLabel("Genre:");
		JLabel Genre = new JLabel();
		if (!albums.getGenre().equals("")){
			Genre.setText(albums.getGenre());
		}else{
			Genre.setText("N/A");
		}
		Genre.setFont(new Font("Tahoma", Font.BOLD, 11));
		Genre.setAlignmentX(Component.LEFT_ALIGNMENT);
		
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
	    if (albums.getRating() != 1){
	    	oneButton.setEnabled(false);
	    }	    
	    if (albums.getRating() != 2){
	    	twoButton.setEnabled(false);
	    }	    
	    if (albums.getRating() != 3){
	    	threeButton.setEnabled(false);
	    }
	    if (albums.getRating() != 4){
	    	fourButton.setEnabled(false);
	    }
	    if (albums.getRating() != 5){
	    	fiveButton.setEnabled(false);
	    }
	    
	    if (albums.getRating() == 1){
	    	oneButton.setSelected(true);
	    }	    
	    if (albums.getRating() == 2){
	    	twoButton.setSelected(true);
	    }	    
	    if (albums.getRating() == 3){
	    	threeButton.setSelected(true);
	    }
	    if (albums.getRating() == 4){
	    	fourButton.setSelected(true);
	    }
	    if (albums.getRating() == 5){
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
		description.setText(albums.getDescription());
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
		review.setText(albums.getReview());
		review.setEditable(false);
		JScrollPane reviewscroll = new JScrollPane(review);
				
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

		//title, artist, releaseDate, numberTracks, Label, genre, format
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
		
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(artist);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(ReleaseDate);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(NumTracks);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(Label);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(Genre);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(Format);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));		
		infopanel.add(labelpanel);
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));
		infopanel.add(textpanel);
		infopanel.add(Box.createRigidArea(new Dimension(5,0)));
		infopanel.add(imagepanel);
		//infopanel.add(Box.createHorizontalGlue());
				
		ratepanel.add(oneButton);
		ratepanel.add(twoButton);
		ratepanel.add(threeButton);
		ratepanel.add(fourButton);
		ratepanel.add(fiveButton);
		
		midpanel.add(infopanel);
		midpanel.add(ratepanel);
		
		descpanel.add(descscroll);
				
		revpanel.add(reviewscroll);
				
		buttonpanel.add(Box.createHorizontalGlue());
		buttonpanel.add(btnClose);
		
		mainpanel.add(midpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(descpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(revpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
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
	
	public static void CreateGUI(Albums album){
		setWindowsLook(); //Set windows decorations
		albums = album;
		scaleImage = null;
		
		//Create and set up the window.
		frame = new JFrame("View Album");
		
		scaleImage = null;
		BufferedImage img = null;
		try {
			//System.out.println("Album num is "+albums.getAlbumNum());
			img = ImageIO.read(new File(Main.getCurrentUser().getName()+"-"+"album_"+albums.getAlbumNum()+".jpg"));
			scaleImage = (Image) img;
		} catch (IOException e) {}
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        ViewAlbumGUI app = new ViewAlbumGUI();
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
				CreateGUI(new Albums
						("", "", "", 
								"1", "", 
								"", "", 
								3, "", "1", "")
				);
			}
		});
	}*/
}

