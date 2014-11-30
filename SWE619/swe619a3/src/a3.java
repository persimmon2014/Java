//////////////////////////RESULT//////////////////////////
///  create: set1 = {3,2,1} set2 = {4,3,2}
///  setUnion(set1,set2) = {3,2,1,4}
///  setIntersection(set1,set2) = {3,2}
///  relativeComplement(set1,set2) = {1}
///  symmetricDifference(set1,set2) = {1,4}
///  sEqualTo(set1,set2) = false
///////////////////////////////////////////////////////////

import java.util.EmptyStackException;
import java.util.Vector;

class IntSet{
	
	//OVERVIEW: IntSets are unbounded, mutable sets of integers
	private Vector els;
	
	//constructos
	IntSet(){
	  //EFFECTS: Initializes this to be empty
	  els = new Vector();
	}
	
	//methods
	void insert(int x){
		//MODIFIES: this
		//EFFECTS: Adds x to the elements of this
		Integer y = new Integer(x);
        if(getIndex(y)<0) els.add(y);
	}
	
	void remove(int x){
		//MODIFIES: this
		//EFFECTS: Removes x from this
		int i = getIndex(new Integer(x));
		if(i<0) return;
		els.set(i, els.lastElement());
		els.remove(els.size()-1);
	}
	
	boolean isIn(int x){
		//EFFECTS: Returns true if x is in this else returns false
		return getIndex(new Integer(x))>=0;
	}
	
	int getIndex(Integer x){
		//EFFECTS: If x is in this returns index where x appears else returns -1
		for(int i=0; i<els.size(); i++)
			if(x.equals(els.get(i)))
				return i;
		return -1;
	}
	
	Vector getVector(){
		//EFFECTS: get this
		return els;
	}
	
	int getElement(int i){
		//EFFECTS: get a certain element from this
		return ((Integer)els.elementAt(i)).intValue();	
	}
	
	int size(){
		//EFFECTS: Returns the cardinality of this
		return els.size();
	}
	
	void printSet()	{
		//EFFECTS: Print out this
		for(int i=0; i<els.size(); i++)
			System.out.println(els.elementAt(i));
	}
	
	//creates a third set which is the set-theoretic union of two existing sets
	Vector setUnion(Vector set1, Vector set2){
        //EFFECTS: union two existing sets
		int flag = 0;
		int index = 0;
		els = set1;
		for(int i=0;i<set2.size();i++)
		{
			for(int j=0; j<els.size();j++)
			{
				if(((Integer)els.elementAt(j)).intValue() != ((Integer)set2.elementAt(i)).intValue())
				{   				   
				    continue;
				}
				else
				{   flag = 1;
					break;
				}			
			}
			
			if(flag == 0)
				els.addElement(set2.elementAt(i));
					
		}
		
		return els;
	}
	
	//creates a third set which is the set-theoretic intersection of two existing sets	
	Vector setIntersection(Vector set1, Vector set2){
		//EFFECTS: create a third set which is the intersection of two existing sets
		Vector set3 = new Vector();
		int flag = 0;
		for(int i=0;i<set2.size();i++)
		{
			for(int j=0; j<set1.size();j++)
			{
				if(((Integer)set1.elementAt(j)).intValue() != ((Integer)set2.elementAt(i)).intValue())
				{   				   
				    continue;
				}
				else
				{   flag = 1;
					break;
				}			
			}
			
			if(flag == 1)
				set3.add(set2.elementAt(i));
					
		}
		
		return set3;
		
	}
	
	//creates a third set which is the set of elements that are in set A and not in set B
	Vector relativeComplement(Vector set1, Vector set2) throws EmptyStackException{
		//REQUIRES: Two sets and set1 cannot be empty 
		//EFFECTS: If set1 is empty throw an exception, otherwise create a set is the set of elements that are in set1 but not set2
		if(set1.size() == 0) throw new EmptyStackException();
		Vector set3 = new Vector();
		int flag = 0;
		for(int i=0;i<set1.size();i++)
		{
			for(int j=0; j<set2.size();j++)
			{
				if(((Integer)set1.elementAt(i)).intValue() == ((Integer)set2.elementAt(j)).intValue())
				{   flag =1;			   
				    break;
				}
						
			}			
			if(flag == 0)
				set3.add(set1.elementAt(i));
			
			flag = 0;
					
		}
		return set3;
	}
	
	//creates a third set whose elements belong to set A or to set B but not both
	Vector symmetricDifference(Vector set1, Vector set2) throws EmptyStackException{
		//REQUIRES: Two sets both of them cannot be empty 
		//EFFECTS: If either set is empty then throw an exception, otherwise create a set whose  elements belong to set1 or set2 but not both
		if(set1.size() == 0) throw new EmptyStackException();
		if(set2.size() == 0) throw new EmptyStackException();
		Vector set3 = new Vector();
		Vector set4 = new Vector();
		Vector set5 = new Vector();
		set3 = setIntersection(set1,set2);
		set4 = setIntersection(set1,set2);
		set3 = relativeComplement(set1,set3);
		set4 = relativeComplement(set2,set4);
		set5 = setUnion(set3,set4);
		
		return set5;
	}
	
	//returns true if the two sets are equal, false otherwise; does not change either set
	boolean sEqualTo(Vector set1, Vector set2){
		//REQUIRES: Two sets both of them cannot be empty 
		//EFFECTS: Return true if twe sets are identical, otherwise return false
		if(set1.size() == 0) throw new EmptyStackException();
		if(set2.size() == 0) throw new EmptyStackException();
		Vector set3 = new Vector();
		set3 = setIntersection(set1,set2);
		if((set3.size() == set1.size()) && (set3.size() == set2.size()))	
			return true;
		else 
			return false;
	}
	
}


class a3{
	public static void main(String args[])
	{
		IntSet sample1 = new IntSet();
		IntSet sample2 = new IntSet();
		IntSet sample3 = new IntSet();
		
		IntSet sample4 = sample2;
		
		sample1.insert(3);
		sample2.insert(3);
		
		System.out.println(sample1.equals(sample4));
		
		
//		sample1.insert(3);
//		sample1.insert(2);
//		sample1.insert(1);
//		
//		sample2.insert(4);
//		sample2.insert(3);		
//		sample2.insert(2);
//		
//		Vector t1 = sample1.getVector();
//		Vector t2 = sample2.getVector();
//		Vector t3 = sample3.getVector();
//	    
//		//t3 = sample3.setUnion(t1,t2);	
//		//t3 = sample3.setIntersection(t1, t2);
//		//t3 = sample3.relativeComplement(t1, t2);
//		t3 = sample3.symmetricDifference(t1, t2);
//			
//		for(int i=0;i<t3.size();i++)
//		System.out.println(t3.elementAt(i));
//		
//		
		
	   
	}
}


