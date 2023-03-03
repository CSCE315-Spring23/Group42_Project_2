import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML Button bLogin;
	@FXML TextField fEmail;
	@FXML TextField fPassword;
	private Scene employeeScene;

	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("controller running");

	    bLogin.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent event) {
	            System.out.println("Attempting to log in...");
	            if(validateCredentials(fEmail.getText(), fPassword.getText())) {
	            	triggerLogin();
	            } else {
	            	System.out.println("Invalid credentials!");
	            }
	        }
	    });
	}

	// loads in the employee scene for when we want to switch
	public void setEmployeeScene(Scene scene) {
        employeeScene = scene;
    }


	private boolean validateCredentials(String email, String pass) {
		return true;
	}

	private void triggerLogin() {
		openEmployeeScene();
	}

	public void openEmployeeScene() {    
        Stage stage = (Stage) (bLogin.getScene().getWindow());
        stage.setScene(employeeScene);
    }


}
