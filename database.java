import java.sql.*;

public class database{
    
    Connection conn = null;
    String teamNumber = "team_42";
    String dbName = "csce315331_" + teamNumber;
    String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    dbSetup myCredentials = new dbSetup();

    //constructor
    database(){
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

    public String getPasswd(String email){
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