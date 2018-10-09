//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA1 
// List.java
// List ADT to aplhabetize the lines in a files
//------------------------------------------------------------------------


class List {
	
	private class Node {

		// Fields
		int data;
		
		Node previous;
		
		Node next;
		
		// Constructors
		Node(int data) {
		
			this.data = data;
		
			previous = null;
		
			next = null;
		}
		
	// Creates Node with data, previous and next 
	Node(int data, Node previous, Node next) {
		
			this.data = data;
		
			this.previous = previous;
		
			this.next = next;
		}
		
	// toString()
	// Overrides Object's toString() method
	public String toString() {
			
			return String.valueOf(data);
		}
		
	// equals
	// overrides Object's equals() method
	// function returns true if two of the Nodes data properties are the same.
      	public boolean equals(Object a) {
        
         	boolean same = false;
        
         	Node that;
        
         	if(a instanceof Node) {
        
       	     	that = (Node) a;
        
            	same = (this.data == that.data);
         }
        
         	return same;
      }
   }
	

	// Fields
	private int index;
	
	private int length;
	
	private Node frontEnd;
	
	private Node backEnd;
	
	private Node cursorIndex;


	// Constructor
	List() {
	
		index = -1;
	
		length = 0;
	
		frontEnd = backEnd =  null;
	
		cursorIndex = null;
	}

//--------------------------- Access Functions ---------------------------

	// Returns the number of elements in this List.
	int length() {
	
		return length;
	}

	// If cursor is defined, returns the index of he cursor element.
	// else returns -1
	int index() {
	
		return index;
	}

	// front(): returns front element in this list
	// Pre: length() > 0
	int front() {
	
		if(length < 1) 
	
			throw new RuntimeException("List Error: front() called on List that is empty");
	
		return frontEnd.data;
	}

	// back(): returns back element in this list
	// Pre: length() > 0
	int back() {
	
		if(length < 1) 
	
			throw new RuntimeException("List Error: back() called on List that is empty");
	
		return backEnd.data;
	}

	// get(): returns cursor element in this list
	// Pre: length() > 0, index() >= 0
	int get() {
	
		if(length < 1) 
	
			throw new RuntimeException("List Error: get() called on List that is empty");
	
		if (index < 0) 
	
			throw new RuntimeException("List Error: get() called on undefined index on List");
	
		return cursorIndex.data;
	}

	// Returns true if this List and L are the same integer
	// sequence. The cursor is ignored in both lists.
	boolean equals(List L) {
	
	if(L.length() != length) {
    
         return false;
        }
    
    	Node temporary = frontEnd;
    
      	Node checkFront = L.frontEnd;
    
        while(temporary.next !=null && checkFront.next != null) {
    
         if(!checkFront.equals(temporary))
    
            return false;
    
         checkFront = checkFront.next;
    
         temporary = temporary.next;
         }  
    
        return true;
   }

		
//-------------------------------Manipulation procedures----------------------
	
	// Resets this List to the empty state.
	void clear() {
	
		index = -1;
	
		length = 0;
	
		frontEnd = backEnd =  null;
	
		cursorIndex = null;
	}

        // If List is non-empty, places the cursor under the front
   	// element, otherwise does nothing.
   	void moveFront() {
    
      	if(length > 0) {
    
         cursorIndex = frontEnd;
    
         index = 0;
     	 }	 
   }

   	// If List is non-empty, places the cursor under the back
   	// element, otherwise does nothing.
   	void moveBack() {
    
      	if(length > 0) {  // not empty
    
         // placing at back
         cursorIndex = backEnd;

         index = length - 1;
     	 }	
   }

   	// If cursor is defined and not at front, moves cursor one step
   	// toward front of this List, if cursor is defined and at front,
  	 // cursor becomes undefined, if cursor is undefined does nothing.
  	 void movePrev() {

   		// defined and not a front
      	 if (cursorIndex != null && index != 0) {
    
         cursorIndex = cursorIndex.previous;
    
         index--;

         // defined and front
      	} else if (cursorIndex != null && index == 0) {
    
         cursorIndex = null;
    
         index = -1;
      }
   }
   
   	// If cursor is defined and not at back, moves cursor one step
   	// toward back of this List, if cursor is defined and at back,
   	// cursor becomes undefined, if cursor is undefined does nothing.
   	void moveNext() {
   	
   	// defined and not at back moving cursorIndex one step
      	if(cursorIndex != null && index != length - 1) {
    
         cursorIndex = cursorIndex.next;
    
         index++;
         
         // defined and at back cursorIndex becomes null or undefined
      	} else if (cursorIndex != null && index == length - 1) {
         
         cursorIndex = null;
         
         index = -1;
      }
   }
   
   	// Insert new element into this List. If List is non-empty,
   	// insertion takes place before the front element.
   	void prepend(int data) {
   	
   	// new element 
      	Node temporary = new Node(data, null, frontEnd);
    
      	if(frontEnd == null){
    
         backEnd = temporary;
    
      	} else { 
    
         frontEnd.previous = temporary;
     }
    
     	frontEnd = temporary;
    
     	length++;
   }

   	// Insert new element into this List. If List is non-empty,
   	// insertion takes place after back element.
   	void append(int data) {
   	
   	// new element
      	Node temporary = new Node(data, backEnd, null);
    
      	if(frontEnd== null){
    
         frontEnd = temporary;
    
      	} else { 
    
         backEnd.next = temporary;
     }
    
     	backEnd = temporary;
    
     	length++;
   }

   	// Insert new element before cursor.
   	// Pre: length() > 0, index() >= 0
   	void insertBefore(int data) {
   	// Pre: length() > 0
      	if(length < 1)
         throw new RuntimeException("List Error: insertBefore() called on an list that is empty");
    // Pre: index() >= 0  
      	if(index < 0)
         throw new RuntimeException("List Error: insertBefore() called on an undefined index on the List");
      	// new element
      	Node temporary = new Node(data, cursorIndex.previous, cursorIndex);
      
	if(cursorIndex.previous != null){

         cursorIndex.previous.next = temporary;

      	} else { 

         frontEnd = temporary;
    	 }	
        
	 cursorIndex.previous = temporary;

         length++;
   }
   
   	// Inserts new element after cursor.
   	// Pre: length() > 0, index() >= 0
   	void insertAfter(int data) {
   
      	if(index < 0)
   
         throw new RuntimeException("List Error: insertAfter() called on an undefined index on List");
   
      	if(length < 1)
   
         throw new RuntimeException("List Error: insertAfter() called on an list that is empty");
   
      	Node temporary = new Node(data, cursorIndex, cursorIndex.next);
   
      	if(cursorIndex.next != null){
   
         cursorIndex.next.previous = temporary;
   
      	} else {
   
         backEnd = temporary;
      }
   
      	cursorIndex.next = temporary;
   
      	length++; 
   }
	
	// Deletes the front element in this List. 
    // Pre: length()>0
	void deleteFront() {
	
		if(length == 0) {
	
			throw new RuntimeException("List Error: deleteFront() called on an list that is empty");
		}
	
		if(cursorIndex == frontEnd) {
	
			cursorIndex = null;
	
			index = -1;
	
		} else {
	
			index--;
		}
	
		frontEnd = frontEnd.next;
	
		frontEnd.previous = null;
	
		length--;
	}

	// Deletes the back element in this List. 
	// Pre: length()>0
	void deleteBack() {
	
		if(length == 0) {
	
			throw new RuntimeException("List Error: deleteBack() called on an list that is empty");
		}
	
		if(cursorIndex == backEnd) {
	
			cursorIndex = null;
	
			index = -1;
		}
	
		backEnd = backEnd.previous;
	
		backEnd.next = null;
	
		length--;
	}

	// Deletes cursor element in this List. Cursor is undefined after this operation. 
	// Pre: length()>0, index()>=0
	void delete() {
	
		if(index == -1) {
	
			throw new RuntimeException("List Error: delete() called on an undefined index on List");
	
		} else if (length == 0) {
	
			throw new RuntimeException("List Error: delete() called on an list that is empty");
		}
	
		if(cursorIndex == frontEnd) {
	
			deleteFront();
	
		} else if (cursorIndex == backEnd) {
	
			deleteBack();
	
		} else {
	
			Node right = cursorIndex.next;
	
			Node left = cursorIndex.previous;
	
			left.next = right;
	
			right.previous = left;
	
			cursorIndex = null;
	
			index = -1;
	
			length--;
		}
	}

//---------------------------------------Other Methods-------------------------------------------------------
   
   	// Overrides Object's toString method. Returns a String
   	// representation of this List consisting of a space
   	// separated sequence of integers, with front on left.
   	public String toString() {
    
      	Node temp = frontEnd;
    
      	String type = new String();
    
      	while(temp != null) {
    
         type = type + String.valueOf(temp.data) + " ";
    
         temp = temp.next;
    
     	 }	
    
      	return type;
   }
	

	// Returns a new list representing the same integer sequence as this List.
    // The cursor in the new list is undefined, regardless the state of cursor in list
    // This list is unchanged
	List copy() {
		
		List c = new List();
		
		Node now = frontEnd;
		
		while(now != null) {
		
			c.append(now.data);
		
			now = now.next;
		}
		
		c.length = length;
		
		return c;
	}

	// returns a new List which is the concatenation of this list follow by 
	// the cursor in the new List is undefined, regardless of the cursors in List and L.
	// the states of this List and L are unchanged.
	List concat(List L) {
		
		List ccat = copy();
		
		Node now = L.frontEnd;
		
		while(now != null) {
		
			ccat.append(now.data);
		
			now = now.next;
		}
		
		ccat.length = length + L.length;
		
		return ccat;
	}
}
