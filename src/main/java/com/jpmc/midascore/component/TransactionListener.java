package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {


    @KafkaListener(id = "midas-core", topics =  "${general.kafka-topic}")
    public void listen(Transaction transaction) {
        System.out.println("Received: " + transaction);
    }
}
