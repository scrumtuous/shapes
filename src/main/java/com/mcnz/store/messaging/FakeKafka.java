package com.mcnz.store.messaging;

import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;

/** Classroom infrastructure: starts Kafka before Spring creates the listeners. */
public class FakeKafka {
    private static EmbeddedKafkaKraftBroker broker;

    public static void start() {
        broker = new EmbeddedKafkaKraftBroker(1, 3,
                "purchases.validate", "purchases.process");
        broker.brokerListProperty("spring.kafka.bootstrap-servers");
        broker.afterPropertiesSet();
        Runtime.getRuntime().addShutdownHook(new Thread(new StopKafka()));
    }

    private static class StopKafka implements Runnable {
        @Override
        public void run() {
            broker.destroy();
        }
    }
}