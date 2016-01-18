package jewellerystore.com.example.jewellerystore.services.implementation;

import java.util.List;

import jewellerystore.com.example.jewellerystore.model.OrderLine;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;
import jewellerystore.com.example.jewellerystore.repositories.rest.RestOrderlineAPI;
import jewellerystore.com.example.jewellerystore.services.OrderlineService;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class OrderlineServiceImpl implements OrderlineService{

    final RestAPI<OrderLine, Long> rest = new RestOrderlineAPI();

    @Override
    public OrderLine findById(Long id)
    {
        return rest.get(id);
    }

    public String save(OrderLine entity)
    {
        return "hello";
    }

    public String update(OrderLine entity)
    {
        return "hello";
    }

    public void delete(OrderLine entity)
    {

    }
    @Override
    public List<OrderLine> findAll()
    {
        return rest.getAll();
    }
}
