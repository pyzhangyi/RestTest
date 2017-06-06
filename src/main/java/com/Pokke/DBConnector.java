package com.Pokke;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 * Created by eric on 5/31/17.
 */
public class DBConnector {

    private Connection conn = null;
    private final String propertiesFileLocation = "/Properties/test.properties";

    public DBConnector() {
        try {
            Map<String, String> properties = Utility.readProperties(propertiesFileLocation);
            String username = properties.get("userName");
            String password = properties.get("password");
            String dbAddr = properties.get("DBAddr") + ":" + properties.get("DBPort") + "/" + properties.get("DBName");

            Class.forName("org.postgresql.Driver").newInstance();

            conn = DriverManager.getConnection(dbAddr, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return this.conn;
    }

}
