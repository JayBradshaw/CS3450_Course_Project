/**
 * 
 */
package CS3450.course_project.businessLogic;

/**
 * @author Justin Bradshaw
 *
 *
 *this class will work with the order so that only necessary
 *info for the product will be stored in the order
 *the only things that an order needs in order to calculate the cost 
 *is the item price and the quantity of that item
 *the product name is for printing out a receipt
 *
 *this info will be used for printing a receipt and for
 *calculating the total cost for an order
 */
public class OrderHelper {
	private int orderID;
	private int productID;
	//private String productName;
	private double productPrice;
	private int quantity;
	private String orderDate;
	
	/**
	 * default constructor
	 */
	public OrderHelper(){
		//empty
	}
	/**
	 * @param productName
	 * @param productPrice
	 * @param quantity
	 * 
	 * constructor
	 */
	public OrderHelper(int orderID,int productID, double productPrice, int quantity, String orderDate){
		this.orderID = orderID;
		this.productID = productID;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}
	/**
	 * @param id
	 * set the order id
	 */
	public void setOrderID(int id){
		orderID = id;
	}
	/**
	 * @return
	 * get the order ID
	 */
	public int getOrderID(){
		return orderID;
	}
	/**
	 * @param id
	 * 
	 * set the id for the helper item
	 */
	public void setProductID(int id){
		productID = id;
	}
	/**
	 * @return
	 * 
	 * get the product id
	 */
	public int getProductID(){
		return productID;
	}
	/**
	 * @return
	 * 
	 * returns the price of a product
	 */
	public double getProductPrice() {
		return productPrice;
	}
	/**
	 * @param productPrice
	 * 
	 * sets the price of a product
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	/**
	 * @return
	 * 
	 * returns the quantity ordered of a given product
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity
	 * 
	 * sets the quantity ordered of a given product
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return
	 * 
	 * returns the date of the order
	 */
	public String getDate(){
		return orderDate;
	}
	/**
	 * @param date
	 * 
	 * set the date of the order
	 */
	public void setDate(String date){
		orderDate = date;
	}

	
}
