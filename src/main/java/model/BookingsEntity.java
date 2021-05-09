package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bookings", schema = "travelexperts", catalog = "")
public class BookingsEntity {
    private int bookingId;
    private Timestamp bookingDate;
    private String bookingNo;
    private Double travelerCount;
    private Integer customerId;
    private String tripTypeId;
    private Integer packageId;

    @Id
    @Column(name = "BookingId")
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Basic
    @Column(name = "BookingDate")
    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Basic
    @Column(name = "BookingNo")
    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    @Basic
    @Column(name = "TravelerCount")
    public Double getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(Double travelerCount) {
        this.travelerCount = travelerCount;
    }

    @Basic
    @Column(name = "CustomerId")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "TripTypeId")
    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    @Basic
    @Column(name = "PackageId")
    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingsEntity that = (BookingsEntity) o;

        if (bookingId != that.bookingId) return false;
        if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null) return false;
        if (bookingNo != null ? !bookingNo.equals(that.bookingNo) : that.bookingNo != null) return false;
        if (travelerCount != null ? !travelerCount.equals(that.travelerCount) : that.travelerCount != null)
            return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (tripTypeId != null ? !tripTypeId.equals(that.tripTypeId) : that.tripTypeId != null) return false;
        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingId;
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        result = 31 * result + (bookingNo != null ? bookingNo.hashCode() : 0);
        result = 31 * result + (travelerCount != null ? travelerCount.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (tripTypeId != null ? tripTypeId.hashCode() : 0);
        result = 31 * result + (packageId != null ? packageId.hashCode() : 0);
        return result;
    }
}
