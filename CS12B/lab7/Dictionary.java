//------------------------------------------------------------------------------
// Sudeep Baniya 
// CMPS 12M
// sucbaniy
// 6/3/2016
// Binary Search Tree implementation of the Dictionary ADT
// Dictionary.java
//------------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface {

   // private inner Node class
   private class Node {
      
      Pair item;
      
      Node left;
      
      Node right;

      Node(Pair x){
      
         item = x;
      
         left = null;
      
         right = null;
      }
   }

    // private inner Pair class
    private class Pair{
      
      String key;
      
      String value;
      
      Pair(String x, String y){
      
        key = x;
      
        value = y;
      }
    }

   // Fields for the DictionaryBST class
   private Node root;     // root = first node on list
   
   private int numItems;  // total number of items in the list

   // Dictionary()
   // constructor for the DictionaryBST class
   public Dictionary(){
   
      root = null;
   
      numItems = 0;
   }


   //helper functions ------------------------------------------------

   // findKey()
   // returns the Node containing key k in the subtree root, or returns NULL if no such Node exists
   private Node findKey(Node R, String targetKey){ 
   
      if(R == null || R.item.key.equals(targetKey)){
   
      return R;
      }
   
      if(R.item.key.compareToIgnoreCase(targetKey)>0){
   
      return findKey(R.left, targetKey);
   
      }else{
   
      return findKey(R.right, targetKey); 
   }
}
    
   // findParent()
   // returns the parent of N in the subtree rooted at R, or returns NULL if N is equal to R. (pre: R != NULL)
   Node findParent(Node N, Node R){
   
   Node P = null;
   
   if( N!=R ){
   
     P = R;
   
     while( P.left != N && P.right !=N ){
   
       if(N.item.key.compareToIgnoreCase(P.item.key)<0){
   
         P = P.left;
   
       }else{
   
         P = P.right;
     }
   }
   }
   
   return P;
   }

   // findLeftmost()
   // returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
   Node findLeftmost(Node R){
   
   Node L = R;
   
   if( L!= null ) for( ; L.left != null; L = L.left);
   
   return L;
   
   }
   
   
   // printInOrder()
   // prints the (key, value) pairs belonging to the subtree rooted at R in order
   // of increasing keys to file pointed to by out.
   void printInOrder(Node R){
   
   if( R != null ){
   
      printInOrder(R.left);
   
      System.out.println(R.item.key + " " + R.item.value);
   
      printInOrder(R.right);
   }
   }
    
    // deleteAll()
    // deletes all Nodes in the subtree rooted at N.
    void deleteAll(Node N){
   
      if( N != null ){
   
      deleteAll(N.left);
   
      deleteAll(N.right);
      }
    }
    
    
   // BST ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
   
      return(numItems == 0);
   
   }

   // size()
   // pre: none
   // returns the number of elements in this Dictionary
   public int size() {
   
      return numItems;
   }

   // lookup
   // pre: none
   // returns value associated key, or null reference if no such key exists
    public String lookup(String key){
   
      Node N;
   
      N = findKey(root, key);
   
      return ( N == null ? null : N.item.value );
    }


   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
    public void insert(String key, String value) throws DuplicateKeyException{
      
      Node N, A, B;
      
      if(findKey(root, key)!=null){
      
        throw new DuplicateKeyException("Dictionary Error: insert() cannot insert when duplicate keys occur");
      
      }
      
      N = new Node(new Pair(key, value));
      
      B = null;
      
      A = root;
      
      while( A != null ){
      
        B = A;
      
        if(A.item.key.compareToIgnoreCase(key)>0)  A = A.left;
      
       else A = A.right;
      
      }

      if( B == null ) root = N;
      
      else if(B.item.key.compareToIgnoreCase(key)>0)  B.left = N;
      
      else  B.right = N;
      
       numItems++;
    }
    

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
    public void delete(String key) throws KeyNotFoundException{
      
      Node N, P, S;
      
      if(findKey(root, key)==null){
      
        throw new KeyNotFoundException("Dictionary Error: delete() cannot delete a key that does not exist");
      }
      
      N = findKey(root, key);
      
      if( N.left == null && N.right == null ){ // case 1
      
      if( N == root ){
      
        root = null;
      
      }else{
      
        P = findParent(N, root);
      
        if( P.right == N ) P.right = null;
      
        else P.left = null;
      
      }
      
      }else if( N.right == null ){ // case 2 (left but no right child) 
      
      if( N == root ){
      
        root = N.left;
      
      }else{
      
        P = findParent(N, root);
      
        if( P.right == N ) P.right = N.left;
      
        else P.left = N.left;
      
      }
      
      }else if( N.left == null ){   // case 2 (right but no left child)
      
      if( N == root ){
      
         root = N.right;
      
      }else{
      
         P = findParent(N, root);
      
         if( P.right == N ) P.right = N.right;
      
         else P.left = N.right;
      
      }
      
      }else{  // case 3: N.left!=null && N.right!=null
      
        S = findLeftmost(N.right);
      
        N.item.key = S.item.key;
      
        N.item.value = S.item.value;
      
        P = findParent(S, N);
      
        if( P.right == S ) P.right = S.right;
      
        else P.left = S.right; 
      
      }
      
      numItems--;
    }
        
   // makeEmpty
   // pre: none
    public void makeEmpty(){
      
      deleteAll(root);
      
      root = null;
      
      numItems = 0;
    }

   // toString()
   // Overrides Object's toString() method
   // pre: none
    public String toString(){
    
      printInOrder(root);
    
      return "";
    
    }
}
