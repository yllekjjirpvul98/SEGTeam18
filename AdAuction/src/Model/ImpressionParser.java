package Model;

public class ImpressionParser implements Parser{
    Database db;

    ImpressionParser (Database db){
        this.db = db;
    }
    @Override
    public void loadDatabase(String tablename) {

    }
}
