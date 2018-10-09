//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA5
// GraphTest.c
// Testing for Graph ADT
//------------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"
#include "List.h"

int main(int argc, char* argv[]) {
	
	int n = 6;

	List B = newList();
	
	Graph G = newGraph(n);
	
	Graph F = newGraph(n);
	
	Graph T=NULL, C=NULL;

	int i;
	for(i=1; i<=n; i++) append(B, i);

	
	printf("Order of G: %d\n", getOrder(G)); 
	
	printf("Parent of 1 of G: %d\n", getParent(G, 1)); 
	
	printf("Discover time of 1 of G: %d\n", getDiscover(G, 1));
	
	printf("Finish time of 1 of G: %d\n", getFinish(G, 1));

	

	printf("------------------------------------------------------------\n");

	printf("Order of F: %d\n", getOrder(F)); 
	
	printf("Parent of 1 of F: %d\n", getParent(F, 1)); 
	
	printf("Discover time of 1 of F: %d\n", getDiscover(F, 1));
	
	printf("Finish time of 1 of F: %d\n", getFinish(F, 1));

	

	printf("------------------------------------------------------------\n");

   addArc(G, 1,2);
   
   addArc(G, 1,3);
   
   addArc(G, 2,3);
   
   addArc(G, 2,5);
   
   addArc(G, 3,2);
   
   addArc(G, 3,5);
   

   printGraph(stdout, G);

   printf("Order of G: %d\n", getOrder(G)); 
   
   printf("Parent of 1 of G: %d\n", getParent(G, 1)); 
	
   printf("Discover time of 1 of G: %d\n", getDiscover(G, 1));
 
   printf("Finish time of 1 of G: %d\n", getFinish(G, 1));

	

   printf("------------------------------------------------------------\n");

   addArc(F, 1,2);
   
   addArc(F, 1,3);
   
   addArc(F, 2,3);
   
   addArc(F, 2,5);
   
   addArc(F, 3,2);
   
   addArc(F, 3,5);


   printGraph(stdout, F);

   printf("Order of F: %d\n", getOrder(F)); 
	
   printf("Parent of 1 of F: %d\n", getParent(F, 1)); 
	
   printf("Discover time of 1 of F: %d\n", getDiscover(F, 1));
	
   printf("Finish time of 1 of F: %d\n", getFinish(F, 1));

	

   printf("------------------------------------------------------------\n");

	addArc(G,5,4);
	
	printGraph(stdout, G);

   printf("------------------------------------------------------------\n");

	addArc(F,5,4);
	
	printGraph(stdout, F);

	
  printf("------------------------------------------------------------\n");

	DFS(G,B);

	fprintf(stdout, "x:  d  f  p\n");
	
	for(i=1; i<=n; i++){

		fprintf(stdout, "%d: %2d %2d %2d\n", i, getDiscover(G, i), getFinish(G, i), getParent(G, i));
	}

	printList(stdout, B);

	fprintf(stdout, "\n");


	printf("------------------------------------------------------------\n");

	T = transpose(G);

	C = copyGraph(G);

	fprintf(stdout, "\n");

	printGraph(stdout, C);
	
	fprintf(stdout, "\n");
	
	printGraph(stdout, T);

	fprintf(stdout, "\n");


	DFS(T, B);

	fprintf(stdout, "\n");

	fprintf(stdout, "x:  d  f  p\n");

	for(i=1; i<=n; i++){

		fprintf(stdout, "%d: %2d %2d %2d\n", i, getDiscover(T, i), getFinish(T, i), getParent(T, i));
	}

	fprintf(stdout, "\n");

	printList(stdout, B);

	fprintf(stdout, "\n");


	printf("----------------------------------------------------------\n");
           
	

	freeList(&B);

	freeGraph(&G);

	freeGraph(&F);

	freeGraph(&T);

	freeGraph(&C);
    
        return(0);
}
