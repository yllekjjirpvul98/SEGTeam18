import Model.Model;
import View.View;
import Control.Controller;

/*
    Contains main method instantiates a copy of Model, View and Controller.
    Links the Model and View via the Controller.
 */

public class Start {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("Ad Auction Monitor");
        Controller control = new Controller(view, model);

        model.init(control);
        view.init(control);
    }
}
