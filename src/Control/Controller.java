package Control;

import Model.Model;
import View.View;

/*
    The Controller class acts as the medium to communicate between View and Model.
 */

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
