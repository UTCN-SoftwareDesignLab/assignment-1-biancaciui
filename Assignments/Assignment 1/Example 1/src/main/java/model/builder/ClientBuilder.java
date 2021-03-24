package model.builder;

import model.Client;

public class ClientBuilder {

    private Client client;

    public ClientBuilder(){client = new Client();}

    public ClientBuilder setId(Long id){client.setId(id);return this;}
    public ClientBuilder setUsername(String username){client.setUsername(username);return this;}
    /*public ClientBuilder setPassword(String password){client.setPassword(password);return this;}*/
    public ClientBuilder setCNP(String cnp){client.setCNP(cnp);return this;}
    public ClientBuilder setAddress(String address){client.setAddress(address);return this;}

    public Client build(){return client;}
}
