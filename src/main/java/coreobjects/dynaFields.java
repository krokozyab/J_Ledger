/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author facet
 */
public class dynaFields implements Serializable {  
  
    private static final long serialVersionUID = 20120521L;  
    private String name;  
    private Object value;  
    private boolean required;  
    private int size;
    private String completeMethod;

    public String getCompleteMethod() {
        return completeMethod;
    }

    public void setCompleteMethod(String completeMethod) {
        this.completeMethod = completeMethod;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
  
    public dynaFields( String name, int size, Object value, boolean required, String completeMethod) {  
        this.name = name;  
        this.size= size;
        this.required = required;  
        if (value.toString()!="null"){
        this.value=value;}
        else this.value="";
        this.completeMethod=completeMethod;
    }  
  
    public dynaFields(String name, Object value, boolean required) {  
        this.name = name;  
        this.required = required;
        if (value.toString()!="null"){
        this.value = value;}
        else this.value="";
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public Object getValue() {  
        return value;  
    }  
  
    public Object getFormattedValue() {  
        if (value instanceof Date) {  
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");  
  
            return simpleDateFormat.format(value);  
        }  
  
        return value;  
    }  
  
    public void setValue(Object value) {  
        this.value = value;  
    }  
  
    public boolean isRequired() {  
        return required;  
    }  
  
    public void setRequired(boolean required) {  
        this.required = required;  
    }  
}  
