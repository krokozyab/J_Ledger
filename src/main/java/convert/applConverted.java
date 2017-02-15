/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;


import beans.Applic;
import coreobjects.eApplication;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;
/**
 *
 * @author facet
 */

@FacesConverter(value = "applConverter")
public class applConverted implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.trim().length() <= 0) {
            return null;
        } else {

        List<eApplication> aplist = Applic.getApplications();
            if (aplist.stream().filter(i->i.getApplicationName().equals(value)).count()!=0){
                return aplist.stream().filter(i->i.getApplicationName().equals(value)).findAny().get();
            } else return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            eApplication bx=(eApplication) value;
            return bx.getApplicationName();
        }
        else {
            return null;
        }


    }
}
