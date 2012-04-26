/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.entity;

/**
 *
 * @author enclaveit

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class ProvinceEntity {
    private String ProvinceID;
    private String NameP;


    /**
     * @return the ProvinceID
     */
    public String getProvinceID() {
        return ProvinceID;
    }

    /**
     * @param ProvinceID the ProvinceID to set
     */
    public void setProvinceID(String ProvinceID) {
        this.ProvinceID = ProvinceID;
    }

    /**
     * @return the NameP
     */
    public String getNameP() {
        return NameP;
    }

    /**
     * @param NameP the NameP to set
     */
    public void setNameP(String NameP) {
        this.NameP = NameP;
    }
    
    @Override
    public String toString(){
        return NameP;
    }
}
