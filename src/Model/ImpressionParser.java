package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

public class ImpressionParser implements Parser{
    private Database db;
    private String filepath;

    ImpressionParser (Database db, String filepath){
        this.db = db;
        this.filepath = filepath;
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
        String filename = filepath + "/impression_log.csv";
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String line = "";
            int count = 0;
            r.readLine(); //the firstline is not needed because it is the attributes
            db.getStatement().execute("BEGIN TRANSACTION ");
            String query = "INSERT ignore into Impression (ImpressionDate, ID, Gender, Age, Income, Context, ImpressionCost) VALUES ";
            while ((line = r.readLine()) != null){
                count += 1;
                String[] array = line.split(",");
                if (count == 1000){
                    query = query.substring(0, query.length()-3) + ";";
                    db.getStatement().execute(query);
                    count = 1;
                    query = "INSERT ignore into Impression (ImpressionDate, ID, Gender, Age, Income, Context, ImpressionCost) VALUES ";
                }
                query += "(\'" + array[0] + "\', " + array[1] + ", \'"  + array[2] + "\', \'" + array[3] + "\', \'" + array[4] + "\', \'" + array[5] + "\', " + array[6] + "), \n" ;
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
        System.out.println("Impression takes " + (System.nanoTime() - start)/1_000_000_000 + "s to load");
    }


}
