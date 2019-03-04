import Model.Model;

public class Start {

    public static void main(String[] args) {
        Model model = new Model();
        View.GuiFrame view = new View.GuiFrame("Ad Auction Monitor");
        Control.Controller control = new Control.Controller(view, model);

        model.setControl(control);
        view.setControl(control);

        if (!(view.isActive())){
            control.getModel().getDb().closeDatabase();
        }
    };
}
