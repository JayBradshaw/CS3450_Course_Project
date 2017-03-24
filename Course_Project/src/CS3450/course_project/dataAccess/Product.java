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
	 * total units of a product
	 */
	private int availableUnits;
	/**
	 * product barcode number
	 */
	private int barcodeNumber;
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
}
