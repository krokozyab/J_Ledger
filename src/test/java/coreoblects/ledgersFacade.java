/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreoblects;

import coreobjects.AbstractFacade;
import coreobjects.ledgers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author facet
 */

@Stateless
public class ledgersFacade extends AbstractFacade<ledgers> {

    @PersistenceContext(unitName = "com.local_J_Ledger_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ledgersFacade() {
        super(ledgers.class);
    }
    
    public List<ledgers> getLedgers(){
        String qry;
        qry="select ledger_id, name, period_set_name from gl_ledgers";
        return (List<ledgers>) em.createNativeQuery(qry, ledgers.class).getResultList();
        
    }
           
}
