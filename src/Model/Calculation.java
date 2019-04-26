package Model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calculation {

    private Database db;
    private Filter filter;
    private Bounce bounce;
    private Statement statement;
    private Connection connection;

    Calculation(Database db, Bounce bounce, Filter filter) {
        this.bounce = bounce;
        this.filter = filter;
        this.db = db;
        this.statement = db.getStatement();
        this.connection = db.getConnect();
    }

    //apply filter
    public String whereClause() {
        Map<String, ArrayList<String>> map = filter.getFilterArray();
        String query = "";
        if (map.size() != 0) {
            query += "WHERE ";
            if (map.containsKey("dateRange")) {
                String[] two = map.get("dateRange").get(0).split("\\+");
                query += "ImpressionDate between '" + two[0] + " 00:00:00' and '" + two[1] + " 23:59:00'";
            }
            if (map.containsKey("gender")) {
                if (!query.endsWith("WHERE ")) {
                    query += " AND ";
                }
                if (map.get("gender").size() == 1) {
                    query += "Gender = '" + map.get("gender").get(0) + "'";
                }
                if (map.get("gender").size() != 1) {
                    query += "(";
                    query += "Gender = '" + map.get("gender").get(0) + "'";

                    for (int i = 0; i < map.get("gender").size(); i++) {
                        if(i == 0)
                            continue;
                        else
                            query += " OR Gender = '" + map.get("gender").get(i) + "'";
                    }
                    query += ") ";
                }
            }

            if (map.containsKey("age")) {
                if (!query.endsWith("WHERE ")) {
                    query += " AND ";
                }
                if (map.get("age").size() == 1) {
                    query += "Age = '" + map.get("age").get(0) + "'";
                }
                if (map.get("age").size() != 1) {
                    query += "(";
                    query += "Age = '" + map.get("age").get(0) + "'";

                    for (int i = 0; i < map.get("age").size(); i++) {
                        if(i == 0)
                            continue;
                        else
                            query += " OR Age = '" + map.get("age").get(i) + "'";
                    }
                    query += ") ";
                }
            }

            if (map.containsKey("income")) {
                if (!query.endsWith("WHERE ")) {
                    query += " AND ";
                }
                if (map.get("income").size() == 1) {
                    query += "Income = '" + map.get("income").get(0) + "'";
                }
                if (map.get("income").size() > 1) {
                    query += "(";
                    query += "Income = '" + map.get("income").get(0) + "'";

                    for (int i = 0; i < map.get("income").size(); i++) {
                        if(i == 0)
                            continue;
                        else
                            query += " OR Income = '" + map.get("income").get(i) + "'";
                    }
                    query += ") ";
                }
            }
            if (map.containsKey("context")) {
                if (!query.endsWith("WHERE ")) {
                    query += " AND ";
                }
                if (map.get("context").size() == 1) {
                    query += "Context = '" + map.get("context").get(0) + "'";
                }
                if (map.get("context").size() > 1) {
                    query += "(";
                    query += "Context = '" + map.get("context").get(0) + "'";

                    for (int i = 0; i < map.get("context").size(); i++) {
                        if(i == 0)
                            continue;
                        else
                            query += " OR Context = '" + map.get("context").get(i) + "'";
                    }
                    query += ") ";
                }
            }
            query += ";";
        }
        return query;
    }

    public int calImpression() {
        int count = 0;
        String query = "SELECT count(ID) FROM Impression ";
        query += whereClause();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("count(ID)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int calClicks() {

        String query = "SELECT count(Click.ID) FROM Click INNER JOIN Impression ON Impression.ID = Click.ID ";

        int count = 0;
        try {
            query += whereClause();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("count(Click.ID)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int calUnique() {

        String query = "SELECT COUNT(DISTINCT Click.ID) AS id_count FROM Click INNER JOIN Impression ON Impression.ID = Click.ID ";
        query += whereClause();

        int count = 0;
        try {
//            statement.execute("DROP TABLE IF EXISTS temp2;");
//            statement.execute(table);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("id_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int calBounce() {
        Map<String, Integer> map = bounce.getBounceSettings();

        int count = 0;

        String query = "SELECT count(*) FROM Server INNER JOIN ";

        try {

            query += "Impression ON Server.ID = Impression.ID ";
            query += whereClause();

            if (map.size() != 0) {
                if (map.containsKey("times")) {
//                    String tableB = "CREATE TEMPORARY TABLE tempTime AS SELECT ID, (DATEDIFF('second',EntryDate, ExitDate)) =  "
//                            + map.get("times") + " FROM Server";
//                    statement.execute("DROP TABLE IF EXISTS tempTime;");
//                    statement.execute(tableB);
//                    query += " INNER JOIN tempTime ON Server.ID = tempTime.ID";
                    query = query.replaceFirst(";", "");
                    String tableB = "AND EntryDate < ExitDate - INTERVAL '" + map.get("times") + "' SECOND";
                    query += tableB;
                }
            }

            if (map.containsKey("numPage")) {
                query += " AND PageViewed = " + map.get("numPage") + ";";
            }

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int calConversion() {
        int count = 0;

        String query = "SELECT count(*) FROM Server INNER JOIN ";

        try {
            query += "Impression ON Server.ID = Impression.ID ";

            query += whereClause();
            query = query.replaceFirst(";", "");
            query += " AND Conversion = 'Yes'";


            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public float calClickCost() {
        float clickCost = 0;

        String clickQuery = "SELECT sum(clickCost) FROM Click INNER JOIN ";

        try {
            clickQuery += "Impression ON Click.ID = Impression.id ";
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


    public float calTotal() {
        float impressionCost = 0;

        String impQuery = "SELECT sum(ImpressionCost) FROM Impression ";
        impQuery += whereClause();

        try {
            ResultSet rsi = statement.executeQuery(impQuery);
            while (rsi.next()) {
                impressionCost = rsi.getInt("sum(ImpressionCost)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calClickCost() + impressionCost;
    }

    public double calCTR() {
        return calClicks() / calImpression();
    }

    public double calCPA() {
        return calTotal() / calConversion();
    }

    public double calCPC() {
        return calClickCost() / calClicks();
    }

    public double calCPM() {
        return calTotal() / (1000 * calImpression());
    }

    public double calBounceRate() {
        return calBounce() / calClicks();
    }

    public Map<String, Double> getTimeG(String metric, String timeG) {
        Map<String, Double> granularity = new LinkedHashMap<>();

        /*
            timeG : year => return String in the format "2019", "2020" ...
            timeG : month => return String in the format "2015 1", "2015 2" ...
            timeG : date => return String in the format "2015-01-01"...
            timeG : week => return String in the format "2015 0", "2015 1" ... representing week of the year
            timeG : hour => return String in the format "2015-01-02 8", "2015-01-02 7" representing hour of the day
         */


        if (metric.equals("Impression")) {
            String query = "SELECT count(*), " + timeG + "(ImpressionDate) AS Granularity FROM Impression ";
            query += whereClause();
            query = query.replaceFirst(";", " ");
            query += "GROUP BY Granularity ORDER BY Granularity";
            if (timeG.equals("hour")) {
                query = query.replaceFirst("hour\\(ImpressionDate\\)", "concat(date(ImpressionDate),\' \', hour(ImpressionDate))");
            }
            if (timeG.equals("month")) {
                query = query.replaceFirst("month\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', month(ImpressionDate))");
            }
            if (timeG.equals("week")) {
                query = query.replaceFirst("week\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', week(ImpressionDate))");
            }
            try {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String date = rs.getString("Granularity");
                    double datapoint = rs.getInt("count(*)");
                    granularity.put(date, datapoint);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (metric.equals("Clicks")) {
            String query = "SELECT count(Click.ID), " + timeG + "(ImpressionDate) AS Granularity FROM Click INNER JOIN Impression ON Impression.ID = Click.ID ";

            try {
                query += whereClause();
                query = query.replaceFirst(";", "");
                query += " GROUP BY Granularity ORDER BY Granularity";
                if (timeG.equals("hour")) {
                    query = query.replaceFirst("hour\\(ImpressionDate\\)", "concat(date(ImpressionDate),\' \', hour(ImpressionDate))");
                }
                if (timeG.equals("month")) {
                    query = query.replaceFirst("month\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', month(ImpressionDate))");
                }
                if (timeG.equals("week")) {
                    query = query.replaceFirst("week\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', week(ImpressionDate))");
                }

                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String date = rs.getString("Granularity");
                    double datapoint = rs.getInt("count(Click.ID)");
                    granularity.put(date, datapoint);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (metric.equals("Unique")) {
            String query = "SELECT COUNT(DISTINCT Click.ID) AS id_count, " + timeG + "(ImpressionDate) AS Granularity FROM Click INNER JOIN Impression ON Impression.ID = Click.ID ";
            query += whereClause();
            query = query.substring(0, query.length() - 1);
            query = query.replaceFirst(";", "");
            query += " group by Granularity ORDER BY Granularity";
            if (timeG.equals("hour")) {
                query = query.replaceFirst("hour\\(ImpressionDate\\)", "concat(date(ImpressionDate),\' \', hour(ImpressionDate))");
            }
            if (timeG.equals("month")) {
                query = query.replaceFirst("month\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', month(ImpressionDate))");
            }
            if (timeG.equals("week")) {
                query = query.replaceFirst("week\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', week(ImpressionDate))");
            }
            try {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String date = rs.getString("Granularity");
                    double datapoint = rs.getInt("id_count");
                    granularity.put(date, datapoint);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (metric.equals("Bounce")) {
            Map<String, Integer> map = bounce.getBounceSettings();

            String query = "SELECT count(*), " + timeG + "(ImpressionDate) AS Granularity FROM Server INNER JOIN ";

            try {

                query += "Impression ON Server.ID = Impression.ID ";
                query += whereClause();

                if (map.size() != 0) {
                    if (map.containsKey("times")) {
//                        String tableB = "CREATE TEMPORARY TABLE tempTime AS SELECT ID, (DATEDIFF('second',EntryDate, ExitDate)) =  "
//                                + map.get("times") + " FROM Server";
//                        statement.execute("DROP TABLE IF EXISTS tempTime;");
//                        statement.execute(tableB);
//                        query += " INNER JOIN tempTime ON Server.ID = tempTime.ID";
                        query = query.replaceFirst(";", "");
                        String tableB = " AND EntryDate < ExitDate - INTERVAL '" + map.get("times") + "' SECOND";
                        query += tableB;
                    }
                }

                if (map.containsKey("numPage")) {

                    query += " AND PageViewed = " + map.get("numPage");
                }
                if (timeG.equals("hour")) {
                    query = query.replaceFirst("hour\\(ImpressionDate\\)", "concat(date(ImpressionDate),\' \', hour(ImpressionDate))");
                }
                if (timeG.equals("month")) {
                    query = query.replaceFirst("month\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', month(ImpressionDate))");
                }
                if (timeG.equals("week")) {
                    query = query.replaceFirst("week\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', week(ImpressionDate))");
                }

                query = query.replaceFirst(";", "");
                query += " GROUP BY Granularity ORDER BY Granularity;";
                System.out.println(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String date = rs.getString("Granularity");
                    double datapoint = rs.getInt("count(*)");
                    granularity.put(date, datapoint);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (metric.equals("Conversion")) {
            String query = "SELECT count(*) ," + timeG + "(ImpressionDate) AS Granularity FROM Server INNER JOIN ";

            try {
                query += "Impression ON Server.ID = Impression.ID ";

                query += whereClause();
                query = query.replaceFirst(";", "");
                query += " AND Conversion = 'Yes'";

                query += " GROUP BY Granularity ORDER BY Granularity";
                if (timeG.equals("hour")) {
                    query = query.replaceFirst("hour\\(ImpressionDate\\)", "concat(date(ImpressionDate),\' \', hour(ImpressionDate))");
                }
                if (timeG.equals("month")) {
                    query = query.replaceFirst("month\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', month(ImpressionDate))");
                }
                if (timeG.equals("week")) {
                    query = query.replaceFirst("week\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', week(ImpressionDate))");
                }
                System.out.println(query);

                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String date = rs.getString("Granularity");
                    double datapoint = rs.getInt("count(*)");
                    granularity.put(date, datapoint);
                }
                System.out.println(granularity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (metric.equals("TotalCost")) {

            String totalQuery = "SELECT (sum(clickCost)+sum(ImpressionCost)) AS sums, " + timeG + "(ImpressionDate) AS Granularity FROM Click INNER JOIN ";

            try {
                totalQuery += "Impression ON Click.ID = Impression.ID ";
                totalQuery += whereClause();

                totalQuery = totalQuery.replaceFirst(";", "");
                totalQuery += " GROUP BY Granularity ORDER BY Granularity";
                if (timeG.equals("hour")) {
                    totalQuery = totalQuery.replaceFirst("hour\\(ImpressionDate\\)", "concat(date(ImpressionDate),\' \', hour(ImpressionDate))");
                }
                if (timeG.equals("month")) {
                    totalQuery = totalQuery.replaceFirst("month\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', month(ImpressionDate))");
                }
                if (timeG.equals("week")) {
                    totalQuery = totalQuery.replaceFirst("week\\(ImpressionDate\\)", "concat(year(ImpressionDate),\' \', week(ImpressionDate))");
                }

                ResultSet rs = statement.executeQuery(totalQuery);

                while (rs.next()) {
                    String date = rs.getString("Granularity");
                    double datapoint = rs.getInt("sums");
                    granularity.put(date, datapoint);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (metric.equals("clickCost")) {
            String clickQuery = "SELECT sum(clickCost), " + timeG + "(ImpressionDate) AS Granularity FROM Click INNER JOIN ";

            try {
                clickQuery += "Impression ON Click.ID = Impression.id ";
                clickQuery += whereClause();

                clickQuery = clickQuery.replaceFirst(";", "");
                clickQuery += " GROUP BY Granularity ORDER BY Granularity";

                ResultSet rs = statement.executeQuery(clickQuery);
                while (rs.next()) {
                    String date = rs.getString("Granularity");
                    double datapoint = rs.getInt("sum(clickCost)");
                    granularity.put(date, datapoint);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (metric.equals("CTR")) {
            Map<String, Double> clicks = getTimeG("Clicks", timeG);
            Map<String, Double> impression = getTimeG("Impression", timeG);

            for (Map.Entry<String, Double> entry : impression.entrySet()) {

                if (clicks.containsKey(entry.getKey())) {
                    double c = clicks.get(entry.getKey());
                    double d = entry.getValue();
                    granularity.put(entry.getKey(), c / d);
                }
            }

        }
        if (metric.equals("CPA")) {
            Map<String, Double> total = getTimeG("TotalCost", timeG);
            Map<String, Double> conversion = getTimeG("Conversion", timeG);

            for (Map.Entry<String, Double> entry : conversion.entrySet()) {

                if (total.containsKey(entry.getKey())) {
                    double c = total.get(entry.getKey());
                    double d = entry.getValue();
                    granularity.put(entry.getKey(), c / d);
                }
            }

        }
        if (metric.equals("CPC")) {

            Map<String, Double> clicks = getTimeG("Clicks", timeG);
            Map<String, Double> clickCost = getTimeG("clickCost", timeG);

            for (Map.Entry<String, Double> entry : clickCost.entrySet()) {

                if (clicks.containsKey(entry.getKey())) {
                    double c = clicks.get(entry.getKey());
                    double d = entry.getValue();
                    granularity.put(entry.getKey(), c / d);
                }
            }

        }

        if (metric.equals("CPM")) {
            Map<String, Double> total = getTimeG("TotalCost", timeG);
            Map<String, Double> impression = getTimeG("Impression", timeG);

            for (Map.Entry<String, Double> entry : impression.entrySet()) {

                if (total.containsKey(entry.getKey())) {
                    double c = total.get(entry.getKey());
                    double d = entry.getValue();
                    granularity.put(entry.getKey(), c / (d * 1000));
                }
            }

        }

        if (metric.equals("BounceRate")) {
            Map<String, Double> bounce = getTimeG("Bounce", timeG);
            Map<String, Double> clicks = getTimeG("Clicks", timeG);

            for (Map.Entry<String, Double> entry : clicks.entrySet()) {

                if (bounce.containsKey(entry.getKey())) {
                    double c = bounce.get(entry.getKey());
                    double d = entry.getValue();
                    granularity.put(entry.getKey(), c / d);
                }
            }

        }

        return granularity;
    }
    public Map<Integer, Integer> getClickCost () {
        Map<Integer, Integer> granularity = new LinkedHashMap<>();
        Map<Integer, Integer> result = new LinkedHashMap<>();
        String clickQuery = "SELECT FLOOR(clickCost) AS RoundCost , count (Click.ID) FROM Click INNER JOIN ";

        try {
            clickQuery += "Impression ON Click.ID = Impression.id ";
            clickQuery += whereClause();

            clickQuery = clickQuery.replaceFirst(";", "");
            clickQuery += " GROUP BY RoundCost ORDER BY RoundCost";

            ResultSet rs = statement.executeQuery(clickQuery);
            while (rs.next()) {
                Integer cost = rs.getInt("RoundCost");
                Integer count = rs.getInt("count(Click.ID)");
                granularity.put(cost, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Integer i : granularity.keySet()){
            int key = i / 5;
            if (result.containsKey(key)){
                result.put(key, result.get(key)+granularity.get(i));
            }
            else result.put(key, granularity.get(i));
        }
        return result;
    }
}
