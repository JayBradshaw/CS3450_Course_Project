/**
 * 
 */
package CS3450.course_project.userInterface;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import CS3450.course_project.dataAccess.Employee;
import CS3450.course_project.dataAccess.Order;

import javax.swing.JLabel;
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
	    private ArrayList<Employee> employeeList = new ArrayList<Employee>();

	    public LoginScreen() {
	    	getEmployeeInfo();
	    	//employeeInfoNoDatabase();
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
	                    System.out.println(name.getText());
	                    System.out.println(pword.getPassword());
	                    String username = name.getText();
	                    String password = new String(pword.getPassword());
	                    frame.dispose();
	                    MainScreen screen = new MainScreen();
	            }
	        });

	        frame.setVisible(true);
	    }
	    
	    /**
	     * gets the employee info from the database
	     */
	    private void getEmployeeInfo(){
			Connection con = null;
			PreparedStatement statement = null;
			ResultSet result = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//deal with product list
			
			try {
				statement = con.prepareStatement("select * from employees");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				result = statement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while(result.next()){
					employeeList.add(new Employee(result.getString(1),result.getString(2),result.getShort(3),result.getString(4)));
					System.out.println(result.getString(1)+ " " + result.getString(2) + " " + result.getShort(3) + " " + result.getString(4));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    /**
	     * sets up the employee info without using the database (for testing)
	     */
	    private void employeeInfoNoDatabase(){
	    	employeeList.add(new Employee("Ignacio S","spicyTortillas",(short)1,"data/black_curly_hair_man.jpg"));
	    	employeeList.add(new Employee("Andrew F","blueMonkey45",(short)2,"data/handsome_guy.jpg"));
	    	employeeList.add(new Employee("Anita T", "scaryOctopus67",(short)2,"data/red-hair-woman.png"));
	    	employeeList.add(new Employee("Nicholas S","wildWombat245",(short)3,"data/greeter1-2.png"));
	    	employeeList.add(new Employee("Barbara B","whiteHouse08",(short)4,"data/middle-aged-woman-brown-hair.png"));
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
					//System.out.println("Grocery Store!");
					
					new LoginScreen();
				}});
		}

}
