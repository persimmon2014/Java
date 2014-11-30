import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

class InstrumentedHashSet<E> extends HashSet<E>{
	//The number of attempted element insertions
	private int addCount = 0;
	
	public InstrumentedHashSet(){}
	
	public InstrumentedHashSet(int initCap, float loadFactor){
		super(initCap, loadFactor);
	}
	
	public boolean add(E e){
		//Override superclass methods
		addCount++;
		return super.add(e);
	}
	
	
	public boolean addAll(Collection <? extends E> c){
		//Override superclass methods
		addCount += c.size();
		return super.addAll(c);
	}
	
	public int getAddCount(){
		return addCount;
	}
}

class a10b{
	public static void main(String args[])
	{
		InstrumentedHashSet<String> s = new InstrumentedHashSet<String>();
		s.addAll(Arrays.asList("Snap","Crackle","Pop"));
		System.out.println(s.getAddCount());
	}
}



