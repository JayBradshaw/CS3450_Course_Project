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

import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;

public class ManageProductScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Manage Products");
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
	 * list of products
	 */
	private InventoryScreen screen;
	private ArrayList<Product> ProductList;
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
	 * spinner object for getting quantity of product 
	 */
	private SpinnerModel model1 = new SpinnerNumberModel();
	private JSpinner spinner = new JSpinner(model1);
	/**
	 * return to inventory screen, cancel changes.
	 */
	private JButton cancel = new JButton("Cancel");
	/**
	 * return to inventory screen, finalize changes.
	 */
	private JButton updateBtn = new JButton("Apply Update");
	/**
	 * Drop down menu for selecting items.
	 */
	private JComboBox dropDownMenu = new JComboBox();
	private Product temporary;
	
	public ManageProductScreen(ArrayList<Product> productList, ArrayList<Customer> customerList, ArrayList<Order> orderList){
		this.ProductList = productList;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		
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
			
			spinner.setValue(productList.get(dropDownMenu.getSelectedIndex()).getTotalUnits());
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
					spinner.setValue(temporary.getAvailableUnits());
					
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
			
			
		//returns to the inventory screen.
		cancel.setBackground(baseColor);
		cancel.setForeground(secondaryColor);
	    cancel.setFont(buttonFont);
	    cancel.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
	    cancel.addActionListener(
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							
							frame.dispose();
							screen = new InventoryScreen(productList, customerList, orderList);
						}
						
			});
		    
		    GridBagConstraints c = new GridBagConstraints();
		    	c.fill = GridBagConstraints.BOTH;
		    	c.weightx = 1;
		    	c.weighty = .16;
		    	c.gridx = 1;
		    	c.gridy = 4;
		    	c.gridwidth = 1;
		    	
		    	
		    	
    	updateBtn.setBackground(baseColor);
    	updateBtn.setForeground(secondaryColor);
    	updateBtn.setFont(buttonFont);
    	updateBtn.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
    	updateBtn.addActionListener(
						new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								if ((int)spinner.getValue() < 0){
									JOptionPane.showMessageDialog(null, "Error cannot have negative\nquantity for product!");
									return;
								}
								temporary.setAvailableUnits((int)spinner.getValue());
								temporary.setOrderAvailability((int)spinner.getValue());
								//if(temporary.getAvailableUnits()==0){ //JBradshaw don't think you want to remove the product, just say that there are no available units
									//productList.remove(temporary);	
								//}
								
							frame.dispose();
							screen = new InventoryScreen(productList, customerList, orderList);
							}
						});
	    GridBagConstraints u = new GridBagConstraints();
    	u.fill = GridBagConstraints.BOTH;
    	u.weightx = 1;
    	u.weighty = .16;
    	u.gridx = 2;
    	u.gridy = 4;
    	u.gridwidth = 1;
    	
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
		pane.add(cancel, c);
		pane.add(updateBtn, u);
		pane.add(dropDownMenu, r);
		pane.add(spinner, f);
		pane.add(storeFooter, fo);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
