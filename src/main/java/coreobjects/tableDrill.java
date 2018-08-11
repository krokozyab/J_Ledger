/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author facet
 */
@Entity
public class tableDrill implements Serializable {
    
        @Id
        @Column(name="rwd")
    private String rowid;
        @Column(name="source")
    private String source;
        @Column(name="entity_code")
    private String entityCode;
        @Column(name="event_type_code")
    private String eventTypeCode;
        @Column(name="accounting_class_code")
    private String accountingClassCode;
        @Column(name="transaction_number")
    private String transactionNumber;
        @Column(name="party")
    private String party;
        @Column(name="party_site")
    private String partySite;
        @Column(name="currency_code")
    private String currencyCode;
        @Column(name="description")
    private String description;
        @Column(name="Batch_name")
    private String batchName;
        @Column(name="Batch_status")
    private String batchStatus;
        @Column(name="Journal_name")
    private String journalName;
        @Column(name="ae_line_num")
    private Integer aeLineNum;
        @Column(name="Journal_line")
    private Integer journalLine;
        @Column(name="segment1")
    private String segment1;
        @Column(name="segment2")
    private String segment2;
        @Column(name="segment3")
    private String segment3;
        @Column(name="segment4")
    private String segment4;
        @Column(name="segment5")
    private String segment5;
        @Column(name="segment6")
    private String segment6;
        @Column(name="segment7")
    private String segment7;
        @Column(name="segment8")
    private String segment8;
        @Column(name="segment9")
    private String segment9;
        @Column(name="segment10")
    private String segment10;
        @Column(name="segment11")
    private String segment11;
        @Column(name="segment12")
    private String segment12;
        @Column(name="segment13")
    private String segment13;
        @Column(name="segment14")
    private String segment14;
        @Column(name="segment15")
    private String segment15;
        @Column(name="segment16")
    private String segment16;
        @Column(name="segment17")
    private String segment17;
        @Column(name="segment18")
    private String segment18;
        @Column(name="segment19")
    private String segment19;
        @Column(name="segment20")
    private String segment20;
        @Column(name="segment21")
    private String segment21;
        @Column(name="segment22")
    private String segment22;
        @Column(name="segment23")
    private String segment23;
        @Column(name="segment24")
    private String segment24;
        @Column(name="segment25")
    private String segment25;
        @Column(name="entered_dr")
    private BigDecimal enteredDr;
        @Column(name="entered_cr")
    private BigDecimal enteredCr;
        @Column(name="accounted_dr")
    private BigDecimal accountedDr;
        @Column(name="accounted_cr")
    private BigDecimal accountedCr;
        @Column(name="RESULT_COUNT")
    private Integer resultCount;
        @Column(name = "ae_header_id")
        private Integer aeHeaderId;
    @Column(name = "je_header_id")
    private Integer jeHeaderId;
  
    private static Map<String, String> oraJava; // = new HashMap<>();
    
    public tableDrill(){
        oraJava = new HashMap<>();
        oraJava.put("source", "source");
        oraJava.put("entityCode", "entity_code");
        oraJava.put("eventTypeCode", "event_type_code");
        oraJava.put("accountingClassCode", "accounting_class_code");
        oraJava.put("transactionNumber", "transaction_number");
        oraJava.put("party", "party");
        oraJava.put("partySite", "party_site");
        oraJava.put("currencyCode", "currency_code");
        oraJava.put("description", "description");
        oraJava.put("batchName", "Batch_name");
        oraJava.put("batchStatus", "Batch_status");
        oraJava.put("journalName", "Journal_name");
        oraJava.put("aeLineNum", "ae_line_num");
        oraJava.put("journalLine", "Journal_line");
        oraJava.put("enteredDr", "entered_dr");
        oraJava.put("enteredCr", "entered_cr");
        oraJava.put("accountedDr", "accounted_dr");
        oraJava.put("accountedCr", "accounted_cr");
        oraJava.put("segment1", "segment1");
        oraJava.put("segment2", "segment2");
        oraJava.put("segment3", "segment3");
        oraJava.put("segment4", "segment4");
        oraJava.put("segment5", "segment5");
        oraJava.put("segment6", "segment6");
        oraJava.put("segment7", "segment7");
        oraJava.put("segment8", "segment8");
        oraJava.put("segment9", "segment9");
        oraJava.put("segment10", "segment10");
        oraJava.put("segment11", "segment11");
        oraJava.put("segment12", "segment12");
        oraJava.put("segment13", "segment13");
        oraJava.put("segment14", "segment14");
        oraJava.put("segment15", "segment15");
        oraJava.put("segment16", "segment16");
        oraJava.put("segment17", "segment17");
        oraJava.put("segment18", "segment18");
        oraJava.put("segment19", "segment19");
        oraJava.put("segment20", "segment20");
        oraJava.put("segment21", "segment21");
        oraJava.put("segment22", "segment22");
        oraJava.put("segment23", "segment23");
        oraJava.put("segment24", "segment24");
        oraJava.put("segment25", "segment25");
        oraJava.put("aeHeaderId","ae_header_id");
        oraJava.put("jeHeaderId","je_header_id");


    }

    /**
     * @return the rowid
     */
    public String getRowid() {
        return rowid;
    }

    /**
     * @param rowid the rowid to set
     */
    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    /**
     * @return the entityCode
     */
    public String getEntityCode() {
        return entityCode;
    }

    /**
     * @param entityCode the entityCode to set
     */
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    /**
     * @return the eventTypeCode
     */
    public String getEventTypeCode() {
        return eventTypeCode;
    }

    /**
     * @param eventTypeCode the eventTypeCode to set
     */
    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    /**
     * @return the accountingClassCode
     */
    public String getAccountingClassCode() {
        return accountingClassCode;
    }

    /**
     * @param accountingClassCode the accountingClassCode to set
     */
    public void setAccountingClassCode(String accountingClassCode) {
        this.accountingClassCode = accountingClassCode;
    }

    /**
     * @return the transactionNumber
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * @param transactionNumber the transactionNumber to set
     */
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    /**
     * @return the party
     */
    public String getParty() {
        return party;
    }

    /**
     * @param party the party to set
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * @return the partySite
     */
    public String getPartySite() {
        return partySite;
    }

    /**
     * @param partySite the partySite to set
     */
    public void setPartySite(String partySite) {
        this.partySite = partySite;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * @param batchName the batchName to set
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * @return the batchStatus
     */
    public String getBatchStatus() {
        return batchStatus;
    }

    /**
     * @param batchStatus the batchStatus to set
     */
    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    /**
     * @return the journalName
     */
    public String getJournalName() {
        return journalName;
    }

    /**
     * @param journalName the journalName to set
     */
    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    /**
     * @return the aeLineNum
     */
    public Integer getAeLineNum() {
        return aeLineNum;
    }

    /**
     * @param aeLineNum the aeLineNum to set
     */
    public void setAeLineNum(Integer aeLineNum) {
        this.aeLineNum = aeLineNum;
    }

    /**
     * @return the journalLine
     */
    public Integer getJournalLine() {
        return journalLine;
    }

    /**
     * @param journalLine the journalLine to set
     */
    public void setJournalLine(Integer journalLine) {
        this.journalLine = journalLine;
    }

    /**
     * @return the segment1
     */
    public String getSegment1() {
        return segment1;
    }

    /**
     * @param segment1 the segment1 to set
     */
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    /**
     * @return the segment2
     */
    public String getSegment2() {
        return segment2;
    }

    /**
     * @param segment2 the segment2 to set
     */
    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    /**
     * @return the segment3
     */
    public String getSegment3() {
        return segment3;
    }

    /**
     * @param segment3 the segment3 to set
     */
    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    /**
     * @return the segment4
     */
    public String getSegment4() {
        return segment4;
    }

    /**
     * @param segment4 the segment4 to set
     */
    public void setSegment4(String segment4) {
        this.segment4 = segment4;
    }

    /**
     * @return the segment5
     */
    public String getSegment5() {
        return segment5;
    }

    /**
     * @param segment5 the segment5 to set
     */
    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }

    /**
     * @return the segment6
     */
    public String getSegment6() {
        return segment6;
    }

    /**
     * @param segment6 the segment6 to set
     */
    public void setSegment6(String segment6) {
        this.segment6 = segment6;
    }

    /**
     * @return the segment7
     */
    public String getSegment7() {
        return segment7;
    }

    /**
     * @param segment7 the segment7 to set
     */
    public void setSegment7(String segment7) {
        this.segment7 = segment7;
    }

    /**
     * @return the segment8
     */
    public String getSegment8() {
        return segment8;
    }

    /**
     * @param segment8 the segment8 to set
     */
    public void setSegment8(String segment8) {
        this.segment8 = segment8;
    }

    /**
     * @return the segment9
     */
    public String getSegment9() {
        return segment9;
    }

    /**
     * @param segment9 the segment9 to set
     */
    public void setSegment9(String segment9) {
        this.segment9 = segment9;
    }

    /**
     * @return the segment10
     */
    public String getSegment10() {
        return segment10;
    }

    /**
     * @param segment10 the segment10 to set
     */
    public void setSegment10(String segment10) {
        this.segment10 = segment10;
    }

    /**
     * @return the segment11
     */
    public String getSegment11() {
        return segment11;
    }

    /**
     * @param segment11 the segment11 to set
     */
    public void setSegment11(String segment11) {
        this.segment11 = segment11;
    }

    /**
     * @return the segment12
     */
    public String getSegment12() {
        return segment12;
    }

    /**
     * @param segment12 the segment12 to set
     */
    public void setSegment12(String segment12) {
        this.segment12 = segment12;
    }

    /**
     * @return the segment13
     */
    public String getSegment13() {
        return segment13;
    }

    /**
     * @param segment13 the segment13 to set
     */
    public void setSegment13(String segment13) {
        this.segment13 = segment13;
    }

    /**
     * @return the segment14
     */
    public String getSegment14() {
        return segment14;
    }

    /**
     * @param segment14 the segment14 to set
     */
    public void setSegment14(String segment14) {
        this.segment14 = segment14;
    }

    /**
     * @return the segment15
     */
    public String getSegment15() {
        return segment15;
    }

    /**
     * @param segment15 the segment15 to set
     */
    public void setSegment15(String segment15) {
        this.segment15 = segment15;
    }

    /**
     * @return the segment16
     */
    public String getSegment16() {
        return segment16;
    }

    /**
     * @param segment16 the segment16 to set
     */
    public void setSegment16(String segment16) {
        this.segment16 = segment16;
    }

    /**
     * @return the segment17
     */
    public String getSegment17() {
        return segment17;
    }

    /**
     * @param segment17 the segment17 to set
     */
    public void setSegment17(String segment17) {
        this.segment17 = segment17;
    }

    /**
     * @return the segment18
     */
    public String getSegment18() {
        return segment18;
    }

    /**
     * @param segment18 the segment18 to set
     */
    public void setSegment18(String segment18) {
        this.segment18 = segment18;
    }

    /**
     * @return the segment19
     */
    public String getSegment19() {
        return segment19;
    }

    /**
     * @param segment19 the segment19 to set
     */
    public void setSegment19(String segment19) {
        this.segment19 = segment19;
    }

    /**
     * @return the segment20
     */
    public String getSegment20() {
        return segment20;
    }

    /**
     * @param segment20 the segment20 to set
     */
    public void setSegment20(String segment20) {
        this.segment20 = segment20;
    }

    /**
     * @return the segment21
     */
    public String getSegment21() {
        return segment21;
    }

    /**
     * @param segment21 the segment21 to set
     */
    public void setSegment21(String segment21) {
        this.segment21 = segment21;
    }

    /**
     * @return the segment22
     */
    public String getSegment22() {
        return segment22;
    }

    /**
     * @param segment22 the segment22 to set
     */
    public void setSegment22(String segment22) {
        this.segment22 = segment22;
    }

    /**
     * @return the segment23
     */
    public String getSegment23() {
        return segment23;
    }

    /**
     * @param segment23 the segment23 to set
     */
    public void setSegment23(String segment23) {
        this.segment23 = segment23;
    }

    /**
     * @return the segment24
     */
    public String getSegment24() {
        return segment24;
    }

    /**
     * @param segment24 the segment24 to set
     */
    public void setSegment24(String segment24) {
        this.segment24 = segment24;
    }

    /**
     * @return the segment25
     */
    public String getSegment25() {
        return segment25;
    }

    /**
     * @param segment25 the segment25 to set
     */
    public void setSegment25(String segment25) {
        this.segment25 = segment25;
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
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the oraJava
     */
    public static Map<String, String> getOraJava() {
        return oraJava;
    }

    /**
     * @param aOraJava the oraJava to set
     */
    public static void setOraJava(Map<String, String> aOraJava) {
        oraJava = aOraJava;
    }

    /**
     * @return the resultCount
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * @param resultCount the resultCount to set
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }


    public Integer getAeHeaderId() {
        return aeHeaderId;
    }

    public void setAeHeaderId(Integer aeHeaderId) {
        this.aeHeaderId = aeHeaderId;
    }

    public Integer getJeHeaderId() {
        return jeHeaderId;
    }

    public void setJeHeaderId(Integer jeHeaderId) {
        this.jeHeaderId = jeHeaderId;
    }
}
