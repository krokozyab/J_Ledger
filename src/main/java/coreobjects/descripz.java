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
public class descripz implements Serializable {
    private static final long serialVersionUID = 1L;  
       private String name;  
       private Object value;  
       private Integer segOrder;
       
        public descripz(String name, Object value, Integer segOrder){
        this.name=name;
        this.value=value;
        this.segOrder=segOrder;

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
     * @return the segOrder
     */
    public Integer getSegOrder() {
        return segOrder;
    }

    /**
     * @param segOrder the segOrder to set
     */
    public void setSegOrder(Integer segOrder) {
        this.segOrder = segOrder;
    }
 
}
