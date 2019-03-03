package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //apply filter
    public String whereClause(){
        Map<String, String> map = filter.getFilterArray();
        String query = "";
        if (map.size() != 0){
            query += "WHERE ";
            if (map.containsKey("dateRange")){
                String[] two = map.get("dateRange").split("\\+");
                query += "Date between '" + two[0] + " 00:00:00' and '" + two[1] + "23:59:00'";
            }
            if (map.containsKey("gender")){
                if (!query.endsWith("WHERE ")){
                    query += " AND ";
                }
                query += "Gender = " + map.get("gender");
            }
            if (map.containsKey("age")){
                if (!query.endsWith("WHERE ")){
                    query += " AND ";
                }
                query += "Age = \"" + map.get("age") + "\"";
            }
            if (map.containsKey("income")){
                if (!query.endsWith("WHERE ")){
                    query += " AND ";
                }
                query += "Income = " + map.get("income");
            }
            if (map.containsKey("context")){
                if (!query.endsWith("WHERE ")){
                    query += " AND ";
                }
                query += "Context = " + map.get("context");
            }
            query += ";";
        }return query;
    }

    public String whereBounceClause(){
        Map<String, Integer> map = bounce.getBounceSettings();
        String query = "";
        if (map.size() != 0){
            query += "WHERE ";

            if (map.containsKey("times")){
                query += "DATEDIFF(second, EntryDate, ExitDate) == " + map.get("times");
            }
            if (map.containsKey("numPage")){
                if (!query.endsWith("Conversion")){
                    query += " AND ";
                }
                query += "PageViewed = " + map.get("numPage");
            }
            query += ";";
        }return query;
    }


    public int calImpression(){
        int count = 0;
        String query = "SELECT count(*) FROM Impression ";
        query += whereClause();
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return count;
    }

    public int calClicks(){
        String query = "SELECT count(*) FROM Click INNER JOIN ";
        String table = "CREATE TEMPORARY TABLE temp AS SELECT * FROM Impression " + whereClause();
        int count = 0;
        try {
            statement.execute(table);
            query += "temp ON Click.ID = temp.ID";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return count;
    }

    public int calUnique(){
        String query = "SELECT count(*) FROM (SELECT DISTINCT ID FROM Click)";
        int count = 0;
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return count;
    }

    public int calBounce(){
        int count = 0;
        String query = "SELECT count(*) FROM Server INNER JOIN ";
        String table = "CREATE TEMPORARY TABLE tempI AS SELECT * FROM Impression" + whereBounceClause();

        try{
            statement.execute(table);
            query += "temp ON Server.ID = tempI.ID";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public int calConversion(){
        int count = 0;
        String query = "SELECT count(*) FROM Server WHERE Conversion == \"Yes\" AND";
        query += whereClause();

        try{
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public float calClickCost(){
        float clickCost = 0;

        String clickQuery = "SELECT sum(clickCost) FROM Click";
        clickQuery += whereClause();
        try{
            ResultSet rs = statement.executeQuery(clickQuery);
            while(rs.next()){
                clickCost = rs.getInt("sum(clickCost)");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return clickCost;
    }

    public float calTotal(){
        float impressionCost = 0;

        String impQuery = "SELECT sum(ImpressionCost) FROM Impression";
        impQuery += whereClause();
        try{
            ResultSet rsi = statement.executeQuery(impQuery);
            while(rsi.next()){
                impressionCost = rsi.getInt("sum(ImpressionCost)");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return calClickCost()+impressionCost;
    }

    public double calCTR(){
        return calClicks()/calImpression();
    }

    public double calCPA(){
        return calTotal()/calConversion();
    }

    public double calCPC(){
        return calClickCost()/calClicks();
    }

    public double calCPM(){
        return calTotal()/(1000*calImpression());
    }

    public double calBounceRate(){
        return calBounce()/calClicks();
    }
}
