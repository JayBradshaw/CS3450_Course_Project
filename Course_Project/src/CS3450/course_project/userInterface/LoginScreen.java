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
	 	/**
	 	 * frame for the GUI
	 	 */
	 	private JFrame frame = new JFrame("Login Screen");
	    /**
	     * field for the username
	     */
	    private JTextField name;
	    /**
	     * field for the password
	     */
	    private JPasswordField pword;
	    /**
	     * login button
	     */
	    private JButton login;
	    /**
	     * main screen object so that if the correct info is provided the user is taken to the main screen
	     */
	    private MainScreen screen;
		/**
		 * stores how many incorrect passwords the user has entered
		 */
		private int invalidPasswordCount = 0;
		/**
		 * returns the user to the home screen
		 */
		private JButton cancel = new JButton("Cancel");

	    public LoginScreen(databaseAccess databaseConnection) {
	        frame.setSize(310,150);
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
	        name.setText(databaseConnection.getEmployee().getName());
	        name.setEditable(false);
	        panel.add(name);

	        JLabel passwordLabel = new JLabel("Password");
	        passwordLabel.setBounds(10,40,80,25);
	        panel.add(passwordLabel);

	        pword = new JPasswordField(20);
	        pword.setBounds(100,40,160,25);
	        panel.add(pword);

	        cancel.setBounds(15,80,80,25);
	        cancel.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					startUpScreen screen = new startUpScreen();
				};
	        
	        });
	        panel.add(cancel);
	        
	        login = new JButton("Login");
	        login.setBounds(195,80,80,25);
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
	                    	//databaseConnection.setCurrentEmployee(new Employee(23,"master","hello123",(short)1,"data/businessman.jpg"));
	                    	frame.dispose();
	                    	screen = new MainScreen(databaseConnection);
	                    }
	                    else if (isValidUser(username,password, databaseConnection.getEmployeeList())){
	                    	System.out.println(databaseConnection.getEmployee().getName());
	                    	frame.dispose();
	                    	screen = new MainScreen(databaseConnection);
	                    }
	                    else {
	                    	invalidPasswordCount++;
	                    	if (invalidPasswordCount >=  3){
	                    		JOptionPane.showMessageDialog(null, "Invalid credentials entered three\n times! Exiting program...");
	                    		System.exit(0);
	                    	}
	                    	else {
	                    		JOptionPane.showMessageDialog(null, "Invalid credentials entered!");
	                    	}
	                    	return;
	                    }
	            }
	        });
	        frame.getRootPane().setDefaultButton(login);
	        frame.setVisible(true);
	    }
	    
	    /**
	     * @param uname
	     * @param pword
	     * @return
	     * 
	     * determines whether or not the info for the login is valid
	     */
	    private boolean isValidUser(String uname, String pword, ArrayList<Employee> employeeList){
	    	for(Employee x: employeeList){
	    		//System.out.println("Employee: " + x.getName() + " " + x.getPassword());
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


}
