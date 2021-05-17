package model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Booking db entity class by Dexter.
 */

@Entity
@Table(name = "fees", schema = "travelexperts", catalog = "")
public class FeesEntity {
    private String feeId;
    private String feeName;
    private BigDecimal feeAmt;
    private String feeDesc;

    @Id
    @Column(name = "FeeId")
    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    @Basic
    @Column(name = "FeeName")
    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    @Basic
    @Column(name = "FeeAmt")
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    @Basic
    @Column(name = "FeeDesc")
    public String getFeeDesc() {
        return feeDesc;
    }

    public void setFeeDesc(String feeDesc) {
        this.feeDesc = feeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeesEntity that = (FeesEntity) o;

        if (feeId != null ? !feeId.equals(that.feeId) : that.feeId != null) return false;
        if (feeName != null ? !feeName.equals(that.feeName) : that.feeName != null) return false;
        if (feeAmt != null ? !feeAmt.equals(that.feeAmt) : that.feeAmt != null) return false;
        if (feeDesc != null ? !feeDesc.equals(that.feeDesc) : that.feeDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = feeId != null ? feeId.hashCode() : 0;
        result = 31 * result + (feeName != null ? feeName.hashCode() : 0);
        result = 31 * result + (feeAmt != null ? feeAmt.hashCode() : 0);
        result = 31 * result + (feeDesc != null ? feeDesc.hashCode() : 0);
        return result;
    }
}
