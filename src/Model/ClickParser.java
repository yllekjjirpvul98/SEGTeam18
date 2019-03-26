package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

public class ClickParser implements Parser{
    private Database db;
    private String filepath;

    ClickParser(Database db, String filepath){
        this.db = db;
        this.filepath = filepath;
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
        String filename = filepath + "\\click_log.csv";
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String line = "";
            int count = 0;
            r.readLine(); //the firstline is not needed because it is the attributes
            db.getStatement().execute("BEGIN TRANSACTION ");
            String query = "INSERT into Click (Date, ID, ClickCost) VALUES ";
            while ((line = r.readLine()) != null){
                count += 1;
                String[] array = line.split(",");
                if (count == 1000){
                    query = query.substring(0, query.length()-3) + ";";
                    db.getStatement().execute(query);
                    count = 1;
                    query = "INSERT into Click (Date, ID, ClickCost) VALUES ";
                }
                query += "(\'" + array[0] + "\', " + array[1] + ", "  + array[2] + "), \n" ;
            }                    query = query.substring(0, query.length()-3) + ";";
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
        System.out.println("Click takes " + (System.nanoTime() - start)/1_000_000_000 + "s to load");
    }
}
