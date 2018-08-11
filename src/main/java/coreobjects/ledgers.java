/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author facet
 */
//@EJB
@Entity (name="ledgers")
@Table(name="apps.gl_ledgers")
public class ledgers implements Serializable {
    
    @Id
    @Column (name="LEDGER_ID")
    private Integer ledgerId;
    @Column (name="NAME")
    private String ledgerName;
    @Column (name="PERIOD_SET_NAME")
    private String periodSetName;
    @Column (name="CURRENCY_CODE")
    private String currencyCode;

    
    public ledgers () {}
    
    public ledgers (Integer ledgerId, String ledgerName, String periodSetName) {
    this.ledgerId=ledgerId;
    this.ledgerName=ledgerName;
    this.periodSetName=periodSetName;
            }
    /**
     * @return the ledgerId
     */
    public Integer getLedgerId() {
        return ledgerId;
    }

    /**
     * @param ledgerId the ledgerId to set
     */
    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    /**
     * @return the ledgerName
     */
    public String getLedgerName() {
        return ledgerName;
    }

    /**
     * @param ledgerName the ledgerName to set
     */
    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    /**
     * @return the periodSetName
     */
    public String getPeriodSetName() {
        return periodSetName;
    }

    /**
     * @param periodSetName the periodSetName to set
     */
    public void setPeriodSetName(String periodSetName) {
        this.periodSetName = periodSetName;
    }
public String idToString(){
return ledgerId.toString();
}
  
@Override
    public String toString() {
        return ledgerName;
    }

    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
