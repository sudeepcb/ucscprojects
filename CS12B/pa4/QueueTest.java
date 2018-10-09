//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 5-15-2016
// The programs that test Queue ADT
// QueueTest.java
//------------------------------------------------------------------------------
public class QueueTest{
  public static void main (String[] args){
    Queue A = new Queue();

    //inserts
    A.enqueue((int)1);
    A.enqueue((int)2);
    A.enqueue((int)3);
    A.enqueue((int)4);
    A.enqueue((int)5);
    A.enqueue((int)6);
    A.enqueue((int)7);
    A.enqueue((int)8);
    A.enqueue((int)9);
    A.enqueue((int)10);
    
    System.out.println(A);
    System.out.println();
    System.out.println(A.peek());
    A.dequeue(); 
    A.dequeue();
    System.out.println(A.isEmpty());

    System.out.println(A.isEmpty());
    System.out.println(A.length());
    
    System.out.println(A);
    System.out.println(A.length());
    System.out.println(A);
    
    try{
    A.dequeueAll();
    }catch(QueueEmptyException e){
    System.out.println("Caught Exception " + e);
    System.out.println("Continuing without interuption");
    }
    
    Queue B = new Queue();
    B.enqueue((int)1);
    B.enqueue((int)2);
    B.enqueue((int)3);
    B.enqueue((int)4);
    B.enqueue((int)5);
    B.enqueue((int)6);
    B.enqueue((int)7);
    B.enqueue((int)8);
    B.enqueue((int)9);
    B.enqueue((int)10);
    
    System.out.println(B);
    System.out.println();
    System.out.println(B.peek());
    B.dequeue(); 
    B.dequeue();
    System.out.println(B.isEmpty());

    System.out.println(B.isEmpty());
    System.out.println(B.length());
    
    System.out.println(B);
    System.out.println(B.length());
    System.out.println(B);
    
    try{
    B.dequeueAll();
    }catch(QueueEmptyException e){
    System.out.println("Caught Exception " + e);
    System.out.println("Continuing without interuption");
    }
  }
}