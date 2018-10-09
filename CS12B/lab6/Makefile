#------------------------------------------------------------------------------
# Sudeep Baniya
# CMPS 12B
# sucbaniy
# 4-28-2016
# makefile for ListADT
#------------------------------------------------------------------------------
# Variables
SOURCES    = List.java ListInterface.java ListClient.java ListIndexOutOfBoundsException.java ListTest.java
MAINCLASS  = ListClient 
CLASSES    = List.class ListInterface.class ListClient.class List\$$Node.class\
             ListIndexOutOfBoundsException.class
JARFILE    = ListClient
SUBMIT    = submit cmps012b-pt.s16 lab6
CHECK     = ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab6/sucbaniy
# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint:all,-serial $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE) 

submit: $(SOURCES)
	 $(SUBMIT) $(SOURCES)

check:
	$(CHECK)
