package Tests;

import Model.Database;
import Model.ImpressionParser;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class ImpressionParserTest {

    @Test
    public void testConstructorDbNull(){
        try {
            new ImpressionParser(null,"");
            fail();
        } catch (Exception e){
            if (e.getClass()!=(new NullPointerException().getClass())){
                fail();
            }
        }
    }

    @Test
    public void testNormalLoad(){
        try {
            Database db = new Database();
            db.connectToDatabase();
            ImpressionParser ip = new ImpressionParser(db, getTestPath());
            ip.run();
        } catch (Exception e){
            fail();
        }
    }

    /*
    @Test
    public void testLoadNormal(){
        Database db = new Database();
        db.connectToDatabase();
        System.out.println(getTestPath());
        File f = impressionLogNormalGen();
        System.out.println(f.getPath());
        ImpressionParser ip = new ImpressionParser(db,f.getParentFile().getPath());
        ip.run();
        f.delete();
    }
    */


    public File impressionLogNormalGen(){
        File f = new File(getTestPath()+"\\impression_log.csv");
        try {
            f.delete();
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Date,ID,Gender,Age,Income,Context,Impression Cost");
            for (int d = 1;d<29;d++) {
                for (int hour = 0; hour < 24; hour++) {
                    for (int min = 0; min < 60; min++) {
                        String minute;
                        if (min < 10) {
                            minute = "0" + min;
                        } else {
                            minute = Integer.toString(min);
                        }
                        String day;
                        if (d < 10) {
                            day = "0" + d;
                        } else {
                            day = Integer.toString(d);
                        }
                        bw.write(day + "-01-2015 " + hour + ":" + minute + ":01"+
                                "," + getID() +
                                "," + getGender() +
                                "," + getAgeRange() +
                                "," + getIncome() +
                                "," + getContext() +
                                "," + getImpressionCost());
                    }
                }
            }
            bw.flush();
            bw.close();
            return f;
        }   catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private String getTestPath(){
        return System.getProperty("user.dir") + "\\src\\Tests\\Test\\";
    }

    private String getAgeRange(){
        Random rnd = new Random();
        ArrayList<String> ls = new ArrayList<String>();
        ls.add("<25");
        ls.add("25-34");
        ls.add("35-44");
        ls.add("45-54");
        ls.add(">54");
        return ls.get(rnd.nextInt(ls.size()));
    }

    private String getContext(){
        Random rnd = new Random();
        ArrayList<String> ls = new ArrayList<String>();
        ls.add("Blog");
        ls.add("News");
        ls.add("Shopping");
        ls.add("Social Media");
        ls.add("Blog");
        return ls.get(rnd.nextInt(ls.size()));
    }

    private String getIncome(){
        Random rnd = new Random();
        ArrayList<String> ls = new ArrayList<String>();
        ls.add("High");
        ls.add("Medium");
        ls.add("Low");
        return ls.get(rnd.nextInt(ls.size()));
    }

    private String getGender(){
        Random rnd = new Random();
        ArrayList<String> ls = new ArrayList<String>();
        ls.add("Male");
        ls.add("Female");
        return ls.get(rnd.nextInt(ls.size()));
    }

    private String getID(){
        Random rnd = new Random();
        String id = "";
        for (int i = 0; i < 19;i++){
            id = id + rnd.nextInt(9);
        }
        return id;
    }

    private String getImpressionCost(){
        Random rnd = new Random();
        return "0.00"+ rnd.nextInt(9) + rnd.nextInt(9) + rnd.nextInt(9) + rnd.nextInt(9);
    }
}
