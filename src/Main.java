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

/**
 * JavaFX Main Class
 */
public class Main extends Application {
    /**
     * main function
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * starts the program by setting up the login, employee, and manager fxml scenes
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // set up login scene
        FXMLLoader loginLoader = new FXMLLoader(
                getClass().getResource("login.fxml"));
        Parent loginParent = (Parent) loginLoader.load();
        Scene loginScene = new Scene(loginParent, 650, 650);

        // set up employee scene
        FXMLLoader employeeLoader = new FXMLLoader(
                getClass().getResource("employee.fxml"));
        Parent employeeParent = (Parent) employeeLoader.load();
        Scene employeeScene = new Scene(employeeParent, 650, 650);

        // set up manager scene
        FXMLLoader managerLoader = new FXMLLoader(
                getClass().getResource("manager.fxml"));
        Parent managerParent = (Parent) managerLoader.load();
        Scene managerScene = new Scene(managerParent, 650, 650);

        LoginController loginController = (LoginController) loginLoader.getController();
        // ManagerController managerController = (ManagerController)
        // oginLoader.getController();

        loginController.setEmployeeScene(employeeScene);
        // managerController.setEmployeeScene(employeeScene)
        loginController.setManagerScene(managerScene);

        primaryStage.setTitle("315 Project 2");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

}
