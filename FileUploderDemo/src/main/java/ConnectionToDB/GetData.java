package ConnectionToDB;
import java.sql.*;

public class GetData
{
  public static void main( String args[] )
  {
//    Statement stmt = null;
//    try {
//      Class.forName("org.sqlite.JDBC");
//      String dbURL = "jdbc:sqlite:/home/consultadd/fileDB.db";
//      Connection c = SqliteConnection.getCon();
//      System.out.println(c.getMetaData());
//      Statement stmt1 = c.createStatement();
//      System.out.println("Opened database successfully"+stmt1);
//      ResultSet rs = stmt1.executeQuery("SELECT * FROM File_uploader;" );
//      while ( rs.next() ) {
//         int id = rs.getInt("id");
//         String  File_name= rs.getString("File_name");
//         int file_path  = rs.getInt("file_path");
//         String  file_size = rs.getString("file_size");
//         String file_type = rs.getString("file_type");
//         System.out.println( "ID = " + id );
//         System.out.println( "NAME = " + File_name );
//         System.out.println( "AGE = " + file_path );
//         System.out.println( "ADDRESS = " + file_size );
//         System.out.println( "SALARY = " + file_type);
//         System.out.println("ff");
//      }
//      rs.close();
//      stmt1.close();
//      c.close();
//    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//      System.exit(0);
//    }
//    System.out.println("Operation done successfully");
  }
}