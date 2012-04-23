/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.dal;

import com.bc5Neptune.cis.config.ConnectDB2;
import com.bc5Neptune.cis.entity.EmployeeEntity;
import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author thu.doan
 */
public class Employee {
    Connection con = null;
    ResultSet resultSet = null;
    ResultSet resultSetPass = null;
    Statement statement = null;
    PreparedStatement prstatement = null;
    
    
   
    public Employee(){
        
    }
    public EmployeeEntity selectuser(String username){
        EmployeeEntity em = null;
        CallableStatement cstmtUs = null;
         con = ConnectDB2.getConnection();
        //CallableStatement cstmtInsert =null;
        System.out.println("------------------VIEW DATA---------------------");
        try {
            
            cstmtUs = con.prepareCall("Call SPD_USERNAME(?)");
           
            
            cstmtUs.setString(1, username);
            resultSet = cstmtUs.executeQuery();
            em = new EmployeeEntity();
            if (resultSet.next()) {
                
               // em.setPid(resultSet.getString(1));
                em.setUsername(resultSet.getString(3));
                //em.setPassword(resultSet.getString(3));
                //em.setDate(resultSet.getDate(4));
                //em.setRoleid(resultSet.getInt(5));
            }
            if(resultSet.wasNull()==true){
                em.setUsername(null);
            }
            
            } catch (Exception e) {
            e.printStackTrace();
        } 
        return em;
    }
    public EmployeeEntity selectpass(String password){
        EmployeeEntity em1 = null;
        con = ConnectDB2.getConnection();
        CallableStatement cstmtPass = null;
         try {
           
            cstmtPass = con.prepareCall("Call SPD_PASSWORD(?)");
            cstmtPass.setString(1,password);
            resultSetPass=cstmtPass.executeQuery();
            em1 = new EmployeeEntity();
            if(resultSetPass.next()){
                
                em1.setPassword(resultSetPass.getString(4));
            }
            else if(resultSetPass.wasNull()==true){
                em1.setPassword(null);
            }
            
            } catch (Exception e) {
            e.printStackTrace();
        } 
        return em1;
    }
    public int updatepass(String pass1, String pass2){
        //EmployeeEntity em2=null;
        int i = 0;
        con = ConnectDB2.getConnection();
        CallableStatement cstmtPass2=null;
        try {
            cstmtPass2= con.prepareCall("Call SPD_UPDATEPASS(?,?)");
           cstmtPass2.setString(1, pass1);
           cstmtPass2.setString(2, pass2);
            i=cstmtPass2.executeUpdate();
            
                } catch (Exception e) {
            e.printStackTrace();
        }
        
        return i;
    }
}