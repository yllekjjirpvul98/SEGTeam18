package Model;

import java.util.HashMap;
import java.util.Map;

/*
    Class that is responsible for configuring the bounce settings.
 */
public class Bounce {

    //default configurations
    private int time = 3;
    private int numOfPageVisited = 3;
    private boolean timeSet = false;
    private boolean numPageSet = false;

    public Bounce(boolean timeSet, boolean numPageSet){
        this.timeSet = timeSet;
        this.numPageSet = numPageSet;
    }

    //Get all Bounce configurations
    public Map<String, Integer> getBounceSettings(){
        Map map = new HashMap<String, Integer>();
        if (timeSet){
            map.put("times", getTime());
        }
        if (numPageSet){
            map.put("numPage", getNumOfPageVisited());
        }return  map;
    }

    //Getters and Setters
    public void setNumPageSet(boolean numPageSet) {
        this.numPageSet = numPageSet;
    }

    public void setTimeSet(boolean timeSet) {
        this.timeSet = timeSet;
    }

    public void setNumOfPageVisited(int numOfPageVisited) {
        this.numOfPageVisited = numOfPageVisited;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNumOfPageVisited() {
        return numOfPageVisited;
    }

    public int getTime() {
        return time;
    }

}