#------------------------------------------------------------------------------
# Sudeep Baniya
# CMPS 12M
# sucbaniy
# 5-3-2016
# Makefile for charType.c with Macros
# Makefile
#------------------------------------------------------------------------------

FLAGS = -std=c99 -Wall
SOURCES = charType.c Makefile README
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.s16 lab4
CHECK  = ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab4/sucbaniy

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



