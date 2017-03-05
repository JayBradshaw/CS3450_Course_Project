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

import CS3450.course_project.dataAccess.Product;

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
	 * button for checking out
	 */
	private JButton checkout = new JButton("Checkout");
	/**
	 * button for managing inventory
	 */
	private JButton inventory = new JButton("Manage Inventory");
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
	 * array list to store the products from the database
	 */
	private ArrayList<Product> productList= new ArrayList();
	
	private CheckoutScreen checkoutscreen;

	/**
	 * default constructor
	 */
	public MainScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		createProductList();
		
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
						frame.dispose(); //closes the frame
						//create a new frame here for the checkout screen
						System.out.println("Creating checkout page");
						checkoutscreen = new CheckoutScreen();
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
						InventoryScreen i = new InventoryScreen();
					}
					
				});

		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);
		
		//check to see if product list initialized correctly
		for (Product p : productList){
			System.out.println(p.getName());
		}


		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(checkout, BorderLayout.CENTER);
		pane.add(inventory, BorderLayout.LINE_END);
		pane.add(storeFooter, BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * creates the product list from the database connection
	 */
	public void createProductList(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select * from products");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(result.next()){
				productList.add(new Product(result.getString(1),result.getInt(2),result.getDouble(3),result.getInt(4),result.getString(5),result.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				//System.out.println("Grocery Store!");
				
				new MainScreen();
			}});
	}
}
