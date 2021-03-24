package model;

import java.sql.Date;
import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public class User {

    private Long id;
    private String username;
    private String password;
    private List<Role> roles;
    private String address;
    private String CNP;
    private String phoneNr;
    private Date dateOfEmployment;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getAddress() {
        return address;
    }

    public String getCNP() {
        return CNP;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
