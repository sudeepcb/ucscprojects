/* Queens.java
 * Sudeep Baniya
 * sucbaniy
 * Queens
 * finding the possible solutions to the n queens problem
*/

import java.util.Scanner;

class Queens {
	
	public static void main ( String[] args) {
		Scanner s = new Scanner(System.in);
		int queensQ, B,k=0;
		int n = args.length;
		String v = "-v";
		// CommandLine Arguments
		for(int i=0; i<args.length; i++){
				s = new Scanner(args[i]);
		}
		if(n == 0){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v  verbose output, print all solutions");
			System.exit(0);
		}else if (!v.equals(args[0]) && n == 2){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v  verbose output, print all solutions");
			System.exit(0);
		}else if (n>=3){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v  verbose output, print all solutions");
			System.exit(0);
		}else if (Integer(args[0]) && n == 1 ){
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v  verbose output, print all solutions");
			System.exit(0);
		}else if (n == 1){	// the start of the permutation	
			int num = Integer.parseInt(args[0]);
			queensQ = num;
			int [] A = new int [num+1];
			A[0] = 0;
			for( int i=1; i <= num; i++){
				A[i] = i;
			}
		
			B = factorial(num);
			for(int i=1 ;i<=B;i++){
				nextPermutation(A);
				if (isSolution(A)){
					k++;
				}
			}
			System.out.println(queensQ+"-Queens has "+k+" solutions");
		}else if (n == 2 && v.equals(args[0])){
			int num = Integer.parseInt(args[1]);
			queensQ = num;
			int [] A = new int [num+1];    
			A[0] = 0;
			for( int i=1; i <= num; i++){
				A[i] = i;
			}
		
			B = factorial(num);
			for(int i=1 ;i<=B;i++){
				nextPermutation(A);
				if (isSolution(A)){
					printArray(A);
					k++;
				}
			}
			System.out.println(queensQ+"-Queens has "+k+" solutions");
		}
			
	}

	static boolean Integer(String x){ 
	        try{
		   Integer.parseInt(x);
		}catch(NumberFormatException e){
		   return true;
		}
		return false;
		}
	
	static void nextPermutation(int[] A) {
		int pivot=0, successor=0;
		for (int i = A.length-2; i >0; i--){  // check if elemt is less than its right hand neighbour
			if (A[i] < A[i+1]) {
				pivot = i;
				break;
			}
		}
		
		if (pivot == 0){
			reverse(A,1,A.length-1); // reversing the array
			return;
		}
		
		// scan array from right to left (larger than)	
		for (int j = A.length-1; j > pivot; j--){
			if (A[j] > A[pivot]){
				successor = j; 
				break; // stop scanning
			}
		}
		swap(A,pivot,successor); // swapping the pivot and succesor
		reverse(A,pivot+1,A.length-1); // reverse the portion of the array to the right off where the pivot was found
		return;
	}

	static void printArray(int []A){  // prints the contents of A[1....n]
		System.out.print("(");
		for (int i=1; i<A.length-1; i++){
			System.out.print(A[i]+", ");
		}
		System.out.print(A[A.length-1]);
		System.out.println(")");
	}
	
	static int factorial(int A){
		int allnum=1;
		for (int n=1;n<=A;n++){
			allnum *= n;
		}
		return allnum;
	}
	static void reverse(int[] A, int i, int j) {
		while(i<j){
			swap(A, i, j);
			i++;
			j--;
		}
	}
	
	static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	static boolean isSolution(int[] A){ 
		for (int a=2; a< A.length; a++){
			for (int b=1; b<a; b++){
				if ((A[a] - A[b]) == (a-b)){
					return false;
				}else if ((A[b] - A[a]) == (a-b)){
					return false;
				}
			}
		}
		return true;
	}
	

} 
