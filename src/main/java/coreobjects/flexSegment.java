/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author facet
 */
@Entity
public class flexSegment implements Serializable {
    
    private long flex_value_set_id;
    @Id
    private long flex_value_id;
    private String flex_value;
    private String description;

    /**
     * @return the flex_value_set_id
     */
    public long getFlex_value_set_id() {
        return flex_value_set_id;
    }

    /**
     * @param flex_value_set_id the flex_value_set_id to set
     */
    public void setFlex_value_set_id(long flex_value_set_id) {
        this.flex_value_set_id = flex_value_set_id;
    }

    /**
     * @return the flex_value_id
     */
    public long getFlex_value_id() {
        return flex_value_id;
    }

    /**
     * @param flex_value_id the flex_value_id to set
     */
    public void setFlex_value_id(long flex_value_id) {
        this.flex_value_id = flex_value_id;
    }

    /**
     * @return the flex_value
     */
    public String getFlex_value() {
        return flex_value;
    }

    /**
     * @param flex_value the flex_value to set
     */
    public void setFlex_value(String flex_value) {
        this.flex_value = flex_value;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
