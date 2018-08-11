package coreobjects;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by facet on 12/07/16.
 */
@Entity
public class slaLines implements Serializable {
    @Id
    @Column(name="rowid")
    private String rowid;
    @Column(name = "event_type_code")
    private String eventTypeCode;
    @Column(name = "accounting_date")
    @Temporal(TemporalType.DATE)
    private Date accountingDate;
    @Column(name = "gl_transfer_status_code")
    private String glTransferStatusCode;
    @Column(name = "ae_line_num")
    private Integer aeLineNum;
    @Column(name = "accounting_class_code")
    private String accountingClassCode;
    @Column(name = "entered_dr")
    private BigDecimal enteredDr;
    @Column(name = "entered_cr")
    private BigDecimal enteredCr;
    @Column(name = "accounted_dr")
    private BigDecimal accountedDr;
    @Column(name = "accounted_cr")
    private BigDecimal accountedCr;
    // Conversion
    @Column(name="currency_code")
    private String currency;
    @Column(name = "currency_conversion_date")
    @Temporal(TemporalType.DATE)
    private Date currencyConversionDate;
    @Column(name = "currency_conversion_type")
    private String currencyConversionType;
    @Column(name = "currency_conversion_rate")
    private BigDecimal currencyConversionRate;
    @Column(name = "party")
    private String party;
    @Column(name = "party_site")
    private String partySite;
    @Column(name = "h_description")
    private String hDescription;
    @Column(name = "l_description")
    private String lDescription;
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

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getEventTypeCode() {
        return eventTypeCode;
    }

    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getGlTransferStatusCode() {
        return glTransferStatusCode;
    }

    public void setGlTransferStatusCode(String glTransferStatusCode) {
        this.glTransferStatusCode = glTransferStatusCode;
    }

    public Integer getAeLineNum() {
        return aeLineNum;
    }

    public void setAeLineNum(Integer aeLineNum) {
        this.aeLineNum = aeLineNum;
    }

    public String getAccountingClassCode() {
        return accountingClassCode;
    }

    public void setAccountingClassCode(String accountingClassCode) {
        this.accountingClassCode = accountingClassCode;
    }

    public BigDecimal getEnteredDr() {
        return enteredDr;
    }

    public void setEnteredDr(BigDecimal enteredDr) {
        this.enteredDr = enteredDr;
    }

    public BigDecimal getEnteredCr() {
        return enteredCr;
    }

    public void setEnteredCr(BigDecimal enteredCr) {
        this.enteredCr = enteredCr;
    }

    public BigDecimal getAccountedDr() {
        return accountedDr;
    }

    public void setAccountedDr(BigDecimal accountedDr) {
        this.accountedDr = accountedDr;
    }

    public BigDecimal getAccountedCr() {
        return accountedCr;
    }

    public void setAccountedCr(BigDecimal accountedCr) {
        this.accountedCr = accountedCr;
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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPartySite() {
        return partySite;
    }

    public void setPartySite(String partySite) {
        this.partySite = partySite;
    }

    public String gethDescription() {
        return hDescription;
    }

    public void sethDescription(String hDescription) {
        this.hDescription = hDescription;
    }

    public String getlDescription() {
        return lDescription;
    }

    public void setlDescription(String lDescription) {
        this.lDescription = lDescription;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getSegment2() {
        return segment2;
    }

    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    public String getSegment3() {
        return segment3;
    }

    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    public String getSegment4() {
        return segment4;
    }

    public void setSegment4(String segment4) {
        this.segment4 = segment4;
    }

    public String getSegment5() {
        return segment5;
    }

    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }

    public String getSegment6() {
        return segment6;
    }

    public void setSegment6(String segment6) {
        this.segment6 = segment6;
    }

    public String getSegment7() {
        return segment7;
    }

    public void setSegment7(String segment7) {
        this.segment7 = segment7;
    }

    public String getSegment8() {
        return segment8;
    }

    public void setSegment8(String segment8) {
        this.segment8 = segment8;
    }

    public String getSegment9() {
        return segment9;
    }

    public void setSegment9(String segment9) {
        this.segment9 = segment9;
    }

    public String getSegment10() {
        return segment10;
    }

    public void setSegment10(String segment10) {
        this.segment10 = segment10;
    }

    public String getSegment11() {
        return segment11;
    }

    public void setSegment11(String segment11) {
        this.segment11 = segment11;
    }

    public String getSegment12() {
        return segment12;
    }

    public void setSegment12(String segment12) {
        this.segment12 = segment12;
    }

    public String getSegment13() {
        return segment13;
    }

    public void setSegment13(String segment13) {
        this.segment13 = segment13;
    }

    public String getSegment14() {
        return segment14;
    }

    public void setSegment14(String segment14) {
        this.segment14 = segment14;
    }

    public String getSegment15() {
        return segment15;
    }

    public void setSegment15(String segment15) {
        this.segment15 = segment15;
    }

    public String getSegment16() {
        return segment16;
    }

    public void setSegment16(String segment16) {
        this.segment16 = segment16;
    }

    public String getSegment17() {
        return segment17;
    }

    public void setSegment17(String segment17) {
        this.segment17 = segment17;
    }

    public String getSegment18() {
        return segment18;
    }

    public void setSegment18(String segment18) {
        this.segment18 = segment18;
    }

    public String getSegment19() {
        return segment19;
    }

    public void setSegment19(String segment19) {
        this.segment19 = segment19;
    }

    public String getSegment20() {
        return segment20;
    }

    public void setSegment20(String segment20) {
        this.segment20 = segment20;
    }

    public String getSegment21() {
        return segment21;
    }

    public void setSegment21(String segment21) {
        this.segment21 = segment21;
    }

    public String getSegment22() {
        return segment22;
    }

    public void setSegment22(String segment22) {
        this.segment22 = segment22;
    }

    public String getSegment23() {
        return segment23;
    }

    public void setSegment23(String segment23) {
        this.segment23 = segment23;
    }

    public String getSegment24() {
        return segment24;
    }

    public void setSegment24(String segment24) {
        this.segment24 = segment24;
    }

    public String getSegment25() {
        return segment25;
    }

    public void setSegment25(String segment25) {
        this.segment25 = segment25;
    }
}
