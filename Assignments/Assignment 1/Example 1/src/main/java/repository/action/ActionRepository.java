package repository.action;

import model.Action;
import model.User;
import repository.EntityNotFoundException;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface ActionRepository {
    List<Action> findAll();
    Action findById(Long id) throws EntityNotFoundException;
    boolean save(Action action);
    List<Action> findBetweenDates(User user, LocalDateTime startDate, LocalDateTime endDate);
    void removeAll();
}
