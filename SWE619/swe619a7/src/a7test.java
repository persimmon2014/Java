import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;


public class a7test extends TestCase {

	public a7test(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSwap() throws IOException{
		test<Integer> mInt = new test<Integer>();
		test<Integer> mInt1 = new test<Integer>();
		mInt.setValue(5);
		mInt1.setValue(6);
		int tt = mInt.getValue();
		mInt.setValue(mInt1.getValue());
		mInt1.setValue(tt);

		mInt.swap(mInt1);
		
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		os.flush();
		ps.flush();
		System.out.println(mInt1.getValue());
		assertEquals("6"+System.getProperty("line.separator"),os.toString());
	}



}
