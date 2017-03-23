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
	                    	frame.dispose();
	                    	screen = new MainScreen(databaseConnection);
	                    }
	                    else if (isValidUser(username,password)){
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
		 * creates a dummy product list without accessing the database
		 */
		public void prodListNoDatabase(){
			productList.add(new Product("Apple", 56, 0.99,1568494530, "568 S 400 W Atlanta, Georgia 48934 895-594-8745","Johnny Appleseed"));
			productList.add(new Product("Banana", 86,0.58,1578965445,"896 W 500 N Tallahassee, Florida 45897 458-521-8452","Dole Johnson"));
			productList.add(new Product("Coca-Cola", 36, 0.75, 1897456328, "345 E Bubbly St. Tacoma, Washington 45879 568-895-5142","Kris Kringle"));
			productList.add(new Product("Carrots", 26, 1.25, 1356984571, "978 Greeny Way Santa Fe, New Mexico 54876 528-965-4521", "Jimmy Red"));
			productList.add(new Product("Bread", 14, 2.5, 1589478532, "9855 W Dough Avenue Salt Lake City, Utah 82564 801-896-5846","Chris Pillsbury"));
			productList.add(new Product("Potatoes", 88, 0.69, 1563254896, "341 Big Creek Drive Idaho Falls, Idaho 85412 435-986-8541", "Alicia Spud"));
			productList.add(new Product("Tuna", 43, 0.99, 1458965700, "678 Aquarium Ave. Augusta, Maine 45214 985-785-4587", "Anita Fishman"));
			
		}
		
		/**
		 * creates a dummy order list without accessing the database
		 */
		public void orderListNoDatabase(){
			orderList.add(new Order(0,0,"Cash",12.83,"Pick Up","Apple,0.99,4|Coca-Cola,0.75,2|Bread,2.5,1|Potatoes,0.69,6"));
			orderList.add(new Order(1,1,"Check",11.86,"Pick Up","Tuna,0.99,6|Coca-Cola,0.75,2|Carrots,3,1.25"));
			orderList.add(new Order(2,2,"Card",15.16,"Delivery","Potatoes,0.69,14|Banana,8,0.58"));
			orderList.add(new Order(5,4,"Card",32.27,"Pick Up","Carrots,1.25,6|Potatoes,0.69,26|Bread,2.5,2"));
		}
		
		/**
		 * creates a dummy customer list without accessing the database
		 */
		public void customerListNoDatabase(){
			customerList.add(new Customer(0,"William Banks","856 S Christmas Ave. Charleston, South Carolina 59587"));
			customerList.add(new Customer(1,"Mason Jones", "584 Center St. San Francisco, CA 65860"));		
			customerList.add(new Customer(2,"Harry Plopper","888 Wizard Way, Narsaq, Greenland "));
			customerList.add(new Customer(4,"Jonathan James","123 Wisdom Way Rexburg, Idaho 85621"));
		}
	    
	    /**
	     * sets up the employee info without using the database (for testing)
	     */
	    private void employeeInfoNoDatabase(){
	    	employeeList.add(new Employee(0,"Ignacio S", "spicyTortillas",(short)1,"data/black_curly_hair_man.jpg"));
	    	employeeList.add(new Employee(1,"Andrew F", "blueMonkey45",(short)2,"data/handsome_guy.jpg"));
	    	employeeList.add(new Employee(2,"Anita T", "scaryOctopus67",(short)2,"data/red-hair-woman.png"));
	    	employeeList.add(new Employee(3,"Nicholas S", "wildWombat245",(short)3,"data/greeter1-2.png"));
	    	employeeList.add(new Employee(4,"Barbara B", "whiteHouse08",(short)4,"data/middle-aged-woman-brown-hair.png"));    	
	    }
	    
	    /**
	     * load dummy info without using the database
	     */
	    private void loadInfoNoDatabase(){
	    	prodListNoDatabase();
	    	orderListNoDatabase();
	    	customerListNoDatabase();
	    	employeeInfoNoDatabase();
	    }
	    
	    /**
	     * @param uname
	     * @param pword
	     * @return
	     * 
	     * determines whether or not the info for the login is valid
	     */
	    private boolean isValidUser(String uname, String pword){
	    	for(Employee x:employeeList){
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
