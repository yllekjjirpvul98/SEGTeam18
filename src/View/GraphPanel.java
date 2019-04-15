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
    private ArrayList<ArrayList<String>> savedFilterLists;

    private JPanel eastPanel;
    private JPanel southPanel;
    private JPanel centrePanel;

    public GraphPanel(DashboardPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
        this.graph = new Graph2(this);
        this.savedDataMaps = new ArrayList<>();
        this.savedDataLables = new ArrayList<>();
        this.savedFilterLists = new ArrayList<>();
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());
        this.init();
    }

    public void init() {

        //  ---- Layout ----
        eastPanel = new JPanel();
        eastPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.LINE_AXIS));

        eastPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        southPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));

        centrePanel = new JPanel();
        centrePanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));
        centrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));

        centrePanel.add(graph);

        this.add(southPanel, BorderLayout.SOUTH);
        this.add(centrePanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
    }

    public void updateColors(){
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        southPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        eastPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        centrePanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
    }

    public DashboardPanel getDashboardPanel(){
        return dashboardPanel;
    }

    public Graph2 getGraph(){
        return graph;
    }

    public void setGraph(Graph2 graph){
        this.graph = graph;
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

    public ArrayList<ArrayList<String>> getSavedFilterLists() {
        return savedFilterLists;
    }

    public void setSavedFilterLists(ArrayList<ArrayList<String>> savedFilterLists) {
        this.savedFilterLists = savedFilterLists;
    }

}
