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

import CS3450.course_project.businessLogic.OrderHelper;

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
    /**
     * know which employee is currently logged in
     */
    private Employee currentEmployee = null;   
    /**
     * store the current list of sale items
     */
    private ArrayList<SaleItem> saleList = new ArrayList<SaleItem>();
    private Customer currentCustomer = null;
    
    /**
     * default constructor that will initialize all of the lists from the database
     */
    public databaseAccess(){
    	loadInfoFromDatabase(); //load the info from the database and initialize the array lists
    }
    
    /**
	 * creates the product list from the database connection
	 */
	private void createProductList(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		//deal with product list
		try {
			statement = con.prepareStatement("select * from products");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				productList.add(new Product(result.getString(1),result.getInt(2),result.getDouble(3),result.getInt(4),result.getString(5),result.getString(6)));
				System.out.println(result.getString(1) + " "  + result.getInt(2) + " "  + result.getDouble(3) + " "  + result.getInt(4) + " "  + result.getString(5) + " "  + result.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * create a list of orders from the database
	 */
	private void createOrderList(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//deal with product list
		
		try {
			statement = con.prepareStatement("select * from orders");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				orderList.add(new Order(result.getInt(1),result.getInt(2),result.getString(3),result.getDouble(4),result.getString(5),result.getString(6),result.getString(7)));
				System.out.println(result.getInt(1)+ " " + result.getInt(2) + " " + result.getString(3) + " " + result.getDouble(4) + " " + result.getString(5) + " " + result.getString(6) + " " + result.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * create a list of customers from the database
	 */
	private void createCustomerList(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//deal with product list
		
		try {
			statement = con.prepareStatement("select * from customers");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				customerList.add(new Customer(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5)));
				System.out.println(result.getInt(1)+ " " + result.getString(2) + " " + result.getString(3) + " " + result.getInt(4) + " " + result.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		try {
			statement = con.prepareStatement("select * from employees");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				employeeList.add(new Employee(result.getInt(1),result.getString(2),result.getString(3),result.getShort(4),result.getString(5)));
				System.out.println(result.getInt(1)+ " " + result.getString(2) + " " + result.getString(3) + " " + result.getShort(4) + " " + result.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * this method will simply call the create functions for each table
     */
    private void loadInfoFromDatabase(){
    	createProductList();
    	createCustomerList();
    	createOrderList();
    	getEmployeeInfo();
    	createSaleList();
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String query = "update products set availableUnits = " + product.getAvailableUnits() + "\nwhere name = " + '"' + product.getName() + '"' + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
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
    
    public void setCurrentCustomer(Customer customer){
    	currentCustomer = customer;
    }
    
    /**
     * @return
     * 
     * gets the sales info for a given product in a given range
     */
    public ArrayList<OrderHelper> getProductInfo(Product product,String startDate, String endDate){
    	//get all of the orders in a given time period
    	//from those orders extract the ones with the correct product name and add them to the list
    	ArrayList<OrderHelper> returnList =  new ArrayList<OrderHelper>();
    	for (Order o : getOrdersInRange(startDate,endDate)){
    		//need to create an order helper list from each order
    		//from this list if the order helper list has the right product, add this to the new order helper list
    		String [] productsOrdered = getIndividualProducts(o.getOrderInfo());
			String [] individualItems;
			//break the product by pieces and add it to the orderHelperList
			for (int i = 0; i < productsOrdered.length; ++i){
				individualItems = createThreeParts(productsOrdered[i]);
				System.out.println(individualItems[0] + " " + individualItems[1] + " " + individualItems[2]);
				if (individualItems[0].equals(product.getName())){ //add it to the list to be returned if the correct product
					returnList.add(new OrderHelper(individualItems[0],Double.parseDouble(individualItems[1]),Integer.parseInt(individualItems[2])));
				}
				
			}
    	}
    	return returnList;
    }
    
    /**
     * get the total quantity sold of a product in a given range
     */
    public int getTotalProductQuantity(Product product,String startDate, String endDate){
    	int totalQuantity = 0;
    	ArrayList<OrderHelper> helpList = getProductInfo(product,startDate,endDate);
    	//once we have the helper list, we need to just get the total cost to deal with
    	for (OrderHelper o : helpList){
    		totalQuantity += o.getQuantity();
    	}
    	return totalQuantity;
    }
    
    /**
     * get the total product revenue for a given product in a given time period
     */
    public double getTotalProductSales(Product product, String startDate, String endDate){
    	double totalSales = 0.0d;
    	ArrayList<OrderHelper> helpList = getProductInfo(product,startDate,endDate);
    	//once we have the helper list, we need to just get the total cost to deal with
    	for (OrderHelper o : helpList){
    		totalSales += o.getQuantity();
    	}
    	return totalSales;
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
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into customers (customerID, name, address, rewardCard, rewardPoints) values (" +
		customer.getCustomerID() + ',' + '"' + customer.getName() + '"'
		+ ',' + '"' + customer.getAddress()  + '"' + "," + customer.getRewardCard() + ',' + customer.getRewardPoints() + ");";
		System.out.println(query);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		customerList.add(customer);
    }
    
    /**
     * @param customer
     * @return
     * 
     * update the total reward points for a customer
     */
    public void updateRewardPoints(Customer customer){
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo
		String query = "update customers set rewardPoints = " + customer.getRewardPoints() + 
				"\nwhere customerID = " + customer.getCustomerID() + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	//clear the order list and reinitialize it
    	customerList.clear();
    	createCustomerList();
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
    
    public Customer getCurrentCustomer(){
    	return currentCustomer;
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo
		String query = "update orders set customerID = " + order.getCustomerID() + 
				", paymentType = " + '"' + order.getPaymentType() + '"' + ", totalCost = " + order.getTotalCost() + ", deliveryMethod = " +
				'"' + order.getDeliveryMethod() + '"' + " , orderInfo = " + '"' + order.getOrderInfo() + '"' + " , orderDate = " + '"' + order.getOrderDate() + '"' + "\nwhere orderID = " + order.getOrderID() + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
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
    			e.printStackTrace();
    		}
    		
    		try {
    			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		//orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo
    		String query = "update orders set orderInfo = " + '"' + info + '"' + ", totalCost = " + totalCost + "\nwhere orderID = " + order.getOrderID() + ";";
    		System.out.println(query);
    		try {
    			statement = con.createStatement();
    			statement.executeUpdate(query);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		
    		try {
    			con.close();
    			statement.close();
    		} catch (SQLException e) {
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
    			e.printStackTrace();
    		}
    		
    		try {
    			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		//orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo
    		String query = "delete from orders where orderID = " + order.getOrderID() + ";";
    		System.out.println(query);
    		try {
    			statement = con.createStatement();
    			statement.executeUpdate(query);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		
    		try {
    			con.close();
    			statement.close();
    		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//get the query from the database
		String query = "insert into orders (orderID, customerID, paymentType, totalCost, deliveryMethod, orderInfo, orderDate) values (" +
		order.getOrderID() + "," + order.getCustomerID() + ',' + '"' + order.getPaymentType() + '"'
		+ ',' + order.getTotalCost() + ',' + '"' + order.getDeliveryMethod() + '"' + ',' + '"' + order.getOrderInfo() + '"' + ',' + '"' + order.getOrderDate() + '"' + ");";
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//add the order to the orderList
		orderList.add(order);
    }
    
    /**
     * @param startDate
     * @param endDate
     * @return
     * 
     * get all the orders within a certain range
     * will be used to get the needed info for a customer in a given range
     * can also be used to get info for products in a given range
     */
    public ArrayList<Order> getOrdersInRange(String startDate, String endDate){
    	if (endDate.compareTo(startDate) < 0) return null; //error if end date is earlier than start date
    	ArrayList<Order> oList = new ArrayList<Order>();
    	for(Order x: orderList){
    		if (x.getOrderDate().compareTo(startDate) >=0 && x.getOrderDate().compareTo(endDate) <=0){
    			oList.add(x);
    		}
    	}
    	return oList;
    }
    
    /**
     * @param cust
     * @param startDate
     * @param endDate
     * @return
     * 
     * get all of the orders by a specific customer in a given range
     */
    public ArrayList<Order> getOrdersByCustomer(Customer cust, String startDate, String endDate){
    	if (endDate.compareTo(startDate) < 0) return null; //error if end date is earlier than start date
    	ArrayList<Order> oList = new ArrayList<Order>();
    	for (Order x: getOrdersInRange(startDate,endDate)){
    		if (x.getCustomerID() == cust.getCustomerID()){
    			oList.add(x);
    		}
    	}
    	return oList;
    }
    
    /**
     * @param cust
     * @param startDate
     * @param endDate
     * @return
     * 
     * this list will be used to display an overview of the total item purchases of a customer in a given period
     * This is only used to get the total quantity of each item that is purchased. A separate method gets the total cost
     */
    public ArrayList<OrderHelper> getHelperListByCustomer(Customer cust, String startDate, String endDate){
    	if (endDate.compareTo(startDate) < 0) return null; //error if end date is earlier than start date
    	//from all of the orders, add them to the helper list. see the returns screen for functions to help with this
    	ArrayList<Order> oList = getOrdersByCustomer(cust,startDate,endDate);
    	ArrayList<OrderHelper> helpList = new ArrayList<OrderHelper>();
    	//iterate through the order list getting the order info and adding it to the order helper list
		//get the individual products
    	for (Order x: oList){
			String [] productsOrdered = getIndividualProducts(x.getOrderInfo());
			String [] individualItems;
			//break the product by pieces and add it to the orderHelperList
			for (int i = 0; i < productsOrdered.length; ++i){
				individualItems = createThreeParts(productsOrdered[i]);
				System.out.println(individualItems[0] + " " + individualItems[1] + " " + individualItems[2]);
				//check if the help list already has this product, if so just increment the total
				//otherwise just increment the quantity in the helper list
				if (contains(individualItems[0],helpList)){ //item already there
					for (OrderHelper o : helpList){
						if (o.getProductName().equals(individualItems[0])){ //increment the quantity of the item
								o.setQuantity(o.getQuantity() + Integer.parseInt(individualItems[2]));
						}
					}
				}
				else { //item not already there add a new item to the list
				helpList.add(new OrderHelper(individualItems[0],Double.parseDouble(individualItems[1]),Integer.parseInt(individualItems[2])));
				}
			}
    	}
    	return helpList;
    }
    
    /**
     * @param startDate
     * @param endDate
     * @return
     * 
     * get the total cost of the orders for a specific customer based on a range 
     * this will be used with the get helper list by customer so that you can know the total cost of the orders placed by that customer in a time period
     */
    public double getTotalCost(Customer cust, String startDate, String endDate){
    	ArrayList<Order> oList = getOrdersByCustomer(cust,startDate,endDate); //get all customers within a specific date
    	//loop through the entire list and get the total cost. Return it.
    	double totalCost = 0.0d;
    	for (Order o: oList){
    		totalCost += o.getTotalCost();
    	}
    	return totalCost;
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//get the query from the database
		String query = "update employees set accessRights = " + employee.getAccessRights() + "\nwhere id = " + employee.getID() + ";";		
		System.out.println(query);
		
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * @return
     * 
     * returns the employee that is currently logged in
     */
    public Employee getEmployee(){
    	return this.currentEmployee;
    }

    /**
     * @return
     * 
     * get the list of sale items
     */
    public ArrayList<SaleItem> getSaleList(){
    	return saleList;
    }
    
    /**
     * @param product
     * @return
     * 
     * get the sale price of a particular product. Returns 0 if that product is not on sale
     */
    public double getSalePrice(String product){
    	for (SaleItem x : saleList){
    		if (x.getName().equals(product)) return x.getSalePrice();
    	}
    	return 0.0;
    }
    
    /**
     * @param product
     * @return
     * 
     * returns true if an item is on sale, false otherwise
     */
    public boolean isSaleItem(String product){
    	for (SaleItem x : saleList){
    		if (x.getName().equals(product)) return true;
    	}
    	return false;
    }
    
    /**
     * create the sale list from the database
     */
    public void createSaleList(){
    	//create the connection to the database to get all items that are on sale
    	Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/groceryStore","root","");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		//deal with product list
		try {
			statement = con.prepareStatement("select * from saleitems");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(result.next()){
				saleList.add(new SaleItem(result.getInt(1),result.getString(2),result.getDouble(3),result.getString(4),result.getString(5)));
				System.out.println(result.getInt(1) + " "  + result.getString(2) + " "  + result.getDouble(3) + " "  + result.getString(4) + " "  + result.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * @param item
     * 
     * update the sale list based on a specific item
     */
    public void updateSaleList(SaleItem item){
    	//update the sale list based on a given item
    	Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerystore","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String query = "update saleitems set name = " + '"' + item.getName() + '"' + ", salePrice = " + item.getSalePrice() + 
				", startDate = " + '"' + item.getStartDate() + '"' +  ", endDate = " + '"' + item.getEndDate() + '"' 
				+ "\nwhere id = " + item.getID() + ";";
		System.out.println(query);
		try {
			statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	saleList.clear();
    	createSaleList();
    }
    
    /**
     * @param item
     * 
     * add a new item to the sale list
     */
    public void addNewSaleItem(SaleItem item){
    	saleList.add(item);
    }
    
    //these next two will be used to create an orderHelperList from an order if you want to return anything
    /**
     * @param toDivide
     * @return
     * 
     * break up the individual order item into its three parts
     */
    private String [] createThreeParts(String toDivide){
        String delims = "['^']+";
        String [] toReturn = toDivide.split(delims);
        return toReturn;
    }

    /**
     * @param toDivide
     * @return
     * 
     * get individual products from the orderInfo
     */
    private String [] getIndividualProducts(String toDivide){
        String delims = "[|]+";
        String [] toReturn = toDivide.split(delims);
        return toReturn;
    }
	
    private boolean contains(String item, ArrayList<OrderHelper> list){
    	for (OrderHelper x : list){
    		if (x.getProductName().equals(item))return true;
    	}
    	return false;
    }
}
