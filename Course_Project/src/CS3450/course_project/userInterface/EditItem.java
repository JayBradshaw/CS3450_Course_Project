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
import CS3450.course_project.dataAccess.databaseAccess;

/**
 * @author Justin Bradshaw
 * 
 * class for editing an existing sale item
 *
 */
public class EditItem implements ActionListener {
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
	private JButton update = new JButton("Update");
	/**
	 * panel for add portion
	 */
	private JPanel editPanel = new JPanel(new GridLayout(5,2));
	/**
	 * connection to the database
	 */
	private databaseAccess databaseConnection;
	/**
	 * sale item to be worked with
	 */
	private SaleItem item;
	/**
	 * @param databaseConnection
	 * @param item
	 * 
	 * constructor that takes a sale item and a connection to the database
	 */
	public EditItem(databaseAccess databaseConnection , SaleItem item){
		this.databaseConnection = databaseConnection;
		this.item = item;
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
		//update.setBackground(secondaryColor);
		//update.setForeground(baseColor);
		update.addActionListener(this);
		//cancel.setBackground(secondaryColor);
		//cancel.setForeground(baseColor);
		cancel.addActionListener(this);
		
		initializeEdit(item);
		
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
	 * @param item
	 * initialize edit portion of the frame
	 */
	private void initializeEdit(SaleItem item){
		nameField.setText(item.getName());
		nameField.setEditable(false);
		priceField.setText(Double.toString(item.getSalePrice()));
		startField.setText(item.getStartDate());
		endField.setText(item.getEndDate());
		editPanel.add(nameLabel);
		editPanel.add(nameField);
		editPanel.add(priceLabel);
		editPanel.add(priceField);
		editPanel.add(startLabel);
		editPanel.add(startField);
		editPanel.add(endLabel);
		editPanel.add(endField);
		editPanel.add(update);
		editPanel.add(cancel);
		pane.add(editPanel,BorderLayout.CENTER);
		
	}
	
	/**
	 * @param name
	 * @param price
	 * @return
	 * 
	 * know if the user entered a valid new price for the item
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel){
			//just return to the previous screen
			frame.dispose();
			ManageSaleItems screen = new ManageSaleItems(databaseConnection);
		}
		else if (e.getSource() == update){
			//make the updates if possible
			String price = priceField.getText();
			String start = startField.getText();
			String end = endField.getText();
			if (price.isEmpty() || start.isEmpty() || end.isEmpty()){
				JOptionPane.showMessageDialog(null, "Error! Must fill all text fields!");
				return;
			}
			double dPrice;
			try {
				dPrice = Double.parseDouble(price);
			}
			catch (NumberFormatException n){
				JOptionPane.showMessageDialog(null, "Error! Invalid price entered!");
				return;
			}
			if (!validNewPrice(item.getName(),dPrice)){
				JOptionPane.showMessageDialog(null, "Error! Invalid price entered!");
				return;
			}
			item.setSalePrice(dPrice);
			item.setStartDate(start);
			item.setEndDate(end);
			databaseConnection.updateSaleList(item);
			frame.dispose();
			ManageSaleItems screen = new ManageSaleItems(databaseConnection);
		}
	}
}
