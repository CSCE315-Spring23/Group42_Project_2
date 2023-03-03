import java.sql.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Connection conn = null;
        String teamNumber = "team_42";
        String dbName = "csce315331_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup(); 
        
        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        try {

        }
    }
}