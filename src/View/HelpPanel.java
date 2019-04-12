package View;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {

    private View window;

    private JLabel title;
    private JButton backBut;
    private JLabel infoLabel1;
    private JLabel infoLabel2;
    private JLabel infoLabel3;
    private JTextArea infoArea1;
    private JTextArea infoArea2;
    private JTextArea infoArea3;
    private JPanel row1n;
    private JPanel row2n;
    private JPanel row1w;
    private JPanel row2w;
    private JPanel row3w;
    private JPanel row4w;
    private JPanel row1e;
    private JPanel row2e;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel eastPanel;

    public HelpPanel(View window){
        this.window = window;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){

        //  ---- Creating components ----

        title = new JLabel("Help");
        title.setFont(window.getHeadingFont());
        title.setForeground(window.getHeadingColour());

        infoLabel1 = new JLabel("What is Ad Auction Monitor?");
        infoLabel1.setFont(window.getSubHeadingFont());

        infoArea1 = new JTextArea("Ad Auction Monitor is a system which allows you\nto view performance of your advertising campaigns.\nThrough the use of editable filter you can apply to\nyour campaign data.");
        infoArea1.setBackground(window.getBackgoundColor());
        infoArea1.setFont(window.getTextFont());
        infoArea1.setBackground(this.window.getBackground());
        infoArea1.setEditable(false);

        infoLabel2 = new JLabel("How do I use Ad Auction Monitor?");
        infoLabel2.setFont(window.getSubHeadingFont());

        infoArea2 = new JTextArea("You can use Ad Auction monitor by..........");
        infoArea2.setBackground(window.getBackgoundColor());
        infoArea2.setFont(window.getTextFont());
        infoArea2.setBackground(this.window.getBackground());
        infoArea2.setEditable(false);

        infoLabel3 = new JLabel("Glossary");
        infoLabel3.setFont(window.getSubHeadingFont());

        infoArea3 = new JTextArea("Word 1 - Description dsklfj sdkfjlsdk jslkd klsdf lks\nWord 2 - Description dfsdjf  sdjfls dkljf skjsdklj\nsdkj fskdj lskjlk djsflkjsd kf slkdjflks jf");
        infoArea3.setBackground(window.getBackgoundColor());
        infoArea3.setFont(window.getTextFont());
        infoArea3.setBackground(this.window.getBackground());
        infoArea3.setEditable(false);

        backBut = new JButton("Back");
        backBut.setFont(window.getButtonBigFont());
        backBut.setBackground(window.getUnhighlightColor());

        backBut.addActionListener(e -> window.changePanel("loginPanel"));


        //  ---- Layout ----

        // North Panel - Contains title and back
        northPanel = new JPanel();
        northPanel.setBackground(window.getBackgoundColor());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        row1n = new JPanel();
        row1n.setBackground(window.getBackgoundColor());
        row1n.setLayout(new BoxLayout(row1n, BoxLayout.LINE_AXIS));
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1n.add(backBut);
        row1n.add(Box.createHorizontalGlue());

        row2n = new JPanel();
        row2n.setBackground(window.getBackgoundColor());
        row2n.setLayout(new BoxLayout(row2n, BoxLayout.LINE_AXIS));
        row2n.add(Box.createHorizontalGlue());
        row2n.add(title);
        row2n.add(Box.createHorizontalGlue());

        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(row1n);
        northPanel.add(row2n);
        northPanel.add(Box.createRigidArea(new Dimension(0,window.getButtonTitleFont().getSize() * 4)));  // 100 at 1440p


        // West Panel - Contains info1 and 2, then info3 and 4.
        westPanel = new JPanel();
        westPanel.setBackground(window.getBackgoundColor());
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));

        row1w = new JPanel();
        row1w.setBackground(window.getBackgoundColor());
        row1w.setLayout(new BoxLayout(row1w, BoxLayout.LINE_AXIS));
        row1w.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 3, 0)));  // 75 at 1440p
        row1w.add(infoLabel1);
        row1w.add(Box.createHorizontalGlue());

        row2w = new JPanel();
        row2w.setBackground(window.getBackgoundColor());
        row2w.setLayout(new BoxLayout(row2w, BoxLayout.LINE_AXIS));
        row2w.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 3, 0)));  // 75 at 1440p
        row2w.add(infoArea1);
        row2w.add(Box.createHorizontalGlue());

        row3w = new JPanel();
        row3w.setBackground(window.getBackgoundColor());
        row3w.setLayout(new BoxLayout(row3w, BoxLayout.LINE_AXIS));
        row3w.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 3, 0)));  // 75 at 1440p
        row3w.add(infoLabel2);
        row3w.add(Box.createHorizontalGlue());

        row4w = new JPanel();
        row4w.setBackground(window.getBackgoundColor());
        row4w.setLayout(new BoxLayout(row4w, BoxLayout.LINE_AXIS));
        row4w.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 3, 0)));  // 75 at 1440p
        row4w.add(infoArea2);
        row4w.add(Box.createHorizontalGlue());

        westPanel.add(row1w);
        westPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        westPanel.add(row2w);
        westPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        westPanel.add(row3w);
        westPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        westPanel.add(row4w);


        // East Panel - Contains info5 and 6.
        eastPanel = new JPanel();
        eastPanel.setBackground(window.getBackgoundColor());
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));

        row1e = new JPanel();
        row1e.setBackground(window.getBackgoundColor());
        row1e.setLayout(new BoxLayout(row1e, BoxLayout.LINE_AXIS));
        row1e.add(Box.createHorizontalGlue());
        row1e.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 4, 0)));  // 100 at 1440p
        row1e.add(infoLabel3);
        row1e.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 3, 0)));  // 75 at 1440p

        row2e = new JPanel();
        row2e.setBackground(window.getBackgoundColor());
        row2e.setLayout(new BoxLayout(row2e, BoxLayout.LINE_AXIS));
        row2e.add(Box.createHorizontalGlue());
        row2e.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 4, 0)));  // 100 at 1440p
        row2e.add(infoArea3);
        row2e.add(Box.createRigidArea(new Dimension(window.getButtonTitleFont().getSize() * 3, 0)));  // 75 at 1440p


        eastPanel.add(row1e);
        eastPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        eastPanel.add(row2e);


        // Adds the 4 borders to the main panel.
        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
    }

    public void updateColors(){
        this.setBackground(window.getBackgoundColor());
        title.setForeground(window.getHeadingColour());
        backBut.setBackground(window.getUnhighlightColor());
        row1n.setBackground(window.getBackgoundColor());
        row2n.setBackground(window.getBackgoundColor());
        row1w.setBackground(window.getBackgoundColor());
        row2w.setBackground(window.getBackgoundColor());
        row3w.setBackground(window.getBackgoundColor());
        row4w.setBackground(window.getBackgoundColor());
        row1e.setBackground(window.getBackgoundColor());
        row2w.setBackground(window.getBackgoundColor());
        eastPanel.setBackground(window.getBackgoundColor());
        westPanel.setBackground(window.getBackgoundColor());
        northPanel.setBackground(window.getBackgoundColor());
    }

    public void updateTextSize(){
        title.setFont(window.getHeadingFont());
        infoArea1.setFont(window.getTextFont());
        infoArea2.setFont(window.getTextFont());
        infoArea3.setFont(window.getTextFont());
        infoLabel1.setFont(window.getSubHeadingFont());
        infoLabel2.setFont(window.getSubHeadingFont());
        infoLabel3.setFont(window.getSubHeadingFont());
        backBut.setFont(window.getButtonBigFont());


    }

}
