/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreoblects;

import coreobjects.AbstractFacade;
import coreobjects.glPeriods;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author facet
 */
@Stateless
public class glPeriodsFacade extends AbstractFacade<glPeriods> {

    @PersistenceContext(unitName = "com.local_J_Ledger_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public glPeriodsFacade() {
        super(glPeriods.class);
    }
    
    public List<glPeriods> getPeriods(String periodSetName) {
        StringBuilder qry = new StringBuilder();
        qry.append("select period_name, start_date, end_date, period_year, period_num, adjustment_period_flag\n"
                + "from gl_periods \n"
                + "where period_set_name=\'");
        qry.append(periodSetName);
        qry.append("\' " + "order by start_date");
        return (List<glPeriods>) em.createNativeQuery(qry.toString(), glPeriods.class).getResultList();
    }
}
