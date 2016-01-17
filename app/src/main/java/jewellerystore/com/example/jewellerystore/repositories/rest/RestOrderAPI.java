package jewellerystore.com.example.jewellerystore.repositories.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Orders;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class RestOrderAPI implements RestAPI<Orders, Long>{


    final String BASE_URL = "http://10.0.2.2:8080/order/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();
    
    @Override
    public Orders get(Long id)
    {
        final String url = BASE_URL + id.toString();
        HttpEntity<Orders> requestEntity = new HttpEntity<Orders>(requestHeaders);
        ResponseEntity<Orders> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity, Orders.class);
        Orders order = responseEntity.getBody();
        return order;
    }

    public String post(Orders entity)
    {
        return "hello";
    }

    public String put(Orders entity)
    {
        return "hello";
    }

    public void delete(Orders entity)
    {}
    

    @Override
    public List<Orders> getAll()
    {
        List<Orders> orderList = new ArrayList<>();
        final String url = BASE_URL + "all";
        System.out.println("Request for orders!!");
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<Orders[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Orders[].class);

        System.out.println("Received orders!!");
        if(responseEntity.hasBody()) {
            Orders[] resultSet = responseEntity.getBody();

            for (Orders order : resultSet) {
                orderList.add(order);
            }

            return orderList;
        }

        else
        {
            return null;
        }
    }
    
}
