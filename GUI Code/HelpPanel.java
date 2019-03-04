import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {

    private GuiFrame window;

    public HelpPanel(GuiFrame window){
        this.window = window;
        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){

        //  ---- Creating components ----

        JLabel title = new JLabel("Help");
        title.setFont(new Font("Courier", Font.BOLD, 60));
        title.setForeground(new Color(0x2865E1));

        JLabel infoLabel1 = new JLabel("What is Ad Auction Monitor?");
        infoLabel1.setFont(new Font("Courier", Font.BOLD, 30));


        JTextArea infoArea1 = new JTextArea("Ad Auction Monitor is a system which allows you\nto view performance of your advertising campaigns.\nThrough the use of editable filter you can apply to\nyour campaign data.");
        infoArea1.setFont(new Font("Courier", Font.PLAIN, 20));
        infoArea1.setBackground(this.window.getBackground());
        infoArea1.setEditable(false);

        JLabel infoLabel2 = new JLabel("How do I use Ad Auction Monitor?");
        infoLabel2.setFont(new Font("Courier", Font.BOLD, 30));

        JTextArea infoArea2 = new JTextArea("You can use Ad Auction monitor by..........");
        infoArea2.setFont(new Font("Courier", Font.PLAIN, 20));
        infoArea2.setBackground(this.window.getBackground());
        infoArea2.setEditable(false);

        JLabel infoLabel3 = new JLabel("Glossary");
        infoLabel3.setFont(new Font("Courier", Font.BOLD, 30));

        JTextArea infoArea3 = new JTextArea("Word 1 - Description dsklfj sdkfjlsdk jslkd klsdf lks\nWord 2 - Description dfsdjf  sdjfls dkljf skjsdklj\nsdkj fskdj lskjlk djsflkjsd kf slkdjflks jf");
        infoArea3.setFont(new Font("Courier", Font.PLAIN, 20));
        infoArea3.setBackground(this.window.getBackground());
        infoArea3.setEditable(false);

        JButton backBut = new JButton("Back");
        backBut.setFont(new Font( "Courier", Font.BOLD, 20));
        backBut.setBackground(Color.lightGray);

        backBut.addActionListener(e -> window.changePanel("loginPanel"));


        //  ---- Layout ----

        // North Panel - Contains title and back
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        JPanel row1n = new JPanel();
        row1n.setLayout(new BoxLayout(row1n, BoxLayout.LINE_AXIS));
        row1n.add(Box.createRigidArea(new Dimension(25, 0)));
        row1n.add(backBut);
        row1n.add(Box.createHorizontalGlue());

        JPanel row2n = new JPanel();
        row2n.setLayout(new BoxLayout(row2n, BoxLayout.LINE_AXIS));
        row2n.add(Box.createHorizontalGlue());
        row2n.add(title);
        row2n.add(Box.createHorizontalGlue());

        northPanel.add(Box.createRigidArea(new Dimension(0,25)));
        northPanel.add(row1n);
        northPanel.add(row2n);
        northPanel.add(Box.createRigidArea(new Dimension(0,100)));


        // West Panel - Contains info1 and 2, then info3 and 4.
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));

        JPanel row1w = new JPanel();
        row1w.setLayout(new BoxLayout(row1w, BoxLayout.LINE_AXIS));
        row1w.add(Box.createRigidArea(new Dimension(80,0)));
        row1w.add(infoLabel1);
        row1w.add(Box.createHorizontalGlue());

        JPanel row2w = new JPanel();
        row2w.setLayout(new BoxLayout(row2w, BoxLayout.LINE_AXIS));
        row2w.add(Box.createRigidArea(new Dimension(80,0)));
        row2w.add(infoArea1);
        row2w.add(Box.createHorizontalGlue());

        JPanel row3w = new JPanel();
        row3w.setLayout(new BoxLayout(row3w, BoxLayout.LINE_AXIS));
        row3w.add(Box.createRigidArea(new Dimension(80,0)));
        row3w.add(infoLabel2);
        row3w.add(Box.createHorizontalGlue());

        JPanel row4w = new JPanel();
        row4w.setLayout(new BoxLayout(row4w, BoxLayout.LINE_AXIS));
        row4w.add(Box.createRigidArea(new Dimension(80,0)));
        row4w.add(infoArea2);
        row4w.add(Box.createHorizontalGlue());

        westPanel.add(row1w);
        westPanel.add(Box.createRigidArea(new Dimension(0,25)));
        westPanel.add(row2w);
        westPanel.add(Box.createRigidArea(new Dimension(0,200)));
        westPanel.add(row3w);
        westPanel.add(Box.createRigidArea(new Dimension(0,25)));
        westPanel.add(row4w);


        // East Panel - Contains info5 and 6.
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));

        JPanel row1e = new JPanel();
        row1e.setLayout(new BoxLayout(row1e, BoxLayout.LINE_AXIS));
        row1e.add(Box.createHorizontalGlue());
        row1e.add(Box.createRigidArea(new Dimension(100,0)));
        row1e.add(infoLabel3);
        row1e.add(Box.createRigidArea(new Dimension(80,0)));

        JPanel row2e = new JPanel();
        row2e.setLayout(new BoxLayout(row2e, BoxLayout.LINE_AXIS));
        row2e.add(Box.createHorizontalGlue());
        row2e.add(Box.createRigidArea(new Dimension(100,0)));
        row2e.add(infoArea3);
        row2e.add(Box.createRigidArea(new Dimension(80,0)));


        eastPanel.add(row1e);
        eastPanel.add(Box.createRigidArea(new Dimension(0,25)));
        eastPanel.add(row2e);


        // Adds the 4 borders to the main panel.
        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
    }

}
