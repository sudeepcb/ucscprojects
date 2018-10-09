//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 5-29-2016
// This program test the Dictionary.c program
// DictionaryTest.c
//-------------------------------------------------------------------------------

#include<stdio.h>

#include<stdlib.h>

#include<string.h>

#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){

   Dictionary A = newDictionary();

   Dictionary B = newDictionary();

   char* word1[] = {"one","two","three","four","five","six","seven"};

   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};

   int i;

   char* word3[] = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen"};

   char* word4[] = {"foo","bar","blah","galumph","happy","sad","blue"};

   int j;

   for(i=0; i<7; i++){

      insert(A, word1[i], word2[i]);
   }

   printDictionary(stdout, A);

   // insert(A, "five", "glow"); // error: duplicate keys

   delete(A, "five");

   delete(A, "seven");

   delete(A, "six");

   printDictionary(stdout, A);

   // delete(A, "five");  // error: key not found

   printf("%s\n", (isEmpty(A)?"true":"false"));

   printf("%d\n", size(A));

   makeEmpty(A);

   printf("%s\n", (isEmpty(A)?"true":"false"));

   // new dictionary

   for(j=0; j<7; j++){

      insert(B, word3[j], word4[j]);
   }

   printDictionary(stdout, B);

   // insert(B, "eleven", "glow"); // error: duplicate keys

   delete(B, "ten");

   delete(B, "eleven");

   delete(B, "twelve");

   printDictionary(stdout, B);

   // delete(B, "ten");  // error: key not found

   printf("%s\n", (isEmpty(B)?"true":"false"));

   printf("%d\n", size(B));

   makeEmpty(B);

   printf("%s\n", (isEmpty(B)?"true":"false"));

   freeDictionary(&A);
   
   freeDictionary(&B);

   return(EXIT_SUCCESS);
}
