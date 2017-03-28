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
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private JLabel storeHeader = new JLabel();
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
	 * allows the current employee to log out
	 */
	private JButton logout = new JButton("Log out");
	/**
	 * edit the current employee's information
	 */
	private JButton editMyInfo = new JButton("Edit My Info"); 
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
	 * panel that will hold manage inventory, manage employees, and log out buttons
	 */
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	/**
	 * panel that will hold the returns, and edit my info buttons
	 */
	private JPanel buttonPanel2 = new JPanel(new FlowLayout());

	/**
	 * @param databaseConnection
	 * 
	 * non-default constructor that has access to the database connection
	 */
	public MainScreen(databaseAccess databaseConnection){
		System.out.println(databaseConnection.getEmployee().getImage().getIconWidth());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		

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
		
		//make the header look pretty
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		//storeHeader.setHorizontalTextPosition(SwingConstants.LEADING);
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
		inventory.setPreferredSize(new Dimension(frame.getWidth()/4,82));
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
		employees.setPreferredSize(new Dimension(frame.getWidth()/4,82));
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
		//functionality for the logout button
		logout.setBackground(baseColor);
		logout.setForeground(secondaryColor);
		logout.setFont(buttonFont);
		logout.setPreferredSize(new Dimension(frame.getWidth()/4,82));
		logout.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));

		//listener for the manage inventory button
		logout.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with logging out of the system
						int confirmation = JOptionPane.showConfirmDialog(null,"Are you sure you want to log out?","Confirm Logout",JOptionPane.YES_NO_OPTION);
						if (confirmation == 0){
							frame.dispose(); //close the frame
							System.out.println("Logging out...");
							LoginScreen screen = new LoginScreen();
						}
					}
					
				});

		//specifications for button panel
		buttonPanel.setBackground(baseColor);
		buttonPanel.setForeground(secondaryColor);
		buttonPanel.setFont(buttonFont);
		buttonPanel.setPreferredSize(new Dimension(frame.getWidth()/4,250));
		buttonPanel.add(inventory);
		buttonPanel.add(employees);
		buttonPanel.add(logout);
		
		//functionality for the returns button
		returns.setBackground(baseColor);
		returns.setForeground(secondaryColor);
		returns.setFont(buttonFont);
		returns.setPreferredSize(new Dimension(frame.getWidth()/4,125));
		returns.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));
		//returns.setMargin(new Insets(15,15,15,15));
		//listener for the manage inventory button
		returns.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						int orderID = getOrderID(databaseConnection.getOrderList());
						if (orderID == -1)return;
						frame.dispose();
						ReturnScreen screen = new ReturnScreen(databaseConnection, orderID);
					}
					
				});
		
		//functionality for the editMyInfo button
		editMyInfo.setBackground(baseColor);
		editMyInfo.setForeground(secondaryColor);
		editMyInfo.setFont(buttonFont);
		editMyInfo.setPreferredSize(new Dimension(frame.getWidth()/4,125));
		editMyInfo.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));
		//returns.setMargin(new Insets(15,15,15,15));
		//listener for the manage inventory button
		editMyInfo.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//deal with the new screen that should appear
						JOptionPane.showMessageDialog(null, "This screen has not yet been implemented");
					}
					
				});
		
		//specifications for second button panel
		buttonPanel2.setBackground(baseColor);
		buttonPanel2.setForeground(secondaryColor);
		buttonPanel2.setFont(buttonFont);
		buttonPanel2.setPreferredSize(new Dimension(frame.getWidth()/4,250));
		buttonPanel2.add(returns);
		buttonPanel2.add(editMyInfo);

		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);

		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(checkout, BorderLayout.CENTER);
		pane.add(buttonPanel, BorderLayout.LINE_START);
		pane.add(buttonPanel2,BorderLayout.LINE_END);
		pane.add(storeFooter, BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * @param id
	 * @param orderList
	 * @return
	 * 
	 * ensure that the order id is valid
	 */
	private boolean validOrderID(int id, ArrayList<Order> orderList){
		for (Order x : orderList){
			if (x.getOrderID() == id) return true;
		}
		return false;
	}

	/**
	 * @return
	 * 
	 * get an order from the user, make sure that it is a valid order
	 */
	private int getOrderID(ArrayList<Order> orderList){
		String temp;
		int toReturn;
		temp = JOptionPane.showInputDialog("Enter the order ID:",JOptionPane.OK_CANCEL_OPTION);
		if (temp == null) return -1;
		
		try {
			toReturn = Integer.parseInt(temp);
		}
		catch(NumberFormatException nfe){
			System.out.println("Unable to parse value!");
			JOptionPane.showMessageDialog(null,"Invalid entry!");
			return -1;
		}
		if (!validOrderID(toReturn,orderList)){ 
			JOptionPane.showMessageDialog(null,"Invalid entry!");
			return -1;
		}
		return toReturn;
	}

}
