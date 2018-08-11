/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author facet
 */
@Entity
public class sgv implements Serializable {
    @Id
    //@GeneratedValue (strategy=GenerationType.AUTO)
    private int flex_value_id;
    private String flex_value;
    private String summary_flag;
    private String description;

    public sgv(){}

    public sgv(int flex_value_id, String flex_value, String summary_flag, String description){
        this.flex_value_id=flex_value_id;
        this.flex_value=flex_value;
        this.summary_flag=summary_flag;
        this.description=description;
    }


    public String getFlex_value() {
        return flex_value;
    }

    public void setFlex_value(String flex_value) {
        this.flex_value = flex_value;
    }

    /**
     * @return the flex_value_id
     */
    public int getFlex_value_id() {
        return flex_value_id;
    }

    /**
     * @param flex_value_id the flex_value_id to set
     */
    public void setFlex_value_id(int flex_value_id) {
        this.flex_value_id = flex_value_id;
    }

    public String getSummary_flag() {
        return summary_flag;
    }

    public void setSummary_flag(String summary_flag) {
        this.summary_flag = summary_flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
