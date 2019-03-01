package Model;

public class Main {

    public static void main(String[] args){
        Database database = new Database();
        database.createDatabase();
        ImpressionParser ip = new ImpressionParser();
        ip.loadDatabase("Impression");
        ConversionParser cp = new ConversionParser();
        cp.loadDatabase("Conversion");

    }
}
