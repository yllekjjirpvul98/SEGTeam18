package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImpressionParser implements Parser{
    private Database db;

    ImpressionParser (Database db){
        this.db = db;
        //create a new table to store impression
        try {
            db.getStatement().execute("CREATE TABLE Impression(" +
                    "ImpressionDate datetime," +
                    "ID bigint," +
                    "Gender varchar(255)," +
                    "Age varchar(255)," +
                    "Income varchar(255)," +
                    "Context varchar(255)," +
                    "ImpressionCost float," +
                    "primary key (ID)" +
                    ");");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void loadDatabase() {
        String filename = "impression_log.csv";
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String line = "";
            r.readLine(); //the firstline is not needed because it is the attributes
            while ((line = r.readLine()) != null){
                String[] array = line.split(",");
                PreparedStatement ps = db.getConnect().prepareStatement("INSERT ignore into Impression (ImpressionDate, ID, Gender, Age, Income, Context, ImpressionCost)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)");
                for (int i = 0; i < array.length; i++){
                    ps.setString(i+1, array[i]);
                }
                ps.execute();
                ps.clearParameters();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
