//------------------------------------------------------------------------------// Guess.java
// Sudeep Baniya
// sucbaniy
// pa1
// Guessing game depending one the user input
//-----------------------------------------------------------------------------

import java.util.Scanner;


class Guess{
	public static void main ( String[] args ){
	  double guess1, guess2, guess3;
	  Scanner sc = new Scanner(System.in);

	  int integer = (int) (Math.random() * 10)+ 1;
	
	  System.out.println();
	  System.out.println("I'm thinking of an integer in the range 1 to 10. You have three guesses.");
	  System.out.println();
							
	  System.out.print("Enter your first guess: "); //first guess
	  guess1 = sc.nextDouble();
	  if (guess1 == integer){
      	    System.out.println("You win!");
	    System.exit(0);	 //ends program if guess is right
	  } else if (guess1 > integer){
            System.out.println("Your guess is too high.");
	  } else if (guess1 < integer){
	    System.out.println("Your guess is too low.");
	  }
	  System.out.println();
	
	  System.out.print("Enter your second guess: "); //second guess
	  guess2 = sc.nextDouble();
	  if (guess2 == integer){
	    System.out.println("You win!");
	    System.exit(0);	 //ends program if guess is right
	  } else if (guess2 > integer){
	    System.out.println("Your guess is too high.");
	  } else if (guess2 < integer){
	    System.out.println("Your guess is too low.");
	  }
	  System.out.println();
	
	  System.out.print("Enter your third guess: ");  //third guess
	  guess3 = sc.nextDouble();
	  if (guess3 == integer){
	    System.out.println("You win!");
	    System.exit(0);	 //ends program if guess is right
	  } else if (guess3 > integer){
	    System.out.println("Your guess is too high.");
	  } else if (guess3 < integer){
	    System.out.println("Your guess is too low.");
	  } System.out.println();
	  System.out.print("You lose. The number was "+integer+".");  //if guess is still wrong after 3 tries
	  System.out.println();
	}
}
