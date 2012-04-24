/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.dal;

import com.bc5Neptune.cis.config.ConnectDB2;
import com.bc5Neptune.cis.entity.PersonEntity;
import com.bc5Neptune.cis.gui.PPersonInformation;
import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import COM.ibm.db2.app.Blob;

/**
 *
 * @author enclaveit
 */
public class PersonDAL {

    Connection connection = null;
    ResultSet resultSet = null;
    ResultSet resultSetRES = null;
    ResultSet resultSetid=null;
    ResultSet resultSetid1=null;
    ResultSet resultSetname=null;
    Statement statement = null;
    PreparedStatement prstatement = null;
    CallableStatement cstmt = null;
    CallableStatement cstmtRes = null;
    CallableStatement cstmt_id = null;
    CallableStatement cstmt_id1=null;
    CallableStatement cstmt_name = null;

    public PersonDAL() {
    }

    public PersonEntity Select(String pid) {
        PersonEntity p = null;

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
                p = new PersonEntity();
                p.setPid(resultSet.getString(1));
                p.setIdentity_number(resultSet.getString(2));
                p.setFullname(resultSet.getString(3));
                p.setDob(resultSet.getDate(4));
                p.setHometown("To "+resultSet.getString("NAMEG")+"- phuong "+resultSet.getString("NAMEW")+"- quan "+resultSet.getString("NAMED")+"- Tp "+resultSet.getString("NAMEP"));
                //p.setPermanent_residence(resultSet.getString(6));
                p.setImage(resultSet.getBlob(7));
                cstmtRes.setString(1, resultSet.getString(6));
                resultSetRES=cstmtRes.executeQuery();
                if(resultSetRES.next()){
                p.setPermanent_residence("To "+resultSetRES.getString("NAMEG")+"- phuong "+resultSetRES.getString("NAMEW")+"- quan "+resultSetRES.getString("NAMED")+"- Tp "+resultSetRES.getString("NAMEP"));
                }
                p.setEthnic(resultSet.getString(8));
                p.setReligion(resultSet.getString(9));
                p.setCharacteristic(resultSet.getString(10));
                p.setDate(resultSet.getDate(11));
                p.setActive(resultSet.getString(12));
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
    public ResultSet searchid(String id){
        PersonEntity p1 = null;
        p1=new PersonEntity();
        connection = ConnectDB2.getConnection();
        try {
            cstmt_id = connection.prepareCall("Call SPD_SEARCHBYPID(?)");
           
            cstmt_id.setString(1, id);
            resultSetid=cstmt_id.executeQuery();
             } catch (Exception e) {
            e.printStackTrace();
        } 
         return resultSetid;   
        }
    public ResultSet searchname(String name){
        PersonEntity p2=null;
        p2=new PersonEntity();
        connection=ConnectDB2.getConnection();
        try {
            cstmt_name=connection.prepareCall("Call SPD_SEARCHBYNAME(?)");
            cstmt_name.setString(1, name);
            resultSetname=cstmt_name.executeQuery();
             } catch (Exception e) {
            e.printStackTrace();
        } 
         return resultSetname;   
        }
    public PersonEntity deleteID(String id){
        PersonEntity p3 = null;
        p3=new PersonEntity();
        connection= ConnectDB2.getConnection();
        try {
            cstmt_id1=connection.prepareCall("Call SPD_DELETEBYID(?)");
            cstmt_id1.setString(1, id);
            resultSetid1=cstmt_id1.executeQuery();
    
                
                p3.setPid(resultSetid1.getString(1));
            
           
        }catch (Exception e) {
            e.printStackTrace();
        } 
         return p3;
        
        
    }
}
