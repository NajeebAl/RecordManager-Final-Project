package Main;

import Constractor.Record;
import DB.DB;
import StoreGUI.MainWindow;

import java.util.ArrayList;
//THIS setsup main window data base and create



public class Controller {
     static DB db ;
     static Record record;
    public static void main(String[] args) {
        MainWindow m = new MainWindow();
        db = new DB();
        db.createtable();

    }
//this the arraylist that creates record and gets data from database
    public static ArrayList<Record> getDateFromDatabase() {


        return db.fetchAllConsgrNmeRecords();
    }
    public static void loadDataFromDB( ) {
        db.getDataFromDB();
    }



    public static void getIDfromDatabase(Record id) {
        db.getConsgrWithID(record);
    }

    public static void addConsignrDetails(Record rcd) {
        db.addingConsgrInfo(rcd);
    }

    public static void getAllDataFromDB() {
        db.getConsgmnetsFromDatabase();
    }

    public static void saveConsgrInfo(Record record) {
        db.saveRecordInfo( record);
    }


    public static void dumpIntoComBox() {
        db.getConsgrsFromData();
    }



    public static void saveRecordInfo(Record record) {
        db.saveRecordInfoIntoRecordsTable(record);
    }




    public static void removeDataFromrecords(Record r) {
        db.romveDataFromTableToanother(r);
    }

   



}
