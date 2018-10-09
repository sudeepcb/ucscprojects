# Makefile
# Sudeep Baniya
# sucbaniy
# pa4
# Make file for Roots program

Roots: Roots.class
	echo Main-class: Roots > Manifest
	jar cvfm Roots Manifest Roots.class
	rm Manifest
	chmod u+x Roots
Roots.class: Roots.java
	javac -Xlint Roots.java
	java Roots
clean:
	rm -f Roots.class Roots
submit: Makefile Roots.java
	submit cmps011-pt.w16 pa4 Makefile Roots.java
