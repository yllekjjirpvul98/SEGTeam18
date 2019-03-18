package View;

import Model.Filter;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.DatePicker;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

public class FilterPanel extends JPanel {

    private DashboardPanel dashboardPanel;

    public FilterPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setBackground(dashboardPanel.getWindow().getFilterColor2());
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.init();
    }

    private void init(){
        Filter fil = dashboardPanel.getWindow().getControl().getModel().getFilter();

        //  ---- Creating components ----
        JCheckBox dateRangeCB = new JCheckBox(" Date Range ");
        dateRangeCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        dateRangeCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        dateRangeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currDateFilter = fil.getDateRangeSelected();
                fil.setDateRangeSelected(!currDateFilter);
            }
        });

        JCheckBox genderCB = new JCheckBox(" Gender     ");
        genderCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        genderCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        genderCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currGenderFilter = fil.getGenderSelected();
                fil.setGenderSelected(!currGenderFilter);
            }
        });

        JCheckBox ageCB = new JCheckBox(" Age        ");

        ageCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        ageCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currAgeFilter = fil.getAgeSelected();
                fil.setAgeSelected(!currAgeFilter);
            }
        });

        JCheckBox incomeCB = new JCheckBox(" Income     ");
        incomeCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        incomeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currIncomeFilter = fil.getIncomeSelected();
                fil.setIncomeSelected(!currIncomeFilter);
            }
        });

        JCheckBox contextCB = new JCheckBox(" Context    ");
        contextCB.setBackground(dashboardPanel.getWindow().getFilterColor());
        contextCB.setFont(dashboardPanel.getWindow().getTextFontBold());

        contextCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean currContextFilter = fil.getContextSelected();
                fil.setContextSelected(!currContextFilter);
            }
        });


        SqlDateModel dateModel1 = new SqlDateModel();
        Properties dateProp1 = new Properties();
        dateProp1.put("text.today", "Today");
        dateProp1.put("text.month", "Month");
        dateProp1.put("text.year", "Year");

        JDatePanelImpl datePanel1 = new JDatePanelImpl(dateModel1, dateProp1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, new JFormattedTextField.AbstractFormatter() {
            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        });

        SqlDateModel dateModel2 = new SqlDateModel();
        Properties dateProp2 = new Properties();
        dateProp2.put("text.today", "Today");
        dateProp2.put("text.month", "Month");
        dateProp2.put("text.year", "Year");

        JDatePanelImpl datePanel2 = new JDatePanelImpl(dateModel2, dateProp2);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new JFormattedTextField.AbstractFormatter() {
            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        });

        JFormattedTextField dateText1 = datePicker1.getJFormattedTextField();
        dateText1.setFont(dashboardPanel.getWindow().getTextFont());
        dateText1.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        dateText1.setMaximumSize(new Dimension(dashboardPanel.getWindow().getButtonBigFont().getSize() * 10, dateText1.getHeight()));

        JFormattedTextField dateText2 = datePicker2.getJFormattedTextField();
        dateText2.setFont(dashboardPanel.getWindow().getTextFont());
        dateText2.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        dateText2.setMaximumSize(new Dimension(dashboardPanel.getWindow().getButtonBigFont().getSize() * 10, dateText2.getHeight()));

        JLabel date1Label = new JLabel("Start");
        date1Label.setFont(dashboardPanel.getWindow().getTextFont());

        JLabel date2Label = new JLabel(" End ");
        date2Label.setFont(dashboardPanel.getWindow().getTextFont());


        JRadioButton genRad1 = new JRadioButton("Male");
        genRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        genRad1.setFont(dashboardPanel.getWindow().getTextFont());

        genRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(genRad1.isSelected() && !(fil.getGender().contains("Male"))) {
                    fil.setGender("Male");
                }
                if(!(genRad1.isSelected()) && fil.getGender().contains("Male")){
                    fil.getGender().remove("Male");
                }
                System.out.println(fil.getGender());

            }
        });

        JRadioButton genRad2 = new JRadioButton("Female");
        genRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        genRad2.setFont(dashboardPanel.getWindow().getTextFont());

        genRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(genRad2.isSelected() && !(fil.getGender().contains("Female"))) {
                    fil.setGender("Female");
                }
                if(!(genRad2.isSelected()) && fil.getGender().contains("Female")){
                    fil.getGender().remove("Female");
                }
                System.out.println(fil.getGender());
            }
        });


        JRadioButton ageRad1 = new JRadioButton("< 25");
        ageRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad1.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(ageRad1.isSelected() && !(fil.getAge().contains("<25"))) {
                    fil.setAge("<25");
                }
                if(!(ageRad1.isSelected()) && fil.getAge().contains("<25")){
                    fil.getAge().remove("<25");
                }
                System.out.println(fil.getAge());

            }
        });

        JRadioButton ageRad2 = new JRadioButton("25 - 34");
        ageRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad2.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ageRad2.isSelected() && !(fil.getAge().contains("25-34"))) {
                    fil.setAge("25-34");
                }
                if(!(ageRad2.isSelected()) && fil.getAge().contains("25-34")){
                    fil.getAge().remove("25-34");
                }
                System.out.println(fil.getAge());
            }
        });

        JRadioButton ageRad3 = new JRadioButton("35 - 44");
        ageRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad3.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ageRad3.isSelected() && !(fil.getAge().contains("35-44"))) {
                    fil.setAge("35-44");
                }
                if(!(ageRad3.isSelected()) && fil.getAge().contains("35-44")){
                    fil.getAge().remove("35-44");
                }
                System.out.println(fil.getAge());
            }
        });

        JRadioButton ageRad4 = new JRadioButton("45 - 54");
        ageRad4.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad4.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ageRad4.isSelected() && !(fil.getAge().contains("45-54"))) {
                    fil.setAge("45-54");
                }
                if(!(ageRad4.isSelected()) && fil.getAge().contains("45-54")){
                    fil.getAge().remove("45-54");
                }
                System.out.println(fil.getAge());
            }
        });

        JRadioButton ageRad5 = new JRadioButton("> 54");
        ageRad5.setBackground(dashboardPanel.getWindow().getFilterColor());
        ageRad5.setFont(dashboardPanel.getWindow().getTextFont());

        ageRad5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ageRad5.isSelected() && !(fil.getAge().contains(">54"))) {
                    fil.setAge(">54");
                }
                if(!(ageRad5.isSelected()) && fil.getAge().contains(">54")){
                    fil.getAge().remove(">54");
                }
                System.out.println(fil.getAge());
            }
        });

        JRadioButton incomeRad1 = new JRadioButton("Low");
        incomeRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad1.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(incomeRad1.isSelected() && !(fil.getIncome().contains("Low"))) {
                    fil.setIncome("Low");
                }
                if(!(incomeRad1.isSelected()) && fil.getIncome().contains("Low")){
                    fil.getIncome().remove("Low");
                }
                System.out.println(fil.getIncome());
            }
        });

        JRadioButton incomeRad2 = new JRadioButton("Mid");
        incomeRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad2.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(incomeRad2.isSelected() && !(fil.getIncome().contains("Medium"))) {
                    fil.setIncome("Medium");
                }
                if(!(incomeRad2.isSelected()) && fil.getIncome().contains("Medium")){
                    fil.getIncome().remove("Medium");
                }
                System.out.println(fil.getIncome());
            }
        });

        JRadioButton incomeRad3 = new JRadioButton("High");
        incomeRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        incomeRad3.setFont(dashboardPanel.getWindow().getTextFont());

        incomeRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(incomeRad3.isSelected() && !(fil.getIncome().contains("High"))) {
                    fil.setIncome("High");
                }
                if(!(incomeRad3.isSelected()) && fil.getIncome().contains("High")){
                    fil.getIncome().remove("High");
                }
                System.out.println(fil.getIncome());
            }
        });


        JRadioButton conRad1 = new JRadioButton("News");
        conRad1.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad1.setFont(dashboardPanel.getWindow().getTextFont());

        conRad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conRad1.isSelected() && !(fil.getContext().contains(Filter.Context.News))) {
                    fil.setContext(Filter.Context.News);
                }
                if(!(conRad1.isSelected()) && fil.getContext().contains(Filter.Context.News)){
                    fil.getContext().remove(Filter.Context.News);
                }
                System.out.println(fil.getContext());
            }
        });

        JRadioButton conRad2 = new JRadioButton("Shopping");
        conRad2.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad2.setFont(dashboardPanel.getWindow().getTextFont());

        conRad2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conRad2.isSelected() && !(fil.getContext().contains(Filter.Context.Shopping))) {
                    fil.setContext(Filter.Context.Shopping);
                }
                if(!(conRad2.isSelected()) && fil.getContext().contains(Filter.Context.Shopping)){
                    fil.getContext().remove(Filter.Context.Shopping);
                }
                System.out.println(fil.getContext());
            }
        });

        JRadioButton conRad3 = new JRadioButton("Social Media");
        conRad3.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad3.setFont(dashboardPanel.getWindow().getTextFont());

        conRad3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conRad3.isSelected() && !(fil.getContext().contains(Filter.Context.SocialMedia))) {
                    fil.setContext(Filter.Context.SocialMedia);
                }
                if(!(conRad3.isSelected()) && fil.getContext().contains(Filter.Context.SocialMedia)){
                    fil.getContext().remove(Filter.Context.SocialMedia);
                }
                System.out.println(fil.getContext());
            }
        });

        JRadioButton conRad4 = new JRadioButton("Blog");
        conRad4.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad4.setFont(dashboardPanel.getWindow().getTextFont());

        conRad4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conRad4.isSelected() && !(fil.getContext().contains(Filter.Context.Blog))) {
                    fil.setContext(Filter.Context.Blog);
                }
                if(!(conRad4.isSelected()) && fil.getContext().contains(Filter.Context.Blog)){
                    fil.getContext().remove(Filter.Context.Blog);
                }
                System.out.println(fil.getContext());
            }
        });

        JRadioButton conRad5 = new JRadioButton("Hobbies");
        conRad5.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad5.setFont(dashboardPanel.getWindow().getTextFont());

        conRad5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conRad5.isSelected() && !(fil.getContext().contains(Filter.Context.Hobbies))) {
                    fil.setContext(Filter.Context.Hobbies);
                }
                if(!(conRad5.isSelected()) && fil.getContext().contains(Filter.Context.Hobbies)){
                    fil.getContext().remove(Filter.Context.Hobbies);
                }
                System.out.println(fil.getContext());
            }
        });

        JRadioButton conRad6 = new JRadioButton("Travel");
        conRad6.setBackground(dashboardPanel.getWindow().getFilterColor());
        conRad6.setFont(dashboardPanel.getWindow().getTextFont());

        conRad6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conRad6.isSelected() && !(fil.getContext().contains(Filter.Context.Travel))) {
                    fil.setContext(Filter.Context.Travel);
                }
                if(!(conRad6.isSelected()) && fil.getContext().contains(Filter.Context.Travel)){
                    fil.getContext().remove(Filter.Context.Travel);
                }
                System.out.println(fil.getContext());
            }
        });


        JButton applyBut = new JButton("Apply");
        applyBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        applyBut.setBackground(dashboardPanel.getWindow().getHighlightColor());

        applyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date date1 = (Date) datePicker1.getModel().getValue();
                Date date2 = (Date) datePicker2.getModel().getValue();

                if(date1 != null && date2 != null) {
                    fil.setdateLowerRange(date1);
                    fil.setDateUpperRange(date2);
                }

                if(fil.getDateRangeSelected() && (date1 == null || date2 == null)){
                    fil.setDateRangeSelected(false);
                    dateRangeCB.setSelected(false);
                }

                if(fil.getIncomeSelected() && fil.getIncome().isEmpty()){
                    fil.setIncomeSelected(false);
                    incomeCB.setSelected(false);
                }

                if(fil.getGenderSelected() && fil.getGender().isEmpty()){
                    fil.setGenderSelected(false);
                    genderCB.setSelected(false);
                }

                if(fil.getAgeSelected() && fil.getAge().isEmpty()){
                    fil.setAgeSelected(false);
                    ageCB.setSelected(false);
                }

                if(fil.getContextSelected() && fil.getContext().isEmpty()){
                    fil.setContextSelected(false);
                    contextCB.setSelected(false);
                }

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

                dashboardPanel.getDataPanel().updateData();

            }
        });


        //  ---- Layout ----
        JPanel col1 = new JPanel();
        col1.setBackground(dashboardPanel.getWindow().getFilterColor());
        col1.setLayout(new BoxLayout(col1, BoxLayout.PAGE_AXIS));

        JPanel c1r1 = new JPanel();
        c1r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c1r1.setLayout(new BoxLayout(c1r1, BoxLayout.LINE_AXIS));

       c1r1.add(datePicker1);
       c1r1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
       c1r1.add(date1Label);

        JPanel c1r2 = new JPanel();
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


        JPanel col2 = new JPanel();
        col2.setBackground(dashboardPanel.getWindow().getFilterColor());
        col2.setLayout(new BoxLayout(col2, BoxLayout.PAGE_AXIS));

        col2.add(Box.createVerticalGlue());
        col2.add(genderCB);
        col2.add(Box.createVerticalGlue());
        col2.add(genRad1);
        col2.add(Box.createVerticalGlue());
        col2.add(genRad2);
        col2.add(Box.createVerticalGlue());


        JPanel col3 = new JPanel();
        col3.setBackground(dashboardPanel.getWindow().getFilterColor());
        col3.setLayout(new BoxLayout(col3, BoxLayout.PAGE_AXIS));

        JPanel c3r1 = new JPanel();
        c3r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c3r1.setLayout(new BoxLayout(c3r1, BoxLayout.LINE_AXIS));

        c3r1.add(ageRad1);
        c3r1.add(Box.createHorizontalGlue());
        c3r1.add(ageRad2);

        JPanel c3r2 = new JPanel();
        c3r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        c3r2.setLayout(new BoxLayout(c3r2, BoxLayout.LINE_AXIS));

        c3r2.add(ageRad3);
        c3r2.add(Box.createHorizontalGlue());
        c3r2.add(ageRad4);

        JPanel c3r3 = new JPanel();
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


        JPanel col4 = new JPanel();
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

        JPanel col5 = new JPanel();
        col5.setBackground(dashboardPanel.getWindow().getFilterColor());
        col5.setLayout(new BoxLayout(col5, BoxLayout.PAGE_AXIS));

        JPanel c5r1 = new JPanel();
        c5r1.setBackground(dashboardPanel.getWindow().getFilterColor());
        c5r1.setLayout(new BoxLayout(c5r1, BoxLayout.LINE_AXIS));

        c5r1.add(conRad1);
        c5r1.add(Box.createHorizontalGlue());
        c5r1.add(conRad2);

        JPanel c5r2 = new JPanel();
        c5r2.setBackground(dashboardPanel.getWindow().getFilterColor());
        c5r2.setLayout(new BoxLayout(c5r2, BoxLayout.LINE_AXIS));

        c5r2.add(conRad3);
        c5r2.add(Box.createHorizontalGlue());
        c5r2.add(conRad4);

        JPanel c5r3 = new JPanel();
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

        JPanel col6 = new JPanel();
        col6.setBackground(dashboardPanel.getWindow().getFilterColor());
        col6.setLayout(new BoxLayout(col6, BoxLayout.PAGE_AXIS));

        col6.add(Box.createVerticalGlue());
        col6.add(applyBut);
        col6.add(Box.createVerticalGlue());


        this.add(Box.createRigidArea(new Dimension(0,dashboardPanel.getWindow().getTextSizeL() * 4))); //200 at 1440p
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
