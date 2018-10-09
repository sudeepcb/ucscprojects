#------------------------------------------------------------------------------
# Sudeep Baniya
# CMPS 12B
# sucbaniy
# 4-17-2016
# A makefile that creates and archives both .class files into an exectuable jar file called Search
# Makefile
#------------------------------------------------------------------------------
JAVASRC   = sbi.scm
SOURCES   = README PARTNER $(JAVASRC)
CLASSES   = HelloWorld.class
JARFILE   = HelloWorld
SUBMIT    = submit cmps112-wm.s18 asg1
CHECK     = ls /afs/cats.ucsc.edu/class/cmps112-wm.s18/asg1/sucbaniy

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
