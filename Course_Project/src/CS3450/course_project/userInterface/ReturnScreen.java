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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import CS3450.course_project.dataAccess.OrderHelper;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.DatabaseAccess;

/**
 * @author Justin Bradshaw
 * 
 * This screen will take care of returns done by customers
 *
 */
public class ReturnScreen {
	private JFrame frame = new JFrame("Grocery Store Checkout System: Return");
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
	 * text area to hold the order details
	 */
	private JTextArea orderDetails = new JTextArea();
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
	 * order helper list to be able to process an order and create it
	 */
	private ArrayList<OrderHelper> orderHelperList = new ArrayList<OrderHelper>();
	/**
	 * create a panel to hold all of the buttons
	 */
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	/**
	 * cancel button that returns to the previous screen
	 */
	private JButton cancel = new JButton("Cancel");
	/**
	 * button used when the order is completed
	 */
	private JButton finishOrder = new JButton("Finish");
	/**
	 * moves to a different screen that will allow the cashier to edit the order
	 */
	private JButton editOrder = new JButton("Edit Order Item");
	/**
	 * order to be processed
	 */
	private Order order;
	/**
	 * stores the original total of the order
	 */
	private double originalOrderCost;
	/**
	 * connection to the database
	 */
	DatabaseAccess databaseConnection;
	
	/**
	 * @param databaseConnection
	 * non-default constructor
	 */
	public ReturnScreen(DatabaseAccess databaseConnection, int orderID){
		this.databaseConnection = databaseConnection;
		order = databaseConnection.getOrderFromID(orderID);
		originalOrderCost = order.getTotalCost();
		orderHelperList = databaseConnection.getHelperListFromID(orderID);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout()); 
		
		storeHeader.setLayout(new BoxLayout(storeHeader, BoxLayout.X_AXIS));
		JLabel icon1Label = new JLabel();
		JLabel textLabel = new JLabel("Mr. Smith's Groceries");
		textLabel.setForeground(baseColor);
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
		storeHeader.setBackground(secondaryColor);
		storeHeader.setForeground(baseColor);
		storeHeader.setFont(baseFont);
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);

		//build the orderHelper from the order
		//set the details for the text area
		orderDetails.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		orderDetails.setEditable(false);
		orderDetails.setText(populateTextArea());
		//orderDetails.setForeground(baseColor);
		//orderDetails.setBackground(secondaryColor);
		//orderDetails.setBorder(BorderFactory.createLineBorder(secondaryColor));
		orderDetails.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, secondaryColor));

		//add in the items for the buttons editOrder, finishOrder, cancel
		
		//edit order button
		//editOrder.setBackground(secondaryColor);
		//editOrder.setForeground(baseColor);
		editOrder.setFont(buttonFont);
		editOrder.setPreferredSize(new Dimension(frame.getWidth()/2,80));
		//editOrder.setBorder(BorderFactory.createLineBorder(baseColor,5,true));

		//listener for the edit order button
		editOrder.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//get the name of the product and ensure that it is a valid product name
						String productName = JOptionPane.showInputDialog("Enter a product name:");
						//if the user cancels
						if (productName == null) return;
						if (!checkValidName(productName)){
							JOptionPane.showMessageDialog(null,"Invalid product name entered!");
							return;
						}
						//get the item to be edited
						OrderHelper tempItem = getOrderHelperItemByName(productName);
						//get the quantity to be changed ADD ERROR CHECKING HERE OF NOT INSERTING AN INTEGER OR CANCELLING
						String quantityString = ""; //get the value from the input box
						int quantity = 0; //set the quantity
						//worry about invalid entries for quantities
						
						try{
							quantityString = JOptionPane.showInputDialog("Enter a quantity to remove");
							//deal with cancel button
							if (quantityString ==null){
								System.out.println("Cancel button selected");
								return;
							}
							//try to parse to integer
							quantity = Integer.parseInt(quantityString);
						}catch(NumberFormatException er){
							System.out.println("Invalid entry");
							JOptionPane.showMessageDialog(null, "Invalid entry!");
							return;
						}
						if(!checkValidQuantity(tempItem,quantity))
						{
							return;
						}
						//edit the quantity of the tempItem
						tempItem.setQuantity(tempItem.getQuantity()-quantity);
						
						System.out.println("Changing quantity for item");
						for (OrderHelper x : orderHelperList){
							System.out.println(x.getQuantity() + " " + getProdFromID(x.getProductID()).getName());
						}
						//if the quantity becomes 0 remove the item
						if (tempItem.getQuantity() == 0) {orderHelperList.remove(tempItem);
						//ALSO REMOVE FROM THE ORDER HELPER IN THE DATABASE RIGHT HERE
						databaseConnection.deleteHelperItem(tempItem);
						}
						//change the text in the text area and revalidate the frame
						orderDetails.setText(populateTextArea());
						frame.revalidate();
				}
					
		});
		
		//finish order button
		//finishOrder.setBackground(secondaryColor);
		//finishOrder.setForeground(baseColor);
		finishOrder.setFont(buttonFont);
		finishOrder.setPreferredSize(new Dimension(frame.getWidth()/2,80));
		//finishOrder.setBorder(BorderFactory.createLineBorder(baseColor,5,true));

		//listener for the finish order button
		finishOrder.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//pass to the database connection the string that contains the new order info
						//also pass the order itself
						//editOrderInfoFromReturn(order,string)
						//create a function in the DatabaseAccess to deal with this, if the string is
						//empty just remove the order from the order table
						//add a message that tells the user how much money they are getting back from
						//the cashier in cash
						if (Math.abs(originalOrderCost - getTotalOrderCost()) <= 0.05){
							JOptionPane.showMessageDialog(null, "No changes have been made to the order!");
							return;
						}
						if (orderHelperList.isEmpty()){
							databaseConnection.deleteOrder(order);
						}
						else {
							//update the order info based on the array list of order helper items
							//loop through all the items in the helper list
							System.out.println("updating helper list");
							//also need to update the order list
							databaseConnection.updateOrderList(orderHelperList.get(0).getOrderID(),round(getTotalOrderCost(),2));
							for (OrderHelper h : orderHelperList){
								databaseConnection.updateHelperList(h);
							}
						}
						double totalReturn = originalOrderCost - getTotalOrderCost();
						JOptionPane.showMessageDialog(null,"Thank you for your business.\n"
								+ "Your total return comes to $" + String.format("%.2f", totalReturn) + ".");
						frame.dispose();
						if(databaseConnection.getEmployee().getAccessRights() > 1){
							EmployeeMainScreen screen = new EmployeeMainScreen(databaseConnection);
						}
						else {
						MainScreen screen = new MainScreen(databaseConnection);
						}
					}

		});
		
		//cancel order button
		//cancel.setBackground(secondaryColor);
		//cancel.setForeground(baseColor);
		cancel.setFont(buttonFont);
		cancel.setPreferredSize(new Dimension(frame.getWidth()/2,80));
		//cancel.setBorder(BorderFactory.createLineBorder(baseColor,5,true));


		//listener for the cancel order button
		cancel.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//check to see whether or not the order has changed
						if (getTotalOrderCost() == originalOrderCost){
							JOptionPane.showMessageDialog(null, "The order has not changed!");
							return;
						}
						frame.dispose();
						if(databaseConnection.getEmployee().getAccessRights() > 1){
							EmployeeMainScreen screen = new EmployeeMainScreen(databaseConnection);
						}
						else {
						MainScreen screen = new MainScreen(databaseConnection);
						}
					}
					
		});
		
		//set the preferred size for the button panel
		buttonPanel.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		//buttonPanel.setBackground(baseColor);
		buttonPanel.add(editOrder);
		buttonPanel.add(finishOrder);
		buttonPanel.add(cancel);
		
		
		//make the footer look pretty
		storeFooter.setBackground(secondaryColor);
		storeFooter.setForeground(baseColor);
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
	 * @return
	 * 
	 * gets the total cost of an order
	 */
	private double getTotalOrderCost(){
		double totalCost = 0.0;
		for(OrderHelper item : orderHelperList){
			totalCost += item.getProductPrice() * item.getQuantity();
		}
		//add in tax of .06 (6%)
		totalCost += totalCost * 0.06;
		return totalCost;
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
			toReturn += "Date: " + order.getOrderDate() + "\n\n";
			toReturn += String.format("%-20s %-15s %-15s\n", "Product:" , "Cost:", "Quantity:");
			for(OrderHelper item : orderHelperList){
				//int spacing = 20 - getProdFromID(item.getProductID()).getName().length() + (8 -item.getProductName().length());
				toReturn += String.format("%-20s %-15.2f %-15s\n", getProdFromID(item.getProductID()).getName(), item.getProductPrice(), Integer.toString(item.getQuantity()));
			}
			toReturn += String.format("\nTotal Cost: $%.2f\n", getTotalOrderCost());
		}
		System.out.println(toReturn);
		return toReturn;
	}

	/**
	 * @param name
	 * @return
	 * 
	 * ensure that the name of the item is valid
	 */
	private boolean checkValidName(String name){
		//ignore issues with casing
		for (OrderHelper item : orderHelperList){
			if (getProdFromID(item.getProductID()).getName().toUpperCase().equals(name.toUpperCase()))return true;
		}
		return false;
	}	
	/**
	 * @param item
	 * @param quantity
	 * @return
	 * 
	 * ensure that the employee enters a valid quantity to remove from the order
	 */
	private boolean checkValidQuantity(OrderHelper item, int quantity){
		if (item.getQuantity() - quantity < 0 || quantity < 0) {
			JOptionPane.showMessageDialog(null, "Invalid quantity entered!");
			return false;
		}
		return true;
	}
	/**
	 * @param name
	 * @return
	 * 
	 * gets an order helper item based on its name
	 */
	private OrderHelper getOrderHelperItemByName(String name){
		//ignore issues with casing
		for (OrderHelper item : orderHelperList){
			if (getProdFromID(item.getProductID()).getName().toUpperCase().equals(name.toUpperCase()))
				return item;
		}
		return null;
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
	
	/**
	 * @param id
	 * @return
	 * 
	 * get a product from the id
	 */
	private Product getProdFromID(int id){
		for (Product p : databaseConnection.getProductList()){
			if (p.getID() == id) return p;
		}
		return null;
	}
	
}
