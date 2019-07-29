package lessons.database;

/* TOPIC: Database Connections
 * 
 * NOTE: In Lesson34 MySQL was used. This class, however, uses PostgreSQL
 * 
 * The JDBC Driver for PostgreSQL was downloaded from https://jdbc.postgresql.org/download.html
 * The JDBC Driver is saved in the folder "lib" and added to the build-path -> lib -> Classpath
 * */

import java.sql.*;

public class Lesson34 {

	private static final String url = "jdbc:postgresql://localhost/experiments";
	private static final String user = "experiments";
	private static final String password = "experiments";
	
	public static void main(String[] args)
	{
		Connection conn = null;
		
		try {
			// This just checks if the necessary Driver-Class is available
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			
			// Statement-Objects are used to execute a String query via an established connection 
			Statement sqlState = conn.createStatement();
			
			String selectStuff = "Select first_name FROM customer";
			
			/* rows is a ResultSet
			 * -> ResultSets are immutable
			 * -> ResultSets can only be read from the beginning to the end
			 * */ 
			ResultSet rows = sqlState.executeQuery(selectStuff);
			
			while (rows.next()) {
				// rows.getString(column_name)
				System.out.println(rows.getString("first_name"));
			}
			
		} catch (SQLException ex) {
			// Print general SQL Exception message
			System.out.println("SQLException: " + ex.getMessage());
			
			// Print vendor specific error code
			System.out.println("VendorError: " + ex.getErrorCode());
			
		} catch (ClassNotFoundException e) {
			// Catches the Class.forName-method
			// It means that the necessary Driver-Class from the jdbc-jar 
			// could not be located
			e.printStackTrace();
		}
	}
	
}
