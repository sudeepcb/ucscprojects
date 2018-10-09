//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA4 
// FindPath.c
// FindPath finds the shortest path between two vertices by BFS()
//------------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"
#include "List.h"

//Enum for BFS algorithm
#define WHITE 0
#define GRAY 1
#define BLACK 2
#define NILL -2
#define INF  -1


int main(int argc, char* argv[]){
   
   // check for correct number of argument
   if( argc != 3 ){

      printf("Usage: %s <input file> <output file>\n", argv[0]);

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

   int totalCount = 0;

   fgets(line, 128, input);

   sscanf(line, "%d", &lineNum);
   
   Graph G = newGraph(lineNum);

   // populate graph
   while( fgets(line, 128, input) != NULL)  {

      int source = 0;

      int vertex = 0;

      sscanf(line, "%d %d", &source, &vertex);

      if(source == 0 && vertex == 0) break;

      addEdge(G, source, vertex);   
   }

   // print resulting graph
   printGraph(output, G);


   // print paths
   while( fgets(line, 128, input) != NULL)  {

      int source = 0;

      int vertex = 0;

      sscanf(line, "%d %d", &source, &vertex);

      if(source == 0 && vertex == 0) break;

      if(totalCount++ != 0){

         fprintf(output, "\n");
      }
      fprintf(output, "\n");

      BFS(G, source);
      
      fprintf(output, "The distance from %d to %d is ", source, vertex);
      
      if(getDist(G, vertex) == INF){
      
         fprintf(output, "infinity\n");
      
      }else{
      
         fprintf(output, "%d\n", getDist(G, vertex));
      }
      
      List L = newList();
      
      getPath(L, G, vertex);
      
      if(front(L) == NILL){
      
         fprintf(output, "No %d-%d path exists", source, vertex);
      
      } else {
      
         fprintf(output, "A shortest %d-%d path is: ", source, vertex);
      
         printList(output, L);
      }
      
      freeList(&L);
   }

   fprintf(output, "\n");

   // free
   freeGraph(&G);
   
   // close files
   fclose(input);
   
   fclose(output);
   
   return(0);
}
