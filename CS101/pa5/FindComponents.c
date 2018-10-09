//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA5 
// FindComponents.c
// FindComponents finds the Strongly Connected Components on a diGraph by DFS()
//------------------------------------------------------------------------

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "Graph.h"
#include "List.h"

//Enum for BFS algorithm
#define WHITE 0
#define GRAY 1
#define BLACK 2
#define NILL 0
#define INF  -1


int main(int argc, char* argv[]){
   
   // check for correct number of argument
   if( argc != 3 ){

      printf("Usage Error: %s [input file] [output file]\n", argv[0]);

      exit(1);
   }

   // open and check files
   FILE *input, *output;

   input = fopen(argv[1], "r");

   output = fopen(argv[2], "w");
   
   if( input==NULL ){

      printf("Unable to open file %s for reading\n", argv[1]);

      exit(1);
   }

   if( output==NULL ){

      printf("Unable to open file %s for writing\n", argv[2]);

      exit(1);
   }

   char line[128];

   int lineNum = 0;

   fgets(line, 128, input);

   sscanf(line, "%d", &lineNum);
   
   Graph G = newGraph(lineNum);

   // populate graph by reading directed edges from input file to assign to source or vertex
   while( fgets(line, 128, input) != NULL)  {

      int source = 0;

      int vertex = 0;

      sscanf(line, "%d %d", &source, &vertex);

      if(source == 0 && vertex == 0) break;

      addArc(G, source, vertex);   
   }

   // creation of list L for DFS
   List L = newList();
   
   int x;
   
   for(x = 1; x <= lineNum; ++x){ 
   
      append(L, x);
   }

   // Run DFS on Graph G to get List L (stack) of vertices in decreasing finish time
   DFS(G, L);

   Graph T = transpose(G);

   // Run DFS again with the resulting stack to find strong connect components from the Graph G parent array
   DFS(T, L);
   
   int strCon = 0;

   // count number of strong connect components
   if(L!=NULL && length(L)!=0){
   
      moveFront(L);
   
   while(index(L) >= 0) {
   
      if(getParent(T, get(L)) == NILL){
   
         strCon++;
      }
   
      moveNext(L);
   
   }
}

// Reference to store strong connect components
   List StrConCom[strCon];
   
   int y;
   
   for(y = 0; y<strCon; ++y){

      StrConCom[y] = newList();

   }

   if(L!=NULL && length(L)!=0){
   
      moveFront(L);

      int scc = strCon;
   
   while(index(L) >= 0) {
   
      if(getParent(T, get(L)) == NILL){

         --scc;
   
      }
	
      if(scc == strCon){

         break;
      }

      int a;

      a = get(L);

      append(StrConCom[scc], a);

      moveNext(L);
   
      }
   
   }

   // printing of adj

   fprintf(output, "Adjacency list representation of G:\n");
   
   printGraph(output, G);
   
   fprintf(output, "\nG contains %d strongly connected components:", strCon);
   
   // printing of strong connect component and cleaning up
   int b;
   
   for(b = 0; b < strCon; ++b) {
   
      fprintf(output, "\nComponent %d: ", (b + 1));
   
      printList(output, StrConCom[b]);
   
      freeList(&(StrConCom[b]));
   }
   
   fprintf(output, "\n");
   
   // cleaning up
   freeGraph(&G);   
   
   freeGraph(&T);
   
   freeList(&L);

   
   // close files
   fclose(input);
   
   fclose(output);
   
   return(0);
}
