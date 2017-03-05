/**
 * 
 */
package CS3450.course_project.dataAccess;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import CS3450.course_project.businessLogic.CreditCard;
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
	private Customer customer;
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
	 * stores the credit card information of an order if necessary
	 */
	private CreditCard creditCard; //will be used for credit card info if a credit card is the payment type
	private ArrayList<OrderHelper> list;
	
	/**
	 * @param list
	 * 
	 * order constructor that takes a list of order helper items
	 */
	public Order(ArrayList<OrderHelper> list){
		this.list = list; //create a list of Order Helper Objects
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public double getTotalCost() {
		return totalCost;
	}
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
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public void setCardInfo(long cardNumber, String expirationDate, String name){
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationDate(expirationDate);
		creditCard.setName(name);
	}
	/**
	 * @param fileName 
	 * 
	 * prints the receipt for an order
	 */
	public void printReceipt(){ 
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		PrintWriter fileOutput = null;
		try {
			fileOutput = new PrintWriter("data/" + customer.getName() + timeStamp + ".txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		fileOutput.println("Mr. Smith's Groceries");
		fileOutput.println("Order ID: " + getOrderID());
		fileOutput.println("Date: " + timeStamp);
		fileOutput.println("Product Information:");
		for(OrderHelper item : list){ //print out the info for the products bought
			fileOutput.println(item.getProductName() + "\t( " + item.getQuantity() + " )\t" + item.getProductPrice());
		}
		fileOutput.println("Thank you for coming! Come back soon!");

		fileOutput.close();
	}

}