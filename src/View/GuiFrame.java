package View;

import Control.Controller;

import javax.swing.*;
import java.awt.*;

public class GuiFrame extends JFrame {

    public Controller getControl() {
        return control;
    }

    private Controller control;

    private Container window;

    private LoginPanel loginPanel;
    private HelpPanel helpPanel;
    private DashboardPanel dashboardPanel;


    public GuiFrame (String title) {
        super(title);
        window = this.getContentPane();
    }

    public void setControl(Controller control){
        this.control = control;
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        loginPanel = new LoginPanel(this);
        helpPanel = new HelpPanel(this);
        dashboardPanel = new DashboardPanel(this);

        this.add(loginPanel);
        this.pack();
        this.setSize( 1600, 1000);
    }

    public void changePanel(String panel){
        Dimension windowSize = new Dimension(this.getWidth(),this.getHeight());
        window.removeAll();
        this.repaint();

        if (panel.equals("loginPanel"))
            window.add(loginPanel);
        else if (panel.equals("helpPanel"))
            window.add(helpPanel);
        else if (panel.equals("dashboardPanel"))
            window.add(dashboardPanel);

        this.pack();
        this.setSize(windowSize);
    }

}
