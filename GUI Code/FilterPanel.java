import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {

    private DashboardPanel dashboardPanel;

    public FilterPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.init();
    }

    private void init(){

        //  ---- Creating components ----
        JLabel titleLabel = new JLabel("Filters");
        titleLabel.setFont(new Font("Courier", Font.PLAIN, 30));

        JCheckBox dateRangeCB = new JCheckBox(" Date Range ");
        dateRangeCB.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox genderCB = new JCheckBox(" Gender     ");
        genderCB.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox ageCB = new JCheckBox(" Age        ");
        ageCB.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox incomeCB = new JCheckBox(" Income     ");
        incomeCB.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox contextCB = new JCheckBox(" Context    ");
        contextCB.setFont(new Font("Courier", Font.PLAIN, 15));


        JTextField date1 = new JTextField("dd/mm/yy");
        date1.setFont(new Font("Courier", Font.PLAIN, 15));
        date1.setMaximumSize(new Dimension(200,30));
        JLabel date1Label = new JLabel("Start Date");
        date1Label.setFont(new Font("Courier", Font.PLAIN, 10));

        JTextField date2 = new JTextField("dd/mm/yy");
        date2.setFont(new Font("Courier", Font.PLAIN, 15));
        date2.setMaximumSize(new Dimension(200,30));
        JLabel date2Label = new JLabel("End Date");
        date2Label.setFont(new Font("Courier", Font.PLAIN, 10));


        ButtonGroup genderButtons = new ButtonGroup();
        JRadioButton genRad1 = new JRadioButton("Male       ");
        genRad1.setFont(new Font("Courier", Font.PLAIN, 15));

        JRadioButton genRad2 = new JRadioButton("Female     ");
        genRad2.setFont(new Font("Courier", Font.PLAIN, 15));

        JRadioButton genRad3 = new JRadioButton("Unspecified");
        genRad3.setFont(new Font("Courier", Font.PLAIN, 15));

        genderButtons.add(genRad1);
        genderButtons.add(genRad2);
        genderButtons.add(genRad3);


        JSlider ageSlider = new JSlider();
        ageSlider.setMinimum(0);
        ageSlider.setMaximum(99);
        ageSlider.setMaximumSize(new Dimension(200,30));
        JLabel ageLabel = new JLabel("Years");
        ageLabel.setFont(new Font("Courier", Font.PLAIN, 10));


        JSlider moneySlider = new JSlider();
        moneySlider.setMinimum(0);
        moneySlider.setMaximum(99);
        moneySlider.setMaximumSize(new Dimension(200,30));
        JLabel moneyLabel = new JLabel("£1000's");
        moneyLabel.setFont(new Font("Courier", Font.PLAIN, 10));


        JCheckBox conCB1 = new JCheckBox("News        ");
        conCB1.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox conCB2 = new JCheckBox("Blog        ");
        conCB2.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox conCB3 = new JCheckBox("Hobbies     ");
        conCB3.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox conCB4 = new JCheckBox("Shopping    ");
        conCB4.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox conCB5 = new JCheckBox("Social Media");
        conCB5.setFont(new Font("Courier", Font.PLAIN, 15));

        JCheckBox conCB6 = new JCheckBox("Travel      ");
        conCB6.setFont(new Font("Courier", Font.PLAIN, 15));


        //  ---- Layout ----
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        JPanel row1L = new JPanel();
        row1L.setLayout(new BoxLayout(row1L, BoxLayout.LINE_AXIS));
        row1L.add(titleLabel);
        row1L.add(Box.createHorizontalGlue());

        JPanel row2L = new JPanel();
        row2L.setLayout(new BoxLayout(row2L, BoxLayout.LINE_AXIS));
        row2L.add(Box.createRigidArea(new Dimension(60,0)));
        row2L.add(dateRangeCB);
        row2L.add(Box.createHorizontalGlue());

        JPanel row3L = new JPanel();
        row3L.setLayout(new BoxLayout(row3L, BoxLayout.LINE_AXIS));
        row3L.add(Box.createRigidArea(new Dimension(60,0)));
        row3L.add(genderCB);
        row3L.add(Box.createHorizontalGlue());

        JPanel row4L = new JPanel();
        row4L.setLayout(new BoxLayout(row4L, BoxLayout.LINE_AXIS));
        row4L.add(Box.createRigidArea(new Dimension(60,0)));
        row4L.add(ageCB);
        row4L.add(Box.createHorizontalGlue());

        JPanel row5L = new JPanel();
        row5L.setLayout(new BoxLayout(row5L, BoxLayout.LINE_AXIS));
        row5L.add(Box.createRigidArea(new Dimension(60,0)));
        row5L.add(incomeCB);
        row5L.add(Box.createHorizontalGlue());

        JPanel row6L = new JPanel();
        row6L.setLayout(new BoxLayout(row6L, BoxLayout.LINE_AXIS));
        row6L.add(Box.createRigidArea(new Dimension(60,0)));
        row6L.add(contextCB);
        row6L.add(Box.createHorizontalGlue());

        leftPanel.add(row1L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
        leftPanel.add(row2L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,100)));
        leftPanel.add(row3L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,70)));
        leftPanel.add(row4L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,50)));
        leftPanel.add(row5L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,100)));
        leftPanel.add(row6L);
        leftPanel.add(Box.createVerticalGlue());


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

        JPanel row1R = new JPanel();
        row1R.setLayout(new BoxLayout(row1R, BoxLayout.LINE_AXIS));
        row1R.add(date1);
        row1R.add(Box.createRigidArea(new Dimension(25,0)));
        row1R.add(date1Label);

        JPanel row2R = new JPanel();
        row2R.setLayout(new BoxLayout(row2R, BoxLayout.LINE_AXIS));
        row2R.add(date2);
        row2R.add(Box.createRigidArea(new Dimension(25,0)));
        row2R.add(date2Label);

        JPanel row3R = new JPanel();
        row3R.setLayout(new BoxLayout(row3R, BoxLayout.LINE_AXIS));
        row3R.add(genRad1);

        JPanel row4R = new JPanel();
        row4R.setLayout(new BoxLayout(row4R, BoxLayout.LINE_AXIS));
        row4R.add(genRad2);

        JPanel row5R = new JPanel();
        row5R.setLayout(new BoxLayout(row5R, BoxLayout.LINE_AXIS));
        row5R.add(genRad3);

        JPanel row6R = new JPanel();
        row6R.setLayout(new BoxLayout(row6R, BoxLayout.LINE_AXIS));
        row6R.add(ageSlider);
        row6R.add(Box.createRigidArea(new Dimension(25,0)));
        row6R.add(ageLabel);

        JPanel row7R = new JPanel();
        row7R.setLayout(new BoxLayout(row7R, BoxLayout.LINE_AXIS));
        row7R.add(moneySlider);
        row7R.add(Box.createRigidArea(new Dimension(25,0)));
        row7R.add(moneyLabel);

        JPanel row8R = new JPanel();
        row8R.setLayout(new BoxLayout(row8R, BoxLayout.LINE_AXIS));
        row8R.add(conCB1);
        row8R.add(Box.createRigidArea(new Dimension(25,0)));
        row8R.add(conCB2);

        JPanel row9R = new JPanel();
        row9R.setLayout(new BoxLayout(row9R, BoxLayout.LINE_AXIS));
        row9R.add(conCB3);
        row9R.add(Box.createRigidArea(new Dimension(25,0)));
        row9R.add(conCB4);

        JPanel row10R = new JPanel();
        row10R.setLayout(new BoxLayout(row10R, BoxLayout.LINE_AXIS));
        row10R.add(conCB5);
        row10R.add(Box.createRigidArea(new Dimension(25,0)));
        row10R.add(conCB6);

        rightPanel.add(Box.createRigidArea(new Dimension(0,60)));
        rightPanel.add(row1R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row2R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,40)));
        rightPanel.add(row3R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(row4R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(row5R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,40)));
        rightPanel.add(row6R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,60)));
        rightPanel.add(row7R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,60)));
        rightPanel.add(row8R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row9R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row10R);
        rightPanel.add(Box.createVerticalGlue());


        this.add(leftPanel);
        this.add(Box.createRigidArea(new Dimension(100,0)));
        this.add(rightPanel);
    }


}
