package jewellerystore.com.example.jewellerystore.model;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Supplier {
    private Long id;
    private String supplierName;
    private Long itemId;
    private String type;

    public Supplier() {
    }

    public Supplier(String supplierName, Long itemId, String type) {
        this.supplierName = supplierName;
        this.itemId = itemId;
        this.type = type;
    }

    public Supplier(Long id, String supplierName, Long itemId, String type) {
        this.id = id;
        this.supplierName = supplierName;
        this.itemId = itemId;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
