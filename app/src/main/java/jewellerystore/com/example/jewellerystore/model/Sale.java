package jewellerystore.com.example.jewellerystore.model;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class Sale {

    private Long transction_number;
    private Long orderId;

    public Sale() {
    }

    public Sale(Long transction_number, Long orderId) {
        this.transction_number = transction_number;
        this.orderId = orderId;
    }

    public Long getTransction_number() {
        return transction_number;
    }

    public void setTransction_number(Long transction_number) {
        this.transction_number = transction_number;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
