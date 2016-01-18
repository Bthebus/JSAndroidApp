package jewellerystore.com.example.jewellerystore.repositories.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import jewellerystore.com.example.jewellerystore.model.OrderLine;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class RestOrderlineAPI implements RestAPI<OrderLine, Long> {

    final String BASE_URL = "http://10.0.2.2:8080/orderline/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public OrderLine get(Long id)
    {
        final String url = BASE_URL + id.toString();
        HttpEntity<OrderLine> requestEntity = new HttpEntity<OrderLine>(requestHeaders);
        ResponseEntity<OrderLine> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity, OrderLine.class);
        OrderLine orderLine = responseEntity.getBody();
        return orderLine;
    }

    public String post(OrderLine entity)
    {
        return "hello";
    }

    public String put(OrderLine entity)
    {
        return "hello";
    }

    public void delete(OrderLine entity)
    {}


    @Override
    public List<OrderLine> getAll()
    {
        List<OrderLine> orderList = new ArrayList<>();
        final String url = BASE_URL + "all";
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<OrderLine[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, OrderLine[].class);

        if(responseEntity.hasBody()) {
            OrderLine[] resultSet = responseEntity.getBody();

            for (OrderLine orderLine : resultSet) {
                orderList.add(orderLine);
            }

            return orderList;
        }

        else
        {
            return null;
        }
    }
}
