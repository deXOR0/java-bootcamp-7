import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	public Vector<User> userVector = new Vector<>();
	public File saveFile;
	public DatabaseConnection db;

	public Main() {
		// TODO Auto-generated constructor stub
//		GUI + Java Swing
//		- Frame (Container)
//			- Panel (Container)
//				- Component
//		Layout Manager
//		- Border Layout (Default layoutnya JFrame)
//		- Flow Layout (Default JPanel)
//		- Grid Layout
//		
//		JPanel panel1 = new JPanel();
//		panel1.setLayout(new GridLayout(2, 3));
//		
//		JButton button1 = new JButton("Click Me!");
//		JButton button2 = new JButton("Click Me Too!");
//		JButton button3 = new JButton("Button 3");
//		JButton button4 = new JButton("Button 4");
//		JButton button5 = new JButton("Button 5");
//		
//		panel1.add(button1);
//		panel1.add(button2);
//		panel1.add(button3);
//		panel1.add(button4);
//		panel1.add(button5);
//		
//		add(panel1);
//		
//		setLayout(new BorderLayout());
//		setVisible(true);
//		setLocationRelativeTo(null);
//		setSize(400, 400);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
//		setTitle("My Main Frame");
//		readSaveFile();
		getUsersData();
		Register register = new Register(saveFile, userVector);
	}
	
	public void initSaveFile() {
//		saveFile = new File("C:\\Users\\Rig\\Desktop\\save.txt"); // Escape character
		saveFile = new File("save.txt");
		try {
			saveFile.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void readSaveFile() {
		initSaveFile();
		try {
			Scanner fileScan = new Scanner(saveFile);
			while (fileScan.hasNextLine()) {
				String data = fileScan.nextLine();
				String[] splittedData = data.split(",");
				userVector.add(new User(splittedData[0], splittedData[1], splittedData[2], Integer.valueOf(splittedData[3]), splittedData[4]));
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getUsersData() {
		db = new DatabaseConnection();
		
		try {
			db.resultSet = db.getUsersData();
			
			System.out.println("Column count " + db.resultSetMetaData.getColumnCount());
			System.out.println(db.resultSetMetaData.getTableName(1));
			while (db.resultSet.next()) {
				int id = Integer.valueOf(String.valueOf(db.resultSet.getObject(1)));
				String name = (String.valueOf(db.resultSet.getObject(2)));
				String address = (String.valueOf(db.resultSet.getObject(3)));
				String gender = (String.valueOf(db.resultSet.getObject(4)));
				int age = Integer.valueOf(String.valueOf(db.resultSet.getObject(5)));
				String password = (String.valueOf(db.resultSet.getObject(6)));
				userVector.add(new User(name, address, gender, age, password));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
