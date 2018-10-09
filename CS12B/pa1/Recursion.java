//-----------------------------------------------------------------------------
// Recursion.java
// Sudeep Baniya
// 4-8-16
// CMPS 12B
// pa1
// Template for CMPS 12B pa1.  Fill in the five recursive functions below.
// The program uses recursion functions to change the order of the array and find    the maximun and minimum of the array and returns it by the index 
//-----------------------------------------------------------------------------

class Recursion {
   
   // reverseArray1()
   // Places the leftmost n elements of X[] into the rightmost n positions in
   // Y[] in reverse order
   static void reverseArray1(int[] X, int n, int[] Y){
   if (n>0){
      Y[Y.length-n] = X[n-1]; 
      reverseArray1(X,n-1,Y);
      }
      }
   

   // reverseArray2()
   // Places the rightmost n elements of X[] into the leftmost n positions in
   // Y[] in reverse order.
   static void reverseArray2(int[] X, int n, int[] Y){
   if (n>0){
    Y[n-1] = X[Y.length-n];
    reverseArray2(X,n-1,Y); 
   }
   }
   
   // reverseArray3()
   // Reverses the subarray X[i...j].
   static void reverseArray3(int[] X, int i, int j){
    if(i>=j){
	  return;
    }else{
    int temp = X[j];
    X[j] = X[i];
    X[i] = temp;
    reverseArray3(X,i+1,j-1);
}
}   
   // maxArrayIndex()
   // returns the index of the largest value in int array X
   static int maxArrayIndex(int[] X, int p, int r){
   int q = 0;
   if(p < r) {
   q = (p+r)/2;

   // Finds the max value of the left array
   int leftIndex = maxArrayIndex(X, p, q);

   // Finds the max value of the right array
   int rightIndex = maxArrayIndex(X,p+1,q);

   // comparss the left and the right array to find the maximum between them
   return max(X, leftIndex, rightIndex);
   }
   return max(X,p,r);
}
   // helper functions that compares the left and the right array to find the           maximum index of the two
   static int max(int[] X, int p, int r){ 
   int maxValue = 0;
   if (X[p] > X[r]){
	 maxValue = p;
   }else{
	 maxValue = r;
   }
   return maxValue;
   } 
   
   // minArrayIndex()
   // returns the index of the smallest value in int array X
   static int minArrayIndex(int[] X, int p, int r){
   int q = 0;
   if(p < r) {
   q = (p+r)/2;
 
   // Finds the min value of the left array
   int leftIndex = minArrayIndex(X, p, q);
 
   // Finds the min value of the right array
   int rightIndex = minArrayIndex(X,q+1,q);

   // compares the left and the right array to find the minimum of the two
   return min(X,leftIndex, rightIndex);
   }
   return min(X,p,r);
   }

   // helper functions that compares hte left and the right array to the find the       minimun index of the two
   static int min(int[] X, int p, int r){ 
   int minValue = 0;
   if (X[p] > X[r]){
   minValue = r;
   }else{
   minValue = p;
   }
   return minValue;
   } 
   
   // main()
   public static void main(String[] args){
      
      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);
      
      for(int x: A) System.out.print(x+" ");
      System.out.println(); 
      
      System.out.println( "minIndex = " + minIndex );  
      System.out.println( "maxIndex = " + maxIndex );  

      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();
      
      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println();
      
      reverseArray3(A, 0, A.length-1);
      for(int x: A) System.out.print(x+" ");
      System.out.println();  
      
   }
   
}
/* Output:
-1 2 6 12 9 2 -5 -2 8 5 7
minIndex = 6
maxIndex = 3
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
*/
