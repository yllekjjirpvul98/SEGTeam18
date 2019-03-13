package Model;

import java.sql.*;
import java.util.Map;

import static Model.Filter.Context.News;

public class Calculation {

    private Database db;
    private Filter filter;
    private Bounce bounce;
    private Statement statement;
    private Connection connection;

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

        String query = "SELECT count(ID) FROM Impression ";

//        String query = "SELECT count(*) FROM Impression ";

        query += whereClause();
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(ID)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return count;
    }

    public int calClicks(){

        String query = "SELECT count(Click.ID) FROM Click INNER JOIN Impression ON Impression.ID = Click.ID ";
//        String query = "SELECT count(*) FROM click_new INNER JOIN Impression ON Impression.ID = click_new.ID ";

//        String table = "CREATE TEMPORARY TABLE temp AS SELECT I FROM Impression ";
        int count = 0;
        try {
//            statement.execute("DROP TABLE IF EXISTS temp;");
//            statement.execute(table);

//            query += "Impression ON Click.ID = temp.ID ";
            query += whereClause();
//            System.out.println(query);

//            query += "Impression ON Click.ID = temp.ID ";
            query += whereClause();
            System.out.println(query);

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("count(Click.ID)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return count;
    }

    public int calUnique(){

        String query = "SELECT count(*) FROM Click INNER JOIN Impression ON Impression.ID = Click.ID ";
        query += whereClause();
        query = query.substring(0, query.length()-1);
        query +=  " group by Click.ID;";
//        String table = "CREATE TEMPORARY TABLE temp2 AS SELECT DISTINCT ID FROM Click ";

//        String query = "SELECT count(*) FROM temp2 INNER JOIN Impression ON Impression.ID = temp2.ID ";
        query += whereClause();
        String table = "CREATE TEMPORARY TABLE temp2 AS SELECT DISTINCT ID FROM click_new ";

        int count = 0;
        try {
//            statement.execute("DROP TABLE IF EXISTS temp2;");
//            statement.execute(table);
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
//        String table = "CREATE TEMPORARY TABLE tempI AS SELECT * FROM Impression";

//        String query = "SELECT count(*) FROM server_new INNER JOIN ";
//        String table = "CREATE TEMPORARY TABLE tempI AS SELECT * FROM Impression";


        try{
//            statement.execute("DROP TABLE IF EXISTS tempI;");
//            statement.execute(table);

            query += "Impression ON Server.ID = Impression.ID ";

            query += "Impression ON server_new.ID = Impression.ID ";


            if(map.size()!=0) {
                if (map.containsKey("times")) {
                    String tableB = "CREATE TEMPORARY TABLE tempTime AS SELECT ID, TIME_TO_SEC(DATEDIFF(EntryDate, ExitDate)) =  "
                            + map.get("times") + " FROM server_new";
                    statement.execute("DROP TABLE IF EXISTS tempTime;");
                    statement.execute(tableB);
                    query += " INNER JOIN tempTime ON server_new.ID = tempTime.ID";
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
//        String table = "CREATE TEMPORARY TABLE tempImp AS SELECT * FROM Impression ";

//        String query = "SELECT count(*) FROM server_new INNER JOIN ";
//        String table = "CREATE TEMPORARY TABLE tempImp AS SELECT * FROM Impression ";


        try{
//            statement.execute("DROP TABLE IF EXISTS tempImp;");
//            statement.execute(table);

            query += "Impression ON Server.ID = Impression.ID ";

            query += "Impression ON server_new.ID = Impression.ID ";

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

    public float calClickCost() {
        float clickCost = 0;

        String clickQuery = "SELECT sum(clickCost) FROM Click INNER JOIN ";

        try {
            clickQuery += "Impression ON Click.ID = Impression.id ";

//        String clickQuery = "SELECT sum(clickCost) FROM click_new INNER JOIN ";
            String table = "CREATE TEMPORARY TABLE tempImpression AS SELECT * FROM Impression ";
                statement.execute("DROP TABLE IF EXISTS tempImpression;");
                statement.execute(table);
                clickQuery += "tempImpression ON Click.ID = tempImpression.ID ";

                clickQuery += whereClause();

                ResultSet rs = statement.executeQuery(clickQuery);
                while (rs.next()) {
                    clickCost = rs.getInt("sum(clickCost)");
                }
            } catch (SQLException e) {
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

        public static void main(String[] args){
            Database db = new Database();
            db.connectToDatabase();

            Filter filter = new Filter(false, false, false, false, false);
            Bounce bounce = new Bounce(false, false);
            Settings settings = new Settings(false, false);
            Calculation cal = new Calculation(db, bounce, filter);
            filter.setAgeSelected(true);
            filter.setAge("<25");
            filter.setContextSelected(true);
            filter.setContext(News);
            filter.setGenderSelected(true);
            filter.setGender("Female");
            filter.setDateRangeSelected(true);
            filter.setdateLowerRange(Date.valueOf("2015-01-01"));
            filter.setDateUpperRange(Date.valueOf("2015-01-02"));
            filter.setIncomeSelected(true);
            filter.setIncome("High");

            bounce.setTimeSet(true);
            bounce.setNumPageSet(true);
            bounce.setTime(30);
            bounce.setNumOfPageVisited(4);

            float starttime = System.nanoTime();
            float time = System.nanoTime();
            System.out.println(cal.calImpression());
            float time_diff = System.nanoTime() - time;
            time = System.nanoTime();
            System.out.println("calImpression():" + time_diff/1_000_000_000);

            System.out.println(cal.calClicks());
            time_diff = System.nanoTime() - time;
            time = System.nanoTime();
            System.out.println("calClicks():" + time_diff/1_000_000_000);

            System.out.println(cal.calUnique());
            time_diff = System.nanoTime() - time;
            time = System.nanoTime();
            System.out.println("calUnique():" + time_diff/1_000_000_000);

            System.out.println(cal.calBounce());
            time_diff = System.nanoTime() - time;
            time = System.nanoTime();
            System.out.println("calBounce():" + time_diff/1_000_000_000);

            System.out.println(cal.calConversion());
            time_diff = System.nanoTime() - time;
            time = System.nanoTime();
            System.out.println("calConversion():" + time_diff/1_000_000_000);

            System.out.println(cal.calTotal());
            time_diff = System.nanoTime() - time;
            time = System.nanoTime();
            System.out.println("calTotal():" + time_diff/1_000_000_000);
            double endtime = System.nanoTime()-starttime;
            System.out.println("Total time : " + endtime/1_000_000_000);
        }
    }
