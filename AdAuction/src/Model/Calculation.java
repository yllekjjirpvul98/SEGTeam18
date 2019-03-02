package Model;

public class Calculation {
    Database db;
    Filter filter;
    Bounce bounce;

    Calculation(Database db, Bounce bounce, Filter filter){
        this.bounce = bounce;
        this.filter = filter;
        this.db = db;
    }

    public int calImpression(Filter filter){
        return 0;
    }

    public int calClicks(){
        return 0;
    }

    public int calUnique(){
        return 0;
    }

    public int calBounce(){
        return 0;
    }

    public int calConversion(){
        return 0;
    }

    public int calTotal(){
        return 0;
    }

    public double calCTR(){
        return 0;
    }

    public double calCPA(){
        return 0;
    }

    public double calCPC(){
        return 0;
    }

    public double calCPM(){
        return 0;
    }

    public double calBounceRate(){
        return 0;
    }
}
