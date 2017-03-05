package CS3450.course_project.userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import CS3450.course_project.dataAccess.Product;

/**
 * 
 * @author Melanie Pena
 * CS 3450 Course Project 
 * Grocery Store Checkout
 * This class will be for the checkout screen
 * that is displayed after the checkout button  
 * on the main screen is clicked
 */
public class CheckoutScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System");
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
	private JLabel storeHeader = new JLabel("Mr. Smith's Groceries" , cartImage , JLabel.CENTER);
	/**
	 * stores the footer for the pane
	 */
	private JLabel storeFooter = new JLabel("Copyright Info", JLabel.CENTER);
	/**
	 * button for adding an item
	 */
	private JButton addItem = new JButton("Add Item");
	/**
	 * button for removing an item
	 */
	private JButton removeItem = new JButton("Remove Item");
	/**
	 * button to cancel order and return to main screen
	 */
	private JButton mainScreen = new JButton("Return to Main Screen");
	/**
	 * button to finish and pay
	 */
	private JButton finishAndPay = new JButton("Finish and Pay");
	/**
	 * base color for the GUI
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
	 * JBradshaw: Array list to store the products
	 */
	private ArrayList<Product> productList;
	
	/**
	 * JBradshaw: Allow ability to return to the main screen
	 */
	private MainScreen screen;
	/**
	 * default constructor
	 */
	public CheckoutScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout()); 
		
		//make the header look pretty
		storeHeader.setIconTextGap(25);
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		storeHeader.setHorizontalTextPosition(SwingConstants.LEADING);
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
		
		//make add item button look pretty
		addItem.setBackground(baseColor);
		addItem.setForeground(secondaryColor);
		addItem.setFont(buttonFont);
		addItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		//constraints for add item button 
		GridBagConstraints a = new GridBagConstraints();
		a.weightx = .16;
	    a.weighty = .16;
	    a.gridx = 2;
	    a.gridy = 1;
	    a.gridwidth = 1;
	    a.fill = GridBagConstraints.BOTH;
		//a.anchor = GridBagConstraints.EAST;
		//listener for the add item button
		addItem.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//creates pop up button to input item barcode
						System.out.println("Add Item Button Pressed!!!!");
						//adds item to checkout list
						System.out.println("Adding item to checkout list page");
					}
					
		});
		
		//make remove item button look pretty
		removeItem.setBackground(baseColor);
		removeItem.setForeground(secondaryColor);
		removeItem.setFont(buttonFont);
		removeItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		//constraints for remove item button
		GridBagConstraints r = new GridBagConstraints();
		r.weightx = .16;
		r.weighty = .16;
		r.gridx = 2;
		r.gridy = 2;
		r.gridwidth = 1;
		r.fill = GridBagConstraints.BOTH;
		//listener for the remove item button
		removeItem.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//creates pop up button to input item barcode
						System.out.println("Remove Item Button Pressed!!!!");
						//removes item from checkout list
						System.out.println("Removing item to checkout list page");
					}
					
		});

		//make finishAndPay button look pretty
		finishAndPay.setBackground(baseColor);
		finishAndPay.setForeground(secondaryColor);
		finishAndPay.setFont(buttonFont);
		finishAndPay.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		//constraints for finishAndPaym button 
		GridBagConstraints f = new GridBagConstraints();
		f.weightx = .16;
	    f.weighty = .16;
	    f.gridx = 2;
	    f.gridy = 3;
	    f.gridwidth = 1;
	    f.fill = GridBagConstraints.BOTH;
		//listener for the finishAndPay button
	    finishAndPay.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//display payment screen 
						System.out.println("Finish and Pay Button Pressed!!!!");
						//print receipt
						System.out.println("Printing Receipt");
					}
					
		});

		//make back to mainscreen button look pretty
		mainScreen.setBackground(baseColor);
		mainScreen.setForeground(secondaryColor);
		mainScreen.setFont(buttonFont);
		mainScreen.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		//constraints for finishAndPaym button 
		GridBagConstraints m = new GridBagConstraints();
		m.weightx = .16;
	    m.weighty = .16;
	    m.gridx = 2;
	    m.gridy = 4;
	    m.gridwidth = 1;
	    m.fill = GridBagConstraints.BOTH;
		//listener for the finishAndPay button
	    mainScreen.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//cancel order 
						System.out.println("Main Screen Button Pressed!!!!");
						//close checkout window and take user back to main screen
						System.out.println("Back to main screen...");
						//JBradshaw: add ability to return back to the main screen
						frame.dispose();
						screen = new MainScreen();
					}
					
		});

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
	    fo.gridy = 5;
	    fo.gridwidth = 3;
	    fo.gridheight = 1;
		fo.anchor = GridBagConstraints.PAGE_END;
		
		//add components to container
		pane.add(storeHeader, h);
		pane.add(addItem, a);
		pane.add(removeItem, r);
		pane.add(finishAndPay, f);
		pane.add(mainScreen, m);
		pane.add(storeFooter, fo);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}