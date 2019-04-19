package View;

import Control.Controller;
import javax.swing.*;
import java.awt.*;

/*
    Class encapsulates all the front end code of the program.
    Acts as the main JFrame the user interacts with.
 */

public class View extends JFrame {

    private Controller control;

    private Dimension screenDim;
    private Dimension windowDim;
    private Dimension widthBorderDim;
    private Dimension hightBorderDim;

    private Font titleFont;
    private Font headingFont;
    private Font subHeadingFont;
    private Font textFont;
    private Font textFontBold;
    private Font buttonTitleFont;
    private Font buttonBigFont;
    private Font buttonSmallFont;
    private Color backgoundColor;
    private Color filterColor;
    private Color filterColor2;
    private Color headingColour;
    private Color highlightColor;
    private Color unhighlightColor;
    private Color addColor;
    private Color delColor;
    private Color actionButColor;

    private Container window;
    private LoginPanel loginPanel;
    private DashboardPanel dashboardPanel;
    private SettingsPanel settingsPanel;

    private SettingFrame settingFrame;
    private CampFrame campFrame;


    /*
        Constructor sets component size references as a fraction of the users screen size.
        Sets various fonts and colours to be referenced by components throughout the program.
     */
    public View(String title) {
        super(title);
        screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        windowDim = new Dimension(3 * ((screenDim.width) / 4), 3 * ((screenDim.height) / 4));

        int textSizeXL = ((int) Math.round(screenDim.getHeight() / 18)) + 2; //80 at 1440p
        int textSizeL = (Math.round((textSizeXL / 8) * 5)) + 2;          //50 at 1440p
        int textSizeM = (Math.round((textSizeXL / 8) * 3)) + 2;          //30 at 1440p
        int textSizeS = (Math.round((textSizeXL / 16) * 5)) + 2;         //25 at 1440p
        int textSizeXS = (Math.round((textSizeXL / 16) * 3)) + 2;        //15 at 1440p

        widthBorderDim = new Dimension(textSizeS, 0);
        hightBorderDim = new Dimension(0, textSizeS);

        setTitleFont(new Font("Verdana", Font.BOLD, textSizeXL));
        setHeadingFont(new Font("Verdana", Font.BOLD, textSizeL));
        setSubHeadingFont(new Font("Verdana", Font.PLAIN, textSizeM));
        setTextFont(new Font("Verdana", Font.PLAIN, textSizeXS));
        setTextFontBold(new Font("Verdana", Font.BOLD, textSizeXS));
        setButtonTitleFont(new Font("Verdana", Font.BOLD, textSizeS));
        setButtonBigFont(new Font("Verdana", Font.BOLD, textSizeXS));
        setButtonSmallFont(new Font("Verdana", Font.PLAIN, textSizeXS));

        setHeadingColour(new Color(0x4167B2));
        setHighlightColor(new Color(0x71B9FF));
        setUnhighlightColor(new Color(0xFFFFFF));
        setBackgoundColor(new Color(0xEAEBEF));
        setFilterColor(new Color(0x468DDF));
        setFilterColor2(new Color(0x468DDF));
        setAddColor(new Color(0x71B9FF));
        setDelColor(new Color(0x71B9FF));
        setActionButColor(new Color(0xEDB980));
    }

    public void init(Controller control) {
        this.control = control;

        window = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        loginPanel = new LoginPanel(this);
        settingsPanel = new SettingsPanel(this);
        dashboardPanel = new DashboardPanel(this);

        this.add(loginPanel);
        this.pack();
        this.setSize(windowDim);
        this.setLocation(screenDim.width / 2 - this.getSize().width / 2, screenDim.height / 2 - this.getSize().height / 2);
    }

    // Change the panel being displayed in the window depending on given string.
    public void changePanel(String panel) {
        Dimension windowSize = new Dimension(this.getWidth(), this.getHeight());
        window.removeAll();
        this.repaint();

        if (panel.equals("loginPanel"))
            window.add(loginPanel);
        else if (panel.equals("dashboardPanel")) {
            window.add(dashboardPanel);
        } else if (panel.equals("settingsPanel"))
            window.add(settingsPanel);

        this.pack();
        this.setSize(windowSize);
    }

    // Update GUI colours.
    public void updateColors(){
        loginPanel.updateColors();
        dashboardPanel.updateColors();
        settingsPanel.updateColors();
    }

    // Update GUI sizing.
    public void updateTextSize(){
        loginPanel.updateTextSize();
        settingsPanel.updateTextSize();
        dashboardPanel.updateTextSize();
    }

    // Getters and Setters.
    public DashboardPanel getDashboardPanel() {
        return dashboardPanel;
    }

    public Controller getControl() {
        return control;
    }

    public Color getBackgoundColor() {
        return backgoundColor;
    }

    public Color getUnhighlightColor() {
        return unhighlightColor;
    }

    public Color getHighlightColor() {
        return highlightColor;
    }

    public Color getHeadingColour() {
        return headingColour;
    }

    public Font getButtonSmallFont() {
        return buttonSmallFont;
    }

    public Font getButtonBigFont() {
        return buttonBigFont;
    }

    public Font getTextFont() {
        return textFont;
    }

    public Font getSubHeadingFont() {
        return subHeadingFont;
    }

    public Font getHeadingFont() {
        return headingFont;
    }

    public Font getButtonTitleFont() {
        return buttonTitleFont;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public Dimension getWidthBorderDim() {
        return widthBorderDim;
    }

    public Dimension getHightBorderDim() {
        return hightBorderDim;
    }

    public Font getTextFontBold() {
        return textFontBold;
    }

    public void setHeadingColour(Color color) {
        headingColour = color;
    }

    public void setHighlightColor(Color color) {
        highlightColor = color;
    }

    public void setUnhighlightColor(Color color) {
        unhighlightColor = color;
    }

    public void setBackgoundColor(Color color) {
        backgoundColor = color;
    }

    public void setFilterColor(Color color) {
        filterColor = color;
    }

    public Color getFilterColor() {
        return filterColor;
    }

    public Color getFilterColor2() {
        return filterColor2;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public SettingFrame getSettingFrame() {
        return settingFrame;
    }

    public void setSettingFrame(SettingFrame settingFrame) {
        this.settingFrame = settingFrame;
    }

    public CampFrame getCampFrame() {
        return campFrame;
    }

    public void setCampFrame(CampFrame campFrame) {
        this.campFrame = campFrame;
    }

    public void setFilterColor2(Color filterColor2) {
        this.filterColor2 = filterColor2;
    }

    public Color getAddColor() {
        return addColor;
    }

    public void setAddColor(Color addColor) {
        this.addColor = addColor;
    }

    public Color getDelColor() {
        return delColor;
    }

    public void setDelColor(Color delColor) {
        this.delColor = delColor;
    }

    public Color getActionButColor() {
        return actionButColor;
    }

    public void setActionButColor(Color actionButColor) {
        this.actionButColor = actionButColor;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    public void setHeadingFont(Font headingFont) {
        this.headingFont = headingFont;
    }

    public void setSubHeadingFont(Font subHeadingFont) {
        this.subHeadingFont = subHeadingFont;
    }

    public void setTextFont(Font textFont) {
        this.textFont = textFont;
    }

    public void setTextFontBold(Font textFontBold) {
        this.textFontBold = textFontBold;
    }

    public void setButtonTitleFont(Font buttonTitleFont) {
        this.buttonTitleFont = buttonTitleFont;
    }

    public void setButtonBigFont(Font buttonBigFont) {
        this.buttonBigFont = buttonBigFont;
    }

    public void setButtonSmallFont(Font buttonSmallFont) {
        this.buttonSmallFont = buttonSmallFont;
    }
}

