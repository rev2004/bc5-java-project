/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.entity;

/**
 *
 * @author enclaveit
 */
public class DistrictEntity {
    private String districID;
    private String nameD;
    private String provinceID;

    /**
     * @return the districID
     */
    public String getDistricID() {
        return districID;
    }

    /**
     * @param districID the districID to set
     */
    public void setDistricID(String districID) {
        this.districID = districID;
    }

    /**
     * @return the nameD
     */
    public String getNameD() {
        return nameD;
    }

    /**
     * @param nameD the nameD to set
     */
    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    /**
     * @return the provinceID
     */
    public String getProvinceID() {
        return provinceID;
    }

    /**
     * @param provinceID the provinceID to set
     */
    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    @Override
    public String toString() {
        return nameD;
    }
    
}
