package View;

import Model.Bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {

    private View window;
    private Bounce bounce;

    private JCheckBox colourBlind;
    private JCheckBox largeText;
    private JCheckBox minWebsiteTime;
    private JCheckBox minPagesVisited;
    private JButton applyBut;
    private JLabel accessLab;
    private JLabel bounceDefinition;
    private JSpinner webTimeSpinner;
    private JSpinner webPagesSpinner;

    private JPanel row1;
    private JPanel col1;
    private JPanel col2;
    private JPanel c2r0;
    private JPanel c2r1;
    private JPanel c2r2;
    private JPanel sPanel;
    private JPanel centrePanel;

    public SettingsPanel(View window){
        this.window = window;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        bounce = window.getControl().getModel().getBounce();
        this.init();

    }

    private void init(){

        //  ---- Creating components ----
        accessLab = new JLabel("Accessibility");
        accessLab.setFont(window.getSubHeadingFont());

        colourBlind = new JCheckBox("Colour Blind Mode");
        colourBlind.setBackground(window.getBackgoundColor());
        colourBlind.setFont(window.getTextFont());
        colourBlind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean preSetting = window.getControl().getModel().getSettings().getColorBlind();
                window.getControl().getModel().getSettings().setColorBlind(!preSetting);

                if(window.getControl().getModel().getSettings().getColorBlind()) {
                    window.setHeadingColour(new Color(0xFBFBFB));
                    window.setHighlightColor(new Color(0xFBFBFB));
                    window.setUnhighlightColor(new Color(0xFBFBFB));
                    window.setBackgoundColor(new Color(0x8DABCF));
                    window.setFilterColor(new Color(0xEDB980));
                    window.setFilterColor2(new Color(0xEDB980));
                    window.setAddColor(new Color(0xEEE832));
                    window.setDelColor(new Color(0xDEBD6FD5));
                    window.setActionButColor(new Color(0xFBFBFB));
                } else {
                    window.setHeadingColour(new Color(0x4167B2));
                    window.setHighlightColor(new Color(0x71B9FF));
                    window.setUnhighlightColor(new Color(0xFFFFFF));
                    window.setBackgoundColor(new Color(0xEAEBEF));
                    window.setFilterColor(new Color(0x468DDF));
                    window.setFilterColor2(new Color(0x468DDF));
                    window.setAddColor(new Color(0x71B9FF));
                    window.setDelColor(new Color(0x71B9FF));
                    window.setActionButColor(new Color(0xC0F89B4F, true));
                }
            }

        });

        largeText = new JCheckBox("Large Text");
        largeText.setBackground(window.getBackgoundColor());
        largeText.setFont(window.getTextFont());
        largeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean preSetting = window.getControl().getModel().getSettings().getLargeText();
                window.getControl().getModel().getSettings().setLargeText(!preSetting);

                if(window.getControl().getModel().getSettings().getLargeText()) {
                    window.setTitleFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getTitleFont().getSize() * 1.5))));
                    window.setHeadingFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getHeadingFont().getSize() * 1.5))));
                    window.setSubHeadingFont(new Font("Verdana", Font.PLAIN, (int)(Math.round(window.getSubHeadingFont().getSize() * 1.5))));
                    window.setTextFont(new Font("Verdana", Font.PLAIN, (int)(Math.round(window.getTextFont().getSize() * 1.5))));
                    window.setTextFontBold(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getTextFontBold().getSize() * 1.5))));
                    window.setButtonTitleFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getButtonTitleFont().getSize() * 1.5))));
                    window.setButtonBigFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getButtonBigFont().getSize() * 1.5))));
                    window.setButtonSmallFont(new Font("Verdana", Font.PLAIN, (int)(Math.round(window.getButtonBigFont().getSize() * 1.5))));
                } else {
                    window.setTitleFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getTitleFont().getSize() / 1.5))));
                    window.setHeadingFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getHeadingFont().getSize() / 1.5))));
                    window.setSubHeadingFont(new Font("Verdana", Font.PLAIN, (int)(Math.round(window.getSubHeadingFont().getSize() / 1.5))));
                    window.setTextFont(new Font("Verdana", Font.PLAIN, (int)(Math.round(window.getTextFont().getSize() / 1.5))));
                    window.setTextFontBold(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getTextFontBold().getSize() / 1.5))));
                    window.setButtonTitleFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getButtonTitleFont().getSize() / 1.5))));
                    window.setButtonBigFont(new Font("Verdana", Font.BOLD, (int)(Math.round(window.getButtonBigFont().getSize() / 1.5))));
                    window.setButtonSmallFont(new Font("Verdana", Font.PLAIN, (int)(Math.round(window.getButtonBigFont().getSize() / 1.5))));
                }
            }
        });

        bounceDefinition = new JLabel("Bounce Definition");
        bounceDefinition.setFont(window.getSubHeadingFont());

        minWebsiteTime = new JCheckBox("Minimum Time on Website (secs)");
        minWebsiteTime.setBackground(window.getBackgoundColor());
        minWebsiteTime.setFont(window.getTextFont());
        minWebsiteTime.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bounce.setTimeSet(minWebsiteTime.isSelected());
            }

        });

        minPagesVisited = new JCheckBox("Minimum Pages Visited");
        minPagesVisited.setBackground(window.getBackgoundColor());
        minPagesVisited.setFont(window.getTextFont());
        minPagesVisited.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bounce.setNumPageSet(minPagesVisited.isSelected());
            }

        });

        webTimeSpinner = new JSpinner();
        webTimeSpinner.setFont(window.getTextFont());
        webTimeSpinner.setValue(3);
        webTimeSpinner.setMaximumSize(new Dimension ((int) ( window.getButtonBigFont().getSize() * 3),window.getButtonBigFont().getSize() * 2));

        webPagesSpinner = new JSpinner();
        webPagesSpinner.setFont(window.getTextFont());
        webPagesSpinner.setValue(3);
        webPagesSpinner.setMaximumSize(new Dimension((int) ( window.getButtonBigFont().getSize() * 3),window.getButtonBigFont().getSize() * 2));


        applyBut = new JButton("Apply");
        applyBut.setFont(window.getButtonBigFont());
        applyBut.setBackground(window.getActionButColor());

        applyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bounce.setTime((int) webTimeSpinner.getValue());
                bounce.setNumOfPageVisited((int) webPagesSpinner.getValue());

                window.getDashboardPanel().getDataPanel().updateData();
                window.getDashboardPanel().getGraphPanel().getGraph().updateSeries();

                window.getSettingFrame().close();
                window.updateColors();
                window.updateTextSize();
            }

        });

        //  ---- Layout ----
        sPanel = new JPanel();
        sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.PAGE_AXIS));
        sPanel.setBackground(window.getBackgoundColor());

        row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row1.setBackground(window.getBackgoundColor());

        row1.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1.add(Box.createHorizontalGlue());
        row1.add(applyBut);
        row1.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1.add(Box.createRigidArea(window.getWidthBorderDim()));

        sPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        sPanel.add(row1);
        sPanel.add(Box.createRigidArea(window.getHightBorderDim()));


        centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.LINE_AXIS));
        centrePanel.setBackground(window.getBackgoundColor());

        col1 = new JPanel();
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


        col2 = new JPanel();
        col2.setLayout(new BoxLayout(col2, BoxLayout.PAGE_AXIS));
        col2.setBackground(window.getBackgoundColor());

        c2r0 = new JPanel();
        c2r0.setLayout(new BoxLayout(c2r0, BoxLayout.LINE_AXIS));
        c2r0.setBackground(window.getBackgoundColor());

        c2r0.add(bounceDefinition);
        c2r0.add(Box.createHorizontalGlue());

        c2r1 = new JPanel();
        c2r1.setLayout(new BoxLayout(c2r1, BoxLayout.LINE_AXIS));
        c2r1.setBackground(window.getBackgoundColor());

        c2r1.add(minPagesVisited);
        c2r1.add(Box.createRigidArea(window.getWidthBorderDim()));
        c2r1.add(webPagesSpinner);
        c2r1.add(Box.createHorizontalGlue());

        c2r2 = new JPanel();
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

    public void updateColors(){
        this.setBackground(window.getBackgoundColor());
        colourBlind.setBackground(window.getBackgoundColor());
        largeText.setBackground(window.getBackgoundColor());
        minWebsiteTime.setBackground(window.getBackgoundColor());
        minPagesVisited.setBackground(window.getBackgoundColor());
        applyBut.setBackground(window.getActionButColor());
        row1.setBackground(window.getBackgoundColor());
        col1.setBackground(window.getBackgoundColor());
        col2.setBackground(window.getBackgoundColor());
        c2r0.setBackground(window.getBackgoundColor());
        c2r1.setBackground(window.getBackgoundColor());
        c2r2.setBackground(window.getBackgoundColor());
        sPanel.setBackground(window.getBackgoundColor());
        centrePanel.setBackground(window.getBackgoundColor());
    }

    public void updateTextSize(){
        accessLab.setFont(window.getSubHeadingFont());
        colourBlind.setFont(window.getTextFont());
        largeText.setFont(window.getTextFont());
        bounceDefinition.setFont(window.getSubHeadingFont());
        minPagesVisited.setFont(window.getTextFont());
        minWebsiteTime.setFont(window.getTextFont());
        webPagesSpinner.setFont(window.getTextFont());
        webTimeSpinner.setFont(window.getTextFont());
        applyBut.setFont(window.getButtonBigFont());
    }

}