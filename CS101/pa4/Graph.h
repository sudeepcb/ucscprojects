//------------------------------------------------------------------------ 
// Sudeep Baniya
// CMPS 101
// sucbaniy
// PA4 
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

// access functions
int getOrder(Graph G);

int getSize(Graph G);

int getSource(Graph G);

int getParent(Graph G, int u);

int getDist(Graph G, int u);

void getPath(List L, Graph G, int u);

// Manipulation procedures
void makeNull(Graph G);

void addEdge(Graph G, int u, int v);

void addArc(Graph G, int u, int v);

void BFS(Graph G, int s);

// other operations
void printGraph(FILE *out, Graph G);

#endif
