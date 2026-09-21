package com.mcnz.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class StoreRestMockData {

    private final List<Map<String, Object>> customers = List.of(
        customer(1, "Alice Chen", "02108", "Boston"),
        customer(2, "Ben Whitfield", "60601", "Chicago"),
        customer(3, "Chidi Nwosu", "30303", "Atlanta"),
        customer(4, "Diane Kowalski", "98101", "Seattle"),
        customer(5, "Elliot Park", "78701", "Austin"),
        customer(6, "Farida Haidari", "80202", "Denver"),
        customer(7, "Grace Lindqvist", "97205", "Portland"),
        customer(8, "Hassan Malik", "10001", "New York"),
        customer(9, "Isla Fraser", "33101", "Miami"),
        customer(10, "Jamal Ferris", "02116", "Boston")
    );

    private final List<Map<String, Object>> purchases = List.of(
        purchase(
            1001,
            "2026-01-05",
            1,
            "Alice Chen",
            "Boston",
            List.of(
                product(101, "Wireless Mouse", 1, 24.99),
                product(102, "USB-C Cable", 1, 9.99)
            )
        ),
        purchase(
            1002,
            "2026-01-06",
            3,
            "Chidi Nwosu",
            "Atlanta",
            List.of(
                product(103, "Monitor Stand", 1, 32.50)
            )
        ),
        purchase(
            1003,
            "2026-01-07",
            5,
            "Elliot Park",
            "Austin",
            List.of(
                product(104, "Mechanical Keyboard", 1, 89.00)
            )
        ),
        purchase(
            1004,
            "2026-01-08",
            6,
            "Farida Haidari",
            "Denver",
            List.of(
                product(105, "Noise Cancelling Headphones", 1, 129.99)
            )
        ),
        purchase(
            1005,
            "2026-01-09",
            3,
            "Chidi Nwosu",
            "Atlanta",
            List.of(
                product(106, "HDMI Cable", 1, 7.99)
            )
        ),
        purchase(
            1006,
            "2026-01-12",
            8,
            "Hassan Malik",
            "New York",
            List.of(
                product(107, "Smart Plug", 2, 14.50)
            )
        ),
        purchase(
            1007,
            "2026-01-13",
            9,
            "Isla Fraser",
            "Miami",
            List.of(
                product(108, "Coffee Mug", 2, 12.99)
            )
        ),
        purchase(
            1008,
            "2026-01-14",
            10,
            "Jamal Ferris",
            "Boston",
            List.of(
                product(109, "Gaming Mouse", 1, 54.95)
            )
        )
    );

    private final List<Map<String, Object>> refunds = List.of(
        refund(9001, "2026-01-12", 1, "Alice Chen", "Boston", "USB-C Cable", 1, 9.99, "PARTIAL"),
        refund(9002, "2026-01-13", 4, "Diane Kowalski", "Seattle", "Webcam", 1, 59.99, "FULL"),
        refund(9003, "2026-01-15", 2, "Ben Whitfield", "Chicago", "Wireless Keyboard", 1, 49.99, "FULL"),
        refund(9004, "2026-01-16", 3, "Chidi Nwosu", "Atlanta", "HDMI Cable", 2, 7.99, "PARTIAL"),
        refund(9005, "2026-01-16", 7, "Grace Lindqvist", "Portland", "Portable SSD", 1, 84.50, "FULL")
    );

    public List<Map<String, Object>> getCustomers(String city) {

        if (city == null || city.isBlank()) {
            return customers;
        }

        List<Map<String, Object>> matches = new ArrayList<>();

        for (Map<String, Object> customer : customers) {
            String customerCity = (String) customer.get("city");
            if (customerCity.equalsIgnoreCase(city)) {
                matches.add(customer);
            }
        }

        return matches;
    }

    public Optional<Map<String, Object>> getCustomer(long customerId) {
        for (Map<String, Object> customer : customers) {
            long id = (Long) customer.get("id");
            if (id == customerId) {
                return Optional.of(customer);
            }
        }

        return Optional.empty();
    }

    public List<Map<String, Object>> getCities() {
        List<String> cityNames = new ArrayList<>();

        for (Map<String, Object> customer : customers) {
            String city = (String) customer.get("city");
            if (!cityNames.contains(city)) {
                cityNames.add(city);
            }
        }

        Collections.sort(cityNames);

        List<Map<String, Object>> cityInfos = new ArrayList<>();

        for (String city : cityNames) {
            long customerCount = 0;

            for (Map<String, Object> customer : customers) {
                String customerCity = (String) customer.get("city");
                if (customerCity.equals(city)) {
                    customerCount++;
                }
            }

            cityInfos.add(cityInfo(city, customerCount));
        }

        return cityInfos;
    }

    public List<Map<String, Object>> getProducts(String name) {

        List<Map<String, Object>> matches = new ArrayList<>();

        for (Map<String, Object> purchase : purchases) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> productList = (List<Map<String, Object>>) purchase.get("products");

            for (Map<String, Object> product : productList) {
                String productName = (String) product.get("name");
                if (
                    name == null ||
                    name.isBlank() ||
                    productName.toLowerCase().contains(name.toLowerCase())
                ) {
                    matches.add(product);
                }
            }
        }

        return matches;
    }

    public Optional<Map<String, Object>> getProduct(long productId) {
        for (Map<String, Object> purchase : purchases) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> productList = (List<Map<String, Object>>) purchase.get("products");

            for (Map<String, Object> product : productList) {
                long id = (Long) product.get("id");
                if (id == productId) {
                    return Optional.of(product);
                }
            }
        }

        return Optional.empty();
    }

    public List<Map<String, Object>> getPurchases(Long customerId, String city) {
        List<Map<String, Object>> matches = new ArrayList<>();

        for (Map<String, Object> purchase : purchases) {
            Long purchaseCustomerId = (Long) purchase.get("customerId");
            String purchaseCity = (String) purchase.get("city");

            boolean customerMatches = customerId == null || purchaseCustomerId.equals(customerId);
            boolean cityMatches =
                city == null ||
                city.isBlank() ||
                purchaseCity.equalsIgnoreCase(city);

            if (customerMatches && cityMatches) {
                matches.add(purchase);
            }
        }

        return matches;
    }

    public Optional<Map<String, Object>> getPurchase(long purchaseId) {
        for (Map<String, Object> purchase : purchases) {
            long id = (Long) purchase.get("id");
            if (id == purchaseId) {
                return Optional.of(purchase);
            }
        }

        return Optional.empty();
    }

    public List<Map<String, Object>> getRefunds(Long customerId, String city) {
        List<Map<String, Object>> matches = new ArrayList<>();

        for (Map<String, Object> refund : refunds) {
            Long refundCustomerId = (Long) refund.get("customerId");
            String refundCity = (String) refund.get("city");

            boolean customerMatches = customerId == null || refundCustomerId.equals(customerId);
            boolean cityMatches =
                city == null ||
                city.isBlank() ||
                refundCity.equalsIgnoreCase(city);

            if (customerMatches && cityMatches) {
                matches.add(refund);
            }
        }

        return matches;
    }

    public Optional<Map<String, Object>> getRefund(long refundId) {
        for (Map<String, Object> refund : refunds) {
            long id = (Long) refund.get("id");
            if (id == refundId) {
                return Optional.of(refund);
            }
        }

        return Optional.empty();
    }

    public List<Map<String, Object>> getPurchasesForCustomer(long customerId) {
        return getPurchases(customerId, null);
    }

    public List<Map<String, Object>> getRefundsForCustomer(long customerId) {
        return getRefunds(customerId, null);
    }

    public List<Map<String, Object>> getCustomersForCity(String city) {
        return getCustomers(city);
    }

    public List<Map<String, Object>> getPurchasesForCity(String city) {
        return getPurchases(null, city);
    }

    public List<Map<String, Object>> getRefundsForCity(String city) {
        return getRefunds(null, city);
    }

    public Map<String, Object> getSummary() {

        long productCount = 0;
        double purchaseTotal = 0.0;
        double refundTotal = 0.0;

        for (Map<String, Object> purchase : purchases) {
            purchaseTotal += (Double) purchase.get("total");

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> productList = (List<Map<String, Object>>) purchase.get("products");
            productCount += productList.size();
        }

        for (Map<String, Object> refund : refunds) {
            refundTotal += (Double) refund.get("amount");
        }

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("customerCount", customers.size());
        summary.put("cityCount", getCities().size());
        summary.put("purchaseCount", purchases.size());
        summary.put("productCount", productCount);
        summary.put("refundCount", refunds.size());
        summary.put("purchaseTotal", purchaseTotal);
        summary.put("refundTotal", refundTotal);
        return summary;
    }

    private static Map<String, Object> customer(long id, String name, String zip, String city) {
        Map<String, Object> customer = new LinkedHashMap<>();
        customer.put("id", id);
        customer.put("name", name);
        customer.put("zip", zip);
        customer.put("city", city);
        return customer;
    }

    private static Map<String, Object> product(long id, String name, int quantity, double price) {
        Map<String, Object> product = new LinkedHashMap<>();
        product.put("id", id);
        product.put("name", name);
        product.put("quantity", quantity);
        product.put("price", price);
        return product;
    }

    private static Map<String, Object> purchase(
            long id,
            String date,
            long customerId,
            String customerName,
            String city,
            List<Map<String, Object>> products) {

        Map<String, Object> purchase = new LinkedHashMap<>();
        purchase.put("id", id);
        purchase.put("date", date);
        purchase.put("customerId", customerId);
        purchase.put("customerName", customerName);
        purchase.put("city", city);
        purchase.put("products", products);

        double total = 0.0;
        for (Map<String, Object> product : products) {
            Integer quantity = (Integer) product.get("quantity");
            Double price = (Double) product.get("price");
            total += quantity * price;
        }

        purchase.put("total", total);
        return purchase;
    }

    private static Map<String, Object> refund(
            long id,
            String date,
            long customerId,
            String customerName,
            String city,
            String productName,
            int quantity,
            double amount,
            String kind) {

        Map<String, Object> refund = new LinkedHashMap<>();
        refund.put("id", id);
        refund.put("date", date);
        refund.put("customerId", customerId);
        refund.put("customerName", customerName);
        refund.put("city", city);
        refund.put("productName", productName);
        refund.put("quantity", quantity);
        refund.put("amount", amount);
        refund.put("kind", kind);
        return refund;
    }

    private static Map<String, Object> cityInfo(String name, long customerCount) {
        Map<String, Object> city = new LinkedHashMap<>();
        city.put("name", name);
        city.put("customerCount", customerCount);
        return city;
    }
}