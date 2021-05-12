package model;

import javax.persistence.*;

@Entity
@Table(name = "coordinates", schema = "travelexperts", catalog = "")
public class CoordinatesEntity {
    private int CoordinateId;
    private int PackageId;
    private double Latitude;
    private double Longitude;
    private String Name;

    @Id
    @Column(name = "CoordinateId", nullable = false)
    public int getCoordinateId() {
        return CoordinateId;
    }

    public void setCoordinateId(int coordinateId) {
        CoordinateId = coordinateId;
    }

    @Basic
    @Column(name = "PackageId", nullable = false)
    public int getPackageId() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        PackageId = packageId;
    }

    @Basic
    @Column(name = "Latitude", nullable = false, precision = 0)
    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    @Basic
    @Column(name = "Longitude", nullable = false, precision = 0)
    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 50)
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinatesEntity that = (CoordinatesEntity) o;

        if (CoordinateId != that.CoordinateId) return false;
        if (PackageId != that.PackageId) return false;
        if (Double.compare(that.Latitude, Latitude) != 0) return false;
        if (Double.compare(that.Longitude, Longitude) != 0) return false;
        if (Name != null ? !Name.equals(that.Name) : that.Name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = CoordinateId;
        result = 31 * result + PackageId;
        temp = Double.doubleToLongBits(Latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        return result;
    }
}
