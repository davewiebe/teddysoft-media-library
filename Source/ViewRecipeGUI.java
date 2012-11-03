	/*
	 * 	ViewRecipeGUI.java
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
import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewRecipeGUI implements ActionListener {
	
	private static Recipe recipes;
	private JButton btnClose, btnFile;
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
		JLabel title = new JLabel();
		if (!recipes.getTitle().equals("")){
			title.setText(recipes.getTitle());
		}else{
			title.setText("N/A");
		}
		title.setFont(new Font("Tahoma", Font.BOLD, 11));
		title.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Rating Panel
		JPanel ratepanel = new JPanel();
		ratepanel.setLayout(new BoxLayout(ratepanel, BoxLayout.PAGE_AXIS));	
		ratepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		ratepanel.setAlignmentY(Component.TOP_ALIGNMENT);
		ratepanel.setBorder(BorderFactory.createTitledBorder(
        "Rating"));
		
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
	    if (recipes.getRating() != 1){
	    	oneButton.setEnabled(false);
	    }	    
	    if (recipes.getRating() != 2){
	    	twoButton.setEnabled(false);
	    }	    
	    if (recipes.getRating() != 3){
	    	threeButton.setEnabled(false);
	    }
	    if (recipes.getRating() != 4){
	    	fourButton.setEnabled(false);
	    }
	    if (recipes.getRating() != 5){
	    	fiveButton.setEnabled(false);
	    }
	    
	    if (recipes.getRating() == 1){
	    	oneButton.setSelected(true);
	    }	    
	    if (recipes.getRating() == 2){
	    	twoButton.setSelected(true);
	    }	    
	    if (recipes.getRating() == 3){
	    	threeButton.setSelected(true);
	    }
	    if (recipes.getRating() == 4){
	    	fourButton.setSelected(true);
	    }
	    if (recipes.getRating() == 5){
	    	fiveButton.setSelected(true);
	    }
	    ButtonGroup ratinggroup = new ButtonGroup();
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
		
		JTextArea ingredients = new JTextArea(4, 20);
		ingredients.setLineWrap(true);
		ingredients.setText(recipes.getIngredients());
		ingredients.setEditable(false);
		JScrollPane ingredientsscroll = new JScrollPane(ingredients);	    
		
		//instructions Panel
		JPanel instructionspanel = new JPanel();
		instructionspanel.setLayout(new BoxLayout(instructionspanel, BoxLayout.PAGE_AXIS));	
		instructionspanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		instructionspanel.setAlignmentY(Component.TOP_ALIGNMENT);
		instructionspanel.setBorder(BorderFactory.createTitledBorder(
        "Instructions"));
		
		JTextArea instructions = new JTextArea(8, 20);
		instructions.setLineWrap(true);
		instructions.setText(recipes.getInstructions());
		instructions.setEditable(false);
		JScrollPane instructionsscroll = new JScrollPane(instructions);
		
		//description Panel
		JPanel descriptionpanel = new JPanel();
		descriptionpanel.setLayout(new BoxLayout(descriptionpanel, BoxLayout.PAGE_AXIS));	
		descriptionpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		descriptionpanel.setAlignmentY(Component.TOP_ALIGNMENT);
		descriptionpanel.setBorder(BorderFactory.createTitledBorder(
        "Description"));
		
		JTextArea description = new JTextArea(8, 20);
		description.setLineWrap(true);
		description.setText(recipes.getDescription());
		description.setEditable(false);
		JScrollPane descriptionscroll = new JScrollPane(description);
		
		//Review Panel
		JPanel revpanel = new JPanel();
		revpanel.setLayout(new BoxLayout(revpanel, BoxLayout.PAGE_AXIS));	
		revpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		revpanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		revpanel.setBorder(BorderFactory.createTitledBorder(
        "Review"));
				
		JTextArea review = new JTextArea(3, 20);
		review.setLineWrap(true);
		review.setText(recipes.getReview());
		review.setEditable(false);
		JScrollPane reviewscroll = new JScrollPane(review);
		
		//Button panel
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.LINE_AXIS));	
		buttonpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonpanel.setAlignmentY(Component.TOP_ALIGNMENT);	
		
		btnFile = new JButton("Output text file");
		btnFile.setMaximumSize(new Dimension(180, 23));
		btnFile.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnFile.setActionCommand("Output");
		btnFile.addActionListener(this);
		
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
		buttonpanel.add(btnFile);
		buttonpanel.add(btnClose);
		
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
		
		// if close button pressed
		if(e.getSource() == btnClose){
			frame.dispose();
		}
		else if(e.getSource() == btnFile){
			try{
			String[] lines;
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(Main.getCurrentUser().getName()+"-"+recipes.getTitle() + ".txt"));
			outputStream.write(recipes.getTitle());
			outputStream.newLine();
			outputStream.newLine();
			outputStream.write("Description:");
			outputStream.newLine();
			
			lines = recipes.getDescription().split("\n");
			for (int i=0;i<lines.length; i++){
			outputStream.write(lines[i]);
			outputStream.newLine();
			}
			outputStream.newLine();
			
			outputStream.write("Ingredients:");
			outputStream.newLine();
			
			lines = recipes.getIngredients().split("\n");
			for (int i=0;i<lines.length; i++){
			outputStream.write("-"+lines[i]);
			outputStream.newLine();
			}
			outputStream.newLine();
			
			outputStream.write("Instructions:");
			outputStream.newLine();
			
			lines = recipes.getInstructions().split("\n");
			for (int i=0;i<lines.length; i++){
			outputStream.write(lines[i]);
			outputStream.newLine();
			}
			outputStream.close();
			}
			catch(IOException IOe){
				System.out.println("Error writeing "+ recipes.getTitle() + ".txt");
			}
		}
	}	
	
	public static void CreateGUI(Recipe recipe){
		setWindowsLook(); //Set windows decorations
		recipes = recipe;
		
		//Create and set up the window.
		frame = new JFrame("View Recipe");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        ViewRecipeGUI app = new ViewRecipeGUI();
        Component contents = app.mainWindowComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
		
		//Display the window.
        frame.setSize(520,640); // 460 520 // make frame 640x460
		frame.setLocationRelativeTo(null); //centers window
        //frame.pack();
		frame.setVisible(true);

	}	
	
//	public static void Test(){
/*	public static void main(String[] args){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				CreateGUI(new Recipe ("title", "ingredients",
						"instructions", "description", "review", 4)
				);
			}
		});
	}*/
}

