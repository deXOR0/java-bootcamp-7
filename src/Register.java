import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame implements ActionListener{

	Vector<User> userVector;
	File saveFile;
	JPanel mainPanel, titlePanel, formPanel, nameLabelPanel, nameTextFieldPanel, 
	addressLabelPanel, addressTextFieldPanel, genderLabelPanel, genderInputPanel,
	genderOptionPanel, ageLabelPanel, ageComboBoxPanel, passwordLabelPanel, 
	passwordFieldPanel, repeatPasswordLabelPanel, repeatPasswordFieldPanel,
	registerButtonPanel, loginButtonPanel, buttonPanel;
	JLabel registerLabel, nameLabel, addressLabel, genderLabel, ageLabel, passwordLabel, 
	repeatPasswordLabel;
	JTextField nameTextField, addressTextField;
	JPasswordField passwordField, repeatPasswordField;
	JRadioButton maleRadioButton, femaleRadioButton;
	ButtonGroup genderButtonGroup;
	JComboBox<Integer> ageComboBox;
	JButton registerButton, loginButton;
	DatabaseConnection db;
	
	public void saveFile() {
//		Save ke file
//			1. Ubah Vector jadi string
//			2. Tulis string nya ke file
		String content = "";
		String userTemplate = "%s,%s,%s,%d,%s\n";
		
		for (User thisUser : userVector) {
			String newUser = String.format(userTemplate, thisUser.getName(), 
					thisUser.getAddress(), thisUser.getGender(), thisUser.getAge(), 
					thisUser.getPassword());
			content += newUser;
		}
		
		try {
			FileWriter writer = new FileWriter(saveFile);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Register(File saveFile ,Vector<User> userVector) {
		// TODO Auto-generated constructor stub
		
//		Populate User Vector
		
		this.userVector = userVector;
		this.saveFile = saveFile;
		
		db = new DatabaseConnection();
		
//		Main Panel
		mainPanel = new JPanel(new BorderLayout());
		
//		Title Panel
		titlePanel = new JPanel();
		
		registerLabel = new JLabel("Register");
		registerLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
		
		titlePanel.add(registerLabel);
		
//		Form Panel
		formPanel = new JPanel(new GridLayout(6, 2));
		
		nameLabelPanel = new JPanel();
		nameTextFieldPanel = new JPanel();
		
		nameLabel = new JLabel("Name");
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(150, 40));
		
		nameLabelPanel.add(nameLabel);
		nameTextFieldPanel.add(nameTextField);
		
		addressLabelPanel = new JPanel();
		addressTextFieldPanel = new JPanel();
		
		addressLabel = new JLabel("Address");
		addressTextField = new JTextField();
		addressTextField.setPreferredSize(new Dimension(150, 40));
		
		addressLabelPanel.add(addressLabel);
		addressTextFieldPanel.add(addressTextField);
		
		genderLabelPanel = new JPanel();
		genderInputPanel = new JPanel();
		
		genderLabel = new JLabel("Gender");
		genderOptionPanel = new JPanel(new GridLayout(1, 2));
		genderButtonGroup = new ButtonGroup();
		maleRadioButton = new JRadioButton("Male");
		femaleRadioButton = new JRadioButton("Female");
		maleRadioButton.setSelected(true);
		
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		genderOptionPanel.add(maleRadioButton);
		genderOptionPanel.add(femaleRadioButton);
		
		genderLabelPanel.add(genderLabel);
		genderInputPanel.add(genderOptionPanel);
		
		ageLabelPanel = new JPanel();
		ageComboBoxPanel = new JPanel();
		
		ageLabel = new JLabel("Age");
		Integer[] ageArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		ageComboBox = new JComboBox<Integer>(ageArray);
		ageComboBox.setPreferredSize(new Dimension(150, 40));
		
		ageLabelPanel.add(ageLabel);
		ageComboBoxPanel.add(ageComboBox);
		
		passwordLabelPanel = new JPanel();
		passwordFieldPanel = new JPanel();
		
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150, 40));
		
		passwordLabelPanel.add(passwordLabel);
		passwordFieldPanel.add(passwordField);
		
		repeatPasswordLabelPanel = new JPanel();
		repeatPasswordFieldPanel = new JPanel();
		
		repeatPasswordLabel = new JLabel("Repeat Password");
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setPreferredSize(new Dimension(150, 40));
		
		repeatPasswordLabelPanel.add(repeatPasswordLabel);
		repeatPasswordFieldPanel.add(repeatPasswordField);
		
		formPanel.add(nameLabelPanel);
		formPanel.add(nameTextFieldPanel);
		formPanel.add(addressLabelPanel);
		formPanel.add(addressTextFieldPanel);
		formPanel.add(genderLabelPanel);
		formPanel.add(genderInputPanel);
		formPanel.add(ageLabelPanel);
		formPanel.add(ageComboBoxPanel);
		formPanel.add(passwordLabelPanel);
		formPanel.add(passwordFieldPanel);
		formPanel.add(repeatPasswordLabelPanel);
		formPanel.add(repeatPasswordFieldPanel);
		
		
//		Button Panel
		buttonPanel = new JPanel(new GridLayout(2, 1));
		registerButtonPanel = new JPanel();
		loginButtonPanel = new JPanel();
		
		registerButton = new JButton("Register");
		loginButton = new JButton("Already have an account? Login now!");
		loginButton.setBorder(null);
		loginButton.setContentAreaFilled(false);
		
		registerButtonPanel.add(registerButton);
		loginButtonPanel.add(loginButton);
		
		registerButton.addActionListener(this);
		loginButton.addActionListener(this);
		
		buttonPanel.add(registerButtonPanel);
		buttonPanel.add(loginButtonPanel);
		
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
//		Color
		mainPanel.setBackground(Color.decode("#00FFFF"));
		titlePanel.setBackground(Color.decode("#00FFFF"));
		formPanel.setBackground(Color.decode("#00FFFF"));
		nameLabelPanel.setBackground(Color.decode("#00FFFF"));
		nameTextFieldPanel.setBackground(Color.decode("#00FFFF"));
		addressLabelPanel.setBackground(Color.decode("#00FFFF"));
		addressTextFieldPanel.setBackground(Color.decode("#00FFFF"));
		genderLabelPanel.setBackground(Color.decode("#00FFFF"));
		genderInputPanel.setBackground(Color.decode("#00FFFF"));
		genderOptionPanel.setBackground(Color.decode("#00FFFF"));
		maleRadioButton.setBackground(Color.decode("#00FFFF"));
		femaleRadioButton.setBackground(Color.decode("#00FFFF"));
		ageLabelPanel.setBackground(Color.decode("#00FFFF"));
		ageComboBoxPanel.setBackground(Color.decode("#00FFFF"));
		passwordLabelPanel.setBackground(Color.decode("#00FFFF"));
		passwordFieldPanel.setBackground(Color.decode("#00FFFF"));
		repeatPasswordLabelPanel.setBackground(Color.decode("#00FFFF"));
		repeatPasswordFieldPanel.setBackground(Color.decode("#00FFFF"));
		buttonPanel.setBackground(Color.decode("#00FFFF"));
		registerButtonPanel.setBackground(Color.decode("#00FFFF"));
		loginButtonPanel.setBackground(Color.decode("#00FFFF"));
		
		
		add(mainPanel);
		
//		Frame Settings
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(450, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Register");
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		if (actionEvent.getSource() == registerButton) {
			
//			Create new user
			String name = nameTextField.getText();
			String address = addressTextField.getText();
			String gender = (maleRadioButton.isSelected()) ? "Male" : "Female"; // Ternary
			Integer age = (Integer) ageComboBox.getSelectedItem();
			String password = String.valueOf(passwordField.getPassword());
			String repeatPassword = String.valueOf(repeatPasswordField.getPassword());
			
			if (validInput(name, address, gender, age, password, repeatPassword)) {				
				User newUser = new User(name, address, gender, age, password);
				
				userVector.add(newUser);
//				saveFile();
				db.insertNewUser(name, address, gender, age, password);
				JOptionPane.showMessageDialog(this,String.format("%s is registered succesfully!", name),"User Registered!",JOptionPane.INFORMATION_MESSAGE);
				
				this.dispose();
				new Login(saveFile, userVector);
			}
			
		}
		else if (actionEvent.getSource() == loginButton) {
			
			this.dispose();
			new Login(saveFile, userVector);
			
		}
		
	}
	
	public boolean validInput(String name, String address, String gender, Integer age, String password, String repeatPassword) {
		if (!name.isEmpty()) {
			if (!address.isEmpty()) {
				if (age > 0) {
					if (!password.isEmpty()) {
						if (validPassword(password)) {
							if (password.equals(repeatPassword)) {
								return true;
							}
							else {
								JOptionPane.showMessageDialog(this,"Password does not match!","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(this,"Password must be at least 8 characters and alphanumeric!","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(this,"Password must be filled!","Error",JOptionPane.ERROR_MESSAGE);  
					}
				}
				else {
					JOptionPane.showMessageDialog(this,"Password must be filled!","Error",JOptionPane.ERROR_MESSAGE);
					System.out.println("Age is invalid!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this,"Address must be filled!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(this,"Name must be filled!","Error",JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	public boolean validPassword(String password) {
		if (password.length() >= 8) {
			boolean hasAlpha = false, hasDigit = false;
			for (char c : password.toCharArray()) {
				if (!hasAlpha && Character.isAlphabetic(c)) {
					hasAlpha = true;
				}
				else if (!hasDigit && Character.isDigit(c)) {
					hasDigit = true;
				}
				if (hasAlpha && hasDigit) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void printUserVector() {
		for (User user : userVector) {
			System.out.println(user.getName());
			System.out.println(user.getAddress());
			System.out.println(user.getGender());
			System.out.println(user.getAge());
			System.out.println(user.getPassword());
			System.out.println("==============================");
		}
	}

}
