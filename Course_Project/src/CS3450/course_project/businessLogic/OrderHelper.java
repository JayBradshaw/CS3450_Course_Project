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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
