package jewellerystore.com.example.jewellerystore.repositories.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Employee;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class RestEmployeeAPI implements RestAPI<Employee, Long>{

    final String BASE_URL = "http://10.0.2.2:8080/Employee/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Employee get(Long id)
    {
        final String url = BASE_URL + id.toString();
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(requestHeaders);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity, Employee.class);
        Employee employee = responseEntity.getBody();
        return employee;
    }

    /*@Override
    public Employee getByUsername(String username)
    {
        final String url = BASE_URL + "username/" + username;
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(requestHeaders);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity, Employee.class);
        Employee employee = responseEntity.getBody();
        return employee;
    }*/

    @Override
    public String post(Employee employee)
    {
        final String url = BASE_URL + "create/";
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(Employee employee)
    {
        final String url = BASE_URL + "update/" + employee.getId().toString();
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public void delete(Employee employee)
    {
        final String url = BASE_URL + "delete/" + employee.getId().toString();
        restTemplate.delete(url,employee.getId());

    }

    @Override
    public List<Employee> getAll()
    {
        List<Employee> employeeList = new ArrayList<>();
        final String url = BASE_URL + "all";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Employee[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Employee[].class);

        if(responseEntity.hasBody()) {
            Employee[] resultSet = responseEntity.getBody();

            for (Employee employee : resultSet) {
                employeeList.add(employee);
            }
            return employeeList;
        }

        else
        {
            return null;
        }
    }
}
