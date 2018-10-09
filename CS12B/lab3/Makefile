#------------------------------------------------------------------------------
# Sudeep Baniya
# CMPS 12M
# sucbaniy
# 4-19-2016
# Makefile for FileReverse.c with Macros
# Makefile
#------------------------------------------------------------------------------

FLAGS = -std=c99 -Wall
SOURCES = FileReverse.c Makefile README
OBJECTS = FileReverse.c
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.s16 lab3
CHECK  = ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab3/sucbaniy

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	$(CHECK)

