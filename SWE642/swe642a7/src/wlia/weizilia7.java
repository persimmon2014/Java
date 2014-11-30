package wlia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class weizilia7 extends HttpServlet {
	
	private String smile = "<img src=\"http://mason.gmu.edu/~wlia/images/smile.gif\" width=\"20\" height=\"20\" alt=\":)\" title=\"smile\" >";
	private String frown = "<img src=\"http://mason.gmu.edu/~wlia/images/frown.gif\" width=\"20\" height=\"20\" alt=\":(\" title=\"frown\" >";
	private String wink = "<img src=\"http://mason.gmu.edu/~wlia/images/wink.gif\" width=\"20\" height=\"20\" alt=\";)\" title=\"wink\" >";
	private String angry = "<img src=\"http://mason.gmu.edu/~wlia/images/angry.gif\" width=\"20\" height=\"20\" alt=\":x\" title=\"angry\" >";
	private String cry = "<img src=\"http://mason.gmu.edu/~wlia/images/cry.gif\" width=\"20\" height=\"20\" alt=\":cry:\" title=\"cry\" >";
	private String dizzy = "<img src=\"http://mason.gmu.edu/~wlia/images/dizzy.gif\" width=\"20\" height=\"20\" alt=\":@\" title=\"dizzy\" >";
	private String laugh = "<img src=\"http://mason.gmu.edu/~wlia/images/laugh.gif\" width=\"20\" height=\"20\" alt=\":D\" title=\"laugh\" >";
	private String sleep = "<img src=\"http://mason.gmu.edu/~wlia/images/sleep.gif\" width=\"20\" height=\"20\" alt=\":sleep:\" title=\"sleep\" >";
	private String surprised = "<img src=\"http://mason.gmu.edu/~wlia/images/surprised.gif\" width=\"20\" height=\"20\" alt=\":o\" title=\"surprised\" >";
	private String vomit = "<img src=\"http://mason.gmu.edu/~wlia/images/vomit.gif\" width=\"20\" height=\"20\" alt=\":vomit:\" title=\"vomit\" >";
	
	private String mServlet = "http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.weizilia7";
	
	private String result;
	private ArrayList<String> stringStore = new ArrayList<String>();
	private ArrayList<String> stringStoreOut = new ArrayList<String>();
	private ArrayList<String> userName = new ArrayList<String>();
	private ArrayList<String> userNameOut = new ArrayList<String>();
	private ArrayList<String> chathistoryIn = new ArrayList<String>();
	private ArrayList<String> chathistoryOut = new ArrayList<String>();
	private String logoutUser = "";
	String curUser = "";

	private int inSystem = 0;
	private int logintimes = 0;
   

    public weizilia7() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter ("action");
		String logout = request.getParameter("logout");
		String login = request.getParameter("updataName");
		String chathistory = request.getParameter("chathistory");
		
		ServletContext servContext = getServletContext();
	
		   if(chathistory!=null)
		   {
			      HttpSession session = request.getSession ();
			    
			      response.setContentType ("text/html");
		          PrintWriter out = response.getWriter ();
		          
		          if(curUser.length()<1)
		          {
		        	  session.invalidate();
		        	  out.println ("<HTML>");
		  		      out.println ("<br>You should log in first!");	      
		  		      out.println ("<br><A HRef=\"" + mServlet + "?action=newSession\">");
		  		      out.println ("<br>Go back to discussion blackboard</A>");
		  		      out.println ("</HTML>");
		  		      out.close(); 
		          }
		          else
		          {
		        	  out.println ("<HTML>");
				      out.println("<br>");
				      out.println("Chat history:");
				      out.println("<br><br>");
				     // out.println ("<BR>Created Time: ");
				     // out.println (new Date (session.getCreationTime()));    
				      inSystem = 0;
				      logintimes = 0;
				      //chathistoryOut = (ArrayList)servContext.getAttribute("wliachathistory");
				      for(int i=0;i<chathistoryIn.size();i++)
				      {
				    	  if(chathistoryIn.get(i).startsWith("User: "+curUser))
				    	  {
				    		  out.println(chathistoryIn.get(i));
				    		  out.println("<br>");
				    		  inSystem = 1;
				    		  logintimes++;
				    	  }
				      }
				      if(inSystem == 0)
				      {
				    	  out.println("This is your first time here. So, there is no records of your previous chat history.");
				      }
				      else
				      { 
				    	  out.println("<br>");
				    	  out.println("Total logged in times: "+logintimes);
				      }
				      out.println("<form Action=\""+mServlet+"\"NAME=\"myForm\" >");
				      out.println("<br>");
				      out.println("<input name = \"goback\" type=\"submit\" value=\"Go back\"> ");			
					  out.println("</form>");
				      out.println ("</HTML>");
				      out.close();
		          }
			      
		   }
		
		
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
					if (result.length()>20) //if the result is a warning sentence, invalidate current session and allow user to go back, and enter the name again
					{
						HttpSession session = request.getSession ();
			            session.invalidate();
						out.println("<html>");
						out.println("<br>");
				        out.println(result);    
					    out.println ("<br><A HRef=\"" + mServlet + "?action=newSession\">");
					    out.println ("<br>Go back to discussion blackboard</A>");
					    out.println ("</HTML>");
				        
					}
					else //if it's a valid name, store the name and display it on proper place
					{
						String currentUser = request.getParameter("name");					
						servContext.setAttribute("wliaUsera7", currentUser);
						curUser = (String)servContext.getAttribute("wliaUsera7");
						userName.add(curUser);
	
						out.println("<html>");
						out.println("<br>");
				        out.println("Congratulations! ");
				        out.println(result);
				        out.println(", you successfully registered!");
				    
				        out.println("<br>");
				        out.println("<form Action=\""+mServlet+"\"NAME=\"myForm\" >");
				        out.println("<br>");
						out.println("<input name = \"continue\" type=\"submit\" value=\"continue\"> ");
				
						out.println("</form>");
					    out.println ("</HTML>");
					    
					    inSystem = 0;
					}
		   }
		   else//if the login and logout buttons don't pressed, display chat content and other info
		   {  
		      HttpSession session = request.getSession();
		      response.setContentType ("text/html");
	          PrintWriter out = response.getWriter ();
		

	            
				out.println("<html>");
				out.println("<HEAD>");
				out.println("<TITLE> course </TITLE>");
				out.println("<script language=\"JavaScript\">");
				out.println("function insert_text(text, spaces, popup)");
				out.println("{");
				out.println("var textarea;");
				out.println("text = \" \"+text+\" \";");
				out.println("textarea  = document.getElementById(\"chat\").value;");
				out.println("document.getElementById(\"chat\").value = textarea+text;");
				out.println("}");
				out.println("</script>");
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
		        out.println("<FORM Action=\""+mServlet+"\"NAME=\"myForm\" >");
		        out.println("User Name:");
				out.println("<input type=\"text\" id=\"name\" name=\"name\" size=\"20\" >");
				out.println("<input name = \"updataName\" type=\"submit\" value=\"Log in\"> ");
				out.println("</form>");
				
				 
				String tempChatHistory ="User: " +curUser + " - Previous logged in time:" + (new Date (session.getCreationTime()));
			    chathistoryIn.add(tempChatHistory);
			    servContext.setAttribute("wliachathistory", chathistoryIn);
				
		      }
		      else //if it's not a new session, display the chat contents and other staff
		      {
		        //out.println ("Old Session.");
		        out.println("<br>");
		        out.println("<br>");	        
		        out.println("<FORM Action=\""+mServlet+"\"NAME=\"myForm\" >");
				out.println("Hello: "+curUser);
				out.println("<br>");
				out.println("<br>");
				out.println("Dialogue:");
				out.println("<input type=\"text\" id=\"chat\" name=\"chat\" size=\"40\" >");
				out.println("<input name = \"updateChat\" type=\"submit\" value=\"submit\"> ");
				out.println("</form>");

				//print out smilies
				printSmilies(out);

				out.println("<br>");
			    out.println("Chat contents:");
			    out.println("<br>");
				out.println("---------------------------------------");
				out.println("<br>");
				
				
				//only display the most recent ten messages.
				if(request.getParameter("chat")!=null)
				{
					String tempString = request.getParameter("chat");
					
					//if the chat content only has one character
					if(tempString.length() < 2)
					{
						stringStore.add(curUser+": "+tempString);
					}
					else
					{
					
				        tempString = checkSmilies(tempString);
					    stringStore.add(curUser+": "+tempString);
					}
					
									
					servContext.setAttribute("wliaStringa7", stringStore);				
					stringStoreOut = (ArrayList)servContext.getAttribute("wliaStringa7");				
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
			  out.println("<br><br>");
			  out.println("<form Action=\""+mServlet+"?action=invalidate\" NAME=\"myForm\" Method=\"get\">");	
			  out.println("<input name = \"chathistory\" type=\"submit\" value=\"Display chat history\"> ");
		 	  out.println("<input name = \"logout\" type=\"submit\" value=\"Log out\"> ");
		 	  out.println("</form>");
		      out.println ("</BODY>");
		      out.println ("</HTML>");
		      out.close();
		   } 
//		   else if(inSystem == 1)
//		   {
//			   HttpSession session = request.getSession();
//			   response.setContentType ("text/html");
//		       PrintWriter out = response.getWriter ();
//		       session.invalidate();
//		       
//		        out.println("<html>");
//				out.println("<HEAD>");
//				out.println("<TITLE> course </TITLE>");
//				out.println("<script language=\"JavaScript\">");
//				out.println("function insert_text(text, spaces, popup)");
//				out.println("{");
//				out.println("var textarea;");
//				out.println("text = \" \"+text+\" \";");
//				out.println("textarea  = document.getElementById(\"chat\").value;");
//				out.println("document.getElementById(\"chat\").value = textarea+text;");
//				out.println("}");
//				out.println("</script>");
//				out.println("</HEAD>");	
//				out.println("<BODY>");
//				out.println("My name is Weizi Li. And this page is for swe642 sec2.");
//				out.println("<br>");
//				out.println("<br>");
//				out.println("Discussion Blackboard");
//				out.println("<br>");
//		       
//		      	
//			        out.println("<br>");
//			        out.println("<br>");
//			        out.println("<FORM Action=\""+mServlet+"\"NAME=\"myForm\" >");
//			        out.println("User Name:");
//					out.println("<input type=\"text\" id=\"name\" name=\"name\" size=\"20\" >");
//					out.println("<input name = \"updataName\" type=\"submit\" value=\"Log in\"> ");
//			
//					  out.println("</form>");
//					  out.println("<br>");
//				      out.println("<br>");
//				      out.println("<br>");
//				      out.println("Recent visitors:");
//				      out.println("<br>");
//				      out.println("---------------------------------------");
//					  out.println("<br>");
//					  out.println("<br><br>");
//					  out.println("<form Action=\""+mServlet+"?action=invalidate\" NAME=\"myForm\" Method=\"get\">");	
//					  out.println("<input name = \"chathistory\" type=\"submit\" value=\"Display chat history\"> ");
//				 	  out.println("<input name = \"logout\" type=\"submit\" value=\"Log out\"> ");
//				 	  out.println("</form>");
//				      out.println ("</BODY>");
//				      out.println ("</HTML>");
//				      out.close();
//			
//			   
//		   }
	}
	
	
	//check if the chat string contains any smilies
	public String checkSmilies(String value)
	{
		if(value.contains(":)"))
			value = value.replaceAll(":\\)", smile);
		if(value.contains(":-)"))
			value = value.replaceAll(":-\\)", smile);
		if(value.contains(":("))
			value = value.replaceAll(":\\(", frown);
		if(value.contains(":-("))
			value = value.replaceAll(":-\\(", frown);
		if(value.contains(";)"))
			value = value.replaceAll(";\\)", wink);
		if(value.contains(";-)"))
			value = value.replaceAll(";-\\)", wink);
		if(value.contains(":x"))
			value = value.replaceAll(":x", angry);
		if(value.contains(":cry:"))
			value = value.replaceAll(":cry:", cry);
		if(value.contains(":@"))
			value = value.replaceAll(":@", dizzy);
		if(value.contains(":D"))
			value = value.replaceAll(":D", laugh);
		if(value.contains(":sleep:"))
			value = value.replaceAll(":sleep:", sleep);
		if(value.contains(":o"))
			value = value.replaceAll(":o", surprised);
		if(value.contains(":vomit:"))
			value = value.replaceAll(":vomit:", vomit);
				
		return value;
	}
	
	
	//print out smilies
	public void printSmilies(PrintWriter out)
	{
		out.println("Smilies:");
		out.println("<br>");
		out.println("<div id=\"smiley-box\"> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':)', true); return false;\">"+smile+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':(', true); return false;\">"+frown+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(';)', true); return false;\">"+wink+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':x', true); return false;\">"+angry+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':cry:', true); return false;\">"+cry+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':@', true); return false;\">"+dizzy+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':D', true); return false;\">"+laugh+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':sleep:', true); return false;\">"+sleep+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':o', true); return false;\">"+surprised+"</a> ");
		out.println("<a href=\"#\" onclick=\"insert_text(':vomit:', true); return false;\">"+vomit+"</a> ");					
		out.println("</div> ");
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