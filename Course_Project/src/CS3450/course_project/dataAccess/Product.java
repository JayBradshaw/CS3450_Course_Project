/**
 * 
 */
package CS3450.course_project.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBarcodeNumber() {
		return barcodeNumber;
	}
	public void setBarcodeNumber(int barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
	public int getAvailableUnits() {
		return orderAvailability;
	}
	public int getTotalUnits(){
		return availableUnits;
	}
	public void setAvailableUnits(int availableUnits) {
		this.availableUnits = availableUnits;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderInfo() {
		return providerInfo;
	}
	public void setProviderInfo(String providerInfo) {
		this.providerInfo = providerInfo;
	}
	public int getOrderAvailability(){
		return orderAvailability;
	}
	public void setOrderAvailability(int value){
		this.orderAvailability = value;
	}
	
	public void addToDatabase(){
		Connection con = null;
		PreparedStatement statement = null;
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
			statement = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
