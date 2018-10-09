//------------------------------------------------------------------------------
//  Sudeep Baniya
//  CMPS 12B
//  sucbaniy
//  4-17-2016
//  The programs reads CommandLineArguments to search for the word in a file.
//  Search.java
//------------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

class Search{
    // find the position of the location within a sorted array A[p..r] 
    static int binarySearch(String[] A, int p, int r, String location){
         int q;
         if(p > r) {
            return -1;
        }else{
           q = (p+r)/2;
        if(location.compareTo(A[q])==0){
           return q;
        }else if(location.compareTo(A[q])<0){
           return binarySearch(A, p, q-1, location);
        }else{
           return binarySearch(A, q+1, r, location);
        }
      }
   }
   
   // sorts the subarrays word[p...r]
    static void mergeSort(String[] word, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
     q = (p+r)/2;
        mergeSort(word, lineNumber, p, q);
        mergeSort(word, lineNumber, q+1, r);
        merge(word, lineNumber, p, q, r);
      }
   }
   
   // merge()
   // merges the sorted subarrays word[p..q] and word[q+1..r]
    static void merge(String[] word, int[] lineNumber, int p, int q, int r){
      int c = q-p+1;
      int d = r-q;
      String[] left = new String[c];
      String[] right = new String[d];
      int[] leftnumber = new int[c];
      int[] rightnumber = new int[d];
      int i, j, k;

      for(i=0; i<c; i++){
      left[i] = word[p+i]; 
      leftnumber[i] = lineNumber[p+i];
      }
      
      for(j=0; j<d; j++){ 
      right[j] = word[q+j+1]; 
      rightnumber[j] = lineNumber[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<c && j<d ){
            if( left[i].compareTo(right[j])<0 ){
               word[k] = left[i];
               lineNumber[k] = leftnumber[i];
               i++;
            }else{
               word[k] = right[j];
               lineNumber[k] = rightnumber[j];
               j++;
            }
		  }else if( i<c ){
            word[k] = left[i];
            lineNumber[k] = leftnumber[i];
            i++;
         }else{
            word[k] = right[j];
            lineNumber[k] = rightnumber[j];
            j++;
         }
      }
   }

   public static void main(String[] args) throws IOException {
      String[] target;
      int spot = 0;
      int a = 0;
      int b = 0;
      int[] line;

      //if less than 2 arguments is given then print out the statement
      if(args.length < 2) {
         System.out.println("Usage: Search file target1 [target2 ..]");
         System.exit(1);
      }
      // Counts the numbers of lines in the file
      Scanner in = new Scanner(new File(args[0]));

      while( in.hasNextLine() ){
         in.nextLine();
         a++;
      }
      target = new String[a];
      line = new int[a];

      Scanner in1 = new Scanner(new File(args[0]));

      while( in1.hasNextLine() ){
         target[b] = in1.nextLine();
         line[b] = b;
         b++;
      }
      //calls the mergeSort function
      mergeSort(target, line, 0, a-1);

      //calls the binarySearch function
      for(b=1; b<args.length; b++){
         spot = binarySearch(target, 0, a-1, args[b]);
         if( !(binarySearch(target, 0, a-1, args[b]) == -1) ){
            spot = line[spot]+1;
            System.out.println(args[b]+" found on line "+ spot);
         } else {
            System.out.println(args[b]+ " not found");
         }
      }
   }
}
