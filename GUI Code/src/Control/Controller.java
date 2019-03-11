package Control;

import Model.Model;
import View.View;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model){
        this.model = model;
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }










}
