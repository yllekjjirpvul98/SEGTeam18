package View;

import Model.Calculation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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


    public DataPanel(DashboardPanel dashboardPanel){
        this.dashboardPanel = dashboardPanel;
        this.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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

    private void init(){

        // ----  Creating Components  ----
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        this.calcValues();

        colNames = new String[] {"Metric", "Value"};
        tableData = new String[][] {
                {"Number of Impressions", String.valueOf(df.format(numImpressions))},
                {"Number of Clicks", String.valueOf(df.format(numClicks))},
                {"Number of Uniques", String.valueOf(df.format(numUniques))},
                {"Number of Conversions", String.valueOf(df.format(numConvos))},
                {"Number of Bounces", String.valueOf(df.format(numBounces))},
                {"Bounce Rate", String.valueOf(df.format(bounceRate))},
                {"Total Cost", String.valueOf(df.format(totalCost))},
                {"Cost per Acquisition", String.valueOf(df.format(CPA))},
                {"Cost per Click", String.valueOf(df.format(CPC))},
                {"Cost per 1000 Impressions", String.valueOf(df.format(CPM))},
                {"Click Through Rate", String.valueOf(df.format(CTR))}
        };

        DefaultTableModel tableModel = new DefaultTableModel(tableData, colNames);
        table = new JTable(tableModel);
        table.setFont(dashboardPanel.getWindow().getTextFont());
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 15);
        table.getColumnModel().getColumn(1).setPreferredWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 7);
        table.setRowHeight(dashboardPanel.getWindow().getButtonSmallFont().getSize() + 10);
        table.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        table.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));


        ListModel listModel = new DefaultListModel();

        JList graphList = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(graphList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        graphList.setFont(dashboardPanel.getWindow().getTextFont());
        graphList.setFixedCellWidth(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 22);
        graphList.setPreferredSize(new Dimension(dashboardPanel.getWindow().getButtonSmallFont().getSize() * 22, dashboardPanel.getWindow().getButtonSmallFont().getSize() * 10));
        graphList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        graphList.setLayoutOrientation(JList.VERTICAL);
        graphList.setVisibleRowCount(-1);
        graphList.setBackground(dashboardPanel.getWindow().getBackgoundColor());
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,true));


        JButton addBut = new JButton("ADD");
        addBut.setFont(dashboardPanel.getWindow().getButtonBigFont());;
        addBut.setBackground(new Color(0x9CFFAD));

        JButton deleteBut = new JButton("DEL");
        deleteBut.setFont(dashboardPanel.getWindow().getButtonBigFont());
        deleteBut.setBackground(new Color(0xFF8976));

        // ----  Creating Layout  ----
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1,BoxLayout.LINE_AXIS));
        row1.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(table);
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row1.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));

        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2,BoxLayout.LINE_AXIS));
        row2.setBackground(dashboardPanel.getWindow().getBackgoundColor());

        row2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row2.add(scrollPane);
        row2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));
        row2.add(Box.createRigidArea(dashboardPanel.getWindow().getWidthBorderDim()));


        JPanel row3 = new JPanel();
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

}