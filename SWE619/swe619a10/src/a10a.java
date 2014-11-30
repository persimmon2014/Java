class Point2{
	private int x;
	private int y;
	
	protected Point2(int x,int y)
	{//constructor
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object o)
	{//overriding equals() definition
		if(!(o instanceof Point2))
			return false;
		Point2 p = (Point2) o;
		return p.x == x && p.y == y;
		
	}
	
	public int hashCode()
	{//overriding hashCode() definition
		int result = 23;
		result = 31*result + this.x;
		result = 31*result + this.y;
		return result;
	}
	
}

class Point{
	private int x;
	public Point(int x){
	this.x = x;
	}
}

class Point3 extends Point2{


	private int z; //the z coordinate
		
	public Point3(int x, int y, int z) 
	{//constructor
		super(x, y);
		this.z = z;
		
	}
	
	public boolean equals(Object p)
	{//overriding equals() definition
		if(p instanceof Point3)
			return equals((Point3) p);
		return super.equals(p);
	}
	
	public boolean equals(Point2 p)
	{//overriding equals() definition
		if(p instanceof Point3)
			return equals((Point3) p);
		return super.equals(p);
	}
	
	public boolean equals(Point3 p)
	{//extra definition
		if(p == null || z !=p.z)
			return false;
		return super.equals(p);
	}
	
	public int hashCode()
	{//overriding hashCode() definition
		int result = 23;
		result = super.hashCode();
		result = 31*result + this.z;		
		return result;
	}
}

class a9{
	public static void main(String args[])
	{
		Point3 t1 = new Point3(1,1,1);
	}
}
