/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author facet
 */
@Stateless
public class suppliersBalFacade extends AbstractFacade<suppliersBal> {

    @PersistenceContext(unitName = "com.local_J_Ledger_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public suppliersBalFacade() {
        super(suppliersBal.class);
    }
    
}
