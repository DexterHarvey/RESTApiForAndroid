package model;

import javax.persistence.*;

@Entity
@Table(name = "pkg_destinations", schema = "travelexperts")
public class PkgDestinationsEntity {
    private int pkgDestinationId;
    private int PackageId;
    private double Latitude;
    private double Longitude;
    private String Name;
    private String Country;
    private String Description;

    @Id
    @Column(name = "PkgDestinationId", nullable = false)
    public int getPkgDestinationId() {
        return pkgDestinationId;
    }

    public void setPkgDestinationId(int pkgDestinationId) {
        this.pkgDestinationId = pkgDestinationId;
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

    @Basic
    @Column(name = "Country", nullable = false, length = 50)
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Basic
    @Column(name = "Description", nullable = false, length = 1000)
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PkgDestinationsEntity that = (PkgDestinationsEntity) o;

        if (pkgDestinationId != that.pkgDestinationId) return false;
        if (PackageId != that.PackageId) return false;
        if (Double.compare(that.Latitude, Latitude) != 0) return false;
        if (Double.compare(that.Longitude, Longitude) != 0) return false;
        if (Name != null ? !Name.equals(that.Name) : that.Name != null) return false;
        if (Country != null ? !Country.equals(that.Country) : that.Country != null) return false;
        if (Description != null ? !Description.equals(that.Description) : that.Description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = pkgDestinationId;
        result = 31 * result + PackageId;
        temp = Double.doubleToLongBits(Latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (Country != null ? Country.hashCode() : 0);
        result = 31 * result + (Description != null ? Description.hashCode() : 0);
        return result;
    }
}
