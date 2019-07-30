package lessons.database;

/* TOPIC: Database Connections
 * 
 * NOTE: In Lesson34 MySQL was used. This class, however, uses PostgreSQL 11
 * 
 * The JDBC Driver for PostgreSQL was downloaded from https://jdbc.postgresql.org/download.html
 * The JDBC Driver is saved in the folder "lib" and added to the build-path -> lib -> Classpath -> Add JAR's
 * */

import java.sql.*;

public class Lesson34 {

	private static final String URL = "jdbc:postgresql://localhost/experiments";
	private static final String USER = "experiments";
	private static final String PASSWORD = "experiments";
	
	public static void main(String[] args)
	{
		Connection conn = null;
		
		try {
			/* Here: Loads the necessary Driver-Class in memory and initializes all 
			 * static variables. This includes registering at the DriverManager.
			 * NOTE: This is only required for JDBC with a version < 4.0!
			 * If the version > 4.0 -> the statement can be omitted!
			 * 
			 * Generally: Class.forName(String className) loads a class in memory
			 * without instantiating it. 
			 * This is a great method to load a class dynamically. 
			 * The className can be a String! 
			 * */
			Class.forName("org.postgresql.Driver");
			
			/* DriverManager handles all JDBC drivers 
			 * Depending on the database defined in the URL (e.g. jdbc:postgresql), 
			 * the correct driver is used!
			 * */
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
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
