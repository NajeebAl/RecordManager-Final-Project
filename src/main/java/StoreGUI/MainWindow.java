package StoreGUI;

import Constractor.Record;
import Main.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;


public class MainWindow  extends JFrame{
    private JTextField consgrPhnetextField;
    private JTextField congrNametextField2;
    private JRadioButton newConsignorRadioButton;
    private JRadioButton existingConsignorRadioButton;
    private JTextField emailtextField3;
    private JButton continueNewButton;
    private JPanel rootPanel;
    private JButton continueWithAnExistingButton;
    private JButton showConsignmentButton;
    private JButton makePaymentButton;
    private Subwindow2  consgNameComboBox1 ;

    DefaultListModel <Record> csgmntCombListModel;
    static  Vector<Record> recordVector = new Vector<> ();


    static Controller ctr;
    public MainWindow(){
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(500, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);



        newConsignorRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(newConsignorRadioButton.isSelected()){
                    existingConsignorRadioButton.setEnabled(false);
                    continueWithAnExistingButton.setEnabled(false);
                }
                else {
                    existingConsignorRadioButton.setEnabled(true);
                    continueWithAnExistingButton.setEnabled(true);
                }

            }
        });
        existingConsignorRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(existingConsignorRadioButton.isSelected()){
                    newConsignorRadioButton.setEnabled(false);
                   continueNewButton.setEnabled(false);
                   congrNametextField2.setEnabled(false);
                   consgrPhnetextField.setEnabled(false);
                   //emailtextField3.setEnabled(false);

                }

                else {
                    newConsignorRadioButton.setEnabled(true);
                    continueNewButton.setEnabled(true);
                    congrNametextField2.setEnabled(true);
                    consgrPhnetextField.setEnabled(true);
                    //emailtextField3.setEnabled(true);
                }
            }

        });
        continueWithAnExistingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SubWindow1 getUserInput = new SubWindow1(MainWindow.this);
                ArrayList<Record> allData = Controller.getDateFromDatabase();

                //Subwindow2  consgNameComboBox1 = new
           //     SubWindow1.setAllNames(allData);



            }
        });

        continueNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newConsignorRadioButton.isSelected()){
                    String name = congrNametextField2.getText();
                    Record r = new Record (name);

                    recordVector.addElement (r);
                    //consgrNAme(recordVector);
                    if(name.trim().length() == 0){
                        JOptionPane.showMessageDialog(MainWindow.this, "Please enter consignor name ");
                        return;
                    }
                    String phone = consgrPhnetextField.getText();
                    if(phone.trim().length()== 0){
                        JOptionPane.showMessageDialog(MainWindow.this, "Please enter consignor phone number !!!");
                        return;
                    }
                    String email = emailtextField3.getText();
                    if(email.trim().length() == 0){
                        JOptionPane.showMessageDialog(MainWindow.this, "Please enter consignor's E-mail !!!!");
                        return;
                    }
                    Record rcd = new Record(name, phone, email);
                    Controller.addConsignrDetails(rcd);


                    Subwindow2 record = new Subwindow2(MainWindow.this);
                }



            }
        });





        showConsignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsgnmentShelf csmShef = new ConsgnmentShelf(MainWindow.this);
            }
        });
        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // String name = SubWindow1.consgrNamesListComboBox.getSelectedItem().toString();

              // SubWindow1 subWindow1 = new SubWindow1(MainWindow.this);
              // String name = SubWindow1.consgrNamesListComboBox.getSelectedItem().toString();
               // System.out.println(name);
            }
        });
    }




    public void TakeData(String name) {
        Record r ;

        r = new Record(name);
        Record record = new Record (name);
        System.out.println(r);
       Subwindow2.sendDATA(r);


    }

    public void sendData(int id, String name, int quantity, double salePrx, String artist, String title, Date smDAte) {

        //I am making a new record by adding consID;
        Record record = new Record(id,name, quantity, salePrx, artist, title, smDAte);

        Controller.saveRecordInfo(record);
    }
}
