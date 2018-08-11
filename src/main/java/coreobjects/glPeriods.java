/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author facet
 */
@Entity (name="periods")
@Table(name="apps.gl_periods")
public class glPeriods implements Serializable {
    @Id
    @Column (name="PERIOD_NAME")
    private String periodName="";
    @Column (name="START_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column (name="END_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @Column (name="PERIOD_YEAR")
    private Integer periodYear;
    @Column (name="PERIOD_NUM")
    private Integer periodNum;
    @Column (name="ADJUSTMENT_PERIOD_FLAG")
    private String adjustmentPeriodFlag;

    public void clear(){
        this.periodName=null;
        this.startDate=null;
        this.endDate=null;
        this.periodYear=null;
        this.periodNum=null;
        this.adjustmentPeriodFlag=null;
    }

    /**
     * @return the periodName
     */
    public String getPeriodName() {
        return periodName;
    }

    /**
     * @param periodName the periodName to set
     */
    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the periodYear
     */
    public Integer getPeriodYear() {
        return periodYear;
    }

    /**
     * @param periodYear the periodYear to set
     */
    public void setPeriodYear(Integer periodYear) {
        this.periodYear = periodYear;
    }

    /**
     * @return the periodNum
     */
    public Integer getPeriodNum() {
        return periodNum;
    }

    /**
     * @param periodNum the periodNum to set
     */
    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    /**
     * @return the adjustmentPeriodFlag
     */
    public String getAdjustmentPeriodFlag() {
        return adjustmentPeriodFlag;
    }

    /**
     * @param adjustmentPeriodFlag the adjustmentPeriodFlag to set
     */
    public void setAdjustmentPeriodFlag(String adjustmentPeriodFlag) {
        this.adjustmentPeriodFlag = adjustmentPeriodFlag;
    }
    
    @Override
    public String toString() {
        return periodName;
    }
    
}
