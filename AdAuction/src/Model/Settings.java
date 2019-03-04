package Model;

public class Settings {

    boolean colorBlind = false;
    boolean largeText = false;

    Settings(boolean colorBlind, boolean largeText) {
        this.colorBlind = colorBlind;
        this.largeText = largeText;
    }

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
