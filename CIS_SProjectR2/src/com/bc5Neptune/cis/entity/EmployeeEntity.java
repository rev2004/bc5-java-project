/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.entity;

import java.util.Date;

/**
 *
 * @author thu.doan
 */
public class EmployeeEntity {

    private String pid;
    private String username;
    private String password;
    // private Date date;
    private int roleid;

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the pid
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param pid the pid to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the pid
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param pid the pid to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    /**
     * @param date the date to set
     */
    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
}
