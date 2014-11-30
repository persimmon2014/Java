package wlia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class weizilia6 extends HttpServlet {
	private String result;
	private ArrayList<String> stringStore = new ArrayList<String>();
	private ArrayList<String> stringStoreOut = new ArrayList<String>();
	private ArrayList<String> userName = new ArrayList<String>();
	private ArrayList<String> userNameOut = new ArrayList<String>();
	private String logoutUser = "";
	String curUser = "";

   

    public weizilia6() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter ("action");
		String logout = request.getParameter("logout");
		String login = request.getParameter("updataName");
		
		ServletContext servContext = getServletContext();
	
		
		
           //if logout button is pressed, invalidate current session and display thank you info
		   if(logout!=null)
		   { 
		      HttpSession session = request.getSession ();
		      session.invalidate();

		      //flag the most recent log out user's position
		      logoutUser = curUser;
		      
		      response.setContentType ("text/html");
	          PrintWriter out = response.getWriter ();

		      out.println ("<HTML>");
		      out.println ("<br>Thank you for your visiting!");
		      String mServlet = "http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia6";
		      out.println ("<br><A HRef=\"" + mServlet + "?action=newSession\">");
		      out.println ("<br>Go back to discussion blackboard</A>");
		      out.println ("</HTML>");
		      out.close();
		   } 
		   else if(login!=null) //if login button pressed, check the user name first, allow user to login if the name is valid  
		   {
			   
			   response.setContentType ("text/html");
		       PrintWriter out = response.getWriter ();
			   result = checkUserName(request.getParameter("name"));
					if (result.length()>10) //if the result is a warning sentence, invalidate current session and allow user to go back, and enter the name again
					{
						HttpSession session = request.getSession ();
			            session.invalidate();
						out.println("<html>");
						out.println("<br>");
				        out.println(result);    
					    String mServlet = "http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia6";
					    out.println ("<br><A HRef=\"" + mServlet + "?action=newSession\">");
					    out.println ("<br>Go back to discussion blackboard</A>");
					    out.println ("</HTML>");
				        
					}
					else //if it's a valid name, store the name and display it on proper place
					{
						String currentUser = request.getParameter("name");
						
						servContext.setAttribute("wliaUser", currentUser);
						curUser = (String)servContext.getAttribute("wliaUser");
						userName.add(curUser);
	
						out.println("<html>");
						out.println("<br>");
				        out.println("Congratulations! ");
				        out.println(result);
				        out.println(", you successfully registered!");
				    
				        out.println("<br>");
				        out.println("<FORM Action=\"http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia6\" NAME=\"myForm\" >");
				        out.println("<br>");
						out.println("<input name = \"continue\" type=\"submit\" value=\"continue\"> ");
				
						out.println("</form>");
					    out.println ("</HTML>");
					}
		   }
		   else //if the login and logout buttons don't pressed, display chat content and other info
		   {  
		      HttpSession session = request.getSession();
		      response.setContentType ("text/html");
	          PrintWriter out = response.getWriter ();
		

	            
				out.println("<html>");
				out.println("<HEAD>");
				out.println("<TITLE> course </TITLE>");
				out.println("</HEAD>");	
				out.println("<BODY>");
				out.println("My name is Weizi Li. And this page is for swe642 sec2.");
				out.println("<br>");
				out.println("<br>");
				out.println("Discussion Blackboard");
				out.println("<br>");
//				out.println("<br>");
//		        out.println("Session Status: ");
		      
				
			  //if the session is new, create login webpage and allow user to enter their name	
		      if (session.isNew())
		      {	    	  
		    	//out.println ("New Session.");
		        out.println("<br>");
		        out.println("<br>");
		        out.println("<FORM Action=\"http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia6\" NAME=\"myForm\" >");
		        out.println("User Name:");
				out.println("<input type=\"text\" id=\"name\" name=\"name\" size=\"20\" >");
				out.println("<input name = \"updataName\" type=\"submit\" value=\"Log in\"> ");
		
				out.println("</form>");
		      }
		      else //if it's not a new session, display the chat contents and other staff
		      {
		        //out.println ("Old Session.");
		        out.println("<br>");
		        out.println("<br>");
	
		        out.println("<FORM Action=\"http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia6\" NAME=\"myForm\" >");
				
				out.println("Hello: "+curUser);
				out.println("<br>");
				out.println("<br>");
				out.println("Dialogue:");
				out.println("<input type=\"text\" id=\"chat\" name=\"chat\" size=\"20\" >");
				out.println("<input name = \"updateChat\" type=\"submit\" value=\"submit\"> ");
				out.println("</form>");
				out.println("<br>");
			    out.println("Chat contents:");
			    out.println("<br>");
				out.println("---------------------------------------");
				out.println("<br>");
				
				
				//only display the most recent ten messages.
				if(request.getParameter("chat")!=null)
				{
					String tempString = request.getParameter("chat");
					
					stringStore.add(curUser+": "+tempString);
					
					servContext.setAttribute("wliaString", stringStore);
					
					stringStoreOut = (ArrayList)servContext.getAttribute("wliaString");
					
					if(stringStoreOut.size()<10)
					{
					for(int i=0;i<stringStoreOut.size();i++)
					{
						  out.println (stringStoreOut.get(i));
					      out.print   ("<br>");
					}
					}
					else
					{
						for(int i=stringStoreOut.size()-10; i<stringStoreOut.size();i++)
						{
							out.println (stringStoreOut.get(i));
						    out.print   ("<br>");
						}
					}
					

				}
					
				
		      }

		      String mServlet = "http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia6";
		      out.println("<br>");
		      out.println("<br>");
		      out.println("<br>");
		      out.println("Recent visitors:");
		      out.println("<br>");
		      out.println("---------------------------------------");
			  out.println("<br>");
			  
			  //only output the most recent three visitor's name
			  if(userName.size()<3)
				{
				for(int i=0;i<userName.size();i++)
				{
					if(userName.get(i) == logoutUser)
					{
						out.println (userName.get(i)+" - Just logged out.");
					    out.print   ("<br>");
					}
					else
					{
					  out.println (userName.get(i));
				      out.print   ("<br>");
					}
				}
				}
				else
				{
					for(int i=userName.size()-3; i<userName.size();i++)
					{
						if(userName.get(i) == logoutUser)
						{
							out.println (userName.get(i)+" - Just logged out.");
						    out.print   ("<br>");
						}
						else
						{
						  out.println (userName.get(i));
					      out.print   ("<br>");
						}
					}
				}
			  
			  //output the "Log out" button
		 	  out.println("<form Action=\"http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia6?action=invalidate\" NAME=\"myForm\" Method=\"get\">");	 		
		 	  out.println("<BR><BR><input name = \"logout\" type=\"submit\" value=\"Log out\"> ");
		 	  out.println("</form>");
		      out.println ("</BODY>");
		      out.println ("</HTML>");
		      out.close();
		   } 
	}
	
	
	///check the user name
	public String checkUserName(String value)
	{
		String rs = "";
		
		
		if (value == null || value.equals("")) //if it is empty, return warning
			rs = "User name shoud not be empty. Please enter again.";
		else if(value.length()<4) //if it is too short, return warning
		{
			rs = "User name should be at least 4 characters. Please enter again.";
		}
		else if (value.indexOf('!') != -1 || value.indexOf('@') != -1
				|| value.indexOf('#') != -1 || value.indexOf('$') != -1
				|| value.indexOf('%') != -1 || value.indexOf('^') != -1
				|| value.indexOf('&') != -1 || value.indexOf('*') != -1
				|| value.indexOf('{') != -1 || value.indexOf('}') != -1
				|| value.indexOf('=') != -1 || value.indexOf('[') != -1 || value.indexOf('_') != -1
				|| value.indexOf('-') != -1 || value.indexOf('+') != -1
				|| value.indexOf('/') != -1 || value.indexOf('.') != -1
				|| value.indexOf(',') != -1 || value.indexOf('|') != -1
				|| value.indexOf('<') != -1 || value.indexOf('>') != -1
				|| value.indexOf('?') != -1 || value.indexOf(';') != -1
				|| value.indexOf('~') != -1) //if it contains special character, return warning
		{
			rs = "User name contains special characters. Please enter again.";
		}
		else if(value.codePointAt(0)<65 || value.codePointAt(0)>90) //the first character should be capitalized
		{
			rs = "The first character should be capitalized. Please enter again.";
		}
		else
		{
			//again make sure the first character is capitalized.
			rs = value;
			String rs1 = rs.substring(0, 1);	
			rs = (rs1.toUpperCase()+rs.substring(1,rs.length()));
		}
		
		
		return rs;
	}
	

}