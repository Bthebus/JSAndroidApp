package jewellerystore.com.example.jewellerystore.repositories.rest;



import java.util.ArrayList;
import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Item;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class RestItemAPI implements RestAPI<Item, Long>{

    final String BASE_URL = "http://10.0.2.2:8080/item/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public Item get(Long id)
    {
        final String url = BASE_URL + id.toString();
        HttpEntity<Item> requestEntity = new HttpEntity<Item>(requestHeaders);
        ResponseEntity<Item> responseEntity = restTemplate.exchange(url,HttpMethod.GET,requestEntity, Item.class);
        Item item = responseEntity.getBody();
        return item;
    }

    public String post(Item item)
    {
        final String url = BASE_URL + "create";
        HttpEntity<Item> requestEntity = new HttpEntity<Item>(item,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    public String put(Item item)
    {
        final String url = BASE_URL + "update/" + item.getId().toString();
        HttpEntity<Item> requestEntity = new HttpEntity<Item>(item,requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public void delete(Item item)
    {
        final String url = BASE_URL + "delete/" + item.getId().toString();
        restTemplate.delete(url,item.getId());
        /*HttpEntity<Item> requestEntity = new HttpEntity<Item>(item, requestHeaders);
        System.out.println("request entity delete");
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        System.out.println("RESPONSE entity delete");
        //return responseEntity.getBody();*/
    }

    @Override
    public List<Item> getAll()
    {
        System.out.println("REST API");
        List<Item> itemList = new ArrayList<>();
        final String url = BASE_URL + "all";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        System.out.println("headers requested");
        ResponseEntity<Item[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Item[].class);

        if(responseEntity.hasBody()) {
            System.out.println("Response entity");
            Item[] resultSet = responseEntity.getBody();
            System.out.println("body received");

            for (Item item : resultSet) {
                itemList.add(item);
            }

            System.out.println("REST API AFTER ITEMS ARE ADDED");
            return itemList;
        }

        else
        {
            System.out.println("NO ITEMS REtUrned!!!!!!!!!!");
            return null;
        }
    }
}
