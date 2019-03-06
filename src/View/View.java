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

    private Font titleFont;
    private Font headingFont;
    private Font subHeadingFont;
    private Font textFont;
    private Font textFontBold;
    private Font buttonTitleFont;
    private Font buttonBigFont;
    private Font buttonSmallFont;

    private Color headingColour;
    private Color highlightColor;
    private Color unhighlightColor;

    private Container window;
    private LoginPanel loginPanel;
    private HelpPanel helpPanel;
    private DashboardPanel dashboardPanel;


    public View(String title) {
        super(title);
        screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        windowDim = new Dimension(3 * ((screenDim.width)/4), 3 * ((screenDim.height)/4));

        int textSizeXL = (int) Math.round(screenDim.getHeight()/18); //80 at 1440p
        int textSizeL = (int) Math.round((textSizeXL/8)*5);          //50 at 1440p
        int textSizeM = (int) Math.round((textSizeXL/8)*3);          //30 at 1440p
        int textSizeS = (int) Math.round((textSizeXL/16)*5);         //25 at 1440p
        int textSizeXS = (int) Math.round((textSizeXL/16)*3);        //15 at 1440p

        widthBorderDim = new Dimension(textSizeS,0);
        hightBorderDim = new Dimension(0, textSizeS);


        titleFont = new Font("Courier", Font.BOLD, textSizeXL);
        headingFont = new Font("Courier", Font.BOLD, textSizeL);
        subHeadingFont = new Font("Courier", Font.PLAIN, textSizeM);
        textFont = new Font("Courier", Font.PLAIN, textSizeXS);
        textFontBold = new Font("Courier", Font.BOLD, textSizeXS);
        buttonTitleFont = new Font("Courier", Font.BOLD, textSizeS);
        buttonBigFont = new Font("Courier", Font.BOLD, textSizeXS);
        buttonSmallFont = new Font("Courier", Font.PLAIN, textSizeXS);

        headingColour = new Color(0x2865E1);
        highlightColor = new Color(0x76B8FF);
        unhighlightColor = Color.lightGray;
    }

    public void init(Controller control){
        this.control = control;

        window = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        loginPanel = new LoginPanel(this);
        helpPanel = new HelpPanel(this);
        dashboardPanel = new DashboardPanel(this);

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
}
