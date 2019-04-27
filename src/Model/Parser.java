package Model;

/*
    Interface for the parsers, which extends Runnable such that we can multi-thread and speed up the parse process
 */

public interface Parser extends Runnable {

    public void loadDatabase();

}