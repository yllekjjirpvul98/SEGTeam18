package View;

import Model.Filter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class FilterPanel extends JPanel {

    private DashboardPanel dashboardPanel;

    public FilterPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.init();
    }

    private void init(){
        Filter fil = dashboardPanel.getWindow().getControl().getModel().getFilter();

        //  ---- Creating components ----
        JLabel titleLabel = new JLabel("Filters");
        titleLabel.setFont(new Font("Courier", Font.PLAIN, 30));

        JCheckBox dateRangeCB = new JCheckBox(" Date Range ");
        dateRangeCB.setFont(new Font("Courier", Font.PLAIN, 15));

        dateRangeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currDateFilter = fil.getDateRangeSelected();
                fil.setDateRangeSelected(!currDateFilter);
            }
        });

        JCheckBox genderCB = new JCheckBox(" Gender     ");
        genderCB.setFont(new Font("Courier", Font.PLAIN, 15));

        genderCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currGenderFilter = fil.getGenderSelected();
                fil.setGenderSelected(!currGenderFilter);
            }
        });

        JCheckBox ageCB = new JCheckBox(" Age        ");
        ageCB.setFont(new Font("Courier", Font.PLAIN, 15));

        ageCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currAgeFilter = fil.getAgeSelected();
                fil.setAgeSelected(!currAgeFilter);
            }
        });

        JCheckBox incomeCB = new JCheckBox(" Income     ");
        incomeCB.setFont(new Font("Courier", Font.PLAIN, 15));

        incomeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currIncomeFilter = fil.getIncomeSelected();
                fil.setIncomeSelected(!currIncomeFilter);
            }
        });

        JCheckBox contextCB = new JCheckBox(" Context    ");
        contextCB.setFont(new Font("Courier", Font.PLAIN, 15));

        contextCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currContextFilter = fil.getContextSelected();
                fil.setContextSelected(!currContextFilter);
            }
        });


        JTextField date1 = new JTextField("yyyy-mm-dd");
        date1.setFont(new Font("Courier", Font.PLAIN, 15));
        date1.setMaximumSize(new Dimension(200,30));
        JLabel date1Label = new JLabel("Start Date");
        date1Label.setFont(new Font("Courier", Font.PLAIN, 10));

        JTextField date2 = new JTextField("yyyy-mm-dd");
        date2.setFont(new Font("Courier", Font.PLAIN, 15));
        date2.setMaximumSize(new Dimension(200,30));
        JLabel date2Label = new JLabel("End Date");
        date2Label.setFont(new Font("Courier", Font.PLAIN, 10));


        ButtonGroup genGroup = new ButtonGroup();

        JRadioButton genRad1 = new JRadioButton("Male       ");
        genRad1.setFont(new Font("Courier", Font.PLAIN, 15));

        genRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setGender("Male");
            }
        });

        JRadioButton genRad2 = new JRadioButton("Female     ");
        genRad2.setFont(new Font("Courier", Font.PLAIN, 15));

        genRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setGender("Female");
            }
        });

        genGroup.add(genRad1);
        genGroup.add(genRad2);


        ButtonGroup ageGroup = new ButtonGroup();

        JRadioButton ageRad1 = new JRadioButton("< 25");
        ageRad1.setFont(new Font("Courier", Font.PLAIN, 15));

        ageRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge("<25");
            }
        });

        JRadioButton ageRad2 = new JRadioButton("25 - 34");
        ageRad2.setFont(new Font("Courier", Font.PLAIN, 15));

        ageRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge("25-34");
            }
        });

        JRadioButton ageRad3 = new JRadioButton("35 - 44");
        ageRad3.setFont(new Font("Courier", Font.PLAIN, 15));

        ageRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge("35-44");
            }
        });

        JRadioButton ageRad4 = new JRadioButton("> 54");
        ageRad4.setFont(new Font("Courier", Font.PLAIN, 15));

        ageRad4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge(">54");
            }
        });

        ageGroup.add(ageRad1);
        ageGroup.add(ageRad2);
        ageGroup.add(ageRad3);
        ageGroup.add(ageRad4);


        ButtonGroup incomeGroup = new ButtonGroup();

        JRadioButton incomeRad1 = new JRadioButton("Low");
        incomeRad1.setFont(new Font("Courier", Font.PLAIN, 15));

        incomeRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setIncome("Low");
            }
        });

        JRadioButton incomeRad2 = new JRadioButton("Mid");
        incomeRad2.setFont(new Font("Courier", Font.PLAIN, 15));

        incomeRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setIncome("Medium");
            }
        });

        JRadioButton incomeRad3 = new JRadioButton("High");
        incomeRad3.setFont(new Font("Courier", Font.PLAIN, 15));

        incomeRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setIncome("High");
            }
        });

        incomeGroup.add(incomeRad1);
        incomeGroup.add(incomeRad2);
        incomeGroup.add(incomeRad3);

        ButtonGroup conGroup = new ButtonGroup();

        JRadioButton conRad1 = new JRadioButton("News");
        conRad1.setFont(new Font("Courier", Font.PLAIN, 15));

        conRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.News);
            }
        });

        JRadioButton conRad2 = new JRadioButton("Shopping");
        conRad2.setFont(new Font("Courier", Font.PLAIN, 15));

        conRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.Shopping);
            }
        });

        JRadioButton conRad3 = new JRadioButton("Social Media");
        conRad3.setFont(new Font("Courier", Font.PLAIN, 15));

        conRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.SocialMedia);
            }
        });

        JRadioButton conRad4 = new JRadioButton("Blog");
        conRad4.setFont(new Font("Courier", Font.PLAIN, 15));

        conRad4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.Blog);
            }
        });

        JRadioButton conRad5 = new JRadioButton("Hobbies");
        conRad5.setFont(new Font("Courier", Font.PLAIN, 15));

        conRad5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.Hobbies);
            }
        });

        JRadioButton conRad6 = new JRadioButton("Travel");
        conRad6.setFont(new Font("Courier", Font.PLAIN, 15));

        conRad6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.Travel);
            }
        });

        conGroup.add(conRad1);
        conGroup.add(conRad2);
        conGroup.add(conRad3);
        conGroup.add(conRad4);
        conGroup.add(conRad5);
        conGroup.add(conRad6);

        JButton applyBut = new JButton("Apply");
        applyBut.setFont(new Font( "Courier", Font.BOLD, 15));
        applyBut.setBackground(new Color(0x76B8FF));

        applyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sDate = date1.getText();
                String eDate = date2.getText();

                if(fil.getDateRangeSelected()) {
                    fil.setdateLowerRange(Date.valueOf(sDate));
                    fil.setDateUpperRange(Date.valueOf(eDate));
                }

                dashboardPanel.getDataPanel().updateData();

                System.out.println("-----Filter values-----");
                if(fil.getDateRangeSelected()) {
                    System.out.println("Start Date: " + fil.getDateLowerRange());
                    System.out.println("Start Date: " + fil.getDateUpperRange());
                }
                if(fil.getGenderSelected())
                    System.out.println("Gender: " + fil.getGender());
                if(fil.getAgeSelected())
                    System.out.println("Age: " + fil.getAge());
                if(fil.getIncomeSelected())
                    System.out.println("Income: " + fil.getIncome());
                if(fil.getContextSelected())
                    System.out.println("Context: " + fil.getContext());
                System.out.println("----------------------");
            }
        });


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
        leftPanel.add(Box.createRigidArea(new Dimension(0,80)));
        leftPanel.add(row3L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,50)));
        leftPanel.add(row4L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,70)));
        leftPanel.add(row5L);
        leftPanel.add(Box.createRigidArea(new Dimension(0,70)));
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
        row3R.add(Box.createRigidArea(new Dimension(25,0)));
        row3R.add(genRad2);

        JPanel row6R = new JPanel();
        row6R.setLayout(new BoxLayout(row6R, BoxLayout.LINE_AXIS));
        row6R.add(ageRad1);
        row6R.add(Box.createRigidArea(new Dimension(25,0)));
        row6R.add(ageRad2);
        row6R.add(Box.createRigidArea(new Dimension(25,0)));
        row6R.add(ageRad3);
        row6R.add(Box.createRigidArea(new Dimension(25,0)));
        row6R.add(ageRad4);

        JPanel row7R = new JPanel();
        row7R.setLayout(new BoxLayout(row7R, BoxLayout.LINE_AXIS));
        row7R.add(incomeRad1);
        row7R.add(Box.createRigidArea(new Dimension(25,0)));
        row7R.add(incomeRad2);
        row7R.add(Box.createRigidArea(new Dimension(25,0)));
        row7R.add(incomeRad3);

        JPanel row8R = new JPanel();
        row8R.setLayout(new BoxLayout(row8R, BoxLayout.LINE_AXIS));
        row8R.add(conRad1);
        row8R.add(Box.createRigidArea(new Dimension(25,0)));
        row8R.add(conRad2);
        row8R.add(Box.createRigidArea(new Dimension(25,0)));
        row8R.add(conRad3);


        JPanel row10R = new JPanel();
        row10R.setLayout(new BoxLayout(row10R, BoxLayout.LINE_AXIS));
        row10R.add(conRad4);
        row10R.add(Box.createRigidArea(new Dimension(25,0)));
        row10R.add(conRad5);
        row10R.add(Box.createRigidArea(new Dimension(25,0)));
        row10R.add(conRad6);

        JPanel row11R = new JPanel();
        row11R.setLayout(new BoxLayout(row11R, BoxLayout.LINE_AXIS));
        row11R.add(Box.createHorizontalGlue());
        row11R.add(applyBut);

        rightPanel.add(Box.createRigidArea(new Dimension(0,60)));
        rightPanel.add(row1R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,40)));
        rightPanel.add(row2R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,40)));
        rightPanel.add(row3R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,50)));
        rightPanel.add(row6R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,60)));
        rightPanel.add(row7R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,60)));
        rightPanel.add(row10R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(row8R);
        rightPanel.add(Box.createRigidArea(new Dimension(0,40)));
        rightPanel.add(row11R);
        rightPanel.add(Box.createVerticalGlue());


        this.add(leftPanel);
        this.add(Box.createRigidArea(new Dimension(100,0)));
        this.add(rightPanel);
    }


}
