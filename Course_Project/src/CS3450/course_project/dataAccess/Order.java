/**
 * 
 */
package CS3450.course_project.dataAccess;

/**
 * @author Justin Bradshaw
 *
 */
public class Order {
	private int orderID;
	private Customer customer;
	private String paymentType;
	private double totalCost;
	private String deliveryMethod;
	private CreditCard creditCard; //will be used for credit card info if a credit card is the payment type
	
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
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public void incrementTotalCost(double value){
		totalCost += value;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	
}
