package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

public class Calculation {
    Database db;
    Filter filter;
    Bounce bounce;
    Statement statement;
    Connection connection;

    Calculation(Database db, Bounce bounce, Filter filter){
        this.bounce = bounce;
        this.filter = filter;
        this.db = db;
        this.statement = db.getStatement();
        this.connection = db.getConnect();
    }

    public String whereClause(){
        Map<String, String> map = filter.getFilterArray();
        String query = "";
        if (map.size() != 0){
            query += "WHERE ";
            if (map.containsKey("dateRange")){
                String[] two = map.get("dateRange").split("+");
                query += "Date between '" + two[0] + " 00:00:00' and '" + two[1] + "23:59:00'";
            }
            if (map.containsKey("gender")){
                if (!query.endsWith("Impression")){
                    query += " AND ";
                }
                query += "Gender = " + map.get("gender");
            }
            if (map.containsKey("age")){
                if (!query.endsWith("Impression")){
                    query += " AND ";
                }
                query += "Age = " + map.get("age");
            }
            if (map.containsKey("income")){
                if (!query.endsWith("Impression")){
                    query += " AND ";
                }
                query += "Income = " + map.get("income");
            }
            if (map.containsKey("context")){
                if (!query.endsWith("Impression")){
                    query += " AND ";
                }
                query += "Context = " + map.get("context");
            }
            query += ";";
        }return query;
    }


    public int calImpression(){

        String query = "SELECT count(*) FROM Impression ";
        query += whereClause();

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
