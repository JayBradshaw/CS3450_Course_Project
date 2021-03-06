/**
 * 
 */
package CS3450.course_project.userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import CS3450.course_project.dataAccess.OrderHelper;
import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.DatabaseAccess;

public class RemoveProductScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Remove Product");
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
	private JButton removeItem = new JButton("Remove Item");
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
	 * list of customers
	 */
	private ArrayList<OrderHelper> orderHelperList;
	/**
	 * spinner object for getting quantity of product 
	 */
	private SpinnerModel model1 = new SpinnerNumberModel();
	/**
	 * spinner based on the spinner model
	 */
	private JSpinner spinner = new JSpinner(model1);
	/**
	 * Drop down menu for selecting items.
	 */
	private JComboBox dropDownMenu = new JComboBox();
	/**
	 * temporary product that will be set based on the drop down menu
	 */
	private OrderHelper temporary;
	/**
	 * JBradshaw: Allow ability to return to the main screen
	 */
	private CheckoutScreen checkoutscreen;
	/**
	 * connection to the database
	 */
	DatabaseAccess databaseConnection;

	
	/**
	 * @param productList
	 * @param customerList
	 * @param orderHelperList
	 * @param orderList
	 * 
	 * non-default constructor
	 */
	public RemoveProductScreen(DatabaseAccess databaseConnection, ArrayList<OrderHelper> orderHelperList){
		this.databaseConnection = databaseConnection;
		productList = databaseConnection.getProductList();
		this.orderHelperList = orderHelperList;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		//string for holding object names.
		String[] productNames = new String[orderHelperList.size()];
		int i=0;
				for(OrderHelper v : orderHelperList){
					productNames[i++] = getProdFromID(v.getProductID()).getName();
				}
				
				
		//drop down menu for selecting item.
		//dropDownMenu.setModel(new DefaultComboBoxModel(productList.toArray()));
		dropDownMenu = new JComboBox(productNames);
		//change to .length-1 in case the list of products is empty
		dropDownMenu.setSelectedIndex(productNames.length - 1);
		//JBradshaw added this to make sure that if the user doesn't change
		//the drop down it will still add the item that first appears
		
		//should now only show the items in the current checkout list
		temporary = orderHelperList.get(productNames.length-1); 
		//dropDownMenu.setBackground(secondaryColor);
		//dropDownMenu.setForeground(baseColor);
		dropDownMenu.setFont(baseFont);
		dropDownMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cb = (JComboBox) e.getSource();
				temporary = orderHelperList.get(cb.getSelectedIndex());
				System.out.println("selected: " + (String)cb.getSelectedItem());
				
			}
		});
		
		//loops the spinner from 0 to max and vice versa.
				spinner.addChangeListener(new ChangeListener(){
					@Override
					public void stateChanged(ChangeEvent e){
						if((int)spinner.getValue() > orderHelperList.get(dropDownMenu.getSelectedIndex()).getQuantity()){
							System.out.println(dropDownMenu.getSelectedIndex());
							System.out.println((int)spinner.getValue() > orderHelperList.get(dropDownMenu.getSelectedIndex()).getQuantity());
							spinner.setValue(0);
							
							
						}
						if((int)spinner.getValue() < 0){
							spinner.setValue(orderHelperList.get(dropDownMenu.getSelectedIndex()).getQuantity());
						}
					}
				});
		
		GridBagConstraints r = new GridBagConstraints();
		r.weightx = .16;
	    r.weighty = .08;
	    r.gridx = 1;
	    r.gridy = 1;
	    r.gridwidth = 1;
	    r.fill = GridBagConstraints.BOTH;
	    
	    //quantity
	    GridBagConstraints f = new GridBagConstraints();
		f.weightx = .04;
	    f.weighty = .08;
	    f.gridx = 2;
	    f.gridy = 1;
	    f.gridwidth = 1;
	    f.fill = GridBagConstraints.BOTH;
	   
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
		//copied exactly for UI consistency
		//make the header look pretty
		storeHeader.setBackground(secondaryColor);
		storeHeader.setForeground(baseColor);
		storeHeader.setFont(baseFont);
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
		
		
		//cancel aka return to checkoutscreen and do nothing
		//checkoutScreen.setBackground(secondaryColor);
		//checkoutScreen.setForeground(baseColor);
		checkoutScreen.setFont(buttonFont);
		//checkoutScreen.setBorder(BorderFactory.createLineBorder(baseColor,5));
		GridBagConstraints m = new GridBagConstraints();
		m.weightx = .16;
	    m.weighty = .16;
	    m.gridx = 1;
	    m.gridy = 3;
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
						//if we cancel we just use the old original orderList and 
						checkoutscreen = new CheckoutScreen(databaseConnection, orderHelperList);
					}
					
		});
	    
	    
	    //add the selected item and quantity
	    //removeItem.setBackground(secondaryColor);
	    //removeItem.setForeground(baseColor);
	    removeItem.setFont(buttonFont);
	    //removeItem.setBorder(BorderFactory.createLineBorder(baseColor,5));
		
		GridBagConstraints a = new GridBagConstraints();
		a.weightx = .16;
	    a.weighty = .16;
	    a.gridx = 2;
	    a.gridy = 3;
	    a.gridwidth = 1;
	    a.fill = GridBagConstraints.BOTH;
	    
	    //returns to previous checkout screen.
	    removeItem.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//cancel order 
						System.out.println("Remove Item Button Pressed!!!!");
						
						//JBradshaw right here is where we will update the orderHelper list to 
						//include the new product
						int orderHelperIndex = 0;
						
						//make sure that the item you want to remove is a part of the current order
						if (!isInOrderList(temporary.getProductID())){
							JOptionPane.showMessageDialog(null, "Error! Item is not a part of the current order!");
							return;
						}
						
						//make sure that they aren't trying to remove a negative value
						if ((int)spinner.getValue() < 0){
							JOptionPane.showMessageDialog(null, "Error! Cannot remove a negative number of items!");
							return;
						}
						
						//make sure we aren't removing 0 items
						if ((int)spinner.getValue() == 0){
							JOptionPane.showMessageDialog(null, "Error! Cannot remove 0 items!");
							return;
						}
						
						for (int i = 0; i < orderHelperList.size(); ++i){
							if (orderHelperList.get(i).getProductID() == temporary.getProductID()){
								orderHelperIndex = i;
							}
						}
						//this should be redundant now.
						//check to make sure we aren't removing more than we have
						if (orderHelperList.get(orderHelperIndex).getQuantity() < (int)spinner.getValue()){
							JOptionPane.showMessageDialog(null, "Error! Cannot remove a quantity greater than\n what is a part of the current order!");
							return;
						} 
						
						//ok now that we have made the checks we can remove the quantity from the orderHelperList
						orderHelperList.get(orderHelperIndex).setQuantity(orderHelperList.get(orderHelperIndex).getQuantity()-(int)spinner.getValue());
						//if the quantity becomes 0, remove the item from the list
						if (orderHelperList.get(orderHelperIndex).getQuantity() == 0){
							orderHelperList.remove(orderHelperIndex);
						}
						//System.out.println("removing " + spinner.getValue() + " " + temporary.getProductName());
						System.out.println("Back to checkout screen...");
						
						//JBradshaw: add ability to return back to the main screen
						frame.dispose();
						checkoutscreen = new CheckoutScreen(databaseConnection,orderHelperList);
					}	
		});
		//make the footer look pretty
		storeFooter.setBackground(secondaryColor);
		storeFooter.setForeground(baseColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setOpaque(true);
		//constraints for footer
		GridBagConstraints fo = new GridBagConstraints();
		fo.fill = GridBagConstraints.BOTH;
		fo.weightx = 1;
	    fo.weighty = .16;
	    fo.gridx = 0;
	    fo.gridy = 5;
	    fo.gridwidth = 3;
	    fo.gridheight = 1;
		fo.anchor = GridBagConstraints.PAGE_END;
		
		//add components to container
		pane.add(storeHeader, h);
		pane.add(removeItem, a);
		pane.add(dropDownMenu, r);
		pane.add(spinner, f);
		pane.add(checkoutScreen, m);
		pane.add(storeFooter, fo);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * @param name
	 * @return
	 * 
	 * determines whether or not a product is a part of the current order
	 */
	boolean isInOrderList(int id){
		for (int i = 0; i < orderHelperList.size(); ++i){
			if (orderHelperList.get(i).getProductID() == id){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param id
	 * @return
	 * 
	 * get a product from the id
	 */
	private Product getProdFromID(int id){
		for (Product p : databaseConnection.getProductList()){
			if (p.getID() == id) return p;
		}
		return null;
	}
	
}

