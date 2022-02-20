package com.kbtg.techkamp.week1.shop.models.requests;

public class PaymentRequest {

    private String cvv;
    private String fullName;
    private String expiry;
    private String cardNumber;
    private Double prices;

    public PaymentRequest(String cvv, String fullName, String expiry, String cardNumber, Double prices) {
        this.cvv = cvv;
        this.fullName = fullName;
        this.expiry = expiry;
        this.cardNumber = cardNumber;
        this.prices = prices;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }
}
