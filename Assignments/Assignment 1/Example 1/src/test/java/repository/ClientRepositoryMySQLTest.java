package repository;

import database.DatabaseConnectionFactory;
import org.junit.BeforeClass;
import repository.client.ClientRepository;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

public class ClientRepositoryMySQLTest {
    private static UserRepository userRepository;
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass(){
        DatabaseConnectionFactory dbConnectionFactory = new DatabaseConnectionFactory();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection());
        userRepository = new UserRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection(),rightsRolesRepository);
        //userRepository.removeAll();

    }

}
