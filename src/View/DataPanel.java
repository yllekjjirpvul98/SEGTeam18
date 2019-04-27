package View;

import Model.Calculation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/*
    Class displays the data table for current campaign with its applied filters and the list of saved graphs which can
    be added/removed to as well as displaying selected graphs filters when holding mouse button in the list.
 */

public class DataPanel extends JPanel {

    private DashboardPanel dashboardPanel;

    private double numImpressions;
    private double numClicks;
    private double numUniques;
    private double numConvos;
    private double numBounces;
    private double bounceRate;
    private double totalCost;
    private double CPA;
    private double CPC;
    private double CPM;
    private double CTR;

    private String[][] tableData;
    private String[] colNames;
    private JTable table;
    private JList graphList;
    private String campName;
    private JButton addBut;
    private JButton deleteBut;
    private ListModel listModel;
    private JLabel graphListTitle;

    private JPanel row1;
    private JPanel row2;
    private JPanel row3;
    private JPanel row4;

    public DataPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        campName = dashboardPanel.getCampName();
        this.init();
    }

    // Sets the local variables to the values in the models Calculation class.
    private void calcValues(){
        Calculation cal = dashboardPanel.getWindow().getControl().getModel().getCal();

        numImpressions = cal.calImpression();
        numClicks = cal.calClicks();
        numUniques = cal.calUnique();
        numConvos = cal.calConversion();
        numBounces = cal.calBounce();
        bounceRate = numBounces/numClicks;
        totalCost = cal.calTotal();
        CPA = totalCost/numConvos;
        CPC = cal.calClickCost()/numClicks;
        CPM = totalCost/(1000*numImpressions);
        CTR = numClicks/numImpressions;
    }

    // Updates the data in the table to represent current campaign with current filters applied.
    public void updateData(){
        this.calcValues();

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setValueAt(String.valueOf(df.format(numImpressions)), 0,1);
        tableModel.setValueAt(String.valueOf(df.format(numClicks)),1,1);
        tableModel.setValueAt(String.valueOf(df.format(numUniques)),2,1);
        tableModel.setValueAt(String.valueOf(df.format(numConvos)),3,1);
        tableModel.setValueAt(String.valueOf(df.format(numBounces)),4,1);
        tableModel.setValueAt(String.valueOf(df.format(bounceRate)),5,1);
        tableModel.setValueAt(String.valueOf(df.format(totalCost)),6,1);
        tableModel.setValueAt(String.valueOf(df.format(CPA)),7,1);
        tableModel.setValueAt(String.valueOf(df.format(CPC)),8,1);
        tableModel.setValueAt(String.valueOf(df.format(CPM)),9,1);
        tableModel.setValueAt(String.valueOf(df.format(CTR)),10,1);
    }

    public void init(){
        // ----  Creating Components  ----
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        this.calcValues();

        colNames = new String[] {"Metric", "Value"};
        tableData = new String[][] {
                {" Number of Impressions", String.valueOf(df.format(numImpressions))},
                {" Number of Clicks", String.valueOf(df.format(numClicks))},
                {" Number of Uniques", String.valueOf(df.format(numUniques))},
                {" Number of Conversions", String.valueOf(df.format(numConvos))},
                {" Number of Bounces", String.valueOf(df.format(numBounces))},
                {" Bounce Rate", String.valueOf(df.format(bounceRate))},
                {" Total Cost", String.valueOf(df.format(totalCost))},
                {" Cost / Acquisition", String.valueOf(df.format(CPA))},
                {" Cost / Click", String.valueOf(df.format(CPC))},
                {" Cost / 1000 Impressions", String.valueOf(df.format(CPM))},
                {" Click Through Rate", String.valueOf(df.format(CTR))}
        };

        DefaultTableModel tableModel = new DefaultTableModel(tableData, colNames);
        table = new JTable(tableModel);
        table.setFont(dashboardPanel.getWindow().getTextFont());
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 15);
        table.getColumnModel().getColumn(1).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 7);
        table.setRowHeight(dashboardPanel.getWindow().getButtonSmallFont().getSize() + 10);
        table.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));

        graphListTitle = new JLabel("Saved Graphs");
        graphListTitle.setFont(dashboardPanel.getWindow().getTextFontBold());
        graphListTitle.setForeground(dashboardPanel.getWindow().getHeadingColour());

        listModel = new DefaultListModel();

        graphList = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(graphList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
        graphList.setFont(dashboardPanel.getWindow().getTextFont());
        graphList.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        graphList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        graphList.setLayoutOrientation(JList.VERTICAL);
        graphList.setVisibleRowCount(-1);

        // Mouse listener on the graph list displays selected graphs filters when mouse is pressed, removes if released.
        graphList.addMouseListener(new MouseListener() {
            GraphFilterFrame filterFrame;

            @Override
            public void mousePressed(MouseEvent e) {
                filterFrame = new GraphFilterFrame(dashboardPanel.getWindow(), graphList.getSelectedIndex());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                filterFrame.close();
            }

            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });


        addBut = new JButton("ADD");
        addBut.setFont(dashboardPanel.getWindow().getButtonBigFont());;
        addBut.setBackground(dashboardPanel.getWindow().getAddColor());

        addBut.addActionListener(e -> {
            dashboardPanel.getGraphPanel().getGraph().addGraph();
            dashboardPanel.getGraphPanel().getGraph().updateSeries();

            ((DefaultListModel) listModel).addElement(" Graph " + (listModel.getSize()+1) + " ("+ dashboardPanel.getGraphPanel().getMetric() + ") - " + campName);
        });

        deleteBut = new JButton("DEL");
        deleteBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        deleteBut.setBackground(dashboardPanel.getWindow().getDelColor());

        deleteBut.addActionListener(e -> {
            int index = graphList.getSelectedIndex();

            if(index >= 0){
                ((DefaultListModel) listModel).remove(index);
                dashboardPanel.getGraphPanel().getGraph().deleteGraph(index);
            }
        });

        // ---- Layout  ----
        row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1,BoxLayout.LINE_AXIS));
        row1.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(table);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        row4 = new JPanel();
        row4.setLayout(new BoxLayout(row4,BoxLayout.LINE_AXIS));
        row4.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        row4.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row4.add(graphListTitle);
        row4.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row4.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2,BoxLayout.LINE_AXIS));
        row2.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        row2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row2.add(scrollPane);
        row2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3,BoxLayout.LINE_AXIS));
        row3.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        row3.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row3.add(Box.createHorizontalGlue());
        row3.add(addBut);
        row3.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row3.add(deleteBut);
        row3.add(Box.createHorizontalGlue());
        row3.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row3.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        this.add(row1);
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));
        this.add(row4);
        this.add(row2);
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));
        this.add(row3);
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));
    }

    // Update GUI colours.
    public void updateColors(){
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        table.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        graphList.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        addBut.setBackground(dashboardPanel.getWindow().getAddColor());
        deleteBut.setBackground(dashboardPanel.getWindow().getDelColor());
        graphListTitle.setForeground(dashboardPanel.getWindow().getHeadingColour());
        row1.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        row2.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        row3.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        row4.setBackground(dashboardPanel.getWindow().getBackgoundColor());
    }

    // Update GUI sizing.
    public void updateTextSize(){
        table.getColumnModel().getColumn(0).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 15);
        table.getColumnModel().getColumn(1).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 7);
        table.setRowHeight(dashboardPanel.getWindow().getButtonSmallFont().getSize() + 10);

        table.setFont(dashboardPanel.getWindow().getTextFont());
        graphList.setFont(dashboardPanel.getWindow().getTextFont());
        addBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        deleteBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        graphListTitle.setFont(dashboardPanel.getWindow().getTextFontBold());
    }

    // Getters and Setters.
    public void setCampName(String campName) {
        this.campName = campName;
    }
    public JList getGraphList() {
        return graphList;
    }

    public JButton getAddBut() {
        return addBut;
    }

    public JButton getDeleteBut() {
        return deleteBut;
    }

    public ListModel getListModel() {
        return listModel;
    }
}