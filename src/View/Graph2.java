package View;

import Model.Calculation;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Map;

public class Graph2 extends JFXPanel {

    private GraphPanel graphPanel;
    private Calculation calc;
    private LineChart lineChart;
    private BarChart barChart;
    private XYChart.Series series;

    public Graph2(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.calc = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getCal();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        CategoryAxis bxAxis = new CategoryAxis();
        NumberAxis byAxis = new NumberAxis();
        byAxis.setLabel("Frequency");
        bxAxis.setLabel("Click Cost Range");

        lineChart = new LineChart(xAxis, yAxis);
        barChart = new BarChart(bxAxis, byAxis);
        barChart.setBarGap(-5);
        updateSeries();

        if (graphPanel.getMetric() == "Click Cost Frequency") {

            Platform.runLater(() -> {
                Scene scene = new Scene(barChart);
                this.setScene(scene);
            });

        } else {

            Platform.runLater(() -> {
                Scene scene = new Scene(lineChart);
                this.setScene(scene);
            });

        }

    }

//    public void createSeries(){
//        series = new XYChart.Series();
//        series.setName(graphPanel.getMetric());
//
//        Map<String, Double> currentGraphData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());
//
//        for (String date : currentGraphData.keySet()) {
//            series.getData().add(new XYChart.Data(date, currentGraphData.get(date)));
//        }
//
//        lineChart.getData().add(series);
//    }

    public void updateSeries(){

        Platform.runLater(() -> {

            if(this.graphPanel.getMetric() == "Click Cost Frequency") {

                Map<Integer, Integer> currBarData = calc.getClickCost();

                barChart.getData().removeAll();
                barChart.getData().clear();

                series = new XYChart.Series();

                series.getData().add(new XYChart.Data("0-4", currBarData.get(0)));
                series.getData().add(new XYChart.Data("5-9", currBarData.get(1)));
                series.getData().add(new XYChart.Data("10-14", currBarData.get(2)));
                series.getData().add(new XYChart.Data("15-19", currBarData.get(3)));

                series.setName(graphPanel.getMetric());

                barChart.getData().add(series);

            } else {

                Map<String, Double> currentGraphData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());

                lineChart.getData().removeAll();
                lineChart.getData().clear();

                series = new XYChart.Series();

                for (String date : currentGraphData.keySet()) {
                    series.getData().add(new XYChart.Data(date, currentGraphData.get(date)));
                }

                series.setName(graphPanel.getMetric());

                lineChart.getData().add(series);

                for (int i = 0; i < graphPanel.getSavedDataMaps().size(); i++) {
                    XYChart.Series savedSeries = new XYChart.Series();
                    Map<String, Double> savedDataMap = graphPanel.getSavedDataMaps().get(i);
                    String savedDataLable = graphPanel.getSavedDataLables().get(i);

                    for (String date : savedDataMap.keySet()) {
                        savedSeries.getData().add(new XYChart.Data(date, savedDataMap.get(date)));
                    }

                    savedSeries.setName(savedDataLable);
                    lineChart.getData().add(savedSeries);

                }
            }

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
