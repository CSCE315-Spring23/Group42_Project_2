import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
 
public class Main extends Application { // could be different
    public static void main(String[] args) { launch(args); }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	FXMLLoader loader = new FXMLLoader(
    	        getClass().getResource("login.fxml")
    	);
    	loader.setController(loader.getController());
    	Parent root = (Parent) loader.load();
    	
        primaryStage.setTitle("315 Project 2");
        primaryStage.setScene(new Scene(root, 750, 750));
        primaryStage.show();
    }

    /*public void switchScene(String filename) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("login.fxml")
        );
        loader.setController(loader.getController());
        Parent root = (Parent) loader.load();
        
        primaryStage.setTitle("315 Project 2");
        primaryStage.setScene(new Scene(root, 750, 750));
        primaryStage.show();
    }*/
    
    
    
    
}
