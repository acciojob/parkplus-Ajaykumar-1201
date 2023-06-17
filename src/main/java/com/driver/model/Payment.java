package com.driver.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean paymentComplete;
    @Enumerated(value = EnumType.STRING)
    private PaymentMode paymentMode;

    @JsonIgnore
    @OneToOne
    @JoinColumn
    private Reservation reservation;

    public Payment() {
    }

    public Payment(int id, boolean paymentComplete, PaymentMode paymentMode) {
        this.id = id;
        this.paymentComplete = paymentComplete;
        this.paymentMode = paymentMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}
