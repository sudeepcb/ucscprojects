//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12M
// sucbaniy
// 5-3-2016
// The program reads a file containing words and sorts the character in appropriate order (line(count lines in the in file), letter(abc...etc) , num(1...etc), punctuation(&...etc), space( ))
// charType.c
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

#include <stdio.h>

#include <stdlib.h>

#include <string.h>

#include <ctype.h>

#include <assert.h>

#define MAXLENGTH 100 

void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
   
   FILE* in;
   
   FILE* out;
   
   char* line; //stores the character read in from a file 
   
   char* num;  // stores the numbers
   
   char* punctuation; // stores the punctuation
   
   char* letter; // stores alpha characters
   
   char* space; // stores the white spaces
   
   // number of lines
   int numOflines = 1;

   // check command line for correct number of arguments
   if( argc != 3 ){
     printf("Usage: %s <input file> <output file>\n", argv[0]);
     exit(EXIT_FAILURE);
   }

   // open input file for readingthrows error statement if null
   in = fopen(argv[1], "r");
   if ( in==NULL ){
     printf("Unable to read from file %s\n", argv[1]);
     exit(EXIT_FAILURE);
   }

   // open output file for reading or throws error statement if null
   out = fopen(argv[2], "w");
   if( out==NULL ){
     printf("Unable to write to file %s\n", argv[2]);
     exit(EXIT_FAILURE);
   }

   // allocates memory for line, num, puncutation, letter, space
   
   line = calloc(MAXLENGTH + 1, sizeof(char));
   
   num = calloc(MAXLENGTH + 1, sizeof(char));
   
   punctuation = calloc(MAXLENGTH + 1, sizeof(char));
   
   letter = calloc(MAXLENGTH + 1, sizeof(char));
   
   space = calloc(MAXLENGTH + 1, sizeof(char));

   assert( line != NULL && num != NULL && punctuation != NULL && letter != NULL && space != NULL);

   // reads each line in  input file and print thems out in appropriate order
   while ( fgets(line, MAXLENGTH, in) != NULL){
    
     // places each character in the right listing
     extract_chars(line, letter, num, punctuation, space);
    
     fprintf(out, "line %d contains: \n", numOflines);
    
     // the number of the in file
     if(strlen(letter)>1){
       fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(letter), letter);
     } else {
       fprintf(out, "%d alphabetic character: %s\n", (int)strlen(letter), letter);
     }
    
     // the punctutation of the in file
     if(strlen(num)>1){
       fprintf(out, "%d numeric characters: %s\n", (int)strlen(num), num);
     }else {
       fprintf(out, "%d numeric character: %s\n", (int)strlen(num), num);
     }
    
     // the letter of the in file
     if(strlen(punctuation)>1){
       fprintf(out, "%d numeric characters: %s\n", (int)strlen(punctuation), punctuation);
     }else {
       fprintf(out, "%d numeric character: %s\n", (int)strlen(punctuation), punctuation);
     }
    
     // the whitespace of the in file
     if(strlen(space)>1){
       fprintf(out, "%d whitespace characters: %s\n", (int)strlen(space), space);
     }else {
       fprintf(out, "%d whitespace character: %s\n", (int)strlen(space), space);
     }
    
     // increaces the numbers of lines everytime
     numOflines++;
   }

   // frees the allocated heap memory 
   free(line);
   
   free(num);
   
   free(punctuation);
   
   free(letter);
   
   free(space);
  // closes the in and out files 
   
   fclose(in);
   
   fclose(out);

   return EXIT_SUCCESS;

 }

// function definition
// places line, num, letter, punctutaion, and space in the array they belong
 void extract_chars(char* s, char* a, char* d, char* p, char* w){
   
   int i=0, j=0, k=0, l=0, m=0;
   
   while (s[i] != '\0' && i < MAXLENGTH ){
     
     if( isupper((int)s[i])){
			a[j]=s[i];
			j++;

		} else if( isalpha((int)s[i])){
      a[j] =s[i];
      j++;

    } else if( isdigit((int)s[i])){
      d[k] = s[i];
      k++;

    } else if(ispunct((int)s[i])){
			p[l]=s[i];
			l++;

		} else{
			w[m]=s[i];
			m++;
		}
		i++;
   }

   // sets the chracter to null at the end of the arrays
   a[j] = '\0';
   
   d[k] = '\0';
	 
   p[l] = '\0';
	 
   w[m] = '\0';
 }
