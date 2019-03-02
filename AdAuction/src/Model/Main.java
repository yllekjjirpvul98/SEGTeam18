package Model;

public class Main {

    public static void main(String[] args){
        Database db = new Database();
        db.connectToDatabase();
        Filter filter = new Filter(false, false, false, false, false);
        Bounce bounce = new Bounce(false, false);
        Calculation cal = new Calculation(db, bounce, filter);
    }
}
