package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPanel extends JPanel {

    private View window;

    private FilterPanel filterPanel;
    private DataPanel dataPanel;
    private GraphPanel graphPanel;

    public DashboardPanel(View window){
        this.window = window;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BorderLayout());
    }

    public void reset(){
        window.getControl().getModel().getFilter().reset();
        filterPanel.reset();
        dataPanel.updateData();
        graphPanel.getGraph().updateSeries();
    }

    public void init() {
        //  ---- Creating Components ----
        filterPanel = new FilterPanel(this);
        dataPanel = new DataPanel(this);
        graphPanel = new GraphPanel(this);

        JLabel title = new JLabel("DashBoard");
        title.setFont(window.getHeadingFont());
        title.setForeground(window.getHeadingColour());

        JButton logoutBut = new JButton("Campaign Select");
        logoutBut.setFont(window.getButtonBigFont());
        logoutBut.setBackground(window.getUnhighlightColor());

        logoutBut.addActionListener(e -> window.setCampFrame(new CampFrame(window)));


        JButton settingsBut = new JButton("Settings");
        settingsBut.setFont(window.getButtonBigFont());
        settingsBut.setBackground(window.getUnhighlightColor());

        settingsBut.addActionListener(e -> window.setSettingFrame(new SettingFrame(window)));

        String[] metrics = { "Impression","Clicks","Unique","Conversion", "Bounce", "BounceRate", "TotalCost", "CPA", "CPC", "CPM", "CTR"};
        JComboBox<String> metricSelect = new JComboBox<String>(metrics);
        metricSelect.setVisible(true);
        metricSelect.setBackground(window.getUnhighlightColor());
        metricSelect.setFont(window.getTextFont());
        metricSelect.setMaximumSize(new Dimension(window.getButtonBigFont().getSize() * 10,window.getButtonBigFont().getSize() * 3));
        metricSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox metricSelect = (JComboBox) event.getSource();
                graphPanel.setMetric(metricSelect.getSelectedItem().toString());

                graphPanel.getGraph().updateSeries();
            }

        });

        String[] TimeScales = {"Hour","Day","Week","Month","Year"};
        JComboBox<String> timeSlide = new JComboBox<String>(TimeScales);
        timeSlide.setSelectedIndex(1); // TO START ON DAY
        timeSlide.setFont(window.getTextFont());
        timeSlide.setBackground(window.getUnhighlightColor());
        timeSlide.setMaximumSize(new Dimension(window.getButtonBigFont().getSize() * 10, window.getButtonBigFont().getSize() * 3));
        timeSlide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox timeSlide = (JComboBox) event.getSource();

                String timeUpper = timeSlide.getSelectedItem().toString();
                graphPanel.setTime(timeUpper.toLowerCase());

                graphPanel.getGraph().updateSeries();

            }

        });

        // North Panel --- Logout & Title
        JPanel northPanel = new JPanel();
        northPanel.setBackground(window.getBackgoundColor());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        JPanel row1n = new JPanel();
        row1n.setBackground(window.getBackgoundColor());

        row1n.setLayout(new BoxLayout(row1n, BoxLayout.LINE_AXIS));
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1n.add(logoutBut);
        row1n.add(Box.createHorizontalGlue());
        row1n.add(settingsBut);
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));

        JPanel row2n = new JPanel();
        row2n.setBackground(window.getBackgoundColor());

        row2n.setLayout(new BoxLayout(row2n, BoxLayout.LINE_AXIS));
        row2n.add(Box.createHorizontalGlue());
        row2n.add(title);
        row2n.add(Box.createHorizontalGlue());

        JPanel row3n = new JPanel();
        row3n.setBackground(window.getBackgoundColor());
        row3n.setLayout(new BoxLayout(row3n, BoxLayout.LINE_AXIS));

        row3n.add(Box.createHorizontalGlue());
        row3n.add(metricSelect);
        row3n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row3n.add(timeSlide);
        row3n.add(Box.createRigidArea(window.getWidthBorderDim()));

        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(row1n);
        northPanel.add(row2n);
        northPanel.add(row3n);
        northPanel.add(Box.createRigidArea(new Dimension(0,window.getButtonBigFont().getSize())));


        this.add(northPanel, BorderLayout.NORTH);
        this.add(filterPanel, BorderLayout.SOUTH);
        this.add(graphPanel, BorderLayout.CENTER);
        this.add(dataPanel, BorderLayout.WEST);
    }


    public View getWindow(){
        return window;
    }

    public FilterPanel getFilterPanel() {
        return filterPanel;
    }

    public void setFilterPanel(FilterPanel filterPanel) {
        this.filterPanel = filterPanel;
    }

    public DataPanel getDataPanel(){
        return dataPanel;
    }

    public void setDataPanel(DataPanel dataPanel){
        this.dataPanel = dataPanel;
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    public void setGraphPanel(GraphPanel graphPanel){
        this.graphPanel = graphPanel;
    }
}