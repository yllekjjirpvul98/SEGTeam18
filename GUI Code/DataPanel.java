import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {

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


    public DataPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row1.add(Box.createRigidArea(new Dimension(25,0)));
        row1.add(metricLabel1);
        row1.add(Box.createRigidArea(new Dimension(50,0)));
        row1.add(metricValue1);

        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
        row2.add(Box.createRigidArea(new Dimension(25,0)));
        row2.add(metricLabel2);
        row2.add(Box.createRigidArea(new Dimension(50,0)));
        row2.add(metricValue2);

        JPanel row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
        row3.add(Box.createRigidArea(new Dimension(25,0)));
        row3.add(metricLabel3);
        row3.add(Box.createRigidArea(new Dimension(50,0)));
        row3.add(metricValue3);

        JPanel row4 = new JPanel();
        row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
        row4.add(Box.createRigidArea(new Dimension(25,0)));
        row4.add(metricLabel4);
        row4.add(Box.createRigidArea(new Dimension(50,0)));
        row4.add(metricValue4);

        JPanel row5 = new JPanel();
        row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));
        row5.add(Box.createRigidArea(new Dimension(25,0)));
        row5.add(metricLabel5);
        row5.add(Box.createRigidArea(new Dimension(50,0)));
        row5.add(metricValue5);

        JPanel row6 = new JPanel();
        row6.setLayout(new BoxLayout(row6, BoxLayout.LINE_AXIS));
        row6.add(Box.createRigidArea(new Dimension(25,0)));
        row6.add(metricLabel6);
        row6.add(Box.createRigidArea(new Dimension(50,0)));
        row6.add(metricValue6);

        JPanel row7 = new JPanel();
        row7.setLayout(new BoxLayout(row7, BoxLayout.LINE_AXIS));
        row7.add(Box.createRigidArea(new Dimension(25,0)));
        row7.add(metricLabel7);
        row7.add(Box.createRigidArea(new Dimension(50,0)));
        row7.add(metricValue7);

        JPanel row8 = new JPanel();
        row8.setLayout(new BoxLayout(row8, BoxLayout.LINE_AXIS));
        row8.add(Box.createRigidArea(new Dimension(25,0)));
        row8.add(metricLabel8);
        row8.add(Box.createRigidArea(new Dimension(50,0)));
        row8.add(metricValue8);

        JPanel row9 = new JPanel();
        row9.setLayout(new BoxLayout(row9, BoxLayout.LINE_AXIS));
        row9.add(Box.createRigidArea(new Dimension(25,0)));
        row9.add(metricLabel9);
        row9.add(Box.createRigidArea(new Dimension(50,0)));
        row9.add(metricValue9);

        JPanel row10 = new JPanel();
        row10.setLayout(new BoxLayout(row10, BoxLayout.LINE_AXIS));
        row10.add(Box.createRigidArea(new Dimension(25,0)));
        row10.add(metricLabel10);
        row10.add(Box.createRigidArea(new Dimension(50,0)));
        row10.add(metricValue10);

        JPanel row11 = new JPanel();
        row11.setLayout(new BoxLayout(row11, BoxLayout.LINE_AXIS));
        row11.add(Box.createRigidArea(new Dimension(25,0)));
        row11.add(metricLabel11);
        row11.add(Box.createRigidArea(new Dimension(50,0)));
        row11.add(metricValue11);


        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0,40)));
        this.add(row1);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row2);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row3);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row4);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row5);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row6);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row7);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row8);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row9);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row10);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row11);
        this.add(Box.createVerticalGlue());

    }

}