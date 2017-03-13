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
	
	/**
	 * @param cardNumber
	 * @param expirationDate
	 * @param name
	 * 
	 * non-default constructor
	 */
	public CreditCard(long cardNumber, String expirationDate, String name){
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.name = name;
	}
	
	/**
	 * @return
	 * 
	 * returns the card number
	 */
	public long getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber
	 * 
	 * sets the card number
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return
	 * 
	 * returns the expiration date for a card
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate
	 * 
	 * sets the expiration date for a card
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * @return
	 * 
	 * returns the name of the cardholder
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * 
	 * sets the name of the cardholder
	 */
	public void setName(String name) {
		this.name = name;
	}
}
