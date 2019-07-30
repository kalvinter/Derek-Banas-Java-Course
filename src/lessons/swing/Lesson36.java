package lessons.swing;

/* TOPIC: JTables & Database Connections
 * 
 * */

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableCellRenderer;  // Specifically render information in each cell
import javax.swing.table.DefaultTableModel;  // Define methods inside of the JTable
import javax.swing.table.TableColumn;
import java.awt.Component;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Lesson36 {

	private final static String URL = "jdbc:postgresql://localhost/experiments";
	private final static String USER = "experiments";
	private final static String PASSWORD = "experiments";
	
	private static Object[][] databaseInfo;
	private static Object[] columns = {"id", "first_name", "last_name", "order_count", "avg_items", "avg_purchase_value"};
	
	private static ResultSet rows;
	private static ResultSetMetaData metaData;
	
	/* DefaultTableModel(all_rows_with_info, columns) -> allows to edit the JTable
	 * all_rows_with_info -> multi-dimensional array, holding the data itself
	 * columns -> provide column-names in the same order as in the row-array
	 * */ 
	private static DefaultTableModel dbTableModel = new DefaultTableModel(databaseInfo, columns) 
	{
		/* This is a shortcut to override methods in this class! 
		 * Just add curly braces after the new keyword
		 * "new DefaultTableModel(databaseInfo, columns){ ...};
		 *  */
		
		/* By default JTable treats all info retrieved from the database as String!
		 * In this method you look up the correct class of each column and return it
		 * so JTable renders it correctly
		 * */
		public Class getColumnClass(int column)
		{
			Class returnValue;
			
			if ( (column >= 0) && (column < getColumnCount()))
			{
				System.out.println();
				System.out.println(column);
				System.out.println(getValueAt(0, column));
				System.out.println(getValueAt(0, column).getClass());
				// Get the correct Class
				// getValueAt -> JTable function
				// getValueAt(int row, int col)
				// getValueAt(0, column) -> getClass from Cell in first row at this column
				returnValue = getValueAt(0, column).getClass();  
				
			} else {
				returnValue = Object.class;  // Use the default object class instead
			
			}
			
			return returnValue;
		}
		
		
	};
	
	public static void main(String[] args) {
		
		
		
		new Lesson36();
		
	}  // END OF main METHOD
	
	public Lesson36() 
	{
		JFrame frame = new JFrame();
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("My first JTable Frame");
				
		// ---- SET UP DB CONNECTION ---- ---- ---- ---- ----
		
		Connection conn = null;
		
		try {
						
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			Statement statement = conn.createStatement();
			
			/* The Lesson includes a query and data create during a sabermetrics course. 
			 * For convenience purposes a different query and self-created random data was used.
			 * */
			String selectQuery = "SELECT a.id, a.first_name, a.last_name, count(b.id) as order_count, avg(b.items) as avg_items, "
					+ "avg(b.purchase_value) as avg_purchase_value "
					+ "FROM customer as a LEFT JOIN orders as b ON a.id=b.customer_id "
					+ "GROUP BY a.id, a.first_name, a.last_name ORDER BY avg(b.purchase_value)";
			
			// Execute the query-String
			// ResultSet row -> immutable, can only be read from beginning to end
			rows = statement.executeQuery(selectQuery);
			
			// MetaData contains all kind of information such as ColumnCount, ColumnNames etc.
			metaData = rows.getMetaData();
			int columnCount = metaData.getColumnCount();
			System.out.println(columnCount);

			String[] columns = new String[columnCount];
			
			for (int i = 1; i <= columnCount; i++)
			{
				// Column-names can be accessed via an integer index.
				// BUT: the index starts at 1 because this is the first column
				// This has to be kept in mind while looping over it
				columns[i-1] = metaData.getColumnName(i);
			}
			System.out.println(Arrays.toString(columns));
			
			Object[] tempRow;
			
			while(rows.next())
			{
				tempRow = new Object[] {
						rows.getInt(1),  // id
						rows.getString(2),  // first_name
						rows.getString(3),  // last_name
						rows.getInt(4),  // order_count
						rows.getDouble(5),  // avg_items
						rows.getDouble(6),  // avg_purchase_value
				};
				dbTableModel.addRow(tempRow);
				
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} 
		
		
		// ---- FINAL OPERATIONS ---- ---- ---- ---- ----
		// Pass the model to the JTable -> will use it to create 
		// the table with the right columns and values
		JTable theTable = new JTable(dbTableModel);
		
		// Set row height to + 10 px
		theTable.setRowHeight(theTable.getRowHeight() + 10);
		
		theTable.setFont(new Font("Serif", Font.PLAIN, 20));
		
		// Enables sorting of values by column
		theTable.setAutoCreateRowSorter(true);
		
		// Turn off auto-resize to be able to manually define column-sizes 
		theTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// Override the DefaultTableCellRenderer to apply certain settings to cells
		CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
		DecimalTableCellRenderer decimalRenderer = new DecimalTableCellRenderer();
		
		// You can pull out and modify individual columns by using TableColumn object
		// You can draw the information directly from the columnModel
		TableColumn idCol = theTable.getColumn("id");
		idCol.setCellRenderer(centerRenderer);
		
		TableColumn col1 = theTable.getColumnModel().getColumn(1);
		col1.setPreferredWidth(200);  // Set width
		
		TableColumn col2 = theTable.getColumnModel().getColumn(2);
		col2.setPreferredWidth(200);  // Set width
		
		TableColumn col3 = theTable.getColumn("order_count");  // Get column by name
		// Apply the new renderer to the cell
		col3.setCellRenderer(centerRenderer);
		
		TableColumn col_value = theTable.getColumn("avg_purchase_value");
		col_value.setCellRenderer(decimalRenderer);
		
		JScrollPane scrollPane = new JScrollPane(theTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.setSize(800,500);
		// frame.pack();  -> Does not work well
		
		frame.setVisible(true);
		
	}  // END OF Lesson36 CONSTRUCTOR
	
	private class CenterTableCellRenderer extends DefaultTableCellRenderer 
	{
		public CenterTableCellRenderer() {
			setHorizontalAlignment(JLabel.CENTER);
		}
	}  // END OF CenterTableCellRenderer CLASS
	
	private class DecimalTableCellRenderer extends DefaultTableCellRenderer
	{
		private DecimalFormat formatter = new DecimalFormat( "#.00" );
		
		// Override getTableCellRendererComponent() to change the value format itself
		// Its the only method you can override for that
		// never forget to call the super-method to finish the method
		public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column)
		{
			 // format the cell value as required
	         value = formatter.format((double) value);
	 
	         // Call super class to finish the function
	         return super.getTableCellRendererComponent(
	            table, value, isSelected, hasFocus, row, column );
		}
		
		public DecimalTableCellRenderer() {
			super();
			setHorizontalAlignment(JLabel.RIGHT);
		}
		
		
	}  // END OF DecimalTableCellRenderer CLASS
	
}  // END OF Lesson36 CLASS
