package Model;

/*
    Class implements functionality of Settings.
    Store user selections.
 */
public class Settings {

    private boolean colorBlind = false;
    private boolean largeText = false;

    Settings(boolean colorBlind, boolean largeText) {
        this.colorBlind = colorBlind;
        this.largeText = largeText;
    }

    //Getters and Setters
    public void setColorBlind(boolean colorBlind) {
        this.colorBlind = colorBlind;
    }

    public void setLargeText(boolean largeText) {
        this.largeText = largeText;
    }

    public boolean getColorBlind(){
        return colorBlind;
    }

    public boolean getLargeText(){
        return largeText;
    }

}
