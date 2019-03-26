import Model.Model;
import View.View;

public class Start {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View("Ad Auction Monitor");
        Control.Controller control = new Control.Controller(view, model);

        model.init(control);
        view.init(control);
    };
}
