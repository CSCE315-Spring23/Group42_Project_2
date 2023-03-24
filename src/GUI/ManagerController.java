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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Creates the backend for the manager interface
 */
public class ManagerController implements Initializable {
	/**
	 * button to switch to employee view
	 */
	@FXML
	private Button switchView;
	/**
	 * button to log out
	 */
	@FXML
	private Button logout;
	/**
	 * button for adding to menu
	 */
	@FXML
	private Button addMenu;
	/**
	 * button for updating menu
	 */
	@FXML
	private Button updateMenu;
	/**
	 * button for deleting menu item
	 */
	@FXML
	private Button deleteMenuItem;
	/**
	 * button for adding to inventory
	 */
	@FXML
	private Button bAddInventory;
	/**
	 * button for updating inventory
	 */
	@FXML
	private Button bUpdateInventory;
	/**
	 * button for deleting inventory item
	 */
	@FXML
	private Button deleteInvItem;
	/**
	 * button for adding to recipe
	 */
	@FXML
	private Button bAddRecipe;
	/**
	 * button for updating recipe
	 */
	@FXML
	private Button bUpdateRecipe;
	/**
	 * button for deleting recipe item
	 */
	@FXML
	private Button deleteRecipeItem;
	/**
	 * button for populating order
	 */
	@FXML
	private Button populateOrder;
	/**
	 * button to create X report
	 */
	@FXML
	private Button createXreportBtn;
	/**
	 * button to create Z report
	 */
	@FXML
	private Button createZreportBtn;
	/**
	 * button to view combo table
	 */
	@FXML
	private Button comboButton;

	/**
	 * button to view sales table
	 */
	@FXML
	private Button salesButton;
	/**
	 * button to load the report
	 */
	@FXML
	private Button loadReportBtn;

	/**
	 * text field for inventory ID
	 */
	@FXML
	TextField fInventoryID;
	/**
	 * text field for item name
	 */
	@FXML
	TextField fItemName;
	/**
	 * text field for item cost
	 */
	@FXML
	TextField fItemCost;
	/**
	 * 
	 * text field for item quantity
	 */
	@FXML
	TextField fItemQuantity;

	/**
	 * text field for menu ID
	 */
	@FXML
	TextField fMenuID;
	/**
	 * text field for menu name
	 */
	@FXML
	TextField fMenuName;
	/**
	 * text field for menu price
	 */
	@FXML
	TextField fMenuPrice;

	/**
	 * text field for recipe name
	 */
	@FXML
	TextField fRecipeName;
	/**
	 * text field for recipe ID
	 */
	@FXML
	TextField fRecipeID;
	/**
	 * text field for recipe inventory ID
	 */
	@FXML
	TextField fRecipeInventoryID;
	/**
	 * text field for recipe menu ID
	 */
	@FXML
	TextField fRecipeMenuID;
	/**
	 * text field for recipe quantity
	 */
	@FXML
	TextField fRecipeQuantity;

	/**
	 * textfield for start date
	 */
	@FXML
	TextField startDate;

	/**
	 * text field for start date of combo report
	 */
	@FXML
	TextField startDateCombo;

	/**
	 * text field for end date of combo report
	 */
	@FXML
	TextField endDateCombo;

	/**
	 * text field for start date of sales report
	 */
	@FXML
	TextField startDateSale;
	/**
	 * text field for end date of sales report
	 */
	@FXML
	TextField endDateSale;

	/**
	 * text field for report
	 */
	@FXML
	TextField whichReport;

	/**
	 * database
	 */
	private Database db;

	// @FXML
	// private TableView<SaleData> salesTable;

	/**
	 * excess table
	 */
	@FXML
	private TableView<Excess> excessTable;
	/**
	 * inventory table
	 */
	@FXML
	private TableView<Inventory> inventoryTable;
	/**
	 * menu table
	 */
	@FXML
	private TableView<Menu> menuTable;
	/**
	 * recipe table
	 */
	@FXML
	private TableView<Recipe> recipeTable;
	/**
	 * sales history table
	 */
	@FXML
	private TableView<SaleData> salesHistoryTable;
	/**
	 * popular combinations table
	 */
	@FXML
	private TableView<Combo> popularCombosTable;
	/**
	 * report table
	 */
	@FXML
	private TableView<Report> reportTable;
	/**
	 * report content table
	 */
	@FXML
	private TableView<ReportContent> reportContentTable;
	/**
	 * restock report table
	 */
	@FXML
	private TableView<Inventory> restockReport;

	/**
	 * count column for combo report
	 */
	@FXML
	private TableColumn<Combo, Long> countCol;
	/**
	 * menu item ID column for sales report
	 */
	@FXML
	TableColumn<SaleData, Long> menuItemIDCol;
	/**
	 * total quantity for sales report
	 */
	@FXML
	private TableColumn<SaleData, Long> totalQuantity;
	/**
	 * menu item name column for sales report
	 */
	@FXML
	private TableColumn<SaleData, String> menuItemNameCol;
	/**
	 * total quantity column for sales report
	 */
	@FXML
	private TableColumn<SaleData, Long> totalQuantityCol;
	/**
	 * menu item 1 column for combo report
	 */
	@FXML
	private TableColumn<Combo, String> menuItem1Col;
	/**
	 * menu item 2 column for combo report
	 */
	@FXML
	private TableColumn<Combo, String> menuItem2Col;

	/**
	 * inventory ID
	 */
	@FXML
	private TableColumn<Inventory, Long> inventoryID;
	/**
	 * inventory item name
	 */
	@FXML
	private TableColumn<Inventory, String> inventoryItemName;
	/**
	 * inventory item cost
	 */
	@FXML
	private TableColumn<Inventory, Double> inventoryItemCost;
	/**
	 * inventory item quantity
	 */
	@FXML
	private TableColumn<Inventory, Long> inventoryItemQty;
	/**
	 * menu ID
	 */
	@FXML
	private TableColumn<Menu, Long> menuID;
	/**
	 * menu item name
	 */
	@FXML
	private TableColumn<Menu, String> menuItemName;
	/**
	 * menu item cost
	 */
	@FXML
	private TableColumn<Menu, Double> menuItemCost;
	/**
	 * recipe ID
	 */
	@FXML
	private TableColumn<Recipe, Long> recipeID;
	/**
	 * recipe item name
	 */
	@FXML
	private TableColumn<Recipe, String> recipeItemName;
	/**
	 * recipe inventory ID
	 */
	@FXML
	private TableColumn<Recipe, Long> recipeInventoryID;
	/**
	 * recipe menu ID
	 */
	@FXML
	private TableColumn<Recipe, Long> recipeMenuID;
	/**
	 * recipe amount used
	 */
	@FXML
	private TableColumn<Recipe, Double> recipeAmountUsed;

	/**
	 * report ID
	 */
	@FXML
	private TableColumn<Report, Integer> reportID;
	/**
	 * last order ID
	 */
	@FXML
	private TableColumn<Report, Integer> lastOrderID;
	/**
	 * Z report date
	 */
	@FXML
	private TableColumn<Report, String> zReportDate;
	/**
	 * report total cost
	 */
	@FXML
	private TableColumn<Report, Float> reportTotalCost;

	/**
	 * menu item name
	 */
	@FXML
	private TableColumn<ReportContent, String> menuItemNameForReports;
	/**
	 * menu item quantity
	 */
	@FXML
	private TableColumn<ReportContent, Integer> menuItemQuantityForReports;

	/**
	 * inventory ID 2
	 */
	@FXML
	private TableColumn<Inventory, Long> inventoryID2;
	/**
	 * inventory item name 2
	 */
	@FXML
	private TableColumn<Inventory, String> inventoryItemName2;
	/**
	 * inventory item cost 2
	 */
	@FXML
	private TableColumn<Inventory, Double> inventoryItemCost2;
	/**
	 * inventory item quantity 2
	 */
	@FXML
	private TableColumn<Inventory, Long> inventoryItemQty2;

	/**
	 * excess ID
	 */
	@FXML
	private TableColumn<Excess, Long> excessID;
	/**
	 * excess name
	 */
	@FXML
	private TableColumn<Excess, String> excessName;

	/**
	 * Initialize all of the set up functions and buttons
	 * @param location location of the URL
	 * @param resources resource bundle 
	 */
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Manager controller running");
		this.db = new Database();

		this.setUpReportContentTable();
		this.setUpReportTable();
		this.setUpInventoryTable();
		this.setUpMenuTable();
		this.setUpRecipeTable();
		this.setUpReportTable();
		this.setUpRestockReport();

		this.setUpSalesHistoryTable();
		this.setUpPopularCombosTable();

		this.setUpExcessTable();

		this.updateReportTable(0);
		this.updateInventoryTable(0);
		this.updateMenuTable(0);
		this.updateRecipeTable(0);
		this.updateRestockReport();
		this.updateSalesHistoryTable("2022-1-1", "2022-1-1");
		this.updatePopularCombosTable("2022-1-1", "2022-1-1");

		this.updateExcessTable(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

		this.reportTable.refresh();
		this.inventoryTable.refresh();
		this.menuTable.refresh();
		this.recipeTable.refresh();
		this.restockReport.refresh();
		this.salesHistoryTable.refresh();
		this.popularCombosTable.refresh();
		System.out.println("is this the problem?");

		this.excessTable.refresh();

		addMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String newCost = fMenuPrice.getText();
				String itemName = fMenuName.getText();
				String none = "";
				itemName = itemName.strip();
				newCost = newCost.strip();
				if (itemName.equals(none)) {
					System.out.println("Missing menu item name");
				} else if (newCost.equals(none)) {
					System.out.println("Missing menu item cost");
				} else {
					db.addMenuItem(itemName, Double.parseDouble(newCost));
					updateMenuTable(0);
					menuTable.refresh();
				}
			}
		});
		createXreportBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				db.createXReport();
				updateReportTable(0);
				reportTable.refresh();
			}
		});

		createZreportBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				db.createZReport();
				updateReportTable(0);
				reportTable.refresh();
			}
		});

		loadReportBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String reportidS = whichReport.getText().strip();
				if (reportidS.equals("")) {
					System.out.println("Missing report_id");
				}
				else{
				int reportid = Integer.parseInt(reportidS);
				updateReportContentTable(reportid);
				reportTable.refresh();
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
				String none = "";
				itemName = itemName.strip();
				newCost = newCost.strip();
				if (itemName.equals(none)) {
					System.out.println("Missing menu item ID");
				} else if (newCost.equals(none)) {
					System.out.println("Missing menu item cost");
				} else {
					db.changePrice(itemName, Double.parseDouble(newCost));
					updateMenuTable(0);
					menuTable.refresh();
				}
			}
		});

		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String itemID = fMenuID.getText().strip();
				String tableName = "menu";
				String columnName = "menu_item_id";
				String none = "";
				if (itemID.equals(none)) {
					System.out.println("Missing menu item ID");
				} else {
					db.deleteRow(tableName, columnName, Long.parseLong(itemID));
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
				String none = "";
				if (fItemName.getText().strip().equals(none)) {
					System.out.println("Missing Item Name");
				} else if (fItemCost.getText().strip().equals(none)) {
					System.out.println("Missing Item Cost");
				} else if (fItemQuantity.getText().strip().equals(none)) {
					System.out.println("Missing Item Quantity");
				} else {
					db.addInventoryItem(name, Double.parseDouble(cost), Double.parseDouble(quantity));
					updateInventoryTable(0);
					inventoryTable.refresh();
				}
			}
		});

		populateOrder.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String date = startDate.getText().strip();
				if (startDate.getText().strip().equals("")) {
					System.out.println("Missing Item Name");
				} else {
					updateExcessTable(date);
					excessTable.refresh();
				}
			}
		});

		comboButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String startdate = startDateCombo.getText().strip();
				String enddate2 = endDateCombo.getText().strip();
				if (startDateCombo.getText().strip().equals("") || endDateCombo.getText().strip().equals("")) {
					System.out.println("Missing Item Name");
				} else {
					updatePopularCombosTable(startdate, enddate2);
					popularCombosTable.refresh();
				}
			}
		});

		salesButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String startdate = startDateSale.getText().strip();
				String enddate2 = endDateSale.getText().strip();
				if (startDateSale.getText().strip().equals("") || endDateSale.getText().strip().equals("")) {
					System.out.println("Missing Item Name");
				} else {
					updateSalesHistoryTable(startdate, enddate2);
					salesHistoryTable.refresh();
				}
			}
		});

		bUpdateInventory.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String ID = fInventoryID.getText().strip();
				String name = fItemName.getText().strip();
				String cost = fItemCost.getText().strip();
				String quantity = fItemQuantity.getText().strip();
				String none = "";
				if (ID.equals(none)) {
					System.out.println("Missing Item ID");
				} else {
					db.updateInventoryItem(ID, name, cost, quantity);
					updateInventoryTable(0);
					inventoryTable.refresh();
				}
			}
		});

		deleteInvItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String itemID = fInventoryID.getText().strip();
				String tableName = "inventory_item";
				String columnName = "inventory_id";
				String none = "";
				if (itemID.equals(none)) {
					System.out.println("Missing item ID");
				} else {
					db.deleteRow(tableName, columnName, Long.parseLong(itemID));
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
				String none = "";
				if (name.equals(none)) {
					System.out.println("Missing Item Name");
				} else if (invID.equals(none)) {
					System.out.println("Missing Inventory ID");
				} else if (menuID.equals(none)) {
					System.out.println("Missing Menu ID");
				} else if (quantity.equals(none)) {
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
				String none = "";
				if (ID.equals(none)) {
					System.out.println("Missing Item Name");
				} else {
					db.updateRecipeItem(ID, name, invID, menuID, quantity);
					updateRecipeTable(0);
					recipeTable.refresh();
				}
			}
		});

		deleteRecipeItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String itemID = fRecipeID.getText().strip();
				String tableName = "recipe_item";
				String columnName = "recipe_id";
				String none = "";
				if (itemID.equals(none)) {
					System.out.println("Missing recipe ID");
				} else {
					db.deleteRow(tableName, columnName, Long.parseLong(itemID));
					updateRecipeTable(0);
					recipeTable.refresh();
				}
			}
		});

		switchView.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					FXMLLoader employeeLoader = new FXMLLoader(getClass().getResource("employee.fxml"));
					Parent employeeParent = (Parent) employeeLoader.load();
					Scene employeeScene = new Scene(employeeParent, 750, 750);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setTitle("315 Project 2");
					stage.setScene(employeeScene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		logout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
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
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

					loginController.setEmployeeScene(employeeScene);
					// managerController.setEmployeeScene(employeeScene)
					loginController.setManagerScene(managerScene);

					stage.setTitle("315 Project 2");
					stage.setScene(loginScene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

	}

	/**
	 * Update the menu table
	 * @param whichTwenty location of the 20 rows to update
	 */
	private void updateMenuTable(int whichTwenty) {
		this.menuTable.setItems(db.get20RowsMenu(whichTwenty));
		this.menuTable.refresh();
	}

	/**
	 * Update the inventory table
	 * @param whichTwenty location of the 20 rows to update
	 */
	private void updateInventoryTable(int whichTwenty) {
		this.inventoryTable.setItems(db.get20RowsInventory(whichTwenty));
		this.inventoryTable.refresh();
	}

	/**
	 * Update the report table
	 * @param whichTwenty location of the 20 rows to update
	 */
	private void updateReportTable(int whichTwenty) {
		this.reportTable.setItems(db.get20RowsReport(whichTwenty));
		this.reportTable.refresh();
	}

	/**
	 * Update the report content table
	 * @param whichTwenty location of the 20 rows to update
	 */
	private void updateReportContentTable(int whichTwenty) {
		this.reportContentTable.setItems(db.get20RowsReportContent(whichTwenty));
		this.reportContentTable.refresh();
	}

	/**
	 * Update the recipe table
	 * @param whichTwenty location of the 20 rows to update
	 */
	private void updateRecipeTable(int whichTwenty) {
		this.recipeTable.setItems(db.get20RowsRecipe(whichTwenty));
		this.recipeTable.refresh();
	}

	/**
	 * Update the restock report
	 */
	private void updateRestockReport() {
		this.restockReport.setItems(db.createRestockReport());
		this.restockReport.refresh();
	}

	/**
	 * Set up the report table
	 */
	private void setUpReportTable() {
		this.reportID.setCellValueFactory(cellData -> cellData.getValue().getReportID());
		this.lastOrderID.setCellValueFactory(cellData -> cellData.getValue().getLastOrderID());
		this.zReportDate.setCellValueFactory(cellData -> cellData.getValue().getZReportDate());
		this.reportTotalCost.setCellValueFactory(cellData -> cellData.getValue().getReportTotalCost());
		final ObservableList<Report> items = db.get20RowsReport(0);

		this.reportTable.setItems(items);
	}

	/**
	 * Set up the report content table
	 */
	private void setUpReportContentTable() {
		this.menuItemNameForReports.setCellValueFactory(cellData -> cellData.getValue().getMenuItemNameForReports());
		this.menuItemQuantityForReports
				.setCellValueFactory(cellData -> cellData.getValue().getMenuItemQuantityForReports());

		final ObservableList<ReportContent> items = db.get20RowsReportContent(0);

		this.reportContentTable.setItems(items);
	}

	/**
	 * Set up the excess table
	 */
	private void setUpExcessTable() {
		this.excessTable.setItems(
				db.getExcess(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
	}

	/**
	 * Set up the inventory table
	 */
	private void setUpInventoryTable() {
		this.inventoryID.setCellValueFactory(cellData -> cellData.getValue().getInventoryID());
		this.inventoryItemName.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		this.inventoryItemCost.setCellValueFactory(cellData -> cellData.getValue().getItemCost());
		this.inventoryItemQty.setCellValueFactory(cellData -> cellData.getValue().getItemQuantity());
		final ObservableList<Inventory> items = db.get20RowsInventory(0);

		this.inventoryTable.setItems(items);
	}

	/**
	 * Set up the menu table
	 */
	private void setUpMenuTable() {
		this.menuID.setCellValueFactory(cellData -> cellData.getValue().getMenuID());
		this.menuItemName.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		this.menuItemCost.setCellValueFactory(cellData -> cellData.getValue().getItemCost());
		final ObservableList<Inventory> items = db.get20RowsInventory(0);

		this.inventoryTable.setItems(items);
	}

	/**
	 * Set up the recipe table
	 */
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
	 * Set up the restock report
	 */
	private void setUpRestockReport() {
		this.inventoryID2.setCellValueFactory(cellData -> cellData.getValue().getInventoryID());
		this.inventoryItemName2.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		this.inventoryItemCost2.setCellValueFactory(cellData -> cellData.getValue().getItemCost());
		this.inventoryItemQty2.setCellValueFactory(cellData -> cellData.getValue().getItemQuantity());
		final ObservableList<Inventory> items = db.createRestockReport();

		this.restockReport.setItems(items);
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

		final ObservableList<SaleData> salesData = db.salesHistory("2022-01-01",
				LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

		this.salesHistoryTable.setItems(salesData);
		// THIS IS THE ENTIRE PROBLEM
		// this.salesHistoryTable.setItems(salesData);
	}

	/**
	 * Updates the sales history table with new data based on given initial and
	 * final dates.
	 * 
	 * @param initialDate the initial date of the sales history
	 * @param finalDate   the final date of the sales history
	 */
	private void updateSalesHistoryTable(String initialDate, String finalDate) {
		this.salesHistoryTable.setItems(db.salesHistory(initialDate, finalDate));
		this.salesHistoryTable.refresh();

	}

	/**
	 * Sets up the columns and data for the popular combos table.
	 * This method should be called after the FXML view is loaded.
	 */
	private void setUpPopularCombosTable() {
		this.menuItem1Col.setCellValueFactory(cellData -> cellData.getValue().getItem1());
		this.menuItem2Col.setCellValueFactory(cellData -> cellData.getValue().getItem2());

		// TableColumn<String[], Integer> countCol = new TableColumn<>("Count");
		this.countCol.setCellValueFactory(cellData -> cellData.getValue().getNumTimesOrdered());

		final ObservableList<Combo> popularCombosData = db.popularCombos("2022-01-01", "2022-01-01");

		this.popularCombosTable.getColumns().add(countCol);
		this.popularCombosTable.setItems(popularCombosData);
	}

	/**
	 * Updates the popular combos table to display popular combinations of items for
	 * a specific date
	 * range.
	 *
	 * @param initialDate The initial date of the range to display sales data for.
	 * @param finalDate   The final date of the range to display sales data for.
	 */
	private void updatePopularCombosTable(String initialDate, String finalDate) {
		this.menuItem1Col.setCellValueFactory(cellData -> cellData.getValue().getItem1());
		this.menuItem2Col.setCellValueFactory(cellData -> cellData.getValue().getItem2());
		this.popularCombosTable.setItems(db.popularCombos(initialDate, finalDate));
		this.popularCombosTable.refresh();
	}

	/**
	 * Update the excess table
	 * @param date date at which to start excess collection
	 */
	private void updateExcessTable(String date) {

		this.excessID.setCellValueFactory(cellData -> cellData.getValue().getMenuItemId());
		this.excessName.setCellValueFactory(cellData -> cellData.getValue().getMenuItemName());
		this.excessTable
				.setItems(db.getExcess(date, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
		this.excessTable.refresh();
	}

	// public void setEmployeeScene(Scene scene) {
	// employeeScene = scene;
	// }
}
