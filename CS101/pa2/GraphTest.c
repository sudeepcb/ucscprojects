#include <stdio.h>
#include <stdlib.h>
#include "List.h"
#include "Graph.h"

int main(int argc, char* argv[]) {
	int n = 6;
	Graph A = newGraph(n); // graph of order 6
	Graph T=NULL, C=NULL;

	List B = newList();
	int i;
	for(i=1; i<=n; i++) append(B, i);

	// initialization print statements of new graph
	printf("Order: %d\n", getOrder(A)); // 6
	printf("Size: %d\n", getSize(A)); // 0
	printf("Parent of 1: %d\n", getParent(A, 1)); // NIL (0)
	printf("Discover time of 1: %d\n", getDiscover(A, 1)); // UNDEF (-1)
	printf("Finish time of 1: %d\n", getFinish(A, 1)); // UNDEF (-1)

	printf("----------------------------------------------------------\n");

	addArc(A,1,2);
	addArc(A,2,3);
	addArc(A,3,5);

	printGraph(stdout, A);
	// Expected:
	// 1: 2
	// 2: 3
	// 3: 5
	// 4: 
	// 5:
	// 6: 

	// Graph data after 3 addArc()
	printf("Size: %d\n", getSize(A)); // 3
	printf("Parent of 1: %d\n", getParent(A, 1)); // NIL (0) still because DFS has not been called
	printf("Discover time of 1: %d\n", getDiscover(A, 1)); // UNDEF (-1) still because DFS has not been called
	printf("Finish time of 1: %d\n", getFinish(A, 1)); // UNDEF (-1) still because DFS has not been called

	printf("----------------------------------------------------------\n");

	addArc(A,1,2); // shouldn't add the values again so size stays the same
	printf("Size: %d\n", getSize(A)); // still 3
	addArc(A,5,4);
	printf("Size: %d\n", getSize(A)); // now 4
	printGraph(stdout, A);

	printf("----------------------------------------------------------\n");

	DFS(A,B); // run DFS with initial list B
	fprintf(stdout, "x:  d  f  p\n");
	for(i=1; i<=n; i++){
		fprintf(stdout, "%d: %2d %2d %2d\n", i, getDiscover(A, i), getFinish(A, i), getParent(A, i));
	}
	// EXPECTED: 6 1 2 3 5 4
	printList(stdout, B);
	fprintf(stdout, "\n");

	printf("----------------------------------------------------------\n");

	T = transpose(A);
	C = copyGraph(A);
	fprintf(stdout, "\n");
	// EXPECTED SAME GRAPH AS A
	printGraph(stdout, C);
	fprintf(stdout, "\n");
	// EXPECTED TRANSPOSE OF A (value becomes list num, list num becomes value)
	printGraph(stdout, T);
	fprintf(stdout, "\n");

	printf("----------------------------------------------------------\n");

	DFS(T, B);
	fprintf(stdout, "\n");
	fprintf(stdout, "x:  d  f  p\n");
	for(i=1; i<=n; i++){
		fprintf(stdout, "%d: %2d %2d %2d\n", i, getDiscover(T, i), getFinish(T, i), getParent(T, i));
	}
	fprintf(stdout, "\n");
	printList(stdout, B);
	fprintf(stdout, "\n");
	// RESULTING GRAPH SHOWS STRONGLY CONNECTED COMPONENTS
	// in this case each vertex is it's own strongly connected component since all parents = 0

	freeList(&B);
	freeGraph(&A);
	freeGraph(&T);
	freeGraph(&C);
}