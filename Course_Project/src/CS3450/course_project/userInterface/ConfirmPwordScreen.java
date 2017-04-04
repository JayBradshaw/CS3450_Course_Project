package CS3450.course_project.userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import CS3450.course_project.dataAccess.Employee;
import CS3450.course_project.dataAccess.databaseAccess;

public class ConfirmPwordScreen {
	private Employee employee;
	/**
 	 * frame for the GUI
 	 */
 	private JFrame frame = new JFrame("Confirm Password Screen");
    /**
     * field for the password
     */
    private JPasswordField pword;
    /**
     * confirm button
     */
    private JButton confirm;
    /**
     * main screen object so that if the correct password is provided the user is taken to the main screen
     */
    private MainScreen screen;
	/**
	 * returns the user to the home screen
	 */
	private JButton cancel = new JButton("Cancel");

    public ConfirmPwordScreen(String correctPword, String name, String icon, databaseAccess databaseConnection) {
        frame.setSize(310,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

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
				MyInfoScreen screen = new MyInfoScreen(databaseConnection);
			};
        
        });
        panel.add(cancel);
        
        confirm = new JButton("Login");
        confirm.setBounds(195,80,80,25);
        panel.add(confirm);

        confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                    String password = new String(pword.getPassword());
                    if (password==correctPword){
                    	frame.dispose();
                    	employee.setName(name);
                    	employee.setPassword(password);
                    	employee.setImage(icon);
                    }
                    else {
                    	
                    	JOptionPane.showMessageDialog(null, "Incorrect password! Unable to save changes.");
                    	return;
                    }
            }
        });
        frame.getRootPane().setDefaultButton(confirm);
        frame.setVisible(true);
    }
 
}
