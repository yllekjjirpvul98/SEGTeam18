package Tests;

import Model.Database;
import Model.PopulateDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class PopulateDatabaseTest {
    @Test
    public void testConstructor(){
        try {
            PopulateDatabase db = new PopulateDatabase(new Database(), getTestPath());
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void testNullConstructor(){
        try {
            new PopulateDatabase(null, getTestPath());
        } catch (Exception e){
            if ((e.getClass()) != (new NullPointerException()).getClass()){
                fail();
            }
        }
    }
    private String getTestPath(){
        return System.getProperty("user.dir") + "\\src\\Tests\\Test\\";
    }
}
