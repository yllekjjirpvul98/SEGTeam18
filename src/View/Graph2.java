package View;

import Model.Calculation;
import Model.Filter;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.Map;

public class Graph2 extends JFXPanel {

    private GraphPanel graphPanel;
    private Calculation calc;
    private LineChart lineChart;
    private BarChart barChart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private CategoryAxis barXAxis;
    private NumberAxis barYAxis;
    private XYChart.Series series;

    public Graph2(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.calc = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getCal();

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel(graphPanel.getTime());
        yAxis.setLabel("Number of " + graphPanel.getMetric());

        barXAxis = new CategoryAxis();
        barYAxis = new NumberAxis();
        barYAxis.setLabel("Frequency");
        barXAxis.setLabel("Click Cost Range");

        lineChart = new LineChart(xAxis, yAxis);
        barChart = new BarChart(barXAxis, barYAxis);
        barChart.setBarGap(-5);

        updateSeries();

        if(graphPanel.getMetric() == "Click Cost Frequency") {
            Platform.runLater(() -> {
                Scene scene = new Scene(barChart);
                this.setScene(scene);
            });
        }
        else {
            Platform.runLater(() -> {
                Scene scene = new Scene(lineChart);
                this.setScene(scene);
            });
        }

    }

    public void updateSeries(){

        Platform.runLater(() -> {

            if(this.graphPanel.getMetric() == "Click Cost Frequency") {
                Map<Integer, Integer> currBarData = calc.getClickCost();

                barChart.getData().removeAll();
                barChart.getData().clear();

                series = new XYChart.Series();

                series.getData().add(new XYChart.Data("0 - 4", currBarData.get(0)));
                series.getData().add(new XYChart.Data("5 - 9", currBarData.get(1)));
                series.getData().add(new XYChart.Data("10 - 14", currBarData.get(2)));
                series.getData().add(new XYChart.Data("15 - 19", currBarData.get(3)));

                series.setName(graphPanel.getMetric());

                barChart.getData().add(series);
            }

            else {

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

                yAxis.setLabel(graphPanel.getMetric());
                String metric = graphPanel.getTime();
                String capMetric = metric.substring(0, 1).toUpperCase() + metric.substring(1);
                xAxis.setLabel(capMetric);
            }
        });
    }

    public void addGraph(){
        Map<String, Double> copyData = calc.getTimeG(graphPanel.getMetric(), graphPanel.getTime());
        graphPanel.getSavedDataMaps().add(copyData);
        graphPanel.getSavedDataLables().add(graphPanel.getSavedDataMaps().size() + " (" + graphPanel.getMetric() + ")");

        ArrayList<String> filters = new ArrayList<>();
        Filter fil = graphPanel.getDashboardPanel().getWindow().getControl().getModel().getFilter();

        if(fil.getDateRangeSelected()){
            String text1 = "Start Date: " + fil.getDateLowerRange();
            String text2 = "End Date: " + fil.getDateUpperRange();
            filters.add(text1);
            filters.add(text2);
        }

        if(fil.getGenderSelected()){
            String text = "Gender:";

            for(String s : fil.getGender()){
                text = text + " " + s + ",";
            }
            text = text.substring(0,text.length() - 1);

            filters.add(text);
        }

        if(fil.getIncomeSelected()){
            String text = "Income:";

            for(String s : fil.getIncome()){
                text = text + " " + s + ",";
            }
            text = text.substring(0,text.length() - 1);

            filters.add(text);
        }

        if(fil.getAgeSelected()){
            String text = "Age:";

            for(String s : fil.getAge()){
                text = text + " " + s + ",";
            }
            text = text.substring(0,text.length() - 1);

            filters.add(text);
        }

        if(fil.getContextSelected()){
            String text = "Context:";

            for(Filter.Context s : fil.getContext()){
                text = text + " " + s.toString() + ",";
            }
            text = text.substring(0,text.length() - 1);

            filters.add(text);
        }

        graphPanel.getSavedFilterLists().add(filters);
    }

    public void deleteGraph(int position){
        graphPanel.getSavedDataMaps().remove(position);
        graphPanel.getSavedDataLables().remove(position);
        graphPanel.getSavedFilterLists().remove(position);
    }


    public LineChart getLineChart(){
        return lineChart;
    }

    public XYChart.Series getSeries() {
        return series;
    }
}
