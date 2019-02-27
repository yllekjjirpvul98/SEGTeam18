import javax.swing.*;
import java.awt.*;

public class GuiFrame extends JFrame {

    public GuiFrame (String title) {
        super(title);
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container window = this.getContentPane();

        this.setVisible(true);

        LoginPanel loginPanel = new LoginPanel();
        window.add(loginPanel);

        this.pack();
        this.setSize( 2400, 1400);
    }
}
