package com.jpmc.midascore.service;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TransactionService {
    private final UserRepository userRepository;
    private final TransactionRecordRepository transactionRecordRepository;

    @Autowired
    public TransactionService(UserRepository userRepository, TransactionRecordRepository transactionRecordRepository) {
        this.userRepository = userRepository;
        this.transactionRecordRepository = transactionRecordRepository;
    }

    public void processTransaction(Transaction transaction) {
        long senderId = transaction.getSenderId();
        long recipientId = transaction.getRecipientId();
        float amount = transaction.getAmount();


        // verify IDs exist in UserRecord
        Optional<UserRecord> sender = this.userRepository.findById(senderId);
        Optional<UserRecord> recipient = this.userRepository.findById(recipientId);

        if (sender.isEmpty() || recipient.isEmpty()) return;
        UserRecord senderRecord = sender.get();
        UserRecord recipientRecord = recipient.get();

        if (senderRecord.getBalance() >= amount) {
            senderRecord.setBalance(senderRecord.getBalance() - amount);
            this.userRepository.save(senderRecord);
            recipientRecord.setBalance(recipientRecord.getBalance() + amount);
            this.userRepository.save(recipientRecord);

            this.transactionRecordRepository.save(new TransactionRecord(senderRecord, recipientRecord, amount));
        }
    }
}
