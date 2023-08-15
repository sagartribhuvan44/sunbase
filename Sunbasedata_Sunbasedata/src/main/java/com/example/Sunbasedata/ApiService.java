package com.example.Sunbasedata;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Value("${api.token}")
    public String authToken;

    public String authenticate() {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        String requestJson = "{ \"login_id\": \"test@sunbasedata.com\", \"password\":\"Test@123\" }";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        
        return restTemplate.postForObject(url, entity, String.class);
    }

    public String createAssignment(String firstName, String lastName, String street, String address, String city, String state, String email, String phone) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authToken);

        String requestJson = String.format(
            "{\"first_name\":\"%s\", \"last_name\":\"%s\", \"street\":\"%s\", \"address\":\"%s\", \"city\":\"%s\", \"state\":\"%s\", \"email\":\"%s\", \"phone\":\"%s\"}",
            firstName, lastName, street, address, city, state, email, phone
        );

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            return restTemplate.postForObject(url, entity, String.class);
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCustomerList() {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        return restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();
    }

    public ResponseEntity<String> deleteCustomer(String uuid) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + authToken);

        String requestBody = "cmd=delete&uuid=" + uuid;
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }
    
    public ResponseEntity<String> updateCustomer(String uuid, String firstName, String lastName, String street, String address, String city, String state, String email, String phone) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid=" + uuid;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authToken);

        String requestJson = String.format(
            "{\"first_name\":\"%s\", \"last_name\":\"%s\", \"street\":\"%s\", \"address\":\"%s\", \"city\":\"%s\", \"state\":\"%s\", \"email\":\"%s\", \"phone\":\"%s\"}",
            firstName, lastName, street, address, city, state, email, phone
        );

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }
}
