package com.mcnz.store;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/*
Test it here, accounting for any port change in application.properties

http://localhost:8080/swagger-ui/index.html

*/

public class StoreRestController {

    private final StoreRestMockData mockData;
    private final CustomerRepository customerRepository;

    public StoreRestController(StoreRestMockData mockData, CustomerRepository customerRepository) {
        this.mockData = mockData;
        this.customerRepository = customerRepository;
    }

    
    public Map<String, String> apiIndex() {

        Map<String, String> resources = new LinkedHashMap<>();

        resources.put("customers", "/api/customers");
        resources.put("customerByName", "/api/customers/by-name?name=Mark%20Cavendish");
        resources.put("cities", "/api/cities");
        resources.put("products", "/api/products");
        resources.put("purchases", "/api/purchases");
        resources.put("refunds", "/api/refunds");
        resources.put("summary", "/api/summary");

        return resources;
    }

    
    public List<Map<String, Object>> customers(
            @RequestParam(required = false) String city) {

        return mockData.getCustomers(city);
    }

    /* http://localhost:8080/api/customers/by-name?name=Wout  */
    
    public Map<String, Object> customerByName(
            @RequestParam String name) {

        Customer customer = customerRepository
                .findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Customer not found"
        ));

        Map<String, Object> customerMap = new LinkedHashMap<>();
        customerMap.put("id", customer.id);
        customerMap.put("name", customer.name);
        customerMap.put("zip", customer.address.zip);
        customerMap.put("city", customer.address.city);

        return customerMap;
    }

    
    public Map<String, Object> customer(
            @PathVariable long customerId) {

        return mockData
                .getCustomer(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Customer not found"
        ));
    }

    
    public List<Map<String, Object>> customerPurchases(
            @PathVariable long customerId) {

        return null;
    }

    
    public List<Map<String, Object>> customerRefunds(
            @PathVariable long customerId) {

        return null;
    }

    
    public List<Map<String, Object>> cities() {
        return null;
    }

    @GetMapping("/cities/{city}/customers")
    public List<Map<String, Object>> cityCustomers(
            @PathVariable String city) {

        return null;
    }

    
    public List<Map<String, Object>> cityPurchases(
            @PathVariable String city) {

        return null;
    }

    
    public List<Map<String, Object>> cityRefunds(
            @PathVariable String city) {

        return null;
    }

    
    public List<Map<String, Object>> products(
            @RequestParam(required = false) String name) {

        return null;
    }

    
    public Map<String, Object> product(
            @PathVariable long productId) {

        return mockData
                .getProduct(productId)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not found"
        ));
    }

    
    public List<Map<String, Object>> purchases(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) String city) {

        return null;
    }

    
    public Map<String, Object> purchase(
            @PathVariable long purchaseId) {

        return null;
    }

    
    public List<Map<String, Object>> refunds(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) String city) {

        return null;
    }

    
    public Map<String, Object> refund(
            @PathVariable long refundId) {

        return null;
    }

    
    public Map<String, Object> summary() {
        return null;
    }
}
