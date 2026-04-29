package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="sender_id", nullable=false)
    private UserRecord senderId;

    @ManyToOne
    @JoinColumn(name="reciever_id", nullable=false)
    private UserRecord receiverId;

    private float amount;

    public TransactionRecord(UserRecord senderId, UserRecord receiverId, float amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    protected TransactionRecord() {
    }


    public UserRecord getSenderId() {
        return senderId;
    }

    public void setSenderId(UserRecord senderId) {
        this.senderId = senderId;
    }

    public UserRecord getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UserRecord receiverId) {
        this.receiverId = receiverId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
