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

import javax.servlet.*;
import javax.servlet.http.*;

public class test1 extends HttpServlet {
   
	private String result;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("TEXT/HTML");
		PrintWriter out = response.getWriter();

		//name
	    String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		
		//departure date
		String deMonth = request.getParameter("departure_month");
	    String deDay = request.getParameter("departure_day");
	    String deYear =  request.getParameter("departure_year");
	    String departureDate = deMonth + "-" + deDay + "-" + deYear;
	    
	    //mailing address
	    String street11 = request.getParameter("street11");
	    String city1 = request.getParameter("city1");
	    String state1 = request.getParameter("state1");
	    String zipcode1 = request.getParameter("zipcode1");
	    String mailingAddress = street11 + ", " + city1 +", "+ state1+ " " + zipcode1;
	    
	    //return address
	    String street21 = request.getParameter("street21");
	    String city2 = request.getParameter("city2");
	    String state2 = request.getParameter("state2");
	    String zipcode2 = request.getParameter("zipcode2");
	    String returnAddress = street21 + ", " + city2 +", "+ state2+ " " + zipcode2;
	    	
	    //contact info
		String cellNumber = request.getParameter("phone");
		String ext = request.getParameter("ext");
		String workNumber = request.getParameter("phone2");
		String emailAddress = request.getParameter("email");
		String passportNumber = request.getParameter("passport"); 
		
		//visa info
		String visaType = request.getParameter("visa_type");
		String visaEntry = request.getParameter("visa_entry");
		String processingType = request.getParameter("processing_time");
		String agentProcessType = processingType;
		String fedexAccount = request.getParameter("fedexaccount");
		
		if(processingType.equalsIgnoreCase("Standard"))
		{
			agentProcessType += " (10 business days include shipping)";
		}
		else if(processingType.equalsIgnoreCase("Express"))
		{
			agentProcessType += " (6 business days include shipping)";
		}
		else if(processingType.equalsIgnoreCase("Rush"))
		{
			agentProcessType += " (3 business days include shipping)";
		}
		String shippingMethod = request.getParameter("shipping_option");
		
		//fee
		String consularFee = request.getParameter("consular_fee");
		String conExpressFee = request.getParameter("express_fee");
		String agentFee = request.getParameter("agent_process_fee");
		String shippingFee = request.getParameter("shipping");
		String totalFee = request.getParameter("subtotal");
		
		
		
		   Connection conn=null;
		   Statement sm=null;
		   ResultSet result=null;
		   String drive="com.mysql.jdbc.Driver";
		   String url = "jdbc:mysql://mysql5.metawerx.net:3506/persimmon201?autoReconnect=true";
		   //String url="jdbc:mysql://localhost:3306/ucbcec";
		   String user="persimmon201";
		   String password="PErS7922gQ";
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
		   
//		   //insert 
		 
		   java.sql.PreparedStatement ps = null;
		   if(emailAddress.length()>4)
		   {
		   ps = conn.prepareStatement("insert into test" +
		   		"(FirstName,LastName,MailingAddress,ReturnAddress,CellPhone,WorkPhone," +
		   		"Ext,Email,PassportNumber,DepatureDate,VisaType,VisaEntry,ProcessingType," +
		   		"ShippingMethod,AccountNumber,TotalFee) values" +
		   		"('"+firstName+"','"+lastName+"','"+mailingAddress+"','"+returnAddress+"'," +
		   				"'"+cellNumber+"','"+workNumber+"','"+ext+"','"+emailAddress+"'," +
		   						"'"+passportNumber+"','"+departureDate+"','"+visaType+"','"+visaEntry+"','"+processingType+"'," +
		   								"'"+shippingMethod+"','"+fedexAccount+"','"+totalFee+"')");
		  
		   }
		   
		   
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

		   
		 
		   
	
		
		
	
		
		if (request.getParameter("submit")!=null )
		{
			//header
			out.println("<html><head>");
			out.println("<title>UCBCEC - Service Request Form</title>");
			out.println("</head><body topmargin=\"0\">");
			
			
			//for two head pics
			out.println("<table id=\"Table_00\" align=\"center\" width=\"680\" height=\"80\">");
			out.println("<tbody><tr><td>");
			out.println("&nbsp;");
			out.println("<img  src=\"info.jpg\" height=\"50\" width=\"280\">");
			out.println("&nbsp;");
			out.println("<img  src=\"logo.jpg\" height=\"50\" width=\"360\">");
			out.println("</td></tr></tbody></table>");
		
			
			//table and form initialize
			out.println("<table id=\"Table_01\" align=\"center\" width=\"680\"  height=\"651\">");
			out.println("<td ><form  name=\"form1\">");
			out.println("<table  width=\"100%\">");
			out.println("<td><table border=\"1\" width=\"100%\">");
			
			
			//personal information
			out.println("<tr><td><table width=\"100%\"><tbody><tr>");
			out.println("<td><strong>Personal Information:</strong></td>");
			out.println("</tr><tr><td><table width=\"100%\"><tbody>");		
			out.println("<tr><td ><strong>First Name:</strong>");
			out.println(firstName);
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Last Name:</strong>");
			out.println(lastName);
			out.println("</td></tr><tr><td ><strong>Mailing Address:</strong>");
			out.println(mailingAddress);		
			out.println("</td></tr><tr><td ><strong>Passport Return Address:</strong>");
			if(returnAddress.length()>6)
			{
			out.println(returnAddress);
			}
			out.println("</td></tr><tr><td ><strong>Cell Phone Number:</strong>");
			out.println(cellNumber);		
			out.println("</td></tr><tr><td ><strong>Work Phone Number:</strong>");
			out.println(workNumber);	
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Ext:</strong>");
			out.println(ext);
			out.println("</td></tr><tr><td ><strong>Email Address:</strong>");
			out.println(emailAddress);		
			out.println("</td></tr><br></tbody></table></td></tr></tbody></table></td></tr>");
			
			
			//visa application information
			out.println("<tr><td><table width=\"100%\"><tbody><tr>");
			out.println("<td><strong>Visa Application Information:</strong></td>");
			out.println("</tr><tr><td><table width=\"100%\">");	
			out.println("<tbody><tr><td ><strong>Passport Number:</strong>");
			out.println(passportNumber);
			out.println("</td><td ><strong>Estimated Departure Date:</strong>");
			out.println(departureDate);
			out.println("</td></tr><tr><td ><strong>Visa Type:</strong>");
			out.println(visaType);
			out.println("</td><td ><strong>Visa Entry:</strong>");
			out.println(visaEntry);
			out.println("</td></tr><tr><td ><strong>Processing Type:</strong>");
			out.println(processingType);
			out.println("</td><td ><strong>Shipping Method:</strong>");
			out.println(shippingMethod);
			if(shippingMethod.equalsIgnoreCase("self-managed"))
			{
				out.println("</td></tr><tr><td><strong>FedEx Account Number:</strong>");
				out.println(fedexAccount);
				out.println("</td></tr>");
			}
			out.println("</tbody></table></td></tr></tbody></table></td></tr>");
			
			
			//fee summary
			out.println("<tr><td><table width=\"100%\"><tbody><tr>");
			out.println("<td><strong>Consular & Service Fee Summary:</strong></td>");
			out.println("</tr><tr><td><table  width=\"100%\">");
			out.println("<tbody><tr><td ><table border=\"1\" width=\"100%\">");
			out.println("<tbody><tr><td><strong>Consular Fee</strong></td><td>");
			out.println(visaEntry);
			out.println("</td><td align=\"center\">");
			out.println("$"+consularFee);
			out.println("</td></tr><tr><td><strong>Consular Express Fee </strong></td><td>");
			out.println(processingType);		
			out.println("</td><td align=\"center\">");
			out.println("$"+conExpressFee);
			out.println("</td></tr> <tr><td><strong>Agent Processing Fee</strong></td><td>");
			out.println(agentProcessType);
			out.println("</td><td align=\"center\">");
			out.println("$"+agentFee);
			out.println("</td></tr><tr><td><strong>Shipping Fee</strong></td><td>");
			out.println(shippingMethod);	
			out.println("</td><td align=\"center\">");
			out.println("$"+shippingFee);
			out.println("</td></tr><tr><td colspan=\"2\" align=\"right\"><strong>Total:</strong></td><td align=\"center\">");
			out.println("$"+totalFee);
			out.println("</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr>");
			
			
			//additional request
			out.println("<tr><td><table width=\"100%\" style=\"table-layout:fixed;word-wrap:break-word;\">");		
			out.println("<tbody><tr><td><strong>Additional Request:</strong></td></tr><tr><td> ");
		    out.println("<br><br><br>");
			out.println("</td></tr></tbody></table></td></tr>");
			
			
			//mailing reminder
			out.println("<tr> <td><table width=\"100%\"><tbody>");		
			out.println("<tr><td><strong>Mailing Reminder:</strong></td></tr> <tr><td><ul>");
			out.println("<li>Please <strong>Confirm</strong> your information above, then <Strong>Print</Strong> this form out and <Strong>Sign</Strong> it.");
			out.println("<li>We accept Money Order, Traveler's Checks, and Company Checks but <strong>NOT Personal Checks</strong>.");
			out.println("<li>Please make your check payable to <strong>UCBCEC</strong>.");
			out.println("<li>Please make sure you have all <strong>Required Documents (including this form and paycheck)</strong> ready before mailing the package to us.");
			
			out.println("<li>Our mailing address is: <strong>UCBCEC, 3900 Jermantown Rd Suite 300, Fairfax, VA 22030</strong>");
            out.println("</ul></td></tr></tbody></table></td></tr>");			
			
			
			//acknowledgement
            out.println("<tr><td><table width=\"100%\" style=\"table-layout:fixed;word-wrap:break-word;\">");
            out.println("<tbody><tr ><td colspan=\"2\">");
            out.println("I have read and agree to the terms and conditions listed on 1stchinavisa.com<br><br>");
            out.println("</td></tr><tr><td></td></tr><tr>");
            out.println("<td align=\"center\"><strong>Signature:____________________</strong></td><td align=\"center\">");
            out.println("<strong>Date:____________________</strong></td></tr></tbody></table></td></tr>");
            
            
            //end
            out.println("</form>");
            out.println("<table id=\"Table_03\" align=\"center\" width=\"680\" height=\"80\"><tbody><tr><td><div align=\"center\">");
           // out.println("<input onclick='location.href = \"onlinereg.html\"' type=\"button\" name=\"back\" value=\"Go Back\" />");
            out.println("<input type=\"button\" name=\"print\" onClick=\"window.print()\" value=\"Print This Form\" />");
            out.println("</div></td></tr></tbody></table> ");
            
            out.println("</body></html>");
			

		}


	}
}