import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private String username;
    private String password;

    public LoginPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.init();
    }

    private void init(){
        username = "username";
        password = "password";

        JLabel title = new JLabel("Ad Auction Monitor");
        title.setFont(new Font("Serif", Font.PLAIN, 100));
        title.setForeground(Color.blue);

        JTextField usernameField = new JTextField(username);
        JTextField passwordField = new JTextField(password);

        JButton loginBut = new JButton("Login");

        JPanel col2 = new JPanel();
        col2.setLayout(new BoxLayout(col2, BoxLayout.PAGE_AXIS));

        col2.add(Box.createRigidArea(new Dimension(0, 200)));
        col2.add(title);
        col2.add(Box.createRigidArea(new Dimension(0,200)));
        col2.add(usernameField);
        col2.add(Box.createRigidArea(new Dimension(0,100)));
        col2.add(passwordField);
        col2.add(Box.createVerticalGlue());
        col2.add(Box.createRigidArea(new Dimension(0,200)));
        col2.add(loginBut);
        col2.add(Box.createRigidArea(new Dimension(0, 200)));

        this.add(Box.createRigidArea(new Dimension(300,0)));
        this.add(Box.createHorizontalGlue());
        this.add(col2);
        this.add(Box.createHorizontalGlue());
        this.add(Box.createRigidArea(new Dimension(300,0)));


    }

}
