package View;

import javax.swing.*;

import Model.Calculation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.util.Date;
import java.util.Map;

public class Graph extends JPanel {

    private GraphPanel graphPanel;
    private String metric = "Impression";  // Can be any of the metrics (selected via button pressed)
    private String timeSplit = "hour";  // Can be Day, Hour, Min (selected by time granularity slider)
    private Calculation calc;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public Graph(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.calc = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getCal();
        this.setBackground(graphPanel.getDashboardPanel().getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());

        DefaultCategoryDataset dataset = createDataset(metric, timeSplit);
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

            dataset.addValue(i, this.metric, currentGraphData.get(i));

        }

        return dataset;

    }

    public String getTimeSplit() {
        return timeSplit;
    }

    public void setTimeSplit(String timeSplit) {
        this.timeSplit = timeSplit;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
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