/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import coreoblects.ledgersFacade;
import javax.ejb.Stateless;
import coreobjects.*;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author facet
 */
@Named (value="ledgerBean")
@SessionScoped
public class Ledger_Bean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB private ledgersFacade ejbFacade;
    
    private ledgers ledger;
    private List<ledgers> ledgersList;

    /**
     *
     */
    public void populateLedgers(){
        this.setLedgersList(getEjbFacade().findAll());
        System.out.println("Blya!");
        //return "/admin/ledgers";
    }

    /**
     * @return the ejbFacade
     */
    public ledgersFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ledgersFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the ledger
     */
    public ledgers getLedger() {
        return ledger;
    }

    /**
     * @param ledger the ledger to set
     */
    public void setLedger(ledgers ledger) {
        this.ledger = ledger;
    }

    /**
     * @return the ledgersList
     */
    public List<ledgers> getLedgersList() {
        return ledgersList;
    }

    /**
     * @param ledgersList the ledgersList to set
     */
    public void setLedgersList(List<ledgers> ledgersList) {
        this.ledgersList = ledgersList;
    }
    
}
