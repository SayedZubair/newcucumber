package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataBaseUtility {

    private static ResultSet rset;
    private static ResultSetMetaData rSetMetaData;
    static Connection conn = null;
    static Statement statement = null;
    /**
     * This method create connection to the database, execute query and return object ResulSet
     *
     * @param sqlQuery
     * @return ResultSet
     */
    public static ResultSet getResultSet(String sqlQuery) {

//        Connection conn = null;
//        Statement statement = null;
        try {
            conn = DriverManager.getConnection(
                    ConfigReader.getPropertyValue("dbUrl"),
                    ConfigReader.getPropertyValue("dbUsername"),
                    ConfigReader.getPropertyValue("dbPassword"));
            statement = conn.createStatement();

            rset = statement.executeQuery(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
//        }finally {
//            try {
//                if(statement!= null){
//                    statement.close();
//                }
//                if(conn!= null);{
//                    conn.close();
//                }
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
        }
        return rset;
    }

    /**
     * This method return an Object of ResultSetMetaData because when strore the values inside
     * the map when need the number of the culomn and name of the clumns
     *
     * @param query
     * @return ResultSetMetaData
     */
    public static ResultSetMetaData getRsetMetada(String query) {
         getResultSet(query);
        rSetMetaData = null;//here we are deleting the meta data from the previus query resultset
        //because each time query changes
        try {
            rSetMetaData = rset.getMetaData();//here we are retriving the new meda data from the
                                               //FROM the recent result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSetMetaData;
    }

    /**
     * This method extract data from ResultSet and stores into List of Maps
     */

    public static List<Map<String, String>> getListOfMapsFromRset(String query) {

        rSetMetaData = getRsetMetada(query);
        List<Map<String, String>> listFromRset = new ArrayList<>();
        Map<String, String> mapData;
// as we are getting the meta data of the
        try {
            //iterates over the rows
            while (rset.next()) {
                mapData = new LinkedHashMap<>();
                //iterates over the columns
                for (int i = 1; i <= rSetMetaData.getColumnCount(); i++) { //this loop iterate through the header
                    String key = rSetMetaData.getColumnName(i);
                    String value = rset.getString(key);
                    //we store data from every column into a map
                    mapData.put(key, value);
                }
                //we store map with Data into a List
                listFromRset.add(mapData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listFromRset;
    }

    public static void closeResultSet(ResultSet rset){
        if(rset!=null){
            try{
                rset.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
