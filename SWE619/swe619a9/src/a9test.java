import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;


public class a9test extends TestCase {

	public a9test(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testa9() throws IOException
	{
		//Point2 instances
		Point2 p1 = new Point2(1,2);
		Point2 p2 = new Point2(2,2);
		
		//Point3 instances
		Point3 p3 = new Point3(1,2,3);
		Point3 p4 = new Point3(1,2,2);
		Point3 p5 = new Point3(1,2,3);
		
		//System.out.println(p3.hashCode());
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);	
		System.setOut(ps);
		os.flush();
		ps.flush();
		System.out.println(p3.equals(p1));
		assertEquals("true"+System.getProperty("line.separator"),os.toString());
		
		OutputStream os1 = new ByteArrayOutputStream();
		PrintStream ps1 = new PrintStream(os1);
		System.setOut(ps1);
		os1.flush();
		ps1.flush();
		System.out.println(p1.equals(p4));
		assertEquals("true"+System.getProperty("line.separator"),os1.toString());
	
		OutputStream os2 = new ByteArrayOutputStream();
		PrintStream ps2 = new PrintStream(os2);
		System.setOut(ps2);
		os2.flush();
		ps2.flush();
		System.out.println(p3.equals(p4));
		assertEquals("false"+System.getProperty("line.separator"),os2.toString());
		
		OutputStream os3 = new ByteArrayOutputStream();
		PrintStream ps3 = new PrintStream(os3);	
		System.setOut(ps3);
		os.flush();
		ps.flush();
		System.out.println(p1.hashCode());
		assertEquals("22136"+System.getProperty("line.separator"),os3.toString());
		
		OutputStream os4 = new ByteArrayOutputStream();
		PrintStream ps4 = new PrintStream(os4);
		System.setOut(ps4);
		os1.flush();
		ps1.flush();
		System.out.println(p3.hashCode());
		assertEquals("686219"+System.getProperty("line.separator"),os4.toString());
	
		OutputStream os5 = new ByteArrayOutputStream();
		PrintStream ps5 = new PrintStream(os5);
		System.setOut(ps5);
		os2.flush();
		ps2.flush();
		System.out.println(p4.hashCode());
		assertEquals("686218"+System.getProperty("line.separator"),os5.toString());
			
		
	}

}
