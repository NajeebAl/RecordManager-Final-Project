package StoreGUI;

import Constractor.Record;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;


public class Subwindow2 extends JFrame{
    private JTextField artitstextField2;
    private JTextField titletextField3;
    private JTextField salesPrxtextField;
    private JPanel recordPanel;
    private JButton addConsignmentButton;
    private JTextField quantitytextField1;
    static LinkedList<Record> dataQue = new LinkedList<> ();

    public Subwindow2(MainWindow parentWindow ){
        setContentPane(recordPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        parentWindow.setEnabled(false);

        addConsignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = String.valueOf (MainWindow.recordVector.toString ());
                ///The system was printing name by adding brackets IE [name].
                name = name.substring (1, name.length ()-1);
                if(name.trim ().length () == 0){
                    name = String.valueOf (SubWindow1.consgrNamesListComboBox.getSelectedItem ());

                }

               // System.out.println (name);
                //I am getting name from two Windows Existing consignor , and new consignor.

                String artist = artitstextField2.getText();
                String title = titletextField3.getText();
                if(artist.trim().length()== 0||title.trim().length()  == 0){
                    JOptionPane.showMessageDialog(Subwindow2.this, "Please enter fill both artist name and song's title ");
                    return;
                }
                int quantity ;
                try{
                    quantity = Integer.parseInt(quantitytextField1.getText());
                }catch (NumberFormatException nf){
                    JOptionPane.showMessageDialog(Subwindow2.this, "Please quantities here ");
                    return;
                }
                double salePrx;
                try{
                    salePrx = Double.parseDouble(salesPrxtextField.getText());

                    if(salePrx < 0){
                        JOptionPane.showMessageDialog(Subwindow2.this, "Sale price cannt be less than Zero");
                        return;

                    }
                }catch (NumberFormatException nf){
                    JOptionPane.showMessageDialog(Subwindow2.this, "Sale price cannt be less than Zero");
                    return;
                }
                Date date = new Date();

                java.sql.Date smDAte = new java.sql.Date(date.getTime());
                Record r = new Record(quantity, artist, title, salePrx, smDAte);

                int id = r.getConsgrID ();
                Record record = new Record (id, name, quantity, salePrx, artist, title, smDAte);
                //record = new Record (id, consgnrName, quantity, salePrx, artist, title, smDAte);

                parentWindow.sendData(id, name, quantity, salePrx, artist, title, smDAte);
                //parentWindow.sendData(id, consgnrName, quantity, salePrx, artist, title, smDAte);
                parentWindow.setEnabled(false);
                Subwindow2.this.dispose();
            }
        });
    }


    public static void sendDATA(Record r) {
        String name = r.getConsgr_name();
        Record record = new Record (name);
        dataQue.add (record);
        System.out.println(name);


    }


}
