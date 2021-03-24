package model.builder;

import database.Constants;
import model.Account;
import model.Client;

import java.sql.Date;

public class AccountBuilder {
    private Account account;

    public AccountBuilder(){account = new Account();}

    public AccountBuilder setId(Long id){
        account.setId(id);
        return this;
    }
    public AccountBuilder setClient(Client client){
        account.setClient(client);
        return this;
    }
    public AccountBuilder setCurrency(String currency){
        account.setCurrency(currency);
        return this;
    }
    public AccountBuilder setAmount(double amount){
        account.setAmount(amount);
        return this;
    }
    public AccountBuilder setDate(Date date){
        account.setDateOfCreation(date);
        return this;
    }
    public Account build(){return account;}
}
