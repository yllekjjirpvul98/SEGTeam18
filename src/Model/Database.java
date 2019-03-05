package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connect = null;
    private Statement statement = null;

    public void connectToDatabase(){
        //method to create database AdAuction using sql
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/adauction?" + "user=root&password=Lukinator247");
            statement = connect.createStatement();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void closeDatabase(){
        try {
            connect.close();
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
