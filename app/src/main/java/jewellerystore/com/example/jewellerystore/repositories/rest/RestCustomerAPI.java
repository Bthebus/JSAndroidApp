package jewellerystore.com.example.jewellerystore.repositories.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Customer;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;
import jewellerystore.com.example.jewellerystore.repositories.RestCustomer;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */

/**
 * REMOVE ALL COMMENTS AND RETURN RELEVANT OBJECTS, NOT NULL!
 * */
public class RestCustomerAPI implements RestAPI<Customer,Long> {

    final String BASE_URL = "http://10.0.2.2:8080/customer/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Customer get(Long id) {
        final String url = BASE_URL+"customer/"+id.toString();
        HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(requestHeaders);
        ResponseEntity<Customer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer.class);
        Customer customer = responseEntity.getBody();
        return customer;
    }

    @Override
    public String post(Customer entity) {
       //no create customer method in API
        return null;
    }

    @Override
    public String put(Customer entity) {

        //no update method in backend
        return null;
    }

    @Override
    public void delete(Customer entity) {
        //return null;
    }

    @Override
    public List<Customer> getAll() {

        List<Customer> customerList = new ArrayList<>();
        final String url = BASE_URL + "all";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer[].class);

        if(responseEntity.hasBody()) {
            Customer[] resultSet = responseEntity.getBody();

            for (Customer customer : resultSet) {
                customerList.add(customer);
            }
            return customerList;
        }

        else
        {
            return null;
        }

    }

    
}
