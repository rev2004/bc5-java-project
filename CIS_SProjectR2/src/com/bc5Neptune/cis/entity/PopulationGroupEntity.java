/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.entity;

/**
 *
 * @author enclaveit
 */
public class PopulationGroupEntity {
    private String pgroupID;
    private String nameG;
    private String wardID;

    /**
     * @return the pgroupID
     */
    public String getPgroupID() {
        return pgroupID;
    }

    /**
     * @param pgroupID the pgroupID to set
     */
    public void setPgroupID(String pgroupID) {
        this.pgroupID = pgroupID;
    }

    /**
     * @return the nameG
     */
    public String getNameG() {
        return nameG;
    }

    /**
     * @param nameG the nameG to set
     */
    public void setNameG(String nameG) {
        this.nameG = nameG;
    }

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

    @Override
    public String toString() {
        return nameG;
    }
    
}
