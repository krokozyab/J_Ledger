/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author facet
 */
//@EJB (name="ejbSupBal")
@Entity (name="/*supplierBalances*/")
@Table (name="XXYA_SUPPLIER_BAL")
public class suppliersBal implements Serializable {

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
    @Id
    @Column (name="rn")
    private BigDecimal id;
    
@Column (name="company")
    private String company;
@Column (name="account")
    private String account;
    @Column (name="analytics")
    private String analytics;
    @Column (name="cfo")
    private String cfo;
    @Column (name="mvp")
    private String mvp;
    @Column (name="program")
    private String program;
    @Column (name="service")
    private String service;
    @Column (name="sales")
    private String sales;
    @Column (name="interco")
    private String interco;
    @Column (name="nu_gaap")
    private String nu_gaap;
    @Column (name="supplier_name")
    private String supplierName;
    @Column (name="supplier_number")
    private String supplierNumber;
    @Column (name="supplier_site")
    private String supplierSite;
    @Column (name="po_number")
    private String poNumber;
    @Column (name="agreement")
    private String agreement;
    @Column (name="doc_type")
    private String docType;
    @Column (name="doc_number")
    private String docNumber;
    @Column (name="doc_date")
    private String   docDate;       
    @Column (name="currency_code")
    private String currencyCode;        
    @Column (name="entered_dr_beg")
    private BigDecimal enteredDrBeg;
    @Column (name="entered_cr_beg")
    private BigDecimal enteredCrBeg;
    @Column (name="accounted_dr_beg")
    private BigDecimal accountedDrBeg;
    @Column (name="accounted_cr_beg")
    private BigDecimal accountedCrBeg;
    @Column (name="entered_dr")
    private BigDecimal enteredDr;
    @Column (name="entered_cr")
    private BigDecimal enteredCr;
    @Column (name="accounted_dr")
    private BigDecimal accountedDr;
    @Column (name="accounted_cr")
    private BigDecimal accountedCr;
    @Column (name="entered_dr_end")
    private BigDecimal enteredDrEnd;
    @Column (name="entered_cr_end")
    private BigDecimal enteredCrEnd;
    @Column (name="accounted_dr_end")
    private BigDecimal accountedDrEnd;
    @Column (name="accounted_cr_end")
    private BigDecimal accountedCrEnd;
    @Column (name="accounted_bal_end")
    private BigDecimal accountedBalEnd;
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof suppliersBal)) {
            return false;
        }
        suppliersBal other = (suppliersBal) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "coreobjects.suppliersBal[ id=" + getId() + getSupplierName() +  " ]";
    }

    /**
     * @return the analytics
     */
    public String getAnalytics() {
        return analytics;
    }

    /**
     * @param analytics the analytics to set
     */
    public void setAnalytics(String analytics) {
        this.analytics = analytics;
    }

    /**
     * @return the cfo
     */
    public String getCfo() {
        return cfo;
    }

    /**
     * @param cfo the cfo to set
     */
    public void setCfo(String cfo) {
        this.cfo = cfo;
    }

    /**
     * @return the mvp
     */
    public String getMvp() {
        return mvp;
    }

    /**
     * @param mvp the mvp to set
     */
    public void setMvp(String mvp) {
        this.mvp = mvp;
    }

    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the sales
     */
    public String getSales() {
        return sales;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(String sales) {
        this.sales = sales;
    }

    /**
     * @return the interco
     */
    public String getInterco() {
        return interco;
    }

    /**
     * @param interco the interco to set
     */
    public void setInterco(String interco) {
        this.interco = interco;
    }

    /**
     * @return the nu_gaap
     */
    public String getNu_gaap() {
        return nu_gaap;
    }

    /**
     * @param nu_gaap the nu_gaap to set
     */
    public void setNu_gaap(String nu_gaap) {
        this.nu_gaap = nu_gaap;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the supplierNumber
     */
    public String getSupplierNumber() {
        return supplierNumber;
    }

    /**
     * @param supplierNumber the supplierNumber to set
     */
    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    /**
     * @return the supplierSite
     */
    public String getSupplierSite() {
        return supplierSite;
    }

    /**
     * @param supplierSite the supplierSite to set
     */
    public void setSupplierSite(String supplierSite) {
        this.supplierSite = supplierSite;
    }

    /**
     * @return the poNumber
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * @param poNumber the poNumber to set
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     * @return the agreement
     */
    public String getAgreement() {
        return agreement;
    }

    /**
     * @param agreement the agreement to set
     */
    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    /**
     * @return the docType
     */
    public String getDocType() {
        return docType;
    }

    /**
     * @param docType the docType to set
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }

    /**
     * @return the docNumber
     */
    public String getDocNumber() {
        return docNumber;
    }

    /**
     * @param docNumber the docNumber to set
     */
    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

  
    /**
     * @param docDate the docDate to set
     */
    public void setDocDate(Date docDate) {
        this.setDocDate(docDate);
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

    /**
     * @return the enteredDrBeg
     */
    public BigDecimal getEnteredDrBeg() {
        return enteredDrBeg;
    }

    /**
     * @param enteredDrBeg the enteredDrBeg to set
     */
    public void setEnteredDrBeg(BigDecimal enteredDrBeg) {
        this.enteredDrBeg = enteredDrBeg;
    }

    /**
     * @return the enteredCrBeg
     */
    public BigDecimal getEnteredCrBeg() {
        return enteredCrBeg;
    }

    /**
     * @param enteredCrBeg the enteredCrBeg to set
     */
    public void setEnteredCrBeg(BigDecimal enteredCrBeg) {
        this.enteredCrBeg = enteredCrBeg;
    }

    /**
     * @return the accountedDrBeg
     */
    public BigDecimal getAccountedDrBeg() {
        return accountedDrBeg;
    }

    /**
     * @param accountedDrBeg the accountedDrBeg to set
     */
    public void setAccountedDrBeg(BigDecimal accountedDrBeg) {
        this.accountedDrBeg = accountedDrBeg;
    }

    /**
     * @return the accountedCrBeg
     */
    public BigDecimal getAccountedCrBeg() {
        return accountedCrBeg;
    }

    /**
     * @param accountedCrBeg the accountedCrBeg to set
     */
    public void setAccountedCrBeg(BigDecimal accountedCrBeg) {
        this.accountedCrBeg = accountedCrBeg;
    }

    /**
     * @return the enteredDr
     */
    public BigDecimal getEnteredDr() {
        return enteredDr;
    }

    /**
     * @param enteredDr the enteredDr to set
     */
    public void setEnteredDr(BigDecimal enteredDr) {
        this.enteredDr = enteredDr;
    }

    /**
     * @return the enteredCr
     */
    public BigDecimal getEnteredCr() {
        return enteredCr;
    }

    /**
     * @param enteredCr the enteredCr to set
     */
    public void setEnteredCr(BigDecimal enteredCr) {
        this.enteredCr = enteredCr;
    }

    /**
     * @return the accountedDr
     */
    public BigDecimal getAccountedDr() {
        return accountedDr;
    }

    /**
     * @param accountedDr the accountedDr to set
     */
    public void setAccountedDr(BigDecimal accountedDr) {
        this.accountedDr = accountedDr;
    }

    /**
     * @return the accountedCr
     */
    public BigDecimal getAccountedCr() {
        return accountedCr;
    }

    /**
     * @param accountedCr the accountedCr to set
     */
    public void setAccountedCr(BigDecimal accountedCr) {
        this.accountedCr = accountedCr;
    }

    /**
     * @return the enteredDrEnd
     */
    public BigDecimal getEnteredDrEnd() {
        return enteredDrEnd;
    }

    /**
     * @param enteredDrEnd the enteredDrEnd to set
     */
    public void setEnteredDrEnd(BigDecimal enteredDrEnd) {
        this.enteredDrEnd = enteredDrEnd;
    }

    /**
     * @return the enteredCrEnd
     */
    public BigDecimal getEnteredCrEnd() {
        return enteredCrEnd;
    }

    /**
     * @param enteredCrEnd the enteredCrEnd to set
     */
    public void setEnteredCrEnd(BigDecimal enteredCrEnd) {
        this.enteredCrEnd = enteredCrEnd;
    }

    /**
     * @return the accountedDrEnd
     */
    public BigDecimal getAccountedDrEnd() {
        return accountedDrEnd;
    }

    /**
     * @param accountedDrEnd the accountedDrEnd to set
     */
    public void setAccountedDrEnd(BigDecimal accountedDrEnd) {
        this.accountedDrEnd = accountedDrEnd;
    }

    /**
     * @return the accountedCrEnd
     */
    public BigDecimal getAccountedCrEnd() {
        return accountedCrEnd;
    }

    /**
     * @param accountedCrEnd the accountedCrEnd to set
     */
    public void setAccountedCrEnd(BigDecimal accountedCrEnd) {
        this.accountedCrEnd = accountedCrEnd;
    }

    /**
     * @return the accountedBalEnd
     */
    public BigDecimal getAccountedBalEnd() {
        return accountedBalEnd;
    }

    /**
     * @param accountedBalEnd the accountedBalEnd to set
     */
    public void setAccountedBalEnd(BigDecimal accountedBalEnd) {
        this.accountedBalEnd = accountedBalEnd;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @param docDate the docDate to set
     */
    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    /**
     * @return the docDate
     */
    public String getDocDate() {
        return docDate;
    }
    
}
