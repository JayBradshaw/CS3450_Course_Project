package CS3450.course_project.userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
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
import javax.swing.SwingUtilities;

import CS3450.course_project.businessLogic.CreditCard;
import CS3450.course_project.businessLogic.OrderHelper;
import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Order;
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
	/**
	 * list of customers
	 */
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	/**
	 * include the order list
	 */
	private ArrayList<Order> orderList = new ArrayList<Order>();
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
	
	private CreditCard creditCard;
	
	/**
	 * @param productList
	 * 
	 * constructor
	 */
	public CheckoutScreen(ArrayList<Product> productList, ArrayList<Customer> customerList){
		this.productList = productList;
		this.customerList = customerList;
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
						frame.dispose();
						AddProductScreen = new AddProductScreen(productList, customerList, orderHelperList,orderList);
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
						frame.dispose();
						removeProductScreen = new RemoveProductScreen(productList,customerList, orderHelperList,orderList);
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
						JOptionPane.showMessageDialog(null, "Error! No order to process!");
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
		//constraints for main screen button 
		GridBagConstraints m = new GridBagConstraints();
		m.weightx = .16;
	    m.weighty = .16;
	    m.gridx = 2;
	    m.gridy = 4;
	    m.gridwidth = 1;
	    m.fill = GridBagConstraints.BOTH;
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
						screen = new MainScreen(productList,customerList, orderList);
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
	
	public CheckoutScreen(ArrayList<Product> productList, ArrayList<Customer> customerList, ArrayList<OrderHelper> orderHelperList, ArrayList<Order> orderList){
		this.productList = productList;
		this.customerList = customerList;
		this.orderHelperList = orderHelperList;
		this.orderList = orderList;
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
						frame.dispose();
						AddProductScreen = new AddProductScreen(productList, customerList, orderHelperList, orderList);
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
						frame.dispose();
						removeProductScreen = new RemoveProductScreen(productList,customerList, orderHelperList,orderList);
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
						if (orderHelperList.size() == 0){
							JOptionPane.showMessageDialog(null, "Error! No order to process!");
						}
						else{ //print out the receipt based on the orderHelperList
							getPaymentMethod();
							if (cashSelected){
								int custID;
								getCustomerType();
								if (isNewCustomer){
									custID = customerList.get(customerList.size()-1).getCustomerID() + 1;
									String custName = JOptionPane.showInputDialog("Enter your full name");
									String custAddress = JOptionPane.showInputDialog("Enter your address");
									customerList.add(new Customer(custID,custName,custAddress));
									//add new customer to database
									customerList.get(customerList.size()-1).addToDatabase();
								}
								else {
									while (!checkValidCustID(custID = Integer.parseInt(JOptionPane.showInputDialog("Enter your customer ID")))){
										JOptionPane.showMessageDialog(null, "Please enter a valid customer ID");
									}
						
								}
								JOptionPane.showMessageDialog(null, "Thank you for your purchase!\n"
										+ "Please come again soon!");
								//deal with adding a new order based on the order info 
								//if cash just add to order list and print receipt
								int orderListIndex = orderList.size()-1;
								orderList.add(new Order(orderList.get(orderListIndex).getOrderID()+ 1,custID, "cash", getTotalOrderCost(),"Pick up" ));
								printReceipt("cash",custID,false);
								//add order to database
								orderList.get(orderList.size()-1).addToDatabase();
								orderHelperList.clear();
							}
							else if (cardSelected){
								int custID;
								getCustomerType();
								if (isNewCustomer){
									custID = customerList.get(customerList.size()-1).getCustomerID() + 1;
									String custName = JOptionPane.showInputDialog("Enter your full name");
									String custAddress = JOptionPane.showInputDialog("Enter your address");
									customerList.add(new Customer(custID,custName,custAddress));
									//add customer to database
									customerList.get(customerList.size()-1).addToDatabase();
								}
								else {
									while (!checkValidCustID(custID = Integer.parseInt(JOptionPane.showInputDialog("Enter your customer ID")))){
										JOptionPane.showMessageDialog(null, "Please enter a valid customer ID");
									}
								}
								//get card info
								String cardName = JOptionPane.showInputDialog("Enter your name as it appears on the card");
								int cardNumber =  Integer.parseInt(JOptionPane.showInputDialog("Enter your card number"));
								String expirationDate = JOptionPane.showInputDialog("Enter the expiration date(m/yy)");
								creditCard = new CreditCard(cardNumber,expirationDate,cardName);
								
								JOptionPane.showMessageDialog(null, "Thank you for your purchase!\n"
										+ "Please come again soon!");
								int orderListIndex = orderList.size()-1;
								orderList.add(new Order(orderList.get(orderListIndex).getOrderID()+ 1,custID, "cash", getTotalOrderCost(),"Pick up" ));
								printReceipt("card",custID,cardSelected);
								//add order to databse
								orderList.get(orderList.size()-1).addToDatabase();
								orderHelperList.clear();
							}
							else if (checkSelected){
								int custID;
								getCustomerType();
								if (isNewCustomer){
									custID = customerList.get(customerList.size()-1).getCustomerID() + 1;
									String custName = JOptionPane.showInputDialog("Enter your full name");
									String custAddress = JOptionPane.showInputDialog("Enter your address");
									customerList.add(new Customer(custID,custName,custAddress));
									//add customer to database
									customerList.get(customerList.size()-1).addToDatabase();
								}
								else {
									while (!checkValidCustID(custID = Integer.parseInt(JOptionPane.showInputDialog("Enter your customer ID")))){
										JOptionPane.showMessageDialog(null, "Please enter a valid customer ID");
									}
								}
								JOptionPane.showMessageDialog(null, "Thank you for your purchase!\n"
										+ "Please come again soon!");
								//deal with adding a new order based on the order info
								//if check just add to order list and print receipt
								int orderListIndex = orderList.size()-1;
								orderList.add(new Order(orderList.get(orderListIndex).getOrderID()+ 1,custID, "cash", getTotalOrderCost(),"Pick up" ));
								printReceipt("check",custID,false);
								//add order to database
								orderList.get(orderList.size()-1).addToDatabase();
								orderHelperList.clear();
							}
						}
					}	
		});

		//make back to mainscreen button look pretty
		mainScreen.setBackground(baseColor);
		mainScreen.setForeground(secondaryColor);
		mainScreen.setFont(buttonFont);
		mainScreen.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		//constraints for main screen button 
		GridBagConstraints m = new GridBagConstraints();
		m.weightx = .16;
	    m.weighty = .16;
	    m.gridx = 2;
	    m.gridy = 4;
	    m.gridwidth = 1;
	    m.fill = GridBagConstraints.BOTH;
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
						screen = new MainScreen(productList,customerList,orderList);
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
				}
			}
			
		}
	}
	
	private void printReceipt(String paymentMethod, int custID, boolean card){
		PrintWriter fileOutput = null;
		int size = orderList.size()-1;
		String fileName = "data/order(" + size + ").txt"; //string to store the order 
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
			fileOutput.println("Card Number: " + creditCard.getCardNumber());
			fileOutput.println("Expiration Date: " + creditCard.getExpirationDate() + "\n");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		fileOutput.println("Date: " + dateFormat.format(date) + "\n");
		fileOutput.println(String.format("%-30s %10s %10s", "Product:" , "Cost:", "Quantity:\n"));
		for(OrderHelper item : this.orderHelperList){
			fileOutput.println(String.format("%-30s %10f %10s", item.getProductName(), item.getProductPrice(), Integer.toString(item.getQuantity())));
		}
		fileOutput.println("\n\nTotal Cost: " + getTotalOrderCost());
		fileOutput.println("Payment Method: " + printPaymentMethod());
		
		fileOutput.close();
	}
	
	private double getTotalOrderCost(){
		double totalCost = 0.0;
		for(OrderHelper item : this.orderHelperList){
			totalCost += item.getProductPrice() * item.getQuantity();
		}
		return totalCost;
	}
	
	private String printPaymentMethod(){
		if (cashSelected) return "Cash";
		else if (cardSelected) return "Card";
		else return "Check";		
	}
	
	private boolean checkValidCustID(int id){
		if (id >=0 && id < customerList.size()){
			return true;
		}
		return false;
	}
}
