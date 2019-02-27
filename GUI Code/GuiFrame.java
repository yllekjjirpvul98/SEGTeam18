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

        DashboardPanel loginPanel = new DashboardPanel();
        window.add(loginPanel);

        this.pack();
        this.setSize( 1600, 900);
    }
}
