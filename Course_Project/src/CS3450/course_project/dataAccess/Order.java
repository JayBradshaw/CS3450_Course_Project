/**
 * 
 */
package CS3450.course_project.dataAccess;


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
	 * stores the info for an order including product names, cost per item, and quantity
	 * Example: "Apple,0.99,13|Peach,1.25,89|Coca-Cola,45" (item,cost,quantity)
	 */
	private String orderInfo;
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
	public Order(int orderID, int customerID, String paymentType, double totalCost, String deliveryMethod, String orderInfo){
		this.orderID = orderID;
		this.customerID =customerID;
		this.paymentType = paymentType;
		this.totalCost = totalCost;
		this.deliveryMethod = deliveryMethod;
		this.orderInfo = orderInfo;
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
	
	public String getOrderInfo(){
		return this.orderInfo;
	}

}
