package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connect = null;
    private Statement statement = null;
    private String campaignName = null;
//
//    Database(){
//
//    }

    public void connectToDatabase(){
        //method to create database AdAuction using sql
        try {
//            connect = DriverManager.getConnection("jdbc:mysql://db4free.net/adauction?" + "user=segteam18&password=iloveprogramming");
            connect = DriverManager.getConnection("jdbc:h2:mem:"  + ";MODE=MYSQL;");
            statement = connect.createStatement();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void reloadDatabase(){
        try {
            statement.execute("DROP Table IF EXISTS Click ");
            statement.execute("DROP Table IF EXISTS Server");
            statement.execute("DROP TABLE IF EXISTS Impression");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Statement getStatement(){
        return statement;
    }

    public Connection getConnect() {
        return connect;
    }
}
