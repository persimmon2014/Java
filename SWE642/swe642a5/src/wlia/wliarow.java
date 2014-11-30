package wlia;
// Designed and built by Alisha Sandhwani and Jeff Offutt
// July 2010

///////////////////////////////
///Modifed by Weizi Li
///////////////////////////////

import java.io.*;
import java.util.*;
import java.lang.*;

public class wliarow
{
	private wliahole[] eval = new wliahole[4]; //for left four small holes
	private wliahole[] guess = new wliahole[4]; //for guess results
	private String[] copy = new String[4]; //copy of answer
	private String background; //for background color of each row
	
	//constructor
	public wliarow(String[] origarray, String[] newarray, int num)
	{
		if (num % 2 == 0)
			background = "#6495ED"; //green
		else 
			background = "#90EE90"; //blue
		
		//create guess result
		for (int x = 0; x < newarray.length; x++)
		{
			guess[x] = new wliahole(newarray[x], 20);
		}
		
		//get a copy of answer
		copy(origarray);
		
		//create small holes on the left of each row
		createEval();
	}

	
	//copying the answer, arrays are not primitive
	public void copy(String[] array) 
	{
		for (int x = 0; x < array.length; x++)
		{
			copy[x] = array[x];
		}
	}

	//create small holes on the left of each row
	public void createEval()
	{
		for (int a = 0; a < 4; a++) //any reds?
		{
			if (guess[a].getColor().equals(copy[a]))
			{
				eval[a] = new wliahole("red", 1);
				copy[a] = "-1";
			}
		}
		for (int a = 0; a < 4; a++) //any grays?
		{
			if (eval[a] == null) //if not red
			{
				for (int b = 0; b < 4; b++)
				{
					if (guess[a].getColor().equals(copy[b]))
					{
						eval[a] = new wliahole("gray", 1);
						copy[b] = "-1";
						b = 3; //skip to the end of the for-loop
					}
				}
			}
		}
	}

	//get the eval
	public wliahole[] getEval()
	{
		return eval;
	}

	//get the guess result
	public wliahole[] getGuess()
	{
		return guess;
	}

	//get the background   
	public String getBackground(){
	    return background;
	 }
	
	public boolean isWinner() //did they win? is the eval[] all reds?
	{
		for (int x = 0; x < 4; x++)
		{
			if (eval[x] == null)
				return false;
			String c = eval[x].getColor();
			if (!c.equals("red"))
				return false;
		}
		return true;
	}

}