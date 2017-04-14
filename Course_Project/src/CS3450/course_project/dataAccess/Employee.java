/**
 * 
 */
package CS3450.course_project.dataAccess;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	 * stores the id for an employee
	 */
	private int id;
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
	 * image stored as a string
	 */
	private String imageInfo;
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
	public Employee(int id, String name, String password, short accessRights, String imageInfo){
		this.id = id;
		this.name = name;
		this.password = password;
		this.accessRights = accessRights;
		this.imageInfo = imageInfo;
		image = new ImageIcon(imageInfo);
	}
	
	public int getID(){
		return id;
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
	public String getImageInfo(){
		return this.imageInfo;
	}
	public ImageIcon getImage() {
		Image img = null;
		try {
			img = ImageIO.read(new File(getImageInfo()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon =  new ImageIcon(img);
		return icon;
	}
	public void setImage(String imageInfo) {
		image = new ImageIcon(imageInfo);
	}
}
