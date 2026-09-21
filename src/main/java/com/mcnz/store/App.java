package com.mcnz.store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


public class App implements CommandLineRunner {

    private final StoreService storeService;

    public App(StoreService storeService) {
        this.storeService = storeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    
    public void run(String... args) throws Exception {
        storeService.processPurchases();
        storeService.printReport();
    }
}