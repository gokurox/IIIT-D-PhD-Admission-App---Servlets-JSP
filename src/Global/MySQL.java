package Global;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Global.Global;

public class MySQL {
	
	private final static Logger LOG = Logger.getLogger (MySQL.class.getName());
	private static String SQLuser;
	private static String SQLpass;
	private static String SQLdatabase;
	private static String SQLtable;
	private static String SQLadmintable;
	private static String SQLdriver;
	private static String SQLurl;
	
	private static Connection sqlTunnel;
	
	static {
		SQLuser = "root";
		SQLpass = "password";
		SQLdatabase = "AP_CP";
		SQLtable = "APPLICATIONS";
		SQLadmintable = "ADMIN";
		SQLdriver = "com.mysql.jdbc.Driver";
		SQLurl = "jdbc:mysql://localhost:3306/" + SQLdatabase;
		
		sqlTunnel = null;
		
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel (Level.ALL);
		LOG.addHandler (handler);
		LOG.setUseParentHandlers (false);
	}
	
	/* *********************************************************************************************/

	private MySQL() {
	}
	
	/* *********************************************************************************************/
	
	public static String getSQLtable() {
		return SQLtable;
	}
	
	public static String getSQLadmintable() {
		return SQLadmintable;
	}
	
	/* *********************************************************************************************/
	
	public static void connect () {
		LOG.entering (MySQL.class.getName(), "connect");
		try
		{
			if (sqlTunnel != null && !sqlTunnel.isClosed()) {
				LOG.warning ("There is already a valid Connection available to the Server. Disconnect First");
				LOG.exiting (MySQL.class.getName(), "connect");
				return;
			}
			
			// Register JDBC driver
			Class.forName (SQLdriver);
			LOG.info ("JDBC Driver Found");
			
			// Open a connection
			sqlTunnel = DriverManager.getConnection (SQLurl, SQLuser, SQLpass);
			LOG.info ("New Connection to MySQL Database Created Successfully");
		}
		catch (SQLException se)
		{
			//Handle errors for JDBC
			LOG.log (Level.SEVERE, "JDBC Error: ", se);
		}
		catch (Exception e)
		{
			//Handle errors for Class.forName
			LOG.log (Level.SEVERE, "Class.forName Error: ", e);
		}
		
		if (!doesTableExist (SQLtable))
			createTable_unchecked (SQLtable);
		
		LOG.exiting (MySQL.class.getName(), "connect");
	}
	
	public static void disconnect () {
		LOG.entering (MySQL.class.getName(), "disconnect");
		try
		{
			if (sqlTunnel == null || sqlTunnel.isClosed()) {
				LOG.warning ("There is no valid Connection available to the Server. Connect First");
				LOG.exiting (MySQL.class.getName(), "disconnect");
				return;
			}
			
			sqlTunnel.close();
			LOG.info ("MySQL Server Connection dropped Succesfully");
		}
		catch (SQLException se)
		{
			LOG.log (Level.SEVERE, "SQL Exception : ", se);
		}
		catch (Exception e)
		{
			LOG.log (Level.SEVERE, "", e);
		}
		LOG.exiting (MySQL.class.getName(), "disconnect");
	}
	
	public static Statement getStatement () {
		LOG.entering (MySQL.class.getName(), "getStatement");
		Statement STM = null;
		
		try
		{
			if (sqlTunnel == null || sqlTunnel.isClosed()) {
				LOG.warning ("There is no valid Connection available to the Server. Connect First");
				LOG.exiting (MySQL.class.getName(), "getStatement");
				return null;
			}
			
			STM = sqlTunnel.createStatement();
			LOG.info ("MySQL Statement Created Successfully");
		}
		catch (SQLException se)
		{
			LOG.log (Level.SEVERE, "SQL Exception : ", se);
		}
		catch (Exception e)
		{
			LOG.log (Level.SEVERE, "", e);
		}
		LOG.exiting (MySQL.class.getName(), "getStatement");
		return STM;
	}
	
	/* *********************************************************************************************/
	
	// Following functions must be used after a Connection has
	// been made. This is the responsibility of the developer
	
	public static boolean doesTableExist (String tableName) {
		LOG.entering (MySQL.class.getName(), "doesTableExist");
		
		Statement STM = null;
		ResultSet RS = null;
		String sqlQuery = null;
		String q = "\"";
		int NOR;			// Number of Rows
		boolean returnVal = false;
		
		try {
			sqlQuery = "SHOW TABLES LIKE " + q + tableName + q;		
			
			STM = getStatement();
			RS = STM.executeQuery (sqlQuery);
			
			RS.last();
			NOR = RS.getRow();
			RS.first();
			
			if (NOR == 0)
				returnVal = false;		// Table doesn't exist
			else
				returnVal = true;
		}
		catch (SQLException se)
		{
			LOG.log (Level.SEVERE, "", se);
		}
		catch (Exception e)
		{
			LOG.log (Level.SEVERE, "", e);
		}
		finally
		{
			try
			{
				if (STM != null)
					STM.close();
				if (RS != null)
					RS.close();
			}
			catch (SQLException se) {
			}
		}
		
		LOG.log (Level.INFO, "{0}: {1}", new Object[] {tableName, returnVal});
		LOG.exiting (MySQL.class.getName(), "doesTableExist");
		return returnVal;
	}
	
	/* *********************************************************************************************/
	
	public static void createTable_unchecked (String tableName) {
		LOG.entering (MySQL.class.getName(), "createTable_unchecked");
		
		Statement STM = null;
		String sqlQuery = null;
		
		try
		{
			sqlQuery = "CREATE TABLE " + tableName + Global.SQL_tabledeclaration;
			STM = getStatement();
			STM.executeUpdate (sqlQuery);
			STM.close();
			
			LOG.log (Level.INFO, "Table {0} Created Successfully", new Object[] {tableName});
		}
		catch (SQLException se)
		{
			LOG.log (Level.SEVERE, "", se);
		}
		catch (Exception e)
		{
			LOG.log (Level.SEVERE, "", e);
		}
		LOG.exiting (MySQL.class.getName(), "createTable_unchecked");
	}
 	
	public static void createTable_checked (String tableName) {
		LOG.entering (MySQL.class.getName(), "createTable_checked");
		
		if (!doesTableExist (tableName))
		{
			createTable_unchecked (tableName);
		}
		
		LOG.exiting (MySQL.class.getName(), "createTable_checked");
	}

	public static void createTable_overwrite (String tableName) {
		LOG.entering (MySQL.class.getName(), "createTable_overwrite");
		
		if (doesTableExist (tableName))
			dropTable_unchecked (tableName);
		
		createTable_unchecked (tableName);
		
		LOG.exiting (MySQL.class.getName(), "createTable_overwrite");
	}
	
	/* *********************************************************************************************/
	
	public static void dropTable_unchecked (String tableName) {
		LOG.entering (MySQL.class.getName(), "dropTable_unchecked");
		
		Statement STM = null;
		String sqlQuery = null;
		
		try
		{
			sqlQuery = "DROP TABLE " + tableName;
			STM = getStatement();
			STM.executeUpdate (sqlQuery);
			STM.close();
			
			LOG.log (Level.INFO, "Table {0} dropped Successfully", new Object[] {tableName});
		}
		catch (SQLException se)
		{
			LOG.log (Level.SEVERE, "", se);
		}
		catch (Exception e)
		{
			LOG.log (Level.SEVERE, "", e);
		}
		
		LOG.exiting (MySQL.class.getName(), "dropTable_unchecked");
	}
	
	public static void dropTable_checked (String tableName) {
		LOG.entering (MySQL.class.getName(), "dropTable_checked");
		
		if (doesTableExist (tableName)) {
			dropTable_unchecked (tableName);
		}
		
		LOG.exiting (MySQL.class.getName(), "dropTable_checked");
	}

	/* *********************************************************************************************/
		
/*	Main function to Test the Database Operations
 * 	Run As a Java Application
 */
	
//	public static void main(String[] args) {
//		MySQL.connnect();
//		
//		Statement a = MySQL.getStatement();
//		assert (a != null);
//		
//		System.out.println (doesTableExist ("Hello"));		// false
//		MySQL.createTable_checked ("Hello");
//		System.out.println (doesTableExist ("Hello"));		// true
//		MySQL.dropTable_checked ("Hello");
//		System.out.println (doesTableExist ("Hello"));		// false
//		
//		MySQL.disconnect();
//	}
}
