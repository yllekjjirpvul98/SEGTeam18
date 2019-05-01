package Tests;

import Model.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DatabaseTest {
    Database db;

    @BeforeEach
    public void init(){
        db = new Database();
    }

    @Test
    public void testConnection(){
        db.connectToDatabase();
        assertEquals("conn0: url=jdbc:h2:mem: user=",db.getConnect().toString());
        try {
            assertEquals(false,db.getConnect().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStatement(){
        db.connectToDatabase();
        assertEquals("stat0",db.getStatement().toString());
    }
}
