package CS3450.course_project.userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
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

import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.DatabaseAccess;

public class ManageProductScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Manage Existing Products");
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
	 * list of products
	 */
	private InventoryScreen screen;
	/**
	 * array list to store the products
	 */
	private ArrayList<Product> productList;
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
	/**
	 * spinner based on the spinner model
	 */
	private JSpinner spinner = new JSpinner(model1);
	/**
	 * label for the spinner
	 */
	private JLabel spinnerLabel = new JLabel("Quantity");
	/**
	 * return to inventory screen, cancel changes.
	 */
	private JButton cancel = new JButton("Cancel");
	/**
	 * return to inventory screen, finalize changes.
	 */
	private JButton updateBtn = new JButton("Apply Update");
	/**
	 * input fields
	 */
	private TextField iPrice, iBarcode, iProviderInfo, iProviderName;
	/**
	 * labels for the products
	 */
	private JLabel  priceLabel, barcodeLabel, pInfoLabel, pNameLabel;
	/**
	 * Drop down menu for selecting items.
	 */
	private JComboBox dropDownMenu = new JComboBox();
	/**
	 * temporary product that will be set based on the drop down menu
	 */
	private Product temporary;
	
	/**
	 * @param productList
	 * @param customerList
	 * @param orderList
	 * 
	 * non-default constructor
	 */
	public ManageProductScreen(DatabaseAccess databaseConnection){
		productList = databaseConnection.getProductList();
		
		iPrice 			= new TextField("");
		priceLabel = new JLabel("Price");
		iBarcode 		= new TextField("");
		barcodeLabel = new JLabel("Bardcode Number (10 digits beginning with 1)");
		iProviderInfo	= new TextField("");
		pInfoLabel = new JLabel("Provider Information");
		iProviderName	= new TextField("");
		pNameLabel = new JLabel("Provider Name");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 400);
		pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());
		
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
			
			//string for holding object names.
			String[] productNames = new String[productList.size()];
			int i=0;
					for(Product v : productList){
						productNames[i++] = v.getName();
					}
			//drop down menu for selecting item.
			dropDownMenu = new JComboBox(productNames);
			//change to .length-1 in case the list of products is empty
			dropDownMenu.setSelectedIndex(productNames.length - 1);
			
			spinner.setValue(productList.get(dropDownMenu.getSelectedIndex()).getAvailableUnits());
			iPrice.setText("$" + Double.toString(productList.get(dropDownMenu.getSelectedIndex()).getPrice()));
			iBarcode.setText(Long.toString(productList.get(dropDownMenu.getSelectedIndex()).getBarcodeNumber()));
			iProviderInfo.setText(productList.get(dropDownMenu.getSelectedIndex()).getProviderInfo());
			iProviderName.setText(productList.get(dropDownMenu.getSelectedIndex()).getProviderName());
			//JBradshaw added this to make sure that if the user doesn't change
			//the drop down it will still add the item that first appears
			temporary = productList.get(productNames.length-1); 
			//dropDownMenu.setBackground(secondaryColor);
			//dropDownMenu.setForeground(baseColor);
			dropDownMenu.setFont(baseFont);
			dropDownMenu.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					
					JComboBox cb = (JComboBox) e.getSource();
					temporary = productList.get(cb.getSelectedIndex());
					System.out.println("selected: " + (String)cb.getSelectedItem());
					spinner.setValue(temporary.getAvailableUnits());
					iPrice.setText("$" + Double.toString(temporary.getPrice()));
					iBarcode.setText(Long.toString(temporary.getBarcodeNumber()));
					iProviderInfo.setText(temporary.getProviderInfo());
					iProviderName.setText(temporary.getProviderName());

					frame.revalidate();
					
				}
			});
			GridBagConstraints r = new GridBagConstraints();
			r.weightx = .16;
		    r.weighty = .08;
		    r.gridx = 1;
		    r.gridy = 1;
		    r.gridwidth = 2;
		    r.fill = GridBagConstraints.BOTH;

		    //quantity spinner
			GridBagConstraints q = new GridBagConstraints();
				q.weightx = 1;
			    q.weighty = .08;
			    q.gridx = 2;
			    q.gridy = 2;
			    q.gridwidth = 1;
			    q.fill = GridBagConstraints.BOTH;
			//quantity label
			    GridBagConstraints ql = new GridBagConstraints();
				ql.weightx = 1;
			    ql.weighty = .08;
			    ql.gridx = 1;
			    ql.gridy = 2;
			    ql.gridwidth = 1;
			    ql.fill = GridBagConstraints.BOTH;
			    
		    //price textfield
			GridBagConstraints p = new GridBagConstraints();
				p.weightx = 1;
			    p.weighty = .08;
			    p.gridx = 2;
			    p.gridy = 3;
			    p.gridwidth = 2;
			    p.fill = GridBagConstraints.BOTH;
		    //price label
			GridBagConstraints pl = new GridBagConstraints();
				pl.weightx = 1;
			    pl.weighty = .08;
			    pl.gridx = 1;
			    pl.gridy = 3;
			    pl.gridwidth = 2;
			    pl.fill = GridBagConstraints.BOTH;
			    
		    //barcode textfield
			GridBagConstraints b = new GridBagConstraints();
				b.weightx = 1;
			    b.weighty = .08;
			    b.gridx = 2;
			    b.gridy = 4;
			    b.gridwidth = 2;
			    b.fill = GridBagConstraints.BOTH;
		    //barcode label
			GridBagConstraints bl = new GridBagConstraints();
				bl.weightx = 1;
			    bl.weighty = .08;
			    bl.gridx = 1;
			    bl.gridy = 4;
			    bl.gridwidth = 2;
			    bl.fill = GridBagConstraints.BOTH;
			    
		    //provider info textfield
			GridBagConstraints pi = new GridBagConstraints();
				pi.weightx = .16;
			    pi.weighty = .08;
			    pi.gridx = 2;
			    pi.gridy = 5;
			    pi.gridwidth = 2;
			    pi.fill = GridBagConstraints.BOTH;
		    //provider info label
			GridBagConstraints pil = new GridBagConstraints();
				pil.weightx = .16;
			    pil.weighty = .08;
			    pil.gridx = 1;
			    pil.gridy = 5;
			    pil.gridwidth = 2;
			    pil.fill = GridBagConstraints.BOTH;
			    
		    //provider name textfield
			GridBagConstraints pn = new GridBagConstraints();
				pn.weightx = .16;
			    pn.weighty = .08;
			    pn.gridx = 2;
			    pn.gridy = 6;
			    pn.gridwidth = 2;
			    pn.fill = GridBagConstraints.BOTH;
		    //provider name textfield
			GridBagConstraints pnl = new GridBagConstraints();
				pnl.weightx = .16;
			    pnl.weighty = .08;
			    pnl.gridx = 1;
			    pnl.gridy = 6;
			    pnl.gridwidth = 2;
			    pnl.fill = GridBagConstraints.BOTH;
			
			
		//returns to the inventory screen.
		//cancel.setBackground(secondaryColor);
		//cancel.setForeground(baseColor);
	    cancel.setFont(buttonFont);
	    //cancel.setBorder(BorderFactory.createLineBorder(baseColor,5));
	    cancel.addActionListener(
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							
							frame.dispose();
							screen = new InventoryScreen(databaseConnection);
						}
						
			});
		    
		    GridBagConstraints c = new GridBagConstraints();
		    	c.fill = GridBagConstraints.BOTH;
		    	c.weightx = 1;
		    	c.weighty = .16;
		    	c.gridx = 1;
		    	c.gridy = 7;
		    	c.gridwidth = 1;
		    	
		    	
		    	
    	//updateBtn.setBackground(secondaryColor);
    	//updateBtn.setForeground(baseColor);
    	updateBtn.setFont(buttonFont);
    	//updateBtn.setBorder(BorderFactory.createLineBorder(baseColor,5));
    	updateBtn.addActionListener(
						new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								if ((int)spinner.getValue() < 0){
									JOptionPane.showMessageDialog(null, "Error cannot have negative\nquantity for product!");
									return;
								}

								int barcode;
								double price;
								try{
									//getting rid of possible character entries that would break double input.
									String temp = iPrice.getText();
									temp = temp.replace("$", "");
									temp = temp.replace(" ", "");
									price = Double.valueOf(temp);
									}catch(NumberFormatException er){
										System.out.println("input was not a double.");
										JOptionPane.showMessageDialog(frame, "Inputted price was not a double value.",
												"Input Error", JOptionPane.ERROR_MESSAGE);
										iPrice.setText("");
										price = -1;
										return;
									}
								try{
									barcode = Integer.valueOf(iBarcode.getText());
								}catch(NumberFormatException er){
									System.out.println("input was not a integer.");
									JOptionPane.showMessageDialog(frame, "Inputted barcode was not a integer value.",
											"Input Error", JOptionPane.ERROR_MESSAGE);
									iBarcode.setText("");
									barcode = -1;
									return;
								}
								
								//make sure there are no empty fields
								if(!iPrice.getText().isEmpty() && !iBarcode.getText().isEmpty()&& 
										!iProviderInfo.getText().isEmpty()&& !iProviderName.getText().isEmpty()){
									temporary.setAvailableUnits((int)spinner.getValue());
									temporary.setBarcodeNumber(barcode);
									temporary.setPrice(price);
									temporary.setProviderInfo(iProviderInfo.getText());
									temporary.setProviderName(iProviderName.getText());

								}
								else {
									System.out.println("Adding Failed, Incorrect or invalid input entered.");
									JOptionPane.showMessageDialog(null,"Error! One or more\nfields was left empty!");
									return;
								}
								
							//update the product list to reflect the change	
							databaseConnection.updateProductList(temporary);
							
							frame.dispose();
							screen = new InventoryScreen(databaseConnection);
							}
						});
	    GridBagConstraints u = new GridBagConstraints();
    	u.fill = GridBagConstraints.BOTH;
    	u.weightx = 1;
    	u.weighty = .16;
    	u.gridx = 2;
    	u.gridy = 7;
    	u.gridwidth = 1;
    	
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
	    fo.gridy = 8;
	    fo.gridwidth = 3;
	    fo.gridheight = 1;
		fo.anchor = GridBagConstraints.PAGE_END;
		
		
		//add components to container
		pane.add(storeHeader, h);
		pane.add(iPrice, p);
		pane.add(priceLabel, pl);
		pane.add(spinnerLabel, ql);
		pane.add(spinner, q);
		pane.add(iBarcode, b);
		pane.add(iProviderInfo, pi);
		pane.add(iProviderName, pn);
		pane.add(barcodeLabel, bl);
		pane.add(pInfoLabel, pil);
		pane.add(pNameLabel, pnl);
		pane.add(cancel, c);
		pane.add(updateBtn, u);
		pane.add(dropDownMenu, r);
		pane.add(storeFooter, fo);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
