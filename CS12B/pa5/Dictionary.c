//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 5-29-2016
// The programs implements a Dictionary ADT based on the hash table.
// Dictionary.c
//-------------------------------------------------------------------------------

#include<stdio.h>

#include<stdlib.h>

#include<string.h>

#include<assert.h>

#include"Dictionary.h"

#define MAX_LEN 180

const int tableSize = 101;

// hash functions ------------------------------------------------------------

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
  
  int sizeInBits = 8*sizeof(unsigned int);

  shift = shift & (sizeInBits - 1);
  
  if ( shift == 0 )
  
    return value;
  
  return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
  
  unsigned int result = 0xBAE86554;
  
  while (*input) { 
  
    result ^= *input++;
  
    result = rotate_left(result, 5);
  }
  
  return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
  
  return pre_hash(key)%tableSize;
}

// showBits()
// print out the bits in an unsigned int
void showBits(unsigned int x) {
  
  int i;
  
  int sizeInBits = 8*sizeof(unsigned int);
  
  char symbol[2] = {'0','1'};
  
  char* binary = malloc(sizeInBits + 1);

  memset(binary, 0, sizeInBits + 1);

  for (i=0; i<sizeInBits; i++) {
  
    binary[sizeInBits-i-1] = symbol[(x>>i) & 0x01];
  }
   
  printf("%s\n", binary);
  
  free(binary);
}

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
  
   char* key;
  
   char* value;
  
   struct NodeObj* next;

} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {

   Node N = malloc(sizeof(NodeObj));

   assert(N!=NULL);

   N->key = k;

   N->value = v;

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

// ListObj
typedef struct ListObj{

   Node first;

 } ListObj;

// List
typedef ListObj* List;

// newList()
// constructor of the Node type
List newList(void) {
 
   List L = malloc(sizeof(ListObj));
 
   assert(L!=NULL);
 
   L->first = NULL;
 
   return L;
}


// DictionaryObj
typedef struct DictionaryObj{

  List table;

  int numOfItems;

} DictionaryObj;


// private helper functions --------------------------------------------------

//deleteEverything
void deleteEverything(Node N){
  
  if (N!=NULL){
  
  deleteEverything(N->next);
  
    freeNode(&N);
  }
}

//findKey()
//returns a reference to the NodeObj containing its argument key or return null if no such NodeObj exists
Node findKey(Node A, char* targetKey){ 

while(A != NULL){

    if(strcmp(A->key, targetKey)==0){

      break;
    }

    A = A->next;
    }

    return A;
}


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){

   Dictionary D = malloc(sizeof(DictionaryObj));
  
   assert(D!=NULL);
  
   D->table = calloc(tableSize, sizeof(ListObj));
  
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
 
      fprintf(stderr, "DICTIONARY Error: calling isEmpty() on NULL Dictionary reference\n");
 
      exit(EXIT_FAILURE);
   }
 
   return(D->numOfItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre none
int size(Dictionary D){
 
  return D->numOfItems;
}

// lookup()
// returns the value v such that (k,v) is in D, or returns NULL if no 
// such value v exists
// pre: none
char* lookup(Dictionary D, char* k){
  
  int indexOftable;
  
  List L;
  
  Node N;
  
  indexOftable = hash(k);
  
  L = &D->table[indexOftable];
  
  N = findKey(L->first, k);
  
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
  
  List L;
  
  Node N;
  
  int indexOfTable;  
  
  indexOfTable = hash(k);
  
  L = &D->table[indexOfTable];
  
  if(findKey(L-> first, k)!=NULL){
  
    fprintf(stderr, "DICTIONARY Error: calling insert() when exists\n");
    
    exit(EXIT_FAILURE);
  }
  
  N = newNode(k, v);
  
  N->next = L->first;
  
  L->first = N;
  
  N = NULL;
  
  D->numOfItems++;
 
}


// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
  
  if( D==NULL ){
  
    fprintf(stderr, "DICTIONARY Error: calling delete() when nothing exists\n");
  
    exit(EXIT_FAILURE);
  }  
  
  List L;
  
  Node N;
  
  int indexOfTable;
  
  indexOfTable = hash(k);
  
  L = &D->table[indexOfTable];
  
  if(findKey(L->first, k)==L->first){
  
    N = L->first;
  
    L->first = L->first->next;
  
    N->next = NULL;
  
  }else{
  
    N = findKey(L->first, k);
  
    Node before = L->first;
  
    Node temporary = L->first->next;
    
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
// re-sets D to the empty state
// pre: !isEmpty(D)
void makeEmpty(Dictionary D){
 
  List L;
 
  if( D==NULL ){
 
      fprintf(stderr, "DICTIONARY Error: calling makeEmpty() on NULL Dictionary reference\n");
 
      exit(EXIT_FAILURE);
   }
 
   if( D->numOfItems==0 ){
 
      fprintf(stderr, "DICTIONARY Error: calling makeEmpty() on empty Dictionary\n");
 
      exit(EXIT_FAILURE);
   }
   
   int i;
   
   for(i = 0; i<tableSize; i++){
 
       L = &D->table[i];
 
       deleteEverything(L->first);
   }
 
   D->numOfItems = 0; 

   D-> table = NULL;
   }

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   
   if( D==NULL ){
   
      fprintf(stderr, "DICTIONARY Error: calling printDictionary() on where the reference is NULL\n");
   
      exit(EXIT_FAILURE);
      }
  
  Node N;
  
  List L;
  
  int i;

  for(i = 0; i<tableSize; i++){
  
    L = &D->table[i];
  
    N = L->first;
  
    while(N!=NULL){
  
      fprintf(out, "%s %s\n", N->key, N->value);
  
      N = N->next;
    }
  }
}
