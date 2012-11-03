	/*
	 * 	AddEditDVDGUI.java
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
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.awt.image.*;

public class AddEditDVDGUI implements ActionListener, ItemListener{
	private static DVD dvds;
	private JTextField title, director, year, runningtime, format;
	private ButtonGroup ratinggroup;
	private JComboBox ratedList; 
	private JButton btnAdd, btnAnother, btnClose;
	private JButton btnSave, btnCancel;
	private JCheckBox btnWidescreen;
	private static JFrame frame;
	private JTextArea description, review;
	private JRadioButton oneButton, twoButton, threeButton, fourButton, fiveButton;
	private static User currentUser;
	private static int op; //0=add, 1=edit
	private boolean isWideScreen;
	private static Image scaleImage;
	
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
		//String title, String director, String year, String contentRated, 
		//String runningTime, String format, boolean isWideScreen,  int rating
		
		JPanel infopanel = new JPanel();
		infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.LINE_AXIS));	
		infopanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infopanel.setAlignmentY(Component.TOP_ALIGNMENT);
		infopanel.setBorder(BorderFactory.createTitledBorder(
        "DVD Information"));
		
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
		
		//Information Panel: title, director, year, rated, runningtime, format, bool widescreen,rating
		//Title
		JLabel titlelabel = new JLabel("Title:");
		title = new JTextField(20);
		title.setMinimumSize(new Dimension(160, 20));
		title.setMaximumSize(new Dimension(160, 20));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Director
		JLabel directorlabel = new JLabel("Director:");
		director = new JTextField(20);
		director.setMinimumSize(new Dimension(160, 20));
		director.setMaximumSize(new Dimension(160, 20));
		director.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Year
		JLabel yearlabel = new JLabel("Year:");
		year = new JTextField(20);
		year.setMinimumSize(new Dimension(160, 20));
		year.setMaximumSize(new Dimension(160, 20));
		year.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//runningtime
		JLabel runningtimelabel = new JLabel("Running Time:");
		runningtime = new JTextField(20);
		runningtime.setMinimumSize(new Dimension(160, 20));
		runningtime.setMaximumSize(new Dimension(160, 20));
		runningtime.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//format
		JLabel formatlabel = new JLabel("Format:");
		format = new JTextField(20);
		format.setMinimumSize(new Dimension(160, 20));
		format.setMaximumSize(new Dimension(160, 20));
		format.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Rating combo box
		JLabel ratedlabel = new JLabel("Rated:");
		String[] rated = { "Not Rated", "G", "PG", "PG-13", "R", "NC-17" };
		ratedList = new JComboBox(rated);
		ratedList.setSelectedIndex(0);
		ratedList.setMaximumSize(new Dimension(100, 20));
		ratedList.addActionListener(this);
		ratedList.setAlignmentX(Component.LEFT_ALIGNMENT);	
		
		//Widescreen checkbox
		btnWidescreen = new JCheckBox("Widescreen");
		btnWidescreen.setSelected(false);
		isWideScreen = false;
		btnWidescreen.addItemListener(this);
				
		//Rating Panel
		JPanel ratepanel = new JPanel();
		ratepanel.setLayout(new BoxLayout(ratepanel, BoxLayout.PAGE_AXIS));	
		ratepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		ratepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		ratepanel.setBorder(BorderFactory.createTitledBorder(
        "Rating"));
		
		//Right Panel
		JPanel rightpanel = new JPanel();
		rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.PAGE_AXIS));	
		rightpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		rightpanel.setAlignmentY(Component.TOP_ALIGNMENT - 50);
		rightpanel.add(Box.createRigidArea(new Dimension(7,8)));
		if (scaleImage != null){
			ImageIcon icon = new ImageIcon(scaleImage);
			JLabel art = new JLabel(icon);
			art.setBorder(BorderFactory.createEtchedBorder());
			rightpanel.add(art);
			rightpanel.add(Box.createHorizontalGlue());
		}
		
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
		
		if (op == 1){
			
		    if (dvds.getRating() == 1){
		    	oneButton.setSelected(true);
		    }else if (dvds.getRating() == 2){
		    	twoButton.setSelected(true);
		    }else if (dvds.getRating() == 3){
		    	threeButton.setSelected(true);
		    }else if (dvds.getRating() == 4){
		    	fourButton.setSelected(true);
		    }else if (dvds.getRating() == 5){
		    	fiveButton.setSelected(true);
		    }			
		    
		    btnWidescreen.setSelected(dvds.getIsWideScreen());
		    isWideScreen = dvds.getIsWideScreen();

		    //"Not Rated", "G", "PG", "PG-13", "R", "NC-17"
			if (dvds.getContentRated().equals("G")){
				ratedList.setSelectedIndex(1);
			}else if (dvds.getContentRated().equals("PG")){
				ratedList.setSelectedIndex(2);
			}else if (dvds.getContentRated().equals("PG-13")){
				ratedList.setSelectedIndex(3);
			}else if (dvds.getContentRated().equals("R")){
				ratedList.setSelectedIndex(4);
			}else if (dvds.getContentRated().equals("NC-17")){
				ratedList.setSelectedIndex(5);
			}else{
				ratedList.setSelectedIndex(0);
			}	
		    
			title.setText(dvds.getTitle());
			director.setText(dvds.getdirector()); 
			year.setText(dvds.getyear()); 
			runningtime.setText(dvds.getRunningTime()); 
			format.setText(dvds.getFormat());
		    description.setText(dvds.getDescription());
		    review.setText(dvds.getReview());				
			
		}

		labelpanel.add(Box.createRigidArea(new Dimension(0,5)));
		labelpanel.add(titlelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(directorlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(yearlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(runningtimelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(formatlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(ratedlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(director);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(year);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(runningtime);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(format);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(ratedList);
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(btnWidescreen);		
		
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
		midpanel.add(rightpanel);
		
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
		
		//mainpanel.add(Box.createRigidArea(new Dimension(0,100)));
		mainpanel.add(midpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(descpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(revpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(buttonpanel);
		
		return mainpanel;
	}
	
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if (source == btnWidescreen) {
			isWideScreen = true;
		  	if (e.getStateChange() == ItemEvent.DESELECTED) {
	    		isWideScreen = false;
	     	}
		}
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
		
		//Set rated when action happens
		String contentRated = "";
		if (ratedList.getSelectedIndex() == 1){
			contentRated = "G";
		}
		else if(ratedList.getSelectedIndex() == 2){
			contentRated = "PG";
		}
		else if(ratedList.getSelectedIndex() == 3){
			contentRated = "PG-13";
		}
		else if(ratedList.getSelectedIndex() == 4){
			contentRated = "R";
		}
		else if(ratedList.getSelectedIndex() == 5){
			contentRated = "NC-17";
		}
		else{
			contentRated = "Not Rated";
		}
		
		// On button Close
		if(e.getSource() == btnClose){
			Main.refreshJTable();
			frame.dispose();
		}
		// When DVD is saved
		else if(e.getSource() == btnSave){
/*			DVD newDVD = new DVD(title.getText(),director.getText(), year.getText(), contentRated,
					runningtime.getText(), review.getText(), description.getText(), format.getText(), isWideScreen, rating);*/
			   
		    dvds.setTitle(title.getText());
		    dvds.setDirector(director.getText());
		    dvds.setYear(year.getText());
		    dvds.setContedRated(contentRated);
		    dvds.setRunningTime(runningtime.getText());
		    dvds.setFormat(format.getText());
		    dvds.setDescription(description.getText());
		    dvds.setReview(review.getText());
		    dvds.setIsWideScreen(isWideScreen);
		    dvds.setRating(rating);
			
		    Main.refreshJTable();
			frame.dispose();		    
		}
		
		// When DVD is Added
		else if(e.getSource() == btnAdd || e.getSource() == btnAnother){
						
			DVD newDVD = new DVD(title.getText(),director.getText(), year.getText(), contentRated,
					runningtime.getText(), review.getText(), description.getText(), format.getText(), isWideScreen, rating);
					
			currentUser.getDB().addDVD(newDVD);
			Main.writeData(); //serialize the new data
			
			// If "Add" was pressed
			if (e.getSource() == btnAdd){
				Main.refreshJTable();
				frame.dispose();
			}
			
			// If "Add another" was pressed, clear all information
			title.setText("");
			director.setText("");
			runningtime.setText("");
			year.setText("");
			format.setText("");
			oneButton.setSelected(true);
			ratedList.setSelectedIndex(0);
			description.setText("");
			review.setText("");
			btnWidescreen.setSelected(false);
			isWideScreen = false; 
		}
	}	
	
	// PRE: need a user.
	// PARAM: User information parameter, so window knows which user it is.
	// POST: Creates window, will be able to edit users.
	public static void CreateGUI(User user, DVD currentdvd, int operation){
		setWindowsLook(); //Set windows decorations
		currentUser = user;
		op = operation;
		//Create and set up the window.
		if (op == 0){
			frame = new JFrame("Add DVD");
		}else{
			frame = new JFrame("Edit DVD");
			dvds = currentdvd;
		}
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {//add Window closing handler
            public void windowClosing(WindowEvent e) {
            	Main.enableButtons();
            	frame.dispose();
            	}
        });
		
        
        AddEditDVDGUI app = new AddEditDVDGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
		frame.setSize(460,520); // make frame 640x460
		frame.setLocationRelativeTo(null); //centers window
		frame.pack();
		frame.setVisible(true);

	}	
	
//	public static void main(String[] args){
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run(){
//				CreateGUI();
//			}
//		});
//	}
}
