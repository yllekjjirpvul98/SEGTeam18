package Model;

import java.sql.SQLException;

public class PopulateDatabase {
//
    public static void main(String[] args){
        double start = System.nanoTime();


        Database db = new Database();
        db.connectToDatabase();
        ServerParser sp = new ServerParser(db);
        ImpressionParser ip = new ImpressionParser(db);
        ClickParser cp = new ClickParser(db);
        sp.loadDatabase();
        ip.loadDatabase();
        cp.loadDatabase();
        double elapsedTime = (System.nanoTime() - start) / 1_000_000_000.0;
        System.out.println("It takes " + elapsedTime + "s to load");
    }
}