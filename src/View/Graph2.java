package View;

import Model.Calculation;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Map;

public class Graph2 extends JFXPanel {

    private GraphPanel graphPanel;
    private Calculation calc;
    private LineChart lineChart;
    private ArrayList<XYChart.Series> savedSeries;

    private XYChart.Series series;

    public Graph2(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.calc = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getCal();
        this.savedSeries = new ArrayList<>();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        lineChart = new LineChart(xAxis, yAxis);
        createSeries();

        Platform.runLater(() -> {
            Scene scene = new Scene(lineChart);
            this.setScene(scene);
        });

    }

    public void createSeries(){
        series = new XYChart.Series();
        series.setName(graphPanel.getMetric());

        Map<String, Double> currentGraphData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());
        System.out.println(currentGraphData.size());

        for (String i : currentGraphData.keySet()) {
            series.getData().add(new XYChart.Data(i, currentGraphData.get(i)));
        }

        lineChart.getData().add(series);
    }

    public void updateSeries(){

        Platform.runLater(() -> {
            Map<String, Double> currentGraphData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());

            series.getData().clear();
            lineChart.getData().removeAll();
            lineChart.getData().clear();

            series = new XYChart.Series();

            for (String i : currentGraphData.keySet()) {
                series.getData().add(new XYChart.Data(i, currentGraphData.get(i)));
            }

            series.setName(graphPanel.getMetric());

            lineChart.getData().add(series);
        });
    }

    public void addGraph(){
        Platform.runLater(() -> {
            XYChart.Series copy = new XYChart.Series();

            Map<String, Double> currentGraphData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());

            for (String i : currentGraphData.keySet()) {
                copy.getData().add(new XYChart.Data(i, currentGraphData.get(i)));
            }

            copy.setName(graphPanel.getMetric());

            savedSeries.add(copy);
        });
    }


    public LineChart getLineChart(){
        return lineChart;
    }

    public ArrayList<XYChart.Series> getSavedSeries() {
        return savedSeries;
    }

    public XYChart.Series getSeries() {
        return series;
    }
}
