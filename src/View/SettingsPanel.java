package View;

import Model.Bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {

    private View window;
    private Bounce bounce;

    public SettingsPanel(View window){
        this.window = window;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        bounce = window.getControl().getModel().getBounce();
        this.init();

    }

    private void init(){

        //  ---- Creating components ----

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


        JButton applyBut = new JButton("Apply");
        applyBut.setFont(window.getButtonBigFont());
        applyBut.setBackground(window.getFilterColor());

        applyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bounce.setTime((int) webTimeSpinner.getValue());
                bounce.setNumOfPageVisited((int) webPagesSpinner.getValue());

                window.getDashboardPanel().getDataPanel().updateData();
                window.getDashboardPanel().getGraphPanel().getGraph().updateSeries();

                window.getSettingFrame().close();
            }

        });

        //  ---- Layout ----
        JPanel sPanel = new JPanel();
        sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.PAGE_AXIS));
        sPanel.setBackground(window.getBackgoundColor());

        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row1.setBackground(window.getBackgoundColor());

        row1.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1.add(Box.createHorizontalGlue());
        row1.add(applyBut);
        row1.add(Box.createRigidArea(window.getWidthBorderDim()));

        sPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        sPanel.add(row1);
        sPanel.add(Box.createRigidArea(window.getHightBorderDim()));


        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.LINE_AXIS));
        centrePanel.setBackground(window.getBackgoundColor());

        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.PAGE_AXIS));
        col1.setBackground(window.getBackgoundColor());

        col1.add(Box.createRigidArea(window.getHightBorderDim()));
        col1.add(accessLab);
        col1.add(Box.createRigidArea(window.getHightBorderDim()));
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

        col2.add(Box.createRigidArea(window.getHightBorderDim()));
        col2.add(c2r0);
        col2.add(Box.createRigidArea(window.getHightBorderDim()));
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

        this.add(centrePanel, BorderLayout.CENTER);
        this.add(sPanel,BorderLayout.SOUTH);

    }

}