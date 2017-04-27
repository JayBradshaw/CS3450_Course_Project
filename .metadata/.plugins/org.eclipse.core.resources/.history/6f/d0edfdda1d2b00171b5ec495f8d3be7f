package CS3450.course_project.userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import CS3450.course_project.dataAccess.DatabaseAccess;
import CS3450.course_project.dataAccess.Employee;

public class AddNewEmployee {
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
	 * button to return to the manage employee screen
	 */
	private JButton homeScreen =  new JButton("Return");
	/**
	 * creates the new employee.
	 */
	private JButton create = new JButton("Create");
	/**
	 * spinner object for getting quantity of product 
	 */
	private SpinnerModel model1 = new SpinnerNumberModel();
	/**
	 * spinner to select and cycle through available access rights.
	 */
	private JSpinner accessSpinner = new JSpinner(model1);
	/**
	 * text field to store name, password, image path.
	 */
	private TextField name, password, image = new TextField();
	/**
	 * label for name, spinner, password, image textfields.
	 */
	private JLabel nameLabel, passLabel, imageLabel, spinnerLabel = new JLabel();
	
	private Container inputPanel = new Container();
	
	public AddNewEmployee(DatabaseAccess databaseConnection){
		accessSpinner.setValue(1);
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
				
				
			
			create.setFont(baseFont);
			create.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					String n = name.getText();
					if(n.length()>30){
						JOptionPane.showMessageDialog(frame, "Name is longer than 30 characters");
					}
					
					else{
					// x increments current max id by 1 for next employee.
					int x = databaseConnection.getMaxEmployeeID() + 1;
					// casting from int to short proved to be a troublesome in java, explicit casting using temp1 to access was needed.
					int temp1 = (int)accessSpinner.getValue();
					short access = (short)temp1;
					Employee temp = new Employee(x, name.getText(), cryptWithMD5(password.getText()), access, image.getText());
					databaseConnection.addEmployee(temp);
					
					//return to original screen.
					frame.dispose();
					ManageEmployeeAccessRights screen = new ManageEmployeeAccessRights(databaseConnection);	
				}
				}
				
			});
			
			homeScreen.setFont(baseFont);
			homeScreen.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					ManageEmployeeAccessRights screen = new ManageEmployeeAccessRights(databaseConnection);
				}
				
			});
				
		//layout for storing all input fields and corresponding labels
				inputPanel.setLayout(new GridBagLayout());
				
		name = new TextField("");
		nameLabel = new JLabel("Name");
		password = new TextField("");
		passLabel = new JLabel("Password");
		image = new TextField("data/man_silhouette_clip_art.jpg");
		imageLabel = new JLabel("Image Path");
		
		spinnerLabel = new JLabel("Acess Rights level");
		
		//name textfield location
		GridBagConstraints n = new GridBagConstraints();
		n.gridx = 1;
		n.gridy = 0;
		n.weightx = 1.0; 
		n.weighty = 1.0;
	    n.fill = GridBagConstraints.BOTH;
	    
	    //name label location
	    GridBagConstraints nl = new GridBagConstraints();
	    nl.gridx = 0;
		nl.gridy = 0;
		nl.weightx = .5; 
		nl.weighty = 1.0;
	    nl.fill = GridBagConstraints.BOTH;
	    
	    //password text field location
	    GridBagConstraints p = new GridBagConstraints();
	    p.gridx = 1;
	    p.gridy = 1;
	    p.weightx = 1.0;
	    p.weighty = 1.0;
	    p.fill = GridBagConstraints.BOTH;
	    
	    //password label location
	    GridBagConstraints pl = new GridBagConstraints();
	    pl.gridx = 0;
	    pl.gridy = 1;
	    pl.weightx = .5;
	    pl.weighty = 1.0;
	    pl.fill = GridBagConstraints.BOTH;
	    
	  //image text field location
	    GridBagConstraints i = new GridBagConstraints();
	    i.gridx = 1;
	    i.gridy = 2;
	    i.weightx = 1.0;
	    i.weighty = 1.0;
	    i.fill = GridBagConstraints.BOTH;
	    
	    //image label location
	    GridBagConstraints il = new GridBagConstraints();
	    il.gridx = 0;
	    il.gridy = 2;
	    il.weightx = .5;
	    il.weighty = 1.0;
	    il.fill = GridBagConstraints.BOTH;
	    
	  //spinner location
	    GridBagConstraints s = new GridBagConstraints();
	    s.gridx = 1;
	    s.gridy = 3;
	    s.weightx = .5;
	    s.weighty = 1.0;
	    s.fill = GridBagConstraints.BOTH;
	    
	  //loops the spinner from 0 to max and vice versa.
	    accessSpinner.addChangeListener(new ChangeListener(){
	  			@Override
	  			public void stateChanged(ChangeEvent e){
	  				if((int)accessSpinner.getValue() > 4){
	  					accessSpinner.setValue(1);
	  				}
	  				if((int)accessSpinner.getValue() < 1){
	  					accessSpinner.setValue(4);
	  				}
	  			}
	  		});
	    
	  //spinner label location
	    GridBagConstraints sl = new GridBagConstraints();
	    sl.gridx = 0;
	    sl.gridy = 3;
	    sl.weightx = .5;
	    sl.weighty = 1.0;
	    sl.fill = GridBagConstraints.BOTH;
	    
	  //return button location
	    GridBagConstraints r = new GridBagConstraints();
	    r.gridx = 0;
	    r.gridy = 4;
	    r.weightx = 1.0;
	    r.weighty = 1.0;
	    r.fill = GridBagConstraints.BOTH;
	    
	  //create button location
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 1;
	    c.gridy = 4;
	    c.weightx = 1.0;
	    c.weighty = 1.0;
	    c.fill = GridBagConstraints.BOTH;
	    
	    
	    //adding objects to the temporary layout container.
	    inputPanel.add(nameLabel, nl);
	    inputPanel.add(name, n);
	    inputPanel.add(password, p);
	    inputPanel.add(passLabel, pl);
	    inputPanel.add(image, i);
	    inputPanel.add(imageLabel, il);
	    inputPanel.add(accessSpinner, s);
	    inputPanel.add(spinnerLabel, sl);
	    inputPanel.add(create,c);
	    inputPanel.add(homeScreen, r);
				
		
		//make the footer look pretty
				storeFooter.setBackground(secondaryColor);
				storeFooter.setForeground(baseColor);
				storeFooter.setFont(new Font("Verdana",Font.PLAIN,10));
				storeFooter.setPreferredSize(new Dimension(frame.getWidth(),50));
				storeFooter.setOpaque(true);
				
		

				
				pane.add(storeHeader, BorderLayout.PAGE_START);
				pane.add(inputPanel, BorderLayout.CENTER);
				pane.add(storeFooter, BorderLayout.PAGE_END);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);	
	}
	//borrowed to encrypt the passwords.
	private String cryptWithMD5(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error parsing password!");
        }
            return null;
    }
	
}
