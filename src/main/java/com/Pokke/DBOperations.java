package com.Pokke;

import java.sql.*;
import java.util.List;

/**
 * Created by eric on 6/3/17.
 */
public class DBOperations {

    private Connection conn = null;
    private Statement stmt = null;

    public DBOperations() throws Exception {
        if(null == conn) {
            this.conn = new DBConnector().getConn();
        }
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
    }

    public ResultSet selectOperation(String query) {
        try {
            return stmt.executeQuery(query);
        }
        catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return null;
    }

    public ResultSet conditionSelectOperation(String query, List<Integer> parameters) {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for(int i = 1; i <= parameters.size(); ++i) {
                ps.setInt(i, parameters.get(i - 1));
            }
            return ps.executeQuery();
        }
        catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return null;
    }

    public void close() throws SQLException {
        this.stmt.close();
        this.conn.close();
    }
}