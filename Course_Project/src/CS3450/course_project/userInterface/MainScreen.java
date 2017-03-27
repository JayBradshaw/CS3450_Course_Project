/**
 * 
 */
package CS3450.course_project.userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import CS3450.course_project.businessLogic.OrderHelper;
import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Employee;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Justin Bradshaw
 * CS 3450 Course Project 
 * Grocery Store Checkout
 * This class will be for the main screen
 * that is the first thing displayed when the 
 * program runs
 */
public class MainScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Main");
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
	 * button for checking out
	 */
	private JButton checkout = new JButton("Checkout");
	/**
	 * button for managing inventory
	 */
	private JButton inventory = new JButton("Manage Inventory");
	/**
	 * button to allow for returns
	 */
	private JButton returns = new JButton("Returns");
	/**
	 * button to manage the employees
	 */
	private JButton employees = new JButton("Manage Employees");
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
	 * will be used to create a new checkout screen
	 */
	private CheckoutScreen checkoutscreen;
	/**
	 * object for the inventory screen
	 */
	private InventoryScreen inventoryscreen;
	/**
	 * panel that will hold manage inventory and manage employees
	 */
	private JPanel buttonPanel = new JPanel(new FlowLayout());


	/**
	 * @param databaseConnection
	 * 
	 * non-default constructor that has access to the database connection
	 */
	public MainScreen(databaseAccess databaseConnection){

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
		checkout.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));
		//checkout.setMargin(new Insets(15,15,15,15));
		//listener for the checkout button
		checkout.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						System.out.println("Checkout Button Pressed!!!!");
						frame.dispose(); //closes the frame
						//create a new frame here for the checkout screen
						System.out.println("Creating checkout page");
						checkoutscreen = new CheckoutScreen(databaseConnection);
					}
					
				});
		
		//make the inventory button look pretty
		inventory.setBackground(baseColor);
		inventory.setForeground(secondaryColor);
		inventory.setFont(buttonFont);
		inventory.setPreferredSize(new Dimension(frame.getWidth()/4,125));
		inventory.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));
		//inventory.setMargin(new Insets(15,15,15,15));
		//listener for the manage inventory button
		inventory.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						System.out.println("Inventory Button Pressed!!!!");
						frame.dispose(); //close the frame
						//create a new frame here for the manage inventory screen
						System.out.println("Creating new page for Managing Inventory");
						inventoryscreen = new InventoryScreen(databaseConnection);
					}
					
				});
		
		//functionality for the employees button
		employees.setBackground(baseColor);
		employees.setForeground(secondaryColor);
		employees.setFont(buttonFont);
		employees.setPreferredSize(new Dimension(frame.getWidth()/4,125));
		employees.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		//returns.setMargin(new Insets(15,15,15,15));
		//listener for the manage inventory button
		employees.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						JOptionPane.showMessageDialog(null,"This screen has yet to be implemented!");
					}
					
				});

		//specifications for button panel
		buttonPanel.setBackground(baseColor);
		buttonPanel.setForeground(secondaryColor);
		buttonPanel.setFont(buttonFont);
		buttonPanel.setPreferredSize(new Dimension(frame.getWidth()/4,250));
		
		//functionality for the returns button
		returns.setBackground(baseColor);
		returns.setForeground(secondaryColor);
		returns.setFont(buttonFont);
		returns.setPreferredSize(new Dimension(frame.getWidth()/4,150));
		returns.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));
		//returns.setMargin(new Insets(15,15,15,15));
		//listener for the manage inventory button
		returns.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						JOptionPane.showMessageDialog(null,"This screen has yet to be implemented!");
					}
					
				});

		
		buttonPanel.add(inventory);
		buttonPanel.add(employees);

		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);

		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(checkout, BorderLayout.CENTER);
		pane.add(buttonPanel, BorderLayout.LINE_START);
		pane.add(returns,BorderLayout.LINE_END);
		pane.add(storeFooter, BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
