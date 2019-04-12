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
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series series;

    public Graph2(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.calc = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getCal();

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel(graphPanel.getTime());
        yAxis.setLabel("Number of " + graphPanel.getMetric());

        lineChart = new LineChart(xAxis, yAxis);
        updateSeries();

        Platform.runLater(() -> {
            Scene scene = new Scene(lineChart);
            this.setScene(scene);
        });

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

            for(int i = 0; i < graphPanel.getSavedDataMaps().size(); i++){
                XYChart.Series savedSeries = new XYChart.Series();
                Map<String, Double> savedDataMap = graphPanel.getSavedDataMaps().get(i);
                String savedDataLable = graphPanel.getSavedDataLables().get(i);

                for (String date : savedDataMap.keySet()) {
                    savedSeries.getData().add(new XYChart.Data(date, savedDataMap.get(date)));
                }

                savedSeries.setName(savedDataLable);
                lineChart.getData().add(savedSeries);

            }

            yAxis.setLabel(graphPanel.getMetric());
            String metric = graphPanel.getTime();
            String capMetric = metric.substring(0,1).toUpperCase() + metric.substring(1);
            xAxis.setLabel(capMetric);
        });
    }

    public void addGraph(){
        Map<String, Double> copyData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());
        graphPanel.getSavedDataMaps().add(copyData);
        graphPanel.getSavedDataLables().add(graphPanel.getSavedDataMaps().size() + " (" + graphPanel.getMetric() + ")");
    }

    public void deleteGraph(int position){
        graphPanel.getSavedDataMaps().remove(position);
        graphPanel.getSavedDataLables().remove(position);
    }


    public LineChart getLineChart(){
        return lineChart;
    }

    public XYChart.Series getSeries() {
        return series;
    }
}
