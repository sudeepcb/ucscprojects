//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA5 
// Graph.h
// Header file for Graph.c
//-----------------------------------------------------------------------


#ifndef __GRAPH_H__INCLUDE_
#define __GRAPH_H__INCLUDE_

#include <stdio.h>
#include "List.h"

// Structures
typedef struct GraphObj* Graph;

// Constructors/Deconstructors
Graph newGraph(int n);

void freeGraph(Graph* pG);

// access function
int getOrder(Graph G);

int getSize(Graph G);

int getParent(Graph G, int u);

int getDiscover(Graph G, int u);

int getFinish(Graph G, int u);

// Manipulation procedures
void addArc(Graph G, int u, int v);

void addEdge(Graph G, int u, int v);

void DFS(Graph G, List S);

// other operations
void printGraph(FILE *out, Graph G);

Graph transpose(Graph G);

Graph copyGraph(Graph G);

#endif
