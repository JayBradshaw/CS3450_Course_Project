/**
 * 
 */
package CS3450.course_project.userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CS3450.course_project.dataAccess.OrderHelper;
import CS3450.course_project.businessLogic.ProductsInRange;
import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.DatabaseAccess;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;

/**
 * @author Justin Bradshaw
 * 
 * this class will deal with viewing revenue reports by customer or by product
 *
 */
public class RevenueReports implements ActionListener {
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Manage Sale Items");
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
	 * panel for the middle of the screen
	 */
	private JPanel middlePanel = new JPanel(new BorderLayout());
	/**
	 * middle bottom button panel
	 */
	private JPanel middleBottom = new JPanel(new FlowLayout());
	/**
	 * connection to the database class
	 */
	private DatabaseAccess databaseConnection;
	/**
	 * panel for middle header than contains start and end date
	 */
	private JPanel midHeader = new JPanel(new FlowLayout());
	/**
	 * inform the user of the date formatting requirements
	 */
	private JLabel dateFormatting = new JLabel("(YYYY/MM/DD)");
	/**
	 * label for start date
	 */
	private JLabel startLabel = new JLabel("Start Date:");
	/**
	 * label for end date
	 */
	private JLabel endLabel = new JLabel("End Date:");
	/**
	 * text field for start date
	 */
	private JTextField startField = new JTextField(15);
	/**
	 * text field for end date
	 */
	private JTextField endField = new JTextField(15);
	/**
	 * return to the home screen
	 */
	private JButton returnHome = new JButton("Return");
	/**
	 * get a report of a specific product
	 */
	private JButton	productReport = new JButton("Product Report");
	/**
	 * get a report for a specific customer
	 */
	private JButton customerReport = new JButton("Customer Report");
	/**
	 * get a report of all sales in a given time period
	 */
	private JButton allSales = new JButton("All Sales");
	/**
	 * table to store the revenue from products in range
	 */
	private JTable table;
	/**
	 * scroll pane to hold the table
	 */
	private JScrollPane scrollPane;
	/**
	 * @param databaseConnection
	 * constructor
	 */
	public RevenueReports(DatabaseAccess databaseConnection){
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
		
		initializeMiddlePanel();
		
		//make the footer look pretty
		storeFooter.setBackground(secondaryColor);
		storeFooter.setForeground(baseColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);
		
		//add components to container
		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(middlePanel, BorderLayout.CENTER);
		pane.add(storeFooter,BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void initializeMiddlePanel(){
		midHeader.add(dateFormatting);
		midHeader.add(startLabel);
		midHeader.add(startField);
		midHeader.add(endLabel);
		midHeader.add(endField);
		midHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, secondaryColor));
		middlePanel.add(midHeader,BorderLayout.PAGE_START);
		productReport.addActionListener(this);
		middleBottom.add(productReport);
		allSales.addActionListener(this);
		middleBottom.add(allSales);
		customerReport.addActionListener(this);
		middleBottom.add(customerReport);
		returnHome.addActionListener(this);
		middleBottom.add(returnHome);
		middleBottom.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, secondaryColor));
		middlePanel.add(middleBottom, BorderLayout.PAGE_END);
	}
	
	/**
	 * @return
	 * 
	 * get the start and end date from the user
	 */
	private ArrayList<String> getStartEndDate(){
		ArrayList<String> dates = new ArrayList<String>();
		dates.add(startField.getText());
		dates.add(endField.getText());
		return dates;
	}
	
	/**
	 * @param id
	 * @return
	 * ensure the user enters a valid product id
	 */
	private boolean validProdID(int id){
		for (Product p : databaseConnection.getProductList()){
			if (p.getID() == id) return true;
		}
		return false;
	}
	
	/**
	 * @param id
	 * @return
	 * 
	 * get a product name from its id
	 */
	private String getProdNameFromID(int id){
		for (Product p : databaseConnection.getProductList()){
			if (p.getID() == id) return p.getName();
		}
		return "";
	}
	
	/**
	 * @param id
	 * @return
	 * 
	 * make sure the user gave a valid customer id
	 */
	private boolean validCustID(int id){
		for (Customer c : databaseConnection.getCustomerList()){
			if (c.getCustomerID() == id)return true;
		}
		return false;
	}
	
	/**
	 * @param id
	 * @param prodList
	 * @return
	 * 
	 * know whether or not a product is already in the helper list
	 */
	private int inProdHelper(int id, ArrayList<ProductsInRange> prodList){
		int index = 0;
		for (ProductsInRange p : prodList){
			if (p.getProductID() == id){
				return index;
			}
			else {
				++index;
			}
		}
		return -1;
	}
	
	/**
	 * @param prodList
	 * @return
	 * 
	 * get the total revenue for all products in a given range
	 */
	private double getTotalRevenue(ArrayList<ProductsInRange> prodList){
		double totalRevenue = 0.0d;
		for (ProductsInRange p : prodList){
			totalRevenue += p.getTotalCost();
		}
		totalRevenue += totalRevenue*.06; //add in tax
		return totalRevenue;
	}
	
	/**
	 * @param databaseConnection
	 * @return
	 * 
	 * create default table that contains all of the items
	 */
	private void createRevenueTable(ArrayList<ProductsInRange> prodList, double totalRevenue){
		Vector<String> columns = new Vector<String>();
		columns.addElement("Product");
		columns.addElement("Quantity");
		columns.addElement("Revenue");
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		for (ProductsInRange p : prodList){
			Vector<Object> vect = new Vector<Object>();
			vect.addElement(getProdNameFromID(p.getProductID()));
			vect.addElement(p.getQuantity());
			vect.addElement(String.format("$ %.2f", p.getTotalCost()));
			data.addElement(vect);
		}
		//empty row
		Vector<Object> vect = new Vector<Object>();
		vect.addElement("");
		vect.addElement("");
		vect.addElement("");
		data.addElement(vect);
		//add one more row to show total revenue
		Vector<Object> vect1 = new Vector<Object>();
		vect1.addElement("");
		vect1.addElement("");
		vect1.addElement(String.format("Total Revenue: $ %.2f", totalRevenue));
		data.addElement(vect1);
		
		table = new JTable(data,columns);
		table.setDefaultEditor(Object.class,null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == returnHome){
			frame.dispose();
			MainScreen screen = new MainScreen(databaseConnection);
		}
		else if (e.getSource() == productReport){
			if (startField.getText().isEmpty() || endField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter a valid start date and end date.");
				return;
			}
			//get the product id
			String prodID;
			prodID = JOptionPane.showInputDialog(null,"Enter the product ID");
			if (prodID == null) return;
			if (!validProdID(Integer.parseInt(prodID))){
				JOptionPane.showMessageDialog(null, "Invalid ID entered!");
				return;
			}
			int quantity = 0;
			double totalRevenue = 0.0d;
			//get all orders in the range
			ArrayList<String> dates = getStartEndDate();
			//loop through all the order helper items in he range
			System.out.println(dates.get(0) + " " + dates.get(1));
			ArrayList<OrderHelper> orderHelper = databaseConnection.getHelperItemsInRange(dates.get(0), dates.get(1));
			for (OrderHelper x : orderHelper){
				if (x.getProductID() == Integer.parseInt(prodID)){
					quantity += x.getQuantity();
					totalRevenue += x.getProductPrice() * x.getQuantity();
				}
			}
			//get all order helper lists in the range
			//add up the quantity for the product and the total cost
			//print out the info
			//add in tax to the total revenue
			totalRevenue += totalRevenue *.06;
			String report = "";

			report = "\nSales report for " + getProdNameFromID(Integer.parseInt(prodID)) + 
					"\n" + dates.get(0) + " - " + dates.get(1) + "\nQuantity Sold: " + 
					quantity + "\nTotal Revenue: $" + String.format("%.2f",totalRevenue);
			
			JTextArea area = new JTextArea(report);
			area.setFont(baseFont);
			middlePanel.removeAll();
			middlePanel.add(midHeader, BorderLayout.PAGE_START);
			middlePanel.add(middleBottom, BorderLayout.PAGE_END);
			middlePanel.add(area,BorderLayout.CENTER);
			frame.revalidate();
		}
		else if (e.getSource() == allSales){
			if (startField.getText().isEmpty() || endField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter a valid start date and end date.");
				return;
			}
			ArrayList<String> dates = getStartEndDate();
			//create a class in the business logic to deal with this
			//has ID, quantity, totalRevenue
			//call the class ProductsInRange?
			//figure out all of the products in the range, the cost, total it up
			ArrayList<OrderHelper> orderHelper = databaseConnection.getHelperItemsInRange(dates.get(0), dates.get(1));
			ArrayList<ProductsInRange> prodList = new ArrayList<ProductsInRange>();
			//get all the order helper objects in the range
			double totalCost = 0.0d;
			for (OrderHelper x : orderHelper){
				totalCost = x.getQuantity() * x.getProductPrice();
				int index = inProdHelper(x.getProductID(),prodList);
				if (index == -1){ //not already in list
					prodList.add(new ProductsInRange(x.getProductID(),x.getQuantity(),totalCost));
				}
				else { //update list
					prodList.get(index).setQuantity(prodList.get(index).getQuantity() + x.getQuantity());
					prodList.get(index).setTotalCost(prodList.get(index).getTotalCost() + totalCost);
				}
			}
			//if not separate product (same id), just add to the quantity and total cost
			//print out the info in a table
			//product, quantity, totalrevenue
			createRevenueTable(prodList,getTotalRevenue(prodList));
			scrollPane = new JScrollPane(table);
			middlePanel.removeAll();
			middlePanel.add(midHeader, BorderLayout.PAGE_START);
			middlePanel.add(middleBottom, BorderLayout.PAGE_END);
			middlePanel.add(scrollPane, BorderLayout.CENTER);
			frame.revalidate();
		}
		else if (e.getSource() == customerReport){
			if (startField.getText().isEmpty() || endField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter a valid start date and end date.");
				return;
			}
			String cusID;
			cusID = JOptionPane.showInputDialog(null,"Enter the customer ID");
			if (cusID == null) return;
			if (!validCustID(Integer.parseInt(cusID))){
				JOptionPane.showMessageDialog(null, "Invalid ID entered!");
				return;
			}
			//get start and end dates
			//get order list
			ArrayList<String> dates = getStartEndDate();
			ArrayList<Order> orders = databaseConnection.getOrdersByCustomer(databaseConnection.getCustomerFromID(Integer.parseInt(cusID)), dates.get(0), dates.get(1));
			//get the report for this customer
			double totalSales = 0.0d;
			for (Order x : orders){
				totalSales += x.getTotalCost();
			}
			String report = "";
			if (orders.isEmpty()){
				report = "\nNo orders to show for customer " + databaseConnection.getCustomerFromID(Integer.parseInt(cusID)).getName() + 
						"\nIn the time period " + dates.get(0) + " - " + dates.get(1);
			}
			else {
				report = "\nSales report for " + databaseConnection.getCustomerFromID(Integer.parseInt(cusID)).getName() + "\n" +
					dates.get(0) + " - " + dates.get(1) + "\nTotal Orders: " + orders.size() + "\nTotal Revenue: $" + String.format("%.2f", totalSales);
			}
			JTextArea area = new JTextArea(report);
			area.setFont(baseFont);
			middlePanel.removeAll();
			middlePanel.add(midHeader, BorderLayout.PAGE_START);
			middlePanel.add(middleBottom,BorderLayout.PAGE_END);
			middlePanel.add(area,BorderLayout.CENTER);
			frame.revalidate();
		}
	}
}
