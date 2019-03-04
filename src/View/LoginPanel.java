package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel {

    private GuiFrame window;
    private String username;
    private String password;

    public LoginPanel(GuiFrame window){
        this.window = window;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.init();
    }

    private void init(){
        username = " Username                                         ";
        password = " password                 ";

        //  ---- Creating components ----

        JLabel title = new JLabel("Ad Auction Monitor");
        title.setFont(new Font("Courier", Font.BOLD, 75));
        title.setForeground(new Color(0x2865E1));

        JTextField usernameField = new JTextField(username);
        usernameField.setFont(new Font("Courier", Font.PLAIN, 15));
        usernameField.setMaximumSize(new Dimension(400,30));

        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usernameField.setText("");
            }
        });

        JPasswordField passwordField = new JPasswordField(password);
        passwordField.setFont(new Font("Courier", Font.PLAIN, 15));
        passwordField.setMaximumSize(new Dimension(400,30));

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setText("");
            }
        });

        JButton loginBut = new JButton("     Login     ");
        loginBut.setFont(new Font ("Courier", Font.BOLD, 20));
        loginBut.setMaximumSize(new Dimension(200,50));
        loginBut.setBackground(new Color(0x76B8FF));

        loginBut.addActionListener(e -> window.changePanel("dashboardPanel"));

        JButton helpBut = new JButton("?");
        helpBut.setFont(new Font( "Courier", Font.BOLD, 20));
        helpBut.setBackground(Color.lightGray);

        helpBut.addActionListener(e -> window.changePanel("helpPanel"));

        //  ---- Layout ----

        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row1.add(Box.createHorizontalGlue());
        row1.add(title);
        row1.add(Box.createHorizontalGlue());

        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
        row2.add(Box.createHorizontalGlue());
        row2.add(usernameField);
        row2.add(Box.createHorizontalGlue());

        JPanel row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
        row3.add(Box.createHorizontalGlue());
        row3.add(passwordField);
        row3.add(Box.createHorizontalGlue());

        JPanel row4 = new JPanel();
        row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
        row4.add(Box.createHorizontalGlue());
        row4.add(loginBut);
        row4.add(Box.createHorizontalGlue());

        JPanel row5 = new JPanel();
        row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));
        row5.add(Box.createHorizontalGlue());
        row5.add(helpBut);
        row5.add(Box.createRigidArea(new Dimension(20,0)));

        this.add(Box.createRigidArea(new Dimension(0,100)));
        this.add(row1);
        this.add(Box.createRigidArea(new Dimension(0,150)));
        this.add(row2);
        this.add(Box.createRigidArea(new Dimension(0,25)));
        this.add(row3);
        this.add(Box.createRigidArea(new Dimension(0,100)));
        this.add(row4);
        this.add(Box.createVerticalGlue());
        this.add(row5);
        this.add(Box.createRigidArea(new Dimension(0,20)));


    }

}
