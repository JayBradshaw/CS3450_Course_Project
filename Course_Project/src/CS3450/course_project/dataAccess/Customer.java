/**
 * 
 */
package CS3450.course_project.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



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
		this.customerID = customerID;
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
	
	public void addToDatabase(){
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into customers (customerID, name, address) values (" +
		getCustomerID() + ',' + '"' + getName() + '"'
		+ ',' + '"' + getAddress()  + '"' + ");";
		System.out.println(query);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
