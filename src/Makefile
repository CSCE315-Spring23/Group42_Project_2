JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) -cp ".:postgresql-42.5.4.jar" $(JFLAGS) $*.java --module-path "javaFX/lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics *.java
TERMINAL = Driver
GUI = Main

CLASSES = \
	LoginController.java \
	Main.java \
	EmployeeController.java \
	ManagerController.java \
	Database.java \
	dbSetup.java \
	postgresql-42.5.4.jar


default: classes

classes: $(CLASSES:.java=.class)

noGUI: classes
	$(JVM) $(TERMINAL)

GUI: 
	javac $(GUI) --module-path "./javaFX/lib" --add-modules javafx.controls,javafx.fxml
	java --module-path "./javaFX/lib" --add-modules javafx.controls,javafx.fxml -cp ".;postgresql-42.5.8.jar" 

me:
	$(JVM) --module-path "javaFX/lib" --add-modules javafx.controls,javafx.fxml $(GUI) 

clean:
	$(RM) *.class