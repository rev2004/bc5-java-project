/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.config;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author enclaveit
 */
public class ConnectDB2 {

    //ResultSet resultSet = null;
    //Statement statement = null;
    //CallableStatement cstmt = null;
    public static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.ibm.db2.jcc.DB2Driver");  //com.ibm.db2.jcc.DB2Driver
                connection = DriverManager.getConnection(
                        "jdbc:db2://localhost:50000/CIS", "db2inst1", //jdbc:db2://localhost:50000/TEST
                        "bc5@123");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectDB2.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("File Not Found!");
                JOptionPane.showMessageDialog(null, "FILE NOT FOUND", "CONNECTION ERROR", JOptionPane.OK_OPTION);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR CONNECT TO DATABSE", "CONNECTION ERROR", JOptionPane.OK_OPTION);
            }
        }
        return connection;
    }
}
