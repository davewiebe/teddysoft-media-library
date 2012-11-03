	/*
	 * 	ViewVideoGameGUI.java
	 * 	
	 * 	Written by Frankie Yan
	 * 	Edited by David Wiebe and Jordan McMillan
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 *  Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */

package teddySoft;
import javax.swing.*;
import teddySoft.VideoGame;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewVideoGameGUI implements ActionListener {
	
	private static VideoGame game;
	private JButton btnClose;
	private static JFrame frame;
	
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
	
		//Information Panel: title, author, edition, pubDate, pubLocation, isbn
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
		JLabel title = new JLabel();
		if (!game.getTitle().equals("")){
			title.setText(game.getTitle());
		}else{
			title.setText("N/A");
		}
		title.setFont(new Font("Tahoma", Font.BOLD, 11));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//developer
		JLabel developerlabel = new JLabel("Developer:");
		JLabel developer = new JLabel();
		if (!game.getdeveloper().equals("")){
			developer.setText(game.getdeveloper());
		}else{
			developer.setText("N/A");
		}
		developer.setFont(new Font("Tahoma", Font.BOLD, 11));
		developer.setAlignmentX(Component.LEFT_ALIGNMENT);	
				
		//Year
		JLabel yearlabel = new JLabel("Year:");
		JLabel year = new JLabel();
		if (!game.getyear().equals("")){
			year.setText(game.getyear());
		}else{
			year.setText("N/A");
		}
		year.setFont(new Font("Tahoma", Font.BOLD, 11));
		year.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//maxplayers
		JLabel maxPlayerslabel = new JLabel("Max Players:");
		JLabel maxPlayers = new JLabel();
		if (!String.valueOf(game.getMaxPlayers()).equals("")){
			maxPlayers.setText(""+game.getMaxPlayers());
		}else{
			maxPlayers.setText("N/A");
		}
		maxPlayers.setFont(new Font("Tahoma", Font.BOLD, 11));
		maxPlayers.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Platform
		JLabel platformlabel = new JLabel("Platform:");
		JLabel platform = new JLabel();
		if (!game.getPlatform().equals("")){
			platform.setText(game.getPlatform());
		}else{
			platform.setText("N/A");
		}
		platform.setFont(new Font("Tahoma", Font.BOLD, 11));
		platform.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Content rated
		JLabel ratedlabel = new JLabel("Content Rated:");
		JLabel rated = new JLabel();
		if (!game.getContentRated().equals("")){
			rated.setText(game.getContentRated());
		}else{
			rated.setText("Not Rated");
		}
		rated.setFont(new Font("Tahoma", Font.BOLD, 11));
		rated.setAlignmentX(Component.LEFT_ALIGNMENT);
		
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
	    if (game.getRating() != 1){
	    	oneButton.setEnabled(false);
	    }	    
	    if (game.getRating() != 2){
	    	twoButton.setEnabled(false);
	    }	    
	    if (game.getRating() != 3){
	    	threeButton.setEnabled(false);
	    }
	    if (game.getRating() != 4){
	    	fourButton.setEnabled(false);
	    }
	    if (game.getRating() != 5){
	    	fiveButton.setEnabled(false);
	    }
	    
	    if (game.getRating() == 1){
	    	oneButton.setSelected(true);
	    }	    
	    if (game.getRating() == 2){
	    	twoButton.setSelected(true);
	    }	    
	    if (game.getRating() == 3){
	    	threeButton.setSelected(true);
	    }
	    if (game.getRating() == 4){
	    	fourButton.setSelected(true);
	    }
	    if (game.getRating() == 5){
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
		description.setText(game.getDescription());
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
		review.setText(game.getReview());
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


		labelpanel.add(Box.createRigidArea(new Dimension(0,5)));
		labelpanel.add(titlelabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(developerlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(yearlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(maxPlayerslabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(platformlabel);
		labelpanel.add(Box.createRigidArea(new Dimension(0,10)));
		labelpanel.add(ratedlabel);
		
		textpanel.add(Box.createRigidArea(new Dimension(0,5)));
		textpanel.add(title);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(developer);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(year);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(maxPlayers);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(platform);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		textpanel.add(rated);
		textpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
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
	
	public static void CreateGUI(VideoGame currentgame){
		setWindowsLook(); //Set windows decorations
		game = currentgame;
		
		//Create and set up the window.
		frame = new JFrame("View Video Game");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        ViewVideoGameGUI app = new ViewVideoGameGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
        frame.setSize(460,570); // 460 520 // make frame 640x460
		frame.setLocationRelativeTo(null); //centers window
        frame.pack();
		frame.setVisible(true);

	}	
/*	
//	public static void Test(){
	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CreateGUI(new VideoGame
						("a", "b", "c", 
								"1", "d", 
								3, 4)
				);
			}
		});
	}*/
}

