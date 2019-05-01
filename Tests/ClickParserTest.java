package Tests;

import Model.ClickParser;
import Model.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ClickParserTest {

    @Test
    public void testConstructorDbNull(){
        try {
            new ClickParser(null,"");
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
            ClickParser ip = new ClickParser(db, getTestPath());
            ip.run();
        } catch (Exception e){
            fail();
        }
    }

    private String getTestPath(){
        return System.getProperty("user.dir") + "\\src\\Tests\\Test\\";
    }
}
