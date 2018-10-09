
// Lawn.java
// Sudeep Baniya
// sucbaniy
// PA1
// Calculating the mowing time of the lot with user input
//--------------------------------------------------------------------------

import java.util.Scanner;
class Lawn{
   public static void main( String[] args ){
      double length1, width1, length2, width2, area, rate, time;
      int h, m, s, input;
      String hs, ms, ss;
      Scanner sc = new Scanner(System.in);

      System.out.print("Enter length and width of the lot, in feet: ");
      length1 = sc.nextDouble();	// Length of Lot
      width1 = sc.nextDouble();	// Width of Lot
      System.out.print("Enter length and width of the house, in feet: ");
      length2 = sc.nextDouble();	// Length of House
      width2 = sc.nextDouble();	// Width of House
      area = length1*width1 - length2*width2;	// Formula for Lawn Area
      System.out.print("The lawn area is "+area+" square feet ");
      System.out.println();
      System.out.print("Enter the mowing rate, in square feet per second: ");
      rate = sc.nextDouble();
      time = area/rate;
      input = (int) Math.round(time);	
      m = input/60;
      s = input%60; 
      h = m/60;
      m = m%60;
      hs = (h==1? " " : "s.");
      ms = (m==1? " " : "s.");
      ss = (s==1? " " : "s.");
      System.out.println("The mowing time is "+h+" hour"+(h==1?" ":"s ") + m +" minute"+(m==1?"":"s")+" and "+s+" second"+(s==1?".":"s."));
   }
}   

