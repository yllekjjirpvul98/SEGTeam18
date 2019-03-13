package View;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;

public class Graph extends JPanel {

    private GraphPanel graphPanel;
    private String metric = "Impressions";  // Can be any of the metrics (selected via button pressed)
    private String timeSplit = "Min";  // Can be Day, Hour, Min (selected by time granularity slider)

    public Graph(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.setBackground(graphPanel.getDashboardPanel().getWindow().getBackgoundColor());
        this.setLayout(new BorderLayout());

        DefaultCategoryDataset dataset = createDataset(metric, timeSplit);
        JFreeChart chart = ChartFactory.createLineChart("", "Time" , metric, dataset);
        chart.setBackgroundPaint(null);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(graphPanel.getDashboardPanel().getWindow().getBackgoundColor());

        this.add(chartPanel, BorderLayout.CENTER);
    }

    private DefaultCategoryDataset createDataset(String metric, String timeSplit) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        HashMap<Double, Date> currentGraphData = new HashMap<>(); // Method call to Model to get HashMap<Double, Date>

        for(Double d : currentGraphData.keySet()) {
            dataset.addValue(d, this.metric, currentGraphData.get(d));
        }

        return dataset;
    }


}