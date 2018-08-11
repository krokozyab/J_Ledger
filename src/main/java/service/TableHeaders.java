/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;

/**
 *
 * @author facet
 */
public class TableHeaders implements Serializable {    
        private String header;
        private int span;
        
        public TableHeaders (String header, int span){
            this.header=header;
            this.span=span;
        }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return the span
     */
    public int getSpan() {
        return span;
    }

    /**
     * @param span the span to set
     */
    public void setSpan(int span) {
        this.span = span;
    }
}
