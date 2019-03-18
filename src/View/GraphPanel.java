package View;

import org.jfree.chart.ChartFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphPanel extends JPanel {

    private DashboardPanel dashboardPanel;
    private Graph graph;
    private String metric = "Impression";
    private String time = "hour";

    public GraphPanel(DashboardPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
        this.graph = new Graph(this);
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init() {

        //   ---- Creating components ----
        String[] metrics = { "Impression","Clicks","Unique","Conversion", "Bounce", "BounceRate", "TotalCost", "CPA", "CPC", "CPM", "CTR"};
        JComboBox<String> metricSelect = new JComboBox<String>(metrics);
        metricSelect.setVisible(true);
        metricSelect.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        metricSelect.setFont(dashboardPanel.getWindow().getTextFont());
        //metricSelect.setMaximumSize(new Dimension(,metricSelect.getFont().getSize() * 2));
        metricSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox metricSelect = (JComboBox) event.getSource();

                graph.getChart().getCategoryPlot().setDataset(graph.createDataset(metricSelect.getSelectedItem().toString(), graph.getTimeSplit()));
                graph.getChartPanel().repaint();
                repaint();
                metric = metricSelect.getSelectedItem().toString();

            }

        });

        String[] TimeScales = {"hour","day","week","month","year"};
        JComboBox<String> timeSlide = new JComboBox<String>(TimeScales);
        metricSelect.setVisible(true);
        timeSlide.setFont(dashboardPanel.getWindow().getTextFont());
        timeSlide.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        //timeSlide.setMaximumSize(new Dimension(200,timeSlide.getFont().getSize()));
        timeSlide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox timeSlide = (JComboBox) event.getSource();

                graph.getChart().getCategoryPlot().setDataset(graph.createDataset(metricSelect.getSelectedItem().toString(), timeSlide.getSelectedItem().toString()));
                graph.getChartPanel().repaint();
                repaint();
                metric = metricSelect.getSelectedItem().toString();
                time = timeSlide.getSelectedItem().toString();

            }

        });


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

        row1.add(Box.createHorizontalGlue());
        row1.add(metricSelect);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(timeSlide);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        centrePanel.add(row1);
        centrePanel.add(graph);


        this.add(centrePanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
    }

    public DashboardPanel getDashboardPanel(){
        return dashboardPanel;
    }

    public Graph getGraph(){
        return graph;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
