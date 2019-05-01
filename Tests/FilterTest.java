package Tests;

import Model.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    Filter filter;
    HashMap<String, ArrayList<String>> mapTest;

    @BeforeEach
    public void init(){
       filter = new Filter(true,true,true,true,true);
       filter.setContext(Filter.Context.News);
       filter.setIncome("test");
       filter.setGender("test");
       filter.setAge("test");
       filter.setdateLowerRange(new Date(10));
       filter.setDateUpperRange(new Date(20));
       mapTest = (HashMap<String, ArrayList<String>>) filter.getFilterArray();
    }

    //tests on boolean gets
    @Test
    public void testAgeSelected(){
        assertEquals(true,filter.getAgeSelected());
    }

    @Test
    public void testContextSelected(){
        assertEquals(true,filter.getContextSelected());
    }

    @Test
    public void testDateRangeSelected(){
        assertEquals(true,filter.getDateRangeSelected());
    }

    @Test
    public void testGenderSelected(){
        assertEquals(true,filter.getGenderSelected());
    }

    @Test
    public void testIncomeSelected(){
        assertEquals(true,filter.getIncomeSelected());
    }

    //tests on the other get/setters
    @Test
    public void testAge(){
        assertEquals("test",filter.getAge().get(0));
    }

    @Test
    public void testGender(){
        assertEquals("test",filter.getGender().get(0));
    }

    @Test
    public void testIncome(){
        assertEquals("test",filter.getIncome().get(0));
    }

    @Test
    public void TestContext(){
        assertEquals(Filter.Context.News,filter.getContext().get(0));
    }

    @Test
    public void TestAgeMap(){
        assertEquals("test",mapTest.get("age").get(0));
    }

    @Test
    public void TestContextMap(){
        assertEquals("News",mapTest.get("context").get(0));
    }

    @Test
    public void testIncomeMap(){
        assertEquals("test",mapTest.get("income").get(0));
    }

    @Test
    public void TestGenderMap(){
        assertEquals("test",mapTest.get("gender").get(0));
    }

    @Test
    public void TestDateMap(){
        assertEquals(new Date(10) + "+" + new Date(20),mapTest.get("dateRange").get(0));
    }
}
