//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA2 
// Lex.c
// Sorts inputfile using List.java and writes in outputfile
//-------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include "List.h"
#include<string.h>

#define MAX_LEN 254



    int main (int argc, char * argv[]){

      // Pre: Check if two arguments has passed
      if(argc == 0 || argc == 1 || argc == 2) {

         printf(" Usage: %s <inputfile>  <outputfile>\n", argv[0]);
        
         exit(1);
      }


      // variable declarations

      FILE *input, *output;

      int lineNumber = 0;

      char line[MAX_LEN];

      
      //  reading and writing files
      input = fopen(argv[1],"r"); //reading

      output = fopen(argv[2], "w"); //writing

      //count the numbers of line from the inputfile     
      while( fgets(line, 254, input) != NULL) {

         ++lineNumber;

      }
      

      rewind(input);

      
      char n[lineNumber -1][254];

      int n_lines = -1;
      
      // copying files into lines
      while(fgets(line, 254, input) != NULL){

         strcpy(n[++n_lines], line);

      }
      
      // create List
     List l = newList();	     
      
     append(l, 0);

      // Insertion Sort

      for(int j = 1; j < lineNumber; ++j) {
         
         char *type =  n[j];

         int i = j - 1;
         
         // move Index to the back of list
         moveBack(l);

         // comparing string from the current line and each line of List
         while(i > -1 && strcmp(type, n[get(l)]) <= 0) {
      
            --i;
      
            movePrev(l);
         }
         
         if(index(l) > -1){
         
            insertAfter(l, j);
         
         }else{
      
            prepend(l, j);
         }   
      }

      // move Index to front of the list
      moveFront(l);

      // Loop control for List for the correct order of output
      while(index(l) > -1) {
      
         fprintf(output,"%s", n[get(l)]);
      
         moveNext(l);
      }
     
      // Close input and output
      fclose(input);
      
      fclose(output);

      // Free created ADT
      freeList(&l);

      return(0);

   }
