/**
 * 
 */
package CS3450.course_project.businessLogic;

/**
 * @author Justin Bradshaw
 * 
 * this helper class will deal with obtaining all totals of the products
 * in a given range
 *
 */
public class ProductsInRange {
	/**
	 * id of the product
	 */
	private int productID;
	/**
	 * quantity purchased
	 */
	private int quantity;
	/**
	 * total cost based on quantity and price per unit
	 */
	private double totalCost;
	
	/**
	 * @param productID
	 * @param quantity
	 * @param totalCost
	 * 
	 * constructor to initialize objects
	 */
	public ProductsInRange(int productID, int quantity, double totalCost){
		this.productID = productID;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	/**
	 * @return
	 * 
	 * get the id of a specific product
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * @return
	 * 
	 * get the current quantity of a product
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 * 
	 * set the quantity of a product
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return
	 * 
	 * get the total cost for a given product
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost
	 * 
	 * set the total cost for a given product
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
