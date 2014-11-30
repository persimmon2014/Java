import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class database {
   
	private String result;
   public static void main(String args[])
   {
		   Connection conn=null;
		   Statement sm=null;
		   ResultSet result=null;
		   String drive="com.mysql.jdbc.Driver";
		   String url="jdbc:mysql://localhost:3306/ucbcec";
		   String user="root";
		   String password="root";
		   try
		   {
		   Class.forName(drive).newInstance();
		   conn=DriverManager.getConnection(url,user,password);
		   sm=conn.createStatement();
		   //System.out.println("Connected successfully!");
		   
		   //read database
//		   result = sm.executeQuery("select * from test");
//		   while(result.next()){
//			   System.out.println(result.getObject(1));
//		   }
		   String name = "weizi";
		   String name1 = "weizi";
//		   //insert 
		   java.sql.PreparedStatement ps = conn.prepareStatement("insert into test (FirstName,LastName)values('"+name+"','"+name1+"')");
		   ps.executeUpdate();
//		   
		   conn.close();
//		   //delete
//		   java.sql.PreparedStatement ps1 = conn.prepareStatement("delete from test where mainid = '100'");
//		   ps1.executeUpdate();
		   
		   
		   }
		   catch(Exception e)
		   {
		    //System.out.println("Connected fail!");
		    e.printStackTrace();
		   }
   }
	
}