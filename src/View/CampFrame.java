package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
    Class holds the LoginPanel after initial loading of data.
    Displays as a popup menu off the main Dashboard interface.
 */

class CampFrame extends JFrame {

    CampFrame(View window){
        super("Campaign Select");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                window.getDashboardPanel().getCampBut().setEnabled(true);
            }
        });

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameDim = new Dimension(3 * ((screenDim.width)/10), 3 * ((screenDim.height)/8));

        this.add(window.getLoginPanel());
        this.pack();
        this.setSize(frameDim);
        this.setLocation(screenDim.width/2-this.getSize().width/2, screenDim.height/2-this.getSize().height/2);
    }

    // Closing window only closes this frame instead of closing the program.
    public void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}


