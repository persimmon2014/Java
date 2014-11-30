
//SWE619 A4 Weizi Li
import java.util.InputMismatchException;
import java.util.Scanner;

class Tree {
//OVERVIEW: The element of tree is immutable and in this program it only accpet integers
public final int mVal;
public Tree mLeft;
public Tree mRight;


//constructors
public Tree(int val){
//EFFECTS: Initializes this to be val
  mVal = val;
}


//methods
public void add(int val) {
//REQUIRES: input val should be a integer
//MODIFIES: this
//EFFECTS: add a new element to exist tree, if is unique then it will be added to the tree, otherwise not
	if (val < mVal) {
		if (mLeft == null)
			mLeft = new Tree(val);
		else
			mLeft.add(val);
	} else if (val > mVal) {
		if (mRight == null)
			mRight = new Tree(val);
		else
			mRight.add(val);
}
}


//public String inOrder() {
////REQUIRES: at least one element in the tree
////EFFECTS: return all the values in the tree in ascend respect
//	return ((mLeft == null) ? "" : mLeft.inOrder())+ mVal + " "+ ((mRight == null) ? "" : mRight.inOrder());
//}

public String inOrder() {
	//REQUIRES: at least one element in the tree
	//EFFECTS: return all the values in the tree in ascend respect
	 if(mLeft == null)
	 {
		 if(mRight == null)
			 return (mVal+" ");
		 else 
			 return (mVal+" "+mRight.inOrder());
	 }	
	 else
	 {
		 if(mRight == null)
			 return (mLeft.inOrder()+mVal+" ");
		 else 
			 return (mLeft.inOrder()+mVal+" "+mRight.inOrder());
	 } 

	}


public static void main(String[] args) {

	Tree t = new Tree(8);
	Scanner scanner = new Scanner(System.in);

	boolean continueLoop = true; // determines if more input is needed
	for (int i = 1; i < 9; ++i) { 
		try // read two numbers and calculate quotient 
		{
     		System.out.print("Please enter a random integer : ");
			int stackInt = scanner.nextInt();
			t.add(Integer.valueOf(stackInt));
		} // end try
		catch (InputMismatchException inputMismatchException){
			System.err.printf("\nException: %s\n", inputMismatchException);
			scanner.nextLine(); //discard input so user can try again
			System.out.println("You must enter integers. Please try again.\n");
		} // end catch
}

	System.out.println("Values in order = "+ t.inOrder());

}
}