package View;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsPanel extends JPanel {

    private View window;
    private Model.Bounce bounce;

    public SettingsPanel(View window){

        bounce = new Model.Bounce(false, false);
        this.window = window;
        this.setLayout(new BorderLayout());
        this.init();

    }

    private void init(){

        //  ---- Creating components ----

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Courier", Font.BOLD, 60));
        title.setForeground(new Color(0x2865E1));

        JLabel infoLabel1 = new JLabel("Accessibility");
        infoLabel1.setFont(new Font("Courier", Font.BOLD, 30));

        JCheckBox colourBlind = new JCheckBox("Colour Blind Mode");
        colourBlind.setFont(new Font("Courier", Font.PLAIN, 20));
        colourBlind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colourBlind.isEnabled() == true) {
                    window.setHeadingColour(Color.BLACK);
                    window.setHighlightColor(Color.DARK_GRAY);
                    window.setUnhighlightColor(Color.GRAY);
                } else {
                    window.setHeadingColour(new Color(0x2865E1));
                    window.setHighlightColor(new Color(0x76B8FF));
                    window.setUnhighlightColor(Color.lightGray);
                }
            }

        });

        JCheckBox largeText = new JCheckBox("Large Text");
        largeText.setFont(new Font("Courier", Font.PLAIN, 20));
        largeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(largeText.isEnabled() == true) {

                    window.setTextSizeXL((int) Math.round(window.getTextSizeXL() * 1.5));
                    window.setTextSizeL((int) Math.round(window.getTextSizeL() * 1.5));
                    window.setTextSizeM((int) Math.round(window.getTextSizeM() * 1.5));
                    window.setTextSizeS((int) Math.round(window.getTextSizeS() * 1.5));
                    window.setTextSizeXS((int) Math.round(window.getTextSizeXS() * 1.5));

                }

                if(largeText.isEnabled() == false) {

                    window.setTextSizeXL((int) Math.round(window.getTextSizeXL() / 1.5));
                    window.setTextSizeL((int) Math.round(window.getTextSizeL() / 1.5));
                    window.setTextSizeM((int) Math.round(window.getTextSizeM() / 1.5));
                    window.setTextSizeS((int) Math.round(window.getTextSizeS() / 1.5));
                    window.setTextSizeXS((int) Math.round(window.getTextSizeXS() / 1.5));

                }
            }
        });

        JLabel bounceDefinition = new JLabel("Bounce Definition");
        bounceDefinition.setFont(new Font("Courier", Font.BOLD, 30));

        JCheckBox minWebsiteTime = new JCheckBox("Minimum Time on website(seconds)");
        minWebsiteTime.setFont(new Font("Courier", Font.PLAIN, 20));
        minWebsiteTime.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                bounce.setTimeSet(minWebsiteTime.isSelected());

            }

        });

        JCheckBox minPagesVisited = new JCheckBox("Minimum pages visited");
        largeText.setFont(new Font("Courier", Font.PLAIN, 20));
        minPagesVisited.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                bounce.setTimeSet(minPagesVisited.isSelected());

            }

        });

        JSpinner webTimeSpinner = new JSpinner();
        webTimeSpinner.setValue(3);
        webTimeSpinner.setFont(new Font("Courier", Font.PLAIN, 20));
        webTimeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                bounce.setTime((int) webTimeSpinner.getValue());

            }
        });

        JSpinner webPagesSpinner = new JSpinner();
        webPagesSpinner.setValue(3);
        webPagesSpinner.setFont(new Font("Courier", Font.PLAIN, 20));
        webTimeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                bounce.setTime((int) webPagesSpinner.getValue());

            }
        });

        JButton backBut = new JButton("Back");
        backBut.setFont(new Font( "Courier", Font.BOLD, 20));
        backBut.setBackground(Color.lightGray);

        backBut.addActionListener(e -> window.changePanel("dashboardPanel"));

        JPanel mainPanel = new JPanel();

        mainPanel.add(backBut);
        mainPanel.add(title);
        mainPanel.add(infoLabel1);
        mainPanel.add(colourBlind);
        mainPanel.add(largeText);
        mainPanel.add(bounceDefinition);
        mainPanel.add(minWebsiteTime);
        mainPanel.add(webTimeSpinner);
        mainPanel.add(minPagesVisited);
        mainPanel.add(webPagesSpinner);

        // Adds the 4 borders to the main panel.
        this.add(mainPanel, BorderLayout.NORTH);
    }

}