package jewellerystore.com.example.jewellerystore.services.implementation;

import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Orders;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;
import jewellerystore.com.example.jewellerystore.repositories.rest.RestOrderAPI;
import jewellerystore.com.example.jewellerystore.services.OrderService;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class OrderServiceImpl implements OrderService{

    final RestAPI<Orders, Long> rest = new RestOrderAPI();

    @Override
    public Orders findById(Long id)
    {
        return rest.get(id);
    }

    public String save(Orders entity)
    {
        return "hello";
    }

    public String update(Orders entity)
    {
        return "hello";
    }

    public void delete(Orders entity)
    {

    }
    @Override
    public List<Orders> findAll()
    {
        return rest.getAll();
    }
}
