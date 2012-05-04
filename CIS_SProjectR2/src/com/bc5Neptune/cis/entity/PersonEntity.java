/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.entity;

//import COM.ibm.db2.app.Blob;
import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author enclaveit
 */
public class PersonEntity {

    private String pid;
    private String identity_number;
    private String fullname;
    private Date dob;
    private String hometown;
    private String permanent_residence;
    private Blob image;
    private String saveImg;
    //private String image;
    private String ethnic;
    private String religion;
    private String characteristic;
    private Date date;
    private String active;


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
     * @return the identity_number
     */
    public String getIdentity_number() {
        return identity_number;
    }

    /**
     * @param identity_number the identity_number to set
     */
    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * @param hometown the hometown to set
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * @return the permanent_residence
     */
    public String getPermanent_residence() {
        return permanent_residence;
    }

    /**
     * @param permanent_residence the permanent_residence to set
     */
    public void setPermanent_residence(String permanent_residence) {
        this.permanent_residence = permanent_residence;
    }

    /**
     * @return the ethnic
     */
    public String getEthnic() {
        return ethnic;
    }

    /**
     * @param ethnic the ethnic to set
     */
    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the characteristic
     */
    public String getCharacteristic() {
        return characteristic;
    }

    /**
     * @param characteristic the characteristic to set
     */
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the active
     */
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the image
     */
    public Blob getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Blob image) {
        this.image = image;
    }

    /**
     * @return the saveImg
     */
    public String getSaveImg() {
        return saveImg;
    }

    /**
     * @param saveImg the saveImg to set
     */
    public void setSaveImg(String saveImg) {
        this.saveImg = saveImg;
    }
}
