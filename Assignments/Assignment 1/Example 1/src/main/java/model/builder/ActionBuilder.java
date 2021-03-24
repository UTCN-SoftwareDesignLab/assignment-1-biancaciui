package model.builder;

import model.Action;
import model.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActionBuilder {
    private Action action;
    public ActionBuilder(){action = new Action();}
    public ActionBuilder setId(Long id){action.setId(id);return this;}
    public ActionBuilder setUser(User user){action.setUser(user); return this;}
    public ActionBuilder setDateTime(LocalDateTime date){action.setDateTime(date); return this;}
    public ActionBuilder setActionName(String actionName){action.setActionName(actionName); return this;}
    public Action build(){return action;}
}
