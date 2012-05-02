/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.dal;

import com.bc5Neptune.cis.config.ConnectDB2;
import com.bc5Neptune.cis.entity.DistrictEntity;
import com.bc5Neptune.cis.entity.PersonEntity;
import com.bc5Neptune.cis.entity.ProvinceEntity;
import com.bc5Neptune.cis.entity.WardEntity;
import com.bc5Neptune.cis.gui.PPersonInformation;
import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import COM.ibm.db2.app.Blob;

/**
 *
 * @author enclaveit
 */
public class PersonDAL {

    Connection connection = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    ResultSet resultSetRES = null;
    ResultSet resultSetid = null;
    ResultSet resultSetid1 = null;
    ResultSet resultSetname = null;
    Statement statement = null;
    PreparedStatement prstatement = null;
    CallableStatement cstmt = null;
    CallableStatement cstmt1 = null;
    CallableStatement cstmtRes = null;
    CallableStatement cstmt_id = null;
    CallableStatement cstmt_id1 = null;
    CallableStatement cstmt_name = null;

    public PersonDAL() {
    }

    public PersonEntity Select(String pid) {
        PersonEntity p = new PersonEntity();

        connection = ConnectDB2.getConnection();
        //CallableStatement cstmtInsert =null;
        System.out.println("------------------VIEW DATA---------------------");
        try {

            cstmt = connection.prepareCall("Call SPD_SEARCHBYIDCARD(?)");
            cstmtRes = connection.prepareCall("Call SPD_RESIDENCE(?)");

            cstmt.setString(1, pid);
            resultSet = cstmt.executeQuery();
            //statement = connection.createStatement();  
            //resultSet = statement  
            //      .executeQuery("SELECT * FROM NOAH");  
            if (resultSet.next()) {
                System.out.println("Get data");
                //p = new PersonEntity();
                p.setPid(resultSet.getString(1));
                p.setIdentity_number(resultSet.getString(2));
                p.setFullname(resultSet.getString(3));
                p.setDob(resultSet.getDate(4));
                p.setHometown("To " + resultSet.getString("NAMEG") + "- phuong " + resultSet.getString("NAMEW") + "- quan " + resultSet.getString("NAMED") + "- Tp " + resultSet.getString("NAMEP"));
                //p.setPermanent_residence(resultSet.getString(6));
                p.setImage(resultSet.getBlob(7));
                cstmtRes.setString(1, resultSet.getString(6));
                resultSetRES = cstmtRes.executeQuery();
                if (resultSetRES.next()) {
                    p.setPermanent_residence("To " + resultSetRES.getString("NAMEG") + "- phuong " + resultSetRES.getString("NAMEW") + "- quan " + resultSetRES.getString("NAMED") + "- Tp " + resultSetRES.getString("NAMEP"));
                }
                p.setEthnic(resultSet.getString(8));
                p.setReligion(resultSet.getString(9));
                p.setCharacteristic(resultSet.getString(10));
                p.setDate(resultSet.getDate(11));
               // p.setActive(resultSet.getString(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //resultSet.close();
                //cstmt.close();
                //connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public ResultSet searchid(String id) {
        PersonEntity p1 = null;
        p1 = new PersonEntity();
        connection = ConnectDB2.getConnection();
        try {
            cstmt_id = connection.prepareCall("Call SPD_SEARCHBYPID(?)");

            cstmt_id.setString(1, id);
            resultSetid = cstmt_id.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSetid;
    }

    public ResultSet searchname(String name) {
        PersonEntity p2 = null;
        p2 = new PersonEntity();
        connection = ConnectDB2.getConnection();
        try {
            cstmt_name = connection.prepareCall("Call SPD_SEARCHBYNAME(?)");
            cstmt_name.setString(1, name);
            resultSetname = cstmt_name.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSetname;
    }

    public void deleteID(String id) {
        PersonEntity p3 = new PersonEntity();
        connection = ConnectDB2.getConnection();
        try {
            cstmt_id1 = connection.prepareCall("Call SPD_DELETEBYID(?)");
            cstmt_id1.setString(1, id);
            int rowAff = cstmt_id1.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public PersonEntity selectID(String id) {
        PersonEntity p = new PersonEntity();;

        connection = ConnectDB2.getConnection();
        //CallableStatement cstmtInsert =null;
        System.out.println("------------------VIEW DATA---------------------");
        try {

            cstmt1 = connection.prepareCall("Call SPD_SELECTID(?)");

            cstmt1.setString(1, id);
            resultSet1 = cstmt1.executeQuery();

            if (resultSet1.next()) {
                p = new PersonEntity();
                p.setPid(resultSet1.getString(1));
                p.setIdentity_number(resultSet1.getString(2));
                p.setFullname(resultSet1.getString(3));
                p.setDob(resultSet1.getDate(4));
                //p.setHometown("To "+resultSet1.getString("NAME")+"- phuong "+resultSet1.getString("NAMEW")+"- quan "+resultSet1.getString("NAMED")+"- Tp "+resultSet1.getString("NAMEP"));
                p.setHometown(resultSet1.getString("HOMETOWN"));
                p.setPermanent_residence(resultSet1.getString(6));
                p.setImage(resultSet1.getBlob("IMAGE"));
                p.setIdentity_number(resultSet1.getString("IDENTITY_NUMBER"));

                //cstmtRes.setString(1, resultSet.getString(6));
                // resultSetRES=cstmtRes.executeQuery();
                // if(resultSetRES.next()){
                // p.setPermanent_residence("To "+resultSetRES.getString("NAMEG")+"- phuong "+resultSetRES.getString("NAMEW")+"- quan "+resultSetRES.getString("NAMED")+"- Tp "+resultSetRES.getString("NAMEP"));
                //  }
                p.setEthnic(resultSet1.getString(8));
                p.setReligion(resultSet1.getString(9));
                //  p.setCharacteristic(resultSet.getString(10));
                //  p.setDate(resultSet.getDate(11));
                //  p.setActive(resultSet.getString(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<ProvinceEntity> getListProvince() {
        List<ProvinceEntity> listCountry = new ArrayList<ProvinceEntity>();
        String sql = "select * from PROVINCE order by PROVINCEID asc";
        ResultSet rs = null;
        try {
            connection = ConnectDB2.getConnection();
            prstatement = connection.prepareStatement(sql);
            rs = prstatement.executeQuery();
            ProvinceEntity Province = null;
            while (rs.next()) {
                Province = new ProvinceEntity();
                Province.setProvinceID(rs.getString("ProvinceID"));
                Province.setNameP(rs.getString("NameP"));
                listCountry.add(Province);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                prstatement.close();
                //connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }

        return listCountry;
    }

    public HashSet<DistrictEntity> getListDistrict(String ProvinceID) {
        HashSet<DistrictEntity> listDistrict = null;
        try {
            listDistrict = new HashSet<DistrictEntity>();
            String sql = "select * from DISTRICT where PROVINCEID =? order by NAMED asc";
            ResultSet rs = null;

            connection = ConnectDB2.getConnection();
            prstatement = connection.prepareStatement(sql);
            prstatement.setString(1, ProvinceID);
            rs = prstatement.executeQuery();
            DistrictEntity DisE = null;
            while (rs.next()) {
                DisE = new DistrictEntity();
                DisE.setDistricID(rs.getString("DISTRICTID"));
                DisE.setNameD(rs.getString("NAMED"));
                DisE.setProvinceID(rs.getString("PROVINCEID"));
                listDistrict.add(DisE);
            }


            
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDistrict;
    }
    public HashSet<WardEntity> getListward(String DistrictID) {
        HashSet<WardEntity> listWard = null;
        try {
            listWard = new HashSet<WardEntity>();
            String sql = "select * from WARD where WARDID =? order by WARDW asc";
            ResultSet rs = null;

            connection = ConnectDB2.getConnection();
            prstatement = connection.prepareStatement(sql);
            prstatement.setString(1, DistrictID);
            rs = prstatement.executeQuery();
            WardEntity WrdE = null;
            while (rs.next()) {
                WrdE = new WardEntity();
                WrdE.setWardID(rs.getString("WARDID"));
                WrdE.setNameW(rs.getString("NAMEW"));
                WrdE.setDistrictID(rs.getString("DISTRICTID"));
                listWard.add(WrdE);
            }


            
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listWard;
    }
}
