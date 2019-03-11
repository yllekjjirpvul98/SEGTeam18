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
        titleLabel.setFont(dashboardPanel.getWindow().getSubHeadingFont());

        JCheckBox dateRangeCB = new JCheckBox(" Date Range ");
        dateRangeCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        dateRangeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currDateFilter = fil.getDateRangeSelected();
                fil.setDateRangeSelected(!currDateFilter);
            }
        });

        JCheckBox genderCB = new JCheckBox(" Gender     ");
        genderCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        genderCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currGenderFilter = fil.getGenderSelected();
                fil.setGenderSelected(!currGenderFilter);
            }
        });

        JCheckBox ageCB = new JCheckBox(" Age        ");
        ageCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        ageCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currAgeFilter = fil.getAgeSelected();
                fil.setAgeSelected(!currAgeFilter);
            }
        });

        JCheckBox incomeCB = new JCheckBox(" Income     ");
        incomeCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        incomeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currIncomeFilter = fil.getIncomeSelected();
                fil.setIncomeSelected(!currIncomeFilter);
            }
        });

        JCheckBox contextCB = new JCheckBox(" Context    ");
        contextCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        contextCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currContextFilter = fil.getContextSelected();
                fil.setContextSelected(!currContextFilter);
            }
        });


        JTextField date1 = new JTextField("yyyy-mm-dd");
        date1.setFont(dashboardPanel.getWindow().getTextFont());
        date1.setMaximumSize(new Dimension(200,30));
        JLabel date1Label = new JLabel("Start Date");
        date1Label.setFont(dashboardPanel.getWindow().getTextFont());

        JTextField date2 = new JTextField("yyyy-mm-dd");
        date2.setFont(dashboardPanel.getWindow().getTextFont());
        date2.setMaximumSize(new Dimension(200,30));
        JLabel date2Label = new JLabel("End Date");
        date2Label.setFont(dashboardPanel.getWindow().getTextFont());


        ButtonGroup genGroup = new ButtonGroup();

        JRadioButton genRad1 = new JRadioButton("Male       ");
        genRad1.setFont(dashboardPanel.getWindow().getTextFont());

        genRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setGender("Male");
            }
        });

        JRadioButton genRad2 = new JRadioButton("Female     ");
        genRad2.setFont(dashboardPanel.getWindow().getTextFont());

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
        ageRad1.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge("<25");
            }
        });

        JRadioButton ageRad2 = new JRadioButton("25 - 34");
        ageRad2.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge("25-34");
            }
        });

        JRadioButton ageRad3 = new JRadioButton("35 - 44");
        ageRad3.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge("35-44");
            }
        });

        JRadioButton ageRad4 = new JRadioButton("45 - 54");
        ageRad4.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge("45-54");
            }
        });

        JRadioButton ageRad5 = new JRadioButton("> 54");
        ageRad5.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setAge(">54");
            }
        });

        ageGroup.add(ageRad1);
        ageGroup.add(ageRad2);
        ageGroup.add(ageRad3);
        ageGroup.add(ageRad4);
        ageGroup.add(ageRad5);


        ButtonGroup incomeGroup = new ButtonGroup();

        JRadioButton incomeRad1 = new JRadioButton("Low");
        incomeRad1.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setIncome("Low");
            }
        });

        JRadioButton incomeRad2 = new JRadioButton("Mid");
        incomeRad2.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setIncome("Medium");
            }
        });

        JRadioButton incomeRad3 = new JRadioButton("High");
        incomeRad3.setFont(dashboardPanel.getWindow().getTextFont());

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
        conRad1.setFont(dashboardPanel.getWindow().getTextFont());

        conRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.News);
            }
        });

        JRadioButton conRad2 = new JRadioButton("Shopping");
        conRad2.setFont(dashboardPanel.getWindow().getTextFont());

        conRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.Shopping);
            }
        });

        JRadioButton conRad3 = new JRadioButton("Social Media");
        conRad3.setFont(dashboardPanel.getWindow().getTextFont());

        conRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.SocialMedia);
            }
        });

        JRadioButton conRad4 = new JRadioButton("Blog");
        conRad4.setFont(dashboardPanel.getWindow().getTextFont());

        conRad4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.Blog);
            }
        });

        JRadioButton conRad5 = new JRadioButton("Hobbies");
        conRad5.setFont(dashboardPanel.getWindow().getTextFont());

        conRad5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fil.setContext(Filter.Context.Hobbies);
            }
        });

        JRadioButton conRad6 = new JRadioButton("Travel");
        conRad6.setFont(dashboardPanel.getWindow().getTextFont());

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
        applyBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        applyBut.setBackground(dashboardPanel.getWindow().getHighlightColor());

        applyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sDate = date1.getText();
                String eDate = date2.getText();

                String dateFormat = "([0-9]{4})-([0-9]{2})-([0-9]{2})";

                if(eDate.matches(dateFormat) && sDate.matches(dateFormat)){
                    fil.setdateLowerRange(Date.valueOf(sDate));
                    fil.setDateUpperRange(Date.valueOf(eDate));
                } else{
                    System.out.println("Invalid Date Format");
                    fil.setDateRangeSelected(false);
                    dateRangeCB.setSelected(false);
                }

                dashboardPanel.getDataPanel().updateData();

                System.out.println("-----Filter values-----");
                if(fil.getDateRangeSelected()) {
                    System.out.println("Start Date: " + fil.getDateLowerRange());
                    System.out.println("End Date: " + fil.getDateUpperRange());
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
        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.PAGE_AXIS));

        JPanel c1r1 = new JPanel();
        c1r1.setLayout(new BoxLayout(c1r1, BoxLayout.LINE_AXIS));

        c1r1.add(date1);
        c1r1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        c1r1.add(date1Label);

        JPanel c1r2 = new JPanel();
        c1r2.setLayout(new BoxLayout(c1r2, BoxLayout.LINE_AXIS));

        c1r2.add(date2);
        c1r2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        c1r2.add(date2Label);

        col1.add(Box.createVerticalGlue());
        col1.add(dateRangeCB);
        col1.add(Box.createVerticalGlue());
        col1.add(c1r1);
        col1.add(Box.createVerticalGlue());
        col1.add(c1r2);
        col1.add(Box.createVerticalGlue());


        JPanel col2 = new JPanel();
        col2.setLayout(new BoxLayout(col2, BoxLayout.PAGE_AXIS));

        col2.add(Box.createVerticalGlue());
        col2.add(genderCB);
        col2.add(Box.createVerticalGlue());
        col2.add(genRad1);
        col2.add(Box.createVerticalGlue());
        col2.add(genRad2);
        col2.add(Box.createVerticalGlue());


        JPanel col3 = new JPanel();
        col3.setLayout(new BoxLayout(col3, BoxLayout.PAGE_AXIS));

        JPanel c3r1 = new JPanel();
        c3r1.setLayout(new BoxLayout(c3r1, BoxLayout.LINE_AXIS));

        c3r1.add(ageRad1);
        c3r1.add(Box.createHorizontalGlue());
        c3r1.add(ageRad2);

        JPanel c3r2 = new JPanel();
        c3r2.setLayout(new BoxLayout(c3r2, BoxLayout.LINE_AXIS));

        c3r2.add(ageRad3);
        c3r2.add(Box.createHorizontalGlue());
        c3r2.add(ageRad4);

        JPanel c3r3 = new JPanel();
        c3r3.setLayout(new BoxLayout(c3r3, BoxLayout.LINE_AXIS));

        c3r3.add(ageRad5);
        c3r3.add(Box.createHorizontalGlue());

        col3.add(Box.createVerticalGlue());
        col3.add(ageCB);
        col3.add(Box.createVerticalGlue());
        col3.add(c3r1);
        col3.add(Box.createVerticalGlue());
        col3.add(c3r2);
        col3.add(Box.createVerticalGlue());
        col3.add(c3r3);
        col3.add(Box.createVerticalGlue());


        JPanel col4 = new JPanel();
        col4.setLayout(new BoxLayout(col4, BoxLayout.PAGE_AXIS));

        col4.add(Box.createVerticalGlue());
        col4.add(incomeCB);
        col4.add(Box.createVerticalGlue());
        col4.add(incomeRad1);
        col4.add(Box.createVerticalGlue());
        col4.add(incomeRad2);
        col4.add(Box.createVerticalGlue());
        col4.add(incomeRad3);
        col4.add(Box.createVerticalGlue());

        JPanel col5 = new JPanel();
        col5.setLayout(new BoxLayout(col5, BoxLayout.PAGE_AXIS));

        JPanel c5r1 = new JPanel();
        c5r1.setLayout(new BoxLayout(c5r1, BoxLayout.LINE_AXIS));

        c5r1.add(conRad1);
        c5r1.add(Box.createHorizontalGlue());
        c5r1.add(conRad2);

        JPanel c5r2 = new JPanel();
        c5r2.setLayout(new BoxLayout(c5r2, BoxLayout.LINE_AXIS));

        c5r2.add(conRad3);
        c5r2.add(Box.createHorizontalGlue());
        c5r2.add(conRad4);

        JPanel c5r3 = new JPanel();
        c5r3.setLayout(new BoxLayout(c5r3, BoxLayout.LINE_AXIS));

        c5r3.add(conRad5);
        c5r3.add(Box.createHorizontalGlue());
        c5r3.add(conRad6);

        col5.add(Box.createVerticalGlue());
        col5.add(contextCB);
        col5.add(Box.createVerticalGlue());
        col5.add(c5r1);
        col5.add(Box.createVerticalGlue());
        col5.add(c5r2);
        col5.add(Box.createVerticalGlue());
        col5.add(c5r3);
        col5.add(Box.createVerticalGlue());

        JPanel col6 = new JPanel();
        col6.setLayout(new BoxLayout(col6, BoxLayout.PAGE_AXIS));

        col6.add(Box.createVerticalGlue());
        col6.add(applyBut);
        col6.add(Box.createVerticalGlue());


        this.add(Box.createRigidArea(new Dimension(0,150)));
        this.add(Box.createHorizontalGlue());
        this.add(col1);
        this.add(Box.createHorizontalGlue());
        this.add(col2);
        this.add(Box.createHorizontalGlue());
        this.add(col4);
        this.add(Box.createHorizontalGlue());
        this.add(col3);
        this.add(Box.createHorizontalGlue());
        this.add(col5);
        this.add(Box.createHorizontalGlue());
        this.add(col6);
        this.add(Box.createHorizontalGlue());

    }


}
