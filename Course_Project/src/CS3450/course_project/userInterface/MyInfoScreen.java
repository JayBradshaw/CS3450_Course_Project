package CS3450.course_project.userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.databaseAccess;
import CS3450.course_project.userInterface.MainScreen;
import CS3450.course_project.dataAccess.Employee;

public class MyInfoScreen {
	private Employee employee;
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Edit My Info");
	/**
	 * pane to be contained in the frame
	 */
	private Container pane;
	/**
	 * image of a shopping card
	 */
	private ImageIcon cartImage = new ImageIcon("data/shoppingCart.png");
	/**
	 * stores the header for the pane
	 */
	private JLabel storeHeader = new JLabel();
	/**
	 * stores the footer for the pane
	 */
	private JLabel storeFooter = new JLabel("Copyright Info", JLabel.CENTER);
	/**
	 * list of products
	 */
	private MainScreen screen;
	/**
	 * primary color for the GUI
	 */
	private Color baseColor = new Color(180,242,110);
	/**
	 * secondary color for the GUI
	 */
	private Color secondaryColor = Color.GRAY.darker();
	/**
	 * base font for the header
	 */
	private Font baseFont = new Font("Verdana", Font.BOLD,16);
	/**
	 * font specifications for the buttons
	 */
	private Font buttonFont = new Font("Verdana", Font.PLAIN, 16);
	/**
	 * return to main screen, cancel changes.
	 */
	private JButton cancel = new JButton("Cancel");
	/**
	 * return to inventory screen, finalize changes.
	 */
	private JButton updateBtn = new JButton("Apply Update");
	/**
	 * input fields
	 */
	private TextField uName, uPassword, uIcon;
	/**
	 * labels for info
	 */
	private JLabel  nameLabel, passwordLabel, iconLabel;
	
	/**
	 * @param employeeList
	 * 
	 * non-default constructor
	 */
	public MyInfoScreen(databaseAccess databaseConnection){		
		uName 			= new TextField("");
		nameLabel = new JLabel("Username");
		uPassword 		= new TextField("");
		passwordLabel = new JLabel("Password");
		uIcon	= new TextField("");
		iconLabel = new JLabel("Icon Url");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		storeHeader.setLayout(new BoxLayout(storeHeader, BoxLayout.X_AXIS));
		JLabel icon1Label = new JLabel();
		JLabel textLabel = new JLabel("Mr. Smith's Groceries");
		textLabel.setFont(baseFont);
		JLabel icon2Label = new JLabel();
		icon1Label.setIcon(databaseConnection.getEmployee().getImage());
		icon1Label.setIconTextGap(25);
		icon2Label.setIcon(cartImage);
		icon2Label.setIconTextGap(25);
		storeHeader.add(icon1Label);
		storeHeader.add(Box.createRigidArea(new Dimension(5,0)));
		storeHeader.add(textLabel);
		storeHeader.add(Box.createRigidArea(new Dimension(5,0)));
		storeHeader.add(icon2Label);
		//copied exactly for UI consistency
		//make the header look pretty
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		storeHeader.setOpaque(true);
		//constraints for header
		GridBagConstraints h = new GridBagConstraints();
			h.fill = GridBagConstraints.BOTH;
			h.weightx = 1;
		    h.weighty = .16;
		    h.gridx = 0;
		    h.gridy = 0;
		    h.gridwidth = 3;
		    h.gridheight = 1;
			h.anchor = GridBagConstraints.PAGE_START;
				
		uName.setText(employee.getName());
		uPassword.setText(employee.getPassword());
		uIcon.setText(employee.getImageInfo());
		
		System.out.println("here");
		
		GridBagConstraints r = new GridBagConstraints();
			r.weightx = .16;
		    r.weighty = .08;
		    r.gridx = 1;
		    r.gridy = 1;
		    r.gridwidth = 2;
		    r.fill = GridBagConstraints.BOTH;
			    
	    //name textfield
		GridBagConstraints n = new GridBagConstraints();
			n.weightx = 1;
		    n.weighty = .08;
		    n.gridx = 2;
		    n.gridy = 3;
		    n.gridwidth = 2;
		    n.fill = GridBagConstraints.BOTH;
	    //name label
		GridBagConstraints nl = new GridBagConstraints();
			nl.weightx = 1;
		    nl.weighty = .08;
		    nl.gridx = 1;
		    nl.gridy = 3;
		    nl.gridwidth = 2;
		    nl.fill = GridBagConstraints.BOTH;
		    
	    //password textfield
		GridBagConstraints p = new GridBagConstraints();
			p.weightx = 1;
		    p.weighty = .08;
		    p.gridx = 2;
		    p.gridy = 4;
		    p.gridwidth = 2;
		    p.fill = GridBagConstraints.BOTH;
	    //password label
		GridBagConstraints pl = new GridBagConstraints();
			pl.weightx = 1;
		    pl.weighty = .08;
		    pl.gridx = 1;
		    pl.gridy = 4;
		    pl.gridwidth = 2;
		    pl.fill = GridBagConstraints.BOTH;
		    
	    //image Icon textfield
		GridBagConstraints i = new GridBagConstraints();
			i.weightx = .16;
		    i.weighty = .08;
		    i.gridx = 2;
		    i.gridy = 5;
		    i.gridwidth = 2;
		    i.fill = GridBagConstraints.BOTH;
	    //image Icon label
		GridBagConstraints il = new GridBagConstraints();
			il.weightx = .16;
		    il.weighty = .08;
		    il.gridx = 1;
		    il.gridy = 5;
		    il.gridwidth = 2;
		    il.fill = GridBagConstraints.BOTH;
			    
		    
		//returns to the inventory screen.
		cancel.setBackground(baseColor);
		cancel.setForeground(secondaryColor);
	    cancel.setFont(buttonFont);
	    cancel.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
	    cancel.addActionListener(
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							
							frame.dispose();
							screen = new MainScreen(databaseConnection);
						}
						
			});
		    
		    GridBagConstraints c = new GridBagConstraints();
		    	c.fill = GridBagConstraints.BOTH;
		    	c.weightx = 1;
		    	c.weighty = .16;
		    	c.gridx = 1;
		    	c.gridy = 7;
		    	c.gridwidth = 1;
		    	
		    	
		    	
    	updateBtn.setBackground(baseColor);
    	updateBtn.setForeground(secondaryColor);
    	updateBtn.setFont(buttonFont);
    	updateBtn.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
    	updateBtn.addActionListener(
						new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								String name, password, icon;
			
								name = uName.getText();
								
								password = uPassword.getText();
								
								icon = uIcon.getText();
								
								//make sure there are no empty fields
								/*if(!uName.getText().isEmpty() && !uPassword.getText().isEmpty()&& 
										!uIcon.getText().isEmpty()){
									this.name = iName;
									temporary.setPassword(password);
									temporary.setImage(icon);
									

								}
								else {
									System.out.println("Adding Failed, Incorrect or invalid input entered.");
									JOptionPane.showMessageDialog(null,"Error! One or more\nfields was left empty!");
									return;
								}
								*/
														
							frame.dispose();
							screen = new MainScreen(databaseConnection);
							}
						});
	    GridBagConstraints u = new GridBagConstraints();
    	u.fill = GridBagConstraints.BOTH;
    	u.weightx = 1;
    	u.weighty = .16;
    	u.gridx = 2;
    	u.gridy = 7;
    	u.gridwidth = 1;
    	
		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setOpaque(true);
		//constraints for footer
		GridBagConstraints fo = new GridBagConstraints();
		fo.fill = GridBagConstraints.BOTH;
		fo.weightx = 1;
	    fo.weighty = .16;
	    fo.gridx = 0;
	    fo.gridy = 8;
	    fo.gridwidth = 3;
	    fo.gridheight = 1;
		fo.anchor = GridBagConstraints.PAGE_END;
		
		
		//add components to container
		pane.add(storeHeader, h);
		pane.add(uName, n);
		pane.add(nameLabel, nl);
		pane.add(uPassword, p);
		pane.add(passwordLabel, pl);
		pane.add(uIcon, i);
		pane.add(iconLabel, il);
		pane.add(cancel, c);
		pane.add(updateBtn, u);
		pane.add(storeFooter, fo);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
