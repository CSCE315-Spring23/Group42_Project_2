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

/**
 * Controls the backend for the login interface
 */
public class LoginController implements Initializable {
	/**
	 * button for login
	 */
	@FXML
	Button bLogin;
	/**
	 * email text field
	 */
	@FXML
	TextField fEmail;
	/**
	 * password text field
	 */
	@FXML
	TextField fPassword;
	/**
	 * employee JavaFX scene
	 */
	private Scene employeeScene;
	/**
	 * manager JavaFX scene
	 */
	private Scene managerScene;
	/**
	 * database
	 */
	private Database db;

	/**
	 * 
	 * initializes the scene and implements trigger functionality
	 *
	 * @param location URL
	 * @param resources resources bundle
	 * 
	 */
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Login controller running");

		/**
		 * authenticates password by querying password from database by email address
		 */
		bLogin.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Attempting to log in...");
				char perm = validateCredentials(fEmail.getText(), fPassword.getText());
				switch (perm) {
					case 'm':
						triggerLogin('m');
						break;
					case 'e':
						triggerLogin('e');
						break;
					default:
						System.out.println("Invalid credentials!");

				}
			}
		});
		this.db = new Database();
	}

	/**
	 * loads in the employee scene for when we want to switch
	 * 
	 * @param scene scene to load
	 */
	public void setEmployeeScene(Scene scene) {
		System.out.println("setting employee");
		employeeScene = scene;
	}

	/**
	 * loads in the manager scene for when we want to switch
	 * 
	 * @param scene scene to load
	 */
	public void setManagerScene(Scene scene) {
		System.out.println("setting manager");
		managerScene = scene;
	}

	/**
	 * Given email and password, gives respective authentication to the application.
	 * If authentication fails, no access. If manager, full access, if employee,
	 * limited access.
	 * 
	 * @param email email of user
	 * @param pass password of user
	 * @return
	 */
	private char validateCredentials(String email, String pass) {
		return db.getPasswd(email).equals(pass) ? db.isManager(email) ? 'm' : 'e' : 'x';

	}

	/**
	 * based on permissions, logs in to the correct interface, or doesn't log in at
	 * all
	 * 
	 * @param perm either m or e for manager and employee
	 */
	private void triggerLogin(char perm) {
		if (perm == 'm') {
			System.out.println("triggering manager login");
			openManagerScene();
		} else if (perm == 'e') {
			System.out.println("triggering employee login");
			openEmployeeScene();
		}
	}

	/**
	 * opens the Employee facing GUI
	 */
	public void openEmployeeScene() {
		Stage stage = (Stage) (bLogin.getScene().getWindow());
		stage.setScene(employeeScene);
	}

	/**
	 * opens the Managerial GUI
	 */
	public void openManagerScene() {
		Stage stage = (Stage) (bLogin.getScene().getWindow());
		stage.setScene(managerScene);
	}

}
