// Roots.java
// Sudeep Baniya
// sucbaniy
// pa4
// Prints out roots of the polynomial function

import java.util.Scanner;

class Roots {

      public static void main( String[] args ){
            double L, R;		// L =leftendpoint, R= rightendpoint
            double resolution = 0.01;
            double tolerance = 0.0000001;
            double threshold = 0.001;

            int polyRoot = 0;
            int diffRoot = 0;

            boolean root = false;

            Scanner sc = new Scanner(System.in);	//for user input


            System.out.print("Enter the degree: "); //user enters degree of the polynomial
            degree = sc.nextInt();
            coef = degree + 1; // coefficent is found by adding the degree by 1
            System.out.print("Enter " + coef + " coefficients: ");// use inputs number of coefficents depending on degree
            double[] coefficients = new double[coef]; // array initalization
            polyArray = new double[coef]; // naming the array
            double[] polyOfRootArray = new double[degree];
            diffArray = new double[coef];
            double[] diffOfRootArray = new double[degree];
            for(int i = 0; i < coef; i++) {
                  coefficients[i] = sc.nextDouble();
            }
            System.out.print("Enter the left and right endpoints: "); // asks user for input for the left and right endpoints
            L = sc.nextDouble();
            R = sc.nextDouble();

            System.out.println();

            diff(coefficients); // calculates the coefficients of derivative polynomial

            for (double i = L; i < R-resolution; i = i + resolution){
                  if (positive(diffArray, i) != positive(diffArray, i+resolution) || positive(diffArray, i) == 0) {
                        diffOfRootArray[diffRoot] = findRoot(diffArray, i, i+resolution, tolerance);
                        diffRoot++;
                  }
            }

            for (int i = 0; i < diffOfRootArray.length; i++) {
                  double temp;
                  temp = poly(coefficients, diffOfRootArray[i]);
                  temp = Math.abs(temp);
                  if (temp < threshold) {
                        polyOfRootArray[polyRoot] = diffOfRootArray[i];
                        polyRoot++;
                        root = true;
                  }
            }

            for (double i = L; i < R-resolution; i = i + resolution){
                  if (positive(coefficients, i) != positive(coefficients, i+resolution) || positive(coefficients, i) == 0) {
                        polyOfRootArray[polyRoot] = findRoot(coefficients, i, i+resolution, tolerance);
                        polyRoot++;
                        root = true;
                  }
            }
	    

            if (root == true) {
                  for (int i = 0; i < polyOfRootArray.length; i++) {
                        if (polyOfRootArray[i] != 0.0) {
                              System.out.printf("Root found at %.5f%n", polyOfRootArray[i]); // if roots are found print this messagge
			}
		   }
	   }else{
	       System.out.println("No roots where found in the specified range."); // if roots dont find display this
                            
	   }
}


     public static int degree;
     public static double[] polyArray;
     public static double[] diffArray;
     public static int coef;

     static double poly(double[] C, double x){
           double sum = 0.0;
           polyArray[0] = C[0];
           for (int i = 1; i < polyArray.length; i++){
                 polyArray[i] = C[i]*(Math.pow(x, i)); // multiplies by the powers
           }
           for (int i = 0; i < polyArray.length; i++){
                 sum = sum + polyArray[i]; // find the sum
           }
           return(sum);

      }

      static double[] diff(double[] C){
            for (int i = 0; i < degree; i++){
                  diffArray[i] = (i+1)*C[i+1]; // new allocated space fir array
            }
            return(diffArray);
      }

      static double findRoot(double[] C, double a, double b, double tolerance){  
            double root = 0.0 , residual;
            while ( Math.abs(b - a) > tolerance ) {
                  root = (a + b) / 2.0; // using the bicetion method
                  residual = poly(C, root);
                  if (poly(C, a) > 0 && poly(C, b) < 0) {
                        if (residual > 0)
                              a = root; // replacing endpoints
                        else
                              b = root; // replacing endpoints
                  } else if (poly(C, a) < 0 && poly(C, b) > 0) {
                        if (residual > 0)
                              b = root;
                        else
                              a = root;
                  }
            }
            return(root);
      }
      static int positive(double[] C, double a){ // if it is positive
            double tempAendPoint;
            tempAendPoint = poly(C, a);
            if (tempAendPoint < 0) {
                  return(1);
            } else if (tempAendPoint > 0) {
                  return(2);
            } else {
                  return(0);
            }
      }
}
