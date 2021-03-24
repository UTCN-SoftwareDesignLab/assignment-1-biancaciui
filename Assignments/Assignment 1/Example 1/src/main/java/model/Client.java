package model;

public class Client {
    private Long id;
    private String username;
    private String CNP;
    private String address;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCNP() {
        return CNP;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
