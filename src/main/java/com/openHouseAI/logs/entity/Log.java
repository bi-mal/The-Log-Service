package com.openHouseAI.logs.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="log")
public class Log {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private String userId;

    @Column(name="session_id")
    private String sessionId;

    @OneToMany(mappedBy= "log",cascade=CascadeType.ALL)
    private List<Action> actions;

    public Log() {

    }

    public Log(int id, String userId, String sessionId) {
    this.id= id;
    this.userId = userId;
        this.sessionId = sessionId;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getSessionId() {
        return sessionId;
    }


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Action> getActions() {
        return actions;
    }


    public void setActions(List<Action> action) {
        this.actions = action;
        action.forEach(entity -> entity.setLog(this));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

    }

    @Override
    public String toString() {
        return "Log [userId=" + userId + ", sessionId=" + sessionId + "]";
    }

}

