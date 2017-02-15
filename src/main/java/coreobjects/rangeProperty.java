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
public class rangeProperty implements Serializable {
    
    private static final long serialVersionUID = 1L;  
    
    private String name;  
    private Object value;  
    private boolean required; 
    private int size;
    private String completeMethod;
    private String hi_lo;
    private Integer segNum;

    public rangeProperty(String name, Object value, boolean required, int size, String completeMethod, String hi_lo, int segNum){
        this.name=name;
        this.value=value;
        this.required=required;
        this.size=size;
        this.completeMethod=completeMethod;
        this.hi_lo=hi_lo;
        this.segNum=segNum;
        
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the completeMethod
     */
    public String getCompleteMethod() {
        return completeMethod;
    }

    /**
     * @param completeMethod the completeMethod to set
     */
    public void setCompleteMethod(String completeMethod) {
        this.completeMethod = completeMethod;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the hi_lo
     */
    public String getHi_lo() {
        return hi_lo;
    }

    /**
     * @param hi_lo the hi_lo to set
     */
    public void setHi_lo(String hi_lo) {
        this.hi_lo = hi_lo;
    }

    /**
     * @return the segNum
     */
    public Integer getSegNum() {
        return segNum;
    }

    /**
     * @param segNum the segNum to set
     */
    public void setSegNum(Integer segNum) {
        this.segNum = segNum;
    }
}
