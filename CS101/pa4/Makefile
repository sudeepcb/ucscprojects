#------------------------------------------------------------------------ 
# Sudeep Baniya
# CMPS 101
# sucbaniy
# PA4 
# Makefile
# Makefile for PA4
#------------------------------------------------------------------------

#------------------------------------------------------------------------------
#  Makefile for CMPS 101 Programming Assignment 4
#
#  make                     makes FindPath
#  make GraphClient         makes GraphClient
#  make clean               removes binaries
#  make checkFind           tests FindPath for memory leaks on in3
#  make checkClient         tests GraphClient for memory leaks
#------------------------------------------------------------------------------

BASE_SOURCES   = Graph.c List.c
BASE_OBJECTS   = Graph.o List.o
HEADERS        = Graph.h List.h
COMPILE        = gcc -c -std=c99
LINK           = gcc -o
REMOVE         = rm -f
MEMCHECK       = valgrind --leak-check=full

FindPath : FindPath.o $(BASE_OBJECTS)
	$(LINK) FindPath FindPath.o $(BASE_OBJECTS)

GraphClient : GraphClient.o $(BASE_OBJECTS)
	$(LINK) GraphClient GraphClient.o $(BASE_OBJECTS)

GraphTest : GraphTest.o $(BASE_OBJECTS)
	$(LINK) GraphTest GraphTest.o $(BASE_OBJECTS)

Graph_BFS : Graph_BFS.o $(BASE_OBJECTS)
	$(LINK) Graph_BFS Graph_BFS.o $(BASE_OBJECTS)

FindPath.o : FindPath.c $(HEADERS)
	$(COMPILE) FindPath.c

GraphClient.o : GraphClient.c $(HEADERS)
	$(COMPILE) GraphClient.c

$(BASE_OBJECTS) : $(BASE_SOURCES) $(HEADERS)
	$(COMPILE) $(BASE_SOURCES)

clean :
	$(REMOVE) FindPath GraphClient GraphTest FindPath.o GraphClient.o GraphTest.o out11 out12 out13 out14 out15 out16 $(BASE_OBJECTS)

checkFind : FindPath
	$(MEMCHECK) FindPath in3 junk3

checkClient : GraphClient
	$(MEMCHECK) GraphClient

checkTest : GraphTest
	$(MEMCHECK) GraphTest

submit : 
	submit cmps101-pt.w17 pa4 List.c List.h Graph.c Graph.h GraphTest.c FindPath.c Makefile README
input : 
	FindPath in1 out11
	FindPath in2 out12
	FindPath in3 out13
	FindPath in4 out14
	FindPath in5 out15
	FindPath in6 out16

diff : 
	diff out1 out11
	diff out2 out12
	diff out3 out13
	diff out4 out14
	diff out5 out15
	diff out6 out16
