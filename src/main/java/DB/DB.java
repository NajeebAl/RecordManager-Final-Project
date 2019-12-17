package DB;

import Constractor.Record;
import StoreGUI.ConsgnmentShelf;
import StoreGUI.SubWindow1;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class DB {
    //creating connection
    private static final String DB_CONNECTION_URL = "jdbc:sqlite:recordstore.sqlite";

    private static Connection conn = null;
    private static final Statement statement = null;
    private ResultSet rs = null;

    public DB() {

        //exception handler
        try {
            conn = DriverManager.getConnection(DB_CONNECTION_URL);
            System.out.println("Your connection is OK");

        } catch (SQLException sqle) {
            System.out.println("Can't connect to database. " +
                    "\nIs MySQL running? " +
                    "\nHave you created the database? " +
                    "\nVerify username and password. " +
                    "\nHave you granted the right permissions to your user?");
            sqle.printStackTrace();

        }

    }
//creating two tables
    //one for records,
    //one for consignors
    public void createtable () {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL)) {
            Statement statement = conn.createStatement();
            //String dropTable = "DROP TABLE records";
            String createTableConsignors = "CREATE TABLE IF NOT EXISTS consignors (Name TEXT, Phone INTEGER, Email TEXT)";
            //statement.execute(dropTable);
            String createTableRecord = "CREATE TABLE IF NOT EXISTS records (ARTIST TEXT, title TEXT, QUANTITY INTEGER, SALEPRICE INTEGER, recordID INTEGER )";
            statement.execute(createTableConsignors);
            statement.execute(createTableRecord);

        } catch (SQLException sql) {
            sql.getErrorCode();
            sql.getCause();
            sql.printStackTrace();
        }
    }



//creating each consigonr with unique ID NUMBER

    public void getConsgrWithID(Record record) {

        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL)){
            Statement statement = conn.createStatement();
            String createTableSql = "CREATE TABLE IF NOT EXIST consignors (Name TEXT, Phone INTEGER)";
            statement.execute(createTableSql);
            int id = record.getConsgrID();
            String search = "Select consgrID from records where consgrID = ? ";
            ResultSet rs = statement.executeQuery(search);
            System.out.println(rs);


        }catch (SQLException sql ){
            sql.getErrorCode();
            sql.getCause();
            sql.printStackTrace();
        }

    }
//adding the consignot information
    public void addingConsgrInfo(Record rcd) {
        try(Connection cnn = DriverManager.getConnection(DB_CONNECTION_URL)){
            String addConsgrDetails = "INSERT INTO consignors (Name, phone, email) VALUES ( ?, ?, ?)";
            PreparedStatement addcongsrDetailsPS = conn.prepareStatement(addConsgrDetails);

            addcongsrDetailsPS.setString(1, rcd.getConsgr_name());
            addcongsrDetailsPS.setString(2, rcd.getConsgr_phoneNumber());
           addcongsrDetailsPS.setString(3, rcd.getConsgr_Email());

            addcongsrDetailsPS.execute();
            System.out.println("Added data into  consignor table ");

            addcongsrDetailsPS.close();
            conn.close();

        }catch (SQLException sql){
            sql.getCause();
            sql.getErrorCode();
            sql.printStackTrace();
        }
    }

    public void getConsgmnetsFromDatabase() {
        try(Connection cnn  = DriverManager.getConnection(DB_CONNECTION_URL) ){
            String fetchAllData = " Select * From records ";
            rs = statement.executeQuery(fetchAllData);


        }catch (SQLException sql){
            sql.getErrorCode();
            sql.printStackTrace();
            sql.getCause();
        }
    }

    public void saveRecordInfo(Record record) {


    }

    public ArrayList<Record> fetchAllConsgrNmeRecords() {
        ArrayList<Record> allCONSGRNAMES = new ArrayList();
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()){
            String selctAllconsgrNames = "SELECT  consgrName FROM consignors ";
            ResultSet rs = statement.executeQuery(selctAllconsgrNames);

            while (rs.next()){
                String name = rs.getString("consignors");
                Record r = new Record(name);
                allCONSGRNAMES.add(r);

            }
            System.out.println("We sent all consignor names ");
            rs.close();
            statement.close();
            conn.close();

            return allCONSGRNAMES;

        }catch (SQLException sql){
                 sql.getErrorCode();
                 sql.getCause();
                 return null;
        }
    }
//getting consigonrs from data
    public void getConsgrsFromData() {
        try(Connection cnn = DriverManager.getConnection(DB_CONNECTION_URL)){
            Statement statement = cnn.createStatement();
            String consgrName = "SELECT consgrName FROM consignors ";

            ResultSet rs = statement.executeQuery(consgrName);
            while (rs.next()){
                String name = rs.getString("consgrName");
                Record r = new Record(name);
                LinkedList<Record> dumpDate = new LinkedList<Record>();
                dumpDate.add(r);
                SubWindow1.takeBackData(dumpDate);
                //System.out.println(name);
            }
            rs.close();
            statement.close();

        }catch (SQLException sql){
            sql.getErrorCode();
            sql.getErrorCode();
            sql.getCause();

        }
    }


    public void saveRecordInfoIntoRecordsTable(Record record) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL)){
           // ARTIST TEXT, title TEXT, QUANTITY INTEGER, SALEPRICE INTEGER

            String addConsgnmtInfo = "INSERT INTO  records ( ARTIST , title , QUANTITY , SALEPRICE, recordID  ) VALUES (  ?,?, ?, ?, ?) ";
            PreparedStatement addConsgrDetails = conn.prepareStatement(addConsgnmtInfo);



            addConsgrDetails.setInt(1, record.getQuantity());
            addConsgrDetails.setDouble(2, record.getSalePrx());
            addConsgrDetails.setString(3, record.getArtist());
            addConsgrDetails.setString(4, record.getTitle() );
            //addConsgrDetails.setDate(5, record.getSmDate());

            addConsgrDetails.execute();
            System.out.println("Added data into records table ");
            addConsgrDetails.close();
            conn.close();
        }catch (SQLException sql){
            sql.getCause();
            sql.getErrorCode();
            sql.printStackTrace();
        }
    }
//getting data from the Database
    public void getDataFromDB( ) {

        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL)){
            Statement statement = conn.createStatement();
            String consmntRecord = "SELECT  recordID,  artist, title, quantity, salePrice FROM records  " ;
            ResultSet rsAll = statement.executeQuery(consmntRecord);
            while (rsAll.next()){
                int recordID = rsAll.getInt("recordID");
//                //int consgrID = rsAll.getInt("consgrID");
                 String artistNAme = rsAll.getString("artist");
                String title = rsAll.getString("title");
                int quantity = rsAll.getInt("quantity");
                double salePrice = rsAll.getDouble("salePrice");
                //Date date = rsAll.getDate("receivedDate");
                Record record = new Record(recordID, artistNAme, title, quantity, salePrice );
                LinkedList<Record> receivedData = new LinkedList<Record>();
                receivedData.add(record);
                ConsgnmentShelf.dumpData(receivedData, record);
                //System.out.println(record.toString());


            }
            //System.out.println("We sent ");
            rsAll.close();
            statement.close();
            conn.close();


        }catch (SQLException sql){
            sql.getCause();
            sql.getErrorCode();
            sql.printStackTrace();
        }
    }


    public void romveDataFromTableToanother(Record r) {

            int recorID = r.getRecordID();
            int quantity = r.getQuantity();
            double salePrx = r.getSalePrx();

            java.sql.Date date = r.getSmDate();
            Record record = new Record(recorID, quantity, salePrx, date);
            System.out.println(record);
        try(Connection cnn = DriverManager.getConnection(DB_CONNECTION_URL)) {

            String insertIntoSalesTable = "INSERT INTO sales ( recordID, quantity, salePrx, soldDate) VALUES  (?,?,?,?)";
            PreparedStatement insertIntoSales = cnn.prepareStatement(insertIntoSalesTable);

            insertIntoSales.setInt(1, recorID);

            insertIntoSales.setInt(2, quantity);
            insertIntoSales.setDouble(3, salePrx);
            insertIntoSales.setDate(4, date);
            insertIntoSales.execute();
            System.out.println("Sales tables is updated");

            String deleting = "Delete From records where recordID = ?";
            PreparedStatement st = cnn.prepareStatement(deleting);
            st.setInt(1,recorID);
            System.out.println("Sold item  is deleted from records !!! ");

            st.execute();

        }catch (SQLException sql){
            sql.getErrorCode();
            sql.getCause();
            sql.printStackTrace();
        }
    }



}
