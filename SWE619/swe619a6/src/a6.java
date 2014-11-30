import java.util.Vector;
import java.util.Set;
import java.util.HashSet;


public class a6{

	public static void main(String args[])
	{
		Set s = new HashSet();
	    Vector x =  new Vector();
	    Vector y = new Vector();
	    s.add(x);
	    s.add(y);
	    System.out.println(s.contains(x));
	    System.out.println(s.contains(y));
	    System.out.println(s.size());
	    y.add("cat");
	    System.out.println(s.contains(x));
	    System.out.println(s.contains(y));
	    s.add(y);
	    y.remove("cat");
	    System.out.println(s.size());
	    
	}
}

























