//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA3
// Matrix.java
// Calculates various applications for Matricies
//------------------------------------------------------------------------

 public class Matrix {

   private class Entry {
      // Fields
      int position;
      
      double value;

      // Default Constructor
      Entry() {
	
	position = 0;
	
	value = 0;
	}

      // Overloaded Constructor
      Entry(int position, double value) {
    
         this.position = position;
    
         this.value = value;
      }
      
      // String representation of the data
      public String toString() {
    
         return "(" + position + ", " + value + ")";
      }
      
      // Returns true if two the two entriees are equal.
      public boolean equals(Object x) { 
		 
  	 	 Entry other = (Entry) x;
         
         return (this.position == other.position && this.value == other.value);
	}
 }

   List[] column;
 
   int size;
   
   // makes a new n x n zero Matrix.
   // Pre: n >= 1
    Matrix(int n){
 
      if(n < 1)
  
         throw new RuntimeException("Matrix Error: Matrix() size must e greater than or equal to 1.");
     
      column = new List[n + 1];
  
      size = n;
  
      for(int i = 1; i <= size; i++) {
  
         column[i] = new List();
  
      }
   }

   // returns n
   // the number of rows and columns of this Matrix.
   int getSize() {
  
      return size;
   }
   
   // returns the number of non-zero entries in this Matrix.
   int getNNZ() {
  
      int numEntries = 0;
  
      for(int i = 1; i <= getSize(); i++) {
  
         numEntries += column[i].length();
  
      }
  
      return numEntries;
   }
   
   // Overrides Object's equals() method.
   public boolean equals(Object M) {

      if(!(M instanceof Matrix)) {

         throw new RuntimeException("Matrix Error: equals() called with no matrix parameter");
      }

      Matrix other = (Matrix) M;

      if(M instanceof Matrix) {
         // check if equivalent size
         if(size != other.getSize())

            return false;
         
	 for(int i = 1; i <= size; i++) {

            if(!(column[i].equals(other.column[i])))

               return false;
            }
         }

      return true;
   }
   
   // sets this Matrix to the zero state.
   void makeZero() {

      column = new List[size+1];

      for(int i = 1; i <= size; i++) {

         column[i] = new List();

      }
   }

   // returns a new Matrix having the same entries as this Matrix.
   Matrix copy() {

      Matrix mxCpy = new Matrix(size);

      for(int i = 1; i <= mxCpy.getSize(); i++) {

         column[i].moveFront();

         while(column[i].index() >= 0) {

            Entry temporary = (Entry) column[i].get();

            mxCpy.changeEntry(i, temporary.position, temporary.value);

            column[i].moveNext();

         }

      }

      return mxCpy;
   }
   
   // changes ith row, jth column of this Matrix to x.
   // Pre: 1<=i<=getSize(), 1<=j<= getSize()
   void changeEntry(int i, int j, double x) {

      if(i < 1 || i > size){ 

         throw new RuntimeException("Matrix Error: changeEntry() called on invalid row number");
      }
      
      if(j < 1 || j > size){

         throw new RuntimeException("Matrix Error: changeEntry() called on invalid column number");
      }

      boolean check = false;

      column[i].moveFront();

      Entry E = new Entry(j,x);

      while(column[i].index() > -1) {

         Entry data = (Entry) column[i].get();

         check = (data.position == j ? true : false);

         if(check) {

            if(x == 0) {

               column[i].delete(); 

               return;

            } else { 

               data.value = x; 

               return;
            }
         }

         column[i].moveNext();
      }

      if(!check && x != 0.0) {

         column[i].moveFront();

         if(column[i].index() == -1) {

            column[i].append(E); 

            return;

         } else {

            while(column[i].index() > -1 && ((Entry)column[i].get()).position < j) {

               column[i].moveNext();
            }

            if(column[i].index() >= 0) {
               
               column[i].insertBefore(E); 

               return;

            } else {

               column[i].append(E); 
               
	    		return;
            }
         }
      }
   }

   // returns a new Matrix that is the scalar product of this Matrix with x
   Matrix scalarMult(double x) {
      
      Matrix product = this.copy();

      for(int i = 1; i <= product.getSize(); i++) {
      
         product.column[i].moveFront();
      
         while(product.column[i].index() >= 0) {
      
            Entry temporary = (Entry) product.column[i].get();
      
            product.changeEntry(i, temporary.position, (x * temporary.value));
      
            product.column[i].moveNext();
         }
      }
      
      return product;
   }

   // returns a new Matrix that is the sum of this Matrix with M.
   // Pre: getSize()== M.getSize()
   Matrix add(Matrix M) {
      
      if(this.size != M.getSize()){
      
         throw new RuntimeException("Matrix Error: add() called on undefined Matrix size");
      }
      
      if(M == this){
      
         return this.copy().scalarMult(2);
      }
      
      Matrix addition = new Matrix(this.size);
      
      for(int i = 1; i <= this.size; i++) {
      
          addition.column[i] = addsub(column[i], M.column[i], true);
      }
      
      return addition;
   }
   
   // returns a new Matrix that is the difference of this Matrix with M
   // Pre: getSize()==M.getSize()
   Matrix sub(Matrix M) {
      
      if(this.size != M.getSize()){
      
         throw new RuntimeException("sub() called on incompatible Matriciess");
      }
      
      if(M == this) {
      
         return new Matrix(this.size);
      }
      
      Matrix subtract = new Matrix(this.size);
      
      for(int i = 1; i <= getSize(); i++) {
      
         subtract.column[i] = addsub(column[i], M.column[i], false);
      }
      
      return subtract;
   }

   // returns a new Matrix that is the transpose of this Matrix
   Matrix transpose() {
      
      int j;
      
      double x;
      
      Matrix trans = new Matrix(this.size);
      
      for(int i = 1; i <= trans.getSize(); i++) {
      
         column[i].moveFront();
      
         while(column[i].index() != -1) {
      
            Entry data = (Entry)column[i].get();
      
            j = data.position;
      
            x = data.value;
      
            trans.changeEntry(j, i, x);
      
            column[i].moveNext();
         }
      }
      
      return trans;
   }

   // returns a new Matrix that is the product of this Matrix with M
   // Pre: getSize()==M.getSize()
   Matrix mult(Matrix M) {
      
      if(this.size != M.getSize()){
      
         throw new RuntimeException("mult() called on incompatible Matricies");
      }
      
      Matrix that = M.transpose();
      
      Matrix prod = new Matrix(this.size);
      
      for(int i = 1; i <= size; i++) {
      
        // check length of colum is > 0
         // zero DNE compatibale dot product
         if(column[i].length() > 0) {  
     
         for(int j = 1; j <= size; j++) {
     
            // check that the length of the columns j > 0
            if(that.column[j].length() > 0){
     
               double last = dot(column[i], that.column[j]);
     
               prod.changeEntry(i, j, last);
            }
         }
         }
      }
      
      return prod;   
   }

//-----------------------------OtherFunctions--------------------------------------------

   // toString() : overrides Object's toString() method
   // print a matrix in the form [line#]: [(position,data)]
   public String toString() {
      
      String strMatrix = "";
      
      for(int i = 1; i <= size; i++) {
      
         if(column[i].length() != 0){
      
            strMatrix += (i + ": " + column[i] + "\n"); 
         }
      }
      
      return strMatrix;
   }


   //------------------------------------------------------------HelperFunctions------------------------------------------------------------------------


   // private helper function for add() ,sub()
   // adds two Entry lists together
   private List addsub(List P, List Q, boolean doaddsub) {
   
      List L = new List();
   
      Entry pCursor;
   
      Entry qCursor; 
   
      P.moveFront();
   
      Q.moveFront();

         while(P.index() > -1 && Q.index() > -1) {

            pCursor = (Entry) P.get();

            qCursor = (Entry) Q.get();

            if(pCursor.position == qCursor.position) {

               if((doaddsub && pCursor.value + qCursor.value != 0) || (!doaddsub && pCursor.value - qCursor.value != 0)){
			
	       	Entry rCursor = new Entry(pCursor.position, (doaddsub ? pCursor.value+qCursor.value : pCursor.value - qCursor.value));
            	
            	    L.append(rCursor);
	       }
               P.moveNext();
               
               Q.moveNext();
            
            } else if(pCursor.position > qCursor.position) {
            
               Entry rCursor = new Entry(qCursor.position,(doaddsub ? 1.0 : -1.0) * qCursor.value);
            
               L.append(rCursor);
            
               Q.moveNext();
            
            } else if(pCursor.position < qCursor.position) {
            
               Entry rCursor = new Entry(pCursor.position,qCursor.value);
            
               L.append(rCursor);
            
               P.moveNext();
            }
         }

         while (P.index() != -1 || Q.index() != -1){
           
           if (P.index() != -1) {
           
            pCursor = (Entry) P.get();
           
            Entry rCursor = new Entry(pCursor.position, pCursor.value);
           
            L.append(rCursor);
           
            P.moveNext();
         
         } else if (Q.index() != -1) {
         
            qCursor = (Entry) Q.get();
         
            Entry rCursor = new Entry(qCursor.position, (doaddsub ? 1.0 : -1.0) * qCursor.value);
         
            L.append(rCursor);
         
            Q.moveNext();
         }
      }
      
      return L;
   }


   
   // helper method that takes two Lists and calculates its dot product
   // returns a scalar value 
   private static double dot(List P, List Q) {
      
      Entry pCursor;
      
      Entry qCursor;
      
      P.moveFront();
      
      Q.moveFront();
      
      double total = 0;
      
      while(P.index() > -1 && Q.index() > -1) {
      
         pCursor= (Entry) P.get();
      
         qCursor = (Entry) Q.get();
      
         if(pCursor.position > qCursor.position) {
      
            Q.moveNext();
      
         } else if(pCursor.position < qCursor.position) {
      
            P.moveNext();
      
         } else {
      
            total = total +(pCursor.value * qCursor.value);
      
            P.moveNext();
      
            Q.moveNext();
         }
      }
    
      return total;
   }

}   
   
