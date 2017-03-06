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

import CS3450.course_project.businessLogic.OrderHelper;
import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Order;
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
	private ArrayList<Product> productList= new ArrayList<Product>();
	
	private ArrayList<OrderHelper> orderHelperList = new ArrayList<OrderHelper>();
	
	/**
	 * will be used to create a new checkout screen
	 */
	private CheckoutScreen checkoutscreen;
	
	/**
	 * object for the inventory screen
	 */
	private InventoryScreen inventoryscreen;
	/**
	 * array list to store the orders already placed from the database
	 */
	private ArrayList<Order> orderList = new ArrayList<Order>();
	/**
	 * array list to store the customers already in the database
	 */
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	/**
	 * static boolean for the entire class that will tell whether or not the database has already been 
	 * accessed. No need to reaccess the database if we have already accessed it.
	 */
	private static boolean accessed = false;

	/**
	 * default constructor
	 */
	public MainScreen(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		if (!accessed){
			prodListNoDatabase();
			customerListNoDatabase();
			orderListNoDatabase();
			//createProductList();
			//createCustomerList();
			//createOrderList();
		}
		accessed = true;
		
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
						checkoutscreen = new CheckoutScreen(productList,customerList, orderHelperList, orderList);
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
						frame.dispose(); //close the frame
						//create a new frame here for the manage inventory screen
						System.out.println("Creating new page for Managing Inventory");
						inventoryscreen = new InventoryScreen(productList,customerList);
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
	
	
	/**
	 * @param productList
	 * @param customerList
	 * 
	 * non-default constructor
	 */
	public MainScreen(ArrayList<Product> productList, ArrayList<Customer> customerList, ArrayList<Order> orderList){
		this.productList = productList;
		this.customerList = customerList;
		this.orderList = orderList;
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
						frame.dispose(); //closes the frame
						//create a new frame here for the checkout screen
						System.out.println("Creating checkout page");
						checkoutscreen = new CheckoutScreen(productList,customerList);
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
						frame.dispose(); //close the frame
						//create a new frame here for the manage inventory screen
						System.out.println("Creating new page for Managing Inventory");
						inventoryscreen = new InventoryScreen(productList,customerList);
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
	
	/**
	 * @param productList
	 * @param customerList
	 * 
	 * non-default constructor
	 */
	public MainScreen(ArrayList<Product> productList, ArrayList<Customer> customerList){
		this.productList = productList;
		this.customerList = customerList;
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
						frame.dispose(); //closes the frame
						//create a new frame here for the checkout screen
						System.out.println("Creating checkout page");
						checkoutscreen = new CheckoutScreen(productList,customerList);
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
						frame.dispose(); //close the frame
						//create a new frame here for the manage inventory screen
						System.out.println("Creating new page for Managing Inventory");
						inventoryscreen = new InventoryScreen(productList,customerList);
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
	
	
	
	/**
	 * creates the product list from the database connection
	 */
	public void createProductList(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deal with product list
		
		try {
			statement = con.prepareStatement("select * from products");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				productList.add(new Product(result.getString(1),result.getInt(2),result.getDouble(3),result.getInt(4),result.getString(5),result.getString(6)));
				System.out.println(result.getString(1) + " "  + result.getInt(2) + " "  + result.getDouble(3) + " "  + result.getInt(4) + " "  + result.getString(5) + " "  + result.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * create a list of orders from the database
	 */
	public void createOrderList(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deal with product list
		
		try {
			statement = con.prepareStatement("select * from orders");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				orderList.add(new Order(result.getInt(1),result.getInt(2),result.getString(3),result.getDouble(4),result.getString(5)));
				System.out.println(result.getInt(1)+ " " + result.getInt(2) + " " + result.getString(3) + " " + result.getDouble(4) + " " + result.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * create a list of orders from the database
	 */
	public void createCustomerList(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deal with product list
		
		try {
			statement = con.prepareStatement("select * from customers");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				customerList.add(new Customer(result.getInt(1),result.getString(2),result.getString(3)));
				System.out.println(result.getInt(1)+ " " + result.getString(2) + " " + result.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void prodListNoDatabase(){
		productList.add(new Product("Apple", 56, 0.99,1568494530, "568 S 400 W Atlanta, Georgia 48934 895-594-8745","Johnny Appleseed"));
		productList.add(new Product("Banana", 86,0.58,1578965445,"896 W 500 N Tallahassee, Florida 45897 458-521-8452","Dole Johnson"));
		productList.add(new Product("Coca-Cola", 36, 0.75, 1897456328, "345 E Bubbly St. Tacoma, Washington 45879 568-895-5142","Kris Kringle"));
		productList.add(new Product("Carrots", 26, 1.25, 1356984571, "978 Greeny Way Santa Fe, New Mexico 54876 528-965-4521", "Jimmy Red"));
		productList.add(new Product("Bread", 14, 2.5, 1589478532, "9855 W Dough Avenue Salt Lake City, Utah 82564 801-896-5846","Chris Pillsbury"));
		productList.add(new Product("Potatoes", 88, 0.69, 1563254896, "341 Big Creek Drive Idaho Falls, Idaho 85412 435-986-8541", "Alicia Spud"));
	}
	
	public void orderListNoDatabase(){
		orderList.add(new Order(0,0,"Cash",56.89,"Pick Up"));
		orderList.add(new Order(1,1,"Check",45.27,"Pick Up"));
		orderList.add(new Order(2,2,"Card",89.36,"Delivery"));
	}
	
	public void customerListNoDatabase(){
		customerList.add(new Customer(0,"William Banks","856 S Christmas Ave. Charleston, South Carolina 59587"));
		customerList.add(new Customer(1,"Mason Jones", "584 Center St. San Francisco, CA 65860"));		
		customerList.add(new Customer(2,"Harry Plopper","888 Wizard Way, Narsaq, Greenland "));
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
