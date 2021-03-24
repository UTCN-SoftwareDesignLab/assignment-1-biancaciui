package repository;

import database.DatabaseConnectionFactory;
import model.Account;
import model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

import java.util.List;

public class AccountRepositoryMySQLTest {
    private static UserRepository userRepository;
    private static AccountRepository accountRepository;
    private static ClientRepository clientRepository;
    @BeforeClass

    public static void setupClass(){
        DatabaseConnectionFactory dbConnectionFactory = new DatabaseConnectionFactory();
        accountRepository = new AccountRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection());
        //userRepository = new UserRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection(),rightsRolesRepository);
        clientRepository = new ClientRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection());
        //userRepository.removeAll();
        //TODO: make sure cleanup is uncommented
    }
    @Test
    public void findAll() {
        System.out.println("findAll");
        List<Account> accounts = accountRepository.findAll();
        Assert.assertFalse(accounts.isEmpty());
    }


}
