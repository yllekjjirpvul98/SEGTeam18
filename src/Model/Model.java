package Model;

import Control.Controller;

import java.sql.Date;

import static Model.Filter.Context.News;

public class Model {

    private Controller control;

    public void setControl(Controller control){
        this.control = control;
        init();
    }

    private Calculation cal;

    public Calculation getCal() {
        return cal;
    }
    public void init(){
        Database db = new Database();
        db.connectToDatabase();
        Filter filter = new Filter(false, false, false, false, false);
        Bounce bounce = new Bounce(false, false);
        Settings settings = new Settings(false, false);
        cal = new Calculation(db, bounce, filter);
    }
    public Model(){};

//
//        filter.setAgeSelected(true);
//        filter.setAge("<25");
//        filter.setContextSelected(true);
//        filter.setContext(News);
//        filter.setGenderSelected(true);
//        filter.setGender("Female");
//        filter.setDateRangeSelected(true);
//        filter.setdateLowerRange(Date.valueOf("2015-01-01"));
//        filter.setDateUpperRange(Date.valueOf("2015-01-02"));
//        filter.setIncomeSelected(true);
//        filter.setIncome("High");
//
//        bounce.setTimeSet(true);
//        bounce.setNumPageSet(true);
//        bounce.setTime(30);
//        bounce.setNumOfPageVisited(4);
//
//        float time = System.nanoTime();
//        System.out.println(cal.calImpression());
//        float time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calImpression():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calClicks());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calClicks():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calUnique());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calUnique():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calBounce());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calBounce():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calConversion());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calConversion():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calTotal());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calTotal():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calCTR());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calCTR():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calCTR());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calCTR:" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calCPA());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calCPA():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calCPC());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calCPC():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calCPM());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calCPM():" + time_diff/1_000_000_000);
//
//        System.out.println(cal.calBounceRate());
//        time_diff = System.nanoTime() - time;
//        time = System.nanoTime();
//        System.out.println("calBounceRate():" + time_diff/1_000_000_000);
//    }
}
