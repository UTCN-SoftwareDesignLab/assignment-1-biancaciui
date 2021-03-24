package repository.account;

import database.Constants;
import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean save(Account account);

    void removeAll();

    public Account findByUsername(String username) throws EntityNotFoundException;

    public Double findAmountByUsername(String username);

    public String findCurrencyByUsername(String username);

    public boolean updateAmount(String username, Double amount);

}

