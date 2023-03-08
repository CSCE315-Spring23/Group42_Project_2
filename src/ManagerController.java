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
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManagerController implements Initializable {
	@FXML
	private Button addMenu;
	@FXML
	private Button updateMenu;
	@FXML
	private Button addInventory;
	@FXML
	private Button updateInventory;
	@FXML
	TextField fItemName;
	@FXML
	TextField fCost;

	private Database db;

	@FXML
	private TableView<Inventory> inventoryTable;
	@FXML
	private TableView<Menu> menuTable;
	@FXML
	private TableColumn<Inventory, Long> inventoryID;
	@FXML
	private TableColumn<Inventory, String> inventoryItemName;
	@FXML
	private TableColumn<Inventory, Double> inventoryItemCost;
	@FXML
	private TableColumn<Inventory, Long> inventoryItemQty;
	@FXML
	private TableColumn<Menu, Long> menuID;
	@FXML
	private TableColumn<Menu, String> menuItemName;
	@FXML
	private TableColumn<Menu, Double> menuItemCost;

	// @FXML
	// private Button switchView;
	//
	// private Scene employeeScene;

	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Manager controller running");
		this.db = new Database();

		this.setUpInventoryTable();
		this.setUpMenuTable();
		this.updateInventoryTable(0);
		this.updateMenuTable(0);
		this.inventoryTable.refresh();
		this.menuTable.refresh();
		System.out.println("we get here");

		addMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
			}
		});

		updateMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Manager click");
				String newCost = fCost.getText();
				String itemName = fItemName.getText();
				itemName = itemName.strip();
				newCost = newCost.strip();
				db.changePrice(itemName, Double.parseDouble(newCost));
				updateMenuTable(0);
				menuTable.refresh();
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

		// switchView.setOnAction(new EventHandler<ActionEvent>() {
		// public void handle(ActionEvent event) {
		// openEmployeeScene();
		// }
		// });

	}
	// public void openEmployeeScene() {
	// Stage stage = (Stage) (switchView.getScene().getWindow());
	// stage.setScene(employeeScene);
	// }

	private void updateMenuTable(int whichTwenty) {
		this.menuTable.setItems(db.get20RowsMenu(whichTwenty));
		this.menuTable.refresh();
	}

	private void updateInventoryTable(int whichTwenty) {
		this.inventoryTable.setItems(db.get20RowsInventory(whichTwenty));
		this.inventoryTable.refresh();
	}

	private void setUpInventoryTable() {
		this.inventoryID.setCellValueFactory(cellData -> cellData.getValue().getInventoryID());
		this.inventoryItemName.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		this.inventoryItemCost.setCellValueFactory(cellData -> cellData.getValue().getItemCost());
		this.inventoryItemQty.setCellValueFactory(cellData -> cellData.getValue().getItemQuantity());
		final ObservableList<Inventory> items = db.get20RowsInventory(0);

		this.inventoryTable.setItems(items);
	}

	private void setUpMenuTable() {
		this.menuID.setCellValueFactory(cellData -> cellData.getValue().getMenuID());
		this.menuItemName.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		this.menuItemCost.setCellValueFactory(cellData -> cellData.getValue().getItemCost());
		final ObservableList<Inventory> items = db.get20RowsInventory(0);

		this.inventoryTable.setItems(items);
	}

	// public void setEmployeeScene(Scene scene) {
	// employeeScene = scene;
	// }
}
