import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

public class Home extends JFrame implements ActionListener, MenuListener{
	
	Vector<User> userVector;
	File saveFile;
	User user;
	JPanel mainPanel, userDataPanel, userTablePanel;
	JLabel nameLabel, addressLabel, genderLabel, ageLabel;
	JButton backButton;
	JMenuBar homeMenuBar;
	JMenu profileMenu;
	JMenuItem logoutMenuItem;
	JTable userTable;
	JScrollPane userTableScrollPane;
	
	void initTableData() {
		Vector<Object> column = new Vector<>();
		column.add("Name");
		column.add("Address");
		column.add("Gender");
		column.add("Age");
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		for (User thisUser : userVector) {
			if (thisUser != user) {
				Vector<Object> row = new Vector<>();
				
				row.add(thisUser.getName());
				row.add(thisUser.getAddress());
				row.add(thisUser.getGender());
				row.add(thisUser.getAge());
				
				data.add(row);
			}
		}
		
		userTable.setModel(new DefaultTableModel(data, column));
		
	}
	
	public Home(File saveFile, User user, Vector<User> userVector) {
		// TODO Auto-generated constructor stub
		
//		Populate User Vector
		this.userVector = userVector;
		this.user = user;
		this.saveFile = saveFile;
		
//		Menu Bar
		homeMenuBar = new JMenuBar();
		profileMenu = new JMenu("Profile");
		logoutMenuItem = new JMenuItem("Logout");
		logoutMenuItem.addActionListener(this);
		
		profileMenu.add(logoutMenuItem);
		profileMenu.addMenuListener(this);
		
		homeMenuBar.add(profileMenu);
		
//		Main Panel 
		mainPanel = new JPanel(new BorderLayout());
				
		userDataPanel = new JPanel(new GridLayout(5, 1));
		
		nameLabel = new JLabel(user.getName());
		addressLabel = new JLabel(user.getAddress());
		genderLabel = new JLabel(user.getGender());
		ageLabel = new JLabel(String.valueOf(user.getAge()));
		userDataPanel.setBorder(new EmptyBorder(10, 25, 0, 0));
		
		userDataPanel.add(nameLabel);
		userDataPanel.add(addressLabel);
		userDataPanel.add(genderLabel);
		userDataPanel.add(ageLabel);
		
//		User Table
		userTablePanel = new JPanel();
		
		userTable = new JTable();
		userTable.setEnabled(false);
		initTableData();
		userTableScrollPane = new JScrollPane(userTable);
		userTableScrollPane.setPreferredSize(new Dimension(600, 250));
		
		userTablePanel.add(userTableScrollPane);
		
		mainPanel.add(userDataPanel, BorderLayout.NORTH);
		mainPanel.add(userTablePanel, BorderLayout.CENTER);
		
		add(mainPanel);
		
//		Frame Settings
		setJMenuBar(homeMenuBar);
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(650, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Home");
	}

	@Override
	public void actionPerformed(ActionEvent eventListener) {
		// TODO Auto-generated method stub
		if (eventListener.getSource() == logoutMenuItem) {
			this.dispose();
			new Login(saveFile, userVector);
		}
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == profileMenu) {
			
		}
	}

}
