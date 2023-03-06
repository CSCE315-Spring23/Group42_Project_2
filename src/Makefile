JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java --module-path "./javaFX/lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics *.java
TERMINAL = Driver
GUI = ./java/*.java

CLASSES = \
	LoginController.java \
	Main.java \
	EmployeeController.java \
	Database.java


default: classes

classes: $(CLASSES:.java=.class)

noGUI: classes
	$(JVM) $(TERMINAL)

GUI: 
	javac $(GUI) --module-path "./javaFX/lib" --add-modules javafx.controls,javafx.fxml
	java --module-path "./javaFX/lib" --add-modules javafx.controls,javafx.fxml -cp ".;postgresql-42.2.8.jar" ./java/Main.java

clean:
	rm -rf ./java/*.class