package de.dis2011.data;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by nxirakia on 23.04.17.
 */
public class Contract {
    //owner info
    private int ownerid = -1;
    private String firstname;
    private String lastname;
    private String address;

    //contract info
    private int contractid= -1;
    private String contractdate;
    private String settlemtnplace;
    private String contractType;
    private int installments;
    private double interestrate;
    private String startDate;
    private int duration;
    private double extracosts;
    private int houseid;
    private int apartmentid;


    //get/set owner info
    public int getOwnerID() {
        return ownerid;
    }

    public void setOwnerID(int ownerid) {
        this.ownerid = ownerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //get/set contract info

    public int getContractID() {
        return contractid;
    }

    public void setContractID(int contractid) {
        this.contractid = contractid;
    }

    public String getContractdate(){return contractdate; }

    public void setContractdate(String date){

        this.contractdate = date;

    }

    public String getSettlemtnPlace(){ return settlemtnplace;}

    public void setSettlemtnPlace(String settlemtnplace) {this.settlemtnplace=settlemtnplace;}

    public String getContractType(){return contractType;}

    public void setContractType(String contractType){this.contractType=contractType;}

    public int getInstallments(){return installments;}

    public void setInstallments(int installments){this.installments= installments;}

    public double getInterestrate(){return interestrate;}

    public void setInterestrate(double interestrate){this.interestrate=interestrate;}

    public String getStartDate(){return startDate;}

    public void setStartDate(String startDate){this.startDate=startDate;}

    public int getDuration(){return duration;}

    public void setDuration(int duration){this.duration= duration;}

    public double getExtracosts(){return extracosts;}

    public void setExtracosts(double extracosts){this.extracosts=extracosts;}

    public int getHouseid(){return houseid;}

    public void setHouseid(int houseid){this.houseid= houseid;}

    public int getApartmentid(){return apartmentid;}

    public void setApartmentid(int apartmentid){this.apartmentid= apartmentid;}


    public void createOwner(){
        Connection con = DB2ConnectionManager.getInstance().getConnection();

        try {

            // Fetch new element if the object does not yet have an ID.
            if (getOwnerID() == -1) {
                // Here we pass another parameter
                // so that later generated IDs can be delivered!
                String insertSQL = "INSERT INTO owner(ownername, ownersurname, address) VALUES (?, ?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Set request parameters and fetch your request
                pstmt.setString(1, getFirstname());
                pstmt.setString(2, getLastName());
                pstmt.setString(3, getAddress());
                pstmt.executeUpdate();

                // Get the Id of the record
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    setOwnerID(rs.getInt(1));
                }

                rs.close();
                pstmt.close();
            } else {
                // If an ID already exists, make an update
                String updateSQL = "UPDATE owner SET ownername = ?, ownersurname=?, address = ? WHERE ownerid = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Set request parameters
                pstmt.setString(1, getFirstname());
                pstmt.setString(2, getLastName());
                pstmt.setString(3, getAddress());
                pstmt.setInt(4, getOwnerID());
                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createContract(){
        Connection con = DB2ConnectionManager.getInstance().getConnection();

        try {

            // Fetch new element if the object does not yet have an ID.
            if (getContractID() == -1) {
                // Here we pass another parameter
                // so that later generated IDs can be delivered!
                String insertSQL = "INSERT INTO contract(contractdate, settlementplace) VALUES (?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Set request parameters and fetch your request

                pstmt.setString(1, getContractdate());
                pstmt.setString(2, getSettlemtnPlace());
                pstmt.executeUpdate();

                // Get the Id of the record
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    setContractID(rs.getInt(1));
                }

                rs.close();
                pstmt.close();
            } else {
                // If an ID already exists, make an update
                String updateSQL = "UPDATE contract SET contractdate = ?, settlementplace=? WHERE id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Set request parameters
                pstmt.setString(1, getContractdate());
                pstmt.setString(2, getSettlemtnPlace());
                pstmt.setInt(3, getContractID());
                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTenancy(){
        Connection con = DB2ConnectionManager.getInstance().getConnection();

        try {

            // Fetch new element if the object does not yet have an ID.
            if (getContractID() == -1) {
                // Here we pass another parameter
                // so that later generated IDs can be delivered!
                String insertSQL = "INSERT INTO tenancycontract(strartdate, duration, extracharges) VALUES (?, ?,?); INSERT INTO apartmentrent(ownerid,apartmentid) VALUES (?,?); ";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Set request parameters and fetch your request
                pstmt.setString(1, getStartDate());
                pstmt.setInt(2,getDuration());
                pstmt.setDouble(3,getExtracosts());
                pstmt.setInt(4,getOwnerID());
                pstmt.setInt(5,getApartmentid());
                pstmt.executeUpdate();

                // Get the Id of the record
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    setContractID(rs.getInt(1));
                }

                rs.close();
                pstmt.close();
            } else {
                // If an ID already exists, make an update
                String updateSQL = "UPDATE tenancycontract SET strartdate = ?, duration=? , extracharges=? WHERE id = ?; UPDATE apartmentrent SET ownerid=?, apartmentid=? WHERE contractid=?;";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Set request parameters
                pstmt.setString(1, getStartDate());
                pstmt.setInt(2,getDuration());
                pstmt.setDouble(3,getExtracosts());
                pstmt.setInt(4,getContractID());
                pstmt.setInt(5,getOwnerID());
                pstmt.setInt(6,getApartmentid());
                pstmt.setInt(7,getContractID());
                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createPurchase(){
        Connection con = DB2ConnectionManager.getInstance().getConnection();

        try {

            // Fetch new element if the object does not yet have an ID.
            if (getContractID() == -1) {
                // Here we pass another parameter
                // so that later generated IDs can be delivered!
                String insertSQL = "INSERT INTO purchasecontract(numberofinstallments, interestrate) VALUES (?, ?); INSERT INTO SALES(owner_id, house_id) VALUES (?,?,?);";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Set request parameters and fetch your request
                pstmt.setInt(1, getInstallments());
                pstmt.setDouble(2,getInterestrate());
                pstmt.setInt(3,getOwnerID());
                pstmt.setInt(4,getHouseid());
                pstmt.executeUpdate();

                // Get the Id of the record
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    setContractID(rs.getInt(1));
                }

                rs.close();
                pstmt.close();
            } else {
                // If an ID already exists, make an update
                String updateSQL = "UPDATE purchasecontract SET numberofinstallments = ?, interestrate=? WHERE contract_id = ?; UPDATE sales SET owner_id=?, house_id=? WHERE contract_id=?;";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Set request parameters
                pstmt.setInt(1, getInstallments());
                pstmt.setDouble(2,getInterestrate());
                pstmt.setInt(3,getContractID());
                pstmt.setInt(4,getOwnerID());
                pstmt.setInt(5,getHouseid());
                pstmt.setInt(6,getContractID());
                pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

