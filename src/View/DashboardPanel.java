package View;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private View window;
    private  FilterPanel filterPanel;
    private DataPanel dataPanel;
    private GraphPanel graphPanel;

    public DashboardPanel(View window){
        this.window = window;
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init() {
        //  ---- Creating Components ----
        filterPanel = new FilterPanel(this);
        dataPanel = new DataPanel(this);
        graphPanel = new GraphPanel(this);


        JLabel title = new JLabel("DashBoard");
        title.setFont(window.getHeadingFont());
        title.setForeground(window.getHeadingColour());

        JButton logoutBut = new JButton("Logout");
        logoutBut.setFont(window.getButtonBigFont());
        logoutBut.setBackground(window.getUnhighlightColor());

        logoutBut.addActionListener(e -> window.changePanel("loginPanel"));


        // North Panel --- Logout & Title
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        JPanel row1n = new JPanel();
        row1n.setLayout(new BoxLayout(row1n, BoxLayout.LINE_AXIS));
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1n.add(logoutBut);
        row1n.add(Box.createHorizontalGlue());
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));

        JPanel row2n = new JPanel();
        row2n.setLayout(new BoxLayout(row2n, BoxLayout.LINE_AXIS));
        row2n.add(Box.createHorizontalGlue());
        row2n.add(title);
        row2n.add(Box.createHorizontalGlue());

        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(row1n);
        northPanel.add(row2n);
        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));


        this.add(northPanel, BorderLayout.NORTH);
        this.add(filterPanel, BorderLayout.SOUTH);
        this.add(dataPanel, BorderLayout.WEST);
        this.add(graphPanel, BorderLayout.CENTER);
    }

    public View getWindow(){
        return window;
    }

    public DataPanel getDataPanel(){
        return dataPanel;
    }
}