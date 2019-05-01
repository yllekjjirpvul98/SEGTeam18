package Tests;

import Control.Controller;
import Model.*;
import View.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    Model model;
    @BeforeEach
    public void init(){
        model = new Model();
    }

    @Test
    public void testConstructorNull(){
        assertNull(model.getSettings());
        assertNull(model.getFilter());
        assertNull(model.getCal());
        assertNull(model.getBounce());
        assertNull(model.getDb());
    }

    @Test
    public void testFilter(){
        model.init(new Controller(new View("Ad Auction Monitor"),model));
        assertTrue(filterEquals(new Filter(false, false, false, false, false),model.getFilter()));
    }

    @Test
    public void testSettings(){
        model.init(new Controller(new View("Ad Auction Monitor"),model));
        assertTrue(settingsEquals(new Settings(false, false),model.getSettings()));
    }

    @Test
    public void testBounce(){
        model.init(new Controller(new View("Ad Auction Monitor"),model));
        assertTrue(bounceEquals(new Bounce(false,false),model.getBounce()));
    }

    @Test
    public void testDbNullness(){
        model.init(new Controller(new View("Ad Auction Monitor"),model));
        assertNotNull(model.getDb());
    }

    private boolean bounceEquals(Bounce b1, Bounce b2){
        if (!(b1.getBounceSettings().equals(b2.getBounceSettings()))){
            return false;
        }
        if (b1.getNumOfPageVisited() != b2.getNumOfPageVisited()){
            return false;
        }
        if (b1.getTime() != b2.getTime()){
            return false;
        }
        return true;
    }

    private boolean filterEquals(Filter f1,Filter f2){
        String f1null, f2null;
        boolean fnull = false;
        if (f1.getFilterArray().equals(f2.getFilterArray()) &&
                (f1.getAgeSelected() == f2.getAgeSelected()) &&
                (f1.getAge().equals(f2.getAge())) &&
                (f1.getContextSelected() == f2.getContextSelected()) &&
                (f1.getDateRangeSelected() == f2.getDateRangeSelected()) &&
                (f1.getContext().equals(f2.getContext())) &&
                (f1.getGenderSelected().equals(f2.getGenderSelected())) &&
                (f1.getGenderSelected() == f2.getGenderSelected()) &&
                (f1.getIncomeSelected() == f2.getIncomeSelected()) &&
                (f1.getIncome().equals(f2.getIncome()))){
            try {
                f1null = f1.getDateLowerRange();
            } catch (NullPointerException e) {
                System.out.println("");
                f1null = null;
                fnull = true;
            }
            try {
                f2null = f2.getDateLowerRange();
            } catch (NullPointerException e) {
                f2null = null;
                fnull = true;
            }
            if (fnull) {
                if (((f1null == null)&&(f2null == null))){
                    return true;
                }
            } else {
                if (f1null.equals(f2null)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean settingsEquals(Settings s1,Settings s2){
        if ((s1.getColorBlind() == s2.getColorBlind())&&
                (s1.getLargeText() == s2.getLargeText())){
            return true;
        }
        return false;
    }
}