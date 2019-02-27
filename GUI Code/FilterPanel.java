import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {

    public FilterPanel(){

        //this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.init();

    }

    private void init(){

        JLabel titleLabel = new JLabel("Filters");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(titleLabel);

        JCheckBox dateRangeCB = new JCheckBox("Date Range ");
        JCheckBox genderCB = new JCheckBox("Gender ");
        JCheckBox ageCB = new JCheckBox("Age ");
        JCheckBox incomeCB = new JCheckBox("Income ");
        JCheckBox contextCB = new JCheckBox("Context ");

        JTextField date1 = new JTextField("Start Date");
        JTextField date2 = new JTextField("End Date");

        ButtonGroup genderButtons = new ButtonGroup();
        JRadioButton genBut1 = new JRadioButton("Male");
        JRadioButton genBut2 = new JRadioButton("Female");
        JRadioButton genBut3 = new JRadioButton("Unspecified");
        genderButtons.add(genBut1);
        genderButtons.add(genBut2);
        genderButtons.add(genBut3);

        JSlider ageSlider = new JSlider();
        ageSlider.setMinimum(10);
        ageSlider.setMaximum(99);
        JLabel ageLabel = new JLabel("Years");

        JSlider moneySlider = new JSlider();
        ageSlider.setMinimum(10);
        ageSlider.setMaximum(99);
        JLabel moneyLabel = new JLabel("£100's");

        ButtonGroup contextButtons = new ButtonGroup();
        JCheckBox conBut1 = new JCheckBox("News");
        JCheckBox conBut2 = new JCheckBox("Blog");
        JCheckBox conBut3 = new JCheckBox("Hobbies");
        JCheckBox conBut4 = new JCheckBox("Shopping");
        JCheckBox conBut5 = new JCheckBox("Social Media");
        JCheckBox conBut6 = new JCheckBox("Travel");
        contextButtons.add(conBut1);
        contextButtons.add(conBut2);
        contextButtons.add(conBut3);
        contextButtons.add(conBut4);
        contextButtons.add(conBut5);
        contextButtons.add(conBut6);

        this.add(titleLabel);
        this.add(dateRangeCB);
        this.add(genderCB);
        this.add(ageCB);
        this.add(incomeCB);
        this.add(contextCB);
        this.add(date1);
        this.add(date2);
        this.add(genBut1);
        this.add(genBut2);
        this.add(genBut3);
        this.add(ageSlider);
        this.add(ageLabel);
        this.add(moneySlider);
        this.add(moneyLabel);
        this.add(conBut1);
        this.add(conBut2);
        this.add(conBut3);
        this.add(conBut4);
        this.add(conBut5);
        this.add(conBut6);



    }


}
