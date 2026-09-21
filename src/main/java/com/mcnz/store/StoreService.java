package com.mcnz.store;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class StoreService {

    private final CustomerRepository customerRepository;

    private int rowsProcessed;
    private int purchaseRows;
    private int refundRows;
    private int fullRefunds;
    private int partialRefunds;
    private int unmatchedRefunds;

    public StoreService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    
    public void processPurchases() {

        for (String[] row : StorePurchasesData.ROWS) {
            processRow(row);
            rowsProcessed++;
        }
    }

    private void processRow(String[] values) {

        String transactionType = values[0];
        LocalDate date = LocalDate.parse(values[1]);
        String customerName = values[2];
        String zip = values[3];
        String city = values[4];
        String productName = values[5];
        int quantity = Integer.parseInt(values[6]);
        double price = Double.parseDouble(values[7]);

        if ("PURCHASE".equalsIgnoreCase(transactionType)) {

            processPurchase(
                date,
                customerName,
                zip,
                city,
                productName,
                quantity,
                price
            );

            purchaseRows++;
            return;
        }

        if ("REFUND".equalsIgnoreCase(transactionType)) {

            processRefund(customerName, productName, quantity);
            refundRows++;
            return;
        }

        System.out.println("Ignoring unknown transaction type: " + transactionType);
    }

    private void processPurchase(
            LocalDate date,
            String customerName,
            String zip,
            String city,
            String productName,
            int quantity,
            double price) {

        Customer customer = customerRepository.findByName(customerName).orElse(null);

        if (customer == null) {
            customer = customerRepository.save(
                new Customer(
                    customerName,
                    new Address(zip, city)
                )
            );
        }

        Purchase purchase = null;

        for (Purchase candidate : customer.purchases) {
            if (candidate.date.equals(date)) {
                purchase = candidate;
                break;
            }
        }

        if (purchase == null) {
            purchase = new Purchase(date, customer);
            customer.addPurchase(purchase);
        }

        purchase.addProduct(
            new Product(productName, quantity, price)
        );

        customerRepository.save(customer);
    }

    private void processRefund(
            String customerName,
            String productName,
            int refundQuantity) {

        Customer customer = customerRepository.findByName(customerName).orElse(null);

        if (customer == null) {
            refundNotFound(customerName, productName, refundQuantity);
            return;
        }

        Product product = null;
        LocalDate latestDate = null;

        for (Purchase purchase : customer.purchases) {
            for (Product candidate : purchase.products) {
                if (!candidate.name.equals(productName)) {
                    continue;
                }

                if (latestDate == null || purchase.date.isAfter(latestDate)) {
                    latestDate = purchase.date;
                    product = candidate;
                }
            }
        }

        if (product == null) {
            refundNotFound(customerName, productName, refundQuantity);
            return;
        }

        if (refundQuantity < product.quantity) {

            product.quantity -= refundQuantity;
            customerRepository.save(customer);

            partialRefunds++;

            System.out.printf(
                "PARTIAL REFUND: %s - %s, remaining quantity %d%n",
                customerName,
                productName,
                product.quantity
            );

            return;
        }

        Purchase purchase = product.purchase;

        purchase.removeProduct(product);

        if (purchase.products.isEmpty()) {
            customer.removePurchase(purchase);
        }

        customerRepository.save(customer);

        fullRefunds++;

        System.out.printf(
            "FULL REFUND: %s - %s removed%n",
            customerName,
            productName
        );
    }

    private void refundNotFound(
            String customerName,
            String productName,
            int refundQuantity) {

        unmatchedRefunds++;
        System.out.printf(
            "REFUND NOT FOUND: %s - %s x%d%n",
            customerName,
            productName,
            refundQuantity
        );
    }

    @Transactional(readOnly = true)
    public void printReport() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("STORE IMPORT REPORT");
        System.out.println("========================================");
        System.out.println("Rows processed:      " + rowsProcessed);
        System.out.println("Purchase rows:       " + purchaseRows);
        System.out.println("Refund rows:         " + refundRows);
        System.out.println("Full refunds:        " + fullRefunds);
        System.out.println("Partial refunds:     " + partialRefunds);
        System.out.println("Unmatched refunds:   " + unmatchedRefunds);

        System.out.println();
        System.out.println("========================================");
        System.out.println("CURRENT PURCHASES");
        System.out.println("========================================");

        double grandTotal = 0.0;

        List<Customer> customers = customerRepository.findAll();
        customers.sort(Comparator.comparing(customer -> customer.name));

        for (Customer customer : customers) {

            System.out.printf(
                "%n%s - %s, %s%n",
                customer.name,
                customer.address.city,
                customer.address.zip
            );

            customer.purchases.sort(Comparator.comparing(purchase -> purchase.date));

            for (Purchase purchase : customer.purchases) {

                System.out.println("  " + purchase.date);

                for (Product product : purchase.products) {

                    double lineTotal = product.quantity * product.price;
                    grandTotal += lineTotal;

                    System.out.printf(
                        "    %-28s x%-2d @ $%7.2f = $%8.2f%n",
                        product.name,
                        product.quantity,
                        product.price,
                        lineTotal
                    );
                }
            }
        }

        System.out.println();
        System.out.printf("CURRENT STORE TOTAL: $%.2f%n", grandTotal);
        System.out.println("========================================");
    }
}