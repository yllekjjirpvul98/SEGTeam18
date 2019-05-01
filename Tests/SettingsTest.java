package Tests;

import Model.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingsTest {

    Settings setting;

    @BeforeEach
    public void init(){
        setting = new Settings(true,true);
    }

    @Test
    public void testConstructor(){
        assertEquals(true, setting.getColorBlind());
        assertEquals(true, setting.getLargeText());
    }

    @Test
    public void testColorBlind(){
        setting.setColorBlind(false);
        assertEquals(false,setting.getColorBlind());
    }

    @Test
    public void testLargeText(){
        setting.setLargeText(false);
        assertEquals(false, setting.getLargeText());
    }
}
