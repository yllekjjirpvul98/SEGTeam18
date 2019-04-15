package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GraphFilterFrame extends JFrame {

    public GraphFilterFrame(View window, int index) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setVisible(true);

        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();


        JPanel panel = new JPanel();
        panel.setBackground(window.getActionButColor());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));

        ListModel listModel = new DefaultListModel();

        JList filterList = new JList(listModel);
        filterList.setFont(window.getTextFont());
        filterList.setBackground(window.getActionButColor());
        filterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        filterList.setLayoutOrientation(JList.VERTICAL);
        filterList.setVisibleRowCount(-1);

        String graphNumber = (String) window.getDashboardPanel().getDataPanel().getGraphList().getModel().getElementAt(index);
        String[] graphNumberArray = graphNumber.split(" ");

        String number = graphNumberArray[2];

        ((DefaultListModel) listModel).addElement("----- Graph " + (number) + " Filters -----");


        ArrayList<String> filters = window.getDashboardPanel().getGraphPanel().getSavedFilterLists().get(index);

        for(String s : filters){
            ((DefaultListModel) listModel).addElement(s);
        }

        panel.add(filterList);

        this.add(panel);
        this.pack();
        this.setLocation(x, y);

    }

    public void close(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
