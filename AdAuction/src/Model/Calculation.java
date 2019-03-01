package Model;

public class Calculation {
    Database database;
    Filter filter;
    Bounce bounce;

    Calculation(Database database, Filter filter, Bounce bounce){
        this.bounce = bounce;
        this.filter = filter;
        this.database = database;
    }

    public int calImpression(){
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
