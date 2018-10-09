//-----------------------------------------------------------------------------
// FileReverse.java
// Sudeep Baniya
// 4-11-2016
// CMPS 12M
// sucbaniy
// Program that reads a file containing strings and then prints it out in a different file reversed with no white space
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class FileReverse{
   public static void main(String[] args) throws IOException{

      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      String[] token = null;
      int i, n, lineNumber = 0;

      // check number of command line arguments 
      if(args.length < 2){
         System.out.println("Usage: FileTokens <infile> <outfile>");
         System.exit(1);
      }

      // open files
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      // read lines from in, extract and print tokens from each line
      while( in.hasNextLine() ){
         lineNumber++;

         // trim leading and trailing spaces, then add one trailing space so 
         // split works on blank lines
         line = in.nextLine().trim() + " "; 

         // split line around white space 
         token = line.split("\\s+");  

         // calls the stringreverse method that calls out the tokens in reverse        
         n = token.length;
         for(i=0; i<n; i++){
            out.println(stringReverse( token[i], token[i].length()));
         }
      }

      // close files
      in.close();
      out.close();
   }
     
      public static String stringReverse(String s, int n){
	if (n == 0){
	      return s;
	} n--; 
	      return stringReverse((s.substring(1)),n)+s.charAt(0);
        }
}
	

