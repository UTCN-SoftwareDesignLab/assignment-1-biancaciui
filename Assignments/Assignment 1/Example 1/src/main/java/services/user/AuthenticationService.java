package services.user;

import model.Right;
import model.Role;
import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface AuthenticationService {

    Notification<Boolean> register(String username, String password, String role);

    Notification<User> login(String username, String password);

    Notification<Boolean> createEmployee(String username, String password, String address, String CNP, String phoneNr);

    Notification<Boolean> updateEmployee(String username, String password, String address, String CNP, String phoneNr) throws EntityNotFoundException;

    boolean logout(User user);

}
