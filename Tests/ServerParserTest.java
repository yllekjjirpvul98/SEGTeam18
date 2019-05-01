package Tests;

import Model.Database;
import Model.ServerParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ServerParserTest {

    @Test
    public void testConstructorDbNull(){
        try {
            new ServerParser(null,"");
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
        ServerParser ip = new ServerParser(db,getTestPath());
        ip.run();
        } catch (Exception e){
            fail();
        }
    }

    private String getTestPath(){
        return System.getProperty("user.dir") + "\\src\\Tests\\Test\\";
    }
}
