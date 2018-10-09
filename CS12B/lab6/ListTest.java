//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12M
// sucbaniy
// 5-24-2016
// Test program for List.java
// ListTest.java
//-------------------------------------------------------------------------------

public class ListTest{

	public static void main(String[] args){

	List<String> A = new List<String>();

	List<String> B = new List<String>();

	List<List<String>> C = new List<List<String>>();
	

	System.out.println("A: "+A);

    	System.out.println("B: "+B);

    	System.out.println("C: "+C);

    	A.add(1, "twenty");

    	A.removeAll();

	
    	A.add(1, "two");

    	A.add(2, "three");

    	A.add(3, "four");

    	B.add(1, "five");

    	B.add(2, "six");

    	B.add(3, "seven");

    	C.add(1, A);

    	C.add(2, B);

    	C.add(3, A);

    	System.out.println("A: "+A);

    	System.out.println("B: "+B);

    	System.out.println("C: "+C);

    	A.remove(1);

    	B.remove(2);

    	C.remove(3);

   	System.out.println("A: "+A);

    	System.out.println("B: "+B);

    	System.out.println("C: "+C);

    	System.out.println("A.equals(A) is "+A.equals(A));

    	System.out.println("A.equals(B) is "+A.equals(B));

    	System.out.println("A.equals(C) is "+A.equals(C));

    	System.out.println("A.size() is "+A.size());

    	System.out.println("B.size() is "+B.size());

    	System.out.println("C.size() is "+C.size());


    	A.add(1, "twenty");
    	B.add(2, "fifty");

    	System.out.println("A.size() is "+A.size());

    	System.out.println("B.size() is "+B.size());

    	System.out.println("B.get(1) is "+B.get(1));

    	System.out.println("C: "+C);

    	System.out.println();
    
    	try{

        	 System.out.println(A.get(200));

      	}catch(ListIndexOutOfBoundsException e){

        	System.out.println("Caught Exception: ");

         	System.out.println(e);

     	    	System.out.println("Continuing without interuption");
      }

	}
}
