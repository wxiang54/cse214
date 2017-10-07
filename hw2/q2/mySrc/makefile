make: in2.txt TeamSelection.java mySrc/DLL.java mySrc/MyIterable.java mySrc/MyIterator.java
	javac -d . TeamSelection.java

#	javac 

run: make
	java TeamSelection

test: make
	java mySrc.DLL

clean:
	rm -f *.class *~ *\#
	rm -f */*.class */*~ */*\#
