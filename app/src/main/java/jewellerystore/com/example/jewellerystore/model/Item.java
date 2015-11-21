package jewellerystore.com.example.jewellerystore.model;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Item {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity_on_hand;

    public Item() {
    }

    public Item(String name, String description, double price, int quantity_on_hand) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity_on_hand = quantity_on_hand;
    }

    public Item(Long id, String name, String description, double price, int quantity_on_hand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity_on_hand = quantity_on_hand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity_on_hand() {
        return quantity_on_hand;
    }

    public void setQuantity_on_hand(int quantity_on_hand) {
        this.quantity_on_hand = quantity_on_hand;
    }
}
