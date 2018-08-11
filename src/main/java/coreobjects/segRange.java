/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author facet
 */
@Entity
public class segRange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String segment1Hi;
    private String segment1Lo;
 
    private String segment2Hi;
    private String segment2Lo;  
    
    private String segment3Hi;
    private String segment3Lo;
    
    private String segment4Hi;
    private String segment4Lo;
    
    private String segment5Hi;
    private String segment5Lo;
    
    private String segment6Hi;
    private String segment6Lo;
    
    private String segment7Hi;
    private String segment7Lo;
    
    private String segment8Hi;
    private String segment8Lo;
    
    private String segment9Hi;
    private String segment9Lo;
    
    private String segment10Hi;
    private String segment10Lo;
    
    private String segment11Hi;
    private String segment11Lo;
    
    private String segment12Hi;
    private String segment12Lo;    
    
    private String segment13Hi;
    private String segment13Lo;
    
    private String segment14Hi;
    private String segment14Lo;
    
    private String segment15Hi;
    private String segment15Lo;
    
    private String segment16Hi;
    private String segment16Lo;
    
    private String segment17Hi;
    private String segment17Lo;
    
    private String segment18Hi;
    private String segment18Lo;
    
    private String segment19Hi;
    private String segment19Lo;
    
    private String segment20Hi;
    private String segment20Lo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the segment1Hi
     */
    public String getSegment1Hi() {
        return segment1Hi;
    }

    /**
     * @param segment1Hi the segment1Hi to set
     */
    public void setSegment1Hi(String segment1Hi) {
        this.segment1Hi = segment1Hi;
    }

    /**
     * @return the segment1Lo
     */
    public String getSegment1Lo() {
        return segment1Lo;
    }

    /**
     * @param segment1Lo the segment1Lo to set
     */
    public void setSegment1Lo(String segment1Lo) {
        this.segment1Lo = segment1Lo;
    }

    /**
     * @return the segment2Hi
     */
    public String getSegment2Hi() {
        return segment2Hi;
    }

    /**
     * @param segment2Hi the segment2Hi to set
     */
    public void setSegment2Hi(String segment2Hi) {
        this.segment2Hi = segment2Hi;
    }

    /**
     * @return the segment2Lo
     */
    public String getSegment2Lo() {
        return segment2Lo;
    }

    /**
     * @param segment2Lo the segment2Lo to set
     */
    public void setSegment2Lo(String segment2Lo) {
        this.segment2Lo = segment2Lo;
    }

    /**
     * @return the segment3Hi
     */
    public String getSegment3Hi() {
        return segment3Hi;
    }

    /**
     * @param segment3Hi the segment3Hi to set
     */
    public void setSegment3Hi(String segment3Hi) {
        this.segment3Hi = segment3Hi;
    }

    /**
     * @return the segment3Lo
     */
    public String getSegment3Lo() {
        return segment3Lo;
    }

    /**
     * @param segment3Lo the segment3Lo to set
     */
    public void setSegment3Lo(String segment3Lo) {
        this.segment3Lo = segment3Lo;
    }

    /**
     * @return the segment4Hi
     */
    public String getSegment4Hi() {
        return segment4Hi;
    }

    /**
     * @param segment4Hi the segment4Hi to set
     */
    public void setSegment4Hi(String segment4Hi) {
        this.segment4Hi = segment4Hi;
    }

    /**
     * @return the segment4Lo
     */
    public String getSegment4Lo() {
        return segment4Lo;
    }

    /**
     * @param segment4Lo the segment4Lo to set
     */
    public void setSegment4Lo(String segment4Lo) {
        this.segment4Lo = segment4Lo;
    }

    /**
     * @return the segment5Hi
     */
    public String getSegment5Hi() {
        return segment5Hi;
    }

    /**
     * @param segment5Hi the segment5Hi to set
     */
    public void setSegment5Hi(String segment5Hi) {
        this.segment5Hi = segment5Hi;
    }

    /**
     * @return the segment5Lo
     */
    public String getSegment5Lo() {
        return segment5Lo;
    }

    /**
     * @param segment5Lo the segment5Lo to set
     */
    public void setSegment5Lo(String segment5Lo) {
        this.segment5Lo = segment5Lo;
    }

    /**
     * @return the segment6Hi
     */
    public String getSegment6Hi() {
        return segment6Hi;
    }

    /**
     * @param segment6Hi the segment6Hi to set
     */
    public void setSegment6Hi(String segment6Hi) {
        this.segment6Hi = segment6Hi;
    }

    /**
     * @return the segment6Lo
     */
    public String getSegment6Lo() {
        return segment6Lo;
    }

    /**
     * @param segment6Lo the segment6Lo to set
     */
    public void setSegment6Lo(String segment6Lo) {
        this.segment6Lo = segment6Lo;
    }

    /**
     * @return the segment7Hi
     */
    public String getSegment7Hi() {
        return segment7Hi;
    }

    /**
     * @param segment7Hi the segment7Hi to set
     */
    public void setSegment7Hi(String segment7Hi) {
        this.segment7Hi = segment7Hi;
    }

    /**
     * @return the segment7Lo
     */
    public String getSegment7Lo() {
        return segment7Lo;
    }

    /**
     * @param segment7Lo the segment7Lo to set
     */
    public void setSegment7Lo(String segment7Lo) {
        this.segment7Lo = segment7Lo;
    }

    /**
     * @return the segment8Hi
     */
    public String getSegment8Hi() {
        return segment8Hi;
    }

    /**
     * @param segment8Hi the segment8Hi to set
     */
    public void setSegment8Hi(String segment8Hi) {
        this.segment8Hi = segment8Hi;
    }

    /**
     * @return the segment8Lo
     */
    public String getSegment8Lo() {
        return segment8Lo;
    }

    /**
     * @param segment8Lo the segment8Lo to set
     */
    public void setSegment8Lo(String segment8Lo) {
        this.segment8Lo = segment8Lo;
    }

    /**
     * @return the segment9Hi
     */
    public String getSegment9Hi() {
        return segment9Hi;
    }

    /**
     * @param segment9Hi the segment9Hi to set
     */
    public void setSegment9Hi(String segment9Hi) {
        this.segment9Hi = segment9Hi;
    }

    /**
     * @return the segment9Lo
     */
    public String getSegment9Lo() {
        return segment9Lo;
    }

    /**
     * @param segment9Lo the segment9Lo to set
     */
    public void setSegment9Lo(String segment9Lo) {
        this.segment9Lo = segment9Lo;
    }

    /**
     * @return the segment10Hi
     */
    public String getSegment10Hi() {
        return segment10Hi;
    }

    /**
     * @param segment10Hi the segment10Hi to set
     */
    public void setSegment10Hi(String segment10Hi) {
        this.segment10Hi = segment10Hi;
    }

    /**
     * @return the segment10Lo
     */
    public String getSegment10Lo() {
        return segment10Lo;
    }

    /**
     * @param segment10Lo the segment10Lo to set
     */
    public void setSegment10Lo(String segment10Lo) {
        this.segment10Lo = segment10Lo;
    }

    /**
     * @return the segment11Hi
     */
    public String getSegment11Hi() {
        return segment11Hi;
    }

    /**
     * @param segment11Hi the segment11Hi to set
     */
    public void setSegment11Hi(String segment11Hi) {
        this.segment11Hi = segment11Hi;
    }

    /**
     * @return the segment11Lo
     */
    public String getSegment11Lo() {
        return segment11Lo;
    }

    /**
     * @param segment11Lo the segment11Lo to set
     */
    public void setSegment11Lo(String segment11Lo) {
        this.segment11Lo = segment11Lo;
    }

    /**
     * @return the segment12Hi
     */
    public String getSegment12Hi() {
        return segment12Hi;
    }

    /**
     * @param segment12Hi the segment12Hi to set
     */
    public void setSegment12Hi(String segment12Hi) {
        this.segment12Hi = segment12Hi;
    }

    /**
     * @return the segment12Lo
     */
    public String getSegment12Lo() {
        return segment12Lo;
    }

    /**
     * @param segment12Lo the segment12Lo to set
     */
    public void setSegment12Lo(String segment12Lo) {
        this.segment12Lo = segment12Lo;
    }

    /**
     * @return the segment13Hi
     */
    public String getSegment13Hi() {
        return segment13Hi;
    }

    /**
     * @param segment13Hi the segment13Hi to set
     */
    public void setSegment13Hi(String segment13Hi) {
        this.segment13Hi = segment13Hi;
    }

    /**
     * @return the segment13Lo
     */
    public String getSegment13Lo() {
        return segment13Lo;
    }

    /**
     * @param segment13Lo the segment13Lo to set
     */
    public void setSegment13Lo(String segment13Lo) {
        this.segment13Lo = segment13Lo;
    }

    /**
     * @return the segment14Hi
     */
    public String getSegment14Hi() {
        return segment14Hi;
    }

    /**
     * @param segment14Hi the segment14Hi to set
     */
    public void setSegment14Hi(String segment14Hi) {
        this.segment14Hi = segment14Hi;
    }

    /**
     * @return the segment14Lo
     */
    public String getSegment14Lo() {
        return segment14Lo;
    }

    /**
     * @param segment14Lo the segment14Lo to set
     */
    public void setSegment14Lo(String segment14Lo) {
        this.segment14Lo = segment14Lo;
    }

    /**
     * @return the segment15Hi
     */
    public String getSegment15Hi() {
        return segment15Hi;
    }

    /**
     * @param segment15Hi the segment15Hi to set
     */
    public void setSegment15Hi(String segment15Hi) {
        this.segment15Hi = segment15Hi;
    }

    /**
     * @return the segment15Lo
     */
    public String getSegment15Lo() {
        return segment15Lo;
    }

    /**
     * @param segment15Lo the segment15Lo to set
     */
    public void setSegment15Lo(String segment15Lo) {
        this.segment15Lo = segment15Lo;
    }

    /**
     * @return the segment16Hi
     */
    public String getSegment16Hi() {
        return segment16Hi;
    }

    /**
     * @param segment16Hi the segment16Hi to set
     */
    public void setSegment16Hi(String segment16Hi) {
        this.segment16Hi = segment16Hi;
    }

    /**
     * @return the segment16Lo
     */
    public String getSegment16Lo() {
        return segment16Lo;
    }

    /**
     * @param segment16Lo the segment16Lo to set
     */
    public void setSegment16Lo(String segment16Lo) {
        this.segment16Lo = segment16Lo;
    }

    /**
     * @return the segment17Hi
     */
    public String getSegment17Hi() {
        return segment17Hi;
    }

    /**
     * @param segment17Hi the segment17Hi to set
     */
    public void setSegment17Hi(String segment17Hi) {
        this.segment17Hi = segment17Hi;
    }

    /**
     * @return the segment17Lo
     */
    public String getSegment17Lo() {
        return segment17Lo;
    }

    /**
     * @param segment17Lo the segment17Lo to set
     */
    public void setSegment17Lo(String segment17Lo) {
        this.segment17Lo = segment17Lo;
    }

    /**
     * @return the segment18Hi
     */
    public String getSegment18Hi() {
        return segment18Hi;
    }

    /**
     * @param segment18Hi the segment18Hi to set
     */
    public void setSegment18Hi(String segment18Hi) {
        this.segment18Hi = segment18Hi;
    }

    /**
     * @return the segment18Lo
     */
    public String getSegment18Lo() {
        return segment18Lo;
    }

    /**
     * @param segment18Lo the segment18Lo to set
     */
    public void setSegment18Lo(String segment18Lo) {
        this.segment18Lo = segment18Lo;
    }

    /**
     * @return the segment19Hi
     */
    public String getSegment19Hi() {
        return segment19Hi;
    }

    /**
     * @param segment19Hi the segment19Hi to set
     */
    public void setSegment19Hi(String segment19Hi) {
        this.segment19Hi = segment19Hi;
    }

    /**
     * @return the segment19Lo
     */
    public String getSegment19Lo() {
        return segment19Lo;
    }

    /**
     * @param segment19Lo the segment19Lo to set
     */
    public void setSegment19Lo(String segment19Lo) {
        this.segment19Lo = segment19Lo;
    }

    /**
     * @return the segment20Hi
     */
    public String getSegment20Hi() {
        return segment20Hi;
    }

    /**
     * @param segment20Hi the segment20Hi to set
     */
    public void setSegment20Hi(String segment20Hi) {
        this.segment20Hi = segment20Hi;
    }

    /**
     * @return the segment20Lo
     */
    public String getSegment20Lo() {
        return segment20Lo;
    }

    /**
     * @param segment20Lo the segment20Lo to set
     */
    public void setSegment20Lo(String segment20Lo) {
        this.segment20Lo = segment20Lo;
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
        if (!(object instanceof segRange)) {
            return false;
        }
        segRange other = (segRange) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "coreobjects.segRange[ id=" + getId() + " ]";
    }
    
}
