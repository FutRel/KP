package org.example;

import java.sql.*;

public class SQLTableOutput {

    public static void outputData(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        /*query = "SELECT * FROM KP.users";*/
        ResultSet result = statement.executeQuery(query);

        while(result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            String email = result.getString("email");

            System.out.print("Vacant post: ");
            System.out.print("id = " + id);
            System.out.print(", name = \"" + name + "\"");
            System.out.println(", email = \"" + email + "\".");
        }
    }

}
