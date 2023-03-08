import java.sql.*;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Database {

    private Connection conn = null;
    private static String teamNumber = "team_42";
    private static String dbName = "csce315331_" + teamNumber;
    private static String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;

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
     * @param command the SQL command to run
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
        }
        return null;
    }

    /**
     * <<<<<<< HEAD
     * =======
     * 
     * >>>>>>> bd10b86cec59345cb0b60afcdc9b0fb4056336ae
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
     * @author Daniela Santos
     * @param email the email address of the user to check
     * @return a boolean value indicating whether the user is a manager
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
     * @param tableName the name of the table to retrieve the number of rows for
     * @return an integer value representing the number of rows in the table
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
     * 
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

    /*
     * Get blocks of 20 Rows in any table based on specified table name and
     * converts to array list
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
     * Return the specific recipe items used in an item on the menu.
     * List length is fixed and contains quantities for every ingredient.
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
     * Create row in order table.
     * Menu items is a list of food (ID, Quantity)
     * inventoryItems is a list of inventory items (ID, Quantity)
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
            String date = today.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
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
     * Change the cost of any menu item in menu table
     */
    public void changePrice(String itemName, double newCost) {
        try {
            if (Integer.parseInt(itemName) > 26)
                return;
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
     * Create menu item from given info, find Order_ID from last added order + 1
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
     * Create inventory item from given info, find Order_ID from last added order +
     * 1
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
     * Add a new menu item to Menu
     * with menu id which is the last menu id + 1
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
     * Add a new recipe item to Recipe
     * with recipe id which is the last recipe id + 1
     */
    public void addRecipeItem(String name, int inventoryId, int menuId, int amtUsed) {
        try {
            // get the last recipe id
            ResultSet result = runCommand("SELECT MAX(RECIPE_ID) as max_id FROM Recipe");
            int lastRecipeId = 0;
            if (result.next()) {
                lastRecipeId = result.getInt("max_id");
            }

            // insert the new recipe item with last recipe id + 1
            String sqlStatement = String.format("INSERT INTO Recipe (RECIPE_ID, RECIPE_ITEM_NAME, INVENTORY_ID, MENU_ID, AMT_USED) " +
                    "VALUES (%d, '%s', %d, %d, %d)", lastRecipeId + 1, name, inventoryId, menuId, amtUsed);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}

/**
 * Class to store Id and Quantity within the Database function
 */
class CustomPair {
    public int ID;
    public int Quantity;

    CustomPair(int iD, int quantity) {
        this.ID = iD;
        this.Quantity = quantity;
    }
}