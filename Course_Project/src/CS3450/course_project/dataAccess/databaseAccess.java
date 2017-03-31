/**
 * 
 */
package CS3450.course_project.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Justin Bradshaw
 *
 *contains all of the necessary methods and data members for accessing the database
 *this includes products, customers, orders, and employees 
 */
public class databaseAccess {
    /**
     * stores the list of products from the database
     */
    private ArrayList<Product> productList = new ArrayList<Product>();
    /**
     * stores the list of orders from the database
     */
    private ArrayList<Order> orderList = new ArrayList<Order>();
    /**
     * stores the list of customers from the database
     */
    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    /**
     * stores the list of employees from the database
     */
    private ArrayList<Employee> employeeList = new ArrayList<Employee>();
    private Employee currentEmployee = null;    
    
    /**
     * default constructor that will initialize all of the lists from the database
     */
    public databaseAccess(){
    	loadInfoFromDatabase(); //load the info from the database and initialize the array lists
    }
    
    /**
	 * creates the product list from the database connection
	 */
	public void createProductList(){
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
			statement = con.prepareStatement("select * from products");
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
				productList.add(new Product(result.getString(1),result.getInt(2),result.getDouble(3),result.getInt(4),result.getString(5),result.getString(6)));
				System.out.println(result.getString(1) + " "  + result.getInt(2) + " "  + result.getDouble(3) + " "  + result.getInt(4) + " "  + result.getString(5) + " "  + result.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * create a list of orders from the database
	 */
	public void createOrderList(){
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
			statement = con.prepareStatement("select * from orders");
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
				orderList.add(new Order(result.getInt(1),result.getInt(2),result.getString(3),result.getDouble(4),result.getString(5),result.getString(6)));
				System.out.println(result.getInt(1)+ " " + result.getInt(2) + " " + result.getString(3) + " " + result.getDouble(4) + " " + result.getString(5) + " " + result.getString(6));
				//System.out.println(orderList.get(orderList.size()-1).getOrderInfo());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * create a list of customers from the database
	 */
	public void createCustomerList(){
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deal with product list
		
		try {
			statement = con.prepareStatement("select * from customers");
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
				customerList.add(new Customer(result.getInt(1),result.getString(2),result.getString(3)));
				System.out.println(result.getInt(1)+ " " + result.getString(2) + " " + result.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				employeeList.add(new Employee(result.getInt(1),result.getString(2),result.getString(3),result.getShort(4),result.getString(5)));
				System.out.println(result.getInt(1)+ " " + result.getString(2) + " " + result.getString(3) + " " + result.getShort(4) + " " + result.getString(5));
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
     * this method will simply call the load functions for each table
     */
    private void loadInfoFromDatabase(){
    	createProductList();
    	createCustomerList();
    	createOrderList();
    	getEmployeeInfo();
    }
    
    //all of the needed methods for accessing a product and adding a new product to the database
    /**
     * @param product
     * 
     * update a product in the product list
     */
    public void updateProductList(Product product){
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query = "update products set name = " + '"' + product.getName() + '"' + ", availableUnits = " + product.getAvailableUnits() + 
				", barcode = " + product.getBarcodeNumber() + ", providerInfo = " + '"' + product.getProviderInfo() + '"' + ", providerName = " +
				'"' + product.getProviderName() + '"' + "\nwhere name = " + '"' + product.getName() + '"' + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must delete and reload the product list because it has changed
		productList.clear();
		createProductList();
    }
    
    public void updateProductQuantity(Product product){
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query = "update products set availableUnits = " + product.getAvailableUnits() + "\nwhere name = " + '"' + product.getName() + '"' + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must delete and reload the product list because it has changed
		productList.clear();
		createProductList();
    }
    
    public void addProductToDatabase(Product product){
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into products (name, availableUnits, price, barcode, providerInfo, providerName) values (" +
		'"' + product.getName() + '"' + "," + product.getAvailableUnits() + ',' + product.getPrice() + ','
		+ product.getBarcodeNumber() + ',' + '"' + product.getProviderInfo() + '"' + ',' + '"' + product.getProviderName() + '"' + ");";
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//add the new product to the array list
		productList.add(product);
    }
    
    /**
     * @return
     * 
     * get the array list of products
     */
    public ArrayList<Product> getProductList(){
    	return this.productList;
    }
    
    //all of the needed methods for accessing a customer and adding a new customer to the database
    
    /**
     * @return
     * 
     * get the array list of customers
     */
    public ArrayList<Customer> getCustomerList() {
    	return this.customerList;
    }
    
    /**
     * @param customer
     * 
     * add a new customer to the database
     */
    public void addNewCustomer (Customer customer) {
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into customers (customerID, name, address) values (" +
		customer.getCustomerID() + ',' + '"' + customer.getName() + '"'
		+ ',' + '"' + customer.getAddress()  + '"' + ");";
		System.out.println(query);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
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
		
		customerList.add(customer);
    }
    
    /**
     * @param id
     * @return
     * 
     * get a customer from their id
     */
    public Customer getCustomerFromID(int id){
    	for (Customer x : customerList){
    		if (x.getCustomerID() == id){
    			return x;
    		}
    	}
    	return null;
    }

    //all of the needed methods for accessing an an order and adding a new order to the database
    /**
     * @return
     * 
     * get the array list of orders
     */
    public ArrayList<Order> getOrderList(){
    	return this.orderList;
    }
    
    /**
     * @param id
     * @return
     * 
     * get a specific order from an order id
     */
    public Order getOrderFromID(int id){
    	for (Order x:orderList){
    		if (x.getOrderID() == id){
    			return x;
    		}
    	}
    	return null;
    }
    
    /**
     * @param order
     * 
     * update the orderList if an order is changed
     */
    public void updateOrderList (Order order){
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo
		String query = "update orders set customerID = " + order.getCustomerID() + 
				", paymentType = " + '"' + order.getPaymentType() + '"' + ", totalCost = " + order.getTotalCost() + ", deliveryMethod = " +
				'"' + order.getDeliveryMethod() + '"' + " , orderInfo = " + '"' + order.getOrderInfo() + '"' + "\nwhere orderID = " + order.getOrderID() + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//clear the order list and reinitialize it
    	orderList.clear();
    	createOrderList();
    }
    
    /**
     * @param order
     * @param info
     * 
     * update just the order info of an order
     */
    public void updateOrderInfo(Order order, String info, double totalCost){
       	Connection con = null;
    		Statement statement = null;
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		try {
    			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		//orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo
    		String query = "update orders set orderInfo = " + '"' + info + '"' + ", totalCost = " + totalCost + "\nwhere orderID = " + order.getOrderID() + ";";
    		System.out.println(query);
    		try {
    			statement = con.createStatement();
    			statement.executeUpdate(query);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		try {
    			con.close();
    			statement.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	//clear the order list and reinitialize it
    	orderList.clear();
    	createOrderList();
    }
    
    /**
     * @param order
     * 
     * delete an order entirely from the order table
     */
    public void deleteOrder(Order order){
       	Connection con = null;
    		Statement statement = null;
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		try {
    			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		//orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo
    		String query = "delete from orders where orderID = " + order.getOrderID() + ";";
    		System.out.println(query);
    		try {
    			statement = con.createStatement();
    			statement.executeUpdate(query);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		try {
    			con.close();
    			statement.close();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	//clear the order list and reinitialize it
    	orderList.clear();
    	createOrderList();
    }
    
    /**
     * @param order
     * 
     * add a new order to the database
     */
    public void addOrderToDatabase (Order order){
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into orders (orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo) values (" +
		order.getOrderID() + "," + order.getCustomerID() + ',' + '"' + order.getPaymentType() + '"'
		+ ',' + order.getTotalCost() + ',' + '"' + order.getDeliveryMethod() + '"' + ',' + '"' + order.getOrderInfo() + '"' + ");";
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//add the order to the orderList
		orderList.add(order);
    }
    
    //all of the needed methods for accessing an employee and adding a new employee to the database
    
    public ArrayList<Employee> getEmployeeList() {
    	return this.employeeList;
    }
    
    public Employee getEmployeeByID(int id){
    	for (Employee x :employeeList){
    		if (id == x.getID()) return x;
    	}
    	return null;
    }
    
    /**
     * @param employee
     * 
     * add a new employee
     */
    public void addEmployee(Employee employee){
    	//add database connection here
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into employees (id, name, password, accessRights, image) values (" +
		employee.getID() + "," + '"' + employee.getName() + '"' + ',' + '"' + employee.getPassword() + '"'
		+ ',' + employee.getAccessRights() + ',' + '"' + employee.getImageInfo() + '"' + ");";
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	employeeList.add(employee);
    }
    
    /**
     * @param employee
     * 
     * update the employee list
     */
    public void updateEmployeeList(Employee employee){
    	//add database connection here
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "update employees set name = " + '"' + employee.getName() + '"' + ',' + 
		"password = " + '"' + employee.getPassword() + '"' + ", accessRights = " + employee.getAccessRights() + 
		", image = " + '"' + employee.getImageInfo() + '"' + "\nwhere id = " + employee.getID() + ";";		
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	//clear the employee list and reinitialize it
    	employeeList.clear();
    	getEmployeeInfo();
    }
    
    /**
     * @param accessRights
     * 
     * set the current employee based on the login
     * don't need to check if invalid employee because that is already done earlier
     * so we know the employee is valid
     * IF I CREATE A PROGRAM CLASS THEN THIS LOGIC SHOULD MOVE
     */
    public void setCurrentEmployee(Employee employee){
    	currentEmployee = employee;
    }
    
    public void setEmployeeAccessRights(Employee employee){
    	//add database connection here
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the query from the database
		String query = "update employees set accessRights = " + employee.getAccessRights() + "\nwhere id = " + employee.getID() + ";";		
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Employee getEmployee(){
    	return this.currentEmployee;
    }

}
