import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;


public class a6Test extends TestCase {

	public a6Test(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void testPrintArray() throws IOException{
    	test<Integer> t1=new test<Integer>(); 
		test<Double> t2=new test<Double>(); 
		test<Character> t3=new test<Character>(); 
		
		Integer array1[]={1,2,3,4};
		Double array2[]={1.1,2.2,3.3,4.4};
		Character array3[]={65,66,67,68};
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		os.flush();
		ps.flush();
		t1.printArray(array1);
		assertEquals("1 2 3 4 "+System.getProperty("line.separator"),os.toString());
		
		OutputStream os1 = new ByteArrayOutputStream();
		PrintStream ps1 = new PrintStream(os1);
		System.setOut(ps1);
		os1.flush();
		ps1.flush();
		t2.printArray(array2);
		assertEquals("1.1 2.2 3.3 4.4 "+System.getProperty("line.separator"),os1.toString());
		
		OutputStream os2 = new ByteArrayOutputStream();
		PrintStream ps2 = new PrintStream(os2);
		System.setOut(ps2);
		os2.flush();
		ps2.flush();
		t3.printArray(array3);
		assertEquals("A B C D "+System.getProperty("line.separator"),os2.toString());
		
    }

}
