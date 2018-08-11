/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author facet
 */
public class selectParts {
    private StringBuilder fromPart=new StringBuilder();
    private StringBuilder wherePart=new StringBuilder();
    
 

    public void addFromPart(String part){
        if(part!=null && !part.isEmpty()){
            fromPart.append(part);
        }
    }
    public void addWherePart(String part){
        if(part!=null && !part.isEmpty()){
            wherePart.append(part);
        }
    }

    /**
     * @return the fromPart
     */
    public StringBuilder getFromPart() {
        return fromPart;
    }

    /**
     * @param fromPart the fromPart to set
     */
    public void setFromPart(String fromPart) {
        this.fromPart.setLength(0);
        this.fromPart.append(fromPart);
    }

    /**
     * @return the wherePart
     */
    public StringBuilder getWherePart() {
        return wherePart;
    }

    /**
     * @param wherePart the wherePart to set
     */
    public void setWherePart(String wherePart) {
        this.wherePart.setLength(0);
        this.wherePart.append(wherePart);
    }
   
    
}
