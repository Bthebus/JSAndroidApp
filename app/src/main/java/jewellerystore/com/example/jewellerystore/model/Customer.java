package jewellerystore.com.example.jewellerystore.model;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Customer {
    private Long id;
    private Name name;
    private String companyName;
    private ContactInformation contactInformation;
    private String address;

    public Customer() {
    }

    public Customer(Name name, String companyName, ContactInformation contactInformation, String address) {
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
        this.companyName = companyName;
    }

    public Customer(Long id, Name name, String companyName, ContactInformation contactInformation, String address) {
        this.id = id;
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
