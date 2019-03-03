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
                query += "ImpressionDate between '" + two[0] + " 00:00:00' and '" + two[1] + " 23:59:00'";
            }
            if (map.containsKey("gender")){
                if (!query.endsWith("WHERE ")){
                    query += " AND ";
                }
                query += "Gender = \"" + map.get("gender") + "\"";
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
                query += "Income = \"" + map.get("income") +"\"";
            }
            if (map.containsKey("context")){
                if (!query.endsWith("WHERE ")){
                    query += " AND ";
                }
                query += "Context = \"" + map.get("context") + "\"";
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
        String table = "CREATE TEMPORARY TABLE temp AS SELECT * FROM Impression ";
        int count = 0;
        try {
            statement.execute("DROP TABLE IF EXISTS temp;");
            statement.execute(table);
            query += "temp ON Click.ID = temp.ID ";
            query += whereClause();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return count;
    }

    public int calUnique(){
        String query = "SELECT count(*) FROM temp2 INNER JOIN Impression ON Impression.ID = temp2.ID ";
        query += whereClause();
        String table = "CREATE TEMPORARY TABLE temp2 AS SELECT DISTINCT ID FROM Click";
        int count = 0;
        try {
            statement.execute("DROP TABLE IF EXISTS temp2;");
            statement.execute(table);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return count;
    }

    public int calBounce(){
        Map<String, Integer> map = bounce.getBounceSettings();

        int count = 0;
        String query = "SELECT count(*) FROM Server INNER JOIN ";
        String table = "CREATE TEMPORARY TABLE tempI AS SELECT * FROM Impression";

        try{
            statement.execute("DROP TABLE IF EXISTS tempI;");
            statement.execute(table);
            query += "tempI ON Server.ID = tempI.ID";

            if(map.size()!=0) {
                if (map.containsKey("times")) {
                    String tableB = "CREATE TEMPORARY TABLE tempTime AS SELECT ID, TIME_TO_SEC(DATEDIFF(EntryDate, ExitDate)) =  "
                            + map.get("times") + " FROM Server";
                    statement.execute("DROP TABLE IF EXISTS tempTime;");
                    statement.execute(tableB);
                    query += " INNER JOIN tempTime ON Server.ID = tempTime.ID";
                }
            }
            query += " " + whereClause();
            if (map.containsKey("numPage")){
                query = query.replaceFirst(";", "");
                query += " AND PageViewed = \"" + map.get("numPage") + "\";";
            }

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
        String query = "SELECT count(*) FROM Server INNER JOIN ";
        String table = "CREATE TEMPORARY TABLE tempImp AS SELECT * FROM Impression ";

        try{
            statement.execute("DROP TABLE IF EXISTS tempImp;");
            statement.execute(table);
            query += "tempImp ON Server.ID = tempImp.ID ";
            query += whereClause();
            query = query.replaceFirst(";", "");
            query += " AND Conversion = \"Yes\"";


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
        String clickQuery = "SELECT sum(clickCost) FROM Click INNER JOIN ";
        String table = "CREATE TEMPORARY TABLE tempImpression AS SELECT * FROM Impression ";

        try{
            statement.execute("DROP TABLE IF EXISTS tempImpression;");
            statement.execute(table);
            clickQuery += "tempImpression ON Click.ID = tempImpression.ID ";
            clickQuery += whereClause();

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

        String impQuery = "SELECT sum(ImpressionCost) FROM Impression ";
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
