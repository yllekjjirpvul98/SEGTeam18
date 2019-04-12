import Model.Model;
import View.View;
import Control.Controller;

public class Start {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("Ad Auction Monitor");
        Controller control = new Controller(view, model);

        model.init(control);
        view.init(control);
    }
}
