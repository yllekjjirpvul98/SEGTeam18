package Model;

public class ConversionParser implements Parser {
    Database db;

    ConversionParser (Database db){
        this.db = db;
    }

    @Override
    public void loadDatabase(String tablename) {

    }
}
