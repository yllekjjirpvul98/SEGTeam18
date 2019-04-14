package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoginPanel extends JPanel {

    private View window;
    private File currentCamp;
    private File lastLoadedCamp;
    private boolean firstLogin;

    private JLabel title;
    private JButton loadBut;
    private JPanel row1;
    private JPanel row2;
    private JPanel row3;
    private JPanel row4;
    private JButton delBut;
    private JButton addbut;
    private JList campList;


    public LoginPanel(View window){
        this.window = window;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        currentCamp = null;
        lastLoadedCamp = null;
        firstLogin = true;
        this.init();
    }

    private void init(){

        //  ---- Creating components ----

        title = new JLabel("Campaign Select");
        title.setFont(window.getHeadingFont());
        title.setForeground(window.getHeadingColour());

        loadBut = new JButton("Load Data");
        loadBut.setFont(window.getButtonBigFont());
        loadBut.setBackground(window.getActionButColor());

        row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
        row4 = new JPanel();

        loadBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(currentCamp != null) {
                    boolean checkImp = new File(currentCamp, "impression_log.csv").exists();
                    boolean checkServer = new File(currentCamp, "server_log.csv").exists();
                    boolean checkClick = new File(currentCamp, "click_log.csv").exists();

                    if(checkImp && checkServer && checkClick) {

                        window.getDashboardPanel().setCampName(currentCamp.getName());

                        if(lastLoadedCamp != currentCamp) { // Only reloads if loading a new file else simply switches back

                            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            window.getControl().getModel().populateDatabase(currentCamp.toString());
                            setCursor(null);
                            lastLoadedCamp = currentCamp;

                            if (firstLogin) {
                                window.getDashboardPanel().init();
                                firstLogin = false;
                                window.changePanel("dashboardPanel");

                                removeAll();

                                add(Box.createRigidArea(new Dimension(0,window.getButtonTitleFont().getSize() * 3)));  // 150 at 1440p
                                add(row2);
                                add(Box.createRigidArea(window.getHightBorderDim()));
                                add(row3);
                                add(Box.createRigidArea(new Dimension(0,window.getButtonTitleFont().getSize() * 2)));  // 100 at 1440p
                                add(row4);
                                add(Box.createRigidArea(window.getHightBorderDim()));

                            } else {
                                window.getDashboardPanel().getDataPanel().setCampName(currentCamp.getName());
                                window.getDashboardPanel().reset();
                                window.getCampFrame().close();
                            }

                        }
                        else {
                            window.getCampFrame().close();
                        }

                    }

                    else{
                        JOptionPane.showMessageDialog(window, "Please select a campaign containing required files.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(window, "Please select a campaign.");
                }
            }
        });

        ListModel listModel = new DefaultListModel();

        campList = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(campList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        campList.setFont(window.getTextFont());
        campList.setFixedCellWidth(window.getButtonTitleFont().getSize() * 20);
        campList.setPreferredSize(new Dimension(window.getButtonTitleFont().getSize() * 15, window.getButtonTitleFont().getSize() * 15));
        campList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        campList.setLayoutOrientation(JList.VERTICAL);
        campList.setVisibleRowCount(-1);

        campList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = campList.getSelectedIndex();

                if(index >= 0){
                    currentCamp = (File) ((DefaultListModel) listModel).getElementAt(index);
                }
            }
        });

        delBut = new JButton("DEL");
        delBut.setFont(window.getButtonBigFont());
        delBut.setBackground(window.getDelColor());

        delBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = campList.getSelectedIndex();

                if(index >= 0) {
                    ((DefaultListModel) listModel).remove(index);

                    if (index > 0) {
                        index--;
                        campList.setSelectedIndex(index);
                        campList.ensureIndexIsVisible(index);
                        currentCamp = (File) ((DefaultListModel) listModel).getElementAt(index);
                    }
                    else{
                        currentCamp = null;
                    }
                }
            }
        });

        addbut = new JButton("ADD");
        addbut.setFont(window.getButtonBigFont());
        addbut.setBackground(window.getAddColor());

        addbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Campaign Folder");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int choice = fileChooser.showOpenDialog(window);

                if(choice != JFileChooser.APPROVE_OPTION)
                    return;

                File chosenFile = fileChooser.getSelectedFile();
                ((DefaultListModel) listModel).addElement(chosenFile);
            }
        });

        //  ---- Layout ----

        row1.setBackground(window.getBackgoundColor());
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row1.add(Box.createHorizontalGlue());
        row1.add(title);
        row1.add(Box.createHorizontalGlue());

        row2.setBackground(window.getBackgoundColor());
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
        row2.add(Box.createHorizontalGlue());
        row2.add(scrollPane);
        row2.add(Box.createHorizontalGlue());

        row3.setBackground(window.getBackgoundColor());
        row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
        row3.add(Box.createHorizontalGlue());
        row3.add(addbut);
        row3.add(Box.createRigidArea(window.getWidthBorderDim()));
        row3.add(delBut);
        row3.add(Box.createHorizontalGlue());

        row4.setBackground(window.getBackgoundColor());
        row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
        row4.add(Box.createHorizontalGlue());
        row4.add(loadBut);
        row4.add(Box.createHorizontalGlue());


        this.add(Box.createRigidArea(new Dimension(0,window.getButtonTitleFont().getSize() * 4)));  // 100 at 1440p
        this.add(row1);
        this.add(Box.createRigidArea(new Dimension(0,window.getButtonTitleFont().getSize() * 6)));  // 150 at 1440p
        this.add(row2);
        this.add(Box.createRigidArea(window.getHightBorderDim()));
        this.add(row3);
        this.add(Box.createRigidArea(new Dimension(0,window.getButtonTitleFont().getSize() * 4)));  // 100 at 1440p
        this.add(row4);
        this.add(Box.createVerticalGlue());
        this.add(Box.createRigidArea(window.getHightBorderDim()));

    }

    public void updateColors(){
        this.setBackground(window.getBackgoundColor());
        title.setForeground(window.getHeadingColour());
        loadBut.setBackground(window.getActionButColor());
        delBut.setBackground(window.getDelColor());
        addbut.setBackground(window.getAddColor());
        row1.setBackground(window.getBackgoundColor());
        row2.setBackground(window.getBackgoundColor());
        row3.setBackground(window.getBackgoundColor());
        row4.setBackground(window.getBackgoundColor());
    }

    public void updateTextSize(){
        title.setFont(window.getHeadingFont());
        loadBut.setFont(window.getButtonBigFont());
        addbut.setFont(window.getButtonBigFont());
        delBut.setFont(window.getButtonBigFont());
        campList.setFont(window.getTextFont());
    }

    public File getLastLoadedCamp() {
        return lastLoadedCamp;
    }

    public void setLastLoadedCamp(File lastLoadedCamp) {
        this.lastLoadedCamp = lastLoadedCamp;
    }

}
