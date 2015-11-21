package jewellerystore.com.example.jewellerystore.model;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Customer {
    private Long id;
    private Name name;
    private ContactInformation contactInformation;
    private Address address;

    public Customer() {
    }

    public Customer(Name name, ContactInformation contactInformation, Address address) {
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
    }

    public Customer(Long id, Name name, ContactInformation contactInformation, Address address) {
        this.id = id;
        this.name = name;
        this.contactInformation = contactInformation;
        this.address = address;
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

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
