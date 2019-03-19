package Model;

public class PopulateDatabase {
    Database db;
    public PopulateDatabase(Database db) {
        float start = System.nanoTime();

        ThreadGroup tg = new ThreadGroup("Parser");
        ImpressionParser ip = new ImpressionParser(db);
        Thread i = new Thread(tg, ip);
        i.setPriority(Thread.MAX_PRIORITY);
        i.start();
        ServerParser sp = new ServerParser(db);
        Thread s = new Thread(tg, sp);
        s.start();
        ClickParser cp = new ClickParser(db);
        Thread c = new Thread(tg, cp);
        c.start();

        while (tg.activeCount() != 0) {
            //System.out.println("Loading...");
        }
        float elapsedTime = (System.nanoTime() - start) / 1_000_000_000;
        System.out.println("It takes " + elapsedTime + "s to load all the data");
    }
}