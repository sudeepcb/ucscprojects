//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA4 
// Graph.c
// Graph ADT implemention used to find shortest vertex and edges
//------------------------------------------------------------------------

#include <stdlib.h>
#include <stdio.h>
#include "List.h"
#include "Graph.h"

// Enum for BFS algorithm
#define WHITE 0
#define GRAY 1
#define BLACK 2
#define NILL -2
#define INF  -1

// private GraphObj
typedef struct GraphObj{ 
   int order;

   int size;
   
   int source;
   
   int *distance;
   
   int *color;
   
   int *parent;
   
   List* adj; 

} GraphObj;

// Returns a pointer to a new GraphObj
Graph newGraph(int n) {

   Graph G = malloc(sizeof( GraphObj));
  
   G->adj = malloc((n+1) * sizeof(List));
  
   G->color = malloc((n+1) * sizeof(int));
  
   G->parent = malloc((n+1) * sizeof(int));
  
   G->distance = malloc((n+1) * sizeof(int));
  
   G->order = n;
  
   G->size = 0;
  
   G->source = NILL;

   int i;
  
   for(i = 0; i < n+1; ++i) {
  
      G->distance[i] = INF;
  
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
  
   free(none->distance);
  
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

//  return the corresponding field value (edges)
int getSize(Graph G) {
   
   if(G == NULL) {
   
      printf("Graph Error: getSize() called on NULL Graph pointer\n");
   
      exit(1);
   }
   
   int s = G->size;

   //return G->size;
   return abs(s);
}

//  returns the source vertex most recently used in function BFS()
//  NIL if BFS() has not ran yet
int getSource(Graph G) {
   
   if(G == NULL) {
   
      printf("Graph Error: getSource() called on NULL Graph pointer\n");
   
      exit(1);
   }
   
   return G->source;
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

// returns the distance from the most recent BFS source to vertex u 
// INF if BFS() has not yet been called
// Pre: 1 <= u <= getOrder(G)
int getDist(Graph G, int u) {
   
    if(G == NULL) {
   
      printf("Graph Error: getDist() called on NULL Graph pointer\n");
   
      exit(1);
   }
   
   if(u < 1 || u > getOrder(G)) {
   
     printf("Graph Error: getDist() called on out of bounds index\n");
   
     exit(1); 
   }
   
   return G->distance[u];
}

// append to the List L the vertices of a shortest path in G from source to u
// append to L the value NIL if no such path exists
// Pre: 1 <= u <= getOrder(G)
void getPath(List L, Graph G, int u) {
   
   if(L == NULL) {
   
      printf("Graph Error: getPath() called on NULL List pointer\n");
   
      exit(1); 
   }

   if(G == NULL) {
   
      printf("Graph Error: getPath() called on NULL Graph pointer\n"); 
   
      exit(1);
   }

   if(getSource(G) == NILL) {
   
      printf("Graph Error: getPath() called on NULL pointer\n");
   
      exit(1);
   }
   
   if(u < 1 || u > getOrder(G)) {
   
     printf("Graph Error: getPath() called on vertex out of bounds\n");
   
     exit(1); 
   }
   
   int s = G->source;
   
   if(u == s) {
   
      prepend(L, s);
   
   } else if(G->parent[u] == NILL) {
   
      append(L,NILL);
   
   }else{
	    prepend(L,u);
	
	    getPath(L,G,G->parent[u]);
}
}

// deletes all edges of G, restoring it to its original (no edge) state
void makeNull(Graph G) {
   
   if(G == NULL) {
   
      printf("Graph Error: getPath() called on NULL Graph pointer\n"); 
   
      exit(1);
   }

   int i;
   
   for(i = 0; i < G->order + 1; ++i) {
   
     clear(G->adj[i]);
   }
   
   G->size = 0;
   
   G->source = NILL;
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


// performs BFS algorithm on the Graph G with source s
void BFS(Graph G, int s) {
   
   if( G==NULL ){
   
        printf("Graph Error: BFS() called on NULL Graph pointer");
   
        exit(1);
    }
   
    if( 1>s || s>getOrder(G) ){
   
        printf("Graph Error:  BFS() called on vertex out of bounds");
   
        exit(1);
    }
   
   G->source = s;
   
   int i;
   
   for(i = 0; i < getOrder(G) + 1; ++i) {
   
      G->color[i] = WHITE;
   
      G->distance[i] = INF;
   
      G->parent[i] = NILL;
   }
   
   G->color[s] = GRAY;
   
   G->distance[s] = 0;
   
   List Q = newList();
   
   prepend(Q, s);
   
   while(length(Q) > 0) {
   
      int u = back(Q);
   
      deleteBack(Q);
   
      List adj = G->adj[u];
   
      moveFront(adj);
   
      while(index(adj) > -1) {
   
         int v = get(adj);
   
         if(G->color[v] == WHITE) {
   
            G->color[v] = GRAY;
   
            G->distance[v] = G->distance[u] + 1;
   
            G->parent[v] = u;
   
            prepend(Q, v);
         }
   
         moveNext(adj);
      }
   
      G->color[u] = BLACK;
   }
   
   freeList(&Q); 
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
