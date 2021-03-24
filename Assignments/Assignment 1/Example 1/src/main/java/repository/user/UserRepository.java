package repository.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    Long findIdByUsername(String username);

    List<String> getNamesOfEmployees();

    User findById(Long id) throws EntityNotFoundException;
    User findByUsername(String username) throws EntityNotFoundException;
    //User findUser(String username, String password);
    boolean update(Long id, String address, String password, String CNP, String phoneNr);
    boolean updateAddress(Long id, String address);
    boolean updatePassword(Long id, String password);
    boolean updateCNP(Long id, String CNP);
    boolean updatePhoneNr(Long id, String phoneNr);
    boolean delete(Long id);
    boolean save(User user);

    void removeAll();

}
