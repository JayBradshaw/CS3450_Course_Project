/**
 * 
 */
package CS3450.course_project.userInterface;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import CS3450.course_project.dataAccess.Customer;
import CS3450.course_project.dataAccess.Employee;
import CS3450.course_project.dataAccess.Order;
import CS3450.course_project.dataAccess.Product;
import CS3450.course_project.dataAccess.databaseAccess;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Justin Bradshaw
 *
 *class that deals with the login screen
 */
public class LoginScreen {
	 	private JFrame frame = new JFrame("Login Screen");
	    private JTextField name;
	    private JPasswordField pword;
	    private JButton login;
	    private ArrayList<Product> productList = new ArrayList<Product>();
	    private ArrayList<Order> orderList = new ArrayList<Order>();
	    private ArrayList<Customer> customerList = new ArrayList<Customer>();
	    private ArrayList<Employee> employeeList = new ArrayList<Employee>();
	    private databaseAccess databaseConnection;
	    private MainScreen screen;

	    public LoginScreen() {
	    	//get the needed info from the database
	    	databaseConnection = new databaseAccess();
	    	//if not using the database get the dummy info
	    	//loadInfoNoDatabase();
	        frame.setSize(300,150);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	        JPanel panel = new JPanel();
	        frame.add(panel);

	        panel.setLayout(null);

	        JLabel userLabel = new JLabel("Username");
	        userLabel.setBounds(10,10,80,25);
	        panel.add(userLabel);

	        name = new JTextField(20);
	        name.setBounds(100,10,160,25);
	        panel.add(name);

	        JLabel passwordLabel = new JLabel("Password");
	        passwordLabel.setBounds(10,40,80,25);
	        panel.add(passwordLabel);


	        pword = new JPasswordField(20);
	        pword.setBounds(100,40,160,25);
	        panel.add(pword);

	        login = new JButton("Login");
	        login.setBounds(180,80,80,25);
	        panel.add(login);

	        login.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	                    //System.out.println(name.getText());
	                    //System.out.println(pword.getPassword());
	                    String username = name.getText();
	                    String password = new String(pword.getPassword());
	                    //easter egg for a master user that has access to everything
	                    if (isMasterUser(username,password)){
	                    	System.out.println("Hello Master");
	                    	databaseConnection.setCurrentEmployee(username);
	                    	frame.dispose();
	                    	screen = new MainScreen(databaseConnection);
	                    }
	                    else if (isValidUser(username,password)){
	                    	databaseConnection.setCurrentEmployee(username);
	                    	System.out.println(databaseConnection.getEmployee().getName());
	                    	frame.dispose();
	                    	screen = new MainScreen(databaseConnection);
	                    }
	                    else {
	                    	JOptionPane.showMessageDialog(null, "Invalid credentials entered!");
	                    	return;
	                    }
	            }
	        });

	        frame.setVisible(true);
	    }

	    
	    /**
	     * @param uname
	     * @param pword
	     * @return
	     * 
	     * determines whether or not the info for the login is valid
	     */
	    private boolean isValidUser(String uname, String pword){
	    	for(Employee x:databaseConnection.getEmployeeList()){
	    		System.out.println("Employee: " + x.getName() + " " + x.getPassword());
	    		if (x.getName().equals(uname) && x.getPassword().equals(pword)){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    /**
	     * @param uname
	     * @param pword
	     * @return
	     * 
	     * hidden
	     */
	    private boolean isMasterUser(String uname, String pword){
	    	return (uname.equals("master") && pword.equals("hello123")); 
	    }

		/**
		 * @param args
		 * 
		 * main method where the program will run
		 */
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run() {
					new LoginScreen();
				}});
		}

}
