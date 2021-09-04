import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener{

	Vector<User> userVector;
	File saveFile;
	
	JPanel mainPanel, titlePanel, formPanel, nameLabelPanel, nameTextFieldPanel, 
	passwordLabelPanel, passwordFieldPanel, buttonPanel, loginButtonPanel,
	registerButtonPanel;
	JLabel loginLabel, nameLabel, passwordLabel;
	JTextField nameTextField;
	JPasswordField passwordField;
	JButton loginButton, registerButton;
	
	public Login(File saveFile, Vector<User> userVector) {
		
//		Populate User Vector
		this.userVector = userVector;
		this.saveFile = saveFile;
		
//		Title Panel
		titlePanel = new JPanel();
		
		loginLabel = new JLabel("Login");
		loginLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
		
		titlePanel.add(loginLabel);
		
//		Form Panel
		formPanel = new JPanel(new GridLayout(2, 2));
		
		nameLabelPanel = new JPanel();
		nameTextFieldPanel = new JPanel();
		
		nameLabel = new JLabel("Name");
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(150, 40));
		
		nameLabelPanel.add(nameLabel);
		nameTextFieldPanel.add(nameTextField);
		
		passwordLabelPanel = new JPanel();
		passwordFieldPanel = new JPanel();
		
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150, 40));
		
		passwordLabelPanel.add(passwordLabel);
		passwordFieldPanel.add(passwordField);
		
		formPanel.add(nameLabelPanel);
		formPanel.add(nameTextFieldPanel);
		formPanel.add(passwordLabelPanel);
		formPanel.add(passwordFieldPanel);
		
//		Button Panel
		buttonPanel = new JPanel(new GridLayout(2, 1));
		loginButtonPanel = new JPanel();
		registerButtonPanel = new JPanel();
		
		loginButton = new JButton("Login");
		registerButton = new JButton("Don't have an account yet? Create now!");
		registerButton.setBorder(null);
		registerButton.setContentAreaFilled(false);
		
		registerButtonPanel.add(registerButton);
		loginButtonPanel.add(loginButton);
		
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
		
		buttonPanel.add(loginButtonPanel);
		buttonPanel.add(registerButtonPanel);
		
		mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
//		Color
		mainPanel.setBackground(Color.decode("#00FFFF"));
		titlePanel.setBackground(Color.decode("#00FFFF"));
		formPanel.setBackground(Color.decode("#00FFFF"));
		nameLabelPanel.setBackground(Color.decode("#00FFFF"));
		nameTextFieldPanel.setBackground(Color.decode("#00FFFF"));
		passwordLabelPanel.setBackground(Color.decode("#00FFFF"));
		passwordFieldPanel.setBackground(Color.decode("#00FFFF"));
		buttonPanel.setBackground(Color.decode("#00FFFF"));
		registerButtonPanel.setBackground(Color.decode("#00FFFF"));
		loginButtonPanel.setBackground(Color.decode("#00FFFF"));
		
		add(mainPanel);
		
//		Frame Settings
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Login");
	}

	@Override
	public void actionPerformed(ActionEvent eventListener) {
		// TODO Auto-generated method stub
		if (eventListener.getSource() == loginButton) {
			String name = nameTextField.getText();
			String password = String.valueOf(passwordField.getPassword());
			
			User user = authenticateUser(name, password);
			if (user != null) {
				
				this.dispose();
				new Home(saveFile, user, userVector);
				
			}
			else {
				JOptionPane.showMessageDialog(this,"Name or password is wrong!","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if (eventListener.getSource() == registerButton) {
			
			this.dispose();
			new Register(saveFile, userVector);
			
		}
	}
	
	User authenticateUser(String name, String password) {
		for (User user : userVector) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}
