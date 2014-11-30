import java.util.*;

class myException extends Exception{
	public myException()
	{
		super("The type is not match!");
	}
}

class Fruit implements Comparable<Fruit> {
	
String name;
int size;

public Fruit(String name, int size) {
this.name = name; this.size = size;
}

public int compareTo(Fruit that) {

       return this.size < that.size ? - 1 :this.size == that.size ? 0 : 1 ;

}

public String toString() {
return this.name + "(" + size + ")";
}
public boolean equals(Object o) {
return (o instanceof Fruit && name.equals(((Fruit)o).name) && size == ((Fruit)o).size) ;
}

}
class Apple extends Fruit {
public Apple (int size) {
super("Apple", size);
}
}
class Orange extends Fruit {
public Orange (int size) {
super("Orange", size);
}
}
class Test1 {
public static void main (String... args) {
	myException myEx = new myException();
	Apple weeApple = new Apple(1);

	Orange bigOrange = new Orange(1);
	
	if(!weeApple.name.equals(bigOrange.name))
		try {
			throw (new myException());
		} catch (myException e) {
			e.printStackTrace();
		} 

}
}