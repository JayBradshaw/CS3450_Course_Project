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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import CS3450.course_project.businessLogic.OrderHelper;
import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;

public class RemoveProductScreen {
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
	private JLabel storeHeader = new JLabel("Mr. Smith's Groceries" , cartImage , JLabel.CENTER);
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
	private ArrayList<Customer> customerList;
	/**
	 * list for order helper
	 */
	private ArrayList<OrderHelper> orderHelperList;
	/**
	 * order list
	 */
	private ArrayList<Order> orderList;
	
	/**
	 * spinner object for getting quantity of product 
	 */
	private SpinnerModel model1 = new SpinnerNumberModel();
	private JSpinner spinner = new JSpinner(model1);
	
	/**
	 * Drop down menu for selecting items.
	 */
	private JComboBox dropDownMenu = new JComboBox();
	
	private Product temporary;
	/**
	 * JBradshaw: Allow ability to return to the main screen
	 */
	private CheckoutScreen checkoutscreen;

	
	public RemoveProductScreen(ArrayList<Product> productList, ArrayList<Customer> customerList, ArrayList<OrderHelper> orderHelperList, ArrayList<Order> orderList){
		this.productList = productList;
		this.customerList = customerList;
		this.orderHelperList = orderHelperList;
		this.orderList = orderList;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		//string for holding object names.
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
	   
		
		//copied exactly for UI consistency
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
	    h.gridwidth = 3;
	    h.gridheight = 1;
		h.anchor = GridBagConstraints.PAGE_START;
		
		
		//cancel aka return to checkoutscreen and do nothing
		checkoutScreen.setBackground(baseColor);
		checkoutScreen.setForeground(secondaryColor);
		checkoutScreen.setFont(buttonFont);
		checkoutScreen.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
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
						checkoutscreen = new CheckoutScreen(productList,customerList, orderHelperList, orderList);
					}
					
		});
	    
	    
	    //add the selected item and quantity
	    removeItem.setBackground(baseColor);
	    removeItem.setForeground(secondaryColor);
	    removeItem.setFont(buttonFont);
	    removeItem.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		
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
						System.out.println("removing " + spinner.getValue() + " " + temporary.getName());
						//JBradshaw right here is where we will update the orderHelper list to 
						//include the new product
						System.out.println(temporary.getPrice());
						int orderHelperIndex = 0;
						
						//make sure that the item you want to remove is a part of the current order
						if (!isInOrderList(temporary.getName())){
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
						
						//create temp OrderHelper so that we can use it later
						OrderHelper tempHelper = null;
						for (int i = 0; i < orderHelperList.size(); ++i){
							if (orderHelperList.get(i).getProductName() == temporary.getName()){
								orderHelperIndex = i;
								tempHelper = orderHelperList.get(i);
							}
						}
						//check to make sure we aren't removing more than we have
						if (tempHelper.getQuantity() < (int)spinner.getValue()){
							JOptionPane.showMessageDialog(null, "Error! Cannot remove a quantity greater than\n what is a part of the current order!");
							return;
						} 
						
						//ok now that we have made the checks we can remove the quantity from the orderList
						orderHelperList.get(orderHelperIndex).setQuantity(orderHelperList.get(orderHelperIndex).getQuantity()-(int)spinner.getValue());
						
						System.out.println("Back to main screen...");
						//JBradshaw: add ability to return back to the main screen
						frame.dispose();
						checkoutscreen = new CheckoutScreen(productList,customerList,orderHelperList,orderList);
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
	
	boolean isInOrderList(String name){
		for (int i = 0; i < orderHelperList.size(); ++i){
			if (orderHelperList.get(i).getProductName() == name){
				return true;
			}
		}
		return false;
	}
	
	
}
