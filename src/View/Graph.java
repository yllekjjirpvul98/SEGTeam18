package View;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.util.HashMap;

public class Graph extends JPanel {

    private String yAxisVar = "Impressions";
    private HashMap<Double, String> currentGraphData = new HashMap<>();

    public Graph() {

        // Create dataset
        DefaultCategoryDataset dataset = createDataset();
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "", // Chart title
                "Date", // X-Axis Label
                yAxisVar, // Y-Axis Label
                dataset
        );

        ChartPanel panel = new ChartPanel(chart);
        this.setLayout(new BorderLayout());
        chart.setBackgroundPaint(null);
        this.add(panel, BorderLayout.CENTER);
    }

    private DefaultCategoryDataset createDataset() {

        String series1 = yAxisVar;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        this.fillHashMap();
        for(Double d: currentGraphData.keySet()) {

            dataset.addValue(d, series1, currentGraphData.get(d));

        }

        return dataset;
    }

    private void fillHashMap() {

        currentGraphData.put(22.0, "01/01/2018");
        currentGraphData.put(42.0, "06/03/2018");
        currentGraphData.put(67.0, "09/04/2018");
        currentGraphData.put(11.0, "14/04/2018");
        currentGraphData.put(14.0, "18/05/2018");
        currentGraphData.put(72.0, "26/05/2018");
        currentGraphData.put(56.0, "01/06/2018");
        currentGraphData.put(74.0, "03/08/2018");
        currentGraphData.put(1.0, "22/10/2018");
        currentGraphData.put(19.0, "04/11/2018");

    }

}