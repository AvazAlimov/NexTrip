package Classes;

public class Location {
    private String country;
    private String region;
    private String city;
    private String district;
    private String street;
    private String home;

    public Location(String country, String region, String city, String district, String street, String home) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.district = district;
        this.street = street;
        this.home = home;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
