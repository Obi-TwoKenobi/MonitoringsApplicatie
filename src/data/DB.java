package data;

import java.sql.*;

public class DB {

    public static Connection databaseConnection;

    private static final String dbUrl = "jdbc:mariadb://192.168.3.2:3306/monitoring";
    private static final String user = "HAProxy";
    private static final String password = "ICTM2O2!#gangsters";

    private DB(){}

    public static Connection getConnection(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            if(databaseConnection != null){
                return databaseConnection;
            }else{
                databaseConnection = DriverManager.getConnection(dbUrl, user, password);
                databaseConnection.setAutoCommit(true);
                return databaseConnection;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
            System.out.println("Error connecting to database");
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return null;
    }
}