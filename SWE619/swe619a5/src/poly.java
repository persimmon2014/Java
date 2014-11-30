import junit.framework.TestCase;

class poly{
	
	//OVERVIEW: This program is the implementation of poly.
	private int[] trms;
	private int deg;
	
	
	//constructors
	public poly(){
		//EFFECTS: Initializes this to be the zero polynomial.
		trms = new int[1]; deg = 0;
	}
	
	public poly(int c,int n){
		//EFFECTS: If n<0 throws NegativeExponentException else initializes this to be the poly c*pow(x,n).
		if(n<0)
			System.out.println("poly(int,int) constructor");
		if (c==0){trms = new int[1]; deg = 0; return;}
		trms = new int[n+1];
		for(int i=0;i<n;i++) 
			trms[i] = 0;
		trms[n] = c;
		deg = n;
	}
	
	private poly(int n){
		trms = new int[n+1]; deg = n;
	}
	
	
	//methods
	public int degree(){
		//EFFECTS: Returns the degree of this
		return deg;
	}
	
	public int coeff(int d){
		//EFFECTS: Returns the coefficient of the term of this whose exponent is d.
		if(d<0 || d>deg) return 0; 
		else return trms[d];
	}
	
	public poly sub(poly q) throws NullPointerException{
		//EFFECTS: If q is null throws NullPointerException else returns the Poly this -q
		return add (q.minus());
	}
	
	public poly minus(){
		//EFFECTS: Returns the poly - this.
		poly r = new poly(deg);
		for(int i=0;i<deg;i++)
			r.trms[i] = -trms[i];
		return r;
	}
	
	public poly add(poly q) throws NullPointerException{
		//EFFECTS: If q is null throws NullPointerException else returns the poly this + q
		poly la, sm;
		if(deg > q.deg)
		{
		  la = this;
		  sm = q;
		}
		else
		{
			la = q; sm = this;
		}
		int newdeg = la.deg;
		if(deg == q.deg)
			for(int k=deg; k>0; k--)
				if(trms[k] + q.trms[k]!=0) 
				break;
				else
					newdeg--;
				
		poly r =new poly(newdeg);
		int i;
		for(i=0; i<=sm.deg && i<= newdeg; i++)
			r.trms[i] = sm.trms[i] + la.trms[i];
		for(int j=i; j<= newdeg; j++)
			r.trms[j] = la.trms[j];
		return r;
	}
	
	public poly mul(poly q) throws NullPointerException{
		//EFFECTS: If q is null throws NullPointerException else returns the poly this *q.
		if((q.deg==0 && q.trms[0] == 0) || (deg == 0 && trms[0] == 0)) 
		 return new poly();
		poly r = new poly(deg+q.deg);
		r.trms[deg+q.deg] = 0;
		for(int i=0; i<=deg; i++)
			for(int j=0;j<q.deg; j++)
				r.trms[i+j] = r.trms[i+j] + trms[i] * q.trms[j];
		return r;
	}
	
	public boolean repOK(){
		//EFFECTS: return true if there is a element inside the poly set otherwise false
		if(trms == null || deg != trms.length - 1 || trms.length == 0)
			return false;
		if(deg == 0)
			return true;
		return trms[deg] != 0;
	}
	
	public String toStringSmall(){
		//EFFECTS: print out poly set 
		if(trms == null)
			return "Poly: {}";
		String s = "Poly: { ";
		for(int i=0; i< trms.length; i++)
		{
			s += " "+trms[i]+"x"+i;
		}
		s += " }";
		
		return s;
	}
	
	public String toStringLarge(){
		//EFFECTS: print out poly set 
		if(trms == null)
			return "Poly: {}";
		String s = "Poly: { ";
		for(int i=0; i< trms.length; i++)
		{
			if(trms[i] == 0)
				continue;
			s += " "+trms[i]+"x"+i;
		}
		s += " }";
		
		return s;
	}
	
	public static void main(String args[])
	{
		poly sample1 = new poly(1,2);
		poly sample2 = new poly(1,2);
		
		System.out.println(sample1.equals(sample2));
		
	}
}

//implement of JUnit
//class polyTest extends TestCase{
//	
//	private poly test1;
//	private poly test2;
//	private poly test3;
//	
//	protected void setUp() throws Exception{
//		super.setUp();
//		test1 = new poly(1,2);
//		test2 = new poly(3,4);
//		test3 = new poly(5,6);
//	}
//	
//	 public polyTest(String name) {
//	        super(name);
//	    }
//	 
//	 public void testEquals(){
//	        assertFalse(test1.equals(test2));
//	        assertTrue(test2.equals(test3));
//	    }
//}















