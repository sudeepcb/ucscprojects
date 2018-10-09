//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 4-28-2016
// The programs that tests Dictionary.java
// DictionaryTest.java
//------------------------------------------------------------------------------
public class DictionaryTest{
      public static void main(String[] args){
      Dictionary test = new Dictionary();
      System.out.println(test.isEmpty());
      System.out.println(test);
      System.out.println(test.size());
      test.insert("2","f");
      System.out.println(test);
      test.insert("3","h");
      System.out.println(test);
      System.out.println(test.isEmpty());
      try{
      test.insert("2","f"); 
      }catch(DuplicateKeyException e){
      System.out.println("Caught Exception " + e);
      System.out.println("Continuing without interuption");
      }
      System.out.println(test);
      System.out.print(test.size());
      System.out.println();
      test.delete("2");
      System.out.println(test);
      System.out.print(test.size());
      System.out.println();
      test.makeEmpty();
      System.out.println(test);
      test.insert("1","z");
      System.out.println(test);
      test.insert("2","x");
      System.out.println(test);
      test.insert("3","3");
      System.out.println(test);
      test.insert("4","y");
      System.out.println(test);
      test.insert("5","m");
      System.out.println(test);
      test.insert("6","o");
      System.out.println(test);
      try{
      test.delete("50");
      }catch(KeyNotFoundException e){
      System.out.println("Caught Exception" + e);
      System.out.println("Continuing without interuption");
    }
  }
 } 


