package Model;

public class ClickParser implements Parser{
    Database db;

    ClickParser(Database db){
        this.db = db;
    }

    @Override
    public void loadDatabase(String tablename) {

    }
}
