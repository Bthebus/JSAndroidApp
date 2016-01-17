package jewellerystore.com.example.jewellerystore.model;

import java.util.Date;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Orders {
    private Long id;
    private Long customerId;
    private String orderDate;

    public Orders() {
    }

    public Orders(Long customerId, String orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public Orders(Long id, Long customerId, String orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
