/**
 * 
 */
package CS3450.course_project.userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Product;

/**
 * @author Melanie Pena
 *
 */
public class InventoryScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Inventory");
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
	private JLabel storeHeader = new JLabel("Mr. Smith's Groceries" , cartImage , JLabel.CENTER);
	/**
	 * stores the footer for the pane
	 */
	private JLabel storeFooter = new JLabel("Copyright Info", JLabel.CENTER);
	/**
	 * button for adding a new product to inventory
	 */
	private JButton addProduct = new JButton("Add product");
	/**
	 * button for managing existing products
	 */
	private JButton manageExistingProducts = new JButton("Manage Existing Products");
	/**
	 * button to show availability
	 */
	private JButton availability = new JButton("Show Availability");
	/**
	 * button to return to main screen
	 */
	private JButton mainScreen = new JButton("Return to Main Screen");
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
	 * font specifications for the buttons
	 */
	private Font buttonFont = new Font("Verdana", Font.PLAIN, 16);
	
	/**
	 * JBradshaw: Allow ability to return to the main screen
	 */
	private MainScreen screen;
	
	private AddInventoryScreen addInvScreen;
	private ProductListScreen prodListScreen;
	private ManageProductScreen manageProductScreen;
	
	
	
	/**
	 * array list to store all of the products
	 */
	private ArrayList<Product> productList;
	private ArrayList<Customer> customerList;
	/**
	 * default constructor
	 */ 
	public InventoryScreen(ArrayList<Product> productList, ArrayList<Customer> customerList){ //JBradshaw added parameter productList so that when we jump from screen to screen we don't lose the products
		this.productList = productList;
		this.customerList = customerList;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout()); 
		
		//make the header look pretty
		storeHeader.setIconTextGap(25);
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		storeHeader.setHorizontalTextPosition(SwingConstants.LEADING);
		storeHeader.setOpaque(true);
		//constraints for header
		GridBagConstraints h = new GridBagConstraints();
		h.fill = GridBagConstraints.BOTH;
		h.weightx = 1;
	    h.weighty = .16;
	    h.gridx = 0;
	    h.gridy = 0;
	    h.gridwidth = 2;
	    h.gridheight = 1;
		h.anchor = GridBagConstraints.PAGE_START;
		
		//make add item button look pretty
		addProduct.setBackground(baseColor);
		addProduct.setForeground(secondaryColor);
		addProduct.setFont(buttonFont);
		addProduct.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		//constraints for add product button 
		GridBagConstraints a = new GridBagConstraints();
		a.weightx = .5;
	    a.weighty = .34;
	    a.gridx = 0;
	    a.gridy = 1;
	    a.gridwidth = 1;
	    a.fill = GridBagConstraints.BOTH;
		//listener for the add procuct button
	    addProduct.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//creates pop up button to input product info
						System.out.println("Add Product Button Pressed!!!!");
						//adds product and info to database
						frame.dispose();
						addInvScreen = new AddInventoryScreen(productList, customerList);
						System.out.println("Adding product to inventory");
					}
		});

	    //make button look pretty
  		manageExistingProducts.setBackground(baseColor);
  		manageExistingProducts.setForeground(secondaryColor);
  		manageExistingProducts.setFont(buttonFont);
  		manageExistingProducts.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
  		//constraints for button 
  		GridBagConstraints m = new GridBagConstraints();
  		m.weightx = .5;
  	    m.weighty = .34;
  	    m.gridx = 1;
  	    m.gridy = 1;
  	    m.gridwidth = 1;
  	    m.fill = GridBagConstraints.BOTH;
  		//listener for manage products button
  	    manageExistingProducts.addActionListener(
  				new ActionListener(){
  					@Override
  					public void actionPerformed(ActionEvent e) {
  						//opens window to edit current inventory
  						System.out.println("Manage Existing Products Button Pressed!!!!");
  						//allows user to change product info
  						frame.dispose();
  						manageProductScreen = new ManageProductScreen(productList, customerList);
  						System.out.println("Edit products here");
  					}
  		});

  	    //make button look pretty
  		availability.setBackground(baseColor);
  		availability.setForeground(secondaryColor);
  		availability.setFont(buttonFont);
  		availability.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
  		//constraints for button 
  		GridBagConstraints av = new GridBagConstraints();
  		av.weightx = .5;
  	    av.weighty = .34;
  	    av.gridx = 0;
  	    av.gridy = 2;
  	    av.gridwidth = 1;
  	    av.fill = GridBagConstraints.BOTH;
  		//listener for the availability button
  		availability.addActionListener(
  				new ActionListener(){
  					@Override
  					public void actionPerformed(ActionEvent e) {
  						//opens window to view current inventory
  						System.out.println("Show Availability Button Pressed!!!!");
  						frame.dispose();
  						prodListScreen = new ProductListScreen(productList, customerList);
  						System.out.println("Creating new window to show availability of products");
  					}
  		});

  	  	//make mainScreen button look pretty
  		mainScreen.setBackground(baseColor);
  		mainScreen.setForeground(secondaryColor);
  		mainScreen.setFont(buttonFont);
  		mainScreen.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
  		//constraints for button 
  		GridBagConstraints ms = new GridBagConstraints();
  		ms.weightx = .5;
  	    ms.weighty = .34;
  	    ms.gridx = 1;
  	    ms.gridy = 2;
  	    ms.gridwidth = 1;
  	    ms.fill = GridBagConstraints.BOTH;
  		//listener for the "" button
  	    mainScreen.addActionListener(
  				new ActionListener(){
  					@Override
  					public void actionPerformed(ActionEvent e) {
  						//opens window to view current inventory
  						System.out.println("Return to Main Screen Button Pressed!!!!");
  						//close inventory window 
  						System.out.println("Return to main screen...");
  						frame.dispose();
  						screen = new MainScreen(productList, customerList);
  					}
  		});
  	    
		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setOpaque(true);
		//constraints for footer
		GridBagConstraints fo = new GridBagConstraints();
		fo.fill = GridBagConstraints.BOTH;
		fo.weightx = 1;
	    fo.weighty = .16;
	    fo.gridx = 0;
	    fo.gridy = 3;
	    fo.gridwidth = 3;
	    fo.gridheight = 1;
		fo.anchor = GridBagConstraints.PAGE_END;
		
		//add components to container
		pane.add(storeHeader, h);
		pane.add(addProduct, a);
		pane.add(manageExistingProducts, m);
		pane.add(availability, av);
		pane.add(mainScreen, ms);
		pane.add(storeFooter, fo);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
