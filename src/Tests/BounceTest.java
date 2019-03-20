package Tests;

import Model.Bounce;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BounceTest {
    Bounce testBounce;
    Map<String, Integer> testMap;

    @BeforeEach
    public void init(){
        testBounce = new Bounce(true,true);
        testBounce.setTime(10);
        testBounce.setNumOfPageVisited(5);
        testMap = testBounce.getBounceSettings();
    }

    @Test
    public void testTime(){
        assertEquals(10,testBounce.getTime());
    }

    @Test
    public void testNumOfPages(){
        assertEquals(5,testBounce.getNumOfPageVisited());
    }

    @Test
    public void TestTimesTable(){
        assertEquals(10,testMap.get("times"));
    }

    @Test
    public void TestNumPageTable(){
        assertEquals(5,testMap.get("numPage"));
    }

    @Test
    public void TestTimesNullTable(){
        testBounce.setTimeSet(false);
        testMap = testBounce.getBounceSettings();
        assertEquals(null,testMap.get("times"));
    }

    @Test
    public void TestNumPageNullTable(){
        testBounce.setNumPageSet(false);
        testMap = testBounce.getBounceSettings();
        assertEquals(null,testMap.get("numPage"));
    }
}
