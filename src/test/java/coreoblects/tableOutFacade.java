/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreoblects;


import org.apache.commons.beanutils.BeanUtils;
import coreobjects.AbstractFacade;
import coreobjects.AbstractFacade;
import coreobjects.AbstractFacade;
import coreobjects.AbstractFacade;
import coreobjects.tableOut;
import coreobjects.tableOut;
import coreobjects.tableOut;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author facet
 */
@Stateless
public class tableOutFacade extends AbstractFacade<tableOut> {

    @PersistenceContext(unitName = "com.local_J_Ledger_war_1.0-SNAPSHOTPU")
    private  EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public tableOutFacade() {
        super(tableOut.class);
    }

    public List<tableOut> getTabres(String qry, List<String> mp) throws NoSuchMethodException, InvocationTargetException {
        List<Object> rslt = new ArrayList<>();
        List<tableOut> rtab = new ArrayList<>();
        tableOut ax;
        rslt = (List<Object>) em.createNativeQuery(qry).getResultList();
        Iterator rsltI = rslt.iterator();
        while (rsltI.hasNext()) {
            ax = new tableOut();
            Object[] obj = (Object[]) rsltI.next();
            for (int i = 0; i < mp.size(); i++) {
                if (obj[i] == null) {
                    obj[i] = 0;
                }
                try {
                    BeanUtils.setProperty(ax, mp.get(i), obj[i]);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(tableOutFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rtab.add(ax);
        }
        return rtab;
    }

}
