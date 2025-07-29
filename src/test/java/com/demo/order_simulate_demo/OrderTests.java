package com.demo.order_simulate_demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OrderTests {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Test
	void createOrder() {
        System.out.println("------------START TEST createOrder------------");

        String url = "http://localhost:8080/api/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"symbol\":\"VND\",\"price\":\"10\",\"quantity\":\"111\",\"side\":\"BUY\"" +
                ",\"status\":\"PENDING\",\"createdTime\":\"value\"}";

//        String requestBody = "{\"symbol\":\"VND\",\"price\":\"10\",\"quantity\":\"111\",\"side\":\"BUY1\"" +
//                ",\"status\":\"PENDING\",\"createdTime\":\"value\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Xử lý response
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error calling API: " + e.getMessage());
        }

        System.out.println("------------END TEST createOrder------------");
	}

	@Test
	void cancelOrder() {
        System.out.println("------------START TEST cancelOrder------------");

        String url = "http://localhost:8080/api/orders/1/cancel";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Xử lý response
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error calling API: " + e.getMessage());
        }

        System.out.println("------------END TEST cancelOrder------------");
	}

	@Test
	void getAllOrders() {
        System.out.println("------------START TEST getAllOrders------------");

        String url = "http://localhost:8080/api/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Xử lý response
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error calling API: " + e.getMessage());
        }

        System.out.println("------------END TEST getAllOrders------------");
	}

	@Test
	void getOrderById() {
        System.out.println("------------START TEST getOrderById------------");

        String url = "http://localhost:8080/api/orders/1";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Xử lý response
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error calling API: " + e.getMessage());
        }

        System.out.println("------------END TEST getOrderById------------");
	}

	@Test
	void SimulateExecution() {
        System.out.println("------------START TEST SimulateExecution------------");

        String url = "http://localhost:8080/api/orders/simulate-execution";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Xử lý response
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error calling API: " + e.getMessage());
        }

        System.out.println("------------END TEST SimulateExecution------------");
	}
}
