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


/**
 *
 * @author facet
 */
@Entity
public class sobStruct implements Serializable {

    private static long serialVersionUID = 1L;
   
   
    //@Column(name="seg_id")
    //private Long seg_id;    
    @Column(name="flex_value_set_id")
    private Long flexValueSetId;
    @Column(name="name")
    private String ledgerName;
    @Column(name="ledger_id")
    private Integer ledgerId;
    @Column(name="chart_of_accounts_id")
    private Integer coaId;
    @Column(name="id_flex_structure_name")
    private String structName;
    @Column(name="segment_name")
    private String segmentName;
    @Column(name="segment_num")
    private Integer segmentNum;
    @Column(name="display_size")
    private Integer displaySize;
    @Column(name="default_type")
    private String defaultType;
    @Column(name="default_value")
    private String defaultValue;
    @Column(name="form_left_prompt")
    private String formLeftPrompt;
     @Id
    @Column(name="application_column_name")
    private String columnName;
    @Column(name="balancing")
    private String balancing;
    @Column(name="costCenter")
    private String costCenter;
    @Column(name="naturalAccount")
    private String naturalAccount;
    @Column(name="intercompany")
    private String intercompany;
    @Column(name="secondaryTracking")
    private String secondaryTracking;
    @Column(name="global")
    private String global;
    @Column(name="flex_value_set_name")
    private String flexValueSetName; 

   
    /**
     * @return the flexValueSetId
     */
    public Long getFlexValueSetId() {
        return flexValueSetId;
    }

    /**
     * @param flexValueSetId the flexValueSetId to set
     */
    public void setFlexValueSetId(Long flexValueSetId) {
        this.flexValueSetId = flexValueSetId;
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
     * @return the coaId
     */
    public Integer getCoaId() {
        return coaId;
    }

    /**
     * @param coaId the coaId to set
     */
    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    /**
     * @return the structName
     */
    public String getStructName() {
        return structName;
    }

    /**
     * @param structName the structName to set
     */
    public void setStructName(String structName) {
        this.structName = structName;
    }

    /**
     * @return the segmentName
     */
    public String getSegmentName() {
        return segmentName;
    }

    /**
     * @param segmentName the segmentName to set
     */
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    /**
     * @return the segmentNum
     */
    public Integer getSegmentNum() {
        return segmentNum;
    }

    /**
     * @param segmentNum the segmentNum to set
     */
    public void setSegmentNum(Integer segmentNum) {
        this.segmentNum = segmentNum;
    }

    /**
     * @return the displaySize
     */
    public Integer getDisplaySize() {
        return displaySize;
    }

    /**
     * @param displaySize the displaySize to set
     */
    public void setDisplaySize(Integer displaySize) {
        this.displaySize = displaySize;
    }

    /**
     * @return the defaultType
     */
    public String getDefaultType() {
        return defaultType;
    }

    /**
     * @param defaultType the defaultType to set
     */
    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return the formLeftPrompt
     */
    public String getFormLeftPrompt() {
        return formLeftPrompt;
    }

    /**
     * @param formLeftPrompt the formLeftPrompt to set
     */
    public void setFormLeftPrompt(String formLeftPrompt) {
        this.formLeftPrompt = formLeftPrompt;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the balancing
     */
    public String getBalancing() {
        return balancing;
    }

    /**
     * @param balancing the balancing to set
     */
    public void setBalancing(String balancing) {
        this.balancing = balancing;
    }

    /**
     * @return the costCenter
     */
    public String getCostCenter() {
        return costCenter;
    }

    /**
     * @param costCenter the costCenter to set
     */
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    /**
     * @return the naturalAccount
     */
    public String getNaturalAccount() {
        return naturalAccount;
    }

    /**
     * @param naturalAccount the naturalAccount to set
     */
    public void setNaturalAccount(String naturalAccount) {
        this.naturalAccount = naturalAccount;
    }

    /**
     * @return the intercompany
     */
    public String getIntercompany() {
        return intercompany;
    }

    /**
     * @param intercompany the intercompany to set
     */
    public void setIntercompany(String intercompany) {
        this.intercompany = intercompany;
    }

    /**
     * @return the secondaryTracking
     */
    public String getSecondaryTracking() {
        return secondaryTracking;
    }

    /**
     * @param secondaryTracking the secondaryTracking to set
     */
    public void setSecondaryTracking(String secondaryTracking) {
        this.secondaryTracking = secondaryTracking;
    }

    /**
     * @return the global
     */
    public String getGlobal() {
        return global;
    }

    /**
     * @param global the global to set
     */
    public void setGlobal(String global) {
        this.global = global;
    }

    /**
     * @return the flexValueSetName
     */
    public String getFlexValueSetName() {
        return flexValueSetName;
    }

    /**
     * @param flexValueSetName the flexValueSetName to set
     */
    public void setFlexValueSetName(String flexValueSetName) {
        this.flexValueSetName = flexValueSetName;
    }
    


 

}
