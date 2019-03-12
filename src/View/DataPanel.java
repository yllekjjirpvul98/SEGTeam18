package View;

import Model.Calculation;

import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DataPanel extends JPanel {

    private DashboardPanel dashboardPanel;

    private double numImpressions;
    private double numClicks;
    private double numUniques;
    private double numConvos;
    private double numBounces;
    private double bounceRate;
    private double totalCost;
    private double CPA;
    private double CPC;
    private double CPM;
    private double CTR;

    private JLabel metricValue1;
    private JLabel metricValue2;
    private JLabel metricValue3;
    private JLabel metricValue4;
    private JLabel metricValue5;
    private JLabel metricValue6;
    private JLabel metricValue7;
    private JLabel metricValue8;
    private JLabel metricValue9;
    private JLabel metricValue10;
    private JLabel metricValue11;

    public DataPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.init();
    }

    private void calcValues(){
        Calculation cal = dashboardPanel.getWindow().getControl().getModel().getCal();

        numImpressions = cal.calImpression();
        numClicks = cal.calClicks();
        numUniques = cal.calUnique();
        numConvos = cal.calConversion();
        numBounces = cal.calBounce();
        bounceRate = numBounces/numClicks;
        totalCost = cal.calTotal();
        CPA = totalCost/numConvos;
        CPC = cal.calClickCost()/numClicks;
        CPM = totalCost/(1000*numImpressions);
        CTR = numClicks/numImpressions;
    }

    public void updateData(){
        this.calcValues();

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        metricValue1.setText(String.valueOf(df.format(numImpressions)));
        metricValue2.setText(String.valueOf(df.format(numClicks)));
        metricValue3.setText(String.valueOf(df.format(numUniques)));
        metricValue4.setText(String.valueOf(df.format(numConvos)));
        metricValue5.setText(String.valueOf(df.format(numBounces)));
        metricValue6.setText(String.valueOf(df.format(bounceRate)));
        metricValue7.setText(String.valueOf(df.format(totalCost)));
        metricValue8.setText(String.valueOf(df.format(CPA)));
        metricValue9.setText(String.valueOf(df.format(CPC)));
        metricValue10.setText(String.valueOf(df.format(CPM)));
        metricValue11.setText(String.valueOf(df.format(CTR)));
    }

    private void init(){
        //  ---- Creating components ----
        JLabel metricLabel1 = new JLabel("Number of Impressions:");
        metricLabel1.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue1 = new JLabel();
        metricValue1.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel2 = new JLabel("Number of Clicks:");
        metricLabel2.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue2 = new JLabel();
        metricValue2.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel3 = new JLabel("Number of Uniques:");
        metricLabel3.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue3 = new JLabel();
        metricValue3.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel4 = new JLabel("Number of Conversations:");
        metricLabel4.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue4 = new JLabel();
        metricValue4.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel5 = new JLabel("Number of Bounces:");
        metricLabel5.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue5 = new JLabel();
        metricValue5.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel6 = new JLabel("Bounce Rate:");
        metricLabel6.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue6 = new JLabel();
        metricValue6.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel7 = new JLabel("Total Cost:");
        metricLabel7.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue7 = new JLabel();
        metricValue7.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel8 = new JLabel("Cost per Acquisition:");
        metricLabel8.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue8 = new JLabel();
        metricValue8.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel9 = new JLabel("Cost per Click:");
        metricLabel9.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue9 = new JLabel();
        metricValue9.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel10 = new JLabel("Cost per 1000 Impressions:");
        metricLabel10.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue10 = new JLabel();
        metricValue10.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel metricLabel11 = new JLabel("Click Through Rate:");
        metricLabel11.setFont(dashboardPanel.getWindow().getTextFont());
        metricValue11 = new JLabel();
        metricValue11.setFont(dashboardPanel.getWindow().getTextFont());

        this.updateData();

        //  ---- Layout ----
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));


        leftPanel.add(metricLabel1);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel2);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel3);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel4);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel5);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel6);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel7);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel8);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel9);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel10);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(metricLabel11);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));


        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));


        rightPanel.add(metricValue1);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue2);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue3);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue4);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue5);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue6);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue7);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue8);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue9);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue10);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(metricValue11);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));

        this.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        this.add(leftPanel);
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        this.add(rightPanel);
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

    }

}