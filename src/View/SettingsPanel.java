package View;

import Model.Bounce;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;

public class SettingsPanel extends JPanel {

    private View window;
    private Bounce bounce;

    public SettingsPanel(View window){
        this.window = window;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BorderLayout());

        bounce = window.getControl().getModel().getBounce();
        this.init();

    }

    private void init(){

        //  ---- Creating components ----

        JLabel title = new JLabel("Settings");
        title.setFont(window.getHeadingFont());
        title.setForeground(window.getHeadingColour());

        JLabel accessLab = new JLabel("Accessibility");
        accessLab.setFont(window.getSubHeadingFont());

        JCheckBox colourBlind = new JCheckBox("Colour Blind Mode");
        colourBlind.setBackground(window.getBackgoundColor());
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
            }

        });

        JCheckBox largeText = new JCheckBox("Large Text");
        largeText.setBackground(window.getBackgoundColor());
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
            }
        });

        JLabel bounceDefinition = new JLabel("Bounce Definition");
        bounceDefinition.setFont(window.getSubHeadingFont());

        JCheckBox minWebsiteTime = new JCheckBox("Minimum Time on Website (secs)");
        minWebsiteTime.setBackground(window.getBackgoundColor());
        minWebsiteTime.setFont(window.getTextFont());
        minWebsiteTime.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bounce.setTimeSet(minWebsiteTime.isSelected());
            }

        });

        JCheckBox minPagesVisited = new JCheckBox("Minimum Pages Visited");
        minPagesVisited.setBackground(window.getBackgoundColor());
        minPagesVisited.setFont(window.getTextFont());
        minPagesVisited.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bounce.setNumPageSet(minPagesVisited.isSelected());
            }

        });

        JSpinner webTimeSpinner = new JSpinner();
        webTimeSpinner.setFont(window.getTextFont());
        webTimeSpinner.setValue(3);
        webTimeSpinner.setMaximumSize(new Dimension ((int) ( window.getButtonBigFont().getSize() * 1.5),window.getButtonBigFont().getSize() * 2));

        JSpinner webPagesSpinner = new JSpinner();
        webPagesSpinner.setFont(window.getTextFont());
        webPagesSpinner.setValue(3);
        webPagesSpinner.setMaximumSize(new Dimension((int) ( window.getButtonBigFont().getSize() * 1.5),window.getButtonBigFont().getSize() * 2));


        JButton backBut = new JButton("Back");
        backBut.setFont(window.getButtonBigFont());
        backBut.setBackground(window.getUnhighlightColor());

        backBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                bounce.setTime((int) webTimeSpinner.getValue());
                bounce.setNumOfPageVisited((int) webPagesSpinner.getValue());
                window.getDashboardPanel().getDataPanel().updateData();
                window.getDashboardPanel().getGraphPanel().getGraph().updateSeries();


                window.changePanel("dashboardPanel");
            }

        });

        //  ---- Layout ----
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
        northPanel.setBackground(window.getBackgoundColor());

        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row1.setBackground(window.getBackgoundColor());

        row1.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1.add(backBut);
        row1.add(Box.createHorizontalGlue());


        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
        row2.setBackground(window.getBackgoundColor());

        row2.add(Box.createHorizontalGlue());
        row2.add(title);
        row2.add(Box.createHorizontalGlue());


        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(row1);
        northPanel.add(row2);
        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));


        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.LINE_AXIS));
        centrePanel.setBackground(window.getBackgoundColor());

        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.PAGE_AXIS));
        col1.setBackground(window.getBackgoundColor());

        col1.add(accessLab);
        col1.add(Box.createRigidArea(window.getHightBorderDim()));
        col1.add(colourBlind);
        col1.add(Box.createRigidArea(window.getHightBorderDim()));
        col1.add(largeText);
        col1.add(Box.createRigidArea(window.getHightBorderDim()));
        col1.add(Box.createVerticalGlue());


        JPanel col2 = new JPanel();
        col2.setLayout(new BoxLayout(col2, BoxLayout.PAGE_AXIS));
        col2.setBackground(window.getBackgoundColor());

        JPanel c2r0 = new JPanel();
        c2r0.setLayout(new BoxLayout(c2r0, BoxLayout.LINE_AXIS));
        c2r0.setBackground(window.getBackgoundColor());

        c2r0.add(bounceDefinition);
        c2r0.add(Box.createHorizontalGlue());

        JPanel c2r1 = new JPanel();
        c2r1.setLayout(new BoxLayout(c2r1, BoxLayout.LINE_AXIS));
        c2r1.setBackground(window.getBackgoundColor());

        c2r1.add(minPagesVisited);
        c2r1.add(Box.createRigidArea(window.getWidthBorderDim()));
        c2r1.add(webPagesSpinner);
        c2r1.add(Box.createHorizontalGlue());

        JPanel c2r2 = new JPanel();
        c2r2.setLayout(new BoxLayout(c2r2, BoxLayout.LINE_AXIS));
        c2r2.setBackground(window.getBackgoundColor());

        c2r2.add(minWebsiteTime);
        c2r2.add(Box.createRigidArea(window.getWidthBorderDim()));
        c2r2.add(webTimeSpinner);
        c2r2.add(Box.createHorizontalGlue());

        col2.add(c2r0);
        col2.add(Box.createRigidArea(window.getHightBorderDim()));
        col2.add(c2r1);
        col2.add(Box.createRigidArea(window.getHightBorderDim()));
        col2.add(c2r2);
        col2.add(Box.createRigidArea(window.getHightBorderDim()));
        col2.add(Box.createVerticalGlue());


        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(col1);
        centrePanel.add(Box.createRigidArea(window.getWidthBorderDim()));
        centrePanel.add(Box.createHorizontalGlue());
        centrePanel.add(Box.createRigidArea(window.getWidthBorderDim()));
        centrePanel.add(col2);

        this.add(northPanel,BorderLayout.NORTH);
        this.add(centrePanel, BorderLayout.CENTER);
    }

}