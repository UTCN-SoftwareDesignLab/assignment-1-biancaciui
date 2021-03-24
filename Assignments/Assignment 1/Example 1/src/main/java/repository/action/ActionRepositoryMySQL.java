package repository.action;

import database.Constants;
import model.*;
import model.builder.ActionBuilder;
import repository.EntityNotFoundException;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryMySQL implements ActionRepository{
    private final Connection connection;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    public ActionRepositoryMySQL(Connection connection) {
        this.connection = connection;
        rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
    }

    @Override
    public List<Action> findAll() {
        List<Action> actions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from employee_actions";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                actions.add(getActionFromResultSet(rs));
            }
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
        }
        return actions;
    }

    @Override
    public Action findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from employee_actions where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getActionFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Action.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Action.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Action action) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Long action_id = userRepository.findIdByUsername(a)
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO employee_actions values (null, ?, ?, ?)");
            insertStatement.setLong(1, action.getUser().getId());
            insertStatement.setLong(2, Constants.decodeRight(action.getActionName()));
            insertStatement.setTimestamp(3, Timestamp.valueOf(sdf.format(new Timestamp(System.currentTimeMillis()))));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Action> findBetweenDates(User user, LocalDateTime startDate, LocalDateTime endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Action> actions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from employee_actions WHERE id = ? AND timestamp >= ? AND timestamp <= ?");
            statement.setLong(1,user.getId());
            statement.setTimestamp(2,Timestamp.valueOf(startDate));
            statement.setTimestamp(3,Timestamp.valueOf(endDate));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Action a = getActionFromResultSet(rs);
                a.setUser(user);
                actions.add(a);
            }
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
        }
        return actions;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from employee_actions where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Action getActionFromResultSet(ResultSet rs) throws SQLException, EntityNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // User user = userRepository.findById(rs.getLong("employee_id"));
        return new ActionBuilder()
                .setId(rs.getLong("id"))
                .setActionName(Constants.decodeRight(rs.getLong("action_id")))
                .setDateTime(rs.getTimestamp("timestamp").toLocalDateTime())
                .build();
    }
}
