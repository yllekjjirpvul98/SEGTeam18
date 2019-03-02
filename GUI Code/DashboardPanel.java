import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPanel extends JPanel {

    private GuiFrame window;

    JPanel centrePanel = new JPanel();

    private FilterPanel filterPanel = new FilterPanel(this);
    private DataPanel dataPanel = new DataPanel(this);
    private GraphPanel graphPanel = new GraphPanel(this);


    public DashboardPanel(GuiFrame window){
        this.window = window;
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){

        //  ---- Creating Components ----
        JLabel title = new JLabel("DashBoard");
        title.setFont(new Font("Courier", Font.BOLD, 60));
        title.setForeground(new Color(0x2865E1));

        JButton logoutBut = new JButton("Logout");
        logoutBut.setFont(new Font( "Courier", Font.BOLD, 20));
        logoutBut.setBackground(Color.lightGray);

        logoutBut.addActionListener(e -> window.changePanel("loginPanel"));

        JButton dataViewBut = new JButton("Data View");
        dataViewBut.setFont(new Font( "Courier", Font.BOLD, 20));
        dataViewBut.setBackground(new Color(0x76B8FF));

        JButton graphViewBut = new JButton("Graph View");
        graphViewBut.setFont(new Font( "Courier", Font.BOLD, 20));
        graphViewBut.setBackground(Color.lightGray);

        dataViewBut.addActionListener(e -> {
            dataViewBut.setBackground(new Color(0x76B8FF));
            graphViewBut.setBackground(Color.lightGray);
            this.changePanel("dataPanel");
        });

        graphViewBut.addActionListener(e -> {
            graphViewBut.setBackground(new Color(0x76B8FF));
            dataViewBut.setBackground(Color.lightGray);
            this.changePanel("graphPanel");
        });


        //  ---- Layout ----
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        JPanel row1n = new JPanel();
        row1n.setLayout(new BoxLayout(row1n, BoxLayout.LINE_AXIS));
        row1n.add(Box.createRigidArea(new Dimension(25, 0)));
        row1n.add(dataViewBut);
        row1n.add(Box.createRigidArea(new Dimension(25,0)));
        row1n.add(graphViewBut);
        row1n.add(Box.createRigidArea(new Dimension(25,0)));
        row1n.add(Box.createHorizontalGlue());
        row1n.add(logoutBut);
        row1n.add(Box.createRigidArea(new Dimension(25,0)));

        JPanel row2n = new JPanel();
        row2n.setLayout(new BoxLayout(row2n, BoxLayout.LINE_AXIS));
        row2n.add(Box.createHorizontalGlue());
        row2n.add(title);
        row2n.add(Box.createHorizontalGlue());

        northPanel.add(Box.createRigidArea(new Dimension(0,25)));
        northPanel.add(row1n);
        northPanel.add(row2n);
        northPanel.add(Box.createRigidArea(new Dimension(0,100)));

        // Sets the default view
        this.createCentrePanel("graphPanel");

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centrePanel, BorderLayout.CENTER);

    }

    private void changePanel(String newView) {
        centrePanel.removeAll();
        this.remove(centrePanel);

        this.createCentrePanel(newView);
        this.add(centrePanel, BorderLayout.CENTER);
    }

    private void createCentrePanel(String newView){
        JPanel newPanel;

        if (newView.equals("dataPanel"))
            newPanel = dataPanel;
        else
            newPanel = graphPanel;

        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.LINE_AXIS));

        centrePanel.add(Box.createRigidArea(new Dimension(80,0)));
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(newPanel);
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(Box.createRigidArea(new Dimension(100,0)));
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(filterPanel);
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(Box.createRigidArea(new Dimension(80,0)));
    }


}