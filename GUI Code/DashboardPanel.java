import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel(){

        this.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.blue);
        northPanel.setPreferredSize(new Dimension(1600, 150));
        this.add(northPanel, BorderLayout.NORTH);

        DataPanel dataPanel = new DataPanel();
        dataPanel.setBackground(Color.green);
        dataPanel.setPreferredSize(new Dimension(800, 850));
        this.add(dataPanel, BorderLayout.WEST);

        JPanel filterPanel = new FilterPanel();
        filterPanel.setBackground(Color.red);
        filterPanel.setPreferredSize(new Dimension(800, 850));
        this.add(filterPanel, BorderLayout.EAST);

    }

}
