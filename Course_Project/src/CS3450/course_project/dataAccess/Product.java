/**
 * 
 */
package CS3450.course_project.dataAccess;

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
	private long barcodeNumber;
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
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBarcodeNumber() {
		return barcodeNumber;
	}
	public void setBarcodeNumber(long barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
	public int getAvailableUnits() {
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
	
}