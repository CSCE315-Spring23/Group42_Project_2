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
	EmployeeController.java \
	ManagerController.java \
	Database.java \
	dbSetup.java


default: classes

classes: $(CLASSES:.java=.class)

noGUI: classes
	$(JVM) $(TERMINAL)

GUI: 
	javac $(GUI) --module-path "./javaFX/lib" --add-modules javafx.controls,javafx.fxml
	java --module-path "./javaFX/lib" --add-modules javafx.controls,javafx.fxml -cp ".;postgresql-42.2.8.jar" 

me:
	$(JVM) --module-path "javaFX/lib" --add-modules javafx.controls,javafx.fxml $(GUI) 

clean:
	$(RM) *.class