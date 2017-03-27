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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import CS3450.course_project.businessLogic.OrderHelper;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.databaseAccess;

/**
 * @author Justin Bradshaw
 * 
 * This screen will take care of returns done by customers
 *
 */
public class ReturnScreen {
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
	 * list of all orders
	 */
	private ArrayList<Order> orderList = new ArrayList<Order>();
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
	 * id of the order to be manipulated
	 */
	private int orderID;
	/**
	 * order to be processed
	 */
	private Order order;
	
	/**
	 * @param databaseConnection
	 * non-default constructor
	 */
	public ReturnScreen(databaseAccess databaseConnection){
		orderList = databaseConnection.getOrderList();
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
		
		
		//get the correct order
		orderID = getOrder(orderList);
		order = databaseConnection.getOrderFromID(orderID);
		//build the orderHelper from the order
		//set the details for the text area
		orderDetails.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		orderDetails.setEditable(false);
		orderDetails.setText(populateTextArea());
		orderDetails.setForeground(secondaryColor);
		orderDetails.setBackground(baseColor);
		orderDetails.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));
		
		//add in the items for the buttons editOrder, finishOrder, cancel
		
		//edit order button
		editOrder.setBackground(baseColor);
		editOrder.setForeground(secondaryColor);
		editOrder.setFont(buttonFont);
		editOrder.setPreferredSize(new Dimension(frame.getWidth()/2,80));
		editOrder.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));

		//listener for the edit order button
		editOrder.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "This has not yet been implemented!");
					}
					
		});
		
		//finish order button
		finishOrder.setBackground(baseColor);
		finishOrder.setForeground(secondaryColor);
		finishOrder.setFont(buttonFont);
		finishOrder.setPreferredSize(new Dimension(frame.getWidth()/2,80));
		finishOrder.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));

		//listener for the finish order button
		finishOrder.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "This has not yet been implemented!");
					}
					
		});
		
		//cancel order button
		cancel.setBackground(baseColor);
		cancel.setForeground(secondaryColor);
		cancel.setFont(buttonFont);
		cancel.setPreferredSize(new Dimension(frame.getWidth()/2,80));
		cancel.setBorder(BorderFactory.createLineBorder(secondaryColor,5,true));


		//listener for the cancel order button
		cancel.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						MainScreen screen = new MainScreen(databaseConnection);
					}
					
		});
		
		
		//set the preferred size for the button panel
		buttonPanel.setPreferredSize(new Dimension(frame.getWidth()/2,150));
		buttonPanel.setBackground(baseColor);
		buttonPanel.add(editOrder);
		buttonPanel.add(finishOrder);
		buttonPanel.add(cancel);
		
		
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
	private int getOrder(ArrayList<Order> orderList){
		int temp;
		while (!validOrderID(temp = Integer.parseInt(JOptionPane.showInputDialog("Enter the order ID:")),orderList)){
			JOptionPane.showMessageDialog(null,"Invalid ID entered!");
		}
		return temp;
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
		return totalCost;
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
				int spacing = 20 - item.getProductName().length() + (8 -item.getProductName().length());
				toReturn += String.format("%-20s %-15.2f %-15s\n", item.getProductName(), item.getProductPrice(), Integer.toString(item.getQuantity()));
			}
			toReturn += String.format("\nTotal Cost: %.2f\n", getTotalOrderCost());
		}
		System.out.println(toReturn);
		return toReturn;
	}

}
