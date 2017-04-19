/**
 * 
 */
package CS3450.course_project.dataAccess;

/**
 * @author Justin Bradshaw
 * 
 *This class will deal with all items that are currently on sale
 *stores id product name and sale price
 */
public class SaleItem implements Comparable{
	/**
	 * id of the sale item. This cannot be altered
	 */
	private int id;
	/**
	 * name of the sale item
	 */
	private String name;
	/**
	 * sale price 
	 */
	private double salePrice;
	/**
	 * start date for the sale in the form yyyy//MM/dd
	 */
	private String startDate;
	/**
	 * end date for the slae in the form yyyy/MM/dd
	 */
	private String endDate;
	/**
	 * default constructor
	 */
	SaleItem(){
		
	}
	/**
	 * @param id
	 * @param name
	 * @param salePrice
	 * @param startDate
	 * @param endDate
	 * 
	 * non-default constructor to initialize objects
	 */
	public SaleItem(int id, String name, double salePrice, String startDate, String endDate){
		this.id = id;
		this.name = name;
		this.salePrice = salePrice;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * @return
	 * 
	 * returns the id of a sale item
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * @return
	 * 
	 * returns the product name of a sale item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return
	 * 
	 * returns the price of a sale item
	 */
	public double getSalePrice() {
		return salePrice;
	}
	
	/**
	 * @param salePrice
	 * 
	 * set the sale price of a product
	 */
	public void setSalePrice(double salePrice){
		this.salePrice = salePrice;
	}
	
	/**
	 * @return
	 * 
	 * returns the start date of a sale in the form yyyy/MM/dd
	 */
	public String getStartDate(){
		return startDate;
	}
	
	/**
	 * @param startDate
	 * 
	 * sets the start date for a sale
	 */
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
	
	/**
	 * @return
	 * 
	 * returns the end date for a sale in the form yyyy/MM/dd
	 */
	public String getEndDate(){
		return endDate;
	}
	
	/**
	 * @param endDate
	 * 
	 * sets the end date for a sale
	 */
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
	
	@Override
	public int compareTo(Object o) {
		int compare = startDate.compareTo(((SaleItem)o).getStartDate());
		 return compare;
	}
	
	/**
	 * @param start
	 * @param end
	 * @return
	 * 
	 * know whether or not a sale item is currently on sale
	 */
	public boolean fallsInRange(String start, String end){
		if (startDate.compareTo(start) <=0 && endDate.compareTo(end) >= 0) return true;
		return false;
	}
}
