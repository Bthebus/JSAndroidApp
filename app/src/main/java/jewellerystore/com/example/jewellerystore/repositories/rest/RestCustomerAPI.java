package jewellerystore.com.example.jewellerystore.repositories.rest;

import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Customer;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */

/**
 * REMOVE ALL COMMENTS AND RETURN RELEVANT OBJECTS, NOT NULL!
 * */
public class RestCustomerAPI implements RestAPI<Customer, Long>{

    final String BASE_URL="http://localhost:8080/api";

    // final HttpHeaders requestHeaders = RestMethods.getHeaders();
    // final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Customer get(Long id) {
        final String url = BASE_URL+"customer/"+id.toString();
      //  HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(requestHeaders);
      //  ResponseEntity<Customer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer.class);
      //  Customer customer = responseEntity.getBody();
      //  return customer;
        return null;
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
    public String delete(Customer entity) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
