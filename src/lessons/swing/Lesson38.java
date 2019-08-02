package lessons.swing;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;  // Detect whenever a mouse is clicked somewhere 
import java.awt.event.MouseEvent;  // Mouse-Events

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Lesson38 {

	private static final String URL = "jdbc:postgresql://localhost/experiments";
	private static final String USER = "experiments";
	private static final String PASSWORD ="experiments";
	
	private static JButton addCustomer, removeCustomer;
	private static JLabel lFirstName, lLastName, lBirthDate;
	private static JSpinner tBirthDate;
	private static JTextField tFirstName, tLastName;
	
	private static ResultSet rows;
	private static ResultSetMetaData metaData;
	
	private static Object[] columns = {"Customer ID", "First Name", "Last Name", "Birth Date"};
	private static Object[] dbColumns = {"id", "first_name", "last_name", "birth_date"};
	private static Object[][] databaseResults;

	private static Connection conn = null;
	
	private static DefaultTableModel dbTableModel =  new DefaultTableModel(databaseResults, columns) {
		
		public Class getColumnClass(int column)
		{
			Class returnValue;
			
			if ( (column >= 0) && (column < getColumnCount()))
			{
				returnValue = getValueAt(0, column).getClass();  
				
			} else {
				returnValue = Object.class;  // Use the default object class instead
			
			}
			
			return returnValue;
		}
	};
	
	public static void main(String[] args) {
		new Lesson38();
		
	} // END OF main METHOD
	
	public Lesson38 () {
		// ---- INITIAL FRAME OPERATIONS ---- ---- ---- ---- ----
		JFrame frame = new JFrame();
		frame.setTitle("My first CRUD Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel thePanel = new JPanel();
		
		// ---- SQL OPERATIONS ---- ---- ---- ---- ----
		
		try {
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// By adding TYPE_SCROLL_SENSITIVE and CONCUR_UPDATABLE 
			// -> JTable will automatically update the database table
			Statement statement = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE, 
					Statement.RETURN_GENERATED_KEYS);
			
			String selectQuery = "SELECT id, first_name, last_name, birth_date FROM customers;";
			
			// Due to the statement-config a reference to rows will be actually
			// a reference to the database table itself
			rows = statement.executeQuery(selectQuery);
			
			Object[] tmpRow = null;
			
			while (rows.next()){				
				tmpRow = new Object[] {
						rows.getInt(1),
						rows.getString(2),
						rows.getString(3),
						rows.getDate(4)
				};
				dbTableModel.addRow(tmpRow);
				System.out.println(tmpRow);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		// ---- CREATE JTABLE ---- ---- ---- ---- ----
		JTable theTable = new JTable(dbTableModel);
		theTable.setAutoCreateRowSorter(true);
		theTable.setRowHeight(theTable.getRowHeight() + 10);
		theTable.setFont(new Font("Serif", Font.PLAIN, 18));
		
		TableColumn idColumn = theTable.getColumn("Customer ID");
		centerCellRenderer centerRenderer = new centerCellRenderer();
		idColumn.setCellRenderer(centerRenderer);
		
		JScrollPane scrollPane = new JScrollPane(theTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		thePanel.add(scrollPane, BorderLayout.CENTER);
		
		// ---- CREATE EDIT ---- ---- ---- ---- ----
		JPanel inputPanel = new JPanel();
		
		lFirstName = new JLabel("First Name");
		lLastName = new JLabel("Last Name");
		lBirthDate = new JLabel("Birthdate");
		 
		tFirstName = new JTextField(15);
		tLastName = new JTextField(15);
		
		Date today = new Date();
		
		tBirthDate = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.DAY_OF_MONTH));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(tBirthDate, "dd.MM.yyyy");
		tBirthDate.setEditor(dateEditor);
		
		addCustomer = new JButton("Add");
		addCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(tBirthDate.getValue());

				int sId = 0;
				String sFirstName = tFirstName.getText();
				String sLastName = tLastName.getText();
				Date sBirthDate = (Date) tBirthDate.getValue();
				
				java.sql.Date sqlBirthDate = parseSQLDate(sBirthDate);
								
				// Start inserting the new row
				try {
					String insertQuery = "INSERT INTO customers (first_name, last_name, birth_date) "
							+ "VALUES('" + sFirstName + "', '" + sLastName + "', '" + sBirthDate + "')";
					PreparedStatement insertStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
					
					insertStatement.executeUpdate();
					
					/* 
					// The solution taught by Derek Banas did unfortunately not work!
					// It could not correctly retrieve the ID generated by the auto-increment-id-column.
					
					// Move the database reference to the Insert-Position
					rows.moveToInsertRow();
					
					// create a new tmp-row
					rows.updateString("first_name", sFirstName);
					rows.updateString("last_name", sLastName);
					rows.updateDate("birth_date", sqlBirthDate);
					
					// Insert the newly created row
					rows.insertRow();
					
					// Move back to current row position
					//rows.last();
					
					// Write changes in ResultSet-object to the database
					rows.moveToCurrentRow();
					
					
					// GO to the last row (last inserted)
					rows.last();
					
					// Get the newly created id
					sId = rows.getInt(1);
					*/
					
					ResultSet keys = insertStatement.getGeneratedKeys();
					
					while (keys.next())
					{
						System.out.println(keys);
						sId = keys.getInt(1);
						System.out.println(sId);
					}
					
					// Update the JTable in the GUI
					Object[] customer = {sId, sFirstName, sLastName, sqlBirthDate};
					
					dbTableModel.addRow(customer);
					
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		
		removeCustomer = new JButton("Remove");
		removeCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				// Get the selected row (starts counting from 0
				int selected_row = theTable.getSelectedRow();
				
				// Remove row from Database
				try {
					// Moves the cursor to the given row number in this ResultSet object.
					// Why +1? selected row begins counting from 0 
					// BUT rows.absolute starts from 1
					rows.absolute(selected_row + 1);
					
					// Delete this row
					rows.deleteRow();
					
				} catch (SQLException se) {
					se.printStackTrace();
				}
				// Delete the row from the displayed JTable
				dbTableModel.removeRow(selected_row);
				
			}
		});
		
		inputPanel.add(lFirstName);
		inputPanel.add(tFirstName);
		inputPanel.add(lLastName);
		inputPanel.add(tLastName);
		inputPanel.add(lBirthDate);
		inputPanel.add(tBirthDate);
		inputPanel.add(addCustomer);
		inputPanel.add(removeCustomer);
		
		frame.add(inputPanel, BorderLayout.SOUTH);
		
		// ---- CELL CHANGES ---- ---- ---- ---- ----
		theTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) 
			{
				System.out.println("DOUBLE " + me.getClickCount());
				System.out.println("Select row/col: " + theTable.getSelectedRow() + "/" + theTable.getSelectedColumn());
				// If a right click occurred after any row was selected
				if ((SwingUtilities.isRightMouseButton(me)) && (theTable.getSelectedRow() >= 0))
				{
					String initialValue = (String) theTable.getModel().getValueAt(theTable.getSelectedRow(), theTable.getSelectedColumn());
					String value = JOptionPane.showInputDialog("Enter new Cell Value", initialValue);
					
					// If the user clicked Cancel - a null value would be returned and we should not proceed
					if (value != null) {
						theTable.setValueAt(value, theTable.getSelectedRow(), theTable.getSelectedColumn());
					
						String updateColumn = "";
						
						try {
							rows.absolute(theTable.getSelectedRow() + 1);
							String updateColumnFormattedName = dbTableModel.getColumnName(theTable.getSelectedColumn());
							int updateIndex = Arrays.asList(columns).indexOf(updateColumnFormattedName);
							updateColumn = (String) dbColumns[updateIndex];
							
						} catch (Exception e) {
							System.exit(0);
						}
						
						try {
							switch(updateColumn) 
							{
								case "birth_date":
									java.sql.Date sqlBirthDate = parseSQLDate(value);
									rows.updateDate(updateColumn, sqlBirthDate);
									rows.updateRow();
									break;
								default:
									rows.updateString(updateColumn, value);
									rows.updateRow();
									break;
							}
						
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
								
			}
		});
		
		
		// ---- FINAL FRAME OPERATIONS ---- ---- ---- ---- ----
		
		frame.add(thePanel, BorderLayout.CENTER);
		frame.setSize(900, 500);
		frame.setVisible(true);
		
	}  // END OF Lesson38 CONSTRUCTOR

	public class centerCellRenderer extends DefaultTableCellRenderer {
		public centerCellRenderer () {
			super();
			setHorizontalAlignment(JLabel.CENTER);
		}
	}
	private java.sql.Date parseSQLDate(Date rawDate){
		java.sql.Date sqlBirthDate = null;
		
		try {
			sqlBirthDate = new java.sql.Date(rawDate.getTime());
			
		} catch (Exception pe) {
			pe.printStackTrace();
			
		}
		return sqlBirthDate;
	}
	
	private java.sql.Date parseSQLDate(String dateString)
	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		
		java.util.Date dateBirthDate = null;
		
		try {
			dateBirthDate = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		java.sql.Date sqlBirthDate = parseSQLDate(dateBirthDate);
		
		return sqlBirthDate;
	}
	
}  // END OF Lesson38 CLASS
