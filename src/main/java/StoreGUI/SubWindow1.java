package StoreGUI;

import Constractor.Record;
import Main.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class SubWindow1 extends JFrame {
    private JTextField consgrIDTextField;
    private JButton submitButton;
    private JPanel consgrIdPanel;
    private JComboBox<String> comboBoxForExistingConsignrs;
    static Controller cntr;

    static DefaultComboBoxModel<String> consgrNamesListComboBox;


    public SubWindow1(MainWindow parentWindw) {


        setContentPane(consgrIdPanel);
        pack();
        setVisible(true);
        parentWindw.setEnabled(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        consgrNamesListComboBox = new DefaultComboBoxModel<String>();
        comboBoxForExistingConsignrs.setModel(consgrNamesListComboBox);
        Controller.dumpIntoComBox();

       //
        //
        // setAllNames(ArrayList<Record> allData);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = comboBoxForExistingConsignrs.getSelectedItem().toString();

                //SubWindow1 win = SubWindow1();
                //SubWindow1.this.dispose();
                parentWindw.TakeData(name);
                parentWindw.setEnabled(false);
                Subwindow2 ctr = new Subwindow2(parentWindw);
                SubWindow1.this.dispose();
            }
        });
    }


    public static void takeBackData(LinkedList<Record> dumpDate) {
        for(Record r : dumpDate){
           String name = r.getConsgr_name();
           Record record = new Record(name);
          // record = record.wrtingIntoComBoc(name);

            System.out.println(name);
            consgrNamesListComboBox.addElement(String.format(record.printingConsignorName(), record));


        }
    }


}
