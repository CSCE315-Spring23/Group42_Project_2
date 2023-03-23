import java.sql.*;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Contains all functionality to access the SQL Database
 */
public class Database {

    /**
     * The Connection object representing the connection to the PostgreSQL database.
     */
    private Connection conn = null;

     /**
     * The team number used to construct the name of the database.
     */
    private static String teamNumber = "team_42";
    
    /**
     * The name of the database.
     */
    private static String dbName = "csce315331_" + teamNumber;
    
    /**
     * The connection string used to connect to the database.
     */
    private static String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    
    /**
     * The total amount for a Z report.
     */
    private float zReportTotal = 0;

    /**
     * Database constructor
     */
    Database() {

        // open the database
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Runs a SQL command on the database and returns the results as a ResultSet.
     * 
     * @param sqlStatement the SQL command to run
     * @return a ResultSet object containing the results of the query
     * @throws SQLException if there is an error executing the SQL command
     * @author Arjun
     */
    private ResultSet runCommand(String sqlStatement) throws SQLException {
        try {
            System.out.println(sqlStatement);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);
            return result;
        } catch (Exception e) {
            System.out.println("Problems running the command");
            System.out.println(e);
        }
        return null;
    }

    /**
     * 
     * Call when done to close connection
     * Closes the database connection and prints a message indicating whether the
     * connection was closed.
     * 
     * @author Arjun
     */
    public void closeDB() {
        // closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch (Exception e) {
            System.out.println("Connection NOT Closed.");
        } // end try catch
    }

    /**
     * Retrieves whether a user with a specified email address is a manager.
     * 
     * @param email the email address of the user to check
     * @return a boolean value indicating whether the user is a manager
     * @author Daniela Santos
     */
    public boolean isManager(String email) {
        boolean manager = false;
        try {
            // run query
            ResultSet result = runCommand("SELECT IS_MANAGER FROM Employee WHERE EMAIL = '" + email + "';");
            if (result.next()) {
                manager = result.getBoolean("IS_MANAGER");
            }
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return manager;
    }

    /**
     * Returns the number of rows in the specified table.
     * 
     * @param tableName the name of the table to retrieve the number of rows for
     * @return an integer value representing the number of rows in the table
     * @throws Exception if there is an error executing the query
     * @author Arjun
     */
    public int getNumRows(String tableName) {
        int rows = 0;
        try {
            // Run query to get the total amount of items in table
            ResultSet result = runCommand("SELECT COUNT(*) FROM " + tableName + ";");
            if (result.next()) {
                rows = result.getInt(1);
            }
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rows;
    }

    /**
     * This method is part of a class that interacts with a database and is used to
     * retrieve a list of inventory items (and quantities)
     * used on a specific day. The inventory items are obtained from the RecipeItem
     * and ItemSold tables, which store the recipes and sales
     * data for menu items, respectively. The method returns an ArrayList of
     * integers, where each index represents an inventory item ID and
     * the value at that index represents the total quantity of the inventory item
     * used on the specified day.
     * 
     * @param date a string representing the date for which to retrieve the
     *             inventory items used
     * @return an ArrayList of integers representing the inventory items used on the
     *         specified day
     * @author Arjun
     */
    public ArrayList<Integer> inventoryItemsUsed(String date) {
        int inventoryItemsCount = getNumRows("inventoryItem");
        ArrayList<Integer> inventoryItems = new ArrayList<>(inventoryItemsCount);
        // Initialize all inventory items to be set all to zero
        for (int i = 0; i < inventoryItemsCount; i++) {
            inventoryItems.add(0);
        }
        try {
            // Run query to get order IDs for a specific date
            ResultSet orderResult = runCommand("SELECT ORDER_ID FROM Orders WHERE DATE_ORDERED = '" + date + "';");
            while (orderResult.next()) {
                int orderID = orderResult.getInt("ORDER_ID");
                // Run query to get menu item IDs and sold quantities for each order
                ResultSet menuItemResult = runCommand(
                        "SELECT MENU_ITEM_ID, ITEM_SOLD_QUANTITY FROM ItemSold WHERE ORDER_ID = " + orderID + ";");
                while (menuItemResult.next()) {
                    int menuItemID = menuItemResult.getInt("MENU_ITEM_ID");
                    int menuItemQuantity = menuItemResult.getInt("ITEM_SOLD_QUANTITY");
                    // Run query to get inventory IDs and amounts used for each menu item
                    ResultSet recipeResult = runCommand(
                            "SELECT INVENTORY_ID, AMT_USED FROM RecipeItem WHERE MENU_ID = " + menuItemID + ";");
                    while (recipeResult.next()) {
                        int inventoryID = recipeResult.getInt("INVENTORY_ID");
                        int amtUsed = recipeResult.getInt("AMT_USED");
                        // Add inventory ID and amount used to list in the index of the inventoryID
                        inventoryItems.set(inventoryID, menuItemQuantity * amtUsed);
                    }
                    recipeResult.close();
                }
                menuItemResult.close();

                // Now we also check for each individual inventory item sold (which would be all
                // the costumizations)
                ResultSet InventoryItemSoldResult = runCommand(
                        "SELECT INVENTORY_ID, ITEM_SOLD_QUANTITY FROM ItemSold WHERE ORDER_ID = " + orderID + ";");
                while (InventoryItemSoldResult.next()) {
                    int inventoryID = InventoryItemSoldResult.getInt("INVENTORY_ID");
                    int quantity = InventoryItemSoldResult.getInt("ITEM_SOLD_QUANTITY");
                    inventoryItems.set(inventoryID, quantity);
                }
                InventoryItemSoldResult.close();

            }
            orderResult.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return inventoryItems;
    }

    /**
     * Get blocks of 20 Rows in any table based on specified table name and
     * converts to array list
     * 
     * @param whichTwenty integer representing the desired block of twenty items in
     *                    the inventory table
     * @return an ObservableList of Inventory objects representing the retrieved
     *         rows of inventory items.
     * @author Bryan
     */
    public ObservableList<Inventory> get20RowsInventory(int whichTwenty) {
        String tableName = "inventory_item";
        ObservableList<Inventory> rows = FXCollections.observableArrayList();

        try {
            // run query
            ResultSet result = runCommand("Select * FROM "
                    + tableName);

            // Get metadata which gets info about the types/properties of the columns in a
            // ResultSet
            // ResultSetMetaData metaData = result.getMetaData();
            // int numberOfColumns = metaData.getColumnCount();

            // Loop through the 20 rows in result
            while (result.next()) {
                // Loop through columns an
                rows.add(new Inventory(result.getLong(1), result.getString(2), result.getDouble(3), result.getLong(4)));
                // Add current row to rows
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rows;
    }

    /**
     * Retrieves 20 rows of menu items from the "menu" table starting from a
     * specified position.
     * 
     * @param whichTwenty An integer specifying which set of 20 rows to retrieve.
     *                    The first set of 20 rows would be 1, the second set would
     *                    be 2, and so on.
     * @return An ObservableList of Menu objects representing the retrieved rows of
     *         menu items.
     * @author Daniela Santos
     */
    public ObservableList<Menu> get20RowsMenu(int whichTwenty) {
        String tableName = "menu";
        ObservableList<Menu> rows = FXCollections.observableArrayList();
        try {
            // run query
            ResultSet result = runCommand("Select * FROM "
                    + tableName);

            // Get metadata which gets info about the types/properties of the columns in a
            // ResultSet
            // ResultSetMetaData metaData = result.getMetaData();
            // int numberOfColumns = metaData.getColumnCount();

            // Loop through the 20 rows in result
            while (result.next()) {
                // Loop through columns an
                rows.add(new Menu(result.getLong(1), result.getString(2), result.getDouble(3)));
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rows;
    }

    /**
     * Retrieves a list of items that have used less than 10% of their inventory in
     * the given timeframe
     * 
     * @param initialDate
     *                    the start date for this measure.
     * @param finalDate
     *                    the end date for this measure. use current date for the
     *                    most up to date measurement.
     * @return list of items
     * @author Arjun
     */
    public ObservableList<Excess> getExcess(String initialDate, String finalDate) {
        ObservableList<Excess> excess = FXCollections.observableArrayList();
        if (initialDate.equals(finalDate))
            return excess;

        try {
            // Get the sales data for the given time window
            ResultSet result = runCommand(
                    "SELECT SUM(AMT_USED) AS total_amt_used, i.INVENTORY_ID, i.INVENTORY_ITEM_NAME " +
                            "FROM Recipe_Item r " +
                            "JOIN Inventory_Item i ON r.INVENTORY_ID = i.INVENTORY_ID " +
                            "JOIN Item_Sold s ON r.MENU_ID = s.MENU_ITEM_ID " +
                            "JOIN Orders o ON s.ORDER_ID = o.ORDER_ID " +
                            "WHERE o.DATE_ORDERED BETWEEN '" + initialDate.strip() + "' AND '" + finalDate.strip() +
                            "' GROUP BY i.INVENTORY_ID, i.INVENTORY_ITEM_NAME"

            );
            if (result == null)
                return excess;
            // Parse the sales data into a list of SaleData objects
            while (result.next()) {
                String inventoryItemName = result.getString("INVENTORY_ITEM_NAME");
                Long inventoryID = result.getLong("INVENTORY_ID");
                Long quantityUsed = result.getLong("TOTAL_AMT_USED");

                ResultSet res2 = runCommand(
                        "SELECT INVENTORY_ITEM_QUANTITY FROM INVENTORY_ITEM WHERE INVENTORY_ID = " + inventoryID + ";");
                res2.next();

                Long inventoryOnHand = res2.getLong("INVENTORY_ITEM_QUANTITY");
                // System.out.println("DEBUG LOG: " + inventoryItemName + " : " + inventoryID +
                // " : " + quantityUsed
                // + " : " + inventoryOnHand);

                if (inventoryOnHand + quantityUsed > quantityUsed * 10) {
                    Excess ex = new Excess(inventoryID, inventoryItemName);
                    excess.add(ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return excess;
    }

    /**
     * Retrieves 20 rows of recipe items from the "recipe_item" table starting from
     * a specified position.
     * 
     * @param whichTwenty An integer specifying which set of 20 rows to retrieve.
     *                    The first set of 20 rows would be 1, the second set would
     *                    be 2, and so on.
     * @return An ObservableList of Recipe objects representing the retrieved rows
     *         of recipe items.
     * @author Ariela
     */
    public ObservableList<Recipe> get20RowsRecipe(int whichTwenty) {
        String tableName = "recipe_item";
        ObservableList<Recipe> rows = FXCollections.observableArrayList();
        try {
            // run query
            ResultSet result = runCommand("SELECT * FROM "
                    + tableName);

            // Get metadata which gets info about the types/properties of the columns in a
            // ResultSet
            // ResultSetMetaData metaData = result.getMetaData();
            // int numberOfColumns = metaData.getColumnCount();

            // Loop through the 20 rows in result
            while (result.next()) {
                // Loop through columns an
                rows.add(new Recipe(result.getLong(1), result.getString(2), result.getLong(3), result.getLong(4),
                        result.getDouble(5)));
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rows;
    }

    /**
     * Retrieves 20 rows of recipe items from the report table starting from
     * a specified position.
     * 
     * @param whichTwenty An integer specifying which set of 20 rows to retrieve.
     *                    The first set of 20 rows would be 1, the second set would
     *                    be 2, and so on.
     * @return An ObservableList of report objects representing the retrieved rows
     *         of recipe items.
     * @author Srikar
     */

    public ObservableList<Report> get20RowsReport(int whichTwenty) {
        String tableName = "zreports";
        ObservableList<Report> rows = FXCollections.observableArrayList();
        try {
            // run query
            ResultSet result = runCommand("SELECT * FROM "
                    + tableName);

            // Get metadata which gets info about the types/properties of the columns in a
            // ResultSet
            // ResultSetMetaData metaData = result.getMetaData();
            // int numberOfColumns = metaData.getColumnCount();

            // Loop through the 20 rows in result
            while (result.next()) {
                // Loop through columns an
                rows.add(new Report(result.getInt(1), result.getInt(2), result.getString(3), result.getFloat(4)));
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rows;
    }

    /**
     * Gets the 20 rows of report content with a given report ID
     * 
     * @param whichTwenty the report ID for which to get the rows
     * @return an ObservableList of ReportContent objects representing the 20 rows of report content with the given report ID
     * @author Srikar
     */
    public ObservableList<ReportContent> get20RowsReportContent(int whichTwenty) {
        String tableName = "zreportcontent";
        ObservableList<ReportContent> rows = FXCollections.observableArrayList();
        try {
            // run query
            // ResultSet result = runCommand("SELECT * FROM zreportcontent");
            ResultSet result = runCommand(
                    String.format("SELECT * FROM zreportcontent WHERE report_id= '%d'", whichTwenty));

            // Get metadata which gets info about the types/properties of the columns in a
            // ResultSet
            // ResultSetMetaData metaData = result.getMetaData();
            // int numberOfColumns = metaData.getColumnCount();

            // Loop through the 20 rows in result
            while (result.next()) {
                // Loop through columns an
                rows.add(new ReportContent(result.getString(3), result.getInt(4)));
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rows;
    }

    /**
     * Returns an ArrayList of integers representing the quantities of specific
     * recipe items used in the specified item on the menu.
     * The ArrayList has a fixed length equal to the number of items in the
     * inventory, with each element representing the
     * quantity of the corresponding inventory item used in the recipe.
     * 
     * @param item the name of the menu item to query the recipe for
     * @return an ArrayList of integers representing the quantities of specific
     *         recipe items used in the specified item on the menu
     * @throws Exception if there is an error executing the query
     * @author Ariela
     */
    public ArrayList<Integer> getRecipe(String item) {
        int inventoryItemsCount = getNumRows("inventoryItem");
        ArrayList<Integer> recipe = new ArrayList<>(inventoryItemsCount);
        // Initialize all inventory items to be set all to zero
        for (int i = 0; i < inventoryItemsCount; i++) {
            recipe.add(0);
        }
        try {
            ResultSet menuIdResult = runCommand("SELECT MENU_ITEM_ID FROM Menu WHERE MENU_ITEM_NAME = '" + item + "';");
            int menuItemId = 0;
            if (menuIdResult.next()) {
                menuItemId = menuIdResult.getInt("MENU_ITEM_ID");
            }
            menuIdResult.close();
            ResultSet result = runCommand(
                    "SELECT INVENTORY_ID, AMT_USED FROM RecipeItem WHERE MENU_ID = " + menuItemId + ";");
            while (result.next()) {
                int inventoryID = result.getInt("INVENTORY_ID");
                int amount = result.getInt("AMT_USED");
                recipe.set(inventoryID - 1, amount);
            }
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return recipe;
    }

    /**
     * Creates a new row in the orders table with the specified cost and list of
     * menu items and inventory items.
     * 
     * @param cost           the cost of the order
     * @param menuItems      a list of CustomPair objects representing the menu
     *                       items and their corresponding quantities in the order
     * @param inventoryItems a list of CustomPair objects representing the inventory
     *                       items and their corresponding quantities in the order
     * @throws Exception if there is an error executing the query
     * @author Daniela Santos
     */
    public void createOrder(double cost, ArrayList<CustomPair> menuItems, ArrayList<CustomPair> inventoryItems) {
        try {
            // get new pk of order
            int orderID = 0;
            Statement stmt2 = conn.createStatement();
            String sqlStatement1 = "SELECT MAX(order_id) FROM orders";
            ResultSet result = stmt2.executeQuery(sqlStatement1);
            if (result.next()) {
                orderID = result.getInt(1) + 1;
            }

            // get the current date as a LocalDate object
            //
            LocalDate today = LocalDate.now();
            // format the date as a string in "MM-dd-yyyy" format
            String date = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    String.format("INSERT INTO orders (order_id, date_ordered, order_cost) VALUES (%d, '%s', %f)",
                            orderID, date, cost));
            // runCommand(String.format("INSERT INTO orders (order_id, date_ordered,
            // order_cost) VALUES (%d, '%s', %f)", orderID, date, cost));
            for (int i = 0; i < menuItems.size(); i++) {
                createMenuItemSold(menuItems.get(i).ID, orderID, menuItems.get(i).Quantity);
            }
            for (int i = 0; i < inventoryItems.size(); i++) {
                createInventoryItemSold(inventoryItems.get(i).ID, orderID, inventoryItems.get(i).Quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Updates the cost of the specified menu item.
     * 
     * @param itemName the name or ID of the menu item to update
     * @param newCost  the new cost of the menu item
     * @author Bryan
     */
    public void changePrice(String itemName, double newCost) {
        try {
            // if (Integer.parseInt(itemName) > 26) //TODO: make this dynamic
            // return;
            // run query
            runCommand("UPDATE Menu SET MENU_ITEM_COST = " + newCost
                    + " WHERE menu_item_id = '" + itemName + "';");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Updates an inventory item with the specified ID with the given parameters.
     *
     * @param itemID   the ID of the inventory item to update
     * @param itemName the new name of the inventory item (can be empty string to
     *                 skip update)
     * @param newCost  the new cost of the inventory item (can be empty string to
     *                 skip update)
     * @param quantity the new quantity of the inventory item (can be empty string
     *                 to skip update)
     * @author Daniela M
     */
    public void updateInventoryItem(String itemID, String itemName, String newCost, String quantity) {
        try {
            // if (Integer.parseInt(ID) > 26) //TODO: make this dynamic
            // return;
            // run query
            if (newCost != "") {
                runCommand("UPDATE inventory_item SET INVENTORY_ITEM_COST = " + Double.parseDouble(newCost)
                        + " WHERE inventory_id = '" + itemID + "';");
            }
            if (itemName != "") {
                runCommand("UPDATE inventory_item SET INVENTORY_ITEM_NAME = '" + itemName
                        + "' WHERE inventory_id = '" + itemID + "';");
            }
            if (quantity != "") {
                runCommand("UPDATE inventory_item SET INVENTORY_ITEM_QUANTITY = " + Double.parseDouble(quantity)
                        + " WHERE inventory_id = '" + itemID + "';");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Updates a recipe item with the specified ID with the given parameters.
     *
     * @param itemID   the ID of the recipe item to update
     * @param itemName the new name of the recipe item (can be empty string to skip
     *                 update)
     * @param invID    the new inventory ID for the recipe item (can be empty string
     *                 to skip update)
     * @param menuID   the new menu ID for the recipe item (can be empty string to
     *                 skip update)
     * @param quantity the new quantity of the recipe item (can be empty string to
     *                 skip update)
     * @author Arjun
     */
    public void updateRecipeItem(String itemID, String itemName, String invID, String menuID, String quantity) {
        try {
            // if (Integer.parseInt(ID) > 26) //TODO: make this dynamic
            // return;
            // run query

            if (itemName != "") {
                runCommand("UPDATE recipe_item SET RECIPE_ITEM_NAME = '" + itemName
                        + "' WHERE RECIPE_ID = '" + itemID + "';");
            }
            if (invID != "") {
                runCommand("UPDATE recipe_item SET INVENTORY_ID = " + Integer.parseInt(invID)
                        + " WHERE RECIPE_ID = '" + itemID + "';");
            }
            if (menuID != "") {
                runCommand("UPDATE recipe_item SET MENU_ID = " + Integer.parseInt(menuID)
                        + " WHERE RECIPE_ID = '" + itemID + "';");
            }
            if (quantity != "") {
                runCommand("UPDATE recipe_item SET AMT_USED = " + Double.parseDouble(quantity)
                        + " WHERE RECIPE_ID = '" + itemID + "';");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Create a new menu item sold and update inventory accordingly
     * 
     * @param MenuId   the ID of the menu item being sold
     * @param orderID  the ID of the order this menu item is being sold under
     * @param quantity the quantity of the menu item being sold
     * @author Srikar
     */
    private void createMenuItemSold(int MenuId, int orderID, int quantity) {
        // add a row to ItemsSold
        try {
            int newItemID = 0;
            Statement stmt = conn.createStatement();
            String sqlStatement1 = "SELECT MAX(item_id) FROM item_sold";
            ResultSet result = stmt.executeQuery(sqlStatement1);
            if (result.next()) {
                newItemID = result.getInt(1) + 1;
            }

            // insert into item sold
            String sqlStatement2 = String.format(
                    "INSERT INTO item_sold (item_id, menu_item_id, order_id, item_sold_quantity) VALUES ('%d', '%d', '%d', '%d')",
                    newItemID, MenuId, orderID, quantity);
            stmt.executeUpdate(sqlStatement2);

            // update menu
            String sqlStatement6 = String.format(
                    "UPDATE menu SET menu_item_sold_since_z = menu_item_sold_since_z + 1 WHERE menu_item_id= '%d'",
                    MenuId);
            stmt.executeUpdate(sqlStatement6);

            // update inventory
            String sqlStatement4 = String.format("SELECT * FROM recipe_item WHERE menu_id = %d", MenuId);
            ResultSet result2 = stmt.executeQuery(sqlStatement4);
            String sqlStatement3 = "";
            ArrayList<Integer> vector1 = new ArrayList<Integer>();
            ArrayList<Integer> vector2 = new ArrayList<Integer>();
            while (result2.next()) {
                vector1.add(result2.getInt("amt_used"));
                vector2.add(result2.getInt("inventory_id"));
                // sqlStatement3 = String.format(
                // "UPDATE inventory_item SET inventory_item_quantity = inventory_item_quantity
                // - %d WHERE inventory_id = %d"
                // , Integer.parseInt(result2.getString("amt_used")),
                // Integer.parseInt(result2.getString("inventory_id")));
                // stmt.executeUpdate(sqlStatement3);
            }
            for (int i = 0; i < vector1.size(); i++) {
                sqlStatement3 = String.format(
                        "UPDATE inventory_item SET inventory_item_quantity = inventory_item_quantity - %d WHERE inventory_id = %d",
                        vector1.get(i), vector2.get(i));
                stmt.executeUpdate(sqlStatement3);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Creates an item sold record for a given inventory item with the specified
     * quantity and order ID.
     * Finds the next available item ID by querying the maximum item ID from the
     * item_sold table and
     * adding 1 to it. Updates the item_sold table with the new record and updates
     * the inventory_item
     * table by reducing the quantity of the specified inventory item by the sold
     * quantity.
     * 
     * @param inventoryId the ID of the inventory item being sold
     * @param orderId     the ID of the order associated with the item sold record
     * @param quantity    the quantity of the inventory item being sold
     * @author Bryan
     */
    private void createInventoryItemSold(int InventoryId, int orderID, int quantity) {
        // add a row to ItemsSold
        try {
            int newItemID = 0;
            Statement stmt = conn.createStatement();
            String sqlStatement1 = "SELECT MAX(item_id) FROM item_sold";
            ResultSet result = stmt.executeQuery(sqlStatement1);
            if (result.next()) {
                newItemID = result.getInt(1) + 1;
            }

            // insert into item_sold
            String sqlStatement2 = String.format(
                    "INSERT INTO item_sold (item_id, inventory_id, order_id, item_sold_quantity) VALUES ('%d', '%d', '%d', '%d')",
                    newItemID, InventoryId, orderID, quantity);
            stmt.executeUpdate(sqlStatement2);

            String sqlStatement3 = String.format(
                    "UPDATE inventory_item SET inventory_item_quantity = inventory_item_quantity - %d WHERE inventory_id = %d",
                    quantity, InventoryId);
            stmt.executeUpdate(sqlStatement3);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Retrieve the cost of a menu item from the menu table based on its ID.
     *
     * @param itemID the ID of the menu item to get the price for.
     * @return the cost of the menu item as a double.
     * @author Srikar
     */
    public double getPriceOfMenuItem(int itemID) {
        double price = 0.0;
        try {
            // Run query
            ResultSet result = runCommand("SELECT MENU_ITEM_COST FROM Menu WHERE MENU_ITEM_ID = " + itemID + ";");

            // Extract price from result
            if (result.next()) {
                price = result.getFloat("MENU_ITEM_COST");
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return price;
    }

    public void deleteRow(String tableName, String columnName, Long ID) {
        try {
            String command = "DELETE FROM " + tableName + " WHERE " + columnName + " = " + ID + ";";
            runCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Retrieve the cost of an inventory item from the inventory_item table based on
     * its ID.
     *
     * @param itemID the ID of the inventory item to get the price for.
     * @return the cost of the inventory item as a double.
     * @author Arjun
     */
    public double getPriceOfInventoryItem(int itemID) {
        double price = 0.0;
        try {
            // Run query
            ResultSet result = runCommand(
                    "SELECT inventory_item_cost FROM inventory_item WHERE inventory_id = " + itemID + ";");

            // Extract price from result
            if (result.next()) {
                price = result.getFloat("inventory_item_cost");
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return price;
    }

    /**
     * Return password associated with email
     *
     * @param email the email address of the employee to get the password for.
     * @return the password associated with the email address as a String.
     * @author Daniela M
     */
    public String getPasswd(String email) { // check error handlign
        try {
            // run query
            ResultSet result = runCommand("SELECT PASSWORD FROM Employee WHERE EMAIL = '" + email + "';");

            // if null, return empty string, else return password as string
            if (result.next()) {
                if (result.getString("PASSWORD") == null) {
                    return "";
                } else {
                    return result.getString("PASSWORD");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return "";
    }

    /**
     * Adds a new menu item to the restaurant's menu.
     * The menu ID for the new item is the last menu ID + 1.
     *
     * @param name the name of the new menu item
     * @param cost the cost of the new menu item
     * @author Daniela Santos
     */
    public void addMenuItem(String name, double cost) {
        try {
            // run query
            String sqlStatement = String.format("INSERT INTO Menu (MENU_ITEM_ID, MENU_ITEM_NAME, MENU_ITEM_COST) " +
                    "VALUES ((SELECT MAX(MENU_ITEM_ID) FROM Menu) + 1, '%s', %f)", name, cost);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Adds a new recipe item to the restaurant's recipe book.
     * The recipe ID for the new item is the last recipe ID + 1.
     *
     * @param name        the name of the new recipe item
     * @param inventoryId the ID of the inventory item used in the recipe
     * @param menuId      the ID of the menu item that the recipe item belongs to
     * @param amtUsed     the amount of the inventory item used in the recipe
     * @author Arjun
     */
    public void addRecipeItem(String name, int inventoryId, int menuId, int amtUsed) {
        try {
            // get the last recipe id
            ResultSet result = runCommand("SELECT MAX(RECIPE_ID) as max_id FROM Recipe_item");
            int lastRecipeId = 0;
            if (result.next()) {
                lastRecipeId = result.getInt("max_id");
            }

            // insert the new recipe item with last recipe id + 1
            String sqlStatement = String
                    .format("INSERT INTO Recipe_item (RECIPE_ID, RECIPE_ITEM_NAME, INVENTORY_ID, MENU_ID, AMT_USED) " +
                            "VALUES (%d, '%s', %d, %d, %d)", lastRecipeId + 1, name, inventoryId, menuId, amtUsed);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Adds an inventory item to the database with the given name, cost, and
     * 
     * quantity.
     * 
     * @param name     name of the inventory item
     * @param cost     the cost of the inventory item
     * @param quantity the quantity of the inventory item
     * @author Ariela
     */
    public void addInventoryItem(String name, double cost, double quantity) {
        try {
            // get the last recipe id
            ResultSet result = runCommand("SELECT MAX(INVENTORY_ID) as max_id FROM inventory_item");
            int lastInventoryID = 0;
            if (result.next()) {
                lastInventoryID = result.getInt("max_id");
            }

            // insert the new recipe item with last recipe id + 1
            String sqlStatement = String
                    .format("INSERT INTO inventory_item (inventory_ID, inventory_ITEM_NAME, inventory_item_cost, inventory_item_quantity) "
                            +
                            "VALUES (%d, '%s', %f, %f)", lastInventoryID + 1, name, cost, quantity);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Gets the name of a menu item given its ID.
     *
     * @param id the ID of the menu item to get the name of
     * @return the name of the menu item with the given ID, or an empty string if no
     *         such item exists
     * @author Srikar
     */
    public String getNameFromID(int id) {
        try {
            ResultSet result = runCommand("SELECT menu_item_name FROM menu WHERE MENU_ITEM_ID = " + id);
            if (result.next()) {

                return result.getString("MENU_ITEM_NAME");

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return "";
    }

    /**
     * Updates the quantity of the specified inventory item.
     * 
     * @param itemName    the name or ID of the inventory item to update
     * @param newQuantity the new quantity of the inventory item
     * @author Srikar
     */
    public void changeInventoryQuantity(String itemName, int newQuantity) {
        try {
            // Get the maximum inventory ID from the table
            ResultSet result = runCommand("SELECT MAX(INVENTORY_ID) FROM InventoryItem");
            result.next();
            int maxId = result.getInt(1);

            // Check if itemName is a valid integer ID
            int itemId = Integer.parseInt(itemName);
            if (itemId < 1 || itemId > maxId) {
                return;
            }

            // run query
            runCommand("UPDATE InventoryItem SET INVENTORY_ITEM_QUANTITY = " + newQuantity
                    + " WHERE INVENTORY_ID = " + itemId + ";");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Creates Sales History list to display in manager UI
     * 
     * @param initialDate lowerbound for sales history interval
     * @param finalDate   upperbound for sales history interval
     * @author Daniela Santos
     */
    public ObservableList<SaleData> salesHistory(String initialDate, String finalDate) {
        ObservableList<SaleData> saleData = FXCollections.observableArrayList();
        try {
            // Get the sales data for the given time window
            // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            // String initialDateString = formatter.format(initialDate);
            // String finalDateString = formatter.format(finalDate);

            ResultSet result = runCommand(
                    "SELECT Menu.MENU_ITEM_ID, Menu.MENU_ITEM_NAME, SUM(item_sold.ITEM_SOLD_QUANTITY) AS TOTAL_QUANTITY FROM item_sold "
                            +
                            "JOIN Menu ON Menu.MENU_ITEM_ID = item_sold.MENU_ITEM_ID " +
                            "JOIN Orders ON Orders.ORDER_ID = item_sold.ORDER_ID " +
                            "WHERE Orders.DATE_ORDERED BETWEEN '" + initialDate + "' AND '" + finalDate + "' " +
                            "GROUP BY Menu.MENU_ITEM_ID, Menu.MENU_ITEM_NAME;");

            // Parse the sales data into a list of SaleData objects
            while (result.next()) {
                Long menuItemId = result.getLong("MENU_ITEM_ID");
                String menuItemName = result.getString("MENU_ITEM_NAME");
                Long totalQuantity = result.getLong("TOTAL_QUANTITY");
                SaleData data = new SaleData(menuItemId, menuItemName, totalQuantity);
                saleData.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return saleData;
    }

    /**
     * Retrieves a list of the 20 most popular menu item combos sold within a given
     * date range.
     *
     * @param initialDate the start of the date range
     * @param finalDate   the end of the date range
     * @return an ObservableList of String arrays, each containing the names of two
     *         menu items that are frequently sold together
     * @author Daniela Santos
     */
    public ObservableList<Combo> popularCombos(String initialDate, String finalDate) {
        ObservableList<Combo> popularCombos = FXCollections.observableArrayList();
        try {
            // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            // String initialDateString = formatter.format(initialDate);
            // String finalDateString = formatter.format(finalDate);

            String query = "SELECT m1.MENU_ITEM_NAME AS menu_item_name_1, m2.MENU_ITEM_NAME AS menu_item_name_2, COUNT(*) AS combo_count "
                    +
                    "FROM item_sold s1 " +
                    "JOIN item_sold s2 ON s1.ORDER_ID = s2.ORDER_ID AND s1.MENU_ITEM_ID < s2.MENU_ITEM_ID " +
                    "JOIN Menu m1 ON s1.MENU_ITEM_ID = m1.MENU_ITEM_ID AND m1.MENU_ITEM_ID <= 25 " +
                    "JOIN Menu m2 ON s2.MENU_ITEM_ID = m2.MENU_ITEM_ID AND m2.MENU_ITEM_ID <= 25 " +
                    "WHERE s1.ORDER_ID IN (SELECT ORDER_ID FROM orders WHERE DATE_ORDERED BETWEEN '" + initialDate
                    + "' AND '" + finalDate + "') " +
                    "GROUP BY menu_item_name_1, menu_item_name_2 " +
                    "ORDER BY combo_count DESC LIMIT 20;";

            ResultSet result = runCommand(query);
            if (result == null)
                return popularCombos;
            while (result.next()) {
                String menuItem1 = result.getString("menu_item_name_1");
                String menuItem2 = result.getString("menu_item_name_2");
                Long comboCount = result.getLong("combo_count");
                Combo combo = new Combo(menuItem1, menuItem2, comboCount);
                popularCombos.add(combo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return popularCombos;
    }

    /**
     * Creates a Z report object
     * 
     * @author Srikar
     */
    public void createZReport() {
        try {
            // get new pk
            int newReportID = 0;
            Statement stmt = conn.createStatement();
            String sqlStatement1 = "SELECT MAX(report_id) FROM zreports";
            ResultSet result = stmt.executeQuery(sqlStatement1);
            if (result.next()) {
                newReportID = result.getInt(1) + 1;
            }

            // get new latest order
            int lastOrderID = 0;
            String sqlStatement5 = "SELECT MAX(order_id) FROM orders";
            ResultSet result3 = stmt.executeQuery(sqlStatement5);
            if (result3.next()) {
                lastOrderID = result3.getInt(1);
            }

            // get zreport date
            // get the current date as a LocalDate object
            //
            LocalDate today = LocalDate.now();
            // format the date as a string in "MM-dd-yyyy" format
            String date = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

            // get report_total_cost
            float reportTotalCost = 0;
            String sqlStatement6 = "SELECT order_cost FROM orders WHERE order_id > (SELECT last_order_id FROM zreports WHERE report_id=(SELECT MAX(report_id) FROM zreports))";
            ResultSet result4 = stmt.executeQuery(sqlStatement6);
            while (result4.next()) {
                reportTotalCost += Float.parseFloat(result4.getString(1));
            }

            // insert into item sold
            String sqlStatement2 = String.format(
                    "INSERT INTO zreports (report_id, last_order_id, zreport_date, report_total_cost, is_zreport) VALUES ('%d', '%d', '%s', '%f', '%d')",
                    newReportID, lastOrderID, date, reportTotalCost, 1);
            stmt.executeUpdate(sqlStatement2);

            // create zreportContent
            createZReportContent(newReportID);

            // reset menu items sold to 0
            String sqlStatement7 = "UPDATE menu SET menu_item_sold_since_z =0";
            stmt.executeUpdate(sqlStatement7);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void createZReportContent(int reportID) {
        try {
            // get new pk
            int newReportCID = 0;
            Statement stmt = conn.createStatement();
            String sqlStatement1 = "SELECT MAX(report_content_id) FROM zreportcontent";
            ResultSet result = stmt.executeQuery(sqlStatement1);
            if (result.next()) {
                newReportCID = result.getInt(1) + 1;
            }

            // populate zreport content table
            String sqlStatement4 = "SELECT * FROM menu";
            ResultSet result2 = stmt.executeQuery(sqlStatement4);
            String sqlStatement2 = "";
            ArrayList<String> vector1 = new ArrayList<String>();
            ArrayList<Integer> vector2 = new ArrayList<Integer>();
            while (result2.next()) {
                vector1.add(result2.getString("menu_item_name"));
                vector2.add(result2.getInt("menu_item_sold_since_z"));

            }
            for (int i = 0; i < vector1.size(); i++) {
                sqlStatement2 = String.format(
                        "INSERT INTO zreportcontent (report_content_id, report_id, menu_item_name, menu_item_quantity) VALUES ('%d', '%d', '%s', '%d')",
                        newReportCID, reportID, vector1.get(i), vector2.get(i));
                stmt.executeUpdate(sqlStatement2);
                newReportCID += 1;
            }

            // insert into item sold

            // zReportTotal = 0;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void createXReport() {
        try {
            int newReportID = 0;
            Statement stmt = conn.createStatement();
            String sqlStatement1 = "SELECT MAX(report_id) FROM zreports";
            ResultSet result = stmt.executeQuery(sqlStatement1);
            if (result.next()) {
                newReportID = result.getInt(1) + 1;
            }

            // get new latest order
            int lastOrderID = 0;
            String sqlStatement5 = "SELECT last_order_id FROM zreports WHERE report_id=(SELECT MAX(report_id) FROM zreports)";
            ResultSet result3 = stmt.executeQuery(sqlStatement5);
            if (result3.next()) {
                lastOrderID = result3.getInt(1);
            }

            // get zreport date
            // get the current date as a LocalDate object
            //
            LocalDate today = LocalDate.now();
            // format the date as a string in "MM-dd-yyyy" format
            String date = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

            // get report_total_cost
            float reportTotalCost = 0;
            String sqlStatement6 = "SELECT order_cost FROM orders WHERE order_id > (SELECT last_order_id FROM zreports WHERE report_id=(SELECT MAX(report_id) FROM zreports))";
            ResultSet result4 = stmt.executeQuery(sqlStatement6);
            while (result4.next()) {
                reportTotalCost += Float.parseFloat(result4.getString(1));
            }

            // insert into item sold
            String sqlStatement2 = String.format(
                    "INSERT INTO zreports (report_id, last_order_id, zreport_date, report_total_cost, is_zreport) VALUES ('%d', '%d', '%s', '%f', '%d')",
                    newReportID, lastOrderID, date, reportTotalCost, 0);
            stmt.executeUpdate(sqlStatement2);

            // create zreportContent
            createZReportContent(newReportID);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Retrieves a list of all inventory items that need to be restocked
     * 
     *
     * @return an ObservableList of Inventory objects representing the retrieved
     *         rows of inventory items.
     * @author Daniela Martinez Banda
     * 
     * 
     */
    public ObservableList<Inventory> createRestockReport() {
        String tableName = "inventory_item";
        ObservableList<Inventory> rows = FXCollections.observableArrayList();

        try {
            // Get inventory that has a quantity of less than 30
            ResultSet result = runCommand("Select * FROM "
                    + tableName + " WHERE inventory_item_quantity <= 50");
            // Loop through the rows in result
            while (result.next()) {
                // Loop through columns an
                rows.add(new Inventory(result.getLong(1), result.getString(2),
                        result.getDouble(3), result.getLong(4)));
                // Add current row to rows
            }
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rows;
    }

}

/**
 * * Class to store Id and Quantity within the Database function
 */

class CustomPair {
    public int ID;
    public int Quantity;

    CustomPair(int iD, int quantity) {
        this.ID = iD;
        this.Quantity = quantity;
    }
}