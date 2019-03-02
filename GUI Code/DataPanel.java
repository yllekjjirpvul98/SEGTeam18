import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {

    private DashboardPanel dashboardPanel;
    private int numImpressions;
    private int numClicks;
    private int numUniques;
    private int numConvos;
    private int numBounces;
    private double bounceRate;
    private double totalCost;
    private double CPA;
    private double CPC;
    private double CPM;
    private double CTR;


    public DataPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.init();
    }

    private void init(){

        numImpressions = 0;
        numClicks = 0;
        numUniques = 0;
        numConvos = 0;
        numBounces = 0;
        bounceRate = 0;
        totalCost = 0;
        CPA = 0;
        CPC = 0;
        CPM = 0;
        CTR = 0;

        //  ---- Creating components ----
        JLabel titleLabel = new JLabel("Key Metrics");
        titleLabel.setFont(new Font("Courier", Font.PLAIN, 30));

        JLabel metricLabel1 = new JLabel("Number of Impressions:  ");
        metricLabel1.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue1 = new JLabel(String.valueOf(numImpressions));
        metricValue1.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel2 = new JLabel("Number of Clicks:       ");
        metricLabel2.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue2 = new JLabel(String.valueOf(numClicks));
        metricValue2.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel3 = new JLabel("Number of Uniques:      ");
        metricLabel3.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue3 = new JLabel(String.valueOf(numUniques));
        metricValue3.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel4 = new JLabel("Number of Conversations:");
        metricLabel4.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue4 = new JLabel(String.valueOf(numConvos));
        metricValue4.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel5 = new JLabel("Number of Bounces:      ");
        metricLabel5.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue5 = new JLabel(String.valueOf(numBounces));
        metricValue5.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel6 = new JLabel("Bounce Rate:            ");
        metricLabel6.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue6 = new JLabel(String.valueOf(bounceRate));
        metricValue6.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel7 = new JLabel("Total Cost:             ");
        metricLabel7.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue7 = new JLabel(String.valueOf(totalCost));
        metricValue7.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel8 = new JLabel("Cost per Acquisition:   ");
        metricLabel8.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue8 = new JLabel(String.valueOf(CPA));
        metricValue8.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel9 = new JLabel("Cost per Click:");
        metricLabel9.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue9 = new JLabel(String.valueOf(CPC));
        metricValue9.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel10 = new JLabel("Cost per Thousand Impressions:");
        metricLabel10.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue10 = new JLabel(String.valueOf(CPM));
        metricValue10.setFont(new Font("Courier", Font.PLAIN, 15));

        JLabel metricLabel11 = new JLabel("Click Through Rate:");
        metricLabel11.setFont(new Font("Courier", Font.PLAIN, 15));
        JLabel metricValue11 = new JLabel(String.valueOf(CTR));
        metricValue11.setFont(new Font("Courier", Font.PLAIN, 15));


        //  ---- Layout ----
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        JPanel row0L = new JPanel();
        row0L.setLayout(new BoxLayout(row0L, BoxLayout.LINE_AXIS));
        row0L.add(Box.createRigidArea(new Dimension(25,0)));
        row0L.add(titleLabel);
        row0L.add(Box.createHorizontalGlue());

        JPanel row1L = new JPanel();
        row1L.setLayout(new BoxLayout(row1L, BoxLayout.LINE_AXIS));
        row1L.add(Box.createRigidArea(new Dimension(85,0)));
        row1L.add(metricLabel1);
        row1L.add(Box.createHorizontalGlue());

        JPanel row2L = new JPanel();
        row2L.setLayout(new BoxLayout(row2L, BoxLayout.LINE_AXIS));
        row2L.add(Box.createRigidArea(new Dimension(85,0)));
        row2L.add(metricLabel2);
        row2L.add(Box.createHorizontalGlue());

        JPanel row3L = new JPanel();
        row3L.setLayout(new BoxLayout(row3L, BoxLayout.LINE_AXIS));
        row3L.add(Box.createRigidArea(new Dimension(85,0)));
        row3L.add(metricLabel3);
        row3L.add(Box.createHorizontalGlue());

        JPanel row4L = new JPanel();
        row4L.setLayout(new BoxLayout(row4L, BoxLayout.LINE_AXIS));
        row4L.add(Box.createRigidArea(new Dimension(85,0)));
        row4L.add(metricLabel4);
        row4L.add(Box.createHorizontalGlue());

        JPanel row5L = new JPanel();
        row5L.setLayout(new BoxLayout(row5L, BoxLayout.LINE_AXIS));
        row5L.add(Box.createRigidArea(new Dimension(85,0)));
        row5L.add(metricLabel5);
        row5L.add(Box.createHorizontalGlue());

        JPanel row6L = new JPanel();
        row6L.setLayout(new BoxLayout(row6L, BoxLayout.LINE_AXIS));
        row6L.add(Box.createRigidArea(new Dimension(85,0)));
        row6L.add(metricLabel6);
        row6L.add(Box.createHorizontalGlue());

        JPanel row7L = new JPanel();
        row7L.setLayout(new BoxLayout(row7L, BoxLayout.LINE_AXIS));
        row7L.add(Box.createRigidArea(new Dimension(85,0)));
        row7L.add(metricLabel7);
        row7L.add(Box.createHorizontalGlue());

        JPanel row8L = new JPanel();
        row8L.setLayout(new BoxLayout(row8L, BoxLayout.LINE_AXIS));
        row8L.add(Box.createRigidArea(new Dimension(85,0)));
        row8L.add(metricLabel8);
        row8L.add(Box.createHorizontalGlue());

        JPanel row9L = new JPanel();
        row9L.setLayout(new BoxLayout(row9L, BoxLayout.LINE_AXIS));
        row9L.add(Box.createRigidArea(new Dimension(85,0)));
        row9L.add(metricLabel9);
        row9L.add(Box.createHorizontalGlue());

        JPanel row10L = new JPanel();
        row10L.setLayout(new BoxLayout(row10L, BoxLayout.LINE_AXIS));
        row10L.add(Box.createRigidArea(new Dimension(85,0)));
        row10L.add(metricLabel10);
        row10L.add(Box.createHorizontalGlue());

        JPanel row11L = new JPanel();
        row11L.setLayout(new BoxLayout(row11L, BoxLayout.LINE_AXIS));
        row11L.add(Box.createRigidArea(new Dimension(85,0)));
        row11L.add(metricLabel11);
        row11L.add(Box.createHorizontalGlue());

        leftPanel.add(row0L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
        leftPanel.add(row1L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row2L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row3L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row4L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row5L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row6L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row7L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row8L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row9L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row10L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,20)));
        leftPanel.add(row11L);
        leftPanel.add(Box.createVerticalGlue());


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

        JPanel row1R = new JPanel();
        row1R.setLayout(new BoxLayout(row1R, BoxLayout.LINE_AXIS));
        row1R.add(Box.createRigidArea(new Dimension(25,0)));
        row1R.add(metricValue1);

        JPanel row2R = new JPanel();
        row2R.setLayout(new BoxLayout(row2R, BoxLayout.LINE_AXIS));
        row2R.add(Box.createRigidArea(new Dimension(25,0)));
        row2R.add(metricValue2);

        JPanel row3R = new JPanel();
        row3R.setLayout(new BoxLayout(row3R, BoxLayout.LINE_AXIS));
        row3R.add(Box.createRigidArea(new Dimension(25,0)));
        row3R.add(metricValue3);

        JPanel row4R = new JPanel();
        row4R.setLayout(new BoxLayout(row4R, BoxLayout.LINE_AXIS));
        row4R.add(Box.createRigidArea(new Dimension(25,0)));
        row4R.add(metricValue4);

        JPanel row5R = new JPanel();
        row5R.setLayout(new BoxLayout(row5R, BoxLayout.LINE_AXIS));
        row5R.add(Box.createRigidArea(new Dimension(25,0)));
        row5R.add(metricValue5);

        JPanel row6R = new JPanel();
        row6R.setLayout(new BoxLayout(row6R, BoxLayout.LINE_AXIS));
        row6R.add(Box.createRigidArea(new Dimension(25,0)));
        row6R.add(metricValue6);

        JPanel row7R = new JPanel();
        row7R.setLayout(new BoxLayout(row7R, BoxLayout.LINE_AXIS));
        row7R.add(Box.createRigidArea(new Dimension(25,0)));
        row7R.add(metricValue7);

        JPanel row8R = new JPanel();
        row8R.setLayout(new BoxLayout(row8R, BoxLayout.LINE_AXIS));
        row8R.add(Box.createRigidArea(new Dimension(25,0)));
        row8R.add(metricValue8);

        JPanel row9R = new JPanel();
        row9R.setLayout(new BoxLayout(row9R, BoxLayout.LINE_AXIS));
        row9R.add(Box.createRigidArea(new Dimension(25,0)));
        row9R.add(metricValue9);

        JPanel row10R = new JPanel();
        row10R.setLayout(new BoxLayout(row10R, BoxLayout.LINE_AXIS));
        row10R.add(Box.createRigidArea(new Dimension(25,0)));
        row10R.add(metricValue10);

        JPanel row11R = new JPanel();
        row11R.setLayout(new BoxLayout(row11R, BoxLayout.LINE_AXIS));
        row11R.add(Box.createRigidArea(new Dimension(25,0)));
        row11R.add(metricValue11);

        rightPanel.add(Box.createRigidArea(new Dimension(0,80)));
        rightPanel.add(row1R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row2R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row3R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row4R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row5R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row6R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row7R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row8R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row9R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row10R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row11R);
        rightPanel.add(Box.createVerticalGlue());

        this.add(leftPanel);
        this.add(Box.createRigidArea(new Dimension(100,0)));
        this.add(rightPanel);

    }

}