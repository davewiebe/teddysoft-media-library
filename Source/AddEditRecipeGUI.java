	/*
	 * 	AddEditRecipeGUI.java
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

public class AddEditRecipeGUI implements ActionListener {
	private static Recipe recipes;
	private JTextField title;
	private ButtonGroup ratinggroup;
	private JButton btnAdd, btnAnother, btnClose;
	private JButton btnSave, btnCancel;
	private static JFrame frame;
	private JTextArea ingredients, description, instructions, review;
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
		
		//title Panel
		JPanel titlepanel = new JPanel();
		titlepanel.setLayout(new BoxLayout(titlepanel, BoxLayout.LINE_AXIS));	
		titlepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		titlepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//topleft Panel
		JPanel topleftpanel = new JPanel();
		topleftpanel.setLayout(new BoxLayout(topleftpanel, BoxLayout.PAGE_AXIS));	
		topleftpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		topleftpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//top Panel
		JPanel toppanel = new JPanel();
		toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.LINE_AXIS));	
		toppanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		toppanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//Mid Panel
		JPanel midpanel = new JPanel();
		midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.LINE_AXIS));	
		midpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		midpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		//Title
		JLabel titlelabel = new JLabel("Title:");
		title = new JTextField(20);
		title.setMinimumSize(new Dimension(240, 20));
		title.setMaximumSize(new Dimension(240, 20));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
	
		//Rating Panel
		JPanel ratepanel = new JPanel();
		ratepanel.setLayout(new BoxLayout(ratepanel, BoxLayout.PAGE_AXIS));	
		ratepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		ratepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		ratepanel.setBorder(BorderFactory.createTitledBorder(
        "Rating"));
		
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
		
		//ingredients Panel
		JPanel ingredientspanel = new JPanel();
		ingredientspanel.setLayout(new BoxLayout(ingredientspanel, BoxLayout.PAGE_AXIS));	
		ingredientspanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		ingredientspanel.setAlignmentY(Component.TOP_ALIGNMENT);
		ingredientspanel.setBorder(BorderFactory.createTitledBorder(
        "Ingredients"));
		
		ingredients = new JTextArea(4, 20);
		ingredients.setLineWrap(true);
		JScrollPane ingredientsscroll = new JScrollPane(ingredients);
		
		//instructions Panel
		JPanel instructionspanel = new JPanel();
		instructionspanel.setLayout(new BoxLayout(instructionspanel, BoxLayout.PAGE_AXIS));	
		instructionspanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		instructionspanel.setAlignmentY(Component.TOP_ALIGNMENT);
		instructionspanel.setBorder(BorderFactory.createTitledBorder(
        "Instructions"));
		
		instructions = new JTextArea(8, 20);
		instructions.setLineWrap(true);
		JScrollPane instructionsscroll = new JScrollPane(instructions);
		
		//description Panel
		JPanel descriptionpanel = new JPanel();
		descriptionpanel.setLayout(new BoxLayout(descriptionpanel, BoxLayout.PAGE_AXIS));	
		descriptionpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		descriptionpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		descriptionpanel.setBorder(BorderFactory.createTitledBorder(
        "Description"));
		
		description = new JTextArea(8, 10);
		description.setLineWrap(true);
		JScrollPane descriptionscroll = new JScrollPane(description);
		
		//Review Panel
		JPanel revpanel = new JPanel();
		revpanel.setLayout(new BoxLayout(revpanel, BoxLayout.PAGE_AXIS));	
		revpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		revpanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		revpanel.setBorder(BorderFactory.createTitledBorder(
        "Review"));

		review = new JTextArea(3, 20);
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
		
		//Add op=0, Edit op=1
		if (op == 1){
						
			//Rating
		    if (recipes.getRating() == 1){
		    	oneButton.setSelected(true);
		    }else if (recipes.getRating() == 2){
		    	twoButton.setSelected(true);
		    }else if (recipes.getRating() == 3){
		    	threeButton.setSelected(true);
		    }else if (recipes.getRating() == 4){
		    	fourButton.setSelected(true);
		    }else if (recipes.getRating() == 5){
		    	fiveButton.setSelected(true);
		    }
		    
		    title.setText(recipes.getTitle());
		    ingredients.setText(recipes.getIngredients());
		    instructions.setText(recipes.getInstructions());
		    description.setText(recipes.getDescription());
		    review.setText(recipes.getReview());		    
		}


		titlepanel.add(titlelabel);
		titlepanel.add(Box.createRigidArea(new Dimension(5,0)));
		titlepanel.add(title);
		
		topleftpanel.add(titlepanel);
		topleftpanel.add(Box.createRigidArea(new Dimension(0,5)));
		ingredientspanel.add(ingredientsscroll);
		topleftpanel.add(ingredientspanel);
		
		ratepanel.add(oneButton);
		ratepanel.add(twoButton);
		ratepanel.add(threeButton);
		ratepanel.add(fourButton);
		ratepanel.add(fiveButton);
		
		toppanel.add(topleftpanel);
		toppanel.add(Box.createRigidArea(new Dimension(10,0)));
		toppanel.add(ratepanel);
		
		instructionspanel.add(instructionsscroll);
		descriptionpanel.add(descriptionscroll);
		midpanel.add(instructionspanel);
		midpanel.add(Box.createRigidArea(new Dimension(10,0)));
		midpanel.add(descriptionpanel);
		
		revpanel.add(reviewscroll);
		
		
		buttonpanel.add(Box.createHorizontalGlue());
		
		if (op == 0){
			buttonpanel.add(btnAdd);
			buttonpanel.add(btnAnother);
			buttonpanel.add(btnClose);
		}else{
			buttonpanel.add(btnSave);
			buttonpanel.add(btnCancel);
		}
		
		mainpanel.add(toppanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(midpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(revpanel);
		mainpanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainpanel.add(buttonpanel);
		
		return mainpanel;
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
		
		// On button Close
		if(e.getSource() == btnClose){
			Main.refreshJTable();
			frame.dispose();
		}
			
		//Saves a recipe (edit recipe)
		if(e.getSource() == btnSave){
			//Recipe newRecipe = new Recipe(title.getText(),ingredients.getText(), instructions.getText(),
			//		description.getText(), review.getText(), rating);
			
			recipes.setTitle(title.getText());
			recipes.setIngredients(ingredients.getText());
			recipes.setRating(rating);
			recipes.setInstructions(instructions.getText());
			recipes.setDescription(description.getText());
			recipes.setReview(review.getText());
						
			Main.refreshJTable();
			frame.dispose();
		}		
		
		// When Recipe is Added
		else if(e.getSource() == btnAdd || e.getSource() == btnAnother){
						
			Recipe newRecipe = new Recipe(title.getText(),ingredients.getText(), instructions.getText(),
					description.getText(), review.getText(), rating);
					
			currentUser.getDB().addRecipe(newRecipe);
			Main.writeData(); //serialize the new data
			
			// If "Add" was pressed
			if (e.getSource() == btnAdd){
				Main.refreshJTable();
				frame.dispose();
			}
			
			// If "Add another" was pressed, clear all information
			title.setText("");
			title.setText("");
			ingredients.setText("");
			instructions.setText("");
			description.setText("");
			review.setText("");
			oneButton.setSelected(true);
		}
	}	
	
	// PRE: need a user.
	// PARAM: User information parameter, so window knows which user it is.
	// POST: Creates window, will be able to edit users.
	public static void CreateGUI(User user, Recipe currentrecipe, int operation){
		setWindowsLook(); //Set windows decorations
		currentUser = user;
		//Create and set up the window.
		op = operation;
		if (op == 0){
			frame = new JFrame("Add Recipe");
		}else{
			frame = new JFrame("Edit Recipe");
			recipes = currentrecipe;
		}
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {//add Window closing handler
            public void windowClosing(WindowEvent e) {
            	Main.enableButtons();
            	frame.dispose();
            	}
        });
		
        AddEditRecipeGUI app = new AddEditRecipeGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
		frame.setSize(520,640); // make frame 640x460
		frame.setLocationRelativeTo(null); //centers window
		//frame.pack();
		frame.setVisible(true);

	}	
	
/*	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CreateGUI(0);
			}
		});
	}*/
}
