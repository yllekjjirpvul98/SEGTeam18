package View;

import Model.Calculation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;


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

    private JPanel row1;
    private JPanel row2;
    private JPanel row3;


    public DataPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        campName = dashboardPanel.getCampName();
        this.init();
    }

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


        ListModel listModel = new DefaultListModel();

        graphList = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(graphList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        graphList.setFont(dashboardPanel.getWindow().getTextFont());
        graphList.setFixedCellWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 22);
        graphList.setPreferredSize(new Dimension(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 22, dashboardPanel.getWindow().getButtonSmallFont().getSize() * 10));
        graphList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        graphList.setLayoutOrientation(JList.VERTICAL);
        graphList.setVisibleRowCount(-1);
        graphList.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));


        addBut = new JButton("ADD");
        addBut.setFont(dashboardPanel.getWindow().getButtonBigFont());;
        addBut.setBackground(dashboardPanel.getWindow().getAddColor());

        addBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardPanel.getGraphPanel().getGraph().addGraph();
                dashboardPanel.getGraphPanel().getGraph().updateSeries();

                ((DefaultListModel) listModel).addElement(" Graph " + (listModel.getSize()+1) + " ("+ dashboardPanel.getGraphPanel().getMetric() + ") - " + campName);

            }
        });

        deleteBut = new JButton("DEL");
        deleteBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        deleteBut.setBackground(dashboardPanel.getWindow().getDelColor());

        deleteBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = graphList.getSelectedIndex();

                if(index >= 0){
                    ((DefaultListModel) listModel).remove(index);
                    dashboardPanel.getGraphPanel().getGraph().deleteGraph(index);
                    dashboardPanel.getGraphPanel().getGraph().updateSeries();
                }

            }
        });

        // ----  Creating Layout  ----
        row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1,BoxLayout.LINE_AXIS));
        row1.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(table);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

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
        this.add(row2);
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));
        this.add(row3);
        this.add(Box.createRigidArea(dashboardPanel.getWindow().getHightBorderDim()));
    }

    public void updateColors(){
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        table.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        graphList.setBackground(dashboardPanel.getWindow().getUnhighlightColor());
        addBut.setBackground(dashboardPanel.getWindow().getAddColor());
        deleteBut.setBackground(dashboardPanel.getWindow().getDelColor());
        row1.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        row2.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        row3.setBackground(dashboardPanel.getWindow().getBackgoundColor());
    }

    public void updateTextSize(){
        if(dashboardPanel.getWindow().getControl().getModel().getSettings().getLargeText()) {
            table.getColumnModel().getColumn(0).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 9);
            table.getColumnModel().getColumn(1).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 4);
            table.setRowHeight(dashboardPanel.getWindow().getButtonSmallFont().getSize());
        }
        else{
            table.getColumnModel().getColumn(0).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 15);
            table.getColumnModel().getColumn(1).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 4);
            table.setRowHeight(dashboardPanel.getWindow().getButtonSmallFont().getSize() + 15);
        }
        table.setFont(dashboardPanel.getWindow().getTextFont());
        graphList.setFont(dashboardPanel.getWindow().getTextFont());
        addBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        deleteBut.setFont(dashboardPanel.getWindow().getButtonBigFont());


    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }
    public JList getGraphList() {
        return graphList;
    }

    public void setGraphList(JList graphList) {
        this.graphList = graphList;
    }

    public JButton getAddBut() {
        return addBut;
    }

    public void setAddBut(JButton addBut) {
        this.addBut = addBut;
    }

    public JButton getDeleteBut() {
        return deleteBut;
    }

    public void setDeleteBut(JButton deleteBut) {
        this.deleteBut = deleteBut;
    }

}