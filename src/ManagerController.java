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
	private Button bAddInventory;
	@FXML
	private Button bUpdateInventory;
	@FXML
	private Button bAddRecipe;
	@FXML
	private Button bUpdateRecipe;

	@FXML
	TextField fInventoryID;
	@FXML
	TextField fItemName;
	@FXML
	TextField fItemCost;
	@FXML
	TextField fItemQuantity;

	@FXML
	TextField fMenuID;
	@FXML
	TextField fMenuName;
	@FXML
	TextField fMenuPrice;

	@FXML
	TextField fRecipeName;
	@FXML
	TextField fRecipeID;
	@FXML
	TextField fRecipeInventoryID;
	@FXML
	TextField fRecipeMenuID;
	@FXML
	TextField fRecipeQuantity;

	private Database db;

	@FXML
	private TableView<SaleData> salesTable;
	@FXML
	private TableView<Inventory> inventoryTable;
	@FXML
	private TableView<Menu> menuTable;
	@FXML
	private TableView<Recipe> recipeTable;
	@FXML
	private TableView<SaleData> salesHistoryTable;
	@FXML
	private TableView<SaleData> popularCombosTable;

	@FXML
	TableColumn<SaleData, Long> menuItemIDCol;
	@FXML
	private TableColumn<SaleData, Long> totalQuantity;
	@FXML
	private TableColumn<SaleData, String> menuItemNameCol;
	@FXML
	private TableColumn<SaleData, Long> totalQuantityCol;
	@FXML
	private TableColumn<SaleData, String> menuItem1Col;
	@FXML
	private TableColumn<SaleData, String> menuItem2Col;

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
	@FXML
	private TableColumn<Recipe, Long> recipeID;
	@FXML
	private TableColumn<Recipe, String> recipeItemName;
	@FXML
	private TableColumn<Recipe, Long> recipeInventoryID;
	@FXML
	private TableColumn<Recipe, Long> recipeMenuID;
	@FXML
	private TableColumn<Recipe, Double> recipeAmountUsed;

	// @FXML
	// private Button switchView;
	//
	// private Scene employeeScene;

	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Manager controller running");
		this.db = new Database();

		this.setUpInventoryTable();
		this.setUpMenuTable();
		this.setUpRecipeTable();
		this.updateInventoryTable(0);
		this.updateMenuTable(0);
		this.updateRecipeTable(0);
		this.inventoryTable.refresh();
		this.menuTable.refresh();
		this.recipeTable.refresh();

		addMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String newCost = fMenuPrice.getText();
				String itemName = fMenuName.getText();
				itemName = itemName.strip();
				newCost = newCost.strip();
				if (itemName == "") {
					System.out.println("Missing menu item name");
				} else if (newCost == "") {
					System.out.println("Missing menu item cost");
				} else {
					db.addMenuItem(itemName, Double.parseDouble(newCost));
					updateMenuTable(0);
					menuTable.refresh();
				}
			}
		});

		/*
		 * addRecipeItem.setOnAction(new EventHandler<ActionEvent>() {
		 * public void handle(ActionEvent event) {
		 * db.addRecipeItem(itemName.getText().strip(),
		 * Integer.parseInt(inventoryId.getText().strip()),
		 * Integer.parseInt(menuId.getText().strip()),
		 * Integer.parseInt(amountUsed.getText().strip()));
		 * }
		 * });
		 */

		updateMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String newCost = fMenuPrice.getText();
				String itemName = fMenuID.getText();
				itemName = itemName.strip();
				newCost = newCost.strip();
				if (itemName == "") {
					System.out.println("Missing menu item ID");
				} else if (newCost == "") {
					System.out.println("Missing menu item cost");
				} else {
					db.changePrice(itemName, Double.parseDouble(newCost));
					updateMenuTable(0);
					menuTable.refresh();
				}
			}
		});

		bAddInventory.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String name = fItemName.getText().strip();
				String cost = fItemCost.getText().strip();
				String quantity = fItemQuantity.getText().strip();
				if (fItemName.getText().strip() == "") {
					System.out.println("Missing Item Name");
				} else if (fItemCost.getText().strip() == "") {
					System.out.println("Missing Item Cost");
				} else if (fItemQuantity.getText().strip() == "") {
					System.out.println("Missing Item Quantity");
				} else {
					db.addInventoryItem(name, Double.parseDouble(cost), Double.parseDouble(quantity));
					updateInventoryTable(0);
					inventoryTable.refresh();
				}
			}
		});

		bUpdateInventory.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String ID = fInventoryID.getText().strip();
				String name = fItemName.getText().strip();
				String cost = fItemCost.getText().strip();
				String quantity = fItemQuantity.getText().strip();
				if (ID == "") {
					System.out.println("Missing Item ID");
				} else {
					db.updateInventoryItem(ID, name, cost, quantity);
					updateInventoryTable(0);
					inventoryTable.refresh();
				}
			}
		});

		bAddRecipe.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String name = fRecipeName.getText().strip();
				String invID = fRecipeInventoryID.getText().strip();
				String menuID = fRecipeMenuID.getText().strip();
				String quantity = fRecipeQuantity.getText().strip();
				if (name == "") {
					System.out.println("Missing Item Name");
				} else if (invID == "") {
					System.out.println("Missing Inventory ID");
				} else if (menuID == "") {
					System.out.println("Missing Menu ID");
				} else if (quantity == "") {
					System.out.println("Missing Quantity");
				} else {
					db.addRecipeItem(name, Integer.parseInt(invID), Integer.parseInt(menuID),
							Integer.parseInt(quantity));
					updateRecipeTable(0);
					recipeTable.refresh(); // TODO: this wasn't updating the gui for me (but yes the database)
					// plz check if it works on someone else's end
				}
			}
		});

		bUpdateRecipe.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String ID = fRecipeID.getText().strip();
				String name = fRecipeName.getText().strip();
				String invID = fRecipeInventoryID.getText().strip();
				String menuID = fRecipeMenuID.getText().strip();
				String quantity = fRecipeQuantity.getText().strip();
				if (ID == "") {
					System.out.println("Missing Item Name");
				} else {
					db.updateRecipeItem(ID, name, invID, menuID, quantity);
					updateRecipeTable(0);
					recipeTable.refresh();
				}
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

	private void updateRecipeTable(int whichTwenty) {
		// this.recipeTable.setItems(db.get20RowsRecipe(whichTwenty)); // TODO: code
		// this in db
		this.recipeTable.refresh();
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

	private void setUpRecipeTable() {
		this.recipeID.setCellValueFactory(cellData -> cellData.getValue().getRecipeID());
		this.recipeItemName.setCellValueFactory(cellData -> cellData.getValue().getRecipeName());
		this.recipeInventoryID.setCellValueFactory(cellData -> cellData.getValue().getInventoryID());
		this.recipeMenuID.setCellValueFactory(cellData -> cellData.getValue().getMenuID());
		this.recipeAmountUsed.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
		final ObservableList<Recipe> items = db.get20RowsRecipe(0);

		this.recipeTable.setItems(items);
	}

	/**
	 * Sets up the columns and data for the sales history table with given initial
	 * and final dates.
	 * This method should be called after the FXML view is loaded.
	 */
	private void setUpSalesHistoryTable() {
		this.menuItemIDCol.setCellValueFactory(cellData -> cellData.getValue().getMenuItemId());
		this.menuItemNameCol.setCellValueFactory(cellData -> cellData.getValue().getMenuItemName());
		this.totalQuantityCol.setCellValueFactory(cellData -> cellData.getValue().getTotalQuantity());

		final ObservableList<SaleData> salesData = db.salesHistory("01/01/2022", "01/01/2022");

		this.salesTable.setItems(salesData);
	}

	/**
	 * Updates the sales history table with new data based on given initial and
	 * final dates.
	 * 
	 * @param initialDate the initial date of the sales history
	 * @param finalDate   the final date of the sales history
	 */
	private void updateSalesHistoryTable(String initialDate, int finalDate) {
		this.salesHistoryTable.setItems(db.getSalesHistory(initialDate, finalDate));
		this.salesHistoryTable.refresh();
	}

	/**
	 * Sets up the columns and data for the popular combos table.
	 * This method should be called after the FXML view is loaded.
	 */
	private void setUpPopularCombosTable() {
		this.menuItem1Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
		this.menuItem2Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));

		TableColumn<String[], Integer> countCol = new TableColumn<>("Count");
		countCol.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue()[2])).asObject());

		final ObservableList<String[]> popularCombosData = db.getPopularCombosData("01/01/2022", "01/01/2022");

		this.popularCombosTable.getColumns().add(countCol);
		this.popularCombosTable.setItems(popularCombosData);
	}

	/**
	 * Updates the sales history table to display sales data for a specific date
	 * range.
	 *
	 * @param initialDate The initial date of the range to display sales data for.
	 * @param finalDate   The final date of the range to display sales data for.
	 */
	private void updatePopularCombosTable(String initialDate, String finalDate) {
		this.popularCombosTable.setItems(db.popularCombos(initialDate, finalDate));
		this.popularCombosTable.refresh();
	}

	// public void setEmployeeScene(Scene scene) {
	// employeeScene = scene;
	// }
}
