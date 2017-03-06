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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Product;

public class ProductListScreen {
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
	 * list of products
	 */
	private ArrayList<Product> productList;
	/**
	 * area for displaying all items and quantity available.
	 */
	private JTextArea textArea = new JTextArea();
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
	private InventoryScreen screen;
	/**
	 * return to inventory screen.
	 */
	private JButton returnBtn = new JButton("Return");
	
	public ProductListScreen(ArrayList<Product> productList, ArrayList<Customer> customerList){
		this.productList = productList;
		
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
			
		textArea.setFont(baseFont);
		textArea.setBackground(baseColor);
		textArea.setForeground(secondaryColor);
		for(Product v : this.productList){
			textArea.append(String.format("%-15s %10d %n",v.getName() + ":", v.getAvailableUnits()));
		}
		textArea.setEditable(false); //JBradshaw need this or the user can edit the text
		GridBagConstraints ta = new GridBagConstraints();
	    	ta.fill = GridBagConstraints.BOTH;
	    	ta.weightx = 1;
	    	ta.weighty = .16;
	    	ta.gridx = 1;
	    	ta.gridy = 3;
	    	ta.gridwidth = 1;
			
		//returns to the inventory screen.
		returnBtn.setBackground(baseColor);
		returnBtn.setForeground(secondaryColor);
	    returnBtn.setFont(buttonFont);
	    returnBtn.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
	    returnBtn.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						screen = new InventoryScreen(productList, customerList);
					}
					
		});
	    
	    GridBagConstraints rb = new GridBagConstraints();
	    	rb.fill = GridBagConstraints.BOTH;
	    	rb.weightx = 1;
	    	rb.weighty = .16;
	    	rb.gridx = 1;
	    	rb.gridy = 4;
	    	rb.gridwidth = 1;
			
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

		pane.add(returnBtn, rb);
		pane.add(storeFooter, fo);
		pane.add(textArea, ta);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}