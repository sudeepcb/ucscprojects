//------------------------------------------------------------------------------// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA3
// Sparse.java
// Creates Matrix for inputfile and uses it to perform operations on it Matrix
//------------------------------------------------------------------------------



import java.io.*;

import java.util.Scanner;

public class Sparse {
   
      public static void main(String[] args) throws IOException {

      Scanner input = null;

      PrintWriter output = null;

      int i, n;

      if(args.length < 2){

         System.err.println("Usage: Sparse inputFile outputFile");

         System.exit(1);
      }
      

      input = new Scanner(new File(args[0]));

      output = new PrintWriter(new FileWriter(args[1]));


      int lineNumber = input.nextInt();

      int linesA = input.nextInt();

      int linesB = input.nextInt();

      Matrix A = new Matrix(lineNumber);

      Matrix B = new Matrix(lineNumber);

      for(i = 1; i<=linesA; i++){

         A.changeEntry(input.nextInt(), input.nextInt(), input.nextDouble());
      }

      for(n = 1; n<=linesB; n++){

         B.changeEntry(input.nextInt(), input.nextInt(), input.nextDouble());
      }


      // Print A
      output.println("A has " +linesA+ " non-zero entries:");

      output.println(A);
      

      // Print B
      output.println("B has " +linesB+ " non-zero entries:");

      output.println(B);


      // Print (1.5)A
      output.println("(1.5)*A =");

      Matrix C = A.scalarMult(1.5);

      output.println(C);


      // Print A+B
      output.println("A+B =");

      Matrix D = A.add(B);

      output.println(D);
      

      // Print A+A
      output.println("A+A =");

      Matrix E = A.add(A);

      output.println(E);


      // Print B-A
      output.println("B-A =");

      Matrix F = B.sub(A);

      output.println(F);
      

      // Print A-A
      output.println("A-A =");

      Matrix G = A.sub(A);

      output.println(G);
      

      // Print Transpose(A)
      output.println("Transpose(A) =");

      Matrix H = A.transpose();

      output.println(H);
      

      // Print A*B
      output.println("A*B =");

      Matrix I = A.mult(B);

      output.println(I);
      

      // Print B*B
      output.println("B*B =");

      Matrix J = B.mult(B);

      output.println(J);
      


      input.close();

      output.close();
   }
}
