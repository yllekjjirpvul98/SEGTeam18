package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class GraphPanel extends JPanel {

    private DashboardPanel dashboardPanel;
    private Graph2 graph;
    private String metric = "Impression";
    private String time = "day";
    private ArrayList<Map<String, Double>> savedDataMaps;
    private ArrayList<String> savedDataLables;


    public GraphPanel(DashboardPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
        this.graph = new Graph2(this);
        this.savedDataMaps = new ArrayList<>();
        this.savedDataLables = new ArrayList<>();
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());
        this.init();
    }

    public void init() {

        //  ---- Layout ----
        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.LINE_AXIS));

        eastPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        southPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));

        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));
        centrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));

        centrePanel.add(graph);

        this.add(southPanel, BorderLayout.SOUTH);
        this.add(centrePanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);

    }

    public DashboardPanel getDashboardPanel(){
        return dashboardPanel;
    }

    public Graph2 getGraph(){
        return graph;
    }

    public void setGraph(Graph2 newGraph) {

        graph = newGraph;

    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) { this.metric = metric; }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Map<String, Double>> getSavedDataMaps() {
        return savedDataMaps;
    }

    public void setSavedDataMaps(ArrayList<Map<String, Double>> savedDataMaps) {
        this.savedDataMaps = savedDataMaps;
    }

    public ArrayList<String> getSavedDataLables() {
        return savedDataLables;
    }

    public void setSavedDataLables(ArrayList<String> savedDataLables) {
        this.savedDataLables = savedDataLables;
    }
}
