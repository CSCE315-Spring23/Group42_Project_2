import java.sql.*;

public class database{
    
    private static final Connection conn;
    private static final String teamNumber;
    private static final String dbName;
    private static final String dbConnectionString;
    dbSetup myCredentials = new dbSetup();

    //constructor
    database(){
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

    private ResultSet runCommand(String sqlStatement){
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

    public bool isManager(email){
        //do the thing
    }


    /**
    Creates ArrayList of inventory items (and quantities)
    used in a specific day 
    */
    public ArrayList<int> inventoryItemsUsed(String date){
        //do the thing
    }

    public ArrayList<ArrayList<String>> get20Rows(String tableName, int whichTwenty){
        //do the thing
    }

    public int getNumRows(String tableName){
        //do the thing
    }

    /**
    returns the specific recipe items used in an item on the menu.
    list length is fixed and contains quantities for every ingredient.
     */
    public ArrayList<int> getRecipe(String item){
        //do the thing
    }

    /**
    creates row in order table. 
    Menu items is a list of food (ID, Quantity)
    inventoryItems is a list of inventory items (ID, Quantity)
     */
    public void createOrder(double price, ArrayList<int,int> menuItems, ArrayList<int,int> inventoryItems){
        //do the thing
    }

    /**
    change the cost of any menu item
     */
    public void changePrice(String itemName, double newCost){
        //do the thing
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

    public double getPriceOfMenuItem(int itemID){
        //do the thing
    }

    public String getPasswd(String email){  //check error handlign
        try{
            //run query
            ResultSet result = runCommand("
                SELECT PASSWORD FROM Employee WHERE 
                EMPLOYEE_NAME = " + email + ";");

            //if null, return empty string, else return password as string
            if (result.next()) return result.getString("PASSWORD") == null ? 
                "" : result.getString("PASSWORD");
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}