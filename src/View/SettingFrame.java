package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class SettingFrame extends JFrame {

    private Container frame;

    public SettingFrame(View window){
        super("Settings");

        frame = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameDim = new Dimension(4 * ((screenDim.width)/12), 3 * ((screenDim.height)/10));

        this.add(window.getSettingsPanel());
        this.pack();
        this.setSize(frameDim);
        this.setLocation(screenDim.width/2-this.getSize().width/2, screenDim.height/2-this.getSize().height/2);
    }

    public void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
