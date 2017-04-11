package CS3450.course_project.userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static java.util.Comparator.comparing;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import CS3450.course_project.businessLogic.OrderHelper;
import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.SaleItem;
import CS3450.course_project.dataAccess.databaseAccess;

public class AddProductScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Add Product");
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
	 * button for adding an item
	 */
	private JButton addItem = new JButton("Add Item");
	/**
	 * button for canceling an item
	 */
	private JButton checkoutScreen = new JButton("Cancel");
	/**
	 * primary color for the GUI
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
	 * JBradshaw: Array list to store the products
	 */
	private ArrayList<Product> productList;
	/**
	 * array list to store the items in the current order
	 */
	private ArrayList<OrderHelper> orderHelperList;
	/**
	 * spinner object for getting quantity of product 
	 */
	private SpinnerModel model1 = new SpinnerNumberModel();
	/**
	 * spinner for the spinner model
	 */
	private JSpinner spinner = new JSpinner(model1);
	/**
	 * Drop down menu for selecting items.
	 */
	private JComboBox dropDownMenu = new JComboBox();
	/**
	 * temporary product that is set to whatever product the drop down menu is on
	 */
	private Product temporary;
	/**
	 * JBradshaw: Allow ability to return to the main screen
	 */
	private CheckoutScreen checkoutscreen;
	/**
	 * general instructions for adding items.
	 */
	private JLabel instruction;
	/**
	 * list of items that are on sale
	 */
	private ArrayList<SaleItem> saleList = new ArrayList<SaleItem>();
	private Customer currentCustomer;

	
	/**
	 * @param productList
	 * @param customerList
	 * @param orderHelperList
	 * @param orderList
	 * 
	 * non-default constructor
	 */
	@SuppressWarnings("unchecked")
	public AddProductScreen(databaseAccess databaseConnection, ArrayList<OrderHelper> orderHelperList){
		//just need the product list and the orderHelperList
		productList = databaseConnection.getProductList();
		this.orderHelperList = orderHelperList;
		System.out.println("Getting sale items");
		setCurrentSaleItems(databaseConnection);
		//saleList = databaseConnection.getSaleList();
		currentCustomer = databaseConnection.getCurrentCustomer();
		System.out.println(currentCustomer.getName());
		//figure out how to sort the list of sale items manager will be able to edit this. Also need to check for valid date
		Collections.sort(saleList);
		for(SaleItem I:saleList){
			System.out.println(I.getStartDate());
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		//string for holding object names
		String[] productNames = new String[productList.size()];
		int i=0;
				for(Product v : productList){
					productNames[i++] = v.getName();
				}
				
				
		//drop down menu for selecting item.
		//dropDownMenu.setModel(new DefaultComboBoxModel(productList.toArray()));
		dropDownMenu = new JComboBox(productNames);
		//change to .length-1 in case the list of products is empty
		dropDownMenu.setSelectedIndex(productNames.length - 1);
		//JBradshaw added this to make sure that if the user doesn't change
		//the drop down it will still add the item that first appears
		temporary = productList.get(productNames.length-1); 
		dropDownMenu.setBackground(baseColor);
		dropDownMenu.setForeground(secondaryColor);
		dropDownMenu.setFont(baseFont);
		dropDownMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cb = (JComboBox) e.getSource();
				temporary = productList.get(cb.getSelectedIndex());
				System.out.println("selected: " + (String)cb.getSelectedItem());
				
			}
		});
		
		//loops the spinner from 0 to max and vice versa.
		spinner.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				if((int)spinner.getValue() > temporary.getAvailableUnits()){
					spinner.setValue(0);
				}
				if((int)spinner.getValue() < 0){
					spinner.setValue(temporary.getAvailableUnits());
				}
			}
		});
		
		GridBagConstraints r = new GridBagConstraints();
		r.weightx = .16;
	    r.weighty = .08;
	    r.gridx = 1;
	    r.gridy = 3;
	    r.gridwidth = 1;
	    r.fill = GridBagConstraints.BOTH;
	    
	    //quantity
	    GridBagConstraints f = new GridBagConstraints();
		f.weightx = .04;
	    f.weighty = .08;
	    f.gridx = 2;
	    f.gridy = 3;
	    f.gridwidth = 1;
	    f.fill = GridBagConstraints.BOTH;
	   
		storeHeader.setLayout(new BoxLayout(storeHeader, BoxLayout.X_AXIS));
		JLabel icon1Label = new JLabel();
		JLabel textLabel = new JLabel("Mr. Smith's Groceries");
		instruction = new JLabel("After clicking on drop down menu, select by typing or clicking on item name.");
		instruction.setForeground(secondaryColor);
		instruction.setBackground(baseColor);
		instruction.setFont(baseFont);
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
		//copied exactly for UI consistency
		//make the header look pretty
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
	    h.gridwidth = 3;
	    h.gridheight = 1;
		h.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints I = new GridBagConstraints();
		I.fill=GridBagConstraints.BOTH;
		I.weightx = .04;
		I.weighty = .16;
		I.gridx = 1;
		I.gridy = 2;
		
		
		
		//cancel aka return to checkout screen and do nothing
		checkoutScreen.setBackground(baseColor);
		checkoutScreen.setForeground(secondaryColor);
		checkoutScreen.setFont(buttonFont);
		checkoutScreen.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		GridBagConstraints m = new GridBagConstraints();
		m.weightx = .16;
	    m.weighty = .16;
	    m.gridx = 1;
	    m.gridy = 5;
	    m.gridwidth = 1;
	    m.fill = GridBagConstraints.BOTH;
	    //returns to previous checkout screen.
	    checkoutScreen.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//cancel order 
						System.out.println("Cancel Button Pressed!!!!");
						
						//close checkout window and take user back to main screen
						System.out.println("Back to main screen...");
						//JBradshaw: add ability to return back to the main screen
						frame.dispose();
						//use the previous old list if we cancel the changes we made
						checkoutscreen = new CheckoutScreen(databaseConnection,orderHelperList);
					}
					
		});
	    
	    
	    //add the selected item and quantity
	    addItem.setBackground(baseColor);
	    addItem.setForeground(secondaryColor);
	    addItem.setFont(buttonFont);
	    addItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		
		GridBagConstraints a = new GridBagConstraints();
		a.weightx = .16;
	    a.weighty = .16;
	    a.gridx = 2;
	    a.gridy = 5;
	    a.gridwidth = 1;
	    a.fill = GridBagConstraints.BOTH;
	    
	    //returns to previous checkout screen.
	    addItem.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//cancel order 
						System.out.println("Add Item Button Pressed!!!!");
						System.out.println("adding " + spinner.getValue() + " " + temporary.getName());
						//JBradshaw right here is where we will update the orderHelper list to 
						//include the new product
						System.out.println(temporary.getPrice());
						int productIndex = 0; //stores the index of the product
						//make sure we are entering valid values
						boolean invalidEntry = false;
						for (int i = 0; i < productList.size(); ++i){
							if (productList.get(i).getName() == temporary.getName()){
								productIndex = i;
								if ((int)spinner.getValue() > getTotalAvailable(temporary)){
									//print out an error message
									invalidEntry = true;
									JOptionPane.showMessageDialog(null, "Error! Quantity to add is greater than inventory!");
								}
							}
						}
						if ((int)spinner.getValue() > 0 && !invalidEntry){
							if (contains(temporary.getName())){ //if the item is already in the list, just increment the quantity
								for (int i = 0; i < orderHelperList.size(); ++i){
									//if the correct object increment the quantity
									if (orderHelperList.get(i).getProductName() == temporary.getName()){
										orderHelperList.get(i).setQuantity(orderHelperList.get(i).getQuantity() + (int)spinner.getValue());
									}
								}
							}
							else { //create a new order helper item
								System.out.println("Adding new item to order helper");
								if (onSale(temporary,databaseConnection)){ //if the item is on sale get the sale price
									orderHelperList.add(new OrderHelper(productList.get(productIndex).getName(),getSalePrice(temporary),(int)spinner.getValue()));
								}
								else {
								orderHelperList.add(new OrderHelper(productList.get(productIndex).getName(),productList.get(productIndex).getPrice(),(int)spinner.getValue()));
								}
							}
							System.out.println("Back to main screen...");
							//JBradshaw: add ability to return back to the main screen
							frame.dispose();
							checkoutscreen = new CheckoutScreen(databaseConnection,orderHelperList);
						}
						else if (!invalidEntry){
							if ((int) spinner.getValue() == 0){
								JOptionPane.showMessageDialog(null, "No reason to add 0 items!");
							}
							else {
							JOptionPane.showMessageDialog(null, "Error! Cannot add negative values");
							}
						}
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
	    fo.gridy = 6;
	    fo.gridwidth = 3;
	    fo.gridheight = 1;
		fo.anchor = GridBagConstraints.PAGE_END;
		
		//add components to container
		pane.add(storeHeader, h);
		pane.add(addItem, a);
		pane.add(dropDownMenu, r);
		pane.add(spinner, f);
		pane.add(instruction, I);
		pane.add(checkoutScreen, m);
		pane.add(storeFooter, fo);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * @param name
	 * @return
	 * 
	 * know if the order helper list contains a specific product
	 */
	public boolean contains(String name){
		for (int i = 0; i < orderHelperList.size(); ++i){
			if (orderHelperList.get(i).getProductName() == name){
				return true;
			}
		}
		return false;
	}
	/**
	 * @param product
	 * @return
	 * 
	 * figure out the total availability for a product
	 * should be the total units minus what is already a part of the order
	 * or if the product is not yet part of an order it should just be the total units
	 */
	private int getTotalAvailable(Product product){
		for (int i = 0; i < orderHelperList.size(); ++i){
			if (orderHelperList.get(i).getProductName().equals(product.getName())){
				return product.getAvailableUnits() - orderHelperList.get(i).getQuantity();
			}
		}
		//just return the total units if the product was not a part of the order
		return product.getAvailableUnits();
	}

	/**
	 * @param product
	 * @return
	 * 
	 * know whether or not a given product is on sale
	 */
	private boolean onSale(Product product,databaseAccess databaseConnection){
		//if a guest just return false because they are not eligible for rewards
		if (databaseConnection.getCurrentCustomer().getRewardCard() == 0) return false;
		//run through the list of items currently on sale
		for (SaleItem x : saleList){
			if (x.getName().equals(product.getName())) return true;
		}
		return false;
	}
	
	/**
	 * @param product
	 * @return
	 * 
	 * get the sale price of a product
	 */
	private double getSalePrice(Product product){
		for (SaleItem x : saleList){
			if (x.getName().equals(product.getName())) return x.getSalePrice();
		}
		return 0.0d;
	}
	
	/**
	 * @param saleItems
	 * 
	 * know which items are currently on sale based on time restrictions
	 */
	private void setCurrentSaleItems(databaseAccess databaseConnection){
		String date = getCurrentDate(); //get the date
		for (SaleItem x : databaseConnection.getSaleList()){
			//System.out.println(x.getName());
			//System.out.println(date);
			//System.out.println(x.getStartDate() + " | " + x.getEndDate());
			//System.out.println(x.getStartDate().compareTo(date) + " | " + x.getEndDate().compareTo(date));
			//if the item falls in the range then it is on sale
			if (x.getStartDate().compareTo(date) <=0 && x.getEndDate().compareTo(date) >=0){
				//System.out.println(date);
				//System.out.println(x.getStartDate() + " | " + x.getEndDate());
				//System.out.println(x.getStartDate().compareTo(date) + " | " + x.getEndDate().compareTo(date));
				saleList.add(x); //add the item to the sale list
			}
		}
	}
	
	/**
	 * @return
	 * returns the current date in the format yyyy/MM/dd
	 */
	private String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return (String)dateFormat.format(date);
	}
	
}
