/**
 * 
 */
package CS3450.course_project.businessLogic;

/**
 * @author Justin Bradshaw
 * 
 * class that creates a credit card in the case that the user
 * wants to pay for their items with a credit card
 *
 */
public class CreditCard {
	private long cardNumber;
	private String expirationDate;
	private String name;
	
	public CreditCard(long cardNumber, String expirationDate, String name){
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.name = name;
	}
	
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
