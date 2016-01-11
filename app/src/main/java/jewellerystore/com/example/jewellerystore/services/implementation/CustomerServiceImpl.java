package jewellerystore.com.example.jewellerystore.services.implementation;

import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Customer;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;
import jewellerystore.com.example.jewellerystore.repositories.RestCustomer;
import jewellerystore.com.example.jewellerystore.repositories.rest.RestCustomerAPI;
import jewellerystore.com.example.jewellerystore.services.CustomerService;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class CustomerServiceImpl implements CustomerService{

    final RestAPI<Customer, Long> rest = new RestCustomerAPI();

    @Override
    public Customer findById(Long id)
    {
        return rest.get(id);
    }

    @Override
    public String save(Customer entity)
    {
        return rest.post(entity);
    }

    @Override
    public String update(Customer entity)
    {
        return rest.put(entity);
    }

    @Override
    public void delete(Customer entity)
    {
        rest.delete(entity);
    }

    @Override
    public List<Customer> findAll()
    {
        return rest.getAll();
    }
}
