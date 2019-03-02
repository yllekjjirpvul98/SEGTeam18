package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServerParser implements Parser {
    Database db;

    ServerParser (Database db){
        this.db = db;
        try {
            db.getStatement().execute("CREATE TABLE Server(" +
                    "ConversionID int NOT NULL AUTO_INCREMENT,"+
                    "EntryDate datetime," +
                    "ID bigint," +
                    "ExitDate datetime," +
                    "PageViewed int," +
                    "Conversion varchar(255)," +
                    "primary key (ConversionID)" +
                    ");");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void loadDatabase() {
        String filename = "AdAuction/src/2_week_campaign_1/2_week_campaign_2/server_log.csv";
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String line = "";
            r.readLine(); //the firstline is not needed because it is the attributes
            while ((line = r.readLine()) != null) {
                String[] array = line.split(",");
                if (!array[2].equals("n/a")) {
                    PreparedStatement ps = db.getConnect().prepareStatement("INSERT into Server (EntryDate, ID, ExitDate, PageViewed, Conversion)" +
                            "VALUES (?, ?, ?, ?, ?)");
                    for (int i = 0; i < array.length; i++) {
                        ps.setString(i + 1, array[i]);
                    }
                    ps.execute();
                    ps.clearParameters();
                }else continue;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
