package repository;

import database.Constants;
import database.DatabaseConnectionFactory;
import launcher.Launcher;
import model.builder.ActionBuilder;
import model.builder.UserBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.action.ActionRepository;
import repository.action.ActionRepositoryMySQL;

import java.time.LocalDateTime;

public class ActionRepositoryMySQLTest {

    private static ActionRepository actionRepository;

    @BeforeClass
    public static void setupClass() {
        DatabaseConnectionFactory dbConnectionFactory = new DatabaseConnectionFactory();
        actionRepository = new ActionRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection());
        actionRepository.removeAll();
    }

  /*  @Test
    public void save(){
        UserBuilder userBuilder = new UserBuilder();
        ActionBuilder actionBuilder = new ActionBuilder();
        userBuilder = userBuilder.setId(1L).setAddress("Str. Aualeu nr 4, Ardeal").setUsername("Ion").setPassword("2#24FrsW.").setCNP("1230523747485").setPhoneNr("0735463728");//TODO:.setRoles();

      /*  builder = builder.set("Joe Generic").setAddress("Infinite Loop 1,CA").setIdentityCard("KT696969").setPersonalNumericCode("1234567890112");
        Assertions.assertTrue(clientRepository.save(builder.build()));
        actionRepository.save(new ActionBuilder().setActionName(Constants.Rights.CREATE_EMPLOYEE).setUser(Launcher.user).setDateTime(LocalDateTime.now()).build());

    }*/
}
