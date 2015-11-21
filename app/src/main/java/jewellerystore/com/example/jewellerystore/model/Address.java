package jewellerystore.com.example.jewellerystore.model;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Address {

    private String streetNumber;
    private String streetName;
    private String town;
    private String province;
    private String postalCode;

    public Address(){}

    public Address(String streetNumber, String streetName, String town, String province, String postalCode){
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.town = town;
        this.province = province;
        this.postalCode = postalCode;
    }


    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
