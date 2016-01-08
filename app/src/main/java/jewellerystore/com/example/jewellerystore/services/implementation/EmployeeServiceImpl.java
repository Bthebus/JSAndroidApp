package jewellerystore.com.example.jewellerystore.services.implementation;

import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Employee;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;
import jewellerystore.com.example.jewellerystore.repositories.rest.RestEmployeeAPI;
import jewellerystore.com.example.jewellerystore.services.EmployeeService;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class EmployeeServiceImpl implements EmployeeService{

    final RestAPI<Employee, Long> rest = new RestEmployeeAPI();

    @Override
    public Employee findById(Long id)
    {
        return rest.get(id);
    }

    @Override
    public String save(Employee entity)
    {
        return rest.post(entity);
    }

    @Override
    public String update(Employee entity)
    {
        return rest.put(entity);
    }

    @Override
    public void delete(Employee entity)
    {
        rest.delete(entity);
    }

    @Override
    public List<Employee> findAll()
    {
        return rest.getAll();
    }
}
