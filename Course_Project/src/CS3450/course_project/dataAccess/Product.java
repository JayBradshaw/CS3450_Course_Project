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
 * class for dealing with a specific product
 *
 */
public class Product {
	/**
	 * name of the product
	 */
	private String name;
	/**
	 * product barcode number
	 */
	private int barcodeNumber;
	/**
	 * how many units are available
	 */
	private int availableUnits;
	/**
	 * cost of one unit of the product
	 */
	private double price;
	/**
	 * name of the person or company who provides the product
	 */
	private String providerName;
	/**
	 * information for provider including address and phone number
	 */
	private String providerInfo;
	/**
	 * sets the availability of each product
	 */
	private int orderAvailability;
	
	
	/**
	 * default constructor
	 */
	public Product(){
		//empty default constructor
	}
	
	/**
	 * @param name
	 * @param availableUnits
	 * @param price
	 * @param barcodeNumber
	 * @param providerInfo
	 * @param providerName
	 * 
	 * non-default constructor to read from the database and initialize the variables
	 */
	public Product(String name, int availableUnits, double price, int barcodeNumber, String providerInfo, String providerName) {
		this.name = name;
		this.availableUnits = availableUnits;
		this.price = price;
		this.barcodeNumber = barcodeNumber;
		this.providerInfo = providerInfo;
		this.providerName = providerName;
		this.orderAvailability = availableUnits;
	}
	/**
	 * @return
	 * 
	 * returns the name of a product
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * 
	 * sets the name of a product
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * 
	 * returns the bar code number of a product
	 */
	public long getBarcodeNumber() {
		return barcodeNumber;
	}
	/**
	 * @param barcodeNumber
	 * 
	 * sets the bar code number of a product
	 */
	public void setBarcodeNumber(int barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
	/**
	 * @return
	 * 
	 * returns the number of available units left while creating an order
	 */
	public int getAvailableUnits() {
		return orderAvailability;
	}
	/**
	 * @return
	 * 
	 * returns the total number of units
	 */
	public int getTotalUnits(){
		return availableUnits;
	}
	/**
	 * @param availableUnits
	 * 
	 * sets the number of available units
	 */
	public void setAvailableUnits(int availableUnits) {
		this.availableUnits = availableUnits;
	}
	/**
	 * @return
	 * 
	 * returns the price of a product
	 */
	/**
	 * @return
	 * 
	 * returns the price of a product
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price
	 * 
	 * sets the price of a product
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return
	 * 
	 * returns the provider name
	 */
	public String getProviderName() {
		return providerName;
	}
	/**
	 * @param providerName
	 * 
	 * sets the provider name
	 */
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	/**
	 * @return
	 * 
	 * returns the provider info
	 */
	public String getProviderInfo() {
		return providerInfo;
	}
	/**
	 * @param providerInfo
	 * 
	 * sets the provider info
	 */
	public void setProviderInfo(String providerInfo) {
		this.providerInfo = providerInfo;
	}
	/**
	 * @return
	 * 
	 * returns the current availability of a product based on what is a part of the current order
	 */
	public int getOrderAvailability(){
		return orderAvailability;
	}
	/**
	 * @param value
	 * 
	 * sets the availability of a product based on what is a part of the current order
	 */
	public void setOrderAvailability(int value){
		this.orderAvailability = value;
	}
	
	/**
	 * adds a new product to the database
	 */
	public void addToDatabase(){
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into products (name, availableUnits, price, barcode, providerInfo, providerName) values (" +
		'"' + getName() + '"' + "," + getAvailableUnits() + ',' + getPrice() + ','
		+ getBarcodeNumber() + ',' + '"' + getProviderInfo() + '"' + ',' + '"' + getProviderName() + '"' + ");";
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * this will update the database when a product is modified in some way
	 */
	public void updateDatabase(){
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query = "update products set name = " + '"' + getName() + '"' + ", availableUnits = " + getTotalUnits() + 
				", barcode = " + getBarcodeNumber() + ", providerInfo = " + '"' + getProviderInfo() + '"' + ", providerName = " +
				'"' + getProviderName() + '"' + "\nwhere name = " + '"' + getName() + '"' + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * updates the total quantity of a product in the database
	 */ 
	public void updateQuantity(){
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query = "update products set availableUnits = " + getAvailableUnits() + " where name = " + '"' + getName() + '"' + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
