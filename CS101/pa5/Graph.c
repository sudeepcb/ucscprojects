//-------------------------------------------------------------------------------- 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA5 
// Graph.c
// Graph ADT implemention used to find Strongly Connected Components of a diGraph
//--------------------------------------------------------------------------------


#include <stdlib.h>
#include <stdio.h>
#include "List.h"
#include "Graph.h"

// Enum for BFS algorithm
#define WHITE 0
#define GRAY 1
#define BLACK 2
#define NILL 0
#define UNDEF  -3

// private GraphObj
typedef struct GraphObj{
   int order;

   int size;

   int *finish;

   int *discover;

   int *color;

   int *parent;

   List *adj; 

} GraphObj;


// Private Help Class
void Visit(Graph G, List S, int u, int *time);

// Returns a pointer to a new GraphObj
Graph newGraph(int n) {
   Graph G = malloc(sizeof( GraphObj));
   
   G->order = n;

   G->size = 0;
   
   G->adj = malloc((n+1) * sizeof(List));
   
   G->color = malloc((n+1) * sizeof(int));
   
   G->parent = malloc((n+1) * sizeof(int));
   
   G->discover = malloc((n+1) * sizeof(int));
   
   G->finish = malloc((n+1) * sizeof(int));
   
   int i;
   
   for(i = 0; i < n+1; ++i) {
   
      G->discover[i] = UNDEF;
   
      G->finish[i] = UNDEF;
   
      G->color[i] = WHITE;
   
      G->parent[i] = NILL;
   
      G->adj[i] = newList();
   }
   
   return G;
}

// Graph Deconstructor
void freeGraph(Graph *pG) {
   Graph none = *pG;
   
   int i;
   
   for(i = 0; i < (none->order + 1); ++i) {
   
      freeList(&(none->adj[i]));
   }
   
   free(none->adj);
   
   free(none->parent);
   
   free(none->discover);
   
   free(none->finish);
   
   free(none->color);
   
   free(*pG);
   
   *pG = NULL;
}

// returns the corresponding field value (vertex)
int getOrder(Graph G) {
   if(G == NULL) {
      printf("Graph Error: getOrder() called on NULL Graph pointer\n");
      exit(1);
   }
   return G->order;
}

// // returns the corresponding field value (edges)
int getSize(Graph G) {
   
   if(G == NULL) {
   
      printf("Graph Error: getSize() called on NULL Graph pointer\n");
   
      exit(1);
   }
   
   return  G->size;
   
}

// return the parent of vertex u in the BreadthFirsttree created by BFS() 
// NIL if BFS() has not yet been called
// Pre: 1 <= u <= getOrder(G)
int getParent(Graph G, int u) {

    if(G == NULL) {
      
      printf("Graph Error: getParent() called on NULL Graph pointer\n");
      
      exit(1);
   }

   if(u < 1 || u > getOrder(G)) {
     
     printf("Graph Error: getParent() called on out of bounds vertex\n");
     
     exit(1); 
   }
   
   return G->parent[u];
}

// Returns the distance from the most recent DFS source to vertex u
// Pre: 1 <= u <= getOrder(G)
int getDiscover(Graph G, int u) {
    
    if(G == NULL) {
    
      printf("Graph Error: getDiscover() called on NULL Graph pointer\n");
    
      exit(1);
   }
   
   if(u < 1 || u > getOrder(G)) {
   
     printf("Graph Error: getDiscover() called on out of bounds index\n");
   
     exit(1); 
   }
   
   return G->discover[u];
}

// Returns the finish time of vertex u
// Pre: 1 <= u <= n = getOrder(G)
int getFinish(Graph G, int u) {
    
    if(G == NULL) {

      printf("Graph Error: getDist() called on NULL Graph pointer\n");
    
      exit(1);
   }
   
   if(u < 1 || u > getOrder(G)) {
   
     printf("Graph Error: getDist() called on out of bounds index\n");
   
     exit(1); 
   }
   
   return G->finish[u];
}

// inserts a new edge joining u to v
// Pre: 1 <= u <= getOrder(G), 1 <= v <= getOrder(G)
void addEdge(Graph G, int u, int v) {
   
   if(u < 1 || u > getOrder(G)){
   
     printf("Graph Error: addEdge() called on vertex out of bounds\n");
   
     exit(1);
  }
   
   if (v < 1 || v > getOrder(G)) {
   
     printf("Graph Error: addEdge() called on vertex out of bounds\n");
   
     exit(1); 
   }
   
   addArc(G, u, v);
   
   addArc(G, v, u);
   
   G->size--;
   }

// inserts a new directed edge from u to v
// Pre: 1 <= u <= getOrder(G), 1 <= v <= getOrder(G)
void addArc(Graph G, int u, int v) {

   if(u < 1 || u > getOrder(G)){
  
     printf("Graph Error: addArc() called on vertex out of bounds\n");
  
     exit(1);
  }
  
   if (v < 1 || v > getOrder(G)) {
  
     printf("Graph Error: addArc() called on vertex out of bounds\n");
  
     exit(1); 
   }
  
   List S = G->adj[u];
  
   if(length(S) == 0){
  
      append(S,v);
  
      return;
  
   }else{
  
      moveFront(S);
  
      while(index(S) != -1){
  
         if (v == get(S)){
  
            return;
  
         }else if( get(S) < v){
  
            moveNext(S);
  
            continue;
  
         }else{
  
            insertBefore(S,v);
  
            return;
         }

      }
  
         append(S,v);
      }
   }


// performs DFS Algorithm on Graph G with source s
void DFS(Graph G, List S) {

   if (G == NULL) {
  
      printf("Graph Error: DFS() called on NULL Graph pointer\n");
  
      exit(1);
  
   } else if (S == NULL) {
  
      printf("Graph Error: DFS() called on NULL List pointer\n");
  
      exit(1);
  
   } else if (length(S) != getOrder(G)) {
  
      printf("Graph Error: DFS() called with invalid List\n");
  
      exit(1);
   }

   int time = 0;

   int i;
  
   for(i = 1; i < getOrder(G); ++i) {
  
      G->color[i] = WHITE;
  
      G->discover[i] = UNDEF;
  
      G->finish[i] = UNDEF;
  
      G->parent[i] = NILL;

   }
  
   moveFront(S);
  
   while(index(S) >= 0) {
  
      int u = get(S);
  
      if(G->color[u] == WHITE) {
  
         Visit(G, S, u, &time);   
      }
  
      moveNext(S);
   }
      int numSize;
      
      for(numSize = length(S)/2; numSize > 0; --numSize){
	
	deleteBack(S);
      }
}

// prints the adjacency list representation of G to the file pointed to by out
void printGraph(FILE *out, Graph G) {

  if(out == NULL) {
   
      printf("Graph Error: printGraph() called on a NULL FILE pointer");
   
      exit(1);
   }
   
   if(G == NULL) {
   
      printf("Graph Error: printGraph() called on a NULL Graph pointer");
   
      exit(1);
   }
   
   for(int i = 1; i <= getOrder(G); ++i) {
  
      fprintf(out, "%d: ", i);
  
      printList(out, G->adj[i]);
  
      fprintf(out, "\n");
   }
}

// Returns a new Graph that is a copy of Graph G
Graph copyGraph(Graph G) {
	
   if (G == NULL) {

       printf("Graph Error: copyGraph() called on NULL Graph pointer.\n");
	}
   
   Graph T = newGraph(getOrder(G));
   
   int i;
   
   for(i = 1; i <= getOrder(G); ++i) {
   
   	  List I = G->adj[i];
   
      moveFront(I);
   
      while(index(I) >= 0) {
   
      	 int z = get(I);
   
         addArc(T, i, z); 
   
         moveNext(I);
      }
   }
   
   return T;
}

// Returns a new Graph that is the transpose of Graph G
Graph transpose(Graph G) {
   
   if (G == NULL) {
   
      printf("Graph Error: transpose() called on NULL Graph pointer.\n");
   }
   
   Graph T = newGraph(getOrder(G));
   
   int i;
   
   for(i = 1; i <= getOrder(G); ++i) {
   
   	  List I = G->adj[i];
   
      moveFront(I);
   
      while(index(I) >= 0) {
   
      	int z = get(I);
   
        addArc(T, z, i);
   
        moveNext(I);  
      }
   }
   
   return T;
}

// private inner recursive function of DFS()
void Visit(Graph G, List S, int u, int *time) {
  
  if (S == NULL || time == NULL) {
  
     printf("Graph Error: Visit called with null pointers\n");
  
     exit (1);
  }

   G->color[u] = GRAY;
  
   G->discover[u] = ++*time;
  
   List neighbors = G->adj[u];
  
   moveFront(neighbors);
  
   while(index(neighbors) >= 0) {
  
      int v = get(neighbors);
  
      if(G->color[v] == WHITE) {
  
         G->parent[v] = u;
  
         Visit(G, S, v, time);
      }
  
      moveNext(G->adj[u]);
   }
  
   G->color[u] = BLACK;
  
   G->finish[u] = ++*time;
  
   prepend(S, u);
}


