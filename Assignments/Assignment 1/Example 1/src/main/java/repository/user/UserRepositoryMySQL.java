package repository.user;

import model.Account;
import model.Book;
import model.User;
import model.builder.BookBuilder;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

/**
 * Created by Alex on 11/03/2017.
 */
public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while(resultSet.next()){
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e){

        }
        return users;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameAndPasswordNotification;
    }

    @Override
    public Long findIdByUsername(String username) {
        Long id = -1L;
        try {
            Statement statement = connection.createStatement();
            String sql = "Select id from `" + USER + "` where `username`=\'"+username+"\'";

            ResultSet rs = statement.executeQuery(sql);

            if(rs.next()) {
                id = rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1l;
        }
        return id;
    }

    @Override
    public List<String> getNamesOfEmployees() {
        List<String> strings = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select username from `" + USER + "`";

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                strings.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return strings;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setString(3, user.getCNP());
            insertUserStatement.setString(4, user.getAddress());
            insertUserStatement.setDate(5, user.getDateOfEmployment());
            insertUserStatement.setString(6,user.getPhoneNr());

            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public User findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getUserFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, User.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, User.class.getSimpleName());
        }
    }
    @Override
    public User findByUsername(String username) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user where username=\'" + username+"'";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getUserFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(-2L, User.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(-2L, User.class.getSimpleName());
        }
    }

    @Override
    public boolean update(Long id, String address, String password, String CNP, String phoneNr) {
        if(id!=-1L){
            if(!address.equals(""))
                if(!updateAddress(id,address))
                    return false;
            if(!phoneNr.equals(""))
                if(!updatePhoneNr(id,phoneNr))
                    return false;
            if(!password.equals(""))
                if(!updatePassword(id,password))
                    return false;
            if(!CNP.equals(""))
                if(!updateCNP(id,CNP))
                    return false;
            return true;
        }
        return false;
    }
    @Override
    public boolean updateAddress(Long id, String address) {
        try{
            Statement statement = connection.createStatement();
            String sql = "UPDATE user SET address ='"+address+"' where id = "+ id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean updatePassword(Long id, String password){
        try{
            Statement statement = connection.createStatement();
            String sql = "UPDATE user SET password ='"+password+"' where id = "+ id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean updateCNP(Long id, String CNP){
        try{
            Statement statement = connection.createStatement();
            String sql = "UPDATE user SET CNP ='"+CNP+"' where id = "+ id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean updatePhoneNr(Long id, String phoneNr){
        try{
            Statement statement = connection.createStatement();
            String sql = "UPDATE user SET phone_nr ='"+phoneNr+"' where id = "+ id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id = "+id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .setAddress(rs.getString("address"))
                .setCNP(rs.getString("CNP"))
                .setDateOfEmployment(rs.getDate("date_of_employment"))
                .setPhoneNr(rs.getString("phone_nr"))
                .build();
    }

}
