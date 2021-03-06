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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.DatabaseAccess;

public class AddNewProductScreen {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Add New Product");
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
	 * spinner object for getting quantity of product 
	 */
	private SpinnerModel model1 = new SpinnerNumberModel();
	/**
	 * spinner associated with the spinner model
	 */
	private JSpinner spinner = new JSpinner(model1);
	/**
	 * label for the spinner
	 */
	private JLabel spinnerLabel = new JLabel("Quantity");
	/**
	 * temporarily holds the object of selected item.
	 */
	private TextField iName, iPrice, iBarcode, iProviderInfo, iProviderName;
	/**
	 * labels for the text fields
	 */
	private JLabel nameLabel, priceLabel, barcodeLabel, pInfoLabel, pNameLabel;
	/**
	 * font for the labels
	 */
	private Font buttonFont = new Font("Verdana", Font.PLAIN, 16);
	/**
	 * inventory screen
	 */
	private InventoryScreen screen;
	/**
	 * return to inventory screen, cancel changes.
	 */
	private JButton cancel = new JButton("Cancel");
	/**
	 * return to inventory screen, finalize changes.
	 */
	private JButton addItem = new JButton("Add Item");
	
	/**
	 * @param productList
	 * @param customerList
	 * @param orderList
	 * 
	 * non-default constructor
	 */
	public AddNewProductScreen(DatabaseAccess databaseConnection){
		productList = databaseConnection.getProductList();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
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
					
					
			iName 			= new TextField("");
			nameLabel = new JLabel("Name");
			iPrice 			= new TextField("");
			priceLabel = new JLabel("Price");
			iBarcode 		= new TextField("");
			barcodeLabel = new JLabel("Bardcode Number (10 digits beginning with 1)");
			iProviderInfo	= new TextField("");
			pInfoLabel = new JLabel("Provider Information");
			iProviderName	= new TextField("");
			pNameLabel = new JLabel("Provider Name");
			
			//name textfield
			GridBagConstraints n = new GridBagConstraints();
				n.weightx = 1;
			    n.weighty = .08;
			    n.gridx = 2;
			    n.gridy = 1;
			    n.gridwidth = 2;
			    n.fill = GridBagConstraints.BOTH;
			//name label
			GridBagConstraints nl = new GridBagConstraints();
				nl.weightx = 1;
			    nl.weighty = .08;
			    nl.gridx = 1;
			    nl.gridy = 1;
			    nl.gridwidth = 2;
			    nl.fill = GridBagConstraints.BOTH;		
			    
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
	    		//if they cancel just return to the main screen
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
		    	
		    	
		    	
    	//addItem.setBackground(secondaryColor);
    	//addItem.setForeground(baseColor);
    	addItem.setFont(buttonFont);
    	//addItem.setBorder(BorderFactory.createLineBorder(baseColor,5));
    	addItem.addActionListener(
						new ActionListener(){
							//JBradshaw return statements added so that user does not have to put in all of the information again
							double price;
							int barcode;
							@Override
							public void actionPerformed(ActionEvent e) {
								if (iPrice.getText().isEmpty() || iBarcode.getText().isEmpty() || iName.getText().isEmpty() || iProviderInfo.getText().isEmpty() || iProviderName.getText().isEmpty()){
									JOptionPane.showMessageDialog(null, "Error! One or more fields are blank!");
									return;
								}
								try{
									price = Double.valueOf(iPrice.getText());
									}catch(NumberFormatException er){
										System.out.println("input was not a double.");
										JOptionPane.showMessageDialog(frame, "Inputted price was not a double value.",
												"Input Error", JOptionPane.ERROR_MESSAGE);
										iPrice.setText("");
										return;
									}
								try{
									barcode = Integer.valueOf(iBarcode.getText());
								}catch(NumberFormatException er){
									System.out.println("input was not a integer.");
									JOptionPane.showMessageDialog(frame, "Inputted barcode was not a integer value.",
											"Input Error", JOptionPane.ERROR_MESSAGE);
									iBarcode.setText("");
									return;
								}
								if ((int) spinner.getValue() < 0){
									JOptionPane.showMessageDialog(null, "Error! Cannot enter a negative\nvalue for quantity!");
									return;
								}
								if (price < 0){
									JOptionPane.showMessageDialog(null, "Error! Price cannot be less than 0!");
									return;
								}

								//add new product to the database
								if (isExistingProduct((String)iName.getText())){
									JOptionPane.showMessageDialog(null,"Error! Product already exists!");
									return;
								}
								databaseConnection.addProductToDatabase(new Product(getNextProdID(),(String)iName.getText(), (int)spinner.getValue(),
										price, Integer.parseInt(iBarcode.getText()),
										iProviderInfo.getText(), iProviderName.getText()));
								
								frame.dispose();
								screen = new InventoryScreen(databaseConnection);
								
							}
							
				});
		    GridBagConstraints ai = new GridBagConstraints();
		    	ai.fill = GridBagConstraints.BOTH;
		    	ai.weightx = 1;
		    	ai.weighty = .16;
		    	ai.gridx = 2;
		    	ai.gridy = 7;
		    	ai.gridwidth = 1;
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
			    fo.gridy = 9;
			    fo.gridwidth = 3;
			    fo.gridheight = 1;
				fo.anchor = GridBagConstraints.PAGE_END;
			
			//add components to container
			pane.add(storeHeader, h);
			pane.add(iName, n);
			pane.add(nameLabel, nl);
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
			pane.add(addItem, ai);
			pane.add(storeFooter, fo);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
	}
	
	private boolean isExistingProduct(String name){
		for (Product x : productList){
			if (name.toUpperCase().equals(x.getName().toUpperCase())){
				return true;
			}
		}
		return true;
	}
	
	private int getNextProdID(){
		int id = 0;
		for (Product p : productList){
			if (p.getID() > id) id = p.getID();
		}
		return id + 1;
	}
	
}
