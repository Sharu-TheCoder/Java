package ConnectionToDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class FetchData {
    @Autowired  
    FilePojo filePojo;
	JSONObject obj=new JSONObject();
	@RequestMapping("/viewemp") 
//	@GetMapping("/view")
//    @ResponseBody
    
    public String viewemp(){  
		 try {
		Connection c=SqliteConnection.getCon();
		   Statement stmt1 = c.createStatement();		     
		      ResultSet rs = stmt1.executeQuery("SELECT * FROM File_uploader;" );
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         String  File_name= rs.getString("File_name");
		         int file_path  = rs.getInt("file_path");
		         String  file_size = rs.getString("file_size");
		         String file_type = rs.getString("file_type");
		         System.out.println( "ID = " + id );
		         System.out.println( "NAME = " + File_name );
		         System.out.println( "AGE = " + file_path );
		         System.out.println( "ADDRESS = " + file_size );
		         System.out.println( "SALARY = " + file_type);
		         System.out.println("ff");
		      }
		      rs.close();
		     
				stmt1.close();
				 c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		    
       
		return "";
    }  
	@GetMapping("/")
	public String fileData()
	{
		try{
			
			Connection c=SqliteConnection.getCon();
			PreparedStatement pst=c.prepareStatement("select * from File_uploader");
			
			ResultSet rst=pst.executeQuery();
			while(rst.next())
			{
				
				obj.put("Id", rst.getInt(1));
				obj.put("File_name", rst.getString(2));
				
			}
		}
		catch(Exception e)
		{
			System.out.println("errorrr"+e);
		}
System.out.println(obj);
		return obj+"";
	}
	
}
