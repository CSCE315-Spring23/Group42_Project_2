    /**
     * Add a new menu item to Menu
     * with menu id which is the last menu id + 1
     */
    /*public void addMenuItem(String name, double cost) { 
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
    }*/





