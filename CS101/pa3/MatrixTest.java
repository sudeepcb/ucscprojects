//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA3
// MatrixTest.java
// Test program for Matrix.java 
//------------------------------------------------------------------------



public class MatrixTest {
	
	public static void main(String[] args) {

		int i;

		int j;

		int n = 1000;


		Matrix A = new Matrix(n);
		
		A.changeEntry(1, 1, 1);
		
		A.changeEntry(1, 2, 0);
		
		A.changeEntry(1, 3, 5);
		
		A.changeEntry(2, 1, 0);
		
		A.changeEntry(2, 2, 1);
		
		A.changeEntry(2, 3, 0);
		
		A.changeEntry(3, 1, 1);
		
		A.changeEntry(3, 2, 0);
		
		A.changeEntry(3, 3, 5);

		

		Matrix B = new Matrix(n);
		
		B.changeEntry(1, 1, 0);
		
		B.changeEntry(1, 2, 2);
		
		B.changeEntry(1, 3, 0);
		
		B.changeEntry(2, 1, 1);
		
		B.changeEntry(2, 2, 0);
		
		B.changeEntry(2, 3, 5);
		
		B.changeEntry(3, 1, 0);
		
		B.changeEntry(3, 2, 2);
		
		B.changeEntry(3, 3, 0);


		
		
		System.out.println(A.getNNZ());
		
		System.out.println("Matrix A:");
		
		System.out.println(A);


		
		System.out.println(B.getNNZ());
		
		System.out.println("Matrix B:");
		
		System.out.println(B);

		
		
		Matrix C = A.scalarMult(2.0);
		
		System.out.println(C.getNNZ());
		
		System.out.println("Matrix C:");
		
		System.out.println(C);

		
		
		Matrix D = B.scalarMult(2.0);
		
		System.out.println(D.getNNZ());
		
		System.out.println("Matrix D:");
		
		System.out.println(D);

		
		
		Matrix E = A.add(A);
		
		System.out.println(E.getNNZ());
		
		System.out.println("Matrix E:");
		
		System.out.println(E);

		
		
		Matrix F = B.add(B);
		
		System.out.println(F.getNNZ());
		
		System.out.println("Matrix F:");
		
		System.out.println(F);

		
		
		Matrix G = A.copy();
		
		System.out.println("Matrix G:");
		
		System.out.println(G);


		
		
		Matrix H = A.sub(A);

		System.out.println(H.getNNZ());

		System.out.println("Matrix H:");

		System.out.println(H);

		
		
		Matrix I = B.sub(B);

		System.out.println(I.getNNZ());

		System.out.println("Matrix I:");

		System.out.println(I);

		

		Matrix J = A.transpose();

		System.out.println(J.getNNZ());

		System.out.println("Matrix J:");

		System.out.println(J);

		

		Matrix K = new Matrix(n);

		K.changeEntry(1, 1, 1);

		K.changeEntry(1, 2, 2);

		K.changeEntry(1, 3, 3);

		K.changeEntry(2, 1, 2);

		K.changeEntry(2, 2, 2);

		K.changeEntry(2, 3, 2);

		K.changeEntry(3, 1, 3);

		K.changeEntry(3, 2, 3);

		K.changeEntry(3, 3, 3);

		System.out.println(G.getNNZ());

		System.out.println("Matrix K:");

		System.out.println(A);

		


		Matrix L = new Matrix(n);

		L.changeEntry(1,1,1);

		L.changeEntry(1,2,2);

		L.changeEntry(1,3,3);

		L.changeEntry(2,2,1);

		L.changeEntry(2,2,2);

		L.changeEntry(2,2,3);

		L.changeEntry(3,1,7);

		L.changeEntry(3,2,8);

		L.changeEntry(3,3,9);

		System.out.println(L.getNNZ());

		System.out.println("Matrix L:");

		System.out.println(L);

		


		Matrix M = K.transpose();

		System.out.println(K.getNNZ());

		System.out.println("Matrix M:");

		System.out.println(M);

		


		Matrix N = L.transpose();

		System.out.println(L.getNNZ());

		System.out.println("Matrix N:");

		System.out.println(N);


		

		Matrix O = new Matrix(n);

		O = K.add(L);

		System.out.println("Matrix O:");

		System.out.println(O);




		Matrix P = K.mult(M);

		System.out.println(P.getNNZ());

		System.out.println("Matrix P:");

		System.out.println(P);




		Matrix Q = L.mult(N);

		System.out.println(Q.getNNZ());

		System.out.println("Matrix Q:");

		System.out.println(Q);




		Matrix R = P.copy();

		System.out.println(R.getNNZ());

		System.out.println("Matrix R:");

		System.out.println(R);

		System.out.println(R.equals(P));

		System.out.println(R.equals(A));

		System.out.println(R.equals(B));

		System.out.println(R.equals(K));

		System.out.println(P.equals(K));

		System.out.println(P.equals(L));




		Matrix S = Q.copy();

		System.out.println(S.getNNZ());

		System.out.println("Matrix S:");

		System.out.println(S);

		System.out.println(S.equals(P));

		System.out.println(S.equals(A));

		System.out.println(S.equals(B));

		System.out.println(S.equals(R));

		System.out.println(S.equals(K));

		System.out.println(Q.equals(K));

		System.out.println(Q.equals(L));




		Matrix T = new Matrix(n);



		Matrix U = T.mult(P);

		System.out.println(U.getNNZ());

		System.out.println("Matrix U:");

		System.out.println(U);




		Matrix V = A.add(B);

		System.out.println(V.getNNZ());

		System.out.println("Matrix V:");

		System.out.println(V);




		Matrix W = B.add(A);

		System.out.println(W.getNNZ());

		System.out.println("Matrix W:");

		System.out.println(W);




		Matrix X = A.sub(B);

		System.out.println(X.getNNZ());

		System.out.println("Matrix X:");

		System.out.println(X);




		Matrix Y = B.sub(A);

		System.out.println(Y.getNNZ());

		System.out.println("Matrix Y:");

		System.out.println(Y);




		Matrix Z = V.scalarMult(0.5);

		System.out.println(Z.getNNZ());

		System.out.println("Matrix Z:");

		System.out.println(Z);




		A.makeZero();

		System.out.println(A.getNNZ());

		System.out.println("New Matrix A:");

		System.out.println(A);




		A.changeEntry(500, 400, 0000);

		A.changeEntry(20, 50, 0);

		A.changeEntry(500, 400, 0);

		System.out.println(A.getNNZ());

		System.out.println("Matrix A:");

		System.out.println(A);




		B.makeZero();

		System.out.println(B.getNNZ());

		System.out.println("New Matrix B:");

		System.out.println(B);	



		B.changeEntry(500, 400, 0000);

		B.changeEntry(20, 50, 0);

		B.changeEntry(500, 400, 0);

		System.out.println(B.getNNZ());

		System.out.println("Matrix B:");

		System.out.println(B);	

	}
}
