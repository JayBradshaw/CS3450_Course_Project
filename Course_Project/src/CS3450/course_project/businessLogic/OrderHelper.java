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
	private String productName;
	private double productPrice;
	private int quantity;
	
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
	public OrderHelper(String productName, double productPrice, int quantity){
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}
	/**
	 * @return
	 * 
	 * returns  the name of a given product
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName
	 * 
	 * sets the name of a given product
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * create the needed info for an order (this is for one item)
	 * form: Apple^0.99^48
	 */
	public String createOrderInfo() {
		String toReturn = getProductName() + '^' + Double.toString(getProductPrice()) + '^' + Integer.toString(getQuantity());
	return toReturn;
	}
	
}
