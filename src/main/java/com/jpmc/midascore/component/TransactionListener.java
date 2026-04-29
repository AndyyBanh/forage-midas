package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    private final TransactionService transactionService;

    @Autowired
    public TransactionListener(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @KafkaListener(id = "midas-core", topics =  "${general.kafka-topic}")
    public void listen(Transaction transaction) {
        this.transactionService.processTransaction(transaction);
    }
}
