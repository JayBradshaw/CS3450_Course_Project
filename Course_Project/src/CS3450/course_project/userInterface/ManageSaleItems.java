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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import CS3450.course_project.dataAccess.SaleItem;
import CS3450.course_project.dataAccess.databaseAccess;

/**
 * @author Justin Bradshaw
 * 
 * This class will deal with managing the sale items.
 * Sale items will be stored in a table and will be potentially editable
 * there will be a top portion that contains a start date and end date search option
 * also need an add button, a remove expired items button, show all items button
 * 
 */
public class ManageSaleItems implements ActionListener{
	/**
	 * frame where the system will be produced
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Main");
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
	 * font specifications for the buttons
	 */
	private Font buttonFont = new Font("Verdana", Font.PLAIN, 16);
	/**
	 * button to add a new sale item
	 */
	private JButton add = new JButton("Add");
	/**
	 * button to remove a sale item
	 */
	private JButton remove = new JButton("Remove");
	/**
	 * button to remove all expired items
	 */
	private JButton removeExpired = new JButton("Remove Expired");
	/**
	 *button to show all sale items 
	 */
	private JButton showAll  = new JButton("Show All");
	/**
	 * shows all items in a given range
	 */
	private JButton showInRange = new JButton("Show Range");
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
	private databaseAccess databaseConnection;
	/**
	 * list of items on sale
	 */
	private ArrayList<SaleItem> saleList = new ArrayList<SaleItem>();
	/**
	 * scroll pane to hold the table
	 */
	private JScrollPane scrollPane;
	/**
	 * return to the home screen
	 */
	private JButton returnHome = new JButton("Return");
	/**
	 * @param databaseConnection
	 * non-default constructor
	 */
	public ManageSaleItems(databaseAccess databaseConnection){
		this.databaseConnection = databaseConnection;
		saleList = databaseConnection.getSaleList();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		pane = frame.getContentPane();
		pane.setLayout(new BorderLayout()); 
		
		storeHeader.setLayout(new BoxLayout(storeHeader, BoxLayout.X_AXIS));
		JLabel icon1Label = new JLabel();
		JLabel textLabel = new JLabel("Mr. Smith's Groceries");
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
		storeHeader.setBackground(baseColor);
		storeHeader.setForeground(secondaryColor);
		storeHeader.setFont(baseFont);
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);
		
		//create a flow layout for the PAGE_START area of the middle panel
		JPanel midHeader = new JPanel(new FlowLayout());
		midHeader.add(dateFormatting);
		midHeader.add(startLabel);
		midHeader.add(startField);
		midHeader.add(endLabel);
		midHeader.add(endField);
		showInRange.setBackground(secondaryColor);
		showInRange.setForeground(baseColor);
		showInRange.addActionListener(this);
		midHeader.add(showInRange);
		middlePanel.add(midHeader,BorderLayout.PAGE_START);
		add.setBackground(secondaryColor);
		add.setForeground(baseColor);
		add.addActionListener(this);
		middleBottom.add(add);
		remove.setBackground(secondaryColor);
		remove.setForeground(baseColor);
		remove.addActionListener(this);
		middleBottom.add(remove);
		removeExpired.setBackground(secondaryColor);
		removeExpired.setForeground(baseColor);
		removeExpired.addActionListener(this);
		middleBottom.add(removeExpired);
		showAll.setBackground(secondaryColor);
		showAll.setForeground(baseColor);
		showAll.addActionListener(this);
		middleBottom.add(showAll);
		returnHome.setBackground(secondaryColor);
		returnHome.setForeground(baseColor);
		returnHome.addActionListener(this);
		middleBottom.add(returnHome);
		
		middlePanel.add(middleBottom, BorderLayout.PAGE_END);
		
		scrollPane = new JScrollPane(createDefaultTable(databaseConnection));
		
		middlePanel.add(scrollPane, BorderLayout.CENTER);

		
		//make the footer look pretty
		storeFooter.setBackground(baseColor);
		storeFooter.setForeground(secondaryColor);
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

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 * 
	 * create a table based on all items within a start and end date
	 */
	private JTable createTable(String startDate, String endDate, databaseAccess databaseConnection){
		String[] columns = {"Product" , "Sale Price" , "Start Date","End Date"};
		return null;
	}
	
	/**
	 * @param databaseConnection
	 * @return
	 * 
	 * create default table that contains all of the items
	 */
	private JTable createDefaultTable(databaseAccess databaseConnection){
		Vector<String> columns = new Vector<String>();
		columns.addElement("Product");
		columns.addElement("Sale Price");
		columns.addElement("Start Date");
		columns.addElement("End Date");
		
		Vector<Vector> data = new Vector<Vector>();
		
		ArrayList<SaleItem> saleList = databaseConnection.getSaleList();
		for (int i = 0; i < saleList.size(); ++i){
			Vector<Object> vect = new Vector<Object>();
			vect.addElement(saleList.get(i).getName());
			vect.addElement(saleList.get(i).getSalePrice());
			vect.addElement(saleList.get(i).getStartDate());
			vect.addElement(saleList.get(i).getEndDate());
			data.addElement(vect);
		}
		JTable table = new JTable(data,columns);
		table.setDefaultEditor(Object.class,null);
		return table;
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
	
	/**
	 * remove expired sale items
	 */
	private void removeExpired(){
		String date = getCurrentDate();
		for (SaleItem x: databaseConnection.getSaleList()){
			if (x.getEndDate().compareTo(date) < 0) { //remove the sale item
				saleList.remove(x);
				databaseConnection.removeSaleItem(x);
			}
		}
	}

	/**
	 * @param prodName
	 * @return
	 * 
	 * get a sale item by its name
	 * will be used to update the sale item list
	 */
	private SaleItem getItemFromName(String prodName){
		for (SaleItem x: databaseConnection.getSaleList()){
			if (x.getName().equals(prodName)) return x;
		}
		return null;
	}

	//deal with all of the buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (e.getSource() == showAll){
			//just call the create default table
			scrollPane = new JScrollPane(createDefaultTable(databaseConnection));
			frame.revalidate();
		}
		else if (e.getSource() == returnHome){
			frame.dispose();
			MainScreen screen = new MainScreen(databaseConnection);
		}
		
	}
}
