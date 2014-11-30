import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;


public class testa7a extends TestCase {

	public testa7a(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCompareTo()
	{
		Apple weeApple = new Apple(1);

		Orange bigOrange = new Orange(1);
		
	
			weeApple.compareTo(bigOrange);
		
	

		
	}

}
