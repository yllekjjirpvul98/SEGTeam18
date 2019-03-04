//package Model;
//
//public class PopulateDatabase {
//
//    public static void main(String[] args){
//        float start = System.nanoTime();
//        Database db = new Database();
//        db.connectToDatabase();
//        ImpressionParser ip = new ImpressionParser(db);
//        ip.loadDatabase();
//        ClickParser cp = new ClickParser(db);
//        cp.loadDatabase();
//        ServerParser sp = new ServerParser(db);
//        sp.loadDatabase();
//        float elapsedTime = (System.nanoTime() - start) /1000F;
//        System.out.println("It takes " + elapsedTime + "to load");
//    }
//}

//finished creating the database with the required tables.