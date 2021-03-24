package model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Action {
    private Long id;
    private User user;
    private LocalDateTime dateTime;
    private String actionName;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getActionName() {
        return actionName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String toString(){
        return "User "+ getUser().getUsername() + " - "+ getActionName()+" at "+getDateTime();
    }
}
