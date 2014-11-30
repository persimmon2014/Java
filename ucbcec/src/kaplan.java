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

public class kaplan extends HttpServlet {
   
	private String result;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("TEXT/HTML");
		PrintWriter out = response.getWriter();

		//id
	    String id = request.getParameter("id");
	    
		//name
	    String firstName = request.getParameter("firstname");
	    String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastname");
		String wholeName = firstName + "," + middleName + "," +lastName;
		
	    //home address
	    String hastreet = request.getParameter("hastreet");
	    String hacity = request.getParameter("hacity");
	    String haprovince = request.getParameter("haprovince");
	    String hazipcode = request.getParameter("hazipcode");
	    String hacountry = request.getParameter("hacountry");
	    String wholeHaAddress = hastreet + "," + hacity + "," +haprovince + "," + hazipcode + "," + hacountry;
	    
	    //email 
	    String email = request.getParameter("email");
	    
	    //date of birth
		String bmonth = request.getParameter("bmonth");
	    String bday = request.getParameter("bday");
	    String byear =  request.getParameter("byear");
	    String wholeBday = bmonth + "-" + bday + "-" + byear;
	    
	    //place of birth
	    String bcity = request.getParameter("bcity");
	    String bcountry = request.getParameter("bcountry");
	    String wholeBplace = bcity + "," + bcountry;
	    String wholeB = wholeBday + "; " + wholeBplace;
	    
	    //gender and marital status
	    String gender = request.getParameter("gender");
	    String marital = request.getParameter("marital");
	    	
	    //father info
	    String ffirstName = request.getParameter("ffirstName");
	    String fmiddleName = request.getParameter("fmiddleName");
		String flastName = request.getParameter("flastName");
		String fwholeName = ffirstName + " " + fmiddleName + " " +flastName;
	    String fstreet = request.getParameter("fstreet");
	    String fcity = request.getParameter("fcity");
	    String fprovince = request.getParameter("fprovince");
	    String fzipcode = request.getParameter("fzipcode");
	    String fcountry = request.getParameter("fcountry");
	    String wholeFAddress =fwholeName + "; " + fstreet + "," + fcity + "," +fprovince + "," + fzipcode + "," + fcountry;
	    
	    //mother info
	    String mfirstName = request.getParameter("mfirstName");
	    String mmiddleName = request.getParameter("mmiddleName");
		String mlastName = request.getParameter("mlastName");
		String mwholeName = mfirstName + "," + mmiddleName + "," +mlastName;
	    String mstreet = request.getParameter("mstreet");
	    String mcity = request.getParameter("mcity");
	    String mprovince = request.getParameter("mprovince");
	    String mzipcode = request.getParameter("mzipcode");
	    String mcountry = request.getParameter("mcountry");
	    String wholeMAddress = mwholeName + "; " + mstreet + "," + mcity + "," +mprovince + "," + mzipcode + "," + mcountry;
	    
	    //mailing info
	    String maname = request.getParameter("maname");
	    String mastreet = request.getParameter("mastreet");
	    String macity = request.getParameter("macity");
	    String maprovince = request.getParameter("maprovince");
	    String mazipcode = request.getParameter("mazipcode");
	    String macountry = request.getParameter("macountry");
	    String wholeMaAddress = maname + "; " + mastreet + "," + macity + "," +maprovince + "," + mazipcode + "," + macountry;
		
        //phone and citizenship
	    String telephone1 = request.getParameter("telephone1");
	    String citizenship = request.getParameter("citizenship");
		
		//high school info
	    String hsstreet = request.getParameter("hsstreet");
	    String hscity = request.getParameter("hscity");
	    String hsprovince = request.getParameter("hsprovince");
	    String hscountry = request.getParameter("hscountry");
	    String wholeHsAddress = hsstreet + "," + hscity + "," +hsprovince + "," + hscountry;
	    
	    //date of graduation and diploma info
		String gmonth = request.getParameter("gmonth");
	    String gyear =  request.getParameter("gyear");
	    String wholeGday = gmonth + "-" + gyear;
	    String diploma = request.getParameter("diploma");
	    String gradInfo = wholeGday + "; " + diploma;
	    
	    //awards and other info
	    String award = request.getParameter("award");
	    String ainame1 = request.getParameter("ainame1");
	    String ailocation1 = request.getParameter("ailocation1");
	    String aidate1 = request.getParameter("aidate1");
	    String wholeAiName1 = ainame1 + "," + ailocation1 + "," +aidate1;
	    String ainame2 = request.getParameter("ainame2");
	    String ailocation2 = request.getParameter("ailocation2");
	    String aidate2 = request.getParameter("aidate2");
	    String wholeAiName2 = ainame2 + "," + ailocation2 + "," +aidate2;
	    String ainame3 = request.getParameter("ainame3");
	    String ailocation3 = request.getParameter("ailocation3");
	    String aidate3 = request.getParameter("aidate3");
	    String wholeAiName3 = ainame3 + "," + ailocation3 + "," +aidate3;
	    
	    //program interest and begin date
	    String program = request.getParameter("program");
		String pmonth = request.getParameter("pmonth");
	    String pyear =  request.getParameter("pyear");
	    String wholePday = pmonth + "-" + pyear;
	    
	    
		   Connection conn=null;
		   Statement sm=null;
		   ResultSet result=null;
		   String drive="com.mysql.jdbc.Driver";
		   String url="jdbc:mysql://localhost:3306/ucbcec";
		   String user="root";
		   String password="root";
//		   String drive="com.mysql.jdbc.Driver";
//		   String url = "jdbc:mysql://mysql5.metawerx.net:3506/persimmon201?autoReconnect=true";
//		   String user="persimmon201";
//		   String password="PErS7922gQ";
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
		   if(email.length()>4)
		   {
		   ps = conn.prepareStatement("insert into kaplan-student" +
		   		"(student_id,student_name,home_address,email,birth_day&place,gender," +
		   		"marital_status,father_info,mother_info,mailing_info,telephone,citizenship,high_school_info," +
		   		"grad_info,award,ainame_1,ainame_2,ainame_3) values" +
		   		"('"+id+"','"+wholeName+"','"+wholeHaAddress+"','"+email+"'," +
		   				"'"+wholeB+"','"+gender+"','"+marital+"','"+wholeFAddress+"'," +
		   						"'"+wholeMAddress+"','"+wholeMaAddress+"','"+telephone1+"','"+citizenship+"','"+wholeHsAddress+"'," +
		   								"'"+gradInfo+"','"+award+"','"+wholeAiName1+"','"+wholeAiName2+"','"+wholeAiName3+"')");
		  
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
			/*
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
            
            out.println("</body></html>");*/
			

		}


	}
}