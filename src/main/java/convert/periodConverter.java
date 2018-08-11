/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import coreobjects.glPeriods;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
import beans.rangeBean;

/**
 *
 * @author facet
 */
@FacesConverter(value = "periodConverter")
 public class periodConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.trim().length() <= 0) {
            return null;
        } else {
            List<glPeriods> periodList = rangeBean.getPeriodList();
            if (periodList.stream().filter(i -> i.getPeriodName().equals(value)).count() != 0) {
                return periodList.stream().filter(i -> i.getPeriodName().equals(value)).findAny().get();
            } else {
                return null;
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     if(value != null) {
     glPeriods ax= (glPeriods) value;
     return ax.getPeriodName();
        }
        else {
            return null;
        }
    
    
    
    
    }


    


}
