/**
 * 
 */
package CS3450.course_project.dataAccess;

import javax.swing.ImageIcon;

/**
 * @author Justin Bradshaw
 * 
 * class that will deal with all of the employees
 * for accessRights:
 * 1: Full access (manager)
 * 2: Employee that can checkout and return items
 * 3: Cashier that can only do checkout
 * 4: Customer Support Employee that can only do returns
 *
 */
public class Employee {
	/**
	 * stores the name of the employee in the form first name last initial
	 * eg: James R
	 */
	private String name;
	/**
	 * stores the password for a given employee
	 */
	private String password;
	/**
	 * stores the access rights for an employee
	 */
	private short accessRights;
	/**
	 * image related to the employee
	 */
	private ImageIcon image;
	
	public Employee(){
		//empty
	}
	/**
	 * @param name
	 * @param password
	 * @param accessRights
	 * @param imageInfo
	 * 
	 * non-default constructor
	 */
	public Employee(String name, String password, short accessRights, String imageInfo){
		this.name = name;
		this.password = password;
		this.accessRights = accessRights;
		image = new ImageIcon(imageInfo);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public short getAccessRights() {
		return accessRights;
	}
	public void setAccessRights(short accessRights) {
		this.accessRights = accessRights;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(String imageInfo) {
		image = new ImageIcon(imageInfo);
	}
}
