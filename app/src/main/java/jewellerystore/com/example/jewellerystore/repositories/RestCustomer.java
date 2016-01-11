package jewellerystore.com.example.jewellerystore.repositories;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Customer;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;

/**
 * Created by yusraAdmin on 1/10/2016.
 */
public interface RestCustomer<S, ID> {

    List<S> getByName();
    List<S> getBySurname();
    List<S> getByCompany();


}
