package Model;

public class Main {

    public static void main(String[] args){
        Database database = new Database();
        database.connectToDatabase();
        ImpressionParser ip = new ImpressionParser();
        ip.loadDatabase("Impression");
        ConversionParser cop = new ConversionParser();
        cop.loadDatabase("Conversion");
        ClickParser cp = new ClickParser();
        cp.loadDatabase("Click");
    }
}
