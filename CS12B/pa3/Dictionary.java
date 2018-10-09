//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 4-28-2016
// The programs implements a Dictionary ADT based on the linked list data strucure.
// Dictionary.java
//------------------------------------------------------------------------------
public class Dictionary implements DictionaryInterface {

  // private inner Node class
  private class Node {
    Node next;
    String key;
    String value;

    // node class constructor
    Node(String key, String value) {
      next = null;
      this.key = key;
      this.value = value;
    }
  }

  // Fields for the Dictionary class
  private Node first;      // refers to first Node in Dictionary
  private int numItems;   // the number of items in this Dictionary

  // Dictonary()
  // constructor for the Dictionary class
  public Dictionary() {
    first = null;
    numItems = 0;
  }

  // helper functions -------------------------------------------------

   // findKey()
   // returns a reference to the Node at containing its argument key or else return null
   private Node findKey(String key) {
     Node N = first;
     while (N != null){
       if(N.key.equals(key)){
         break;
       }else{ 
          N = N.next;
       }
   }
    return N;
}
  
  // below contains isEmpty(),size(),lookup(),insert(),delete(),makeEmpty(),toString()

   // isEmpty()
   // pre: none
   // returns true if dictionary contains no pairs and returns falls other wise
   public boolean isEmpty() {
     return (numItems==0);
   }

   // size()
   // pre: none
   // returns the number of entries in the dictionary
   public int size() {
     return numItems;
   }

   // lookup()
   // pre: none
   // if contains a pair who key machtes the argument key, it returns the field else returns null
   public String lookup(String key) {
     Node N = first;
     while (N != null){
       if (N.key.equals(key)){
         return N.value;
       }
       N = N.next;
     }
     return null;
   }

   // insert()
   // inserts a new (key,value) if it is still not int the list
   // pre: lookup(key)==null
   public void insert(String key, String value) 
   throws DuplicateKeyException {
     if (lookup(key) != null){
       throw new DuplicateKeyException("cannot insert duplicate keys");
     }else{
      if( first == null){
        Node N = new Node(key,value);
        first = N;
        numItems++;
      }else{
        Node N = first;
        while( N != null){
        if(N.next == null){
        break;
        }
        N = N.next;
        }
        N.next = new Node(key,value);
        numItems++;
      }
    }
  }

  // delete()
  // if the pair matches the key then it is deleted from the dictionary
  // pre: lookup(key)!=null
  public void delete(String key) throws KeyNotFoundException {
    
    if( lookup(key) == null){
      throw new KeyNotFoundException("cannot delete non-existent key");
    } else {
      if(findKey(key) == first){
      Node N = first;
      first = first.next;
      N.next = null;
    }else{
      Node N = findKey(key);
      Node back = first;
      Node temporary = first.next;
      while (temporary != N){
        temporary = temporary.next;
        back = back.next;
      }
      back.next = N.next;
      N.next = null;
    }
    numItems--;
  }
}

  // makeEmpty()
  // pre: none
  // return the dictionary to have nothing
  public void makeEmpty(){
    first = null;
    numItems = 0;
  }

  // toString()
  // returns a String representation of this Dictionary
  // overrides Object's toString() method
  // pre: none
  public String toString() {
    StringBuffer sb = new StringBuffer();
    Node N = first;
    for(N = first; N != null; N = N.next){ 
    sb.append(N.key).append(" ").append(N.value).append("\n");
  }
  return new String(sb);
 }
}
