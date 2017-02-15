/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import coreobjects.sobStruct;
import coreobjects.sobStructFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author facet
 */
@ManagedBean
@Named (value="structBean")
@SessionScoped
public class structBean implements Serializable
        
{
@EJB private sobStructFacade stru;
public static List<sobStruct> flexStruct;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")


//public void populateStruct(){
//        this.setFlexStruct(stru.allStruct());
//    }

    public List<sobStruct> getFlexStruct() {
        return flexStruct;
    }

    public void setFlexStruct(List<sobStruct> flexStruct) {
        structBean.flexStruct = flexStruct;
    }

    /**
     * @return the stru
     */
    public sobStructFacade getStru() {
        return stru;
    }

    /**
     * @param stru the stru to set
     */
    public void setStru(sobStructFacade stru) {
        this.stru = stru;
    }
}
