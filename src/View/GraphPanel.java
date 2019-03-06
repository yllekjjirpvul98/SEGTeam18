package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphPanel extends JPanel {

    private DashboardPanel dashboardPanel;
    private Graph graph;

    public GraphPanel(DashboardPanel dashboardPanel) {
        this.graph = new Graph();
        this.dashboardPanel = dashboardPanel;
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init() {

        //   ---- Creating components ----

        JLabel timeLab = new JLabel("Time Granularity: ");
        timeLab.setFont(dashboardPanel.getWindow().getTextFont());

        JSlider timeSlide = new JSlider();
        timeSlide.setMinimum(0);
        timeSlide.setMaximum(99);
        timeSlide.setMaximumSize(new Dimension(200,30));


        // Metric Buttons
        JButton impressionsBut = new JButton("Impressions");
        impressionsBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        impressionsBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton clicksBut = new JButton("Clicks");
        clicksBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        clicksBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton uniquesBut = new JButton("Uniques");
        uniquesBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        uniquesBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton convoBut = new JButton("Conversations");
        convoBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        convoBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton bounceBut = new JButton("Bounces");
        bounceBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        bounceBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton bounceRateBut = new JButton("Bounce Rate");
        bounceRateBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        bounceRateBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton totalCostBut = new JButton("Total Cost");
        totalCostBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        totalCostBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton cpaBut = new JButton("CPA");
        cpaBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        cpaBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton cpcBut = new JButton("CPC");
        cpcBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        cpcBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton cpmBut = new JButton("CPM");
        cpmBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        cpmBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());

        JButton ctaBut = new JButton("CTA");
        ctaBut.setFont(dashboardPanel.getWindow().getButtonSmallFont());
        ctaBut.setBackground(dashboardPanel.getWindow().getUnhighlightColor());


        JButton addBut = new JButton("ADD");
        addBut.setFont(dashboardPanel.getWindow().getButtonBigFont());;
        addBut.setBackground(new Color(0x9CFFAD));

        JButton deleteBut = new JButton("DEL");
        deleteBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        deleteBut.setBackground(new Color(0xFF8976));




        //  ---- Layout ----
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.LINE_AXIS));

        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.PAGE_AXIS));

        col1.add(impressionsBut);
        col1.add(Box.createVerticalGlue());
        col1.add(clicksBut);
        col1.add(Box.createVerticalGlue());
        col1.add(uniquesBut);
        col1.add(Box.createVerticalGlue());
        col1.add(convoBut);
        col1.add(Box.createVerticalGlue());
        col1.add(bounceBut);
        col1.add(Box.createVerticalGlue());
        col1.add(bounceRateBut);
        col1.add(Box.createVerticalGlue());
        col1.add(totalCostBut);
        col1.add(Box.createVerticalGlue());
        col1.add(cpaBut);
        col1.add(Box.createVerticalGlue());
        col1.add(cpcBut);
        col1.add(Box.createVerticalGlue());
        col1.add(cpmBut);
        col1.add(Box.createVerticalGlue());
        col1.add(ctaBut);
        col1.add(Box.createVerticalGlue());

        eastPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        eastPanel.add(Box.createHorizontalGlue());
        eastPanel.add(col1);
        eastPanel.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.PAGE_AXIS));

        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));

        row1.add(timeLab);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(timeSlide);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(Box.createHorizontalGlue());
        row1.add(addBut);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(deleteBut);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        centrePanel.add(row1);
        centrePanel.add(graph);


        this.add(centrePanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
    }
}
