/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert;

import coreobjects.ledgers;
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
@FacesConverter(value = "ledgerConverter")
public class ledgerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.trim().length() <= 0) {
            return null;
        } else {
            List<ledgers> ledgersList = rangeBean.getLedgersList();
            if (ledgersList.stream().filter(i -> i.getLedgerName().equals(value)).count() != 0) {
                return ledgersList.stream().filter(i -> i.getLedgerName().equals(value)).findAny().get();
            } else {
                return null;
            }
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
 if(object != null) {
     ledgers ax= (ledgers) object;
     return ax.getLedgerName();
        }
        else {
            return null;
        }
    }
}
