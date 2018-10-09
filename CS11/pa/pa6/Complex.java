//-----------------------------------------------------------------------------
// Complex.java
// Sudeep Baniya
// sucbaniy
// pa6
// Represents complex numbers as a pair of double
// Fill in the function definitions below. See notes from 3-2-16 for the 
// definitions of the complex arithmetic operations.
//-----------------------------------------------------------------------------

class Complex{

   //--------------------------------------------------------------------------
   // Private Data Fields 
   //--------------------------------------------------------------------------
   private double re;
   private double im;
   
   //--------------------------------------------------------------------------
   // Public Constant Fields 
   //--------------------------------------------------------------------------
   public static final Complex ONE = Complex.valueOf(1,0);
   public static final Complex ZERO = Complex.valueOf(0,0);
   public static final Complex I = Complex.valueOf(0,1);

   //--------------------------------------------------------------------------
   // Constructors 
   //--------------------------------------------------------------------------
   Complex(double a, double b){
      this.re = a;
      this.im = b;
   }

   Complex(double a){
      this.re = a;
      this.im = 0;
   }

   Complex(String s){
      double[] C = parseComplex(s);
      re = C[0];
      im = C[1];
      // Fill in this constructor.
      // It should accept expressions like "-2+3i", "2-3i", "3", "5i", etc..
      // Throw a NumberFormatException if s cannot be parsed as Complex.
   }

   //---------------------------------------------------------------------------
   // Public methods 
   //---------------------------------------------------------------------------

   // Complex arithmetic -------------------------------------------------------

   // copy()
   // Return a new Complex equal to this Complex
   Complex copy(){
      return new Complex(this.re,this.im);
      // Fill in
   }
   
   // add()
   // Return a new Complex representing the sum this plus z.
   Complex add(Complex z){
      return new Complex(this.re+z.re ,this.im+z.im);
      // Fill in
   }
   
   // negate()
   // Return a new Complex representing the negative of this.
   Complex negate(){
      double negative = -1.0;
      return new Complex(this.re*negative,this.im*negative);
      // Fill in
   }

   // sub()
   // Return a new Complex representing the difference this minus z.
   Complex sub(Complex z){
      return new Complex(this.re-z.re, this.im-z.im);
      // Fill in
   }

   // mult()
   // Return a new Complex representing the product this times z.
   Complex mult(Complex z){
      double product1 = (z.re*this.re-this.im*z.im);
      double product2 = (z.re*this.im+z.im*this.re);
      return new Complex(product1,product2);
      // Fill in
   }

   // recip()
   // Return a new Complex representing the reciprocal of this.
   // Throw an ArithmeticException with appropriate message if 
   // this.equals(Complex.ZERO).
   Complex recip(){
      if (this.equals(Complex.ZERO)){
         throw new ArithmeticException("The problem cannot be divided by zero.");
      }
      return new Complex(re/((re*re)+(im*im)), -im/((re*re)+im*im));
      // Fill in
   }

   // div()
   // Return a new Complex representing the quotient of this by z.
   // Throw an ArithmeticException with appropriate message if 
   // z.equals(Complex.ZERO).
   Complex div(Complex z){
      if (z.equals(Complex.ZERO)){
         throw new ArithmeticException("The problem cannot be divided by zero.");
      }    
         double quotient1 = ((this.re*z.re)+(this.im*z.im))/((z.re*z.re)+(z.im*z.im));
         double quotient2 = ((z.re*this.im)-(z.im*this.re))/((z.re*z.re)+(z.im*z.im));
         return new Complex(quotient1,quotient2);
      }
      // Fill in
   

   // conj()
   // Return a new Complex representing the conjugate of this Complex.
   Complex conj(){
      return new Complex(re,-im);
      // Fill in
   }
   
   // Re()
   // Return the real part of this.
   double Re(){
      return re;
   }

   // Im()
   // Return the imaginary part of this.
   double Im(){
      return im;
   }

   // abs()
   // Return the modulus of this Complex, i.e. the distance between 
   // points (0, 0) and (re, im).
   double abs(){
      double distance = (re-0)*(re-0) + (im-0)*(im-0);
      return Math.sqrt(distance);
      // Fill in
   }

   // arg()
   // Return the argument of this Complex, i.e. the angle this Complex
   // makes with positive real axis.
   double arg(){
      return Math.atan2(im, re);
   }

   // Other functions ---------------------------------------------------------
   
   // toString()
   // Return a String representation of this Complex.
   // The String returned will be readable by the constructor Complex(String s)
   public String toString(){
      if ( im > 0 && re > 0){
            return re+"+"+im+"i";
         }else if (im == 0 && re > 0){
            return re+" ";
         }else if (im < 0 && re > 0){
            return +re+"-"+(-im)+"i";           
         }else if ( im > 0 && re < 0){
            return re+"+"+im+"i";
         }else if (im == 0 && re < 0){
            return re+" ";
         }else if (im < 0 && re < 0){
            return +re+"-"+(-im)+"i";
         }
         return +im+"i";
      // Fill in
   }

   // equals()
   // Return true iff this and obj have the same real and imaginary parts.
   public boolean equals(Object obj){
      Complex k = (Complex) obj;
      return( this.re==k.re && this.im==k.re);
      // Fill in
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part b.
   static Complex valueOf(double a, double b){
      return new Complex(a,b);
      // Fill in
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part 0.
   static Complex valueOf(double a){
      return new Complex(a);
      // Fill in
   }

   // valueOf()
   // Return a new Complex constructed from s.
   static Complex valueOf(String s){
      // Fill in
      return new Complex(s);
   }
    // parseComplex()
   // Returns a double[] of length 2 containing (real, imaginary) parts
   // of a complex number represented by string argument str.  Throws a
   // NumberFormatException if str cannot be parsed as a complex number.
   static double[] parseComplex(String str){
      double[] part = new double[2];
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         part[0] = Double.parseDouble(s);
         part[1] = 0;
      }else if( s.matches(SGN+I) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   //  s.matches(REAL+OP+NUM+I) 
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
      return part;
   }
   
}
