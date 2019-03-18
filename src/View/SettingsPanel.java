package View;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;

public class SettingsPanel extends JPanel {

    private View window;
    private Model.Bounce bounce;

    public SettingsPanel(View window){

        bounce = new Model.Bounce(false, false);
        this.window = window;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BorderLayout());
        this.init();

    }

    private void init(){

        //  ---- Creating components ----

        JLabel title = new JLabel("Settings");
        title.setFont(window.getHeadingFont());
        title.setForeground(window.getHeadingColour());

        JLabel infoLabel1 = new JLabel("Accessibility");
        infoLabel1.setFont(window.getSubHeadingFont());

        JCheckBox colourBlind = new JCheckBox("Colour Blind Mode");
        colourBlind.setFont(window.getTextFont());
        colourBlind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colourBlind.isEnabled() == true) {
                    window.setFilterColor(Color.lightGray);
                    window.setBackgoundColor(Color.white);
                    window.setHeadingColour(Color.BLACK);
                    window.setHighlightColor(Color.DARK_GRAY);
                    window.setUnhighlightColor(Color.GRAY);
                } else {
                    window.setHeadingColour(new Color(0xFBFBFB));
                    window.setHighlightColor(new Color(0x76B8FF));
                    window.setUnhighlightColor(new Color(0xF7F7F7));
                    window.setBackgoundColor(new Color(0x8DABCF));
                    window.setFilterColor(new Color(0xEDB980));
                }
                repaint();
            }

        });

        JCheckBox largeText = new JCheckBox("Large Text");
        largeText.setFont(window.getTextFont());
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
                repaint();
            }
        });

        JLabel bounceDefinition = new JLabel("Bounce Definition");
        bounceDefinition.setFont(window.getSubHeadingFont());

        JCheckBox minWebsiteTime = new JCheckBox("Minimum Time on website(seconds)");
        minWebsiteTime.setFont(window.getTextFont());
        minWebsiteTime.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                bounce.setTimeSet(minWebsiteTime.isSelected());

            }

        });

        JCheckBox minPagesVisited = new JCheckBox("Minimum pages visited");
        largeText.setFont(window.getTextFont());
        minPagesVisited.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                bounce.setTimeSet(minPagesVisited.isSelected());

            }

        });

        JSpinner webTimeSpinner = new JSpinner();
        webTimeSpinner.setValue(3);
        webTimeSpinner.setFont(window.getTextFont());
        webTimeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                bounce.setTime((int) webTimeSpinner.getValue());

            }
        });

        JSpinner webPagesSpinner = new JSpinner();
        webPagesSpinner.setValue(3);
        webPagesSpinner.setFont(window.getTextFont());
        webTimeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                bounce.setTime((int) webPagesSpinner.getValue());

            }
        });

        JButton backBut = new JButton("Back");
        backBut.setFont(window.getButtonBigFont());
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