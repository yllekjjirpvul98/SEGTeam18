package View;

import Model.Filter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/*
    Class contains all components to provide functionality for the user to change, and apply, the filters
    on the campaign data.
 */

class FilterPanel extends JPanel {

    private DashboardPanel dashboardPanel;

    private JCheckBox dateRangeCB;
    private JCheckBox genderCB;
    private JCheckBox incomeCB;
    private JCheckBox ageCB;
    private JCheckBox contextCB;

    private JFormattedTextField dateText1;
    private JFormattedTextField dateText2;
    private JLabel date1Label;
    private JLabel date2Label;

    private JRadioButton genRad1;
    private JRadioButton genRad2;
    private JRadioButton incomeRad1;
    private JRadioButton incomeRad2;
    private JRadioButton incomeRad3;
    private JRadioButton ageRad1;
    private JRadioButton ageRad2;
    private JRadioButton ageRad3;
    private JRadioButton ageRad4;
    private JRadioButton ageRad5;
    private JRadioButton conRad1;
    private JRadioButton conRad2;
    private JRadioButton conRad3;
    private JRadioButton conRad4;
    private JRadioButton conRad5;
    private JRadioButton conRad6;

    private JButton applyBut;
    private JDatePickerImpl datePicker1;
    private JDatePickerImpl datePicker2;
    private Filter fil;

    private JPanel col1;
    private JPanel c1r1;
    private JPanel c1r2;
    private JPanel col2;
    private JPanel col3;
    private JPanel c3r1;
    private JPanel c3r2;
    private JPanel c3r3;
    private JPanel col4;
    private JPanel col5;
    private JPanel c5r1;
    private JPanel c5r2;
    private JPanel c5r3;
    private JPanel col6;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel centrePanel;

    FilterPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        this.setBackground(dashboardPanel.getWindow().getFilterColor2());
        this.setLayout(new BorderLayout());
        this.init();

        // Initially disable components, enable when corresponding checkbox is checked.
        this.disableAgeRad(false);
        this.disableGenRad(false);
        this.disableIncomeRad(false);
        this.disableConRad(false);
        this.disableDateRange(false);
    }

    // Will uncheck all components - resetting them to their starting state.
    void reset(){
        dateRangeCB.setSelected(false);
        genderCB.setSelected(false);
        ageCB.setSelected(false);
        incomeCB.setSelected(false);
        contextCB.setSelected(false);
        dateText1.setText("");
        dateText2.setText("");
        genRad1.setSelected(false);
        genRad2.setSelected(false);
        incomeRad1.setSelected(false);
        incomeRad2.setSelected(false);
        incomeRad3.setSelected(false);
        ageRad1.setSelected(false);
        ageRad2.setSelected(false);
        ageRad3.setSelected(false);
        ageRad4.setSelected(false);
        ageRad5.setSelected(false);
        conRad1.setSelected(false);
        conRad2.setSelected(false);
        conRad3.setSelected(false);
        conRad4.setSelected(false);
        conRad5.setSelected(false);
        conRad6.setSelected(false);

        this.disableAgeRad(false);
        this.disableGenRad(false);
        this.disableIncomeRad(false);
        this.disableConRad(false);
        this.disableDateRange(false);
    }

    /*
        All checkboxes when clicked set corresponding filter in model to be !currentValue.
        When a radio button is selected add corresponding filter in model.
        When radio button deselected remove corresponding filter in model.
     */
    private void init(){
        //  ---- Creating components ----
        fil = dashboardPanel.getWindow().getControl().getModel().getFilter();

        dateRangeCB = new JCheckBox(" Date Range ");
        dateRangeCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        dateRangeCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        dateRangeCB.addActionListener(e -> {
            boolean currDateFilter = fil.getDateRangeSelected();
            fil.setDateRangeSelected(!currDateFilter);

            disableDateRange(fil.getDateRangeSelected());
        });

        genderCB = new JCheckBox(" Gender     ");
        genderCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        genderCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        genderCB.addActionListener(e -> {
            boolean currGenderFilter = fil.getGenderSelected();
            fil.setGenderSelected(!currGenderFilter);

            disableGenRad(fil.getGenderSelected());
        });

        ageCB = new JCheckBox(" Age        ");
        ageCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        ageCB.addActionListener(e -> {
            boolean currAgeFilter = fil.getAgeSelected();
            fil.setAgeSelected(!currAgeFilter);

            disableAgeRad(fil.getAgeSelected());
        });

        incomeCB = new JCheckBox(" Income     ");
        incomeCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        incomeCB.addActionListener(e -> {
            boolean currIncomeFilter = fil.getIncomeSelected();
            fil.setIncomeSelected(!currIncomeFilter);

            disableIncomeRad(fil.getIncomeSelected());
        });

        contextCB = new JCheckBox(" Context    ");
        contextCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        contextCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        contextCB.addActionListener(e -> {
            boolean currContextFilter = fil.getContextSelected();
            fil.setContextSelected(!currContextFilter);

            disableConRad(fil.getContextSelected());
        });

        //METHOD TO RETURN THE FIRST DATA DATE OF THE CAMPAIGN (RESET THE DATE AND JDATEPICKER WHEN NEW CAMPAIGN LOADED)
        int year = 2015;
        int month = 0;

        // Create JDatePickers to allow the user to select start/end date to filter on.
        SqlDateModel dateModel1 = new SqlDateModel();
        dateModel1.setDate(year,month,1);
        Properties dateProp1 = new Properties();
        dateProp1.put("text.today", "Today");
        dateProp1.put("text.month", "Month");
        dateProp1.put("text.year", "Year");

        JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1, dateProp1);
        datePicker1 = new JDatePickerImpl(datePanel1, new JFormattedTextField.AbstractFormatter() {
            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        });

        SqlDateModel dateModel2 = new SqlDateModel();
        dateModel2.setDate(year,month,1);
        Properties dateProp2 = new Properties();
        dateProp2.put("text.today", "Today");
        dateProp2.put("text.month", "Month");
        dateProp2.put("text.year", "Year");

        JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2, dateProp2);
        datePicker2 = new JDatePickerImpl(datePanel2, new JFormattedTextField.AbstractFormatter() {
            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        });

        dateText1 = datePicker1.getJFormattedTextField();
        dateText1.setFont(dashboardPanel.getWindow().getTextFont());
        dateText1.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        dateText1.setMaximumSize(new Dimension(dashboardPanel.getWindow().getButtonBigFont().getSize() * 10, dateText1.getHeight()));

        dateText2 = datePicker2.getJFormattedTextField();
        dateText2.setFont(dashboardPanel.getWindow().getTextFont());
        dateText2.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        dateText2.setMaximumSize(new Dimension(dashboardPanel.getWindow().getButtonBigFont().getSize() * 10, dateText2.getHeight()));

        date1Label = new JLabel("Start");
        date1Label.setFont(dashboardPanel.getWindow().getTextFont());

        date2Label = new JLabel(" End ");
        date2Label.setFont(dashboardPanel.getWindow().getTextFont());

        //Radio buttons for remaining filters.
        genRad1 = new JRadioButton("Male");
        genRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        genRad1.setFont(dashboardPanel.getWindow().getTextFont());

        genRad1.addActionListener(e -> {
            if(genRad1.isSelected() && !(fil.getGender().contains("Male"))) {
                fil.setGender("Male");
            }
            if(!(genRad1.isSelected()) && fil.getGender().contains("Male")){
                fil.getGender().remove("Male");
            }
        });

        genRad2 = new JRadioButton("Female");
        genRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        genRad2.setFont(dashboardPanel.getWindow().getTextFont());

        genRad2.addActionListener(e -> {
            if(genRad2.isSelected() && !(fil.getGender().contains("Female"))) {
                fil.setGender("Female");
            }
            if(!(genRad2.isSelected()) && fil.getGender().contains("Female")){
                fil.getGender().remove("Female");
            }
        });


        ageRad1 = new JRadioButton("< 25");
        ageRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad1.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad1.addActionListener(e -> {
            if(ageRad1.isSelected() && !(fil.getAge().contains("<25"))) {
                fil.setAge("<25");
            }
            if(!(ageRad1.isSelected()) && fil.getAge().contains("<25")){
                fil.getAge().remove("<25");
            }
        });

        ageRad2 = new JRadioButton("25 - 34");
        ageRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad2.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad2.addActionListener(e -> {
            if(ageRad2.isSelected() && !(fil.getAge().contains("25-34"))) {
                fil.setAge("25-34");
            }
            if(!(ageRad2.isSelected()) && fil.getAge().contains("25-34")){
                fil.getAge().remove("25-34");
            }
        });

        ageRad3 = new JRadioButton("35 - 44");
        ageRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad3.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad3.addActionListener(e -> {
            if(ageRad3.isSelected() && !(fil.getAge().contains("35-44"))) {
                fil.setAge("35-44");
            }
            if(!(ageRad3.isSelected()) && fil.getAge().contains("35-44")){
                fil.getAge().remove("35-44");
            }
        });

        ageRad4 = new JRadioButton("45 - 54");
        ageRad4.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad4.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad4.addActionListener(e -> {
            if(ageRad4.isSelected() && !(fil.getAge().contains("45-54"))) {
                fil.setAge("45-54");
            }
            if(!(ageRad4.isSelected()) && fil.getAge().contains("45-54")){
                fil.getAge().remove("45-54");
            }
        });

        ageRad5 = new JRadioButton("> 54");
        ageRad5.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad5.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad5.addActionListener(e -> {
            if(ageRad5.isSelected() && !(fil.getAge().contains(">54"))) {
                fil.setAge(">54");
            }
            if(!(ageRad5.isSelected()) && fil.getAge().contains(">54")){
                fil.getAge().remove(">54");
            }
        });

        incomeRad1 = new JRadioButton("Low");
        incomeRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad1.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad1.addActionListener(e -> {
            if(incomeRad1.isSelected() && !(fil.getIncome().contains("Low"))) {
                fil.setIncome("Low");
            }
            if(!(incomeRad1.isSelected()) && fil.getIncome().contains("Low")){
                fil.getIncome().remove("Low");
            }
        });

        incomeRad2 = new JRadioButton("Medium");
        incomeRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad2.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad2.addActionListener(e -> {
            if(incomeRad2.isSelected() && !(fil.getIncome().contains("Medium"))) {
                fil.setIncome("Medium");
            }
            if(!(incomeRad2.isSelected()) && fil.getIncome().contains("Medium")){
                fil.getIncome().remove("Medium");
            }
        });

        incomeRad3 = new JRadioButton("High");
        incomeRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad3.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad3.addActionListener(e -> {
            if(incomeRad3.isSelected() && !(fil.getIncome().contains("High"))) {
                fil.setIncome("High");
            }
            if(!(incomeRad3.isSelected()) && fil.getIncome().contains("High")){
                fil.getIncome().remove("High");
            }
        });


        conRad1 = new JRadioButton("News");
        conRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad1.setFont(dashboardPanel.getWindow().getTextFont());

        conRad1.addActionListener(e -> {
            if(conRad1.isSelected() && !(fil.getContext().contains(Filter.Context.News))) {
                fil.setContext(Filter.Context.News);
            }
            if(!(conRad1.isSelected()) && fil.getContext().contains(Filter.Context.News)){
                fil.getContext().remove(Filter.Context.News);
            }
        });

        conRad2 = new JRadioButton("Shopping");
        conRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad2.setFont(dashboardPanel.getWindow().getTextFont());

        conRad2.addActionListener(e -> {
            if(conRad2.isSelected() && !(fil.getContext().contains(Filter.Context.Shopping))) {
                fil.setContext(Filter.Context.Shopping);
            }
            if(!(conRad2.isSelected()) && fil.getContext().contains(Filter.Context.Shopping)){
                fil.getContext().remove(Filter.Context.Shopping);
            }
        });

        conRad3 = new JRadioButton("Social Media");
        conRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad3.setFont(dashboardPanel.getWindow().getTextFont());

        conRad3.addActionListener(e -> {
            if(conRad3.isSelected() && !(fil.getContext().contains(Filter.Context.SocialMedia))) {
                fil.setContext(Filter.Context.SocialMedia);
            }
            if(!(conRad3.isSelected()) && fil.getContext().contains(Filter.Context.SocialMedia)){
                fil.getContext().remove(Filter.Context.SocialMedia);
            }
        });

        conRad4 = new JRadioButton("Blog");
        conRad4.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad4.setFont(dashboardPanel.getWindow().getTextFont());

        conRad4.addActionListener(e -> {
            if(conRad4.isSelected() && !(fil.getContext().contains(Filter.Context.Blog))) {
                fil.setContext(Filter.Context.Blog);
            }
            if(!(conRad4.isSelected()) && fil.getContext().contains(Filter.Context.Blog)){
                fil.getContext().remove(Filter.Context.Blog);
            }
        });

        conRad5 = new JRadioButton("Hobbies");
        conRad5.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad5.setFont(dashboardPanel.getWindow().getTextFont());

        conRad5.addActionListener(e -> {
            if(conRad5.isSelected() && !(fil.getContext().contains(Filter.Context.Hobbies))) {
                fil.setContext(Filter.Context.Hobbies);
            }
            if(!(conRad5.isSelected()) && fil.getContext().contains(Filter.Context.Hobbies)){
                fil.getContext().remove(Filter.Context.Hobbies);
            }
        });

        conRad6 = new JRadioButton("Travel");
        conRad6.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad6.setFont(dashboardPanel.getWindow().getTextFont());

        conRad6.addActionListener(e -> {
            if(conRad6.isSelected() && !(fil.getContext().contains(Filter.Context.Travel))) {
                fil.setContext(Filter.Context.Travel);
            }
            if(!(conRad6.isSelected()) && fil.getContext().contains(Filter.Context.Travel)){
                fil.getContext().remove(Filter.Context.Travel);
            }
        });


        applyBut = new JButton("Apply");
        applyBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        applyBut.setBackground(dashboardPanel.getWindow().getActionButColor());

        // Call apply method when apply button pressed.
        applyBut.addActionListener(e -> apply());


        //  ---- Layout ----
        col1 = new JPanel();
        col1.setBackground(dashboardPanel.getWindow().getFilterColor());
        col1.setLayout(new BoxLayout(col1, BoxLayout.PAGE_AXIS));

        c1r1 = new JPanel();
        c1r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c1r1.setLayout(new BoxLayout(c1r1, BoxLayout.LINE_AXIS));

       c1r1.add(datePicker1);
       c1r1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
       c1r1.add(date1Label);

        c1r2 = new JPanel();
        c1r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        c1r2.setLayout(new BoxLayout(c1r2, BoxLayout.LINE_AXIS));

        c1r2.add(datePicker2);
        c1r2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        c1r2.add(date2Label);

        col1.add(Box.createVerticalGlue());
        col1.add(dateRangeCB);
        col1.add(Box.createVerticalGlue());
        col1.add(c1r1);
        col1.add(Box.createVerticalGlue());
        col1.add(c1r2);
        col1.add(Box.createVerticalGlue());

        col2 = new JPanel();
        col2.setBackground(dashboardPanel.getWindow().getFilterColor());
        col2.setLayout(new BoxLayout(col2, BoxLayout.PAGE_AXIS));

        col2.add(Box.createVerticalGlue());
        col2.add(genderCB);
        col2.add(Box.createVerticalGlue());
        col2.add(genRad1);
        col2.add(Box.createVerticalGlue());
        col2.add(genRad2);
        col2.add(Box.createVerticalGlue());

        col3 = new JPanel();
        col3.setBackground(dashboardPanel.getWindow().getFilterColor());
        col3.setLayout(new BoxLayout(col3, BoxLayout.PAGE_AXIS));

        c3r1 = new JPanel();
        c3r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c3r1.setLayout(new BoxLayout(c3r1, BoxLayout.LINE_AXIS));

        c3r1.add(ageRad1);
        c3r1.add(Box.createHorizontalGlue());
        c3r1.add(ageRad2);

        c3r2 = new JPanel();
        c3r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        c3r2.setLayout(new BoxLayout(c3r2, BoxLayout.LINE_AXIS));

        c3r2.add(ageRad3);
        c3r2.add(Box.createHorizontalGlue());
        c3r2.add(ageRad4);

        c3r3 = new JPanel();
        c3r3.setBackground(dashboardPanel.getWindow().getFilterColor());
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

        col4 = new JPanel();
        col4.setBackground(dashboardPanel.getWindow().getFilterColor());
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

        col5 = new JPanel();
        col5.setBackground(dashboardPanel.getWindow().getFilterColor());
        col5.setLayout(new BoxLayout(col5, BoxLayout.PAGE_AXIS));

        c5r1 = new JPanel();
        c5r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c5r1.setLayout(new BoxLayout(c5r1, BoxLayout.LINE_AXIS));

        c5r1.add(conRad1);
        c5r1.add(Box.createHorizontalGlue());
        c5r1.add(conRad2);

        c5r2 = new JPanel();
        c5r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        c5r2.setLayout(new BoxLayout(c5r2, BoxLayout.LINE_AXIS));

        c5r2.add(conRad3);
        c5r2.add(Box.createHorizontalGlue());
        c5r2.add(conRad4);

        c5r3 = new JPanel();
        c5r3.setBackground(dashboardPanel.getWindow().getFilterColor());
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

        col6 = new JPanel();
        col6.setBackground(dashboardPanel.getWindow().getFilterColor());
        col6.setLayout(new BoxLayout(col6, BoxLayout.PAGE_AXIS));

        col6.add(Box.createVerticalGlue());
        col6.add(applyBut);
        col6.add(Box.createVerticalGlue());

        col1.setBorder(BorderFactory.createLineBorder(Color.WHITE,1,true));
        col2.setBorder(BorderFactory.createLineBorder(Color.WHITE,1,true));
        col3.setBorder(BorderFactory.createLineBorder(Color.WHITE,1,true));
        col4.setBorder(BorderFactory.createLineBorder(Color.WHITE,1,true));
        col5.setBorder(BorderFactory.createLineBorder(Color.WHITE,1,true));

        centrePanel = new JPanel();
        centrePanel.setBackground(dashboardPanel.getWindow().getFilterColor());
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.LINE_AXIS));

        centrePanel.add(Box.createRigidArea(new Dimension(0,dashboardPanel.getWindow().getButtonTitleFont().getSize() * 4))); //200 at 1440p
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(col1);
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(col2);
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(col4);
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(col3);
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(col5);
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(col6);
        centrePanel.add(Box.createHorizontalGlue());

        northPanel = new JPanel();
        northPanel.setBackground(dashboardPanel.getWindow().getFilterColor());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        northPanel.add(Box.createRigidArea(new Dimension(0, (int) Math.round(dashboardPanel.getWindow().getHightBorderDim().getHeight())/2)));

        southPanel = new JPanel();
        southPanel.setBackground(dashboardPanel.getWindow().getFilterColor());
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));

        southPanel.add(Box.createRigidArea(new Dimension(0, (int) Math.round(dashboardPanel.getWindow().getHightBorderDim().getHeight())/2)));

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(centrePanel, BorderLayout.CENTER);
    }

    /*
        Set the start/end date in the model to the values in JDatePickers if not null.
        If any filter is active but no options in it selected deactivate the filter.
        Apply the filters to the date and the graph.
     */
    void apply(){
        Date date1 = (Date) datePicker1.getModel().getValue();
        Date date2 = (Date) datePicker2.getModel().getValue();

        if(date1 != null && date2 != null) {
            fil.setdateLowerRange(date1);
            fil.setDateUpperRange(date2);
        }

        if(fil.getDateRangeSelected() && (date1 == null || date2 == null)){
            fil.setDateRangeSelected(false);
            dateRangeCB.setSelected(false);
            disableDateRange(false);
        }

        if(fil.getIncomeSelected() && fil.getIncome().isEmpty()){
            fil.setIncomeSelected(false);
            incomeCB.setSelected(false);
            disableIncomeRad(false);
        }

        if(fil.getGenderSelected() && fil.getGender().isEmpty()){
            fil.setGenderSelected(false);
            genderCB.setSelected(false);
            disableGenRad(false);
        }

        if(fil.getAgeSelected() && fil.getAge().isEmpty()){
            fil.setAgeSelected(false);
            ageCB.setSelected(false);
            disableAgeRad(false);
        }

        if(fil.getContextSelected() && fil.getContext().isEmpty()){
            fil.setContextSelected(false);
            contextCB.setSelected(false);
            disableConRad(false);
        }

        dashboardPanel.getDataPanel().updateData();

        dashboardPanel.getGraphPanel().getGraph().updateSeries();
    }

    // Methods for enabling/disabling components for a filter.
    private void disableDateRange(boolean isEnabled){
        dateText1.setEnabled(isEnabled);
        dateText2.setEnabled(isEnabled);
        date1Label.setEnabled(isEnabled);
        date2Label.setEnabled(isEnabled);
    }

    private void disableGenRad(boolean isEnabled){
        genRad1.setEnabled(isEnabled);
        genRad2.setEnabled(isEnabled);
    }

    private void disableAgeRad(boolean isEnabled){
        ageRad1.setEnabled(isEnabled);
        ageRad2.setEnabled(isEnabled);
        ageRad3.setEnabled(isEnabled);
        ageRad4.setEnabled(isEnabled);
        ageRad5.setEnabled(isEnabled);
    }

    private void disableIncomeRad(boolean isEnabled){
        incomeRad1.setEnabled(isEnabled);
        incomeRad2.setEnabled(isEnabled);
        incomeRad3.setEnabled(isEnabled);
    }

    private void disableConRad(boolean isEnabled){
        conRad1.setEnabled(isEnabled);
        conRad2.setEnabled(isEnabled);
        conRad3.setEnabled(isEnabled);
        conRad4.setEnabled(isEnabled);
        conRad5.setEnabled(isEnabled);
        conRad6.setEnabled(isEnabled);
    }

    // Update GUI colours.
    public void updateColors(){
        this.setBackground(dashboardPanel.getWindow().getFilterColor2());
        dateRangeCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        genderCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        contextCB.setBackground(dashboardPanel.getWindow().getFilterColor());

        dateText1.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        dateText2.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        genRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        genRad2.setBackground(dashboardPanel.getWindow().getFilterColor());

        ageRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad4.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad5.setBackground(dashboardPanel.getWindow().getFilterColor());

        incomeRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad3.setBackground(dashboardPanel.getWindow().getFilterColor());

        conRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad4.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad5.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad6.setBackground(dashboardPanel.getWindow().getFilterColor());

        applyBut.setBackground(dashboardPanel.getWindow().getActionButColor());

        col1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c1r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c1r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        col2.setBackground(dashboardPanel.getWindow().getFilterColor());
        col3.setBackground(dashboardPanel.getWindow().getFilterColor());
        c3r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c3r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        c3r3.setBackground(dashboardPanel.getWindow().getFilterColor());
        col4.setBackground(dashboardPanel.getWindow().getFilterColor());
        col5.setBackground(dashboardPanel.getWindow().getFilterColor());
        c5r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c5r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        c5r3.setBackground(dashboardPanel.getWindow().getFilterColor());
        col6.setBackground(dashboardPanel.getWindow().getFilterColor());

        centrePanel.setBackground(dashboardPanel.getWindow().getFilterColor());
        southPanel.setBackground(dashboardPanel.getWindow().getFilterColor());
        northPanel.setBackground(dashboardPanel.getWindow().getFilterColor());
    }

    // Update GUI sizing.
    public void updateTextSize(){
        dateRangeCB.setFont(dashboardPanel.getWindow().getTextFontBold());
        ageCB.setFont(dashboardPanel.getWindow().getTextFontBold());
        genderCB.setFont(dashboardPanel.getWindow().getTextFontBold());
        incomeCB.setFont(dashboardPanel.getWindow().getTextFontBold());
        contextCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        dateText1.setFont(dashboardPanel.getWindow().getTextFont());
        dateText2.setFont(dashboardPanel.getWindow().getTextFont());
        date1Label.setFont(dashboardPanel.getWindow().getTextFont());
        date2Label.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad1.setFont(dashboardPanel.getWindow().getTextFont());
        ageRad2.setFont(dashboardPanel.getWindow().getTextFont());
        ageRad3.setFont(dashboardPanel.getWindow().getTextFont());
        ageRad4.setFont(dashboardPanel.getWindow().getTextFont());
        ageRad5.setFont(dashboardPanel.getWindow().getTextFont());

        genRad1.setFont(dashboardPanel.getWindow().getTextFont());
        genRad2.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad1.setFont(dashboardPanel.getWindow().getTextFont());
        incomeRad2.setFont(dashboardPanel.getWindow().getTextFont());
        incomeRad3.setFont(dashboardPanel.getWindow().getTextFont());

        conRad1.setFont(dashboardPanel.getWindow().getTextFont());
        conRad2.setFont(dashboardPanel.getWindow().getTextFont());
        conRad3.setFont(dashboardPanel.getWindow().getTextFont());
        conRad4.setFont(dashboardPanel.getWindow().getTextFont());
        conRad5.setFont(dashboardPanel.getWindow().getTextFont());
        conRad6.setFont(dashboardPanel.getWindow().getTextFont());

        applyBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
    }

}
