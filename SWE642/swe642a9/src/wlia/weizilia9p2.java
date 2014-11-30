package wlia;
//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

//Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

public class weizilia9p2 extends HttpServlet
{

   public void processRequest (HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
   {
//      res.setContentType ("TEXT/HTML");
      PrintWriter out = res.getWriter ();

      String Nm  = req.getParameter ("StringSoFar");
      out.print ("Sorry, I have no suggestions: <FONT COLOR=green>");
      out.print (Nm);
      out.print ("</FONT>\n");

      out.close ();
   }

    // Method doPost - just calls processRequest
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       processRequest (request, response);
    }

    // Method doGet - just calls processRequest
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       processRequest (request, response);
    }

}