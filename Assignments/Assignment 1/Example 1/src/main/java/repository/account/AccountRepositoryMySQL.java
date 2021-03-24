package repository.account;

import database.Constants;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository{
    private final Connection connection;
    private final ClientRepository clientRepository;
    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
        clientRepository = new ClientRepositoryMySQL(connection);
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public Double findAmountByUsername(String username) {
        Double amount = -1D;
        try {
            Statement statement = connection.createStatement();
            String sql = "Select amount from `" + Constants.Tables.ACCOUNT + "` where `username`=\'"+username+"\'";

            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                amount = rs.getDouble("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1D;
        }
        return amount;
    }
    @Override
    public String findCurrencyByUsername(String username) {
        String currency;
        try {
            Statement statement = connection.createStatement();
            String sql = "Select currency from `" + Constants.Tables.ACCOUNT + "` where `username`=\'"+username+"\'";

            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                currency = rs.getString("currency");
                return currency;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)");
            insertStatement.setDouble(1, account.getId());
            insertStatement.setDouble(2, account.getClient().getId());
            insertStatement.setString(3, account.getCurrency());
            insertStatement.setDate(3, new java.sql.Date(account.getDateOfCreation().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateAmount(String username, Double amount) {
        try{
            Statement statement = connection.createStatement();
            String sql = "UPDATE account SET amount ='"+amount+"' where `username`=\'"+username+"\'";
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account findByUsername(String username) throws EntityNotFoundException {
        Client c = null;
        c = clientRepository.findByUsername(username);
        if(c!=null){
            try {
                Statement statement = connection.createStatement();
                String sql = "Select * from account where client_id = "+c.getId();
                ResultSet rs = statement.executeQuery(sql);

                if (rs.next()) {
                    return getAccountFromResultSet(rs);
                } else {
                    throw new EntityNotFoundException(-3L, Account.class.getSimpleName());
                }
            } catch (SQLException | EntityNotFoundException e) {
                e.printStackTrace();
                throw new EntityNotFoundException(-3L, Account.class.getSimpleName());
            }
        }
        return null;
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException, EntityNotFoundException {
        Client c = clientRepository.findById(rs.getLong("client_id"));
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                //.setClient(rs.getString("title"))
                .setCurrency(rs.getString("currency"))
                .setDate(new Date(rs.getDate("date_of_creation").getTime()))
                .setAmount(rs.getLong("amount"))
                .setClient(c)
                .build();
    }
}
