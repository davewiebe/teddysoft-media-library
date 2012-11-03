	/*
	 * 	AddEditVideoGameGUI.java
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

public class AddEditVideoGameGUI implements ActionListener {
	private static VideoGame game;
	private JTextField title, developer, year, maxPlayers;
	private ButtonGroup ratinggroup;
	private JComboBox ratedList, platform; 
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
		//Platform
		JLabel platformlabel = new JLabel("Platform:");
		String[] platforms = { "N/A", "PC", "DS", "GameCube", "GBA", "Wii", "PSP", "PS2", "PS3", "XBox", "XBox 360", "Other" };
		platform = new JComboBox(platforms);
		platform.setSelectedIndex(0);
		platform.setMaximumSize(new Dimension(160, 22));
		platform.setAlignmentX(Component.LEFT_ALIGNMENT);		
		platform.addActionListener(this);
		
		//Rating combo box
		JLabel ratedlabel = new JLabel("Rated:");
		String[] rated = { "Not Rated", "EC: Early Childhood", "E: Everyone", "T: Teen", "M: Mature 17+", "AO: Adults Only 18+" };
		ratedList = new JComboBox(rated);
		ratedList.setSelectedIndex(0);
		ratedList.setMaximumSize(new Dimension(160, 22));
		ratedList.addActionListener(this);
		ratedList.setAlignmentX(Component.LEFT_ALIGNMENT);	
		
		//Information Panel: title, developer, year, maxPlayers;
		JPanel infopanel = new JPanel();
		infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.LINE_AXIS));	
		infopanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infopanel.setAlignmentY(Component.TOP_ALIGNMENT);
		infopanel.setBorder(BorderFactory.createTitledBorder(
        "Video Game Information"));
		
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
		
		//Developer
		JLabel developerlabel = new JLabel("Developer:");
		developer = new JTextField(20);
		developer.setMinimumSize(new Dimension(160, 20));
		developer.setMaximumSize(new Dimension(160, 20));
		developer.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Year
		JLabel yearlabel = new JLabel("Publishing year:");
		year = new JTextField(20);
		year.setMinimumSize(new Dimension(160, 20));
		year.setMaximumSize(new Dimension(160, 20));
		year.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Max players
		JLabel playerslabel = new JLabel("Max players:");
		maxPlayers = new JTextField(20);
		maxPlayers.setMinimumSize(new Dimension(160, 20));
		maxPlayers.setMaximumSize(new Dimension(160, 20));
		maxPlayers.setAlignmentX(Component.LEFT_ALIGNMENT);
		
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
		
		//AddVideoGame op=0, EditVideoGame op=1
		if (op == 1){
			//"N/A", "PC", "DS", "GameCube", "GBA", "Wii", "PSP", "PS2", "PS3", "XBox", "XBox 360"
			if (game.getPlatform().equals("PC")){
				platform.setSelectedIndex(1);
			}else if (game.getPlatform().equals("DS")){
				platform.setSelectedIndex(2);
			}else if (game.getPlatform().equals("GameCube")){
				platform.setSelectedIndex(3);
			}else if (game.getPlatform().equals("GBA")){
				platform.setSelectedIndex(4);
			}else if (game.getPlatform().equals("Wii")){
				platform.setSelectedIndex(5);
			}else if (game.getPlatform().equals("PSP")){
				platform.setSelectedIndex(6);
			}else if (game.getPlatform().equals("PS2")){
				platform.setSelectedIndex(7);
			}else if (game.getPlatform().equals("PS3")){
				platform.setSelectedIndex(8);
			}else if (game.getPlatform().equals("XBox")){
				platform.setSelectedIndex(9);
			}else if (game.getPlatform().equals("XBox 360")){
				platform.setSelectedIndex(10);			
			}else if (game.getPlatform().equals("Other")){
				platform.setSelectedIndex(11);						
			}else{
				platform.setSelectedIndex(0);
			}	
			
			//"Not Rated", "EC: Early Childhood", "E: Everyone", "T: Teen", "M: Mature 17+", "AO: Adults Only 18+"
			if (game.getContentRated().equals("EC: Early Childhood")){
				ratedList.setSelectedIndex(1);
			}else if (game.getContentRated().equals("E: Everyone")){
				ratedList.setSelectedIndex(2);
			}else if (game.getContentRated().equals("T: Teen")){
				ratedList.setSelectedIndex(3);
			}else if (game.getContentRated().equals("M: Mature 17+")){
				ratedList.setSelectedIndex(4);
			}else if (game.getContentRated().equals("AO: Adults Only 18+")){
				ratedList.setSelectedIndex(5);
			}else{
				ratedList.setSelectedIndex(0);
			}	
			
			//Rating
		    if (game.getRating() == 1){
		    	oneButton.setSelected(true);
		    }else if (game.getRating() == 2){
		    	twoButton.setSelected(true);
		    }else if (game.getRating() == 3){
		    	threeButton.setSelected(true);
		    }else if (game.getRating() == 4){
		    	fourButton.setSelected(true);
		    }else if (game.getRating() == 5){
		    	fiveButton.setSelected(true);
		    }
		    
		    title.setText(game.getTitle());
		    developer.setText(game.getdeveloper());
		    year.setText(game.getyear());
		    maxPlayers.setText(String.valueOf(game.getMaxPlayers()));
		    description.setText(game.getDescription());
		    review.setText(game.getReview());		    
		}


		labelpanel.add(Box.createRigidArea(new Dimension(0,5)));
		labelpanel.add(titlelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(developerlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(yearlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(playerslabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(platformlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(ratedlabel);
		
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(developer);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(year);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(maxPlayers);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(platform);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(ratedList);
				
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
		
		////"N/A", "PC", "DS", "GameCube", "GBA", "Wii", "PSP", "PS2", "PS3", "XBox", "XBox 360"
		String stringplatform = "";
		if (platform.getSelectedIndex() == 1){
			stringplatform = "PC";
		}
		else if(platform.getSelectedIndex() == 2){
			stringplatform = "DS";
		}
		else if(platform.getSelectedIndex() == 3){
			stringplatform = "GameCube";
		}
		else if(platform.getSelectedIndex() == 4){
			stringplatform = "GBA";
		}
		else if(platform.getSelectedIndex() == 5){
			stringplatform = "Wii";
		}
		else if(platform.getSelectedIndex() == 6){
			stringplatform = "PSP";
		}
		else if(platform.getSelectedIndex() == 7){
			stringplatform = "PS2";
		}
		else if(platform.getSelectedIndex() == 8){
			stringplatform = "PS3";
		}
		else if(platform.getSelectedIndex() == 9){
			stringplatform = "XBox";
		}
		else if(platform.getSelectedIndex() == 10){
			stringplatform = "XBox 360";
		}		
		else if(platform.getSelectedIndex() == 11){
			stringplatform = "Other";
		}	
		
		//"Not Rated", "EC: Early Childhood", "E: Everyone", "T: Teen", "M: Mature 17+", "AO: Adults Only 18+"
		String stringrating = "";
		if (ratedList.getSelectedIndex() == 1){
			stringrating = "EC: Early Childhood";
		}
		else if(ratedList.getSelectedIndex() == 2){
			stringrating = "E: Everyone";
		}
		else if(ratedList.getSelectedIndex() == 3){
			stringrating = "T: Teen";
		}
		else if(ratedList.getSelectedIndex() == 4){
			stringrating = "M: Mature 17+";
		}
		else if(ratedList.getSelectedIndex() == 5){
			stringrating = "AO: Adults Only 18+";
		}
		
		// On button Close
		if(e.getSource() == btnClose){
			Main.refreshJTable();
			frame.dispose();
		}
		
		int date = 0;
		if (!isNumber(year.getText())){
			date = 0;
		}else{
			date = Integer.parseInt(year.getText());
		}
		
		int players = 0;
		if (!isNumber(maxPlayers.getText())){
			players = 0;
		}else{
			players = Integer.parseInt(maxPlayers.getText());
		}
		
		//Saves a game (edit game)
		if(e.getSource() == btnSave){
/*			VideoGame newGame = new VideoGame(title.getText(),
					developer.getText(), String.valueOf(date),
					stringrating, stringplatform, rating, players);*/
			game.setTitle(title.getText());
			game.setDeveloper(developer.getText());
			game.setYear(String.valueOf(date));
			game.setContedRated(stringrating);
			game.setPlatform(stringplatform);
			game.setMaxPlayers(players);
			game.setRating(rating);
			game.setDescription(description.getText());
			game.setReview(review.getText());
						
			Main.refreshJTable();
			frame.dispose();
		}		
		
		// When game is Added
		else if(e.getSource() == btnAdd || e.getSource() == btnAnother){
						
			VideoGame newGame = new VideoGame(title.getText(),
					developer.getText(), String.valueOf(date),
					stringrating, stringplatform, description.getText(), review.getText(), rating, players);
					
			currentUser.getDB().addVideoGame(newGame);
			Main.writeData(); //serialize the new data
			
			// If "Add" was pressed
			if (e.getSource() == btnAdd){
				Main.refreshJTable();
				frame.dispose();
			}
			
			// If "Add another" was pressed, clear all information
			title.setText("");
			developer.setText("");
			maxPlayers.setText("");
			year.setText("");
			maxPlayers.setText("");
			oneButton.setSelected(true);
			ratedList.setSelectedIndex(0);
			platform.setSelectedIndex(0);
			description.setText("");
			review.setText("");
		}
	}	
	
	// PRE: need a user.
	// PARAM: User information parameter, so window knows which user it is.
	// POST: Creates window, will be able to edit users.
	public static void CreateGUI(User user, VideoGame currentgame, int operation){
		setWindowsLook(); //Set windows decorations
		currentUser = user;
		//Create and set up the window.
		op = operation;
		if (op == 0){
			frame = new JFrame("Add Video Game");
		}else{
			frame = new JFrame("Edit Video Game");
			game = currentgame;
		}
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {//add Window closing handler
            public void windowClosing(WindowEvent e) {
            	Main.enableButtons();
            	frame.dispose();
            	}
        });
		
        AddEditVideoGameGUI app = new AddEditVideoGameGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
		frame.setSize(460,580); // make frame 640x460
		frame.setLocationRelativeTo(null); //centers window
		frame.pack();
		frame.setVisible(true);

	}	
	
	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CreateGUI(null,null,0);
			}
		});
	}
}
