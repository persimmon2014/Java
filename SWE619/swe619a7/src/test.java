class test<T>{ 
//OVERVIEW:
	
public T field;

//Constructor
public void test()
{
	field = null;
}

//Methods
public void setValue(T a)
{//MODIFIES: this
 //EFFECTS: set the value of this using a 
	field = a;
	}

public T getValue()
{//MODIFIES: this
 //EFFECTS: get the value of this
	return field;
	}

public void swap(test b)
{//MODIFIES: this
 //EFFECTS: swap the values
	T temp;
	temp = (T) this.getValue();
	this.setValue((T)b.getValue());
	b.setValue(temp);
	
	}

public boolean repOk()
{ if(field!=null)
	return true;
else
	return false;
	}

public static void main(String args[]){ 
	

  
    } 
}