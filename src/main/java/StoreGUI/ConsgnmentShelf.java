package StoreGUI;

import Constractor.Record;
import Main.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class ConsgnmentShelf  extends  JFrame{
    private JPanel consgnmentPanel;
    private JList<Record> consmentList;
    private JList soldlist;
    private JList burgainedlist;
    private JButton saleItemButton;
    private JButton button2;
    private JButton inventoryDateButton;

    static DefaultListModel<Record> allConsgmnetListModel;
    static DefaultListModel<Record>allSoldListModel;

    ConsgnmentShelf(MainWindow parentWindow){
        allConsgmnetListModel = new DefaultListModel<>();
        consmentList.setModel(allConsgmnetListModel);
        allSoldListModel = new DefaultListModel<>();
        soldlist.setModel(allSoldListModel);
        setContentPane(consgnmentPanel);
        pack();
        setVisible(true);
        parentWindow.setEnabled(true);
        Controller.loadDataFromDB();
//        Controller.loadSoldTables();


        saleItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(consmentList.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(ConsgnmentShelf.this, "Please select an item to sale !!");
                }
                else{
                    Record r = consmentList.getSelectedValue();

                    allSoldListModel.addElement(r);
                    allConsgmnetListModel.removeElement(r);
                    Controller.removeDataFromrecords(r);

                }

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        inventoryDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(consmentList.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(ConsgnmentShelf.this, " Please select an item to know old inventry");
                    return;
                }


            }
        });
    }
// recordID | artist    | title   | quantity | salePrx | Date
    public static void dumpData(LinkedList<Record> recordData, Record record) {
       // allConsgmnetListModel = new DefaultListModel<String>();
        int recordID = record.getRecordID();
        String artist = record.getArtist();
        String title = record.getTitle();
        int quantity = record.getQuantity();
        double salePrx = record.getSalePrx();
        java.sql.Date date = record.getSmDate();
        Record newRecord = new Record(recordID, artist, title, quantity, salePrx);
        allConsgmnetListModel.addElement(newRecord);
        //System.out.println(newRecord);
            //System.out.println(recodID);




    }
    /*

    public static void soldData(LinkedList<Record> soldItems, Record record) {
       // System.out.println(record);
        int saleID = record.getSaleID();
        int recordID = record.getRecordID();
        int quantity = record.getQuantity();
        double salePrx = record.getSalePrx();
        java.sql.Date date = record.getSmDate();
        Record saleRecord  = new Record(saleID, recordID,  quantity, salePrx, date);
        //saleRecord = saleRecord.printOnSalesdatabase();
        //System.out.println(r.salesPrintingInJlist());
        allSoldListModel.addElement(saleRecord );
    }
    */
}
