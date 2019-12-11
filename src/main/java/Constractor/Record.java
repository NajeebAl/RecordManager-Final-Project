package Constractor;

import java.util.Date;



public class Record {
    int  consgrID;
    String consgr_name ;
    String consgr_phoneNumber;
    String consgr_Email;
    String artist;
    String title;
    int saleID;
    double salePrx;
    int quantity;

    public java.sql.Date getSoldDate( ) {
        return soldDate;
    }

    java.sql.Date soldDate;

    public int getRecordID( ) {
        return recordID;
    }

    int recordID;

    static  int consgrIDIncremet = 1;

    public int getConsgrID() {
        return consgrID;
    }

    public String getConsgr_name() {
        return consgr_name;
    }

    public int getSaleID( ) {
        return saleID;
    }

    public String getConsgr_phoneNumber() {
        return consgr_phoneNumber;
    }

    public String getConsgr_Email() {
        return consgr_Email;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public double getSalePrx() {
        return salePrx;
    }

    public int getQuantity() {
        return quantity;
    }




    Date date = new Date();

    public java.sql.Date getSmDate() {
        return smDate;
    }

    java.sql.Date smDate= new java.sql.Date(date.getTime());

    //Uploading consignment into ConsgnmentShelf class
   // recordID | artist    | title   | quantity | salePrx | Date
    public Record(int recordID, String artist, String title, int quantity, double salePrx){
        this.quantity = quantity;
        this.artist = artist;
        this.title = title;
        this.salePrx = salePrx;
        //this.smDate = smDate;
        this.recordID = recordID;
        //this.consgrID = consgrID;
    }



    public Record(int consrID, String name,  int quantity, double salePrx, String artist, String title,  java.sql.Date smDate) {
        this.quantity = quantity;
        this.artist = artist;
        this.title = title;
        this.salePrx = salePrx;
        this.smDate = smDate;
        this.consgr_name = name;
        this.consgrID = consgrIDIncremet;
        consgrIDIncremet ++;

    }
    //recordID, title, quantity, salePrx, date
    public Record(int quantity, double salePrx, String artist, String title,  java.sql.Date smDate){
        this.quantity = quantity;
        this.artist = artist;
        this.title = title;
        this.salePrx = salePrx;
        this.smDate = smDate;
    }


    public Record(int consgrID, String artist, String title, double salePrx, java.sql.Date smDAte){
        this.consgrID = consgrID;
        this.artist = artist;
        this.title = title;
        this.salePrx = salePrx;
        this.smDate = smDAte;

    }
    // Sales JList loading data
    //saleID | recordID | quantity | salePrx | soldDate

    public Record(int saleID, int recordID ,int quantity,  double salePrx, java.sql.Date smDAte){
    this.saleID = saleID;
    this.recordID = recordID;
    this.quantity = quantity;
    this.salePrx = salePrx;
    this.smDate = smDAte;


    }




    //Consignor's database constractor
    @Override
    public String toString( ) {
        return
            " Record ID= " + recordID +
            ", Artist= " + artist + '\'' +
            ", Song title= " + title + '\'' +
            ", Quantity= " + quantity +
            ", Sale price = " + salePrx +
            ", Date  " + smDate;
    }
    public String printingConsignorName(){
        return consgr_name;


    }
    public  String printOndatabase(){
        return
                recordID  + artist + title + quantity + salePrx + smDate;
    }



    public Record(String name, String phone , String email){
        this.consgr_name = name;
        this.consgr_phoneNumber = phone;
        //this.consgr_Email = email;

    }
    public Record(int recorID, int consgrID, double salePrx, java.sql.Date date){
        this.consgrID = consgrID;
    }
    // A combobox constractor

    public Record(String  consgName){
        this.consgr_name = consgName;
    }






    /*


    public Record(int quantity, String artist, String title, double salePrx, java.sql.Date smDate) {
        this.quantity = quantity;
        this.artist = artist;
        this.title = title;
        this.salePrx = salePrx;
        this.smDate = smDate;

    }
    */

}
