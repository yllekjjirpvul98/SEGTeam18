package Model;

import Control.Controller;

/*
    Class contains the back-end code of the program that allows the user to initialize all the configurations.
 */

public class Model {

    private Controller control;

    private Database db;
    private Filter filter;
    private Bounce bounce;
    private Settings settings;
    private Calculation cal;
    
    public void init(Controller control){
        this.control = control;
        db = new Database();
        db.connectToDatabase();
        filter = new Filter(false, false, false, false, false);
        bounce = new Bounce(false, false);
        settings = new Settings(false, false);
        cal = new Calculation(db, bounce, filter);
    }

    //Getters
    public Database getDb() {
        return db;
    }

    public Filter getFilter() {
        return filter;
    }

    public Bounce getBounce() {
        return bounce;
    }

    public Settings getSettings(){
        return settings;
    }

    public Calculation getCal() {
        return cal;
    }

    public void populateDatabase (String filepath){
        new PopulateDatabase(getDb(), filepath);
    }

}
