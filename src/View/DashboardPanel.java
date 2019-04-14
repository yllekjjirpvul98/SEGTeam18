package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPanel extends JPanel {

    private View window;
    private String campName;
    private boolean histoEnabled;

    private FilterPanel filterPanel;
    private DataPanel dataPanel;
    private GraphPanel graphPanel;

    private JLabel title;
    private JButton printBut;
    private JButton saveBut;
    private JButton campBut;
    private JButton settingsBut;
    private JButton histoBut;
    private JButton lineBut;
    private JComboBox<String> metricSelect;
    private JComboBox<String> timeSelect;
    private int preTimeSelected;
    private JPanel northPanel;
    private JPanel row1n;
    private JPanel row2n;
    private JPanel row3n;

    public DashboardPanel(View window){
        this.window = window;
        this.histoEnabled = false;
        this.setBackground(window.getBackgoundColor());
        this.setLayout(new BorderLayout());
    }

    public void reset(){
        window.getControl().getModel().getFilter().reset();
        filterPanel.reset();
        dataPanel.updateData();
        graphPanel.getGraph().updateSeries();
    }

    public void init() {
        //  ---- Creating Components ----
        filterPanel = new FilterPanel(this);
        dataPanel = new DataPanel(this);
        graphPanel = new GraphPanel(this);

        title = new JLabel("DashBoard");
        title.setFont(window.getHeadingFont());
        title.setForeground(window.getHeadingColour());

        campBut = new JButton(new ImageIcon(((new ImageIcon("CampIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        campBut.setBackground(window.getHighlightColor());

        campBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setCampFrame(new CampFrame(window));
                campBut.setEnabled(false);
            }
        });

        settingsBut = new JButton(new ImageIcon(((new ImageIcon("SettingsIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        settingsBut.setBackground(window.getHighlightColor());

        settingsBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSettingFrame(new SettingFrame(window));
                settingsBut.setEnabled(false);
            }
        });

        printBut = new JButton(new ImageIcon(((new ImageIcon("PrintIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        printBut.setBackground(window.getHighlightColor());

        printBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Print Graph");
            }
        });

        saveBut = new JButton(new ImageIcon(((new ImageIcon("SaveIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        saveBut.setBackground(window.getHighlightColor());

        saveBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Graph");
            }
        });

        String[] metrics = { "Impression","Clicks","Unique","Conversion", "Bounce", "BounceRate", "TotalCost", "CPA", "CPC", "CPM", "CTR"};
        metricSelect = new JComboBox<String>(metrics);
        metricSelect.setVisible(true);
        metricSelect.setBackground(window.getUnhighlightColor());
        metricSelect.setFont(window.getTextFont());
        metricSelect.setMaximumSize(new Dimension(window.getButtonBigFont().getSize() * 10,window.getButtonBigFont().getSize() * 3));
        metricSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox metricSelect = (JComboBox) event.getSource();
                graphPanel.setMetric(metricSelect.getSelectedItem().toString());

                graphPanel.getGraph().updateSeries();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                graphPanel.getGraph().updateSeries();

            }

        });

        String[] TimeScales = {"Hour","Day","Week","Month","Year"};
        timeSelect = new JComboBox<String>(TimeScales);
        preTimeSelected = 1; // TO START ON DAY
        timeSelect.setSelectedIndex(preTimeSelected);
        timeSelect.setFont(window.getTextFont());
        timeSelect.setBackground(window.getUnhighlightColor());
        timeSelect.setMaximumSize(new Dimension(window.getButtonBigFont().getSize() * 10, window.getButtonBigFont().getSize() * 3));
        timeSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox timeSlide = (JComboBox) event.getSource();
                String timeUpper = timeSlide.getSelectedItem().toString();

                if (graphPanel.getSavedDataMaps().size() > 0) {

                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(window, "Changing time granularitry to \"" + timeUpper + "\" will delete saved graphs. \n Do you wish to continue?", "Confirm Action", dialogButton);

                    if (dialogResult == 0) {
                        // YES selected
                        preTimeSelected = timeSelect.getSelectedIndex();
                        graphPanel.setTime(timeUpper.toLowerCase());

                        graphPanel.getSavedDataMaps().clear();
                        graphPanel.getSavedDataLables().clear();

                        while(dataPanel.getListModel().getSize() != 0) {
                            ((DefaultListModel) dataPanel.getListModel()).remove(0);
                        }

                        graphPanel.getGraph().updateSeries();
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        graphPanel.getGraph().updateSeries();

                    } else {
                        // NO selected
                        timeSelect.setSelectedIndex(preTimeSelected);
                    }

                } else {
                    preTimeSelected = timeSelect.getSelectedIndex();
                    graphPanel.setTime(timeUpper.toLowerCase());

                    graphPanel.getGraph().updateSeries();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    graphPanel.getGraph().updateSeries();
                }
            }
        });

        histoBut = new JButton(new ImageIcon(((new ImageIcon("HistoIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        histoBut.setBackground(window.getHighlightColor());

        histoBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!histoEnabled){
                    dataPanel.getAddBut().setEnabled(false);
                    dataPanel.getDeleteBut().setEnabled(false);
                    dataPanel.getGraphList().setEnabled(false);
                    metricSelect.setEnabled(false);
                    timeSelect.setEnabled(false);

                    graphPanel.setMetric("Click Cost Frequency");
                    graphPanel.setGraph(new Graph2(graphPanel));
                    graphPanel.removeAll();
                    graphPanel.init();
                    histoEnabled = true;
                }
            }
        });

        lineBut = new JButton(new ImageIcon(((new ImageIcon("LineIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        lineBut.setBackground(window.getHighlightColor());

        lineBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(histoEnabled){
                    dataPanel.getAddBut().setEnabled(true);
                    dataPanel.getDeleteBut().setEnabled(true);
                    dataPanel.getGraphList().setEnabled(true);
                    metricSelect.setEnabled(true);
                    timeSelect.setEnabled(true);

                    graphPanel.setMetric(metricSelect.getSelectedItem().toString());
                    graphPanel.setGraph(new Graph2(graphPanel));
                    graphPanel.removeAll();
                    graphPanel.init();
                    histoEnabled = false;
                }
            }
        });

        // North Panel --- Logout & Title
        northPanel = new JPanel();
        northPanel.setBackground(window.getBackgoundColor());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        row1n = new JPanel();
        row1n.setBackground(window.getBackgoundColor());

        row1n.setLayout(new BoxLayout(row1n, BoxLayout.LINE_AXIS));
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1n.add(campBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(histoBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(lineBut);
        row1n.add(Box.createHorizontalGlue());
        row1n.add(printBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(saveBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(settingsBut);
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));


        row2n = new JPanel();
        row2n.setBackground(window.getBackgoundColor());

        row2n.setLayout(new BoxLayout(row2n, BoxLayout.LINE_AXIS));
        row2n.add(Box.createHorizontalGlue());
        row2n.add(title);
        row2n.add(Box.createHorizontalGlue());

        row3n = new JPanel();
        row3n.setBackground(window.getBackgoundColor());
        row3n.setLayout(new BoxLayout(row3n, BoxLayout.LINE_AXIS));

        row3n.add(Box.createHorizontalGlue());
        row3n.add(metricSelect);
        row3n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row3n.add(timeSelect);
        row3n.add(Box.createRigidArea(window.getWidthBorderDim()));

        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(row1n);
        northPanel.add(row2n);
        northPanel.add(row3n);
        northPanel.add(Box.createRigidArea(new Dimension(0,window.getButtonBigFont().getSize())));


        this.add(northPanel, BorderLayout.NORTH);
        this.add(filterPanel, BorderLayout.SOUTH);
        this.add(graphPanel, BorderLayout.CENTER);
        this.add(dataPanel, BorderLayout.WEST);
    }

    private void updateNorthPanel(){
        this.remove(northPanel);

        campBut = new JButton(new ImageIcon(((new ImageIcon("CampIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        campBut.setBackground(window.getHighlightColor());

        campBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setCampFrame(new CampFrame(window));
                campBut.setEnabled(false);
            }
        });

        settingsBut = new JButton(new ImageIcon(((new ImageIcon("SettingsIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        settingsBut.setBackground(window.getHighlightColor());

        settingsBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSettingFrame(new SettingFrame(window));
                settingsBut.setEnabled(false);
            }
        });

        printBut = new JButton(new ImageIcon(((new ImageIcon("PrintIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        printBut.setBackground(window.getHighlightColor());

        printBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Print Graph");
            }
        });

        saveBut = new JButton(new ImageIcon(((new ImageIcon("SaveIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        saveBut.setBackground(window.getHighlightColor());

        saveBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Graph");
            }
        });


        histoBut = new JButton(new ImageIcon(((new ImageIcon("HistoIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        histoBut.setBackground(window.getHighlightColor());

        histoBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!histoEnabled){
                    dataPanel.getAddBut().setEnabled(false);
                    dataPanel.getDeleteBut().setEnabled(false);
                    dataPanel.getGraphList().setEnabled(false);
                    metricSelect.setEnabled(false);
                    timeSelect.setEnabled(false);

                    graphPanel.setMetric("Click Cost Frequency");
                    graphPanel.setGraph(new Graph2(graphPanel));
                    graphPanel.removeAll();
                    graphPanel.init();
                    histoEnabled = true;
                }
            }
        });

        lineBut = new JButton(new ImageIcon(((new ImageIcon("LineIcon.png")).getImage()).getScaledInstance(Math.round((window.getButtonBigFont().getSize()/3) * 5), Math.round((window.getButtonBigFont().getSize()/3) * 5), java.awt.Image.SCALE_SMOOTH)));
        lineBut.setBackground(window.getHighlightColor());

        lineBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(histoEnabled){
                    dataPanel.getAddBut().setEnabled(true);
                    dataPanel.getDeleteBut().setEnabled(true);
                    dataPanel.getGraphList().setEnabled(true);
                    metricSelect.setEnabled(true);
                    timeSelect.setEnabled(true);

                    graphPanel.setMetric(metricSelect.getSelectedItem().toString());
                    graphPanel.setGraph(new Graph2(graphPanel));
                    graphPanel.removeAll();
                    graphPanel.init();
                    histoEnabled = false;
                }
            }
        });

        northPanel = new JPanel();
        northPanel.setBackground(window.getBackgoundColor());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        row1n = new JPanel();
        row1n.setBackground(window.getBackgoundColor());

        row1n.setLayout(new BoxLayout(row1n, BoxLayout.LINE_AXIS));
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row1n.add(campBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(histoBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(lineBut);
        row1n.add(Box.createHorizontalGlue());
        row1n.add(printBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(saveBut);
        row1n.add(Box.createRigidArea(new Dimension((int) Math.round(window.getWidthBorderDim().getWidth()/2),0)));
        row1n.add(settingsBut);
        row1n.add(Box.createRigidArea(window.getWidthBorderDim()));


        row2n = new JPanel();
        row2n.setBackground(window.getBackgoundColor());

        row2n.setLayout(new BoxLayout(row2n, BoxLayout.LINE_AXIS));
        row2n.add(Box.createHorizontalGlue());
        row2n.add(title);
        row2n.add(Box.createHorizontalGlue());

        row3n = new JPanel();
        row3n.setBackground(window.getBackgoundColor());
        row3n.setLayout(new BoxLayout(row3n, BoxLayout.LINE_AXIS));

        row3n.add(Box.createHorizontalGlue());
        row3n.add(metricSelect);
        row3n.add(Box.createRigidArea(window.getWidthBorderDim()));
        row3n.add(timeSelect);
        row3n.add(Box.createRigidArea(window.getWidthBorderDim()));

        northPanel.add(Box.createRigidArea(window.getHightBorderDim()));
        northPanel.add(row1n);
        northPanel.add(row2n);
        northPanel.add(row3n);
        northPanel.add(Box.createRigidArea(new Dimension(0,window.getButtonBigFont().getSize())));

        this.add(northPanel, BorderLayout.NORTH);
    }

    public void updateColors(){
        this.setBackground(window.getBackgoundColor());
        title.setForeground(window.getHeadingColour());
        metricSelect.setBackground(window.getUnhighlightColor());
        timeSelect.setBackground(window.getUnhighlightColor());

        filterPanel.updateColors();
        graphPanel.updateColors();
        dataPanel.updateColors();
    }

    public void updateTextSize(){
        title.setFont(window.getHeadingFont());
        metricSelect.setFont(window.getTextFont());
        timeSelect.setFont(window.getTextFont());
        updateNorthPanel();

        filterPanel.updateTextSize();
        dataPanel.updateTextSize();
    }


    public View getWindow(){
        return window;
    }

    public FilterPanel getFilterPanel() {
        return filterPanel;
    }

    public void setFilterPanel(FilterPanel filterPanel) {
        this.filterPanel = filterPanel;
    }

    public DataPanel getDataPanel(){
        return dataPanel;
    }

    public void setDataPanel(DataPanel dataPanel){
        this.dataPanel = dataPanel;
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    public void setGraphPanel(GraphPanel graphPanel){
        this.graphPanel = graphPanel;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public JButton getCampBut() {
        return campBut;
    }

    public void setCampBut(JButton campBut) {
        this.campBut = campBut;
    }

    public JButton getSettingsBut() {
        return settingsBut;
    }

    public void setSettingsBut(JButton settingsBut) {
        this.settingsBut = settingsBut;
    }
}