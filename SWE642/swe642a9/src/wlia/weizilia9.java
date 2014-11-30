package wlia;

import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import java.io.*;

public class weizilia9 extends HttpServlet
{
	//given the links of all the pics
	private String pic0 = "http://mason.gmu.edu/~wlia/swe642a9pics/1.jpg";
	private String pic1 = "http://mason.gmu.edu/~wlia/swe642a9pics/2.jpg";
	private String pic2 = "http://mason.gmu.edu/~wlia/swe642a9pics/3.jpg";
	private String pic3 = "http://mason.gmu.edu/~wlia/swe642a9pics/4.jpg";
	private String pic4 = "http://mason.gmu.edu/~wlia/swe642a9pics/5.jpg";
	private String pic5 = "http://mason.gmu.edu/~wlia/swe642a9pics/6.jpg";
	private String pic6 = "http://mason.gmu.edu/~wlia/swe642a9pics/7.jpg";
	private String pic7 = "http://mason.gmu.edu/~wlia/swe642a9pics/8.jpg";
	private String pic8 = "http://mason.gmu.edu/~wlia/swe642a9pics/9.jpg";
	private String pic9 = "http://mason.gmu.edu/~wlia/swe642a9pics/10.jpg";
	private String pic10 = "http://mason.gmu.edu/~wlia/swe642a9pics/11.jpg";
	private String pic11 = "http://mason.gmu.edu/~wlia/swe642a9pics/12.jpg";
	private String pic12 = "http://mason.gmu.edu/~wlia/swe642a9pics/13.jpg";
	private String pic13 = "http://mason.gmu.edu/~wlia/swe642a9pics/14.jpg";
	private String pic14 = "http://mason.gmu.edu/~wlia/swe642a9pics/15.jpg";
	private String pic15 = "http://mason.gmu.edu/~wlia/swe642a9pics/16.jpg";
	private String pic16 = "http://mason.gmu.edu/~wlia/swe642a9pics/17.jpg";
	private String pic17 = "http://mason.gmu.edu/~wlia/swe642a9pics/18.jpg";
	private String pic18 = "http://mason.gmu.edu/~wlia/swe642a9pics/19.jpg";
	private String pic19 = "http://mason.gmu.edu/~wlia/swe642a9pics/20.jpg";
	
	//define a array contain all the pics
	private String []pics = {pic0,pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10
			,pic11,pic12,pic13,pic14,pic15,pic16,pic17,pic18,pic19};

	//here using doGet to generate mainpage
   public void doGet  (HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
   {
		res.setContentType("TEXT/HTML");
		PrintWriter out = res.getWriter();
		
		//loading html page from weizilia9.txt file
		File f = new File("/data/tomcat/swe64202/WEB-INF/classes/wlia/weizilia9.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String input = "";
		while((input = br.readLine()) != null){
			out.println(input);
		}
		out.close();
    }
   
   //here using doPost to update changes
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
   //get parameter from client-side
	String flag = request.getParameter("timeout");
	
   //generate a random number between 0 and 19
	int number = (int)(Math.random()*20);
	
	if(flag != null && flag.equals("yes")){
		
   //output a XML Doc for information passing
	response.setContentType("text/xml");
	PrintWriter out = response.getWriter();
	
   //detail of the XML file
	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
	out.println("<root>");
	
   //choose random element from array pics and extract the link from the node
		out.println("<url>" + pics[number] + "</url>");	
	out.println("</root> ");
	out.close();
		
	}
	}

}





