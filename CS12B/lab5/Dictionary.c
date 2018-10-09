//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12M
// sucbaniy
// 5-14-2016
// The programs implements a Dictionary ADT based on the linked list data strucure// Dictionary.c
//-------------------------------------------------------------------------------

#include<stdio.h>

#include<stdlib.h>

#include<string.h>

#include<assert.h>

#include"Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj

typedef struct NodeObj{

   char* value;

   char* key;

   struct NodeObj* next;

} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {

   Node N = malloc(sizeof(NodeObj));

   assert(N!=NULL);

   N->value = v;

   N->key = k;

   N->next = NULL;

   return(N);

}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){

   if( pN!=NULL && *pN!=NULL ){

      free(*pN);

      *pN = NULL;

   }
}

// DictionaryObj
typedef struct DictionaryObj{

   Node first;

   int numOfItems;

} DictionaryObj;

//helper fuctions for delete()
void deleteEverything(Node N){

  if (N!=NULL){

   deleteEverything(N->next);

    freeNode(&N);
  }
}

//findKey()
//this returns reference to the NodeObject with its argument key or else return null if no NodeObject exits
Node findKey(Node A, char* targetKey){
 
 while(A != NULL){
 
    if(strcmp(A->key, targetKey)==0){
 
      break;
    }
 
    A = A->next;

    }

    return A;
}

//helper functions for printDictionary()
void stringout(FILE* out, Node A){

  if(A!=NULL){

    stringout(out, A->next);

    fprintf(out, "%s %s\n", A->key, A->value);
  }
}


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){

   Dictionary D = malloc(sizeof(DictionaryObj));

   assert(D!=NULL);

   D->first = NULL;

   D->numOfItems = 0;

   return D;
}

// Dictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){

   if( pD!=NULL && *pD!=NULL ){

      if( !isEmpty(*pD) ) makeEmpty(*pD);

      free(*pD);

      *pD = NULL;

   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){

   if( D==NULL ){

      fprintf(stderr, 

              "Dictionary Error: calling isEmpty() on NULL reference\n");

      exit(EXIT_FAILURE);

   }

   return(D->numOfItems==0);

}

//size()
// returns the number of (key, value) pairs in D
// pre none
int size(Dictionary D){

  return D->numOfItems;
}

// lookup()
// returns the value v such that (k,v) is in D, or returns NULL if no such value v exists
// pre: none
char* lookup(Dictionary D, char* k){

  Node N = findKey(D->first, k);

  if(N==NULL){

    return NULL;

  }else{
   
    return N->value;
  }
}

// insert()
// inserts new (key, value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
  
  Node N;  
  
  if(findKey(D->first, k)!=NULL){
  
    fprintf(stderr, 
      "Dictionary Error: calling insert() when key exists\n");
  
    exit(EXIT_FAILURE);
  
  }
  
  N = newNode(k, v);
  
  N->next = D->first;
  
  D->first = N;
  
  N = NULL;
  
  D->numOfItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){

  Node N;

  if(findKey(D->first, k)==NULL){

    fprintf(stderr, 
      "Dictionary Error: calling delete() when nothing exits\n");

    exit(EXIT_FAILURE);

  }

  if(findKey(D->first, k)==D->first){

    N = D->first;

    D->first = D->first->next;

    N->next = NULL;

  }else{

    N = findKey(D->first, k);

    Node before = D->first;

    Node temporary = D->first->next;

    while(temporary !=N){

      temporary = temporary->next;

      before = before->next;
    }
    
    before->next = N->next;
    
    N->next = NULL;
  }
  
  D->numOfItems--;
  
  freeNode(&N);

}

// makeEmpty()
// resets D to the empty state
// pre: !isEmpty(D)
void makeEmpty(Dictionary D){
  
   if( D==NULL ){
  
      fprintf(stderr, 
  
              "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
  
      exit(EXIT_FAILURE);
  
   }
  
   if( D->numOfItems==0 ){
  
      fprintf(stderr, 
              "Dictionary Error: calling makeEmpty() on empty Dictionary\n");
  
      exit(EXIT_FAILURE);
   }
  
   deleteEverything(D->first);
  
   D->first = NULL;
  
   D->numOfItems = 0;
}

//printDictionary()
//pre: none
//prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
  
   if( D==NULL ){
  
      fprintf(stderr, 
              "Dictionary Error: calling printDictionary() on where the reference is NULL\n");
  
      exit(EXIT_FAILURE);
  
   }
  
   stringout(out, D->first); 
}
