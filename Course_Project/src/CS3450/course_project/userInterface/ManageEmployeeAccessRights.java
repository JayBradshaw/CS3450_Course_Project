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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import CS3450.course_project.dataAccess.Employee;
import CS3450.course_project.dataAccess.DatabaseAccess;

/**
 * @author Justin Bradshaw
 *
 */
public class ManageEmployeeAccessRights {
	/**
	 * frame for the GUI
	 */
	private JFrame frame = new JFrame("Grocery Store Checkout System: Set Employee Access Rights");
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
	 * base color of the GUI
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
	 * panel that will hold the button list of employee images
	 */
	private JPanel employeePanel = new JPanel(new FlowLayout());
	/**
	 * text area that helps the user understand how to use the page
	 */
	private JTextArea description = new JTextArea("Select an image to change\nthe employee access rights.\n"
			+ "Or select Return to return\nto the home screen.");
	/**
	 * button to return to the home screen
	 */
	private JButton homeScreen =  new JButton("Return");
	/**
	 * add a new employee
	 */
	private JButton addEmployee = new JButton();
	
	public ManageEmployeeAccessRights(DatabaseAccess databaseConnection){
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
		storeHeader.setBackground(secondaryColor);
		storeHeader.setForeground(baseColor);
		storeHeader.setFont(baseFont);
		//storeHeader.setHorizontalTextPosition(SwingConstants.LEADING);
		storeHeader.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeHeader.setOpaque(true);
		
		//description.setForeground(baseColor);
		//description.setBackground(secondaryColor);
		description.setFont(baseFont);
		description.setEditable(false);
		description.setPreferredSize(new Dimension(260,200));
		description.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, secondaryColor));
		//description.setBorder(BorderFactory.createLineBorder(secondaryColor,5));
		
		//loop through the employee list and make each employee icon a button 
		//add each of these buttons to the JPanel employeePanel
		for (Employee x:databaseConnection.getEmployeeList()){
			//ImageIcon icon = new ImageIcon(x.getImageInfo());
		
			JButton tempButton = new JButton(x.getImage());
			//tempButton.setBackground(baseColor);
			tempButton.setPreferredSize(new Dimension(100,75));
			tempButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					int newRights = 0;
					String rights = JOptionPane.showInputDialog(null,"Previous Access Rights: " + x.getAccessRights() + 
							"\nPlease enter the new access\nrights for this employee (1-4)");
					if (rights == null){
						return; //cancel button hit
					}
					try{
						newRights = Integer.parseInt(rights);
					}catch(NumberFormatException er){
						JOptionPane.showMessageDialog(null,"Error! Invalid value entered!");
						return;
					}
					if (newRights < 1 || newRights > 4){
						JOptionPane.showMessageDialog(null,"Error! Invalid value entered!");
						return;
					}
					//change the access rights of this employee
					x.setAccessRights((short)newRights);
					databaseConnection.setEmployeeAccessRights(x);
				}
				
			});
			employeePanel.add(tempButton);
		}
		
		//make the footer look pretty
		storeFooter.setBackground(secondaryColor);
		storeFooter.setForeground(baseColor);
		storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
		storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
		storeFooter.setOpaque(true);
		
		//button for adding a new user
		ImageIcon tempImage =  new ImageIcon("data/plus.png");
		addEmployee.setIcon(tempImage);
		addEmployee.setPreferredSize(new Dimension(100,75));
		addEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This has not yet been implemented!");
			}
			
		});
		
		employeePanel.add(addEmployee);
		
		//homeScreen.setBackground(secondaryColor);
		homeScreen.setPreferredSize(new Dimension(100,75));
		//homeScreen.setForeground(baseColor);
		homeScreen.setFont(baseFont);
		homeScreen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainScreen screen = new MainScreen(databaseConnection);
			}
			
		});
		
		employeePanel.add(homeScreen);
		//employeePanel.setBackground(secondaryColor);
		
		pane.add(storeHeader, BorderLayout.PAGE_START);
		pane.add(description, BorderLayout.LINE_START);
		pane.add(employeePanel, BorderLayout.CENTER);
		pane.add(storeFooter, BorderLayout.PAGE_END);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
