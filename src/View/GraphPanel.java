package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

/*
    Class holds the Graph and Lists for the saved graph data.
 */

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

    GraphPanel(DashboardPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
        this.graph = new Graph2(this);
        this.savedDataMaps = new ArrayList<>();
        this.savedDataLables = new ArrayList<>();
        this.savedFilterLists = new ArrayList<>();
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());
        this.init();
    }

    void init() {

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

    // Update GUI colors.
    public void updateColors(){
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        southPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        eastPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        centrePanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
    }

    // method to get screenshot of graph
    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(this.getWidth()*2, this.getHeight()*2, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.scale(2, 2);
        this.paint(graphics);
        return image;
    }

    // Getters and Setters.
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

    public ArrayList<String> getSavedDataLables() {
        return savedDataLables;
    }

    public ArrayList<ArrayList<String>> getSavedFilterLists() {
        return savedFilterLists;
    }
}
