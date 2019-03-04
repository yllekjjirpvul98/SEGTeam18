package Control;

import Model.Model;
import View.GuiFrame;

public class Controller {

    public GuiFrame getView() {
        return view;
    }

    private GuiFrame view;

    public Model getModel() {
        return model;
    }

    private Model model;

    public Controller(GuiFrame view, Model model){
        this.model = model;
        this.view = view;
    }




}
