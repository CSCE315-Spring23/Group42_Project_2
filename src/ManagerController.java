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
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManagerController implements Initializable {
	@FXML
	private Button viewMenu;
	@FXML
	private Button addMenu;
	@FXML
	private Button updateMenu;
	@FXML
	private Button viewInventory;
	@FXML
	private Button addInventory;
	@FXML
	private Button updateInventory;

	private Database db;

	// @FXML
	// private TableColumn<Class, String> inventoryID;
	// private TableColumn<Class, String> inventoryItemName;
	// private TableColumn<Class, String> inventoryItemCost;
	// private TableColumn<Class, String> inventoryItemQty;
	// private TableColumn<Class, String> menuID;
	// private TableColumn<Class, String> menuItemName;
	// private TableColumn<Class, String> menuItemCost;

	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Manager controller running");
		this.db = new Database();

		viewMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
			}
		});

		addMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
			}
		});

		updateMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
			}
		});

		viewInventory.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
			}
		});

		addInventory.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
			}
		});

		updateInventory.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
			}
		});
	}

}
