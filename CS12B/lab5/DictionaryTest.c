//------------------------------------------------------------------------------
// Sudeep Baniya
// CMPS 12B
// sucbaniy
// 5-14-2016
// The programs test the Dictionary ADT based on the linked list data strucure.
// DictionaryTest.c
//------------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
	Dictionary G = newDictionary();
	int b = size(G);
	int e = isEmpty(G);
	printf("%d,%d\n",b,e);
	char* k;
	char* v;
	char* word1[] = {"zero","one","two","three","four"};
	char* word2[] = {"foo", "bar", "blah", "galump"};
	int i;

	for (i=0; i<5; i++){
	insert(G, word1[i],word2[i]);
	}

	printDictionary(stdout, G);

	printf("%d,%d\n",b,e);

// insert (G, "zero", "lol"); //error: same key entered

	delete(G, "zero");
	delete(G, "one");
	delete(G, "two");
	insert(G, "five", "cmps");
	insert(G, "six", "data");

	printDictionary(stdout,G);
// delete(G, "zero"); // key not found error

	for ( i = 0; i<5; i++){
	k = word1[i];
	v = lookup(G,k);
	printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
	}

	printf("%s\n", (isEmpty(G)?"true":"false"));
	printf("%d\n", size(G));
	makeEmpty(G);
	printf("%s\n", (isEmpty(G)?"true":"false"));

	insert(G, "five", "cmps");
	insert(G, "six", "data");

	printDictionary(stdout,G);

	freeDictionary(&G);

	return(EXIT_SUCCESS);
	}
