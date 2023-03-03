
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.R;

public class LoginController implements Initializable {
	@FXML Button bLogin;

	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("controller running");


		bLogin = (Button)findIdById(R.id.bLogin);
	    bLogin.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent event) {
	            System.out.println("Attempting to log in...");
	        }
	    });
	}


	private boolean validateCredentials() {
		return true;
	}


}
