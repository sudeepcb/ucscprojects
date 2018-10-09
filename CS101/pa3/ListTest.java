//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA3
// ListTest.java
// ListTest to test List ADT
//------------------------------------------------------------------------

public class ListTest {
	
	public static void main(String[] args){

	
	List A = new List();

	List B = new List();

	List C = new List();

	for (int i=1; i<=20; i++){

		A.append(i);

		B.append(i);

		C.append(i);
	}	

	

	System.out.println("List A:");
	
	System.out.println(A);
	
	System.out.println("List B:");
	
	System.out.println(B);



	for(int i=1; i<=10; i++){

		A.prepend(i);

		B.prepend(i);

		C.prepend(i);
	}

	System.out.println("List A:");
	
	System.out.println(A);
	
	System.out.println("List B:");
	
	System.out.println(B);

	System.out.println("List C:");

	System.out.println(C);

	


	for(A.moveFront(); A.index()>=0; A.moveNext()){

		System.out.print(A.get() + " ");
	}



	for(B.moveFront(); B.index()>=0; B.moveNext()){

		System.out.print(B.get() + " ");
	}



	for(C.moveFront(); C.index()>=0; C.moveNext()){

		System.out.print(C.get() + " ");
	}



	A.append(2);

	A.moveFront();
	
	A.prepend(4);
	
	A.append(14);
	
	A.append(3);
	
	A.moveFront();


	

	B.append(2);

	B.moveFront();
	
	B.prepend(4);
	
	B.append(14);
	
	B.append(3);
	
	B.moveFront();



	C.append(2);

	C.moveFront();
	
	C.prepend(4);
	
	C.append(14);
	
	C.append(3);
	
	C.moveFront();

	

	System.out.println(A.equals(B));
	
	System.out.println(A.equals(C));
	
	System.out.println(B.equals(A));
	
	System.out.println(B.equals(C));


	System.out.println("List A:");
	
	System.out.println(A);



	System.out.println("List B:");
	
	System.out.println(B);


	System.out.println("List C:");
	
	System.out.println(C);

	System.out.println();


	System.out.println("Length A:");

	System.out.println(A.length());


	System.out.println("Length B:");
	
	System.out.println(B.length());


	System.out.println("Length C:");
	
	System.out.println(C.length());


	System.out.println("Index A:");
	
	System.out.println(A.index());
	

	System.out.println("Index B:");
	
	System.out.println(B.index());
	

	System.out.println("Index C:");
	
	System.out.println(C.index());


	System.out.println("Front A:");
	
	System.out.println(A.front());


	System.out.println("Back A:");
	
	System.out.println(A.back());

	

	System.out.println("Front B:");
	
	System.out.println(B.front());


	System.out.println("Back B:");
	
	System.out.println(B.back());


	System.out.println("Front C:");
	
	System.out.println(C.front());


	System.out.println("Back C:");
	
	System.out.println(C.back());

	
	System.out.println("Get A:");
	
	System.out.println(A.get());

	
	System.out.println("Get B:");
	
	System.out.println(B.get());

	
	System.out.println("Get C:");
	
	System.out.println(C.get());



	A.moveNext();
	
	B.moveNext();
	
	C.moveNext();

	
	System.out.println();

	
	System.out.println("Length A:");

	System.out.println(A.length());


	System.out.println("Length B:");
	
	System.out.println(B.length());


	System.out.println("Length C:");
	
	System.out.println(C.length());


	System.out.println("Index A:");
	
	System.out.println(A.index());
	

	System.out.println("Index B:");
	
	System.out.println(B.index());
	

	System.out.println("Index C:");
	
	System.out.println(C.index());


	System.out.println("Front A:");
	
	System.out.println(A.front());


	System.out.println("Back A:");
	
	System.out.println(A.back());

	

	System.out.println("Front B:");
	
	System.out.println(B.front());


	System.out.println("Back B:");
	
	System.out.println(B.back());


	System.out.println("Front C:");
	
	System.out.println(C.front());


	System.out.println("Back C:");
	
	System.out.println(C.back());

	
	System.out.println("Get A:");
	
	System.out.println(A.get());

	
	System.out.println("Get B:");
	
	System.out.println(B.get());

	
	System.out.println("Get C:");
	
	System.out.println(C.get());


	System.out.println();

	

	A.insertBefore(-1);
	
	B.insertBefore(2);

	C.insertBefore(-3);

	

	A.insertAfter(-2);
	
	B.insertAfter(1);

	C.insertAfter(-4);



	System.out.println("Index A:");

	System.out.println(A.index());
	

	System.out.println("Index B:");

	System.out.println(B.index());
	

	System.out.println("Index C:");

	System.out.println(C.index());



	System.out.println(A.equals(B));
	
	System.out.println(A.equals(C));
	
	System.out.println(B.equals(A));
	
	System.out.println(B.equals(C));


	A.delete();

	B.delete();

	C.delete();
	
	System.out.println("List A:");
	
	System.out.println(A);
	
	System.out.println("Length A:");
	
	System.out.println(A.length());

	



	A.clear();

	B.clear();

	C.clear();



	System.out.println("List A:");

	System.out.println(A);


	System.out.println("List B:");

	System.out.println(B);


	System.out.println("List C:");

	System.out.println(C);

	}

}


