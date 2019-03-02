import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    private DashboardPanel dashboardPanel;
    private Graph graph = new Graph();

    public GraphPanel(DashboardPanel dashboardPanel) {
        this.dashboardPanel = dashboardPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.init();
    }

    private void init() {

        //   ---- Creating components ----

        JLabel timeLab = new JLabel("Time Granularity");
        timeLab.setFont(new Font("Courier", Font.PLAIN, 15));

        JSlider timeSlide = new JSlider();
        timeSlide.setMinimum(0);
        timeSlide.setMaximum(99);
        timeSlide.setMaximumSize(new Dimension(200,30));

        JButton lineBut = new JButton("Line Graph");
        lineBut.setFont(new Font( "Courier", Font.BOLD, 20));
        lineBut.setBackground(new Color(0x76B8FF));

        JButton histBut = new JButton("Histogram");
        histBut.setFont(new Font( "Courier", Font.BOLD, 20));
        histBut.setBackground(Color.lightGray);

        // Metric Buttons
        JButton impressionsBut = new JButton("Impressions");
        impressionsBut.setFont(new Font( "Courier", Font.BOLD, 20));
        impressionsBut.setBackground(Color.lightGray);

        JButton clicksBut = new JButton("Clicks");
        clicksBut.setFont(new Font( "Courier", Font.BOLD, 20));
        clicksBut.setBackground(Color.lightGray);

        JButton uniquesBut = new JButton("Uniques");
        uniquesBut.setFont(new Font( "Courier", Font.BOLD, 20));
        uniquesBut.setBackground(Color.lightGray);

        JButton convoBut = new JButton("Conversations");
        convoBut.setFont(new Font( "Courier", Font.BOLD, 20));
        convoBut.setBackground(Color.lightGray);

        JButton bounceBut = new JButton("Bounces");
        bounceBut.setFont(new Font( "Courier", Font.BOLD, 20));
        bounceBut.setBackground(Color.lightGray);

        JButton bounceRateBut = new JButton("Bounce Rate");
        bounceRateBut.setFont(new Font( "Courier", Font.BOLD, 20));
        bounceRateBut.setBackground(Color.lightGray);

        JButton totalCostBut = new JButton("Total Cost");
        totalCostBut.setFont(new Font( "Courier", Font.BOLD, 20));
        totalCostBut.setBackground(Color.lightGray);

        JButton cpaBut = new JButton("CPA");
        cpaBut.setFont(new Font( "Courier", Font.BOLD, 20));
        cpaBut.setBackground(Color.lightGray);

        JButton cpcBut = new JButton("CPC");
        cpcBut.setFont(new Font( "Courier", Font.BOLD, 20));
        cpcBut.setBackground(Color.lightGray);

        JButton cpmBut = new JButton("CPM");
        cpmBut.setFont(new Font( "Courier", Font.BOLD, 20));
        cpmBut.setBackground(Color.lightGray);

        JButton ctaBut = new JButton("CTA");
        ctaBut.setFont(new Font( "Courier", Font.BOLD, 20));
        ctaBut.setBackground(Color.lightGray);


        JButton graph1But = new JButton("Graph1");
        graph1But.setFont(new Font( "Courier", Font.BOLD, 20));
        graph1But.setBackground(new Color(0x76B8FF));

        JButton addBut = new JButton("ADD");
        addBut.setFont(new Font( "Courier", Font.BOLD, 20));
        addBut.setBackground(new Color(0x5DFF5D));

        JButton deleteBut = new JButton("DEL");
        deleteBut.setFont(new Font( "Courier", Font.BOLD, 20));
        deleteBut.setBackground(new Color(0xFF5C3C));




        //  ---- Layout ----

        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));

        row1.add(Box.createRigidArea(new Dimension(25,0)));
        row1.add(timeLab);
        row1.add(Box.createRigidArea(new Dimension(25, 0)));
        row1.add(timeSlide);
        row1.add(Box.createHorizontalGlue());
        row1.add(lineBut);
        row1.add(Box.createRigidArea(new Dimension(25,0)));
        row1.add(histBut);
        row1.add(Box.createRigidArea(new Dimension(25,0)));


        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));

        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.PAGE_AXIS));

        col1.add(Box.createRigidArea(new Dimension(0,25)));
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


        row2.add(Box.createRigidArea(new Dimension(25,0)));
        row2.add(col1);
        row2.add(Box.createHorizontalGlue());
        row2.add(graph);


        JPanel row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));

        row3.add(Box.createRigidArea(new Dimension(200,0)));
        row3.add(graph1But);

        JPanel row4 = new JPanel();
        row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));

        row4.add(Box.createRigidArea(new Dimension(25,0)));
        row4.add(addBut);
        row4.add(Box.createRigidArea(new Dimension(25,0)));
        row4.add(deleteBut);


        this.add(row1);
        this.add(row2);
        this.add(row3);
        this.add(row4);
        this.add(Box.createRigidArea(new Dimension(0,50)));
    }
}
