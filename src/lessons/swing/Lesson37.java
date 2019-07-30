package lessons.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;


public class Lesson37 extends JFrame {

	private static JLabel lId, lFirstName, lLastName, lBirthDate;
	private static JTextField tId, tFirstName, tLastName, tBirthDate;
	private static java.util.Date dateBirthDate, sqlBirthDate;
	
	private final static String URL = "jdbc:postgresql://localhost/experiments";
	private static final String USER = "experiments";
	private static final String PASSWORD ="experiments";
	
	private static Object[] columns = {"id", "first_name", "last_name", "birth_date"};
	private static Object[][] databaseResults;
	
	private static ResultSet rows;
	private static ResultSetMetaData metaData;
	
	private static DefaultTableModel dbTableModel = new DefaultTableModel(databaseResults, columns) 
	{
		
		public Class getColumnClass(int column)
		{
			Class returnValue;
			
			if ( (column >= 0) && (column < getColumnCount()))
			{
				/*
				System.out.println();
				System.out.println(column);
				System.out.println(getValueAt(0, column));
				System.out.println(getValueAt(0, column).getClass()); 
				*/

				returnValue = getValueAt(0, column).getClass();  
				
			} else {
				returnValue = Object.class; 
			
			}
			
			return returnValue;
		}
		
		
	};  // END OF dbTableModel DEFINITION
	
	// Once JTable is instantiated with dbTableModel -> all changes in databaseResults will be 
	// reflected via the Model to the table -> table gets updated 
	private static JTable theTable = new JTable(dbTableModel);
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setTitle("My first CRUD Frame");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// ---- DB CONNECTION ---- ---- ---- ---- ----
		
		Connection conn = null;
		
		try {
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			Statement statement = conn.createStatement();
			
			String selectQuery = "SELECT id, first_name, last_name, birth_date FROM customer;";
			
			rows = statement.executeQuery(selectQuery);
			metaData = rows.getMetaData();

			Object[] tmpRow;
			
			while (rows.next()) 
			{
				tmpRow = new Object[] {
						rows.getInt(1),
						rows.getString(2),
						rows.getString(3),
						rows.getDate(4),
				};
				System.out.println(Arrays.toString(tmpRow));
				dbTableModel.addRow(tmpRow);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} 
		
		// ---- Modify & Finalize JTable ---- ---- ---- ---- ---- ---- ---- 
		theTable.setAutoCreateRowSorter(true);
		theTable.setFont(new Font("Serif", Font.PLAIN, 26));
		theTable.setRowHeight(theTable.getRowHeight() + 20);

		JScrollPane scrollPane = new JScrollPane(theTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(scrollPane, BorderLayout.CENTER);

		// ---- Add Buttons ---- ---- ---- ---- ---- ---- ----
		JButton addCustomer = new JButton("Add Customer");
		addCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sFirstName = "", sLastName = "", sDate = "";
				int id = 0;
				
				// Get values from textFields
				id = Integer.parseInt(tId.getText());
				sFirstName = tFirstName.getText();
				sLastName = tLastName.getText();
				sDate = tBirthDate.getText();
				
				System.out.println(sDate);
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
				
				try {
					// Parse DateString to Java-Date by using the format-string
					dateBirthDate = dateFormatter.parse(sDate);
					
					// Format the parsed date to a standard SQL-date
					// The SQL-Date-Parser needs the getTime() representation!
					// getTime() -> Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
					sqlBirthDate = new java.sql.Date(dateBirthDate.getTime());
					
				} catch (ParseException ex) {
					ex.printStackTrace();
					System.exit(0);
				}
				
				Object[] customer = {id, sFirstName, sLastName, sqlBirthDate};
				dbTableModel.addRow(customer);
				
			}
		});
		
		JButton removeCustomer = new JButton("Remove Customer");
		removeCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dbTableModel.removeRow(theTable.getSelectedRow());
			}
		});
		
		lId = new JLabel("Id");
		lFirstName = new JLabel("First Name");
		lLastName = new JLabel("Last Name");
		lBirthDate = new JLabel("Birth Date");
		
		tId = new JTextField(3);
		tFirstName = new JTextField(15);
		tLastName = new JTextField(15);
		tBirthDate = new JTextField("dd.MM.yyyy", 15);
		
		JPanel thePanel = new JPanel();
		
		thePanel.add(lId);
		thePanel.add(lFirstName);
		thePanel.add(lLastName);
		thePanel.add(lBirthDate);
		thePanel.add(tId);
		thePanel.add(tFirstName);
		thePanel.add(tLastName);
		thePanel.add(tBirthDate);
		
		thePanel.add(addCustomer);
		thePanel.add(removeCustomer);
				
		// ---- FINAL FRAME OPERATIONS ---- ---- ---- ---- ----
		frame.add(thePanel, BorderLayout.SOUTH);
		frame.setSize(900, 500);
		// this.pack();
		
		frame.setVisible(true);
		
	}  // END OF main METHOD
	
	
}  // END OF Lesson37 CLASS
