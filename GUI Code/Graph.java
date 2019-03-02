import javax.swing.*;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph extends JPanel {

    private String yAxisVar;

    public Graph() {

        // Create dataset
        DefaultCategoryDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Ad Auction Campaign", // Chart title
                "Date", // X-Axis Label
                "Number of Clicks", // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        this.add(panel);
    }

    private DefaultCategoryDataset createDataset() {

        yAxisVar = "Number of clicks";
        String series1 = yAxisVar;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(200, series1, "2016-12-19");
        dataset.addValue(150, series1, "2016-12-20");
        dataset.addValue(100, series1, "2016-12-21");
        dataset.addValue(210, series1, "2016-12-22");
        dataset.addValue(240, series1, "2016-12-23");
        dataset.addValue(195, series1, "2016-12-24");
        dataset.addValue(245, series1, "2016-12-25");

        return dataset;
    }


}