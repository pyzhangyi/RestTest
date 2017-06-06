package com.Pokke;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eric on 5/31/17.
 */
public class Utility {

    public static Map<String, String> readProperties(String propertiesFileLocation) throws FileNotFoundException, IOException {
        Map<String, String> results = new HashMap<String, String>();

        InputStream in = Utility.class.getResourceAsStream(propertiesFileLocation);
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        String current = null;

        while(null != (current = bf.readLine())) {
            String[] temp = current.split("=");
            results.put(temp[0], temp[1]);
        }

        bf.close();

        return results;
    }

    public static String readSQLFile(String sqlFileLocation) throws FileNotFoundException, IOException {
        InputStream in = Utility.class.getResourceAsStream(sqlFileLocation);
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));

        String results = "";
        String current = null;
        while(null != (current = bf.readLine())) {
            results += current.equals("")? "" : current + " ";
        }

        bf.close();

        return results;
    }

    public static String replace(String sqlFileLocation, Map<String, String> dict) throws IOException {
        String results = readSQLFile(sqlFileLocation);

        for(String currentKey : dict.keySet()) {
            results = results.replace(currentKey, dict.get(currentKey));
        }

        return results;
    }

    public static String resultSetToJSON(ResultSet rs) {
        try {
            JSONArray jsonArray = new JSONArray();
            ResultSetMetaData rsm = rs.getMetaData();
            int numOfCol = rsm.getColumnCount();

            while(rs.next()) {
                JSONObject json = new JSONObject();
                for(int i = 1; i <= numOfCol; ++i) {
                    String colName = rsm.getColumnName(i);
                    json.put(colName, rs.getObject(colName));
                }
                jsonArray.put(json);
            }

            return jsonArray.toString();
        }
        catch (Exception  e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return null;
    }

}
