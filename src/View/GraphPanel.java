package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphPanel extends JPanel {

    private DashboardPanel dashboardPanel;
    private Graph graph;

    public GraphPanel(DashboardPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
        this.graph = new Graph(this);
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init() {

        //   ---- Creating components ----
        JLabel timeLab = new JLabel("Time Granularity: ");
        timeLab.setFont(dashboardPanel.getWindow().getTextFontBold());

        JSlider timeSlide = new JSlider();
        timeSlide.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        timeSlide.setMinimum(0);
        timeSlide.setMaximum(99);
        timeSlide.setMaximumSize(new Dimension(200,30));

        String[] metrics = { "Impressions","Clicks","Uniques","Conversations", "Bounces", "Bounce Rate", "Total Cost", "CPA", "CPC", "CPM", "CTR"};
        JComboBox<String> metricSelect = new JComboBox<String>(metrics);
        metricSelect.setVisible(true);
        metricSelect.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        metricSelect.setFont(dashboardPanel.getWindow().getTextFont());

        JButton addBut = new JButton("ADD");
        addBut.setFont(dashboardPanel.getWindow().getButtonBigFont());;
        addBut.setBackground(new Color(0x9CFFAD));

        JButton deleteBut = new JButton("DEL");
        deleteBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        deleteBut.setBackground(new Color(0xFF8976));


        //  ---- Layout ----
        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.LINE_AXIS));

        eastPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));

        JPanel row1 = new JPanel();
        row1.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));

        row1.add(timeLab);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(timeSlide);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(Box.createHorizontalGlue());
        row1.add(metricSelect);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        row1.add(addBut);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(deleteBut);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        centrePanel.add(row1);
        centrePanel.add(graph);


        this.add(centrePanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
    }

    public DashboardPanel getDashboardPanel(){
        return dashboardPanel;
    }
}
