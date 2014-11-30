import java.io.IOException;
import java.lang.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class DivideByZero{
	private static Scanner san = new Scanner(System.in);
	
	public static void quotient(int numerator, int denominator) //throws InputMismatchException
	{
		System.out.println(numerator / denominator);	
	}
	
	public static void main(String args[])
	{
	  
		int a = san.nextInt();
	    int b = san.nextInt();
	
	  try
	   {
		   quotient(a,b);
	   }
		catch(InputMismatchException e)
		{
			System.err.println("Caught NullPointerException");
			e.printStackTrace();
	
	
	     }
}
}