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

        //this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
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

        JLabel titleLabel = new JLabel("Key Metrics");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(titleLabel);

        JLabel metricLabel1 = new JLabel("Number of Impressions: ");
        JLabel metricValue1 = new JLabel(String.valueOf(numImpressions));
        JLabel metricLabel2 = new JLabel("Number of Clicks:");
        JLabel metricValue2 = new JLabel(String.valueOf(numClicks));
        JLabel metricLabel3 = new JLabel("Number of Uniques:");
        JLabel metricValue3 = new JLabel(String.valueOf(numUniques));
        JLabel metricLabel4 = new JLabel("Number of Conversations:");
        JLabel metricValue4 = new JLabel(String.valueOf(numConvos));
        JLabel metricLabel5 = new JLabel("Number of Bounces:");
        JLabel metricValue5 = new JLabel(String.valueOf(numBounces));
        JLabel metricLabel6 = new JLabel("Bounce Rate:");
        JLabel metricValue6 = new JLabel(String.valueOf(bounceRate));
        JLabel metricLabel7 = new JLabel("Total Cost:");
        JLabel metricValue7 = new JLabel(String.valueOf(totalCost));
        JLabel metricLabel8 = new JLabel("Cost per Acquisition:");
        JLabel metricValue8 = new JLabel(String.valueOf(CPA));
        JLabel metricLabel9 = new JLabel("Cost per Click:");
        JLabel metricValue9 = new JLabel(String.valueOf(CPC));
        JLabel metricLabel10 = new JLabel("Cost per Thousand Impressions:");
        JLabel metricValue10 = new JLabel(String.valueOf(CPM));
        JLabel metricLabel11 = new JLabel("Click Through Rate:");
        JLabel metricValue11 = new JLabel(String.valueOf(CTR));

        this.add(metricLabel1);
        this.add(metricValue1);
        this.add(metricLabel2);
        this.add(metricValue2);
        this.add(metricLabel3);
        this.add(metricValue3);
        this.add(metricLabel4);
        this.add(metricValue4);
        this.add(metricLabel5);
        this.add(metricValue5);
        this.add(metricLabel6);
        this.add(metricValue6);
        this.add(metricLabel7);
        this.add(metricValue7);
        this.add(metricLabel8);
        this.add(metricValue8);
        this.add(metricLabel9);
        this.add(metricValue9);
        this.add(metricLabel10);
        this.add(metricValue10);
        this.add(metricLabel11);
        this.add(metricValue11);

    }

}
