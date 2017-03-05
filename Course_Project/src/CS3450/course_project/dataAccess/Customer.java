/**
 * 
 */
package CS3450.course_project.dataAccess;

/**
 * @author Justin Bradshaw
 *
 *Class that will store all of the information
 *related to a specific customer
 *
 *A customer may make more than one order, so an order needs to store a 
 *customer ID
 *
 */
public class Customer {
	/**
	 * stores the specific ID of each customer
	 */
	private int CustomerID;
	/**
	 * name of the customer
	 */
	private String name;
	/**
	 * stores the customer's address
	 */
	private String address;
	//figure out something with orders. Do we care if the customer is linked to 
	//a specific set of orders? Is it really necessary
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}