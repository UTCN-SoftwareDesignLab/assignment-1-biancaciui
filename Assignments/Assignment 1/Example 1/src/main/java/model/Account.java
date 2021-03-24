package model;

import database.Constants;

import java.sql.Date;

public class Account {
    private Long id;
    private Client client;
    private String currency;
    private double amount;
    private Date dateOfCreation;

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
