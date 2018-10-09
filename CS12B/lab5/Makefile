#------------------------------------------------------------------------------
#------------------------------------------------------------------------------
# Sudeep Baniya
# CMPS 12B
# sucbaniy
# 4-28-2016
# makefile for Dictionary.c with macros
#------------------------------------------------------------------------------

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c 
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
SUBMIT  = submit cmps012b-pt.s16 lab5
FILES   = Dictionary.c DictionaryClient.c DictionaryTest.c Dictionary.h README Makefile
all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit: $(FILES)
	$(SUBMIT) $(FILES)
