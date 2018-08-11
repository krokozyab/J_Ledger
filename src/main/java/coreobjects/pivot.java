/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;

/**
 *
 * @author facet
 */
public class pivot implements  Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String pivotName;
    private String pivotSegId;
    private String pivotSegName;
    
    public pivot(String pivotName, String pivotSegId, String pivotSegName) {
        this.pivotName=pivotName;
        this.pivotSegId=pivotSegId;
        this.pivotSegName=pivotSegName;
    }

    /**
     * @return the pivotName
     */
    public String getPivotName() {
        return pivotName;
    }

    /**
     * @param pivotName the pivotName to set
     */
    public void setPivotName(String pivotName) {
        this.pivotName = pivotName;
    }

    /**
     * @return the pivotSegId
     */
    public String getPivotSegId() {
        return pivotSegId;
    }

    /**
     * @param pivotSegId the pivotSegId to set
     */
    public void setPivotSegId(String pivotSegId) {
        this.pivotSegId = pivotSegId;
    }

    /**
     * @return the pivotSegName
     */
    public String getPivotSegName() {
        return pivotSegName;
    }

    /**
     * @param pivotSegName the pivotSegName to set
     */
    public void setPivotSegName(String pivotSegName) {
        this.pivotSegName = pivotSegName;
    }
    
    @Override
    public String toString(){
        return getPivotName();
    }
    


}
