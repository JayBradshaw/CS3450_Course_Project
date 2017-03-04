package CS3450.course_project.userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
	 * frame that holds the checkout screen 
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout Page");
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
	 * button for adding an item to checkout list
	 */
	private JButton add = new JButton("Add");
	/**
	 * button for removing an item from checkout list
	 */
	private JButton remove = new JButton("Remove");
	/**
	 * button for adding an item to checkout list
	 */
	private JButton homePage = new JButton("Home Page");
	/**
	 * button for adding an item to checkout list
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
	 * default constructor
	 */
	public CheckoutScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		//make the header look pretty
		storeHeader.setIconTextGap(25);
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		storeHeader.setHorizontalTextPosition(SwingConstants.LEADING);
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);
		
		//make the checkout button look pretty
		checkout.setBackground(baseColor);
		checkout.setForeground(secondaryColor);
		checkout.setFont(buttonFont);
		checkout.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		checkout.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		checkout.setMargin(new Insets(15,15,15,15));
		//listener for the checkout button
		checkout.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						System.out.println("Checkout Button Pressed!!!!");
						//frame.dispose(); //closes the frame
						//create a new frame here for the checkout screen
						System.out.println("Creating checkout page");
					}
					
				});
		
		//make the inventory button look pretty
		inventory.setBackground(baseColor);
		inventory.setForeground(secondaryColor);
		inventory.setFont(buttonFont);
		inventory.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		inventory.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		inventory.setMargin(new Insets(15,15,15,15));
		//listener for the manage inventory button
		inventory.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						System.out.println("Inventory Button Pressed!!!!");
						//frame.dispose(); //close the frame
						//create a new frame here for the manage inventory screen
						System.out.println("Creating new page for Managing Inventory");
					}
					
				});

		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);


		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(checkout, BorderLayout.CENTER);
		pane.add(inventory, BorderLayout.LINE_END);
		pane.add(storeFooter, BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	

}
