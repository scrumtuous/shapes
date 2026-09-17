package com.mcnz.mm.service;

import java.util.*;

import com.mcnz.mm.Client;

public class MockClientLookupService extends ClientLookupService {

    private final Map<String, Client> clients = new HashMap<>();

    public MockClientLookupService() {
        clients.put("Alice Chen",       new Client("C001", "Alice Chen", 75_000));
        clients.put("Ben Whitfield",    new Client("C002", "Ben Whitfield", 50_000));
        clients.put("Chidi Nwosu",      new Client("C003", "Chidi Nwosu", 8_000));
        clients.put("Diane Kowalski",   new Client("C004", "Diane Kowalski", 18_000));
        clients.put("Elliot Park",      new Client("C005", "Elliot Park", 40_000));
        clients.put("Farida Haidari",   new Client("C006", "Farida Haidari", 25_000));
        clients.put("Grace Lindqvist",  new Client("C007", "Grace Lindqvist", 12_000));
        clients.put("Hassan Malik",     new Client("C008", "Hassan Malik", 3_000));
        clients.put("Isla Fraser",      new Client("C009", "Isla Fraser", 15_000));
        clients.put("Jamal Ferris",     new Client("C010", "Jamal Ferris", 20_000));
    }

    @Override
    public Client lookup(String name) {
        return clients.get(name);
    }
}