package repository;

import database.Constants;
import database.DatabaseConnectionFactory;
import model.Book;
import model.Role;
import model.User;
import model.builder.BookBuilder;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class UserRepositoryMySQLTest {
    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;

    @BeforeClass
    public static void setupClass(){
        DatabaseConnectionFactory dbConnectionFactory = new DatabaseConnectionFactory();
        rightsRolesRepository = new RightsRolesRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection());
        userRepository = new UserRepositoryMySQL(dbConnectionFactory.getConnectionWrapper(true).getConnection(),rightsRolesRepository);
        //userRepository.removeAll();
        //TODO: make sure cleanup is uncommented
    }

    @Before
    public void setup() {
        //userRepository.removeAll();
    }

    @Test
    public void findAll() {
        System.out.println("findAll");
        List<User> noUsers = userRepository.findAll();
        Assert.assertFalse(noUsers.isEmpty());
    }

    @Test
    public void save(){
        System.out.println("save");
        UserBuilder builder = new UserBuilder();
        UserBuilder userBuilder = builder.setUsername("ionPrimul_1@yahoo.com").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(Constants.Roles.EMPLOYEE))).setAddress("Str Ardealul frumos nr 23").setCNP("1230698354653").setPassword("12#@dsRFE53").setId(123L).setPhoneNr("0762534253").setDateOfEmployment(new Date(Calendar.getInstance().getTime().getTime()));
        Assert.assertTrue(userRepository.save(userBuilder.build()));
    }
    @Test
    public void findById() throws EntityNotFoundException {
        System.out.println("findById");
        User u1 = new UserBuilder().setUsername("ionPrimul_1@yahoo.com").setAddress("Str Ardealul frumos nr 23").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(Constants.Roles.EMPLOYEE))).setDateOfEmployment(new Date(Calendar.getInstance().getTime().getTime())).setCNP("1230698354653").setPassword("12#@dsRFE53").setId(123L).setPhoneNr("0762534253").build();
        User u2 = new UserBuilder().setUsername("celalaltIon@yahoo.com").setAddress("Str Ardealul frumos nr 2").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(Constants.Roles.EMPLOYEE))).setDateOfEmployment(new Date(Calendar.getInstance().getTime().getTime())).setCNP("1230698625360").setPassword("12#@dsRsadcx").setId(124L).setPhoneNr("0762146242").build();
        User user = null;
        userRepository.save(u2);
        userRepository.save(u1);
        List<User> noUsers = userRepository.findAll();
        User u0 = noUsers.get(2);//ion
        User found = userRepository.findById(12L);
        Assert.assertEquals(u0.getUsername(), found.getUsername());
    }
    @Test
    public void findByUsernameAndPassword() throws EntityNotFoundException {
        System.out.println("findByUsernameAndPassword");
        User u1 = new UserBuilder().setUsername("ionAlDoilea_1@yahoo.com").setAddress("Str Ardealul frumos nr 23").setCNP("1230636154653").setRoles(Collections.singletonList(rightsRolesRepository.findRoleByTitle(Constants.Roles.EMPLOYEE))).setPassword("12#@dsRFE53").setId(123L).setPhoneNr("0762532533").setDateOfEmployment(new Date(Calendar.getInstance().getTime().getTime())).build();

        Role customerRole = rightsRolesRepository.findRoleByTitle(Constants.Roles.EMPLOYEE);
        u1.setRoles(Collections.singletonList(customerRole));
        userRepository.save(u1);
        User u = userRepository.findByUsernameAndPassword("ionPrimul_1@yahoo.com","12#@dsRFE53" ).getResult();
        Assert.assertNotNull(u);
    }
    @Test
    public void updatePassword() throws EntityNotFoundException {
        userRepository.updatePassword(1L,"hello@3$fsS");
        User u = userRepository.findById(1L);
        Assert.assertEquals(u.getPassword(),"hello@3$fsS");
    }
    @Test
    public void updateAddress() throws EntityNotFoundException {
        userRepository.updateAddress(1L,"Ion L. Caragiale Str. nr 13");
        User u = userRepository.findById(1L);
        Assert.assertEquals(u.getAddress(),"Ion L. Caragiale Str. nr 13");
    }
    @Test
    public void updateCNP() throws EntityNotFoundException {
        userRepository.updateCNP(1L,"1230636000001");
        User u = userRepository.findById(1L);
        Assert.assertEquals(u.getCNP(),"1230636000001");
    }
    @Test
    public void updatePhoneNr() throws EntityNotFoundException {
        userRepository.updatePhoneNr(1L,"0746352000");
        User u = userRepository.findById(1L);
        Assert.assertEquals(u.getPhoneNr(),"0746352000");
    }
    @Test
    public void updateData() throws EntityNotFoundException {
        updateCNP();updateAddress();updatePassword();updatePhoneNr();
    }
}
