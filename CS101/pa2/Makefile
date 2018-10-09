#------------------------------------------------------------------------ 
# Sudeep Baniya
# CMPS 101
# sucbaniy
# PA5 
# Makefile
# Makefile for PA5
#------------------------------------------------------------------------

#------------------------------------------------------------------------------
#  Makefile for CMPS 101 Programming Assignment 5
#
#  make                     makes FindComponents
#  make GraphClient         makes GraphClient
#  make clean               removes binaries
#  make checkFind           tests FindComponents for memory leaks on in3
#  make checkClient         tests GraphClient for memory leaks
#------------------------------------------------------------------------------

BASE_SOURCES   = Graph.c List.c
BASE_OBJECTS   = Graph.o List.o
HEADERS        = Graph.h List.h
COMPILE        = gcc -c -std=c99 -Wall
LINK           = gcc -o
REMOVE         = rm -f
MEMCHECK       = valgrind --leak-check=full

FindComponents : FindComponents.o $(BASE_OBJECTS)
	$(LINK) FindComponents FindComponents.o $(BASE_OBJECTS)

GraphClient : GraphClient.o $(BASE_OBJECTS)
	$(LINK) GraphClient GraphClient.o $(BASE_OBJECTS)

GraphTest : GraphTest.o $(BASE_OBJECTS)
	$(LINK) GraphTest GraphTest.o $(BASE_OBJECTS)

FindComponents.o : FindComponents.c $(HEADERS)
	$(COMPILE) FindComponents.c

GraphClient.o : GraphClient.c $(HEADERS)
	$(COMPILE) GraphClient.c

$(BASE_OBJECTS) : $(BASE_SOURCES) $(HEADERS)
	$(COMPILE) $(BASE_SOURCES)

clean :
	$(REMOVE) FindComponents GraphClient GraphTest FindComponents.o GraphClient.o GraphTest.o out11 out12 out13 out14 out15 out16 $(BASE_OBJECTS)

checkFind : FindComponents
	$(MEMCHECK) FindComponents in3 junk3

checkClient : GraphClient
	$(MEMCHECK) GraphClient

checkTest : GraphTest
	$(MEMCHECK) GraphTest

submit : 
	submit cmps101-pt.w17 pa5 List.c List.h Graph.c Graph.h GraphTest.c FindPath.c Makefile README

input : 
	FindComponents in1 out11
	FindComponents in2 out12
	FindComponents in3 out13
	FindComponents in4 out14
	FindComponents in5 out15
	FindComponents in6 out16
	FindComponents in7 out17

diff : 
	diff out1 out11
	diff out2 out12
	diff out3 out13
	diff out4 out14
	diff out5 out15
	diff out6 out16
	diff out7 out17
