//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA1 
// Lex.java
// Sorts inputfile using List.java and writes in outputfile
//------------------------------------------------------------------------

import java.io.*;

import java.util.*;


public class Lex {

   public static void main(String[] args) throws IOException {

      // Pre: Check if two arguments has passed
      if(args.length == 0 || args.length == 1 || args.length > 2) {

         System.err.println(" Usage: Lex <inputfile>  <outputfile>");
        
         System.exit(1);
      }


      // variable declarations
      Scanner input = null;
      
      PrintWriter output = null;
      
      String[] n = null;
      
      String line = null;
      
      int lines = -1;
      
      int lineNumber = 0; 
      

      // Count the total lines of file
      input = new Scanner(new File (args [0] ));
      
      while( input.hasNextLine () ) {
      
         input.nextLine ();
      
        ++lineNumber;
      }

      input.close ();
      
      input = null;

      // Recreate Scanner
      input = new Scanner(new File(args[0]));

      // creates String array
      n = new String[lineNumber];

      // Place all inputs in a String array
      while(input.hasNextLine()) {
      
         n[++lines] = input.nextLine();
      }
	     
      // Recreate PrintWriter
      output = new PrintWriter(new FileWriter(args[1]));
      
      // create List
	  List l = new List();	     
      
     
      l.append(0);

      // Insertion Sort
      for(int j = 1; j < n.length; ++j) {
         
         String type =  n[j];

         int i = j - 1;
         
         // move Index to the back of list
         l.moveBack();

         // comparing the current line and each line of List
         while(i > -1 && type.compareTo(n[l.get()]) <= 0) {
      
            --i;
      
            l.movePrev();
         }
         
         if(l.index() > -1){
         
            l.insertAfter(j);
         
         }else{
      
            l.prepend(j);
         }   
      }

      // move Index to front of the list
      l.moveFront();

      // Loop control for List for the correct order of output
      while(l.index() > -1) {
      
         output.println(n[l.get()]);
      
         l.moveNext();
      }
     
      // Close input and output
      input.close();
      
      output.close();
   }
}
