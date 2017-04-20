/**
 * 
 */
package CS3450.course_project.userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.SaleItem;
import CS3450.course_project.dataAccess.DatabaseAccess;

/**
 * @author Justin Bradshaw
 * 
 * class for adding a new sale item
 *
 */
public class AddNewItem implements ActionListener{
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Add New Sale Item");
	/**
	 * pane to be contained in the frame
	 */
	private Container pane;
	/**
	 * image of a shopping card
	 */
	private ImageIcon cartImage = new ImageIcon("data/shoppingCart.png");
	/**
	 * stores the header for the pane
	 */
	private JLabel storeHeader = new JLabel();
	/**
	 * stores the footer for the pane
	 */
	private JLabel storeFooter = new JLabel("Copyright Info", JLabel.CENTER);
	/**
	 * base color for the GUI
	 */
	private Color baseColor = new Color(180,242,110);
	/**
	 * secondary color for the GUI
	 */
	private Color secondaryColor = Color.GRAY.darker();
	/**
	 * base font for the header
	 */
	private Font baseFont = new Font("Verdana", Font.BOLD,16);
	/**
	 * label for start date
	 */
	private JLabel startLabel = new JLabel("Start Date (YYYY/MM/DD) :");
	/**
	 * label for end date
	 */
	private JLabel endLabel = new JLabel("End Date (YYYY/MM/DD) :");
	/**
	 * label for name
	 */
	private JLabel nameLabel = new JLabel("Name:");
	/**
	 * label for price
	 */
	private JLabel priceLabel = new JLabel("Price:");
	/**
	 * text field for start date
	 */
	private JTextField startField = new JTextField(15);
	/**
	 * text field for end date
	 */
	private JTextField endField = new JTextField(15);
	/**
	 * name text field
	 */
	private JTextField nameField = new JTextField(15);
	/**
	 * price text field
	 */
	private JTextField priceField = new JTextField(15);
	/**
	 * cancel an edit or an add
	 */
	private JButton cancel = new JButton("Cancel");
	/**
	 * add a new item
	 */
	private JButton addNew = new JButton("Add New");
	/**
	 * panel for add portion
	 */
	private JPanel addPanel = new JPanel(new GridLayout(5,2));
	/**
	 * connection to the database
	 */
	private DatabaseAccess databaseConnection;
	/**
	 * @param databaseConnection
	 * constructor
	 */
	public AddNewItem(DatabaseAccess databaseConnection){
		this.databaseConnection = databaseConnection;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout()); 
		
		storeHeader.setLayout(new BoxLayout(storeHeader, BoxLayout.X_AXIS));
		JLabel icon1Label = new JLabel();
		JLabel textLabel = new JLabel("Mr. Smith's Groceries");
		textLabel.setForeground(baseColor);
		textLabel.setFont(baseFont);
		JLabel icon2Label = new JLabel();
		icon1Label.setIcon(databaseConnection.getEmployee().getImage());
		icon1Label.setIconTextGap(25);
		icon2Label.setIcon(cartImage);
		icon2Label.setIconTextGap(25);
		storeHeader.add(icon1Label);
		storeHeader.add(Box.createRigidArea(new Dimension(5,0)));
		storeHeader.add(textLabel);
		storeHeader.add(Box.createRigidArea(new Dimension(5,0)));
		storeHeader.add(icon2Label);
		
		//make the header look pretty
		storeHeader.setIconTextGap(25);
		storeHeader.setBackground(secondaryColor);
		storeHeader.setForeground(baseColor);
		storeHeader.setFont(baseFont);
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);
		
		//configurations for buttons used in specific cases
		//addNew.setBackground(secondaryColor);
		//addNew.setForeground(baseColor);
		addNew.addActionListener(this);
		//cancel.setBackground(secondaryColor);
		//cancel.setForeground(baseColor);
		cancel.addActionListener(this);
		
		initializeAdd();
		
		//make the footer look pretty
		storeFooter.setBackground(secondaryColor);
		storeFooter.setForeground(baseColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);
		
		//add components to container
		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(storeFooter,BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * initialize the add portion of the screen
	 */
	private void initializeAdd(){
		nameField.setText("");
		priceField.setText("");
		startField.setText("");
		endField.setText("");
		addPanel.add(nameLabel);
		addPanel.add(nameField);
		addPanel.add(priceLabel);
		addPanel.add(priceField);
		addPanel.add(startLabel);
		addPanel.add(startField);
		addPanel.add(endLabel);
		addPanel.add(endField);
		addPanel.add(addNew);
		addPanel.add(cancel);
		pane.add(addPanel,BorderLayout.CENTER);
	}
	
	/**
	 * @param name
	 * @return
	 * 
	 * know whether or not a user entered a valid new name for the new sale product
	 */
	private boolean validNewName(String name){
		if (name == null){
			return false;
		}
		//if the item is already on sale you should just edit it
		for (SaleItem x : databaseConnection.getSaleList()){
			if (x.getName().equals(name)) return false;
		}
		//make sure they gave a valid product name
		for (Product p : databaseConnection.getProductList()){
			if (p.getName().equals(name))return true;
		}
		return false;
	}

	/**
	 * @param name
	 * @param price
	 * @return
	 * 
	 * know if the user entered a valid price for the new item
	 */
	private boolean validNewPrice(String name, double price){
		if (price < 0) return false; //can't have negative price
		for (Product p : databaseConnection.getProductList()){
			if (p.getName().equals(name) && p.getPrice() > price){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @return
	 * 
	 * get the largest id from the sale list
	 */
	private int getLargestIndex(){
		int index = 0;
		for (SaleItem x: databaseConnection.getSaleList()){
			if (x.getID() > index) index = x.getID();
		}
		return index;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel){
			//just return to the previous screen
			frame.dispose();
			ManageSaleItems screen = new ManageSaleItems(databaseConnection);
		}
		else if (e.getSource() == addNew){
			//deal with adding a new product
			String name = nameField.getText();
			String price = priceField.getText();
			String startDate = startField.getText();
			String endDate = endField.getText();
			if (name.isEmpty() || price.isEmpty() || startDate.isEmpty() || endDate.isEmpty()){
				JOptionPane.showMessageDialog(null, "Error! All fields must be filled!");
				return;
			}
			if (!validNewName(name)){
				JOptionPane.showMessageDialog(null, "Error! Invalid product name!");
				return;
			}
			double dPrice;
			try {
				dPrice = Double.parseDouble(price);
			}catch (NumberFormatException n){
				JOptionPane.showMessageDialog(null, "Error! Price must be a double!");
				return;
			}
			if (!validNewPrice(name,dPrice)){
				JOptionPane.showMessageDialog(null, "Error! Invalid price entered!");
				return;
			}
			SaleItem item = new SaleItem(getLargestIndex() +1, name, dPrice, startDate,endDate);
			databaseConnection.addNewSaleItem(item);
			frame.dispose();
			ManageSaleItems screen = new ManageSaleItems(databaseConnection);
		}
		
	}
}
