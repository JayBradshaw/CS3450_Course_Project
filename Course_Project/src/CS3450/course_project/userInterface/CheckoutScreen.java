package CS3450.course_project.userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import CS3450.course_project.businessLogic.CreditCard;
import CS3450.course_project.businessLogic.OrderHelper;
import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Employee;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.SaleItem;
import CS3450.course_project.dataAccess.databaseAccess;

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
	private JFrame frame = new JFrame("Grocery Store Checkout System: Checkout");
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
	 * JBradshaw: Allow ability to return to the main screen
	 */
	private MainScreen screen;
	/**
	 * move to add product screen
	 */
	private AddProductScreen AddProductScreen;
	/**
	 * move to remove product screen
	 */
	private RemoveProductScreen removeProductScreen;
	/**
	 * order helper list to be able to process an order and create it
	 */
	private ArrayList<OrderHelper> orderHelperList = new ArrayList<OrderHelper>();
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private ArrayList<Employee> employeeList = new ArrayList<Employee>();
	/**
	 * know if cash is selected
	 */
	private boolean cashSelected = true;
	/**
	 * know if card is selected
	 */
	private boolean cardSelected = false;
	/**
	 * know if check is selected
	 */
	private boolean checkSelected = false;
	/**
	 * cash radio button
	 */
	private JRadioButton cash;
	/**
	 * card radio button
	 */
	private JRadioButton card;
	/**
	 * check radio button
	 */
	private JRadioButton check;
	/**
	 * tells you whether or not the user is a new customer
	 * if a new customer, they must be added to the customer list
	 */
	private boolean isNewCustomer = false;
	/**
	 * radio button for existing customer
	 */
	private JRadioButton existing;
	/**
	 * radio button for new customer
	 */
	private JRadioButton newCustomer;
	/**
	 * will store credit card info if a credit card is the payment method
	 */
	private CreditCard creditCard;
	/**
	 * know whether the delivery method is in store pick up
	 */
	private boolean isPickUp = true;
	/**
	 * radio button to know whether or not the delivery method is pick up
	 */
	private JRadioButton pickUp;
	/**
	 * radio button to know whether or not the delivery method is delivery
	 */
	private JRadioButton delivery;
	/**
	 * text area to hold the order info
	 */
	private JTextArea orderDetails = new JTextArea();
	/**
	 * create a panel to hold all of the buttons
	 */
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	
	/**
	 * @param productList
	 * 
	 * non-default constructor
	 */
	public CheckoutScreen(databaseAccess databaseConnection){
		productList = databaseConnection.getProductList();
		customerList = databaseConnection.getCustomerList();
		orderList = databaseConnection.getOrderList();
		employeeList = databaseConnection.getEmployeeList();
		int custID;
		//get a random value for the card number
		getCustomerType();
		if (isNewCustomer){
			//ask them if they want to become a rewards customer
			int dialogButton = JOptionPane.showConfirmDialog (null, "Would you like to\nbecome a rewards customer?","Rewards Customer",JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION){
				if (customerList.isEmpty()){
					custID = 0;
				}
				else {
				custID = customerList.get(customerList.size()-1).getCustomerID() + 1;
				}
				int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
				System.out.println(randomNum);
				String custName = JOptionPane.showInputDialog("Enter your full name");
				String custAddress = JOptionPane.showInputDialog("Enter your address");
				customerList.add(new Customer(custID,custName,custAddress,randomNum,0));
				databaseConnection.setCurrentCustomer(customerList.get(customerList.size()-1));
				//add customer to database
				databaseConnection.addNewCustomer(customerList.get(customerList.size()-1));
			}
			else { //add a guest customer with no reward points
				databaseConnection.setCurrentCustomer(new Customer(50,"Guest","",0,0));
			}
			//if not create some guest customer
			
		}
		else {
			while (!checkValidCustID(custID = Integer.parseInt(JOptionPane.showInputDialog("Enter your customer ID")))){
				JOptionPane.showMessageDialog(null, "Please enter a valid customer ID");
		    }
			databaseConnection.setCurrentCustomer(databaseConnection.getCustomerFromID(custID));
		}
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
		storeHeader.setIconTextGap(25);
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);
		
		
		//set the preferred size for the text area
		orderDetails.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		orderDetails.setEditable(false);
		orderDetails.setText(populateTextArea());
		orderDetails.setForeground(secondaryColor);
		orderDetails.setBackground(baseColor);
		orderDetails.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));
		
		//set the preferred size for the button panel
		buttonPanel.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		buttonPanel.setBackground(baseColor);
		//buttonPanel.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		
		//make add item button look pretty
		addItem.setBackground(baseColor);
		addItem.setForeground(secondaryColor);
		addItem.setFont(buttonFont);
		addItem.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		addItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		addItem.setMargin(new Insets(0,0,0,0));
		//listener for the add item button
		addItem.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//creates pop up button to input item bar code
						System.out.println("Add Item Button Pressed!!!!");
						frame.dispose();
						AddProductScreen = new AddProductScreen(databaseConnection, orderHelperList);
						//adds item to checkout list
						System.out.println("Adding item to checkout list page");
					}
		});
		
		//make remove item button look pretty
		removeItem.setBackground(baseColor);
		removeItem.setForeground(secondaryColor);
		removeItem.setFont(buttonFont);
		removeItem.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		removeItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		removeItem.setMargin(new Insets(0,0,0,0));
		//listener for the remove item button
		removeItem.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//creates pop up button to input item barcode
						System.out.println("Remove Item Button Pressed!!!!");
						if (orderHelperList.size() == 0){
							JOptionPane.showMessageDialog(null, "Error! No products to remove!");
							return;
						}
						//removes item from checkout list
						System.out.println("Removing item to checkout list page");
						frame.dispose();
						removeProductScreen = new RemoveProductScreen(databaseConnection,orderHelperList);
					}
					
		});

		//make finishAndPay button look pretty
		finishAndPay.setBackground(baseColor);
		finishAndPay.setForeground(secondaryColor);
		finishAndPay.setFont(buttonFont);
		finishAndPay.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		finishAndPay.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		finishAndPay.setMargin(new Insets(0,0,0,0));
		//listener for the finishAndPay button
	    finishAndPay.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Error! No order to process!");
						//display payment screen 
						System.out.println("Finish and Pay Button Pressed!!!!");
					}
					
		});

		//make back to main screen button look pretty
		mainScreen.setBackground(baseColor);
		mainScreen.setForeground(secondaryColor);
		mainScreen.setFont(buttonFont);
		mainScreen.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		mainScreen.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		mainScreen.setMargin(new Insets(0,0,0,0));
		//listener for the main screen button
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
						if(databaseConnection.getEmployee().getAccessRights() > 1){
							EmployeeMainScreen screen = new EmployeeMainScreen(databaseConnection);
						}
						else {
						MainScreen screen = new MainScreen(databaseConnection);
						}
					}
					
		});
	    
	    //add all of the buttons to the button panel
	    buttonPanel.add(addItem);
	    buttonPanel.add(removeItem);
	    buttonPanel.add(finishAndPay);
	    buttonPanel.add(mainScreen);
	    //buttonPanel.setBorder(BorderFactory.createLineBorder(secondaryColor,5));

		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);
		
		//add components to container
		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(orderDetails,BorderLayout.CENTER);
		pane.add(buttonPanel,BorderLayout.LINE_END);
		pane.add(storeFooter,BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * @param productList
	 * @param customerList
	 * @param orderHelperList
	 * @param orderList
	 * 
	 * non-default constructor
	 */
	public CheckoutScreen(databaseAccess databaseConnection, ArrayList<OrderHelper> orderHelperList){
		productList = databaseConnection.getProductList();
		customerList = databaseConnection.getCustomerList();
		orderList = databaseConnection.getOrderList();
		employeeList = databaseConnection.getEmployeeList();
		int custID = databaseConnection.getCurrentCustomer().getCustomerID();
		this.orderHelperList = orderHelperList;
		for (OrderHelper x: orderHelperList){
			System.out.println(x.getProductName() + " " + x.getQuantity());
		}
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
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);

		//set the preferred size for the text area
		orderDetails.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		orderDetails.setEditable(false);
		orderDetails.setText(populateTextArea());
		orderDetails.setForeground(secondaryColor);
		orderDetails.setBackground(baseColor);
		orderDetails.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		
		//set the preferred size for the button panel
		buttonPanel.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		buttonPanel.setBackground(baseColor);
		
		//make add item button look pretty
		addItem.setBackground(baseColor);
		addItem.setForeground(secondaryColor);
		addItem.setFont(buttonFont);
		addItem.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		addItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		addItem.setMargin(new Insets(15,15,15,15));

		//listener for the add item button
		addItem.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//creates pop up button to input item barcode
						System.out.println("Add Item Button Pressed!!!!");
						frame.dispose();
						AddProductScreen = new AddProductScreen(databaseConnection, orderHelperList);
						//adds item to checkout list
						System.out.println("Adding item to checkout list page");
					}
					
		});
		
		//make remove item button look pretty
		removeItem.setBackground(baseColor);
		removeItem.setForeground(secondaryColor);
		removeItem.setFont(buttonFont);
		removeItem.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		removeItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		removeItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		removeItem.setMargin(new Insets(15,15,15,15));

		//listener for the remove item button
		removeItem.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//creates pop up button to input item barcode
						System.out.println("Remove Item Button Pressed!!!!");
						//removes item from checkout list
						System.out.println("Removing item to checkout list page");
						if (orderHelperList.size() == 0){
							JOptionPane.showMessageDialog(null, "Error! No products to remove!");
							return;
						}
						frame.dispose();
						removeProductScreen = new RemoveProductScreen(databaseConnection, orderHelperList);
					}
					
		});

		//make finishAndPay button look pretty
		finishAndPay.setBackground(baseColor);
		finishAndPay.setForeground(secondaryColor);
		finishAndPay.setFont(buttonFont);
		finishAndPay.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		finishAndPay.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		finishAndPay.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		finishAndPay.setMargin(new Insets(15,15,15,15));

		//listener for the finishAndPay button
	    finishAndPay.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//display payment screen 
						System.out.println("Finish and Pay Button Pressed!!!!");
						//print receipt
						//System.out.println("Printing Receipt");
						if (orderHelperList.size() == 0){
							JOptionPane.showMessageDialog(null, "Error! No order to process!");
						}
						else{ //print out the receipt based on the orderHelperList
							getPaymentMethod();
							//add a get delivery method here
							String deliveryMethod = getDeliveryMethod();
							//figure out if existing or new customer
						
							if (cashSelected){
								
								JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nTotal Cost: $" + 
								getTotalOrderCost() +  "\nPlease come again soon!");
								//deal with adding a new order based on the order info 
								//if cash just add to order list and print receipt
								int orderListIndex = orderList.size()-1;
								System.out.println(buildOrderInfo());
								if (orderList.isEmpty()){
									orderList.add(new Order(0,custID, "Cash", getTotalOrderCost(),deliveryMethod, buildOrderInfo(),getCurrentDate()));
								}
								else {
									orderList.add(new Order(orderList.get(orderListIndex).getOrderID()+ 1,custID, "Cash", getTotalOrderCost(),deliveryMethod, buildOrderInfo(),getCurrentDate()));
								}								
								printReceipt("cash",custID,false,deliveryMethod);
								//update the product list to reflect the changes
								updateProductListDatabase(databaseConnection);
								productList = databaseConnection.getProductList();
								//add order to database
								databaseConnection.addOrderToDatabase(orderList.get(orderList.size()-1));
								orderHelperList.clear();
								orderDetails.setText("NO CURRENT ORDER");
								frame.revalidate();
							}
							else if (cardSelected){
								//get card info
								String cardName = "";
								long cardNumber = 0;
								String expirationDate = "";
								while (cardName.isEmpty()){
									cardName = JOptionPane.showInputDialog("Enter your name as it appears on the card");
									if (cardName == null)return;
									if (cardName.isEmpty()) JOptionPane.showMessageDialog(null, "Error! Invalid card name entered!");
								}
								while (cardNumber == 0){
									String cardNum = JOptionPane.showInputDialog("Enter your card number");
									if (cardNum == null) return;
									try{
									cardNumber =  Long.parseLong(cardNum);

									}
									catch(NumberFormatException er){
										JOptionPane.showMessageDialog(null,"Error! Invalid card number entered!");
										cardNumber = 0;
									}
								}
								while (expirationDate.isEmpty()){
									expirationDate = JOptionPane.showInputDialog("Enter the expiration date(mm/yy)");
									if (expirationDate == null) return;
									if (expirationDate.isEmpty()) JOptionPane.showMessageDialog(null, "Error! Invalid expiration date entered!");
								}
								creditCard = new CreditCard(cardNumber,expirationDate,cardName);
								
								JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nTotal Cost: $" + 
										getTotalOrderCost() +  "\nPlease come again soon!");
								int orderListIndex = orderList.size()-1;
								System.out.println(buildOrderInfo());
								if (orderList.isEmpty()){
									orderList.add(new Order(0,custID, "Card", getTotalOrderCost(),deliveryMethod, buildOrderInfo(),getCurrentDate()));
								}
								else {
									orderList.add(new Order(orderList.get(orderListIndex).getOrderID()+ 1,custID, "Card", getTotalOrderCost(),deliveryMethod, buildOrderInfo(),getCurrentDate()));
								}
								printReceipt("card",custID,cardSelected,deliveryMethod);
								//update the product list to reflect the changes
								updateProductListDatabase(databaseConnection);
								productList = databaseConnection.getProductList(); //update the product list
								//add order to database
								databaseConnection.addOrderToDatabase(orderList.get(orderList.size()-1));
								orderHelperList.clear();
								orderDetails.setText("NO CURRENT ORDER");
								frame.revalidate();
							}
							else if (checkSelected){
								JOptionPane.showMessageDialog(null, "Thank you for your purchase!\nTotal Cost: $" + 
										getTotalOrderCost() +  "\nPlease come again soon!");
								//deal with adding a new order based on the order info
								//if check just add to order list and print receipt
								int orderListIndex = orderList.size()-1;
								System.out.println(buildOrderInfo());
								if (orderList.isEmpty()){
									orderList.add(new Order(0,custID, "Check", getTotalOrderCost(),deliveryMethod, buildOrderInfo(),getCurrentDate()));
								}
								else {
									orderList.add(new Order(orderList.get(orderListIndex).getOrderID()+ 1,custID, "Check", getTotalOrderCost(),deliveryMethod, buildOrderInfo(),getCurrentDate()));
								}
								//orderList.add(new Order(orderList.get(orderListIndex).getOrderID()+ 1,custID, "Check", getTotalOrderCost(),deliveryMethod, buildOrderInfo()));
								printReceipt("check",custID,false,deliveryMethod);
								//update the product list to reflect the changes
								updateProductListDatabase(databaseConnection); //this is not ideal as it should be a part of the database connection class and should just update the quantities
								productList = databaseConnection.getProductList(); //get the newly updated product list
								//add order to database
								databaseConnection.addOrderToDatabase(orderList.get(orderList.size()-1));
								orderHelperList.clear();
								orderDetails.setText("NO CURRENT ORDER");
								frame.revalidate();
							}
						}
					}	
		});

		//make back to main screen button look pretty
		mainScreen.setBackground(baseColor);
		mainScreen.setForeground(secondaryColor);
		mainScreen.setFont(buttonFont);
		mainScreen.setPreferredSize(new Dimension(frame.getWidth()/2,60));
		mainScreen.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		mainScreen.setMargin(new Insets(15,15,15,15));

		//listener for the main screen button
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
						if(databaseConnection.getEmployee().getAccessRights() > 1){
							EmployeeMainScreen screen = new EmployeeMainScreen(databaseConnection);
						}
						else {
						MainScreen screen = new MainScreen(databaseConnection);
						}
					}
					
		});
	    
	    buttonPanel.add(addItem);
	    buttonPanel.add(removeItem);
	    buttonPanel.add(finishAndPay);
	    buttonPanel.add(mainScreen);

		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);

		//add components to container
		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(orderDetails,BorderLayout.CENTER);
		pane.add(buttonPanel,BorderLayout.LINE_END);
		pane.add(storeFooter,BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * @param ID
	 * @return
	 * 
	 * gets a specific customer based on a customer ID
	 */
	public Customer getCustomerFromID(int ID){
		for (int i =0; i < customerList.size(); ++i){
			if (customerList.get(i).getCustomerID() == ID){
				return customerList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * gets the payment method
	 */
	public void getPaymentMethod(){
		BoxListener boxLis = new BoxListener();
		
		cash = new JRadioButton("Cash",true);
		cash.addItemListener(boxLis);
		check = new JRadioButton("Check");
		check.addItemListener(boxLis);
		card = new JRadioButton("Card");
		card.addItemListener(boxLis);
		
		ButtonGroup paymentMethods = new ButtonGroup();
		paymentMethods.add(cash);
		paymentMethods.add(check);
		paymentMethods.add(card);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.add(cash);
		selectionPanel.add(check);
		selectionPanel.add(card);
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		
		JOptionPane.showConfirmDialog(null, selectionPanel, "Payment Method", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	/**
	 * get the type of customer for the order
	 */
	public void getCustomerType() {
		BoxListener boxLis = new BoxListener();
		
		existing = new JRadioButton("Existing Customer",true);
		existing.addItemListener(boxLis);
		newCustomer = new JRadioButton("New Customer");
		newCustomer.addItemListener(boxLis);
		
		ButtonGroup paymentMethods = new ButtonGroup();
		paymentMethods.add(existing);
		paymentMethods.add(newCustomer);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.add(existing);
		selectionPanel.add(newCustomer);
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		
		JOptionPane.showConfirmDialog(null, selectionPanel, "Customer Type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	/**
	 * get the delivery method for a specific order
	 */
	public String getDeliveryMethod(){
		BoxListener boxLis = new BoxListener();
		
		pickUp = new JRadioButton("Pick Up",true);
		pickUp.addItemListener(boxLis);
		delivery = new JRadioButton("Delivery");
		delivery.addItemListener(boxLis);
		
		ButtonGroup deliveryOptions = new ButtonGroup();
		deliveryOptions.add(pickUp);
		deliveryOptions.add(delivery);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.add(pickUp);
		selectionPanel.add(delivery);
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		
		JOptionPane.showConfirmDialog(null, selectionPanel, "Delivery Method", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (isPickUp) return "Pick Up";
		else return "Delivery";
	}
	
	/**
	 * @author Justin Bradshaw
	 * 
	 * this will tell us what button is pressed
	 *
	 */
	private class BoxListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			Object tempObject = e.getSource();
			if (tempObject instanceof JRadioButton){
				JRadioButton tempRadio = (JRadioButton)tempObject;
				if (tempRadio.isSelected()){
					if (tempRadio == cash){
						System.out.println("Cash button pressed");
						cashSelected=true;
						checkSelected=false;
						cardSelected=false;
					}
					else if (tempRadio == check){
						System.out.println("Check button pressed");
						cashSelected=false;
						checkSelected=true;
						cardSelected=false;
					}
					else if (tempRadio == card){
						System.out.println("Card button pressed");
						cashSelected=false;
						checkSelected=false;
						cardSelected=true;
					}
					else if (tempRadio == existing){
						isNewCustomer = false;
					}
					else if (tempRadio == newCustomer){
						isNewCustomer = true;
					}
					else if (tempRadio == pickUp){
						isPickUp = true;
					}
					else if (tempRadio == delivery){
						isPickUp = false;
					}
				}
			}
			
		}
	}
	
	/**
	 * @param paymentMethod
	 * @param custID
	 * @param card
	 * @param deliveryMethod
	 * 
	 * prints out a receipt based on an order
	 */
	private void printReceipt(String paymentMethod, int custID, boolean card, String deliveryMethod){
		PrintWriter fileOutput = null;
		int orderID = orderList.get(orderList.size()-1).getOrderID(); //get the correct id for the order
		String fileName = "data/order(" + orderID + ").txt"; //string to store the order 
		System.out.println(fileName);
		try {
			fileOutput = new PrintWriter(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Customer customer = getCustomerFromID(custID);
		fileOutput.println("Thank you for choosing Mr. Smith's Groceries!\n");
		fileOutput.println("Here is your final receipt\n\n");
		fileOutput.println("Customer Info:\n");
		fileOutput.println(customer.getName());
		fileOutput.println(customer.getAddress() + "\n");
		if (card){
			fileOutput.println("Card Info: ");
			fileOutput.println("Card Holder: " + creditCard.getName());
			fileOutput.println("Card Number: ************" + creditCard.lastFourDigits());
			//fileOutput.println("Expiration Date: " + creditCard.getExpirationDate() + "\n");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		fileOutput.println("Date: " + dateFormat.format(date) + "\n");
		fileOutput.println(String.format("%-30s %10s %10s", "Product:" , "Cost:", "Quantity:\n"));
		for(OrderHelper item : this.orderHelperList){
			String price = new DecimalFormat("0.00").format(item.getProductPrice());
			//System.out.println("PRICE: " + price);
			fileOutput.println(String.format("%-30s %10s %10s", item.getProductName(), price, Integer.toString(item.getQuantity())));
		}
		fileOutput.println(String.format("\n\nTotal Cost: %.2f", getTotalOrderCost()));
		fileOutput.println("Payment Method: " + printPaymentMethod());
		fileOutput.println("Delivery Method: " + deliveryMethod);
		
		fileOutput.close();
	}
	
	/**
	 * @return
	 * 
	 * gets the total cost of an order
	 */
	private double getTotalOrderCost(){
		double totalCost = 0.0;
		for(OrderHelper item : this.orderHelperList){
			totalCost += item.getProductPrice() * item.getQuantity();
		}
		//add in tax of .06 (6%)
		totalCost += totalCost * 0.06;
		return round(totalCost,2);
	}
	
	/**
	 * @return
	 * 
	 * prints the payment method
	 */
	private String printPaymentMethod(){
		if (cashSelected) return "Cash";
		else if (cardSelected) return "Card";
		else return "Check";		
	}
	
	/**
	 * @param id id to be checked against
	 * @return
	 * 
	 * checks whether or not an ID is valid
	 */
	private boolean checkValidCustID(int id){
		for (int i = 0; i < customerList.size(); ++i){
			if (customerList.get(i).getCustomerID() == id) return true;
		}
		return false;
	}

	/**
	 * updates the availability of items, or available units, based on the order
	 */
	private void updateProductListDatabase(databaseAccess databaseConnection){
		for (int i = 0; i < productList.size(); ++i){
			for (int j =0; j < orderHelperList.size(); ++j){
				if (productList.get(i).getName() == orderHelperList.get(j).getProductName()){
					//change the number of available units
					productList.get(i).setAvailableUnits(productList.get(i).getAvailableUnits()-orderHelperList.get(j).getQuantity());
					//update the quantity so that it reflects the subtraction from the order
					databaseConnection.updateProductQuantity(productList.get(i));
				}
			}
		}
	}
	
	/**
	 * @return
	 * 
	 * build the order info column for the order table
	 */
	private String buildOrderInfo(){
		String toReturn = "";
		//for each object print out the necessary info
		for (int i = 0; i < orderHelperList.size(); ++i){
			toReturn += orderHelperList.get(i).createOrderInfo();
			if (i < orderHelperList.size()-1){
				toReturn += '|';
			}
		}
		return toReturn;
	}
	
	/**
	 * create the necessary info for the text area
	 */
	private String populateTextArea(){
		String toReturn = "";
		if (orderHelperList.isEmpty()){
			toReturn = "NO CURRENT ORDER";
		}
		else {
			toReturn += "Current Order Status:\n\n";
			toReturn += String.format("%-20s %-15s %-15s\n", "Product:" , "Cost:", "Quantity:");
			for(OrderHelper item : this.orderHelperList){
				toReturn += String.format("%-20s %-15.2f %-15s\n", item.getProductName(), item.getProductPrice(), Integer.toString(item.getQuantity()));
			}
			toReturn += String.format("\nTotal Cost: $%.2f\n", getTotalOrderCost());
		}
		System.out.println(toReturn);
		return toReturn;
	}
	
	/**
	 * @return
	 * returns the current date in the format yyyy/MM/dd
	 */
	private String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return (String)dateFormat.format(date);
	}
	
	/**
	 * @param value
	 * @param places
	 * @return
	 * 
	 * round a double value to only two decimal places
	 * taken from stack overflow: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	 */
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
