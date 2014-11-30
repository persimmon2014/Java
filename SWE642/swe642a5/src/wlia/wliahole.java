package wlia;
// Designed and built by Alisha Sandhwani and Jeff Offutt
// July 2010


///////////////////////////////
///Modifed by Weizi Li
///////////////////////////////


import java.io.*;
import java.util.*;
public class wliahole
{
	//for every hole in the page, both those big ones for guess and answer and 
	//small ones for result reminder
	
   private String color;
   private int size; //1=small, 20=large

   //constructor
   public wliahole (String colorinput, int sizeinput)
   {
      color = colorinput;
      size = sizeinput;
   }

   //set the color
   public void setColor (String input)
   {
      color = input;
   }
   
   
   //set the size
   public void setSize (int input)
   {
      size = input;
   }
   

   //get the color
   public String getColor()
   {
      return color;
   }

   //get the size
   public int getSize()
   {
      return size;
   }

}