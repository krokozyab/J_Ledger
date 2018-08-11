/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author facet
 */
public class drillConditions implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    private String accountingDateFrom;
    private String accountingDateTo;
    private String ledgerId;
    private String balanceTypeCode="\'A\'";
    private List<String> periods;
    private String segments;
    private String sqlHeaders;
    private String currencyCode;

    /**
     * @return the accountingDateFrom
     */
    public String getAccountingDateFrom() {
        return accountingDateFrom;
    }

    /**
     * @param accountingDateFrom the accountingDateFrom to set
     */
    public void setAccountingDateFrom(String accountingDateFrom) {
        this.accountingDateFrom = accountingDateFrom;
    }

    /**
     * @return the accountingDateTo
     */
    public String getAccountingDateTo() {
        return accountingDateTo;
    }

    /**
     * @param accountingDateTo the accountingDateTo to set
     */
    public void setAccountingDateTo(String accountingDateTo) {
        this.accountingDateTo = accountingDateTo;
    }

    /**
     * @return the ledgerId
     */
    public String getLedgerId() {
        return ledgerId;
    }

    /**
     * @param ledgerId the ledgerId to set
     */
    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    /**
     * @return the balanceTypeCode
     */
    public String getBalanceTypeCode() {
        return balanceTypeCode;
    }

    /**
     * @param balanceTypeCode the balanceTypeCode to set
     */
    public void setBalanceTypeCode(String balanceTypeCode) {
        this.balanceTypeCode = balanceTypeCode;
    }

    /**
     * @return the periods
     */
    public List<String> getPeriods() {
        return periods;
    }

    /**
     * @param periods the periods to set
     */
    public void setPeriods(List<String> periods) {
        this.periods = periods;
    }


    /**
     * @param segments the segments to set
     */
    public void setSegments(String segments) {
        this.segments = segments;
    }

    /**
     * @return the segments
     */
    public String getSegments() {
        return segments;
    }

    /**
     * @return the sqlHeaders
     */
    public String getSqlHeaders() {
        return sqlHeaders;
    }

    /**
     * @param sqlHeaders the sqlHeaders to set
     */
    public void setSqlHeaders(String sqlHeaders) {
        this.sqlHeaders = sqlHeaders;
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
