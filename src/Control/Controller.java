package Control;

import Model.Model;
import View.GuiFrame;

public class Controller {

    private GuiFrame view;
    private Model model;

    public Controller(GuiFrame view, Model model){
        this.model = model;
        this.view = view;
    }



}
