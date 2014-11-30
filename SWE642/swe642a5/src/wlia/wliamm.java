package wlia;
// Designed and built by Alisha Sandhwani and Jeff Offutt
// July 2010

///////////////////////////////
///Modifed by Weizi Li
///////////////////////////////


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class wliamm extends HttpServlet
{
	//Default variables
    private int num = 2;  //defined to control the background of each role
    private String[] answer = new String[4]; //create string array for the answer
    private wliahole[] answerhole = new wliahole[4];  //create four holes for display the guess and the answer
    private ArrayList<wliarow> list = new ArrayList<wliarow>();  //create a arraylist to display each row
   
    //Background color
    private String blueBack = "#90EE90";
    private String greenBack = "#6495ED";
   
	//Servlet address
	private String servlet = "http://apps-swe642.ite.gmu.edu:8080/swe64202/servlet/wlia.wliamm";
	
	//Big hole pictures collection
	private String pink = "<img src=\"http://t3.gstatic.com/images?q=tbn:YQ6TTvFUEj4QKM:b\" height=\"54\"" +
			" width=\"54\" alt=\"h1\">";
	private String orange = "<img src=\"http://www.labarrenutrition.com/yahoo_site_admin/assets/images/sun-orange-circle_1_.490136.gif\"" +
			" height=\"54\" width=\"54\" alt=\"h1\">";
	private String yellow = "<img src=\"http://t0.gstatic.com/images?q=tbn:ANd9GcS6RwFCbX6y4k-Y-6_U0DNUsc1MmXCgAPamUZWwQnmAoJAkvQk&t=1&usg=__UyLPpEq_T-276ftEABUTrZwoLVw=\"" +
			" height=\"54\" width=\"54\" alt=\"h1\">";
	private String green = "<img src=\"http://t3.gstatic.com/images?q=tbn:ANd9GcRranhz588gZE2bjKEdV0z0GwfBkYOzQFrUTWPSHzbKSeK3z_c&t=1&usg=__oHJg5sq1aFswT7uI-ECYLWDBRz4=\"" +
			" height=\"54\" width=\"54\" alt=\"h1\">";
	private String blue = "<img src=\"http://mammafoo.com/images/Blue%20Circle/Blue%20Circle.1204020872.jpg\"" +
			" height=\"54\" width=\"54\" alt=\"h1\">";
	private String purple = "<img src=\"http://t3.gstatic.com/images?q=tbn:ANd9GcR9HZjn4Luy29Kgu5k-U7oagZAX7Rfksng7RG4eM5qLvpCAdbc&t=1&usg=__W4VcSgxIXXFvMZ9jnmwUOWZGqLw=\"" +
			" height=\"54\" width=\"54\" alt=\"h1\">";
	private String Black = "<img src=\"http://studentweb.cortland.edu/louge32/miniproj2/step3_circle.gif\" " +
	"height=\"27\" width=\"27\" alt=\"h1\">";
	
	//Small hole pictures collection
	private String red = "<img src=\"http://www.layoutlocator.com/graphics/dldimg/f5e9efdef56d8d132b4b87749dd753b2_a15f926a7ae3edd0a5c2096d775ac902.gif\" " +
			"height=\"27\" width=\"27\" alt=\"h1\">";
	private String gray = "<img src=\"http://www.dardanus.com/circle_grey.gif\" " +
	"height=\"27\" width=\"27\" alt=\"h1\">";
	private String black = "<img src=\"http://studentweb.cortland.edu/louge32/miniproj2/step3_circle.gif\" " +
	"height=\"27\" width=\"27\" alt=\"h1\">";


   //Initialize some default variables
   public void Initialize()
   {
	      num = 2;  //defined to control the background of each role
	      //creating the answer array
	      String[] choices = new String[6];
	      choices[0] = "pink";
	      choices[1] = "orange";
	      choices[2] = "yellow";
	      choices[3] = "green";
	      choices[4] = "blue";
	      choices[5] = "purple";
	      for (int x=0; x<4; x++)
	      {
	         int a = (int)(Math.random()*6); //use random number to determine the answer
	         answer[x] = choices[a];
	      }
	      for (int x=0; x<4; x++)
	      {
	         answerhole[x] = new wliahole (answer[x],20); //create hole to display the answer
	      }
	      while (list.size() != 0) //making sure rows are not left over from the previous game
	      {
	         list.remove (list.size()-1);//if there are left rows from the last game, clear it
	      }
   
   }


   public void doGet (HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
   {
	  //initialize some internal value
	   Initialize();

      //start to write the html page
      res.setContentType ("text/html");
      PrintWriter out = res.getWriter();
      File f = new File("/data/tomcat/swe64202/WEB-INF/classes/wlia/mainpage.txt"); //html page info are wrote in mainpage.txt
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String input = "";
		while((input = br.readLine()) != null){
			out.println(input);
		}
		out.close();
   }

   public void doPost (HttpServletRequest req, HttpServletResponse res)
           throws ServletException, IOException
   {
      res.setContentType ("text/html");
      PrintWriter out = res.getWriter();
      
      
      if (req.getParameter ("restart") != null) //if the player wants to restart
      {
         while (list.size() != 0) //making sure rows are not left over from the previous game
         {
            list.remove (list.size()-1);
         }
         doGet (req,res);//restart the game
      }
      else
      {
      //if the person gave up
         if (req.getParameter ("giveup") != null)
         {
            out.println ("<html>");
            out.println ("<head>");
            out.println ("</head>");
            out.println ("<center>");
            out.println ("<body>");
            out.println ("The game of Mastermind: <br>");
            // out.println ("<form METHOD = \"POST\" ACTION = \"http://cs.gmu.edu:8080/offutt/servlet/mastermind642.mm\">");
            out.println ("<form METHOD = \"POST\" ACTION = \""+servlet+"\">");
            out.println ("<table width=\"480\" height=\"108\" border=\"1\">");

            //revealing the answer
            out.println ("<tr height=\"54\" bgcolor=\"gray\">");
            out.println ("<td >");
            out.println ("</td>");
            out.println ("<td  width=\"320\">");
            for (int x=0; x<3; x++)
            {
               out.println (displayHole(answerhole[x])+ "&nbsp; &nbsp; &nbsp;");
            }
            out.println(displayHole(answerhole[3]));
            out.println ("</td>");
            out.println ("<td>");
            out.println ("</td>");
            out.println ("</tr>");

            //displaying the rows played
            for (wliarow r:list)
            {
            	out.println(displayRow(r));
            }
            out.println ("<h1><center>SORRY YOU LOSE! </h1></center>");
            out.println ("<tr>");
            out.println ("<td></td>");
            //out.println ("<td><center><input type=\"submit\" name=\"giveup\" value=\"Give Up?\" disabled=\"disabled\"></center></td>");
            out.println ("<td><center><input type=\"submit\" name=\"giveup\" value=\"Give Up?\" disabled=\"disabled\"><input type=\"submit\" name=\"restart\" value=\"Replay!\"></center></td>");
            out.println ("</tr>");
            out.println ("</table>");
            out.println ("</form>");
            out.println ("</center>");
            out.println ("</body>");
            out.println ("</html>");
         }
         else //if the player has not given up
         {
            //take in input colors-->create a new row and add it to the arraylist
            String[] input=new String[4]; //reads in the values
            input[0]=req.getParameter ("hole1");
            input[1]=req.getParameter ("hole2");
            input[2]=req.getParameter ("hole3");
            input[3]=req.getParameter ("hole4");

            wliarow row=new wliarow (answer,input,num); //adds to ArrayList of rows
            list.add (row);

            out.println ("<html>");
            out.println ("<head>");
            out.println ("</head>");
            out.println ("<center>");
            out.println ("<body>");
            out.println ("The game of Mastermind: <br>");
            out.println("You have guessed: "+list.size()+" times!");
            // out.println ("<form METHOD = \"POST\" ACTION = \"http://cs.gmu.edu:8080/offutt/servlet/mastermind642.mm\">");
            out.println ("<form METHOD = \"POST\" ACTION = \""+servlet+"\">");
            out.println ("<table width=\"480\" height=\"108\" border=\"1\">");

            if (row.isWinner()==false) //if they did not win
            {
               out.println ("<tr height=\"54\" bgcolor=\"gray\">");
               out.println ("<td >");
               out.println ("</td>");
               out.println ("<td  width=\"320\">");
               out.println ("</td>");
               out.println ("<td>");
               out.println ("</td>");
               out.println ("</tr>");
            //out.println (row.won());
            }
            else //if they win, displaying the answer
            {
               out.println ("<tr height=\"54\" bgcolor=\"gray\">");
               out.println ("<td >");
               out.println ("</td>");
               out.println ("<td  width=\"320\">");
               for (int x=0; x<3; x++)
               {
            	   out.println(displayHole(answerhole[x])+ "&nbsp; &nbsp; &nbsp;");
               }
               out.println(displayHole(answerhole[3]));
               out.println ("</td>");
               out.println ("<td>");
               out.println ("</td>");
               out.println ("</tr>");
            }

            //prints out the the rows already played
            for (wliarow r:list)
            {
            	out.println(displayRow(r));
            }
            //final row that's new (if the player has not won), allowing the player to choose the next move.
            num++;
            if (num%2==0)
            {
               out.println ("<tr bgcolor=\""+greenBack+"\">");
            }
            else
            {
            	out.println ("<tr bgcolor=\""+blueBack+"\">");
            }

            if (row.isWinner()==false)
            {
               out.println ("<td height=\"54\"  align=\"center\" bgcolor=\"white\">");
			   out.println(black);
			   out.println(black);
			   out.println("<br>");
			   out.println(black);
			   out.println(black);
               out.println ("</td>");
               out.println ("<td height=\"54\" width=\"320\">");
               out.println ("<SELECT NAME=\"hole1\">");
               out.println ("<OPTION>pink</option>");
               out.println ("<OPTION>orange</option>");
               out.println ("<OPTION>yellow</option>");
               out.println ("<OPTION>green</option>");
               out.println ("<OPTION>blue</option>");
               out.println ("<OPTION>purple</option>");
               out.println ("</select>");
               out.println ("<SELECT NAME=\"hole2\">");
               out.println ("<OPTION>pink</option> ");
               out.println ("<OPTION>orange</option>");
               out.println ("<OPTION>yellow</option>");
               out.println ("<OPTION>green</option> ");
               out.println ("<OPTION>blue</option>");
               out.println ("<OPTION>purple</option>");
               out.println ("</select>");
               out.println ("<SELECT NAME=\"hole3\">");
               out.println ("<OPTION>pink</option> ");
               out.println ("<OPTION>orange</option>");
               out.println ("<OPTION>yellow</option>");
               out.println ("<OPTION>green</option> ");
               out.println ("<OPTION>blue</option>");
               out.println ("<OPTION>purple</option>");
               out.println ("</select>");
               out.println ("<SELECT NAME=\"hole4\">");
               out.println ("<OPTION>pink</option> ");
               out.println ("<OPTION>orange</option>");
               out.println ("<OPTION>yellow</option>");
               out.println ("<OPTION>green</option> ");
               out.println ("<OPTION>blue</option>");
               out.println ("<OPTION>purple</option>");
               out.println ("</select>");
               out.println ("</td>");
               out.println ("<td>");
               out.println ("<input type=\"submit\" value=\"guess\" >");
               out.println ("</td>");
               out.println ("</tr>");
               out.println ("<tr>");
               out.println ("<td></td>");
               out.println ("<td><center><input type=\"submit\" name=\"giveup\" value=\"Give Up?\" ><input type=\"submit\" name=\"restart\" value=\"Replay!\"></center></td>");
               out.println ("</tr>");
            }
            else //if the player has won-say "congrats!"
            {
               out.println ("<h1><center>CONGRATS! YOU WON! </br></h1></center>");
            
               out.println ("<tr>");
               out.println ("<td></td>");
               out.println ("<td><center><input type=\"submit\" name=\"giveup\" value=\"Give Up?\" disabled=\"disabled\"><input type=\"submit\" name=\"restart\" value=\"Replay!\"></center></td>");
               out.println ("</tr>");
            }
            out.println ("</table>");

            out.println ("</form>");
            out.println ("</center>");
            out.println ("</body>");
            out.println ("</html>");
         }
         out.close();
      }
   }
   
    //re-write function in wliarow.java, for displaying each row.
	private String displayRow(wliarow row){
		wliahole[] eval= row.getEval();
		wliahole[] guess = row.getGuess();
		String background = row.getBackground();
		String ret = "<tr bgcolor=\""+background +"\">";
	      ret = ret+ "<td height=\"54\"  align=\"center\" bgcolor=\"white\"> "; 
	      
	      //for display small holes the eval
	      for (int x=3; x>=0; x--)
	      {
	         if (eval[x]!=null)
	            ret = ret+displayHole(eval[x]);
	         else
	            ret = ret+black;
	         if (x==2)
	            ret = ret+"<br>";
	      }
	      ret = ret+"</td>";
	      ret = ret+"<td height=\"54\" width=\"320\">&nbsp; &nbsp;";
	      
	      //for display guess result the big holes
	      for (int x=0; x<3; x++)
	      {
	         ret = ret+displayHole(guess[x])+ "&nbsp; &nbsp; &nbsp;";
	      }
	      ret = ret+displayHole(guess[3]);
	      ret = ret+"</td><td><input type=\"submit\" value=\"guess\" disabled=\"disabled\"></td></tr>"; //diabled "guess" button
	      return ret;
	}
	

	//re-write function in wliahole.java, for displaying both small and big holes.
	private String displayHole(wliahole hole){
		String color = hole.getColor();
		int size = hole.getSize();
		
		//if it's a small hole
		if (size == 1)
	      {
	         if (color.equals ("red"))
	            return red ;
	         else if (color.equals ("gray"))
	            return gray ;
	         else
	            return black;
	      }
	      else //if it's a big hole
	    	  {
	         if (color.equals ("pink"))
	            return pink;
	         else if (color.equals ("orange"))
	            return orange;
	         else if (color.equals ("yellow"))
	            return yellow;
	         else if (color.equals ("green"))
	            return green;
	         else if (color.equals ("blue"))
	            return blue;
	         else if (color.equals ("purple"))
	            return purple;
	         else
	            return Black;

	      }
	}
}