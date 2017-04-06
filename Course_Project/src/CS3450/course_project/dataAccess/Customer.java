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
	 * stores the reward card number of the customer
	 */
	private int rewardCard;
	/**
	 * total reward points of a customer, if the customer doesn't have a reward card this will be 0
	 */
	private int rewardPoints;
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
	public Customer(int customerID, String name, String address, int rewardCard, int rewardPoints){
		this.customerID = customerID;
		this.name = name;
		this.address = address;
		this.rewardCard = rewardCard;
		this.rewardPoints = rewardPoints;
	}
	/**
	 * @return
	 * 
	 * returns the ID of a customer
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * @return
	 * 
	 * returns the name of a specific customer
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * 
	 * sets the name of a specific customer
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * 
	 * returns the address of a specific customer
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 * 
	 * sets the address of a specific customer
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @param rewardCard
	 * 
	 * set the reward card number of the customer
	 */
	public void setRewardCard(int rewardCard){
		this.rewardCard = rewardCard;
	}
	
	/**
	 * @return
	 * 
	 * get the reward card number of the customer
	 */
	public int getRewardCard(){
		return rewardCard;
	}
	
	/**
	 * @return
	 * 
	 * get the number of reward points of the customer
	 */
	public int getRewardPoints(){
		return rewardPoints;
	}
	
	/**
	 * @param points
	 * 
	 * set the number of reward points for a customer
	 */
	public void setRewardPoints(int points){
		rewardPoints = points;
	}
	
	/**
	 * @return
	 * 
	 * know if a customer has a rewards card
	 */
	public boolean isRewardsCustomer(){
		return rewardCard > 0;
	}

	
}
