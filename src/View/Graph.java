package View;

import javax.swing.*;

import Model.Calculation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.awt.List;
import java.util.*;

public class Graph extends JPanel {

    private GraphPanel graphPanel;
    private Calculation calc;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public Graph(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.calc = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getCal();
        this.setBackground(graphPanel.getDashboardPanel().getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());

        DefaultCategoryDataset dataset = createDataset(graphPanel.getMetric(), graphPanel.getTime());
        chart = ChartFactory.createLineChart("", "Time" , "Metric", dataset);
        chart.setBackgroundPaint(null);

        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(graphPanel.getDashboardPanel().getWindow().getBackgoundColor());

        this.add(chartPanel, BorderLayout.CENTER);
    }

    public DefaultCategoryDataset createDataset(String metric, String timeSplit) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<Integer, String> currentGraphData = calc.getTimeG(metric, timeSplit); // Method call to Model to get HashMap<Double, Date>

        for(Integer i : currentGraphData.keySet()) {
            System.out.println(i + " " + currentGraphData.get(i));
            dataset.addValue(i, graphPanel.getMetric(), currentGraphData.get(i));

        }

        return dataset;

    }


    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }
}