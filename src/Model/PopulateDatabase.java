package Model;

public class PopulateDatabase {
    Database db;
    PopulateDatabase(Database db) {
        float start = System.nanoTime();

        ServerParser sp = new ServerParser(db);
        sp.loadDatabase();
        ImpressionParser ip = new ImpressionParser(db);
        ip.loadDatabase();
        ClickParser cp = new ClickParser(db);
        cp.loadDatabase();

        float elapsedTime = (System.nanoTime() - start) /1000F;
        System.out.println("It takes " + elapsedTime + "to load");
    }
}