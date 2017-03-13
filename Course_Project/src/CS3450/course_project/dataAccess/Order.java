/**
 * 
 */
package CS3450.course_project.dataAccess;


import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import CS3450.course_project.businessLogic.OrderHelper;

/**
 * @author Justin Bradshaw
 *
 *
 *deals with a finalized order
 *need to store a list of products and the quantity of that product that is requested (OrderHelper)
 */
public class Order {
	/**
	 * stores the specific ID of the order
	 */
	private int orderID;
	/**
	 * information for the customer that is tied to a specific order
	 */
	private int customerID;
	/**
	 * payment type: card, cash, check
	 */
	private String paymentType;
	/**
	 * total cost of an order
	 */
	private double totalCost = 0.0;
	/**
	 * how the order will be delivered: in store pickup, delivery
	 */
	private String deliveryMethod;
	/**
	 * list of items bought
	 */
	private ArrayList<OrderHelper> list;

	/**
	 * default constructor
	 */
	public Order(){
		//empty
	}
	/**
	 * @param orderID
	 * @param customerID
	 * @param paymentType
	 * @param totalCost
	 * @param deliveryMethod
	 * 
	 * constructor that initializes all of the important attributes
	 */
	public Order(int orderID, int customerID, String paymentType, double totalCost, String deliveryMethod){
		this.orderID = orderID;
		this.customerID =customerID;
		this.paymentType = paymentType;
		this.totalCost = totalCost;
		this.deliveryMethod = deliveryMethod;
	}
	/**
	 * @return
	 * 
	 * returns the order ID
	 */
	public int getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID
	 * 
	 * sets the order ID
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	/**
	 * @return
	 * 
	 * returns the customer ID of the order
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * @param customerID
	 * 
	 * sets the custmer ID of an order
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * @return
	 * 
	 * returns the payment type of the order
	 * cash,card,check
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType
	 * 
	 * sets the payment type of the order
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return
	 * 
	 * returns the total cost of an order
	 */
	public double getTotalCost() {
		return totalCost;
	}
	/**
	 * sets the total cost of an order
	 */
	public void setTotalCost() { //iterate through the array list
		for (OrderHelper item : list){
			totalCost += item.getProductPrice();
		}
	}
	/**
	 * @param value
	 * 
	 * increments the total cost of the order
	 */
	public void incrementTotalCost(double value){ //this could be for a coupon or sale or something
		totalCost += value;
	}
	/**
	 * @return
	 * 
	 * returns the delivery method of an order
	 * Pick Up, Delivery
	 */
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	/**
	 * @param deliveryMethod
	 * 
	 * sets the delivery method of the order
	 */
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	/**
	 * adds an order to the database
	 */
	public void addToDatabase(){
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into orders (orderID, customerID, paymentType, totalCost, deliveryMethod) values (" +
		getOrderID() + "," + getCustomerID() + ',' + '"' + getPaymentType() + '"'
		+ ',' + getTotalCost() + ',' + '"' + getDeliveryMethod() + '"' + ");";
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
