/**
 * 
 */
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
import javax.swing.SwingUtilities;

/**
 * @author Justin Bradshaw
 * CS 3450 Course Project 
 * Grocery Store Checkout
 * This class will be for the main screen
 * that is the first thing displayed when the 
 * program runs
 */
public class MainScreen {
	private JFrame frame = new JFrame("Grocery Store Checkout System");
	private Container pane;
	private ImageIcon cartImage = new ImageIcon("data/shoppingCart.png");
	private JLabel storeHeader = new JLabel("Mr. Smith's Groceries" , cartImage , JLabel.CENTER);
	private JLabel storeFooter = new JLabel("Copyright Info", JLabel.CENTER);
	private JButton checkout = new JButton("Checkout");
	private JButton inventory = new JButton("Manage Inventory");
	private Color baseColor = new Color(180,242,110);
	private Color secondaryColor = Color.GRAY.darker();
	private Font baseFont = new Font("Verdana", Font.BOLD,16);
	private Font buttonFont = new Font("Verdana", Font.PLAIN, 16);
		

	public MainScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		storeHeader.setIconTextGap(25);
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		storeHeader.setHorizontalTextPosition(SwingConstants.LEADING);
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);
		
		
		checkout.setBackground(baseColor);
		checkout.setForeground(secondaryColor);
		checkout.setFont(buttonFont);
		checkout.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		checkout.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		checkout.setMargin(new Insets(15,15,15,15));
		checkout.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						System.out.println("Checkout Button Pressed!!!!");
						//frame.dispose(); //closes the frame
						//create a new frame here for the checkout screen
					}
					
				});
		
		inventory.setBackground(baseColor);
		inventory.setForeground(secondaryColor);
		inventory.setFont(buttonFont);
		inventory.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		inventory.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		inventory.setMargin(new Insets(15,15,15,15));
		inventory.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						System.out.println("Inventory Button Pressed!!!!");
						//frame.dispose(); //close the frame
						//create a new frame here for the manage inventory screen
					}
					
				});

		
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				System.out.println("Grocery Store!");
				new MainScreen();
			}});
	}

}