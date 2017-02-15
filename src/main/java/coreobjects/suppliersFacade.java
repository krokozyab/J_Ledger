/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author facet
 */
@Stateless
public class suppliersFacade extends AbstractFacade<suppliers> {

    @PersistenceContext(unitName = "com.local_J_Ledger_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public suppliersFacade() {
        super(suppliers.class);
    }
    
    public  List CompleteSupplier(String text){
        List<String> matches;
        String qry;
        String qs="%"+text.toUpperCase()+"%";
        
        if (text == null && text.isEmpty()){ 
            return null;//matches;  
        }
        else
         qry="SELECT  S.VENDOR_NAME FROM AP_SUPPLIERS S WHERE UPPER( S.VENDOR_NAME) LIKE ?1";
        
        System.out.println("Query "+qry);
        System.out.println("Qs    "+qs);
        matches = new ArrayList<>();
        
        matches = em.createNativeQuery(qry).setParameter(1, qs).setMaxResults(100).getResultList();
        
        //System.out.println(matches.size());
        
       // for(String match : matches) {
       //     System.out.println(match);
       // }
        
        return matches;  
    }
    
}
