#------------------------------------------------------------------------------
# Sudeep Baniya
# CMPS 12M
# sucbaniy
# 4-11-2016
# A makefile tht creates and archives both .class files into an exectuable jar file called FileReverse
# Makefile
#------------------------------------------------------------------------------
JAVASRC   = FileReverse.java
SOURCES   = README Makefile $(JAVASRC)
MAINCLASS = FileReverse
CLASSES   = FileReverse.class 
JARFILE   = FileReverse 
SUBMIT    = submit cmps012b-pt.s16 lab2
CHECK     = ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab2/sucbaniy

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check: 
	$(CHECK)
