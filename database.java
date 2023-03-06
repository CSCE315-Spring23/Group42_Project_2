import java.sql.*;
import java.util.ArrayList;

public class Database{
    
    private Connection conn;
    private static String teamNumber;
    private static String dbName;
    private static String dbConnectionString;
    dbSetup myCredentials = new dbSetup();

    //constructor
    Database(){
        //initialize variables
        this.conn = null;
        this.teamNumber = "team_42";
        this.dbName = "csce315331_" + teamNumber;
        this.dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;

        //open the database
        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    private ResultSet runCommand(String sqlStatement) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sqlStatement);
        return result;
    }

    //call when done, closes connection
    public void closeDB(){
        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
    }

    /**  Retrieves the if login is a manager from the Employee table based on email. */
    public boolean isManager(String email){
        boolean manager = false;
        try{
            //run query
            ResultSet result = runCommand("SELECT IS_MANAGER FROM Employee WHERE EMAIL = '" + email + "';");
            if(result.next()) {
                manager = result.getBoolean("IS_MANAGER");
            }
            result.close();
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return manager;
    }

     /**
    * Returns the total amount of rows/items in table specified by tableName
    */
    public int getNumRows(String tableName){
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
    Creates ArrayList of inventory items (and quantities)
    used in a specific day 
    */
    public ArrayList<Integer> inventoryItemsUsed(String date) {
        int inventoryItemsCount = getNumRows("inventoryItem");
        ArrayList<Integer> inventoryItems = new ArrayList<>(inventoryItemsCount);
        //Initialize all inventory items to be set all to zero
        for (int i = 0; i < inventoryItemsCount; i++) {
            inventoryItems.add(0);
        }
        try {
            // Run query to get order IDs for a specific date
            ResultSet orderResult = runCommand("SELECT ORDER_ID FROM Orders WHERE DATE_ORDERED = '" + date + "';");
            while (orderResult.next()) {
                int orderID = orderResult.getInt("ORDER_ID");
                // Run query to get menu item IDs and sold quantities for each order
                ResultSet menuItemResult = runCommand("SELECT MENU_ITEM_ID, ITEM_SOLD_QUANTITY FROM ItemSold WHERE ORDER_ID = " + orderID + ";");
                while (menuItemResult.next()) {
                    int menuItemID = menuItemResult.getInt("MENU_ITEM_ID");
                    int menuItemQuantity = menuItemResult.getInt("ITEM_SOLD_QUANTITY");
                    // Run query to get inventory IDs and amounts used for each menu item
                    ResultSet recipeResult = runCommand("SELECT INVENTORY_ID, AMT_USED FROM RecipeItem WHERE MENU_ID = " + menuItemID + ";");
                    while (recipeResult.next()) {
                        int inventoryID = recipeResult.getInt("INVENTORY_ID");
                        int amtUsed = recipeResult.getInt("AMT_USED");
                        // Add inventory ID and amount used to list in the index of the inventoryID
                        inventoryItems.set(inventoryID, menuItemQuantity * amtUsed);
                    }
                    recipeResult.close();
                }
                menuItemResult.close();
                
                //Now we also check for each individual inventory item sold (which would be all the costumizations)
                ResultSet InventoryItemSoldResult = runCommand("SELECT INVENTORY_ID, ITEM_SOLD_QUANTITY FROM ItemSold WHERE ORDER_ID = " + orderID + ";");
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

    /* Gets blocks of 20 Rows in any table based on specified table name and converts to array list */
    public ArrayList<ArrayList<String>> get20Rows(String tableName, int whichTwenty){
        int upperBound = (whichTwenty) * 20;
        int lowerBound = upperBound - 20;
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
         try{
            //run query
            ResultSet result = runCommand("SELECT * FROM " 
                + tableName + " WHERE ROWNUM > " 
                + lowerBound + " AND ROWNUM <= "
                + upperBound + ";");
            
            
            // Get metadata which gets info about the types/properties of the columns in a ResultSet
            ResultSetMetaData metaData = result.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            // Loop through the 20 rows in result
            while (result.next()) {
                ArrayList<String> row = new ArrayList<>();
                // Loop through columns and add to current row
                for (int i = 1; i <= numberOfColumns; i++) {
                    row.add(result.getString(i));
                }
                // Add current row to rows
                rows.add(row);
            }

            result.close();
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return rows;
    }

    /**
    * Returns the specific recipe items used in an item on the menu.
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
            ResultSet result = runCommand("SELECT INVENTORY_ID, AMT_USED FROM RecipeItem WHERE MENU_ID = " + menuItemId + ";");
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
    creates row in order table. 
    Menu items is a list of food (ID, Quantity)
    inventoryItems is a list of inventory items (ID, Quantity)
     */
    public void createOrder(double cost, ArrayList<CustomPair> menuItems, ArrayList<CustomPair> inventoryItems){
        try {
            int orderID = 0;
            String date = "date";
            ResultSet result = runCommand(String.format("INSERT INTO order VALUES (%d, %d, %d)", orderID, date, cost));
            for(int i=0; i<menuItems.size(); i++) {
                createMenuItemSold(menuItems.get(i).ID, orderID, menuItems.get(i).Quantity);
            }
            for(int i=0; i<inventoryItems.size(); i++) {
                createMenuItemSold(inventoryItems.get(i).ID, orderID, inventoryItems.get(i).Quantity);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
    change the cost of any menu item in menu table
     */
    public void changePrice(String itemName, double newCost){
         try{
            //run query
            ResultSet result = runCommand("UPDATE Menu SET MENU_ITEM_COST = " + newCost 
            + " WHERE name = '" + itemName + "';");
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    /**
    create item from given info, find Order_ID from last added order + 1
     */
    private void createMenuItemSold(int MenuId, int orderID, int quantity){
        //add a row to ItemsSold
    }
    private void createInventoryItemSold(int InventoryId, int orderID, int quantity){
        //add a row to ItemsSold
    }

    /**  Retrieves the cost of a menu item from the menu table based on its ID. */
    public double getPriceOfMenuItem(int itemID){
        double price = 0.0;
        try {
            // Run query
            ResultSet result = runCommand("SELECT MENU_ITEM_COST FROM Menu WHERE MENU_ITEM_ID = " + itemID + ";");

            // Extract price from result
            if (result.next()) {
                price = result.getDouble("MENU_ITEM_COST");
            }

            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return price;
    }

    public String getPasswd(String email){  //check error handlign
        try{
            //run query
            ResultSet result = runCommand("SELECT PASSWORD FROM Employee WHERE EMAIL = '" + email + "';");

            //if null, return empty string, else return password as string
            if (result.next()){ 
                if (result.getString("PASSWORD") == null) {
                    return "";
                }
                else{
                    return result.getString("PASSWORD");
                }
            }
                
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return "";
    }
}
class CustomPair {
    public int ID;
    public int Quantity;
    CustomPair(int ID, int Quantity) {
        this.ID = ID;
        this.Quantity = Quantity;
    }
}