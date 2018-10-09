//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA4 
// GraphTest.c
// Testing for Graph ADT
//------------------------------------------------------------------------


#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"
#include "List.h"

int main(int argc, char* argv[]) {
	
	int n = 6;
	
	Graph A = newGraph(n); 
	
	Graph B = newGraph(n);

	printf("Order A: %d\n", getOrder(A)); 
	
	printf("Size A: %d\n", getSize(A)); 
	
	printf("Source A: %d\n", getSource(A)); 
	
	printf("Parent of 1 A: %d\n", getParent(A, 1)); 
	
	printf("Distance to source A: %d\n", getDist(A, 1)); 

	printf("------------------------------------------------\n");

	printf("Order B: %d\n", getOrder(B)); 
	
	printf("Size B: %d\n", getSize(B)); 
	
	printf("Source B: %d\n", getSource(B)); 
	
	printf("Parent of 1 B: %d\n", getParent(B, 1)); 
	
	printf("Distance to source B: %d\n", getDist(B, 1)); 


	printf("------------------------------------------------\n");

	addEdge(A,1,2);
	
	addEdge(A,2,3);
	
	addEdge(A,3,5);
	
	addEdge(A,4,5);
	
	addEdge(A,5,4);
	
	addEdge(B,1,2);
	
	addEdge(B,2,3);
	
	addEdge(B,3,5);
	
	addEdge(B,4,5);
	
	addEdge(B,5,4);

	printGraph(stdout, A);
	

	
	printf("Size A: %d\n", getSize(A)); 
	
	printf("Source A: %d\n", getSource(A)); 
	
	printf("Parent of 1 A: %d\n", getParent(A, 1)); 
	
	printf("Distance to source A: %d\n", getDist(A, 1)); 

	printf("------------------------------------------------\n");

	printGraph(stdout,B);
	
	printf("Size B: %d\n", getSize(B)); 
	
	printf("Source B: %d\n", getSource(B)); 

	printf("Parent of 1 B: %d\n", getParent(B, 1)); 
	
	printf("Distance to source B: %d\n", getDist(B, 1)); 

	printf("------------------------------------------------\n");


	printGraph(stdout, A);
	
	printf("Size A: %d\n", getSize(A));


	printf("------------------------------------------------\n");
	
	printGraph(stdout, B);
	
	printf("Size B: %d\n", getSize(B));

	printf("------------------------------------------------\n");
	
	addArc(A,5,4);

	printGraph(stdout, A);

	printf("Size A: %d\n", getSize(A)); 

	addArc(A,4,5);

	printGraph(stdout, A);

	printf("Size A: %d\n", getSize(A));

	addArc(A,5,4);

	printf("------------------------------------------------\n");

	printGraph(stdout, B);

	printf("Size B: %d\n", getSize(B)); 

	addArc(B,4,5);

	printGraph(stdout, B);

	printf("Size B: %d\n", getSize(B));  
	
	printf("------------------------------------------------\n");

	BFS(A,1); 

	printf("Source A: %d\n", getSource(A)); 

	printf("Parent of 1 A: %d\n", getParent(A, 1)); 

	printf("Parent of 2 A: %d\n", getParent(A, 2)); 

	printf("Parent of 3 A: %d\n", getParent(A, 3)); 

	printf("Parent of 4 A: %d\n", getParent(A, 4)); 

	printf("Parent of 5 A: %d\n", getParent(A, 5)); 

	printf("Distance from (1,4) A: %d\n", getDist(A, 4)); 

	printf("Distance from (1,5) A: %d\n", getDist(A, 5)); 

	printf("Distance from (1,3) A: %d\n", getDist(A, 3)); 

	printf("Distance from (1,2) A: %d\n", getDist(A, 2)); 

	printf("Distance from (1,1) A: %d\n", getDist(A, 1)); 

	printf("------------------------------------------------\n");
	
	BFS(B,1); 

	printf("Source B: %d\n", getSource(B)); 

	printf("Parent of 1 B: %d\n", getParent(B, 1)); 

	printf("Parent of 2 B: %d\n", getParent(B, 2)); 

	printf("Parent of 3 B: %d\n", getParent(B, 3)); 

	printf("Parent of 4 B: %d\n", getParent(B, 4)); 

	printf("Parent of 5 B: %d\n", getParent(B, 5)); 

	printf("Distance from (1,4) B: %d\n", getDist(B, 4)); 

	printf("Distance from (1,5) B: %d\n", getDist(B, 5)); 

	printf("Distance from (1,3) B: %d\n", getDist(B, 3)); 

	printf("Distance from (1,2) B: %d\n", getDist(B, 2)); 

	printf("Distance from (1,1) B: %d\n", getDist(B, 1)); 

	printf("------------------------------------------------\n");

	makeNull(B);

	printGraph(stdout, B); 

	printf("Order B: %d\n", getOrder(B)); 

	printf("Size B: %d\n", getSize(B)); 

	printf("Source B: %d\n", getSource(B)); 

	freeGraph(&B);
	
	printf("------------------------------------------------\n");
	
	printf("Parent of 6 A: %d\n", getParent(A, 6)); 

	printf("Distance from (1,6) A: %d\n", getDist(A, 6)); 

	printf("------------------------------------------------\n");

	BFS(A,3); 

	printf("Source A: %d\n", getSource(A)); 

	printf("Parent of 1 A: %d\n", getParent(A, 1)); 

	printf("Parent of 2 A: %d\n", getParent(A, 2)); 

	printf("Parent of 3 A: %d\n", getParent(A, 3)); 

	printf("Parent of 4 A: %d\n", getParent(A, 4)); 

	printf("Parent of 5 A: %d\n", getParent(A, 5)); 

	printf("Distance from (3,1) A: %d\n", getDist(A, 4)); 

	printf("Distance from (3,2) A: %d\n", getDist(A, 1)); 

	printf("Distance from (3,3) A: %d\n", getDist(A, 5)); 

	printf("Distance from (3,4) A: %d\n", getDist(A, 2)); 

	printf("Distance from (3,5) A: %d\n", getDist(A, 3)); 


	printf("------------------------------------------------\n");

	makeNull(A);

	printGraph(stdout, A); 

	printf("Order A: %d\n", getOrder(A)); 

	printf("Size A: %d\n", getSize(A)); 

	printf("Source A: %d\n", getSource(A)); 

	freeGraph(&A);
}
