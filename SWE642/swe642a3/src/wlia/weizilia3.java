package wlia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class weizilia3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public weizilia3() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("TEXT/HTML");
		PrintWriter out = response.getWriter();
		File f = new File("/data/tomcat/swe64202/WEB-INF/classes/wlia/sample.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String input = "";
		while((input = br.readLine()) != null){
			out.println(input);
		}
		out.close();
	}

}
