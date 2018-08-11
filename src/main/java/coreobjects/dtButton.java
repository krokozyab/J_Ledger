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
public class dtButton implements Serializable {
    private static final long serialVersionUID = 1L;  
       private String name;  
       private Object value;
       private Integer segOrder;
       private String prompt;

        public dtButton(String name, Object value, Integer segOrder, String prompt){
        this.name=name;
        this.value=value;
        this.segOrder=segOrder;
        this.prompt=prompt;
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

    /**
     * @return the prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * @param prompt the prompt to set
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
 
}
