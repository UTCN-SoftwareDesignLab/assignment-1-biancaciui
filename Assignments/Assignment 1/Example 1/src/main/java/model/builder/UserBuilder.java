package model.builder;

import model.Role;
import model.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setRoles(List<Role> roles) {
        user.setRoles(roles);
        return this;
    }

    public UserBuilder setId(Long id){
        user.setId(id);
        return this;
    }
    public UserBuilder setAddress(String address) {
        user.setAddress(address);
        return this;
    }

    public UserBuilder setCNP(String CNP) {
        user.setCNP(CNP);
        return this;
    }

    public UserBuilder setPhoneNr(String  phoneNr) {
        user.setPhoneNr(phoneNr);
        return this;
    }

    public UserBuilder setDateOfEmployment(Date dateOfEmployment){
        user.setDateOfEmployment(dateOfEmployment);
        return this;
    }

    public User build() {
        return user;
    }


}
