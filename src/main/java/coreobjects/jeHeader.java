package coreobjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by facet on 30/06/16.
 */
@Entity(name="jeHeader")
public class jeHeader implements Serializable {
    // Info
    @Id
    @Column(name="je_header_id")
    private Integer header_id;
    @Column(name = "batch_name")
    private String batchName;
    @Column(name = "journal_name")
    private String journalName;
    @Column(name = "descriprion")
    private String description;
    @Column(name = "period_name")
    private String periodName;
    @Column(name = "balance_type")
    private String balanceType;
    @Column(name = "category")
    private String category;
    @Column(name = "source")
    private String source;
    @Column(name = "effective_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date effectiveDate;
    @Column(name = "posted_flag")
    private String postedFlag;
    // Conversion
    @Column(name="currency")
    private String currency;
    @Column(name = "currency_conversion_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date currencyConversionDate;
    @Column(name = "currency_conversion_type")
    private String currencyConversionType;
    @Column(name = "currency_conversion_rate")
    private BigDecimal currencyConversionRate;
    // Reverse
    @Column(name = "reverse_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reverseDate;
    @Column(name = "reverse_period")
    private String reversePeriod;

    @Column(name = "reverse_status")
    private String reverseStatus;
    // User
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name="last_update_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastUpdateDate;

    public Integer getHeader_id() {
        return header_id;
    }

    public void setHeader_id(Integer header_id) {
        this.header_id = header_id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getPostedFlag() {
        return postedFlag;
    }

    public void setPostedFlag(String postedFlag) {
        this.postedFlag = postedFlag;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCurrencyConversionDate() {
        return currencyConversionDate;
    }

    public void setCurrencyConversionDate(Date currencyConversionDate) {
        this.currencyConversionDate = currencyConversionDate;
    }

    public String getCurrencyConversionType() {
        return currencyConversionType;
    }

    public void setCurrencyConversionType(String currencyConversionType) {
        this.currencyConversionType = currencyConversionType;
    }

    public BigDecimal getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    public void setCurrencyConversionRate(BigDecimal currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }

    public Date getReverseDate() {
        return reverseDate;
    }

    public void setReverseDate(Date reverseDate) {
        this.reverseDate = reverseDate;
    }

    public String getReversePeriod() {
        return reversePeriod;
    }

    public void setReversePeriod(String reversePeriod) {
        this.reversePeriod = reversePeriod;
    }

    public String getReverseStatus() {
        return reverseStatus;
    }

    public void setReverseStatus(String reverseStatus) {
        this.reverseStatus = reverseStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    @Override
    public String toString() {return journalName;}
}
