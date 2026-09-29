package com.mcnz.store.messaging;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StoreProducers {

    @Autowired
    private KafkaTemplate<String, Long> kafka;

    public void submit(Long purchaseId) {
        Long id = Objects.requireNonNull(purchaseId);
        kafka.send("purchases.validate", id).join();
    }
}