/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.entity;

/**
 *
 * @author enclaveit
 */
public class WardEntity {
    private String wardID;
    private String nameW;
    private String districtID;

    /**
     * @return the wardID
     */
    public String getWardID() {
        return wardID;
    }

    /**
     * @param wardID the wardID to set
     */
    public void setWardID(String wardID) {
        this.wardID = wardID;
    }

    /**
     * @return the nameW
     */
    public String getNameW() {
        return nameW;
    }

    /**
     * @param nameW the nameW to set
     */
    public void setNameW(String nameW) {
        this.nameW = nameW;
    }

    /**
     * @return the districtID
     */
    public String getDistrictID() {
        return districtID;
    }

    /**
     * @param districtID the districtID to set
     */
    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    @Override
    public String toString() {
        return nameW;
    }
    
}
