#------------------------------------------------------------------------------
# Sudeep Baniya 
# CMPS 101
# sucbaniy
# 1/19/2017
# Makefile for pa1
# This Makefile uses wildcards and pattern substitution, which have not been
# explained in any previous assignment.  See the GNU Make tutorial 
#------------------------------------------------------------------------------

JAVAC      = javac 
MAINCLASS  = Lex
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
SUBMIT     = submit cmps101-pt.w17 pa1
CHECK      = ls /afs/cats.ucsc.edu/class/cmps101-pt.w17/pa1/sucbaniy


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)

submit: $(SOURCES)
	 $(SUBMIT) $(SOURCES)

check:
	$(CHECK)


