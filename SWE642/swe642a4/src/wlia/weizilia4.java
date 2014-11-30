package wlia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class weizilia4 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String result;

	public weizilia4()
	{
		super();

	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("TEXT/HTML");
		PrintWriter out = response.getWriter();
		File f = new File("/data/tomcat/swe64202/WEB-INF/classes/wlia/sample.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String input = "";
		while ((input = br.readLine()) != null)
		{
			out.println(input);
		}
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("TEXT/HTML");
		PrintWriter out = response.getWriter();

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("date");
		String userName = request.getParameter("username");

		int a1 = 0;
		boolean a2 = true;
		//out.println(request.getParameter("gcd"));
		if (request.getParameter("gcd")!=null && request.getParameter("gcd").equals("GCD"))
		{
			result = checkDateInput(year, month, day);
			if (result.equals(""))
			{
				a1 = gcd(year, month, day);
				out.println("The GCD of three data fields is: "+a1);
			}
			else
				out.println(result);
			//a1 =1;
		}
		else if (request.getParameter("pal")!=null && request.getParameter("pal").equals("PAL"))
		{
			result = checkNameInput(userName);
			if (result.equals(""))
			{
				a2 = pal(userName);
			out.println("The string is a palindrome? "+a2);
			}
			else
				out.println(result);
		}

		
		

	}

	protected static boolean pal(String value)
	{

		String copy = "";

		boolean result = true;

		for (int i = 0; i < value.length(); i++)
		{
			if (value.charAt(i) != " ".charAt(0))
				copy += value.charAt(i);
		}



		int start = 0;
		int end = copy.length() - 1;
		//		   System.out.println(copy + " "+ end);
		//		   System.out.println(copy.charAt(start));
		//		   System.out.println(copy.charAt(end));
		while (start < end)
		{
			if (copy.charAt(start) == copy.charAt(end))
			{
				start++;
				end--;
			}
			else
			{
				result = false;
				break;
			}
		}

		return result;

	}


	protected static int gcd(String year, String month, String day)
	{

		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);

		int t1 = 0;
		int t2 = 0;
		int t3 = 0;

		int g = 0;


		if (m <= y)
		{
			if (m <= d)
			{
				t1 = m;
				if (d <= y)
				{
					t2 = d;
					t3 = y;
				}
				else
				{
					t2 = y;
					t3 = d;
				}
			}
			else
			{
				t1 = d;
				if (m <= y)
				{
					t2 = m;
					t3 = y;
				}
				else
				{
					t2 = y;
					t3 = m;
				}
			}

		}
		else
		{
			if (y <= d)
			{
				t1 = y;
				if (m <= d)
				{
					t2 = m;
					t3 = d;
				}
				else
				{
					t2 = d;
					t3 = m;
				}
			}
			else
			{
				t1 = d;
				if (m <= y)
				{
					t2 = m;
					t3 = y;
				}
				else
				{
					t2 = y;
					t3 = m;
				}
			}
		}

		for (int i = t1; i > 0; i--)
		{
			if (t1 % i == 0 && t2 % i == 0)
			{
				g = i;
				break;
			}
		}

		for (int j = g; j > 0; j--)
		{
			if (g % j == 0 && t3 % j == 0)
			{
				g = j;
				break;
			}
		}

		return g;

	}


	protected String checkDateInput(String year, String month, String day)
	{
		String rs = "";
		try
		{
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(day);

			if (y < 1)
			{
				return "year is not valid";
			}
			if (m < 1)
			{
				return "month is not valid";
			}
			if (d < 1)
			{
				return "day is not valid";
			}

		}
		catch (NumberFormatException nfee)
		{
			return "Please enter Integers for the data field.";
		}
		return rs;
	}

	protected String checkNameInput(String value)
	{
		String rs = "";
		if (value == null || value.equals(""))
			rs = "name shoud not be empty";
		else if (value.indexOf('!') != -1 || value.indexOf('@') != -1
				|| value.indexOf('#') != -1 || value.indexOf('$') != -1
				|| value.indexOf('%') != -1 || value.indexOf('^') != -1
				|| value.indexOf('&') != -1 || value.indexOf('*') != -1
				|| value.indexOf('&') != -1 || value.indexOf('&') != -1
				|| value.indexOf('&') != -1 || value.indexOf('=') != -1
				|| value.indexOf('[') != -1 || value.indexOf('_') != -1
				|| value.indexOf('-') != -1 || value.indexOf('+') != -1
				|| value.indexOf('/') != -1 || value.indexOf('.') != -1
				|| value.indexOf(',') != -1 || value.indexOf('|') != -1
				|| value.indexOf('<') != -1 || value.indexOf('>') != -1
				|| value.indexOf('?') != -1 || value.indexOf(';') != -1
				|| value.indexOf('~') != -1)
			rs = "name is not valid";
		return rs;

	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	