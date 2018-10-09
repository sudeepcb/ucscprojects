//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA5 
// List.cjava
// List ADT to aplhabetize the lines in a files
//------------------------------------------------------------------------


#include <stdio.h>
#include <stdlib.h>
#include "List.h"
#include "Graph.h"

// structures ------------------------------------------------------------

//private NodeObj type
typedef struct NodeObj{

		// Fields
		int data;
		
		struct NodeObj* previous;
		
		struct NodeObj* next;
		
	} NodeObj;

	// private ListObj type
	typedef NodeObj* Node;

	// private ListObj
	typedef struct ListObj{

		// Fields
		int index;
	
		int length;
	
		Node frontEnd;
	
		Node backEnd;
	
		Node cursorIndex;

	} ListObj;

	// Creates Node with data, previous and next
	Node newNode(int data, Node previous, Node next) {

			Node N = malloc(sizeof(NodeObj));
		
			N->data = data;
		
			N->previous = previous;
		
			N->next = next;

			return(N);
		}

	// frees heap memory
	void freeNode(Node* pN){

   		if( pN != NULL && *pN != NULL ){

      			free(*pN);
      		
      			*pN = NULL;
     		 }
  }

  	// returns reference to List that is empty
  	List newList(void){
  		List L;

  		L = malloc(sizeof(ListObj));

  		L->index = -1;
	
		L->length = 0;
	
		L->frontEnd =  NULL;
	
		L->backEnd = NULL;

		L->cursorIndex = NULL;

		return(L);
  	}

  	// frees of Heap Memory
	void freeList(List* pL){
   		
   	if(pL!=NULL && *pL!=NULL) { 
      	
      		Node temp = (*pL)->frontEnd;
		
		while(temp != NULL){
			
			Node current = temp;
			
			temp = temp->next;
		
			free(current);
		}
		
		free(*pL);	
		
		*pL = NULL;
	}	
}		


//--------------------------- Access Functions ---------------------------

	// Returns the number of elements in this List.
	int length(List L) {
    	
		if (L == NULL){
    		
		printf("List Error: calling length(List L) on NULL List reference\n");
    		
		exit(1);
    	}	

		return L->length;
	}

	// If cursor is defined, returns the index of he cursor element.
	// else returns -1
	int index(List L) {
		
		if (L == NULL){
    		
		printf("List Error: calling index(List L) on NULL List reference\n");
    		
		exit(1);
    	}
		
		return L->index;
	}

	// front(): returns front element in this list
	// Pre: length() > 0
	int front(List L) {
		
		if (L == NULL){
    		
		printf("List Error: calling front(List L) on NULL List reference\n");
    		
		exit(1);
    	}
	
		if(L->length < 0){ 
	
			printf("List Error: front() called on List that is empty\n");

			exit(1);
		}	
		
		return L->frontEnd->data;
	}

	// back(): returns back element in this list
	// Pre: length() > 0
	int back(List L) {

		if (L == NULL){

    		printf("List Error: calling back(List L) on NULL List reference\n");
    		
    		exit(1);
    	}
		
		if(L->length < 1){
	
			printf("List Error: back() called on List that is empty\n");
			
			exit(1);

		}
		
		return L->backEnd->data;
	}

	// get(): returns cursor element in this list
	// Pre: length() > 0, index() >= 0
	int get(List L) {

		if (L == NULL){

    		printf("List Error: calling get(List L) on NULL List reference\n");
    		
    		exit(1);
    	}
	
		if(L->index < 0){ 
	
			printf("List Error: get() called on List that is empty\n");

			exit(1);

		}
	
		if (L->length < 1){ 
	
			printf("List Error: get() called on undefined index on List\n");

			exit(1);

		}
	
		return L->cursorIndex->data;
	}

	// Returns true if this List and L are the same integer
	// sequence. The cursor is ignored in both lists.
	int equals(List A, List B) {

		
		if( A == NULL || B == NULL){
			
			printf("List Error: calling equals() on NULL List reference\n");
			
			exit(1);
		}

		if(A->length != B->length) {

			return 0;
        }
    
    	Node temporary = A->frontEnd;
    
      	Node checkFront = B->frontEnd;

      	while(checkFront->next != NULL && temporary->next != NULL) {
    
         if(checkFront->data != temporary->data)
    
            return 0;
    
         checkFront = checkFront->next;
    
         temporary = temporary->next;
         }  
    
        return 1;
   }

		
//-------------------------------Manipulation procedures----------------------
	
	// Resets this List to the empty state.
	void clear(List L) {

		if (L == NULL){

    		printf("List Error: calling clear(List L) on NULL List reference\n");
    		
    		exit(1);
    	}
	
	Node fTemporary = L->frontEnd;
	
	while(fTemporary != NULL){
	
		Node temporary = fTemporary;
		
		fTemporary = fTemporary->next;
		
		free(temporary);
	
	}

	L->index = -1;
	
	L->length = 0;

	L->frontEnd = NULL;
	
	L->backEnd = NULL;

	L->cursorIndex = NULL;
}

    // If List is non-empty, places the cursor under the front
   	// element, otherwise does nothing.
   	void moveFront(List L) {

   		if (L == NULL){
    	
    		printf("List Error: calling movefront(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
    
      	if(L->length > 0) {
    
         L->cursorIndex = L->frontEnd;
    
         L->index = 0;

     	 }	 
   }

   	// If List is non-empty, places the cursor under the back
   	// element, otherwise does nothing.
   	void moveBack(List L) {

   		if (L == NULL){
    		
    		printf("List Error: calling moveBack(List L) on NULL List reference\n");
    		
    		exit(1);
    	}
    
      	if(L->length > 0) {  // not empty
    
         // placing at back
         L->cursorIndex = L->backEnd;

         L->index = L->length - 1;
     	 
     	 }	
   }

   	// If cursor is defined and not at front, moves cursor one step
   	// toward front of this List, if cursor is defined and at front,
  	 // cursor becomes undefined, if cursor is undefined does nothing.
  	 void movePrev(List L) {

  	 	if (L == NULL){
    	
    		printf("List Error: calling movePrev(List L) on NULL List reference\n");
    	
    		exit(1);
    	}

   		// defined and not a front
      	 if (L->cursorIndex != NULL && L->index != 0) {
    
         L->cursorIndex = L->cursorIndex->previous;
    
        --L->index;

         // defined and front
      	} else if (L->cursorIndex != NULL && L->index == 0) {
    
         L->cursorIndex = NULL;
    
         L->index = -1;
      }
   }
   
   	// If cursor is defined and not at back, moves cursor one step
   	// toward back of this List, if cursor is defined and at back,
   	// cursor becomes undefined, if cursor is undefined does nothing.
   	void moveNext(List L) {

   		if (L == NULL){
    	
    		printf("List Error: calling moveNext(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
   	
   	// defined and not at back moving cursorIndex one step
      	if(L->cursorIndex != NULL && L->cursorIndex != L->backEnd) {
    
         L->cursorIndex = L->cursorIndex->next;
    
         ++L->index;
         
         // defined and at back cursorIndex becomes null or undefined
      	} else if (L->cursorIndex != NULL && L->cursorIndex == L->backEnd) {
         
         L->cursorIndex = NULL;
         
         L->index = -1;
      }
   }
   
   	// Insert new element into this List. If List is non-empty,
   	// insertion takes place before the front element.
   	void prepend(List L, int data) {

   		if (L == NULL){
    	
    		printf("List Error: calling prepend(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
   	
   		// new element 
      	Node temporary = newNode(data, NULL, L->frontEnd);
    
      	if(L->frontEnd == NULL){
    
           L->backEnd = temporary;
    
      	}else{ 
    
           L->frontEnd->previous = temporary;
	}
    
     	L->frontEnd = temporary;
    
     	++L->length;
   
}
   	// Insert new element into this List. If List is non-empty,
   	// insertion takes place after back element. 
   	void append(List L, int data) {

   		if (L == NULL){
    	
    		printf("List Error: calling append(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
   	
   		// new element
      	Node temporary = newNode(data, L->backEnd, NULL);
    
      	if(L->frontEnd== NULL){
    
          L->frontEnd = temporary;
    
      	}else{
    
          L->backEnd->next = temporary;
    	}
	
     	L->backEnd = temporary;
    
     	++L->length;
   }

   	// Insert new element before cursor.
   	// Pre: length() > 0, index() >= 0
   	void insertBefore(List L, int data) {

   		if (L == NULL){
    	
    		printf("List Error: calling insertBefore(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
	
		// Pre: length() > 0
      	if(L->length < 1){
         
         printf("List Error: insertBefore() called on an list that is empty\n");
      	 
      	 exit(1);

      	}

    	// Pre: index() >= 0  
      	if(L->index < 0){
        
         printf("List Error: insertBefore() called on an undefined index on the List\n");
      	
      	 exit(1);

      	}

      	// new element
      	Node temporary = newNode(data, L->cursorIndex->previous, L->cursorIndex);
      
		if(L->cursorIndex->previous != NULL){

         	  L->cursorIndex->previous->next = temporary;

      	}else{

        	 L->frontEnd = temporary;
        
	}

	L->cursorIndex->previous = temporary;

        ++L->length;
   }
   
   	// Inserts new element after cursor.
   	// Pre: length() > 0, index() >= 0
   	void insertAfter(List L, int data) {

   		if (L == NULL){
    	
    		printf("List Error: calling insertAfter(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
   
      	if(L->length < 1){
   
         printf("List Error: insertAfter() called on an List that is empty\n");
   
   		 exit(1);

   		}
      	
      	if(L->index < 0){
   
         printf("List Error: insertAfter() called on an undefined index of List\n");
   
   		 exit(1);

   		}
      	
      	Node temporary = newNode(data, L->cursorIndex, L->cursorIndex->next);
   
      	if(L->cursorIndex->next != NULL){
   
           L->cursorIndex->next->previous = temporary;
   
      	}else{ 
   
           L->backEnd = temporary;
   
	}

      	L->cursorIndex->next = temporary;
   
      	++L->length; 
   }
	
	// Deletes the front element in this List. 
    // Pre: length()>0
	void deleteFront(List L) {

		if (L == NULL){
    	
    		printf("List Error: calling deleteFront(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
	
		if(L->length < 1) {
	
			printf("List Error: deleteFront() called on an list that is empty\n");
		
			exit(1);
		}
	
		if(L->cursorIndex == L->frontEnd) {
	
			L->cursorIndex = NULL;
	
			L->index = -1;
	
		}
		Node temporary = L->frontEnd;
		
		L->frontEnd = L->frontEnd->next;
		
		L->frontEnd->previous = NULL;
		
		--L->length;
		
		freeNode(&temporary);
}

	// Deletes the back element in this List. 
	// Pre: length()>0
	void deleteBack(List L) {

		if (L == NULL){
    	
    		printf("List Error: calling deleteBack(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
	
		if(L->length < 1) {
	
			printf("List Error: deleteBack() called on an list that is empty\n");

			exit(1);
		}
	
		if(L->cursorIndex == L->backEnd) {
	
			L->cursorIndex = NULL;
	
			L->index = -1;
		}
		
		if(L->backEnd == L->frontEnd){
		   
		   L->frontEnd = NULL;
		}

		Node temporary = L->backEnd;

		if (L->backEnd->previous !=  NULL)
	
			L->backEnd = L->backEnd->previous;
		
		L->backEnd->next= NULL;
	
		--L->length;

		freeNode(&temporary);
	}


	// Deletes cursor element in this List. Cursor is undefined after this operation. 
	// Pre: length()>0, index()>=0
	void delete(List L) {

		if (L == NULL){
    	
    		printf("List Error: calling delete(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
	
		if(L->index < 0) {
	
			printf("List Error: delete() called on an undefined index on List\n");
	
			exit(1);

		}
		
		 if (L->length < 1) {
	
			printf("List Error: delete() called on an list that is empty\n");
		
			exit(1);
		}
	
		if(L->cursorIndex == L->backEnd) {
	
			deleteBack(L);
	
		} else if (L->cursorIndex == L->frontEnd) {
	
			deleteFront(L);
	
		} else {

			Node temporary = L->cursorIndex;
	
			L->cursorIndex->previous->next = L->cursorIndex->next;
	
			L->cursorIndex->next->previous = L->cursorIndex->previous;

			freeNode(&temporary);
	
			L->cursorIndex = NULL;
	
			L->index = -1;
	
			--L->length;
		}
	}

//---------------------------------------Other Methods-------------------------------------------------------
   
   	// printList()
   	// Prints List L to the file pointed by out as a space seperated string
   	void printList(FILE* out, List L) {
    
      	Node temporary = L->frontEnd;
	
	int i = 0;
	
	while(temporary != NULL){
	
		if(i != 0)
		  fprintf(out, " %d", temporary->data);

		else
		  
		  fprintf(out, "%d", temporary->data);
		
	  temporary = temporary->next;
		
	  ++i;
	}
}


	// Returns new List representing the same integer sequence as List
	List copyList(List L) {

		if (L == NULL){
    	
    		printf("List Error: calling copyList(List L) on NULL List reference\n");
    	
    		exit(1);
    	}
		
		List c = newList();
		
		Node now = L->frontEnd;
		
		while(now != NULL) {
		
			append(c, now->data);
		
			now = now->next;
		}
		
		return c;
	}


