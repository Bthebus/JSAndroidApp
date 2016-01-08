package jewellerystore.com.example.jewellerystore.repositories.rest;


/**
 * Created by Braedy Thebus on 2015-11-16.
 */

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestMethods {

    public static RestTemplate getRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        return restTemplate;
    }

    public static HttpHeaders getHeaders()
    {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        return requestHeaders;
    }
}
