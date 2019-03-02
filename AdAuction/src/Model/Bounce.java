package Model;

import java.util.HashMap;
import java.util.Map;

public class Bounce {

    int time;
    int numOfPageVisited;
    boolean timeSet = false;
    boolean numPageSet = false;

    Bounce(boolean timeSet, boolean numPageSet){
        this.timeSet = timeSet;
        this.numPageSet = numPageSet;
    }

    public Map<String, Integer> getBounceSettings(){
        Map map = new HashMap<String, Integer>();
        if (timeSet){
            map.put("times", getTime());
        }else if (numPageSet){
            map.put("numPage", getNumOfPageVisited());
        }return  map;
    }

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
