package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

public class ServerParser implements Parser {
    private Database db;

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
            db.getStatement().execute("CREATE INDEX timediff" +
                    "ON Server (EntryDate, ExitDate)");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void loadDatabase() {
        String filename = "server_log.csv";
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String line = "";
            r.readLine(); //the firstline is not needed because it is the attributes
            db.getStatement().execute("BEGIN TRANSACTION ");
            String query = "";
            int count = 0;
            while ((line = r.readLine()) != null) {
                if (count == 0){
                    query += "INSERT into Server (EntryDate, ID, ExitDate, PageViewed, Conversion) VALUES ";
                }
                count += 1;
                String[] array = line.split(",");
                if (count == 1000){
                    query = query.substring(0, query.length()-3) + ";";
                    db.getStatement().execute(query);
                    count = 1;
                    query = "INSERT into Server (EntryDate, ID, ExitDate, PageViewed, Conversion) VALUES ";
                }
                if (!array[2].equals("n/a")) {
                    query += "(\'" + array[0] + "\', " + array[1] + ", \'"  + array[2] + "\', " + array[3] + ", \'" + array[4] + "\'), \n" ;
                } else continue;
            }
            query = query.substring(0, query.length()-3) + ";";
            db.getStatement().execute(query);
            db.getStatement().execute("COMMIT");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run() {
        float start = System.nanoTime();
        loadDatabase();
        System.out.println("Server takes " + (System.nanoTime() - start)/1_000_000_000 + "s to load");
    }
}
