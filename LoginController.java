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
	private Scene managerScene;
	private Database db;

	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Login controller running");

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
		this.db = new Database();
	}

	// loads in the employee scene for when we want to switch
	public void setEmployeeScene(Scene scene) {
		System.out.println("setting employee");
        employeeScene = scene;
    }

    public void setManagerScene(Scene scene) {
    	System.out.println("setting manager");
        managerScene = scene;
    }


	private boolean validateCredentials(String email, String pass) {
		//return db.getPasswd(email) == pass ? 'm' : 'e';
		//return m if manager AND password is valid, return e if employee
		//and password is valid, else return x
		return false;
	}

	private void triggerLogin() {
		System.out.println("triggering login");
		openManagerScene();
	}

	public void openEmployeeScene() {    
        Stage stage = (Stage) (bLogin.getScene().getWindow());
        stage.setScene(employeeScene);
    }

    public void openManagerScene() {    
        Stage stage = (Stage) (bLogin.getScene().getWindow());
        stage.setScene(managerScene);
    }


}
