package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClickParser implements Parser{
    Database db;

    ClickParser(Database db){
        this.db = db;
        try {
            db.getStatement().execute("CREATE TABLE Click(" +
                    "ClickID int NOT NULL AUTO_INCREMENT," +
                    "Date datetime," +
                    "ID bigint," +
                    "ClickCost float," +
                    "primary key (ClickID)" +
                    ");");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void loadDatabase() {
        String filename = "AdAuction/src/2_week_campaign_1/2_week_campaign_2/click_log.csv";
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String line = "";
            r.readLine(); //the firstline is not needed because it is the attributes
            while ((line = r.readLine()) != null){
                String[] array = line.split(",");
                PreparedStatement ps = db.getConnect().prepareStatement("INSERT into Click (Date, ID, ClickCost)" +
                        "VALUES (?, ?, ?)");
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
