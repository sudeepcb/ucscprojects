//-------------------------------------------------------------------------------
// Sudeep Baniya
// sucbaniy
// GCD.java
// Finding the greatest common divisor with user input and restricitng some values
//-------------------------------------------------------------------------------

import java.util.Scanner;

class GCD{
  
   public static void main( String[] args ){
     Scanner sc = new Scanner(System.in);
     int a ,b, c;
     a = b = c = 0;
     System.out.print("Enter a positive integer: ");
     while (true){
	if (sc.hasNextInt()){
	   a = sc.nextInt();
	   if (a >= 0 ) break;
	}else{
	   sc.next();
	}
	   System.out.print("Please enter a positive integer: ");
	}
	   System.out.print("Enter another positive integer: ");

     while (true){
	if (sc.hasNextInt()){
	   b = sc.nextInt();
	   if (b >= 0) break;
	}else{
	   sc.next();
	   System.out.print("Please enter a positive integer: ");
	}
	}
	   System.out.print("The GCD of "+a+" and "+b+ " is ");


     while (b!=0){
	c = b;
	b = a%b;
	a = c;
     }
      System.out.println(c);
   }
}
