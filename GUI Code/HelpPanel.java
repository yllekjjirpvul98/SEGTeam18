import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {

    public HelpPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.init();
    }

    private void init(){

        JLabel title = new JLabel("Help");
        title.setFont(new Font("Serif", Font.PLAIN, 100));
        title.setForeground(Color.blue);

        JLabel infoLabel1 = new JLabel("What is Ad Auction Monitor?");
        infoLabel1.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel infoLabel2 = new JLabel("Ad Auction Monitor is a system which allows you to view performance of your advertising campaigns........");

        JLabel infoLabel3 = new JLabel("How do I use Ad Auction Monitor?");
        infoLabel3.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel infoLabel4 = new JLabel("You can use Ad Auction monitor by..........");

        JLabel infoLabel5 = new JLabel("Glossary");
        infoLabel5.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel infoLabel6 = new JLabel("Glossary info ..............");

        JButton backButton = new JButton("Back");

        JPanel col2 = new JPanel();
        col2.setLayout(new BoxLayout(col2, BoxLayout.PAGE_AXIS));

        col2.add(Box.createRigidArea(new Dimension(0, 200)));
        col2.add(title);
        col2.add(infoLabel1);
        col2.add(infoLabel2);
        col2.add(Box.createVerticalGlue());
        col2.add(infoLabel3);
        col2.add(infoLabel4);
        col2.add(infoLabel5);
        col2.add(infoLabel6);
        col2.add(backButton);

        this.add(Box.createRigidArea(new Dimension(300,0)));
        this.add(Box.createHorizontalGlue());
        this.add(col2);
        this.add(Box.createHorizontalGlue());
        this.add(Box.createRigidArea(new Dimension(300,0)));


    }

}