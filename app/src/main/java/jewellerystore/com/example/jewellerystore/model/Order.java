package jewellerystore.com.example.jewellerystore.model;

import java.util.Date;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Order {
    private Long id;
    private Long customerId;
    private Date orderDate;

    public Order() {
    }

    public Order(Long customerId, Date orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public Order(Long id, Long customerId, Date orderDate) {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
