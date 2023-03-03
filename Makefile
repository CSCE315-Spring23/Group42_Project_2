JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java --module-path "javaFX/lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics *.java
TERMINAL = Driver
GUI = Main

CLASSES = \
	LoginController.java \
	Main.java \
	EmployeeController.java


default: classes

classes: $(CLASSES:.java=.class)

noGUI: classes
	$(JVM) $(TERMINAL)

GUI: classes
	$(JVM) --module-path "javaFX/lib" --add-modules javafx.controls,javafx.fxml $(GUI) 

clean:
	$(RM) *.class