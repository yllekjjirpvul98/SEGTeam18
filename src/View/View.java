package View;

import Control.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private Controller control;

    private Dimension screenDim;
    private Dimension windowDim;
    private Dimension widthBorderDim;
    private Dimension hightBorderDim;

    private int textSizeXL;
    private int textSizeL;
    private int textSizeM;
    private int textSizeS;
    private int textSizeXS;
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

    private Container window;
    private LoginPanel loginPanel;
    private HelpPanel helpPanel;
    private DashboardPanel dashboardPanel;
    private SettingsPanel settingsPanel;


    public View(String title) {
        super(title);
        screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        windowDim = new Dimension(3 * ((screenDim.width)/4), 3 * ((screenDim.height)/4));

        int textSizeXL = ((int) Math.round(screenDim.getHeight()/18)) + 2; //80 at 1440p
        int textSizeL = (Math.round((textSizeXL/8)*5)) + 2;          //50 at 1440p
        int textSizeM = (Math.round((textSizeXL/8)*3)) + 2;          //30 at 1440p
        int textSizeS = (Math.round((textSizeXL/16)*5)) + 2;         //25 at 1440p
        int textSizeXS = (Math.round((textSizeXL/16)*3)) + 2;        //15 at 1440p

        widthBorderDim = new Dimension(textSizeS,0);
        hightBorderDim = new Dimension(0, textSizeS);

        titleFont = new Font("Verdana", Font.BOLD, textSizeXL);
        headingFont = new Font("Verdana", Font.BOLD, textSizeL);
        subHeadingFont = new Font("Verdana", Font.PLAIN, textSizeM);
        textFont = new Font("Verdana", Font.PLAIN, textSizeXS);
        textFontBold = new Font("Verdana", Font.BOLD, textSizeXS);
        buttonTitleFont = new Font("Verdana", Font.BOLD, textSizeS);
        buttonBigFont = new Font("Verdana", Font.BOLD, textSizeXS);
        buttonSmallFont = new Font("Verdana", Font.PLAIN, textSizeXS);

        backgoundColor = new Color(0x8DABCF);
        filterColor = new Color(0xEDB980);
        filterColor2 = new Color(0xEDB980);
        headingColour = new Color(0xFBFBFB);
        highlightColor = new Color(0x71B9FF);
        unhighlightColor = new Color(0xF7F7F7);
    }

    public void init(Controller control){
        this.control = control;

        window = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        loginPanel = new LoginPanel(this);
        helpPanel = new HelpPanel(this);
        dashboardPanel = new DashboardPanel(this);
        settingsPanel = new SettingsPanel(this);

        this.add(loginPanel);
        this.pack();
        this.setSize(windowDim);
    }

    public void changePanel(String panel){
        Dimension windowSize = new Dimension(this.getWidth(),this.getHeight());
        window.removeAll();
        this.repaint();

        if (panel.equals("loginPanel"))
            window.add(loginPanel);
        else if (panel.equals("helpPanel"))
            window.add(helpPanel);
        else if (panel.equals("dashboardPanel"))
            window.add(dashboardPanel);
        else if (panel.equals("settingsPanel"))
            window.add(settingsPanel);

        this.pack();
        this.setSize(windowSize);
    }

    public Controller getControl() {
        return control;
    }

    public Dimension getScreenSize() {
        return screenDim;
    }

    public Dimension getWindowDim() {
        return windowDim;
    }

    public Color getBackgoundColor(){
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

    public void setHeadingColour(Color black) {
    }

    public void setHighlightColor(Color darkGray) {
    }

    public void setUnhighlightColor(Color gray) {
    }

    public int getTextSizeXL() {
        return textSizeXL;
    }

    public void setTextSizeXL(int textSizeXL) {
        this.textSizeXL = textSizeXL;
    }

    public int getTextSizeL() {
        return textSizeL;
    }

    public void setTextSizeL(int textSizeL) {
        this.textSizeL = textSizeL;
    }

    public int getTextSizeM() {
        return textSizeM;
    }

    public void setTextSizeM(int textSizeM) {
        this.textSizeM = textSizeM;
    }

    public int getTextSizeS() {
        return textSizeS;
    }

    public void setTextSizeS(int textSizeS) {
        this.textSizeS = textSizeS;
    }

    public int getTextSizeXS() {
        return textSizeXS;
    }

    public void setTextSizeXS(int textSizeXS) {
        this.textSizeXS = textSizeXS;
    }

    public Color getFilterColor() {
        return filterColor;
    }

    public Color getFilterColor2() {
        return filterColor2;
    }
}
