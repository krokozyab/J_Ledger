/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;


import beans.rangeBean;
import coreobjects.pivot;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;
/**
 *
 * @author facet
 */
@FacesConverter(value = "pivotConverter")
public class pivotConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.trim().length() <= 0) {
            return null;
        } else {
            List<pivot> pivotList = rangeBean.getPivotList();
            if (pivotList.stream().filter(i -> i.getPivotName().equals(value)).count() != 0) {
                //System.out.println("pivot invoked!");
                return pivotList.stream().filter(i -> i.getPivotName().equals(value)).findAny().get();
            } else {
                return null;
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     if(value != null) {
     pivot ax= (pivot) value;
     return ax.getPivotName();
        }
        else {
            return null;
        }
    
    
    }   
}
