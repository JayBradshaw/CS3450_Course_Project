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
	private int customerID;
	/**
	 * name of the customer
	 */
	private String name;
	/**
	 * stores the customer's address
	 */
	private String address;
	/**
	 * default constructor
	 */
	public Customer(){
		//empty
	}
	/**
	 * @param customerID
	 * @param name
	 * @param address
	 * 
	 * non-default constructor
	 */
	public Customer(int customerID, String name, String address){
		this.customerID = customerID;
		this.name = name;
		this.address = address;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		customerID = customerID;
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
