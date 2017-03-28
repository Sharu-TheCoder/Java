package ConnectionToDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * This program demonstrates making JDBC connection to a SQLite database.
 * @author www.codejava.net
 *
 */
public class SqliteConnection {
 
    
	private static Connection con=null;
	static{
		try{

Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:/home/consultadd/fileDB.db";
             con = DriverManager.getConnection(dbURL);
		}
		catch(Exception e)
		{
			System.out.println("ERRORRRRRRRR"+e);
		}
	}
 public static Connection getCon()
 {	
	return con;
 }
    
}