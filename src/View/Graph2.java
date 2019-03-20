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
    private ArrayList<Map<String, Double>> savedDataMaps;
    private ArrayList<String> savedDataLables;

    private XYChart.Series series;

    public Graph2(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.calc = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getCal();
        this.savedDataMaps = new ArrayList<>();
        this.savedDataLables = new ArrayList<>();

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

        for (String date : currentGraphData.keySet()) {
            series.getData().add(new XYChart.Data(date, currentGraphData.get(date)));
        }

        lineChart.getData().add(series);
    }

    public void updateSeries(){

        Platform.runLater(() -> {
            Map<String, Double> currentGraphData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());

            lineChart.getData().removeAll();
            lineChart.getData().clear();

            series = new XYChart.Series();

            for (String date : currentGraphData.keySet()) {
                series.getData().add(new XYChart.Data(date, currentGraphData.get(date)));
            }

            series.setName(graphPanel.getMetric());

            lineChart.getData().add(series);

            for(int i = 0; i < savedDataMaps.size(); i++){
                XYChart.Series savedSeries = new XYChart.Series();
                Map<String, Double> savedDataMap = savedDataMaps.get(i);
                String savedDataLable = savedDataLables.get(i);

                for (String date : savedDataMap.keySet()) {
                    savedSeries.getData().add(new XYChart.Data(date, savedDataMap.get(date)));
                }

                savedSeries.setName(savedDataLable);
                lineChart.getData().add(savedSeries);

            }

        });
    }

    public void addGraph(){
        Map<String, Double> copyData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());
        savedDataMaps.add(copyData);
        savedDataLables.add(savedDataMaps.size() + " (" + graphPanel.getMetric() + ")");
    }

    public void deleteGraph(int position){
        savedDataMaps.remove(position);
        savedDataLables.remove(position);
    }


    public LineChart getLineChart(){
        return lineChart;
    }

    public ArrayList<Map<String, Double>> getSavedDataMaps() {
        return savedDataMaps;
    }

    public XYChart.Series getSeries() {
        return series;
    }
}
