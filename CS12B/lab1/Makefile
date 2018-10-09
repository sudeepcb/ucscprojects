#------------------------------------------------------------------------------
# Sudeep Baniya
# CMPS 12M
# sucbaniy
# 4-4-2016
# A makefile tht creates and archives both .class files into an exectuable jar file called Hello
# Makefile
#------------------------------------------------------------------------------
JAVASRC   = HelloUser.java HelloUser2.java
SOURCES   = README Makefile $(JAVASRC)
MAINCLASS = HelloUser2
CLASSES   = HelloUser2.class HelloUser.class
JARFILE   = Hello
SUBMIT    = submit cmps012b-pt.s16 lab1
CHECK     = ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/lab1/sucbaniy

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
